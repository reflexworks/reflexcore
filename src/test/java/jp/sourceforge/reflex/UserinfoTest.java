package jp.sourceforge.reflex;

import org.msgpack.MessagePack;
import org.msgpack.util.json.JSON;

import jp.sourceforge.reflex.core.ResourceMapper;

import model3.Error;
import model3.Errors;
import model3.Userinfo;

public class UserinfoTest {

	public static String NEWLINE = System.getProperty("line.separator");

	public static void main(String[] args) {
		try {
			IResourceMapper rxmapper = new ResourceMapper("model3");

			String json = convertUserinfo(getJsonInfo());
			
			System.out.println("=== JSON UserInfo ===");
			System.out.println(json);
			
			Object info = rxmapper.fromJSON(json);
			
			System.out.println("=== Object UserInfo ===");
			System.out.println(info);

			String jsonError = convertUserinfo(getJsonError());
			
			System.out.println("=== JSON Error ===");
			System.out.println(jsonError);
			
			Object infoError = rxmapper.fromJSON(jsonError);
			
			System.out.println("=== Object Error ===");
			System.out.println(infoError);
			
			// MessagePack test
	        MessagePack msgpack = new MessagePack();
	        
	        /*
	        msgpack.register(Errors.class);
	        msgpack.register(Error.class);
	        msgpack.register(Userinfo.class);
	        */
	        /*
	        Class<?> cls = Errors.class;
	        msgpack.register(cls);
	        cls = Error.class;
	        msgpack.register(cls);
	        cls = Userinfo.class;
	        msgpack.register(cls);
	        */
	        Class<?> cls = Class.forName("model3.Errors");
	        msgpack.register(cls);
	        cls = Class.forName("model3.Error");
	        msgpack.register(cls);
	        cls = Class.forName("model3.Userinfo");
	        msgpack.register(cls);
	        
	        byte[] mbytes = msgpack.write(info);
	        Object muserinfo = msgpack.read(mbytes, Userinfo.class);
			
			System.out.println("=== MessagePack UserInfo ===");
			System.out.println(muserinfo);
	        
	        mbytes = msgpack.write(infoError);
	        muserinfo = msgpack.read(mbytes, Userinfo.class);
			
			System.out.println("=== MessagePack Error ===");
			System.out.println(muserinfo);
			
		
		} catch (Throwable e) {
			e.printStackTrace();
		}
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
	
}
