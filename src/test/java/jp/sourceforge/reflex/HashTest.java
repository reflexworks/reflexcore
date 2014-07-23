package jp.sourceforge.reflex;

import static org.junit.Assert.*;
import jp.sourceforge.reflex.util.SHA256;

import org.junit.Test;

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
