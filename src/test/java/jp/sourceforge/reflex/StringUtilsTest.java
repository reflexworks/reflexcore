package jp.sourceforge.reflex;

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
			
			String alphanumericStr = "ABC123";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = "ABC.123";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = "あいう";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = "";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = null;
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = "z";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = "ABC-123";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			alphanumericStr = ".";
			System.out.println("[isAlphernumeric test] " + alphanumericStr + " : " + StringUtils.isAlphanumeric(alphanumericStr));
			
			String numStr = "12345";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "-12345";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = null;
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "ABC";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "-ABC";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "あいう";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "-あいう";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "9999999999";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));
			numStr = "-9999999999";
			System.out.println("[intValue test] " + numStr + " : " + StringUtils.intValue(numStr));

			System.out.println("[intValue test] Integer.MAX_VALUE : " + Integer.MAX_VALUE);
			System.out.println("[intValue test] Integer.MIN_VALUE : " + Integer.MIN_VALUE);

			numStr = "12345";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "-12345";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = null;
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "ABC";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "-ABC";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "あいう";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "-あいう";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "99999999999999999999";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));
			numStr = "-99999999999999999999";
			System.out.println("[longValue test] " + numStr + " : " + StringUtils.longValue(numStr));

			System.out.println("[longValue test] Long.MAX_VALUE : " + Long.MAX_VALUE);
			System.out.println("[longValue test] Long.MIN_VALUE : " + Long.MIN_VALUE);

			numStr = "12345";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "-12345";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "3333.555";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "-3333.555";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = null;
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "ABC.DDD";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "-ABC.DDD";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "あいう";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "-あいう";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "99999999999999999999999.99999999999999999999999999";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));
			numStr = "-99999999999999999999999.99999999999999999999999999";
			System.out.println("[floatValue test] " + numStr + " : " + StringUtils.floatValue(numStr));

			System.out.println("[floatValue test] Float.MAX_VALUE : " + Float.MAX_VALUE);
			System.out.println("[floatValue test] Float.MIN_VALUE : " + Float.MIN_VALUE);

			numStr = "12345";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "-12345";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "3333.555";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "-3333.555";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = null;
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "ABC.DDD";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "-ABC.DDD";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "あいう";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "-あいう";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "99999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			numStr = "-99999999999999999999999.99999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999999";
			System.out.println("[doubleValue test] " + numStr + " : " + StringUtils.doubleValue(numStr));
			
			System.out.println("[doubleValue test] Double.MAX_VALUE : " + Double.MAX_VALUE);
			System.out.println("[doubleValue test] Double.MIN_VALUE : " + Double.MIN_VALUE);
			
			int i = 1;
			int len = 5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = 11111;
			len = 5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = 111111;
			len = 5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = -1;
			len = 5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = 1;
			len = -5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = 0;
			len = 5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			i = 0;
			len = -5;
			System.out.println("[zeroPadding test] i=" + i + ", len=" + len + ", result=" + StringUtils.zeroPadding(i, len));
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
