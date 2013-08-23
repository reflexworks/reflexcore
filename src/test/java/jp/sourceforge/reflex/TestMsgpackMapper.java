package jp.sourceforge.reflex;

import static org.junit.Assert.*;

import java.io.IOException;
import java.text.ParseException;
import java.util.zip.DataFormatException;

import jp.reflexworks.atom.entry.ValidatorBase;
import jp.sourceforge.reflex.core.MessagePackMapper;

import org.json.JSONException;
import org.junit.Test;

public class TestMsgpackMapper {

	public static String entitytempl[] = {
		// {}がMap, #がIndex , %が暗号化, []がArray　, {} # % [] は末尾に一つだけ付けられる。*が必須項目
		"testm3{2}",        //  0行目はパッケージ名(service名)
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

	@Test
	public void testJSONEntry() throws ParseException, JSONException {
		MessagePackMapper dg = new MessagePackMapper(entitytempl);		
		
		System.out.println("JSON Entry デシリアライズ");
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		Object entry = dg.fromJSON(json);
		
		System.out.println("\n=== JSON Entry シリアライズ ===");
        String json2 = dg.toJSON(entry);
		System.out.println(json2);

		assertEquals(json, json2);
	}

	@Test
	public void testXMLEntry() throws ParseException, JSONException {
		MessagePackMapper dg = new MessagePackMapper(entitytempl);		
		
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		Object entry = dg.fromJSON(json);
		
		System.out.println("\n=== XML Entry シリアライズ ===");
        String xml = dg.toXML(entry);
		System.out.println(xml);

		System.out.println("\n=== XML Entry デシリアライズ ===");
        Object entry2 = dg.fromXML(xml);

		assertEquals(json, dg.toJSON(entry2));
	}

	@Test
	public void testMsgPackEntry() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		MessagePackMapper dg = new MessagePackMapper(entitytempl);		
		
		String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
		Object entry = dg.fromJSON(json);
		
		// MessagePack test
		System.out.println("\n=== MessagePack Entry シリアライズ ===");
        byte[] mbytes = dg.toMessagePack(entry);
		System.out.println("len:"+mbytes.length);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
		System.out.println("\n=== MessagePack Entry deflate圧縮 ===");
        byte[] de = dg.deflate(mbytes);
		System.out.println("len:"+de.length+" 圧縮率："+(de.length*100/mbytes.length)+"%");
        for(int i=0;i<de.length;i++) { 
        	System.out.print(Integer.toHexString(de[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== MessagePack Entry infrate解凍 ===");
        byte[] in = dg.inflate(de);
		System.out.println("len:"+in.length);
        for(int i=0;i<in.length;i++) { 
        	System.out.print(Integer.toHexString(in[i]& 0xff)+" "); 
        } 

        System.out.println("\n=== MessagePack Entry デシリアライズ ===");

        ValidatorBase  muserinfo = (ValidatorBase) dg.fromMessagePack(in,false);	// false でEntryをデシリアライズ
//        System.out.println("Validtion:"+muserinfo.validate());         Entryからvalidate() は呼べない(Feedからのみ）

		assertEquals(json, dg.toJSON(muserinfo));
	}

}
