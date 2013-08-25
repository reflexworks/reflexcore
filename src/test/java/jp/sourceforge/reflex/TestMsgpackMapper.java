package jp.sourceforge.reflex;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.DataFormatException;

import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.feed.FeedBase;
import jp.sourceforge.reflex.core.MessagePackMapper;

import org.json.JSONException;
import org.junit.Test;

import com.carrotsearch.sizeof.ObjectTree;

//import com.carrotsearch.sizeof.ObjectTree;

public class TestMsgpackMapper {

	public static String entitytempl[] = {
		// {}がMap, #がIndex , %が暗号化, []がArray　, {} # % [] は末尾に一つだけ付けられる。*が必須項目
		"test2{2}",        //  0行目はパッケージ名(service名)
		" id#",			  // Index
		" email",
		" verified_email(Boolean)",// Boolean型 他に（int,date,long,float,doubleがある。先小文字OK、省略時はString）
		" name",
		" given_name",
		" family_name",
		" error",
		"  errors{1}",				// 多重度(n)、*がないと多重度(1)、繰り返し最大{1}
		"   domain",
		"   reason",
		"   message",
		"   locationType",
		"   location",
		"  code(int){1~100}",			// 1~100の範囲			
		"  message",
		" subInfo",
		"  favorite",
		"   food%abc*:^.{3}$",	// %abdで暗号化、必須項目、正規表現つき
		"   music[3]:^.{5}$",			// 配列(要素数max3)
		"  favorite2",
		"   food",
		"    food1",
		"  favorite3",
		"   food",
		"  hobby{}",
		"   _$$text"				// テキストノード
	};
	
	public static String entitytempl2[] = {
		// {}がMap, #がIndex , %が暗号化, []がArray　, {} # % [] は末尾に一つだけ付けられる。*が必須項目
		"test2{2}",        //  0行目はパッケージ名(service名)
		" id#",			  // Index
		" email",
		" verified_email(Boolean)",// Boolean型 他に（int,date,long,float,doubleがある。先小文字OK、省略時はString）
		" name",
		" given_name",
		" family_name",
		" error",
		"  errors{1}",				// 多重度(n)、*がないと多重度(1)、繰り返し最大{1}
		"   domain",
		"   reason",
		"   message",
		"   locationType",
		"   location",
		"  code(int){1~100}",			// 1~100の範囲			
		"  message",
		"  test",						// 追加項目
		" subInfo",
		"  favorite",
		"   food%abc*:^.{3}$",	// %abdで暗号化、必須項目、正規表現つき
		"   music[3]:^.{5}$",			// 配列(要素数max3)
		"  favorite2",
		"   food",
		"    food1",
		"  favorite3",
		"   food",
		"  hobby{}",
		"   _$$text"				// テキストノード
	};

	
	private static boolean FEED = true;
	private static boolean ENTRY = false;

	/**
	 * 項目追加テスト用
	 * @param mp
	 * @param feed
	 */
	private static void editTestEntry(MessagePackMapper mp,Object feed)  {
		try {
		Field f = feed.getClass().getField("entry");
		List entrylist = (List) f.get(feed);	
		Object entry = entrylist.get(0);	
		
		f = entry.getClass().getField("error");
		Object error = f.get(entry);	
		
		f = error.getClass().getField("test");
		f.set(error, "<この項目が追加された>");		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testJSONEntry() throws ParseException, JSONException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		
		
		System.out.println("JSON Entry デシリアライズ");
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);
				
		System.out.println("\n=== JSON Entry シリアライズ ===");
        String json2 = mp.toJSON(entry);
		System.out.println(json2);

		assertEquals(json, json2);
	}

	@Test
	public void testXMLEntry() throws ParseException, JSONException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		
		
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);
		
		System.out.println("\n=== XML Entry シリアライズ ===");
        String xml = mp.toXML(entry);
		System.out.println(xml);

		System.out.println("\n=== XML Entry デシリアライズ ===");
		EntryBase entry2 = (EntryBase) mp.fromXML(xml);

		assertEquals(json, mp.toJSON(entry2));
	}

	@Test
	public void testMsgPackEntryWithDeflateAndValidate() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		
		
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);
		
		// MessagePack test
		System.out.println("\n=== MessagePack Entry シリアライズ ===");
        byte[] mbytes = mp.toMessagePack(entry);
		System.out.println("len:"+mbytes.length);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
		System.out.println("\n=== MessagePack Entry deflate圧縮 ===");
        byte[] de = mp.deflate(mbytes);
		System.out.println("len:"+de.length+" 圧縮率："+(de.length*100/mbytes.length)+"%");
        for(int i=0;i<de.length;i++) { 
        	System.out.print(Integer.toHexString(de[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== MessagePack Entry infrate解凍 ===");
        byte[] in = mp.inflate(de);
		System.out.println("len:"+in.length);
        for(int i=0;i<in.length;i++) { 
        	System.out.print(Integer.toHexString(in[i]& 0xff)+" "); 
        } 

        System.out.println("\n=== MessagePack Entry デシリアライズ ===");

        EntryBase  muserinfo = (EntryBase) mp.fromMessagePack(in,ENTRY);	// false でEntryをデシリアライズ
        System.out.println("Validtion:"+muserinfo.validate());        
        
		assertEquals(json, mp.toJSON(muserinfo));
	}

	@Test
	public void testArrayEntry() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		
		
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);
		
		// MessagePack test
		System.out.println("\n=== Array Entry シリアライズ ===");
		
        // 項目名を省略した配列形式でもシリアライズ/デシリアライズ可能 (null は省略できない）
		// 一旦、toMessagaPack()でrawにした後、toArray()する
        byte[] mbytes = mp.toMessagePack(entry);
        String array = mp.toArray(mbytes).toString();
        
		System.out.println(array);
		EntryBase entity2 = (EntryBase) mp.fromArray(array,ENTRY);  // Entry

        System.out.println("\n=== Array Entry デシリアライズ ===");
        
		assertEquals(json, mp.toJSON(entity2));
	}

	@Test
	public void testChangeTemplateFeed() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		// 変更前
		MessagePackMapper mp2 = new MessagePackMapper(entitytempl2);	// 項目追加後	
		
		String json = "{ \"feed\" : {\"entry\" : [{\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : { \"errors\" : [{\"domain\": \"com.google.auth\",\"reason\": \"invalidAuthentication\",\"message\": \"invalid header\",\"locationType\": \"header\",\"location\": \"Authorization\"}],\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}},{\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : { \"errors\" : [{\"domain\": \"com.google.auth\",\"reason\": \"invalidAuthentication\",\"message\": \"invalid header\",\"locationType\": \"header\",\"location\": \"Authorization\"}],\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}]}}";
		FeedBase entry = (FeedBase) mp.fromJSON(json);
        byte[] mbytes = mp.toMessagePack(entry);	// mbytesは変更前のrawデータ
		
		// MessagePack test
		System.out.println("\n=== Array Feed(クラス変更後) シリアライズ ===");
		FeedBase entry2 = (FeedBase) mp2.fromMessagePack(mbytes,FEED);		
        editTestEntry(mp2,entry2);
		
        byte[] msgpack = mp2.toMessagePack(entry2);
        for(int i=0;i<msgpack.length;i++) { 
        	System.out.print(Integer.toHexString(msgpack[i]& 0xff)+" "); 
        } 
		System.out.println();
		System.out.println(mp2.toArray(msgpack));
        
		System.out.println("\n=== XML Feed(クラス変更後) シリアライズ ===");
        String xml = mp2.toXML(entry2);
		System.out.println(xml);

		System.out.println("\n=== JSON Feed(クラス変更後) シリアライズ ===");
        String json2 = mp2.toJSON(entry2);
		System.out.println(json2);
		
		assertNotSame(json, json2);
	}

	@Test
	public void testTextNodeEntry() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		MessagePackMapper mp = new MessagePackMapper(entitytempl);		// 変更前

		String json = "{\"entry\" : {\"subInfo\" : {\"hobby\" : [{\"_$$text\" : \"テキストノード\"}]}}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);

		// MessagePack test
		System.out.println("\n=== XML Entry(テキストノード) シリアライズ ===");
        String xml = mp.toXML(entry);
		System.out.println(xml);
		
		assertEquals(json, mp.toJSON(mp.fromXML(xml)));
	}
	
	@Test
	public void testStaticPackages() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
	
		String NAMESPACE_B2 = "b2=http://kuronekoyamato.co.jp/b2/1.0";

		Map<String, String> MODEL_PACKAGE = new HashMap<String, String>();
		MODEL_PACKAGE.put("jp.co.kuronekoyamato.b2web.model", NAMESPACE_B2);
	
		MessagePackMapper mp = new MessagePackMapper(MODEL_PACKAGE);		
	
		File feed1 = new File("/Users/stakezaki/git/reflexcore/src/test/resources/data/feed1.txt");
		FileReader fi = new FileReader(feed1);
	
		// XMLにシリアライズ
		FeedBase feedobj = (FeedBase) mp.fromXML(fi);

		String xml = mp.toXML(feedobj);
		System.out.println("\n=== XML Feed シリアライズ ===");
		System.out.println(xml);
		
		String json = mp.toJSON(feedobj);
		System.out.println("\n=== JSON Feed シリアライズ ===");
		System.out.println(json);
	
		System.out.println("\n=== Messagepack Feed シリアライズ ===");
        byte[] msgpack = mp.toMessagePack(feedobj);
        for(int i=0;i<msgpack.length;i++) { 
        	System.out.print(Integer.toHexString(msgpack[i]& 0xff)+" "); 
        } 
		System.out.println();
		System.out.println("\n=== Array Feed シリアライズ ===");
		System.out.println(mp.toArray(msgpack));
//		System.out.println("feed object size:"+ObjectTree.dump(feedobj));
		
		assertTrue(true);
	}




}
