package jp.sourceforge.reflex;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import jp.sourceforge.reflex.util.SHA256;


public class HashTest {
	
	@Test
	public void testHash() {
		//String str = "testpass12345";
		String str = "terada2014";
		
		String hashStr = SHA256.hashString(str);
		
		System.out.println("str = " + str + ", hashStr = " + hashStr);
		
		assertTrue(hashStr != null && !str.equals(hashStr));
	}

}
