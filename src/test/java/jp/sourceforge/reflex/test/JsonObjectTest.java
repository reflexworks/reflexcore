package jp.sourceforge.reflex.test;

import org.json.simple.JSONObject;
import org.junit.Test;

public class JsonObjectTest {

	@Test
	public void toJson() {
		
		System.out.println("--- test JsonObject start ---");
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("rights", "ERROR");
		jsonObject.put("title", "error message.");
		
		System.out.println(jsonObject.toJSONString());
		
		System.out.println("--- test JsonObject end ---");
	}

}
