package jp.reflexworks.atom.test;

import java.io.IOException;
import java.io.StringWriter;
import java.text.ParseException;
import java.util.zip.DataFormatException;

import org.junit.Test;

import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.sourceforge.reflex.core.XMLSerializer;
import jp.sourceforge.reflex.exception.JSONException;

/**
 * XMLSerializerテスト
 */
public class XMLSerializerTest {

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
	public void testTextNodeEntry() throws ParseException, JSONException, IOException, DataFormatException, ClassNotFoundException {
		FeedTemplateMapper mp = new FeedTemplateMapper(entitytemplp, SECRETKEY);

		String json = "{\"entry\" : {\"public\" : {\"int\" : \"予約語\"},\"subInfo\" : {\"hobby\" : [{\"______text\" : \"テキストノード\\\"\\n\"}]},\"link\" : [{\"___href\" : \"/0762678511-/allA/759188985520\",\"___rel\" : \"self\"},{\"___href\" : \"/transferring/all/0762678511-/759188985520\",\"___rel\" : \"alternate\"},{\"___href\" : \"/0762678511-/@/spool/759188985520\",\"___rel\" : \"alternate\"},{\"___href\" : \"/0762678511-/historyA/759188985520\",\"___rel\" : \"alternate\"}]}}";
		EntryBase entry = (EntryBase) mp.fromJSON(json);

		// MessagePack test
		System.out.println("\n=== XML Entry(テキストノード+Link) シリアライズ ===");
		
		XMLSerializer xmlSerializer = new XMLSerializer();
		StringWriter writer = new StringWriter();
		xmlSerializer.marshal(entry, writer);
		
		System.out.println(writer.toString());
	}

}
