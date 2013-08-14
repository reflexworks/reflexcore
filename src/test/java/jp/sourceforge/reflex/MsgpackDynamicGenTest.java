package jp.sourceforge.reflex;


import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.Loader;
import javassist.NotFoundException;
import jp.reflexworks.atom.entry.Element;
import jp.reflexworks.atom.entry.EntryBase;
import jp.sourceforge.reflex.core.MessagePackMapper;

import org.json.JSONException;
import org.msgpack.MessagePack;
import org.msgpack.template.ListTemplate;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;

public class MsgpackDynamicGenTest {

	public static String NEWLINE = System.getProperty("line.separator");

	public static String entitytempl[] = {
		// *がList, #がIndex , %が暗号化, []が配列　, * # % [] は末尾に一つだけ付けられる。@が必須項目
		"testm3",        //  0行目はパッケージ名(service名)
		"id#",			  // Index
		"email",
		"verified_email(Boolean)",// Boolean型 他に（int,date,long,float,double,booleanがある。先小文字OK、省略時はString）
		"name",
		"given_name",
		"family_name",
		"error",
		" errors*",				// 多重度(n)、*がないと多重度(1)
		"  domain",
		"  reason",
		"  message",
		"  locationType",
		"  location",
		" code(int)",			
		" message",
		"subInfo",
		" favorite",
		"  food%abc@:^.{3}$",	// %abdで暗号化、必須項目、正規表現つき
		"  music[]",			// 配列
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		" hobby*",
		"  _$$text"				// テキストノード
	};

	public static String entitytempl2[] = {
		"testm3",        //  0行目はパッケージ名(service名)
		"id",			 // atom classと重複するがエラーにはならない
		"email",
		"verified_email(Boolean)",
		"name",
		"given_name",
		"family_name",
		"error",
		" errors*",
		"  domain",
		"  reason",
		"  message",
		"  locationType",
		"  location",
		" code(int)",
		" message",
		" test",
		"subInfo",
		" favorite",
		"  food",
		"  music[]",
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		" hobby*",
		"  _$$text"
	};

//	private static String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : {\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";
	private static String json = "{\"entry\" : {\"email\" : \"email1\",\"verified_email\" : false,\"name\" : \"管理者\",\"given_name\" : \"X\",\"family_name\" : \"管理者Y\",\"error\" : { \"errors\" : [{\"domain\": \"com.google.auth\",\"reason\": \"invalidAuthentication\",\"message\": \"invalid header\",\"locationType\": \"header\",\"location\": \"Authorization\"}],\"code\" : 100,\"message\" : \"Syntax Error\"},\"subInfo\" : {\"favorite\" : {\"food\" : \"カレー\",\"music\" : [\"ポップス1\",\"ポップス2\",\"ポップス3\"]}}}}";

	public static void main(String args[]) throws NotFoundException, CannotCompileException, JSONException, IOException, InstantiationException, IllegalAccessException, ParseException, ClassNotFoundException, DataFormatException, SecurityException, NoSuchFieldException {

		
		MessagePackMapper dg = new MessagePackMapper(entitytempl);		
		
		//Object entry = getTestEntry(dg);
		
		System.out.println("JSON デシリアライズ実行");
		Object entry = dg.fromJSON(json);
		System.out.println("\n=== JSON UserInfo(それをシリアライズ) ===");
        String json2 = dg.toJSON(entry);
		System.out.println(json2);
		
		System.out.println("\n=== XML UserInfo ===");
        String xml = dg.toXML(entry);
		System.out.println(xml);
		
		System.out.println("\n=== XML UserInfo(デシリアライズ) ===");
		Object entryx = dg.fromXML(xml);
        String xml2 = dg.toXML(entryx);
		System.out.println(xml2);
		
		// MessagePack test
		System.out.println("\n=== MessagePack UserInfo(シリアライズ) ===");
        byte[] mbytes = dg.toMessagePack(entry);
		System.out.println("len:"+mbytes.length);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
		System.out.println("\n=== MessagePack UserInfo(deflate圧縮) ===");
        byte[] de = dg.deflate(mbytes);
		System.out.println("len:"+de.length+" 圧縮率："+(de.length*100/mbytes.length)+"%");
        for(int i=0;i<de.length;i++) { 
        	System.out.print(Integer.toHexString(de[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== MessagePack UserInfo(raw2に戻す) ===");
        byte[] in = dg.inflate(de);
		System.out.println("len:"+in.length);
        for(int i=0;i<in.length;i++) { 
        	System.out.print(Integer.toHexString(in[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== fromMessagePack UserInfo(デシリアライズ) ===");

        EntryBase muserinfo = (EntryBase) dg.fromMessagePack(in);
        System.out.println("Validtion:"+muserinfo.isValid());
        
        // 項目名を省略した配列形式でもシリアライズ/デシリアライズ可能 (null は省略できない）
		System.out.println("\n=== Array UserInfo ===");
        String array = dg.toArray(in).toString();
		System.out.println(array);
		Object entity3 = dg.fromArray(array);
        String json3 = dg.toJSON(entity3);
		System.out.println(json3);


		System.out.println("\n=== Errorクラスの子要素の項目(Error.test)を追加して値をセット ===");
		List<String> entitytempllist = Arrays.asList(entitytempl2);
		MessagePackMapper dg2 = new MessagePackMapper(entitytempllist);		

		EntryBase muserinfo2 = (EntryBase) dg2.fromMessagePack(mbytes);
        editTestEntry(dg2,muserinfo2);
		
        byte[] mbytes2 = dg2.toMessagePack(muserinfo2);
        for(int i=0;i<mbytes2.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes2[i]& 0xff)+" "); 
        } 
		System.out.println();
		System.out.println(dg2.toArray(mbytes2));
        

		System.out.println("\n=== XML UserInfo(クラス変更後) ===");
        xml = dg2.toXML(muserinfo2);
		System.out.println(xml);

		System.out.println("\n=== JSON UserInfo(クラス変更後) ===");
        json = dg2.toJSON(muserinfo2);
		System.out.println(json);

	}


	public static Object getTestEntry(MessagePackMapper dg)  {
		try {
			
//		Class cc = Class.forName("testm3.Entry");
		Class cc = dg.getClass("testm3.Entry");
		Object entry = (Object) cc.newInstance();
/*		Field[] flds = cc.getFields();
		for (Field fld:flds) {
			System.out.println("flds:"+fld.getName());
		}*/
		Field f = cc.getField("email");
		f.set(entry, "email1");
		f = cc.getField("verified_email");
		f.set(entry, false);
		f = cc.getField("name");
		f.set(entry, "管理者");
		f = cc.getField("given_name");
		f.set(entry, "X");
		f = cc.getField("family_name");
		f.set(entry, "管理者Y");
		
		Class cc2 = dg.getClass("testm3.Error");
		Object error = cc2.newInstance();
		f = cc2.getField("code");
		f.set(error, 100);		
		f = cc2.getField("message");
		f.set(error, "Syntax Error");
		
		f = cc.getField("error");
		f.set(entry, error);
		
		Class cc3 = dg.getClass("testm3.SubInfo");
		Object subInfo = cc3.newInstance();
		
		Class cc4 = dg.getClass("testm3.Favorite");
		Object favorite = cc4.newInstance();
/*
		ArrayList list = new ArrayList<String>();
		list.add("カレー1");
		list.add("カレー2");
		list.add("カレー3");
		list.add("カレー4");
*/		

		f = cc4.getField("food");
//		f.set(favorite,list );		
		f.set(favorite,"カレー" );
		Method m = cc4.getMethod("_Food",null);
		System.out.println("暗号キー(_Food)="+m.invoke(favorite,null));

		List<Element> lines2 = new ArrayList<Element>();
		Element a1 = new Element();
		a1._$$text = "ポップス1";
		lines2.add(a1);
		a1 = new Element();
		a1._$$text = "ポップス2";
		lines2.add(a1);
		a1 = new Element();
		a1._$$text = "ポップス3";
		lines2.add(a1);

		f = cc4.getDeclaredField("music");
		f.set(favorite, lines2);		
//		System.out.println("Generic Type="+f.getGenericType());

		f = cc3.getField("favorite");
		f.set(subInfo, favorite);		
		
		f = cc.getField("subInfo");
		f.set(entry, subInfo);
		
		
		/*
		Field[] flds = cc3.getFields();
		for (Field fld:flds) {
			System.out.println("flds:"+fld.getName());
		}
		*/
		
		return entry;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void editTestEntry(MessagePackMapper dg,Object entry)  {
		try {
/*
			Field[] flds = entry.getClass().getFields();
			for (Field fld:flds) {
				System.out.println("flds:"+fld.getName());
			}

*/
		Field f = entry.getClass().getField("error");
		Object error = f.get(entry);	
		
		f = error.getClass().getField("test");
		f.set(error, "<この項目が追加された>");		
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
