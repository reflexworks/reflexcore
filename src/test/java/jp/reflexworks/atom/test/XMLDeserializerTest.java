package jp.reflexworks.atom.test;

import java.io.StringReader;
import java.text.ParseException;

import org.junit.Test;

import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.reflexworks.atom.mapper.XMLDeserializer;
import jp.sourceforge.reflex.exception.XMLException;

/**
 * XMLDeserializerテスト
 */
public class XMLDeserializerTest {

	public static String entitytempl[] = {
		// {}がMap, []がArray　, {} [] は末尾にどれか一つだけが付けられる。また、!を付けると必須項目となる
		"default{2}",        //  0行目はパッケージ名(service名)
		"Idx 	",
		"email(string){5~30}",	// 5文字~30文字の範囲
		"verified_email(Boolean)",// Boolean型 他に（int,date,long,float,doubleがある。先小文字OK、省略時はString）
		"name",
		"given_name",
		"family_name",
		"error",
		" errors{}",				// 多重度(n)、*がないと多重度(1)、繰り返し最大{1}
		"  domain",
		"  reason",
		"  message",
		"  locationType",
		"  location",
		" code(int){1~100}",			// 1~100の範囲
		" message",
		"subInfo",
		" favorite",
		"  food!=^.{3}$",	// 必須項目、正規表現つき
		"  music=^.{5}$",			// 配列(要素数max3)
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		"  updated(date)",
		" hobby{}",
		"  $$text",				// テキストノード
		"seq(desc)"
	};

	public static String entitytemplp[] = {
		// {}がMap, []がArray　, {} [] は末尾にどれか一つだけが付けられる。また、!を付けると必須項目となる
		"default{2}",        //  0行目はパッケージ名(service名)
		"Idx",
		"public",
		" int",
		"verified_email(Boolean)",// Boolean型 他に（int,date,long,float,doubleがある。先小文字OK、省略時はString）
		"name",
		"given_name",
		"family_name",
		"error",
		" errors{}",				// 多重度(n)、*がないと多重度(1)、繰り返し最大{1}
		"  domain",
		"  reason",
		"  message",
		"  locationType",
		"  location",
		" code(int){1~100}",			// 1~100の範囲
		" message",
		"subInfo",
		" favorite",
		"  food!=^.{3}$",	// 必須項目、正規表現つき
		"  music=^.{5}$",			// 配列(要素数max3)
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		"  updated(date)",
		" hobby{}",
		"  $$text"				// テキストノード
	};

	private static String SECRETKEY = "testsecret123";

	@Test
	public void testFromXmlToJson() throws XMLException, ParseException {
		FeedTemplateMapper mp = new FeedTemplateMapper(entitytemplp, SECRETKEY);
		
		String xml = getXml();
		System.out.println("---- [testFromXmlToJson] XML ----");
		System.out.println(xml);
		
		StringReader reader = new StringReader(xml);
		
		XMLDeserializer xmlDesializer = new XMLDeserializer();
		String json = xmlDesializer.fromXmlToJson(reader, mp);

		System.out.println("---- [testFromXmlToJson] JSON ----");
		System.out.println(json);
	}

	@Test
	public void testFromXmlToJson_returncode() throws XMLException, ParseException {
		FeedTemplateMapper mp = new FeedTemplateMapper(entitytemplp, SECRETKEY);
		
		String xml = getXml_returncode();
		System.out.println("---- [testFromXmlToJson_returncode] XML ----");
		System.out.println(xml);
		
		StringReader reader = new StringReader(xml);
		
		XMLDeserializer xmlDesializer = new XMLDeserializer();
		String json = xmlDesializer.fromXmlToJson(reader, mp);

		System.out.println("---- [testFromXmlToJson_returncode] JSON ----");
		System.out.println(json);
	}

	@Test
	public void testFromXml() throws XMLException, ParseException {
		FeedTemplateMapper mp = new FeedTemplateMapper(entitytemplp, SECRETKEY);
		
		String xml = getXml();
		System.out.println("---- [testFromXml] XML ----");
		System.out.println(xml);
		
		StringReader reader = new StringReader(xml);
		
		XMLDeserializer xmlDesializer = new XMLDeserializer();
		Object obj = xmlDesializer.deserialize(reader, mp);
		String json = mp.toJSON(obj);
		
		System.out.println("---- [testFromXml] JSON ----");
		System.out.println(json);
	}

	private String getXml() {
		StringBuilder sb = new StringBuilder();
		sb.append("<feed>");
		sb.append("<entry>");
		sb.append("<contributor><uri>urn:vte.cx:acl:/_group/$admin,CRUD</uri></contributor>");
		sb.append("<contributor><uri>urn:vte.cx:acl:+,R</uri></contributor>");
		sb.append("<content type=\"text/html\">&lt;p&gt;virtual technology (test)&lt;/p&gt;</content>");
		sb.append("<title>HTML</title>");
		sb.append("<link href=\"/@/test.html\" rel=\"self\"/>");
		sb.append("<link href=\"/_html/@/test.html\" rel=\"alternate\"/>");
		sb.append("</entry>");
		sb.append("<entry>");
		sb.append("<link href=\"/@/test2.html\" rel=\"self\"/>");
		sb.append("<link href=\"/_html/@/test2.html\" rel=\"alternate\"/>");
		sb.append("<contributor><uri>urn:vte.cx:acl:/_group/$admin,CRUD</uri></contributor>");
		sb.append("<contributor><uri>urn:vte.cx:acl:+,R</uri></contributor>");
		sb.append("<content type=\"text/html\">&lt;p&gt;virtual technology (test2)&lt;/p&gt;</content>");
		sb.append("<title>HTML2</title>");
		sb.append("</entry>");
		sb.append("</feed>");
		return sb.toString();
	}

	@Test
	public void testFromXml2() throws XMLException, ParseException {
		FeedTemplateMapper mp = new FeedTemplateMapper(entitytempl, SECRETKEY);
		
		String xml = getXml2();
		System.out.println("---- [testFromXml2] XML ----");
		System.out.println(xml);
		
		StringReader reader = new StringReader(xml);
		
		XMLDeserializer xmlDesializer = new XMLDeserializer();
		Object obj = xmlDesializer.deserialize(reader, mp);
		String json = mp.toJSON(obj);
		
		System.out.println("---- [testFromXml2] JSON ----");
		System.out.println(json);
	}

	private String getXml2() {
		return "<entry><email>email1</email><name>管理者</name><family_name>管理者Y</family_name><subInfo><favorite><food>カレー</food><music>ポップス1</music></favorite></subInfo></entry>";
	}

	private String getXml_returncode() {
		return "<entry><title type=\"null\">return\ncode</title></entry>";
	}

}
