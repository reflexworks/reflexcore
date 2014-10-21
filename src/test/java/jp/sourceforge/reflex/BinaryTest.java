package jp.sourceforge.reflex;

import java.io.UnsupportedEncodingException;

import javax.xml.bind.DatatypeConverter;

import jp.sourceforge.reflex.util.BinaryUtil;
import static org.junit.Assert.*;

import org.junit.Test;

public class BinaryTest {
	
	private static final String TEST_STR = "あいうえおabc12300-#";

	@Test
	public void testHex() throws UnsupportedEncodingException {
		
		byte[] TEST_DATA = TEST_STR.getBytes("UTF-8");
		
		String hexBind = DatatypeConverter.printHexBinary(TEST_DATA);
		String hexUtil = BinaryUtil.bin2hex(TEST_DATA);
		
		System.out.println("hexBind: " + hexBind);
		System.out.println("hexUtil: " + hexUtil);
		
		assertEquals(hexBind, hexUtil.toUpperCase());
		
		byte[] binBind = DatatypeConverter.parseHexBinary(hexUtil);
		byte[] binUtil = BinaryUtil.hex2bin(hexUtil);
		
		System.out.println("binBind: " + BinaryUtil.printInt(binBind));
		System.out.println("binUtil: " + BinaryUtil.printInt(binUtil));
		
		assertTrue(equals(binBind, binUtil));
	}
	
	private boolean equals(byte[] bin1, byte[] bin2) {
		if (bin1 == null || bin2 == null) {
			return false;
		}
		if (bin1.length != bin2.length) {
			return false;
		}
		for (int i = 0; i < bin1.length; i++) {
			if (bin1[i] != bin2[i]) {
				return false;
			}
		}
		return true;
	}

}
