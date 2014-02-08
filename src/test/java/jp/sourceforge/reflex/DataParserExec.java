package jp.sourceforge.reflex;

import java.util.List;
import java.util.Iterator;

import org.json.JSONObject;
import org.json.JSONException;

import jp.sourceforge.reflex.core.XML;
import jp.sourceforge.reflex.util.DataParser;
import jp.sourceforge.reflex.util.FileUtil;

public class DataParserExec {

	public static void main(String[] args) {
		
		try {
			// Data XML
			//String dataXml = "data/helloworld_data.xml";
			//String dataXml = "data/helloworld_data.json";
			String dataXml = "data/helloworld_data.csv";
			String dataXmlFile = FileUtil.getResourceFilename(dataXml);

			DataParser dataParser = new DataParser();
			List dataList = dataParser.createObject(dataXmlFile);
			
			System.out.println("dataPerser.createObject : " + dataList);
			
			//String key = "aaa";
			String key = "bbb";
			//String key = "bbb2";
			//String key = "ccc";
			
			if (dataList != null) {
				for (int i = 0; i < dataList.size(); i++) {
					JSONObject jsonObject = (JSONObject)dataList.get(i);
					
					String val = dataParser.getValue(key, jsonObject);
					if (val != null) {
						System.out.println(key + " = " + val);
						break;
					}
				}
			}
		
			System.out.println("-- Data XML parse end --");

			String json = "{\"content\" : [{\"content\" : \"001あ\"},\"002い\",\"003う\"]}";

			JSONObject jsonobj = new JSONObject(json);

			String xml = (new XML()).toString(jsonobj);
			System.out.println(xml);

			
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
