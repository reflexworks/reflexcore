package jp.sourceforge.reflex;

import jp.sourceforge.reflex.core.XML;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONXMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		// String json =
		// "{\"content\" : [{\"content\" : \"001あ\"},{\"content\" : \"002い\"},{\"content\" : \"003う\"}]}"
		// ;

		String json = "{\"content\" : [{\"content\" : \"001あ\"},\"002い\",\"003う\"]}";

		try {
			JSONObject jsonobj = new JSONObject(json);

			String xml = (new XML()).toString(jsonobj);
			System.out.println(xml);

		} catch (JSONException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

	}

}
