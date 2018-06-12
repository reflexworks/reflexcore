package jp.sourceforge.reflex;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jp.sourceforge.reflex.util.BCDUtil;

public class BcdTest {
	
	@Test
	public void testBcd() {
		
		//keyをbcdに変換
		//String key = "1234567890123456789-9876-54321";
		String key = "1-23478-10-23-3-1-5-243234";
		byte[] bcd = BCDUtil.key2bcd(key);

		System.out.println("key = "+key);
		//Hexで表示
		hexencode(bcd);

		System.out.println("サイズ:"+key.length()+" 変換後のサイズ:"+bcd.length);
		
		System.out.println("BCDからStringへ戻す");
		System.out.println(BCDUtil.bcd2key(bcd));

		assertTrue(true);
	}

	private static void hexencode(byte[] bcd) {

		System.out.println("BCD:");
		for (byte b : bcd) {
			System.out.print(" " + Integer.toHexString(b & 0xff));
		}
		System.out.println();
	}


}
