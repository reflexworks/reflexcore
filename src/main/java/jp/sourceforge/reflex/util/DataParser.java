package jp.sourceforge.reflex.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.logging.Logger;

import jp.sourceforge.reflex.exception.JSONException;
import jp.sourceforge.reflex.util.FileUtil;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.XML;

public class DataParser {

	private static final String ENCODING = "UTF-8";

	Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * XMLまたはJSON形式のテキストデータをObjectに変換します。<br>
	 * 文字列の最初が"{"の場合はJSON形式とみなし、それ以外をXML形式とみなします。
	 * @param entity テキストデータの絶対パスまたはURL、またはJSONかXML文字列
	 * @return JSONObjectを格納したList
	 */
	public List createObject(String entity) throws Exception {

		List ret = null;

		InputStream is = null;
		InputStreamReader ins = null;
		BufferedReader br = null;

		String body = null;

		// entityの形式をチェック
		String trimEntity = entity.trim();
		if (trimEntity.startsWith("{") || trimEntity.startsWith("<")) {
			body = entity;
		} else {
			try {
				//is = new FileUtil().getResourceStream(dataPath, true, 5);
				is = new FileUtil().getUrlStream(entity, 5);
				ins = new InputStreamReader(is, ENCODING);
				br = new BufferedReader(ins);
	
				body = getBody(br);
	
			} finally {
				try {
					if (br != null) {
						br.close();
					}
				} catch (Exception e) {}
	
				try {
					if (ins != null) {
						ins.close();
					}
				} catch (Exception e) {}
	
				try {
					if (is != null) {
						is.close();
					}
				} catch (Exception e) {}
			}
		}
		ret = createJSONObject(body);

		return ret;
	}

	/**
	 * XMLまたはJSON形式のテキストデータをObjectに変換します。<br>
	 * 文字列の最初が"{"の場合はJSON形式とみなし、それ以外をXML形式とみなします。
	 * @param body XMLまたはJSON
	 * @return JSONObjectを格納したList
	 */
	public List createJSONObject(String body) throws Exception {
		List ret = null;

		// XMLかJSONかの判定
		boolean isJson = false;
		if (body != null) {
			String trimBody = body.trim();
			if (trimBody.length() > 0 && "{".equals(trimBody.substring(0, 1))) {
				isJson = true;
			}
		}

		if (isJson) {
			// JSON
			JSONObject jsonObject = new JSONObject(body);
			ret = dividePage(jsonObject);

		} else {
			// XML
			JSONObject xmlJson = XML.toJSONObject(body);
			ret = dividePage(xmlJson);
		}

		return ret;
	}

	private String getBody(BufferedReader reader) throws IOException {
		try {
			StringBuilder sb = new StringBuilder();
			String str = null;
			while ((str = reader.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} finally {
			reader.close();
		}
	}

	private List dividePage(JSONObject jsonObject) throws JSONException {
		List ret = new ArrayList();

		// ページチェック
		boolean isPage = false;
		if (jsonObject.length() == 1) {
			Iterator it1 = jsonObject.keys();
			String key1 = (String)it1.next();

			Object obj2 = jsonObject.get(key1);
			if (obj2 instanceof JSONObject) {
				JSONObject jsonObject2 = (JSONObject)obj2;
//				if (jsonObject2.length() <= 3) {	// namespaceを合わせて3つ以下
					Iterator it2 = jsonObject2.keys();
					String key2 = (String)it2.next();
					if (key1.indexOf(key2) >= 0||key1.toLowerCase(Locale.ENGLISH).equals("feed")) {	// xxxList もしくは、feed で複数ページ(2012/7/31)
						Object obj3 = jsonObject2.get(key2);
						if (obj3 instanceof JSONArray) {
							// ページあり
							isPage = true;
							JSONArray jsonArray = (JSONArray)obj3;
							for (int i = 0; i < jsonArray.length(); i++) {
								ret.add(jsonArray.get(i));
							}
						}
					}
//				}
			}
		}

		if (!isPage) {
			ret.add(jsonObject);
		}

		return ret;
	}
	
	/**
	 * JSONObjectから指定されたキーの値を返却します。
	 * キーが複数ある場合、最初に発見した値を返却します。
	 * @param key キー
	 * @param jsonObject JSONObject
	 * @return 指定されたキーの値
	 * @throws JSONException
	 */
	public String getValue(String key, JSONObject jsonObject) throws JSONException {
		String val = null;
		if (jsonObject.has(key)) {
			val = jsonObject.getString(key);
			return val;
		} else {
			Iterator keys = jsonObject.keys();
			while (keys.hasNext()) {
				String k = (String)keys.next();
				Object obj = jsonObject.get(k);
				if (obj instanceof JSONObject) {
					val = getValue(key, (JSONObject)obj);
					if (val != null) {
						break;
					}
				}
			}
		}
		return val;
	}
}
