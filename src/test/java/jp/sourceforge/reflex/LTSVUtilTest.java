package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import jp.sourceforge.reflex.util.ConsistentHash;
import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.HashFunction;
import jp.sourceforge.reflex.util.LTSVUtil;
import jp.sourceforge.reflex.util.MD5;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class LTSVUtilTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public LTSVUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(LTSVUtilTest.class);
	}

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
