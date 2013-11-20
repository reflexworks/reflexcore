package jp.sourceforge.reflex;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import model.Nsbean;
/*
import jp.co.reflexworks.invoice.model.Customer;
import jp.co.reflexworks.invoice.model.Entry;
import jp.co.reflexworks.invoice.model.Feed;
import jp.co.reflexworks.invoice.model.Item;
import jp.co.reflexworks.invoice.model.Item_list;
import jp.co.reflexworks.invoice.model.Transaction;
import jp.reflexworks.atom.entry.Content;
import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.feed.FeedBase;
import jp.sourceforge.reflex.core.ResourceMapper;
*/
public class V15EntityTest {
/*
	// prefix={名前空間}とする。デフォルトはprefix=を省略する。
	public static final String NAMESPACE_VT = "vt=http://invoice.reflexworks.co.jp/vt/1.0";
	
	public static void main(String[] args) {
		try {

			Map<String, String> MODEL_PACKAGE = new HashMap<String, String>();
			MODEL_PACKAGE.put("jp.reflexworks.atom.feed", "http://www.w3.org/2005/Atom");
			MODEL_PACKAGE.put("jp.reflexworks.atom.entry", "http://www.w3.org/2005/Atom");
			MODEL_PACKAGE.put("jp.reflexworks.atom.source", "http://www.w3.org/2005/Atom");
			MODEL_PACKAGE.put("jp.co.reflexworks.invoice.model", "vt=http://invoice.reflexworks.co.jp/vt/1.0");
			
			ResourceMapper mapper = new ResourceMapper(MODEL_PACKAGE);
			
			Feed feed = getFeed();

			// XMLにシリアライズ
//			mapper.setJo_namespacemap(new HashMap()); // namespace initialize


//			toXML = mapper.toXML(feed,true);
			String toXML = mapper.toXML(feed,false);	// falseで名前空間を出力しない
			System.out.println("\n【XML　シリアライズテスト】:");
			System.out.println(toXML);
			
			// 試しにデシリアリズしてみる
			FeedBase feed2 = (FeedBase) mapper.fromXML(toXML);

			toXML = mapper.toXML(feed2,true);
			System.out.println("\n上をデシリアライズ:");
			System.out.println(toXML);			// 名前空間のないXMLでも正しくパースできる

			// JSONのテスト
			String toJSON = mapper.toJSON(feed2);

			System.out.println("\n【JSON　シリアライズテスト】:");
			System.out.println(toJSON);
			
			// 試しにデシリアリズしてみる
			FeedBase feed3 = (FeedBase) mapper.fromJSON(toJSON);

			toXML = mapper.toXML(feed3,true);
			System.out.println("\n上をデシリアライズ:");
			System.out.println(toXML);			// 名前空間のないXMLでも正しくパースできる

			
		} catch (Throwable e) {
			
		}
	}

	public static Feed getFeed() {

		Feed feed = new Feed();
		feed.entry = new ArrayList<EntryBase>();
		Entry entry = new Entry();
		entry.transaction = new Transaction();
		entry.transaction.total = "100";
		entry.item_list = new Item_list();
		entry.item_list.item = new ArrayList<Item>();
		entry.id = "123";
		Item item = new Item();
		item.description = "商品1";
		item.unit_price  = "100"; 
		entry.item_list.item.add(item);
		entry.content = new Content();
		entry.content._$$text = "テスト";		
		entry.id = "123";
		feed.entry.add(entry);
		
		return feed;
		
	}
	*/

}
