package jp.sourceforge.reflex;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javassist.CannotCompileException;
import javassist.NotFoundException;
import jp.reflexworks.atom.entry.EntryBase;
import jp.sourceforge.reflex.util.ClassFinder;
import model3.Userinfo;
import model3.sub.Favorite;
import model3.sub.Hobby;
import model3.sub.SubInfo;

import org.json.JSONException;

public class DynamicClassGeneratorTest {

	public static String NEWLINE = System.getProperty("line.separator");

	public static String entitytempl[] = {
		// *がList, #がkey , %が暗号化　, * # % は末尾に一つだけ付けられる
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

	public static String entitytempl2[] = {
		// *がList, #がkey , %が暗号化　, * # % は末尾に一つだけ付けられる
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
		"  test",
		" code(int)",
		" message",
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

	private static String[] atom = {"jp.reflexworks.atom.source.Author","jp.reflexworks.atom.source.Category",
							 "jp.reflexworks.atom.source.Contributor","jp.reflexworks.atom.source.Generator",
							 "jp.reflexworks.atom.source.Link","jp.reflexworks.atom.source.Source",
							 "jp.reflexworks.atom.entry.Author","jp.reflexworks.atom.entry.Category",
							 "jp.reflexworks.atom.entry.Content","jp.reflexworks.atom.entry.Contributor",
							 "jp.reflexworks.atom.entry.Link",
							 "jp.reflexworks.atom.feed.Author","jp.reflexworks.atom.feed.Category",
							 "jp.reflexworks.atom.feed.Generator","jp.reflexworks.atom.feed.Contributor",
							 "jp.reflexworks.atom.feed.Link" };
	
	public static void main(String args[]) throws NotFoundException, CannotCompileException, JSONException, IOException, InstantiationException, IllegalAccessException, ParseException {

		
		DynamicClassGenerator dg = new DynamicClassGenerator();		
		HashSet<String> classnames = new LinkedHashSet<String>();
		classnames.addAll(new ArrayList(Arrays.asList(atom)));
		classnames.addAll(dg.generateClass("testm3", entitytempl));
		dg.registClass(classnames);
		
		Object entry = getTestEntry(dg);
		
		// MessagePack test
        byte[] mbytes = dg.toMessagePack(entry);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
		System.out.println("\n=== MessagePack UserInfo ===");
        Object muserinfo = dg.fromMessagePack(mbytes);
		System.out.println(muserinfo);

		dg = new DynamicClassGenerator();		
		classnames = new LinkedHashSet<String>();
		classnames.addAll(new ArrayList(Arrays.asList(atom)));
		classnames.addAll(dg.generateClass("testm3", entitytempl2));
		dg.registClass(classnames);

        muserinfo = dg.fromMessagePack(mbytes);
		
		System.out.println("\n=== MessagePack UserInfo ===");
		System.out.println(muserinfo);
        

	}
	

	public static String getJsonInfo() {
		StringBuilder buf = new StringBuilder();
		buf.append("{");
		buf.append(NEWLINE);
		buf.append(" \"id\": \"113457373253613477905\",");
		buf.append(NEWLINE);
		buf.append(" \"email\": \"xuser@scr01.pdc.jp\",");
		buf.append(NEWLINE);
		//buf.append(" \"verified_email\": true,");
		buf.append(" \"verified_email\": false,");
		buf.append(NEWLINE);
		buf.append(" \"name\": \"X 管理者\",");
		buf.append(NEWLINE);
		buf.append(" \"given_name\": \"X\",");
		buf.append(NEWLINE);
		buf.append(" \"family_name\": \"管理者\"");
		buf.append(NEWLINE);
		buf.append("}");
		return buf.toString();
	}
	
	public static String getJsonError() {
		StringBuilder buf = new StringBuilder();
		buf.append("{");
		buf.append(NEWLINE);
		buf.append(" \"error\": {");
		buf.append(NEWLINE);
		buf.append("  \"errors\": [");
		buf.append(NEWLINE);
		buf.append("   {");
		buf.append(NEWLINE);
		buf.append("    \"domain\": \"com.google.auth\",");
		buf.append(NEWLINE);
		buf.append("    \"reason\": \"invalidAuthentication\",");
		buf.append(NEWLINE);
		buf.append("    \"message\": \"invalid header\",");
		buf.append(NEWLINE);
		buf.append("    \"locationType\": \"header\",");
		buf.append(NEWLINE);
		buf.append("    \"location\": \"Authorization\"");
		buf.append(NEWLINE);
		buf.append("   }");
		buf.append(NEWLINE);
		buf.append("  ],");
		buf.append(NEWLINE);
		buf.append("  \"code\": 401,");
		buf.append(NEWLINE);
		buf.append("  \"message\": \"invalid header\"");
		buf.append(NEWLINE);
		buf.append(" }");
		buf.append(NEWLINE);
		buf.append("}");
		return buf.toString();
	}
	
	public static String convertUserinfo(String json) {
		StringBuilder buf = new StringBuilder();
		buf.append("{ \"userinfo\" : ");
		buf.append(json);
		buf.append("}");
		return buf.toString();
	}
	
	public static Object getTestEntry(DynamicClassGenerator dg)  {
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

		f = cc4.getField("food");
		f.set(favorite, "カレー");		
		f = cc4.getField("music");
		f.set(favorite, "ポップス");		

		f = cc3.getField("favorite");
		f.set(subInfo, favorite);		
		
		f = cc.getField("subInfo");
		f.set(entry, subInfo);
		
		Field[] flds = cc3.getFields();
		for (Field fld:flds) {
			System.out.println("flds:"+fld.getName());
		}
		
		
		return entry;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static void editUserInfo(Userinfo userInfo) {
		SubInfo subInfo = new SubInfo();
		Favorite favorite = new Favorite();
		favorite.food = "カレー";
		favorite.music = "ポップス";
		subInfo.favorite = favorite;

		List<Hobby> hobbies = new ArrayList<Hobby>();
		Hobby hobby = new Hobby();
		hobby._$$text = "ハイキング";
		hobbies.add(hobby);
		hobby = new Hobby();
		hobby._$$text = "映画鑑賞";
		hobbies.add(hobby);
		subInfo.hobby = hobbies;
		
		userInfo.setSubInfo(subInfo);
	}


}
