package jp.reflexworks.atom.mapper;

import java.io.Reader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import jp.reflexworks.atom.mapper.FeedTemplateMapper.Meta;
import jp.sourceforge.reflex.exception.XMLException;
import jp.sourceforge.reflex.util.StringUtils;

/**
 * XMLデシリアライザ.
 */
public class XMLDeserializer {

	private static final String[] CNTLCHRS = {
		"\\u0000", "\\u0001", "\\u0002", "\\u0003", "\\u0004", "\\u0005", "\\u0006", "\\u0007",
		"\\b", "\\t", "\\n", "\\u000B", "\\f","\\r", "\\u000E", "\\u000F",
		"\\u0010", "\\u0011", "\\u0012", "\\u0013", "\\u0014", "\\u0015", "\\u0016", "\\u0017", 
		"\\u0018", "\\u0019", "\\u001A", "\\u001B", "\\u001C", "\\u001D", "\\u001E", "\\u001F"
	};

	/**
	 * XMLをオブジェクトに変換する.
	 * まずJSONに変換し、FeedTemplateMapperでJSONをオブジェクトに変換する。
	 * @param xml XML
	 * @param mapper FeedTemplateMapper
	 * @return オブジェクト
	 * @throws XMLException XMLパースエラー
	 */
	public Object deserialize(Reader xml, FeedTemplateMapper mapper) 
	throws XMLException {
		String json = fromXmlToJson(xml, mapper);
		if (StringUtils.isBlank(json)) {
			return null;
		}
		return mapper.fromJSON(json);
	}
	
	/**
	 * XMLをJSONに変換する.
	 * @param xml XML
	 * @param mapper FeedTemplateMapper
	 * @return JSON
	 * @throws XMLException XMLパースエラー
	 */
	public String fromXmlToJson(Reader xml, FeedTemplateMapper mapper) 
	throws XMLException {
		if (xml == null) {
			return null;
		}
		if (mapper == null) {
			throw new NullPointerException("FeedTemplateMapper is required.");
		}
		StringBuilder json = new StringBuilder();
		json.append("{");
		XMLEventReader reader = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			reader = factory.createXMLEventReader(xml);
			
			// Meta map
			Map<String, Meta> metaMap = getMetaMap(mapper.getMetalist());
			
			// Meta名のqueue
			Deque<String> nameQueue = new ArrayDeque<>();
			// 1つ前のMeta名
			//String lastName = null;
			//int lastLayer = 0;
			//String lastStartName = null;
			//int lastStartLayer = 0;
			String lastEndName = null;
			int lastEndLayer = 0;
			// カッコのqueue (閉じカッコを格納)
			Deque<String> bracketQueue = new ArrayDeque<>();
			// 配列名のqueue
			//Deque<String> arrayQueue = new ArrayDeque<>();
			//Deque<String> outBracketQueue = new ArrayDeque<>();
			//Deque<String> inBracketQueue = new ArrayDeque<>();
			//boolean isFeed = false;
			// text
			String text = "";
			// 属性を出力したかどうか
			boolean hasAttribute = false;

			while (reader.hasNext()) {
				XMLEvent event = reader.nextEvent();

				if (event.isStartElement()) {
					StartElement element = event.asStartElement();
					String tag = element.getName().getLocalPart();
					String name = getMetaname(tag, nameQueue);
					nameQueue.push(name);
					int layer = nameQueue.size();
					//if (layer == 1 && "feed".equals(tag)) {
					//	isFeed = true;
					//}
					hasAttribute = false;
					
					if (lastEndName != null && !name.equals(lastEndName)) {
						// 1つ前の終了タグがリストの場合、配列用閉じカッコを出力
						Meta lastMeta = metaMap.get(lastEndName);
						if (lastMeta.repeated && lastEndLayer == layer) {
							json.append("]");
						}
					}

					// 同列の項目の場合カンマを出力
					if (lastEndLayer == layer) {
						json.append(",");
					}
					
					// metanameの型によりカッコを出力
					if (layer == 1) {
						// タグの出力
						json.append(encloseDoubleQuotes(tag));
						json.append(":");
						json.append("{");
						bracketQueue.push("}");
					//} else if ("entry".equals(name)) {	// feed.entryの場合
					//	json.append("[{");
					//	bracketQueue.push("}]");
					} else {
						Meta meta = metaMap.get(name);
						if (meta == null) {
							throw new XMLStreamException("Invalid tag : " + tag);
						}
						if (meta.repeated) {
							// リスト
							if (lastEndLayer == layer &&
									name.equals(lastEndName)) {
								// 2件目以降はタグ名を出力しない。
							} else {
								// 1件目
								// タグの出力
								json.append(encloseDoubleQuotes(tag));
								json.append(":");
								json.append("[");
								//arrayQueue.push(name);
							}
						} else {
							// タグの出力
							json.append(encloseDoubleQuotes(tag));
							json.append(":");
						}
						if (meta.isrecord) {
							// クラス
							json.append("{");
							bracketQueue.push("}");

							// 属性
							Iterator<Attribute> it = element.getAttributes();
							if (it != null) {
								boolean isFirst = true;
								while (it.hasNext()) {
									if (isFirst) {
										isFirst = false;
									} else {
										json.append(",");
									}
									Attribute attr = it.next();
									json.append(encloseDoubleQuotes("___" + attr.getName().getLocalPart()));
									json.append(":");
									json.append(encloseDoubleQuotes(escapeJson(attr.getValue())));
								}
								if (!isFirst) {
									hasAttribute = true;
								}
							}

						} else {
							// 値
							bracketQueue.push("");
						}
					}
					
					//lastStartName = name;
					//lastStartLayer = layer;
					text = "";
					
				} else if (event.isCharacters()) {
					text += StringUtils.null2blank(event.asCharacters().getData());

				} else if (event.isEndElement()) {
					int layer = nameQueue.size();
					String name = nameQueue.pop();
					
					if (lastEndName != null) {
						// 1つ前の終了タグがリストの場合、配列用閉じカッコを出力
						Meta lastMeta = metaMap.get(lastEndName);
						if (lastMeta.repeated && lastEndLayer > layer) {
							json.append("]");
						}
					}
					
					if (layer > 1) {
						Meta meta = metaMap.get(name);
						
						// テキストノードがあれば出力する
						if (!StringUtils.isBlank(text)) {
							if (meta.isNumeric() || "Boolean".equals(meta.type)) {
								// 数値かboolean
								json.append(escapeJson(text));
							} else if (meta.isrecord) {
								// テキストノード項目(______text)
								if (hasAttribute) {
									json.append(",");
								}
								json.append(encloseDoubleQuotes("______text"));
								json.append(":");
								json.append(encloseDoubleQuotes(escapeJson(text)));
							} else {
								// 文字列
								json.append(encloseDoubleQuotes(escapeJson(text)));
							}
						}

						// 閉じカッコ
						String bracket = bracketQueue.pop();
						json.append(bracket);
						
					} else {
						// 閉じカッコ
						String bracket = bracketQueue.pop();
						json.append(bracket);
					}
					
					lastEndName = name;
					lastEndLayer = layer;
					text = "";
				}
			}
			
			json.append("}");
			
			return json.toString();

		} catch (XMLStreamException e) {
			throw new XMLException(e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Throwable e) {
					// Do nothing.
				}
			}
		}

	}
	
	/**
	 * ダブルクォーテーションで囲む.
	 * @param val
	 * @return
	 */
	private String encloseDoubleQuotes(String val) {
		StringBuilder sb = new StringBuilder();
		sb.append("\"");
		sb.append(val);
		sb.append("\"");
		return sb.toString();
	}
	
	/**
	 * タグと上位タグからmetanameを取得.
	 * {上位タグ}.{タグ}
	 * feed、entryの場合はそのまま返す。
	 * @param tag タグ
	 * @param nameQueue 上位タグ格納Queue
	 * @return metaname
	 */
	private String getMetaname(String tag, Deque<String> nameQueue) {
		String lastName = nameQueue.peek();
		if (lastName == null || "feed".equals(lastName) || "entry".equals(lastName)) {
			return tag;
		} else {
			StringBuilder sb = new StringBuilder();
			sb.append(lastName);
			sb.append(".");
			sb.append(tag);
			return sb.toString();
		}
	}
	
	/**
	 * MetaリストをMapに変換
	 * @param metalist
	 * @return Meta map
	 */
	private Map<String, Meta> getMetaMap(List<Meta> metalist) {
		Map<String, Meta> metaMap = new HashMap<>();
		for (Meta meta : metalist) {
			metaMap.put(meta.name, meta);
		}
		return metaMap;
	}
	
	/**
	 * JSON文字列のエスケープ.
	 * @param s 文字列
	 * @return JSON用エスケープ
	 */
	private String escapeJson(String s) {
		//return JSONObject.escape(s);
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
//				サーバからJSONで出力する場合には、バックスラッシュ自体がエスケープ文字とみなされてしまうため、「\\n」のようにバックスラッシュをエスケープする必要がある。
				case '"':
					sb.append("\\\"");
					break;
//				case '\'':
//					sb.append("\\\\'");
//					break;
				case '\\': 	// \
					sb.append("\\\\");
					break;
				case '\u007F': 
					sb.append("\\u007F");
					break;
				case '<':
					sb.append("\\u003C");
					break;
				case '>':
					sb.append("\\u003E");
					break;
				case '\u2028': 
					sb.append("\\u2028");
					break;
				case '\u2029': 
					sb.append("\\u2029");
					break;
				default:
					if (c < CNTLCHRS.length) {
						sb.append(CNTLCHRS[c]);
					} else {
						sb.append(c);
					}
			}
		}
		
		return sb.toString();
	}

}
