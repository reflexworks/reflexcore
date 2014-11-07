package jp.sourceforge.reflex;

import java.io.UnsupportedEncodingException;

import jp.reflexworks.servlet.util.HeaderUtil;
import static org.junit.Assert.*;

import org.junit.Test;

public class HeaderTest {
	
	private static final String[] TEST_STRS = {
		"RXNODE=.0;Path=/;Expires=Wed, 05-Nov-2015 05:04:42 GMT",
		"OVERRIDE=;Path=/;Expires=Thu, 01 Jan 1970 00:00:00 GMT",
		"TEST=ab:cd-ef,gh/ij;Path=/;Expires=Wed, 05-Nov-15 05:04:42 GMT"
	};

	@Test
	public void testCookieDate() throws UnsupportedEncodingException {
		
		System.out.println("--- test CookieDate start ---");
		
		int cnt = 0;
		for (String testStr : TEST_STRS) {
			String[] keyValue = HeaderUtil.getSetCookieValue(testStr);
			if (keyValue != null) {
				System.out.println(keyValue[0] + "=" + keyValue[1]);
				cnt++;
			} else {
				System.out.println("key-value is null");
			}
		}
		
		System.out.println("--- test CookieDate end ---");
		
		assertTrue(cnt == 2);
	}

}
