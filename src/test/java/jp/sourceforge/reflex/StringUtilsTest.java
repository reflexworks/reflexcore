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
import jp.sourceforge.reflex.util.StringUtils;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class StringUtilsTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public StringUtilsTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(StringUtilsTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		String str = "   あいう  えお   ";
		String str2 = "　　　あいう　　えお　　　";
		String str3 = "  \"あいうえお\"  ";

		try {
			System.out.println("trimUni(str)  : '" + StringUtils.trimUni(str) + "'");
			System.out.println("trimUni(str2) : '" + StringUtils.trimUni(str2) + "'");
			System.out.println("trimUni(str3) : '" + StringUtils.trimUni(str3) + "'");

			System.out.println("trimDoubleQuotes(str3) : '" + StringUtils.trimDoubleQuotes(str3) + "'");

			long lnum = 8988888888888888889L;
			System.out.println("String.valueOf : " + String.valueOf(lnum));
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
