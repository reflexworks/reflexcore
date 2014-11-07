package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.List;

import model3.Userinfo;
import model3.sub.Favorite;
import model3.sub.Hobby;
import model3.sub.SubInfo;

import jp.sourceforge.reflex.core.MessagePackMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class MessagePackMapperTest extends TestCase {
	
	public static String NEWLINE = System.getProperty("line.separator");

	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public MessagePackMapperTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(MessagePackMapperTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {
		try {
			IResourceMapper rxmapper = new MessagePackMapper("model3");

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
	        byte[] mbytes = rxmapper.toMessagePack(info);
	        Object muserinfo = rxmapper.fromMessagePack(mbytes);
			
			System.out.println("=== MessagePack UserInfo ===");
			System.out.println(muserinfo);
	        
	        mbytes = rxmapper.toMessagePack(infoError);
	        muserinfo = rxmapper.fromMessagePack(mbytes);
			
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

	public static void editUserInfo(Userinfo userInfo) {
		SubInfo subInfo = new SubInfo();
		Favorite favorite = new Favorite();
		favorite._food = "カレー";
		favorite._music = "ポップス";
		subInfo._favorite = favorite;

		List<Hobby> hobbies = new ArrayList<Hobby>();
		Hobby hobby = new Hobby();
		hobby._$$text = "ハイキング";
		hobbies.add(hobby);
		hobby = new Hobby();
		hobby._$$text = "映画鑑賞";
		hobbies.add(hobby);
		subInfo._hobby = hobbies;
		
		userInfo.setSubInfo(subInfo);
	}

}
