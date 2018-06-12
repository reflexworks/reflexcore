package jp.sourceforge.reflex;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.LTSVUtil;

/**
 * Unit test for simple App.
 */
public class LTSVUtilTest {

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		String time = DateUtil.getDateTimeFormat(new Date(), "[yyyy-MM-dd HH:mm:ss]");

		//Map<String, String> map = null;
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("time", time);
		map.put("abc", "123");
		map.put("sample_time", time);
		map.put("def", "456");
		
		System.out.println(LTSVUtil.serialize(map));


	}
}
