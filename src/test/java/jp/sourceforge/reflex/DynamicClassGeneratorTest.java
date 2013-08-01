package jp.sourceforge.reflex;

import java.io.IOException;
import java.lang.reflect.Field;
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

	public static String entitysrc[] = {
		// *がList, #がkey , %が暗号化　, * # % は末尾に一つだけ付けられる
		"id",
		"email",
		"verified_email(Boolean)",
		"name(Integer)",
		"given_name(Double)",
		"family_name",
		"error",
		" errors*",
		"  domain(Float)",
		"  reason(Long)",
		"  message",
		"  locationType",
		"  location",
		"subInfo",
		" favorite",
		"  food",
		"  music",
		" hobby*",
		"  _$$text"
	};
	
	private static String[] atom = {"jp.reflexworks.atom.source.Author","jp.reflexworks.atom.source.Category",
							 "jp.reflexworks.atom.source.Contributor","jp.reflexworks.atom.source.Generator",
							 "jp.reflexworks.atom.source.Link","jp.reflexworks.atom.source.Source" };
	
	public static void main(String args[]) throws NotFoundException, CannotCompileException, JSONException, IOException, InstantiationException, IllegalAccessException {

		
		ClassPool pool = ClassPool.getDefault();
//        pool.appendSystemPath();
		
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
		
//		TreeSet treeSet = new TreeSet<String>(Collections.reverseOrder());
//		treeSet.addAll(dg.generateClass("testsvc", entitysrc));

		classnames.addAll(dg.generateClass("testm3", entitysrc));
		dg.registClass(classnames);
		
//		IResourceMapper rxmapper2 = new MessagePackMapper(classnames);
		IResourceMapper rxmapper = new ResourceMapper("model3");
		
		EntryBase entry = getTestEntry(pool);
		
		
		String json = convertUserinfo(getJsonInfo());
		
		System.out.println("=== JSON UserInfo ===");
		System.out.println(json);

		Object info = rxmapper.fromJSON(json);

		System.out.println("=== Object UserInfo ===");
		System.out.println(info);
		
		editUserInfo((Userinfo)info);
		
		System.out.println("=== edit UserInfo ===");
		System.out.println(info);

		String jsonError = convertUserinfo(getJsonError());
		
		System.out.println("=== JSON Error ===");
		System.out.println(jsonError);
		
		Object infoError = rxmapper.fromJSON(jsonError);
		
		System.out.println("=== Object Error ===");
		System.out.println(infoError);


		// MessagePack test
        byte[] mbytes = dg.toMessagePack(info);
        for(int i=0;i<mbytes.length;i++) { 
        	System.out.print(Integer.toHexString(mbytes[i]& 0xff)+" "); 
        } 
        Object muserinfo = dg.fromMessagePack(mbytes);
		
		System.out.println("=== MessagePack UserInfo ===");
		System.out.println(muserinfo);
        
        mbytes = dg.toMessagePack(infoError);
        muserinfo = dg.fromMessagePack(mbytes);
		
		System.out.println("=== MessagePack Error ===");
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
		Field[] flds = cc.getFields();
		for (Field fld:flds) {
			System.out.println("flds:"+fld.getName());
		}
		Field id = cc.getField("id");
		
		id.set(entry, "1");
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
