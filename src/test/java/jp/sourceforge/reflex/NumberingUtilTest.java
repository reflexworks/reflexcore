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
import jp.sourceforge.reflex.util.NumberingUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class NumberingUtilTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public NumberingUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(NumberingUtilTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		int start = 1;
		int end = 10;
		
		try {
			int ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);

			
		} catch (Throwable e) {
			e.printStackTrace();
		}


	}
}
