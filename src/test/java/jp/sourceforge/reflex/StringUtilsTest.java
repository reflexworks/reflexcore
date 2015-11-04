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
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
