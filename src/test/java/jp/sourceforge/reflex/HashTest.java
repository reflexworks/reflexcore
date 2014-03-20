package jp.sourceforge.reflex;

import static org.junit.Assert.*;
import jp.reflexworks.servlet.util.AuthTokenUtil;

import org.junit.Test;

public class HashTest {
	
	@Test
	public void testHash() {
		//String str = "testpass12345";
		String str = "terada2014";
		
		String hashStr = AuthTokenUtil.hash(str);
		
		System.out.println("str = " + str + ", hashStr = " + hashStr);
		
		assertTrue(hashStr != null && !str.equals(hashStr));
	}

}
