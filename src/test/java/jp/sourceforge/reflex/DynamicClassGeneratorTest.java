package jp.sourceforge.reflex;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.NotFoundException;
import jp.reflexworks.atom.entry.EntryBase;
import jp.sourceforge.reflex.core.MessagePackMapper;
import jp.sourceforge.reflex.core.ResourceMapper;
import jp.sourceforge.reflex.util.ClassFinder;
import model3.Userinfo;
import model3.sub.Favorite;
import model3.sub.Hobby;
import model3.sub.SubInfo;

import org.json.JSONException;
import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;
import org.msgpack.template.builder.TemplateBuilder;

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
		" favorite3",
		"  food",
		" hobby*",
		"  _$$text"
	};
	
	private static String[] atom = {"jp.reflexworks.atom.source.Author","jp.reflexworks.atom.source.Category",
							 "jp.reflexworks.atom.source.Contributor","jp.reflexworks.atom.source.Generator",
							 "jp.reflexworks.atom.source.Link","jp.reflexworks.atom.source.Source" };
	
	public static void main(String args[]) throws NotFoundException, CannotCompileException, JSONException, IOException, InstantiationException, IllegalAccessException, ParseException {

		
		ClassPool pool = ClassPool.getDefault();
		
		DynamicClassGenerator dg = new DynamicClassGenerator(pool);		
		HashSet<String> classnames = new LinkedHashSet<String>();
		ClassFinder classFinder = new ClassFinder();
//		Set<String> atom = classFinder.getClassNamesFromPackage("jp.reflexworks.atom.source");
//		classnames.addAll(atom);
		classnames.addAll(new ArrayList(Arrays.asList(atom)));
		Set<String> atom = classFinder.getClassNamesFromPackage("jp.reflexworks.atom.entry");
		classnames.addAll(atom);
		atom = classFinder.getClassNamesFromPackage("jp.reflexworks.atom.feed");
		classnames.addAll(atom);

		classnames.addAll(dg.generateClass("testm3", entitytempl));
		dg.registClass(classnames);
		
		
		EntryBase entry = getTestEntry(pool);
		
		// MessagePack test
        byte[] mbytes = dg.toMessagePack(entry);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
        Object muserinfo = dg.fromMessagePack(mbytes);
		
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
	
	public static EntryBase getTestEntry(ClassPool pool)  {
		try {
			
//		CtClass cc = pool.get("testm3.Error");
		Class cc = Class.forName("testm3.Entry");
		EntryBase entry = (EntryBase) cc.newInstance();
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
		
		Class cc2 = Class.forName("testm3.Error");
		Field[] flds = cc2.getFields();
		for (Field fld:flds) {
			System.out.println("flds:"+fld.getName());
		}
		Object error = cc2.newInstance();
		f = cc2.getField("code");
		f.set(error, 100);		
		f = cc2.getField("message");
		f.set(error, "Syntax Error");
		f = cc2.getField("message");
		f.set(error, "Syntax Error");
		
		
		
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
