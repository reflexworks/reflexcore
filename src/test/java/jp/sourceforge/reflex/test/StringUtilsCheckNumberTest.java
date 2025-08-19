package jp.sourceforge.reflex.test;

import static org.junit.Assert.*;

import org.junit.Test;

import jp.sourceforge.reflex.util.StringUtils;

/**
 * StringUtilsの数値チェックテスト
 */
public class StringUtilsCheckNumberTest {

	@Test
	public void test() {
		try {
			String val = null;
			boolean ret = false;
			System.out.println("--- StringUtilsCheckNumberTest start ---");
			
			// isNumber=true
			ret = true;
			val = "12345";
			isNumber(val, ret);
			val = "-12345";
			isNumber(val, ret);
			val = "12345.6789";
			isNumber(val, ret);
			val = "-12345.6789";
			isNumber(val, ret);
			val = "3.4028235E38";
			isNumber(val, ret);
			val = "1.4E-45";
			isNumber(val, ret);
			val = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNumber(val, ret);

			// isNumber=false
			ret = false;
			val = "aaa";
			isNumber(val, ret);
			val = "--12345";
			isNumber(val, ret);
			val = "a1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNumber(val, ret);

			// isNaturalInteger=true
			ret = true;
			val = "12345";
			isNaturalNumber(val, ret);
			val = "2147483647";
			isNaturalNumber(val, ret);

			// isNaturalInteger=false
			ret = false;
			val = "-12345";
			isNaturalInteger(val, ret);
			val = "12345.6789";
			isNaturalInteger(val, ret);
			val = "-12345.6789";
			isNaturalInteger(val, ret);
			val = "9223372036854775807";
			isNaturalInteger(val, ret);
			val = "3.4028235E38";
			isNaturalInteger(val, ret);
			val = "1.4E-45";
			isNaturalInteger(val, ret);
			val = "aaa";
			isNaturalInteger(val, ret);
			val = "--12345";
			isNaturalInteger(val, ret);
			val = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNaturalInteger(val, ret);
			val = "a1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNaturalInteger(val, ret);

			// isNaturalNumber=true
			ret = true;
			val = "12345";
			isNaturalNumber(val, ret);
			val = "2147483647";
			isNaturalNumber(val, ret);
			val = "9223372036854775807";
			isNaturalNumber(val, ret);

			// isNaturalNumber=false
			ret = false;
			val = "-12345";
			isNaturalNumber(val, ret);
			val = "12345.6789";
			isNaturalNumber(val, ret);
			val = "-12345.6789";
			isNaturalNumber(val, ret);
			val = "3.4028235E38";
			isNaturalNumber(val, ret);
			val = "1.4E-45";
			isNaturalNumber(val, ret);
			val = "aaa";
			isNaturalNumber(val, ret);
			val = "--12345";
			isNaturalNumber(val, ret);
			val = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNaturalNumber(val, ret);
			val = "a1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isNaturalNumber(val, ret);

			// isPositiveNumber=true
			ret = true;
			val = "12345";
			isPositiveNumber(val, ret);
			val = "2147483647";
			isPositiveNumber(val, ret);
			val = "12345.6789";
			isPositiveNumber(val, ret);
			val = "3.4028235E38";
			isPositiveNumber(val, ret);
			val = "1.4E-45";
			isPositiveNumber(val, ret);
			val = "9223372036854775807";
			isPositiveNumber(val, ret);
			val = "12345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isPositiveNumber(val, ret);

			// isNaturalNumber=false
			ret = false;
			val = "-12345";
			isPositiveNumber(val, ret);
			val = "-12345.6789";
			isPositiveNumber(val, ret);
			val = "-3.4028234E38";
			isPositiveNumber(val, ret);
			val = "-1.3E-45";
			isPositiveNumber(val, ret);
			val = "aaa";
			isPositiveNumber(val, ret);
			val = "--12345";
			isPositiveNumber(val, ret);
			val = "a1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890";
			isPositiveNumber(val, ret);

			
			System.out.println("--- StringUtilsCheckNumberTest end ---");

		} catch (Throwable e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	/**
	 * isNumberテスト.
	 * @param val テスト値
	 * @param ret 予想結果
	 */
	private void isNumber(String val, boolean ret) {
		boolean isNumber = StringUtils.isNumber(val);
		System.out.println("[isNumber] ret=" + isNumber + ", val=" + val);
		assertEquals(isNumber, ret);
	}
	
	/**
	 * isNaturalIntegerテスト.
	 * @param val テスト値
	 * @param ret 予想結果
	 */
	private void isNaturalInteger(String val, boolean ret) {
		boolean isNaturalInteger = StringUtils.isNaturalInteger(val);
		System.out.println("[isNaturalInteger] ret=" + isNaturalInteger + ", val=" + val);
		assertEquals(isNaturalInteger, ret);
	}
	
	/**
	 * isNaturalNumberテスト.
	 * @param val テスト値
	 * @param ret 予想結果
	 */
	private void isNaturalNumber(String val, boolean ret) {
		boolean isNaturalNumber = StringUtils.isNaturalNumber(val);
		System.out.println("[isNaturalNumber] ret=" + isNaturalNumber + ", val=" + val);
		assertEquals(isNaturalNumber, ret);
	}

	/**
	 * isPositiveNumberテスト.
	 * @param val テスト値
	 * @param ret 予想結果
	 */
	private void isPositiveNumber(String val, boolean ret) {
		boolean isPositiveNumber = StringUtils.isPositiveNumber(val);
		System.out.println("[isPositiveNumber] ret=" + isPositiveNumber + ", val=" + val);
		assertEquals(isPositiveNumber, ret);
	}

}
