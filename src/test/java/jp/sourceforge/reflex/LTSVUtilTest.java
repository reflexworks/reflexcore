package jp.sourceforge.reflex;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Date;

import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.LTSVUtil;

public class LTSVUtilTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		try {
			
			String time = DateUtil.getDateTimeFormat(new Date(), "[yyyy-MM-dd HH:mm:ss]");

			//Map<String, String> map = null;
			Map<String, String> map = new LinkedHashMap<String, String>();
			map.put("time", time);
			map.put("abc", "123");
			map.put("sample_time", time);
			map.put("def", "456");
			
			System.out.println(LTSVUtil.serialize(map));
			
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}

}
