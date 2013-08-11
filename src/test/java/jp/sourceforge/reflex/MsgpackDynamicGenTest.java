package jp.sourceforge.reflex;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DataFormatException;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import jp.reflexworks.atom.entry.EntryBase;
import jp.sourceforge.reflex.core.MessagePackMapper;

import org.json.JSONException;

public class MsgpackDynamicGenTest {

	public static String NEWLINE = System.getProperty("line.separator");

	public static String entitytempl[] = {
		// *がList, #がkey , %が暗号化　, * # % は末尾に一つだけ付けられる。@必須
		"testm3",        //  0行目はパッケージ名(service名)
		"id",			  //  1行名以降が項目名
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
		"subInfo",
		" favorite",
		"  food",
//		"  food:^.{8}$",
		"  music@",
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		" hobby*",
		"  _$$text"
	};

	public static String entitytempl2[] = {
		// *がList, #がkey , %が暗号化　, * # % は末尾に一つだけ付けられる
		"testm3",        //  0行目はパッケージ名(service名)
		"id",
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
		"  music",
		" favorite2",
		"  food",
		"   food1",
		" favorite3",
		"  food",
		" hobby*",
		"  _$$text"
	};

	
	public static void main(String args[]) throws NotFoundException, CannotCompileException, JSONException, IOException, InstantiationException, IllegalAccessException, ParseException, ClassNotFoundException, DataFormatException {

		
		MessagePackMapper dg = new MessagePackMapper(entitytempl);		
		
		Object entry = getTestEntry(dg);

		System.out.println("\n=== JSON UserInfo ===");
        String json = dg.toJSON(entry);
		System.out.println(json);

		System.out.println("\n=== XML UserInfo ===");
        String xml = dg.toXML(entry);
		System.out.println(xml);

		// MessagePack test
		System.out.println("\n=== MessagePack UserInfo(raw) ===");
        byte[] mbytes = dg.toMessagePack(entry);
		System.out.println("len:"+mbytes.length);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
		System.out.println("\n=== MessagePack UserInfo(def) ===");
        byte[] de = dg.deflate(mbytes);
		System.out.println("len:"+de.length+" 圧縮率："+(de.length*100/mbytes.length)+"%");
        for(int i=0;i<de.length;i++) { 
        	System.out.print(Integer.toHexString(de[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== MessagePack UserInfo(raw2) ===");
        byte[] in = dg.inflate(de);
		System.out.println("len:"+in.length);
        for(int i=0;i<in.length;i++) { 
        	System.out.print(Integer.toHexString(in[i]& 0xff)+" "); 
        } 

		System.out.println("\n=== MessagePack UserInfo ===");

        EntryBase muserinfo = (EntryBase) dg.fromMessagePack(in);
        System.out.println("Validtion:"+muserinfo.isValid());
        
		System.out.println(dg.ArrayfromMessagePack(in));

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
		System.out.println(dg2.ArrayfromMessagePack(mbytes2));
        

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
		f = cc4.getField("music");
		f.set(favorite, "ポップス");		

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
