package jp.sourceforge.reflex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.UnsupportedEncodingException;

import org.junit.jupiter.api.Test;

import jp.reflexworks.servlet.util.HeaderUtil;


public class HeaderTest {
	
	private static final String[] TEST_STRS = {
		"RXNODE=.0;Path=/;Expires=Wed, 05-Nov-2035 05:04:42 GMT",
		"OVERRIDE=;Path=/;Expires=Thu, 01 Jan 1970 00:00:00 GMT",
		"TEST=ab:cd-ef,gh/ij;Path=/;Expires=Wed, 05-Nov-35 05:04:42 GMT"
	};
	
	private static final int EXPIRE_CNT = 2;

	@Test
	public void testCookieDate() throws UnsupportedEncodingException {
		
		System.out.println("--- test CookieDate start ---");
		
		int cnt = 0;
		for (String testStr : TEST_STRS) {
			String[] keyValue = HeaderUtil.getSetCookieValue(testStr);
			if (keyValue != null) {
				System.out.println(keyValue[0] + "=" + keyValue[1]);
				if (!"".equals(keyValue[1])) {
					cnt++;
				}
			} else {
				System.out.println("key-value is null");
			}
		}
		
		System.out.println("--- test CookieDate end ---");
		
		assertTrue(cnt == EXPIRE_CNT);
	}

	@Test
	public void testAddQueryParam() throws UnsupportedEncodingException {
		
		System.out.println("--- test addQueryParam start ---");
		
		// クエリ文字列なし
		String url = "http://myservice.vte.cx/d/15/source";
		String key = "jp.reflexworks.sample";
		String value = "Blank.java";
		
		String editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "?" + key + "=" + value));
		
		// クエリ文字列なし、値null
		url = "http://myservice.vte.cx/d/15/source";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "?" + key));
		
		// クエリ文字列なし、値空文字
		url = "http://myservice.vte.cx/d/15/source";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "?" + key));

		// クエリ文字列1個あり
		url = "http://myservice.vte.cx/d/15/source?test=aa";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "&" + key + "=" + value));
		
		// クエリ文字列1個あり、値null
		url = "http://myservice.vte.cx/d/15/source?test=aa";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "&" + key));
		
		// クエリ文字列1個あり、値空文字
		url = "http://myservice.vte.cx/d/15/source?test=aa";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "&" + key));

		// クエリ文字列2個あり
		url = "http://myservice.vte.cx/d/15/source?test=aa&test2=bb";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url + "&" + key + "=" + value));
		
		// 重複したクエリ文字列あり (1)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key + "=" + value));
		
		// 重複したクエリ文字列あり、値null (1)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key));
		
		// 重複したクエリ文字列あり、値空文字 (1)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key));

		// 重複したクエリ文字列あり (2)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key + "=" + value));

		// 重複したクエリ文字列あり、値null (2)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key));

		// 重複したクエリ文字列あり、値空文字 (2)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?" + key));

		// 重複したクエリ文字列あり (3)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key + "=" + value));

		// 重複したクエリ文字列あり、値null (3)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key));

		// 重複したクエリ文字列あり、値空文字 (3)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key));

		// 重複したクエリ文字列あり (4)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = "Blank.java";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key + "=" + value));

		// 重複したクエリ文字列あり、値null (4)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = null;
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key));

		// 重複したクエリ文字列あり、値空文字 (4)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample=Test.java";
		key = "jp.reflexworks.sample";
		value = "";
		
		editUrl = HeaderUtil.addQueryParam(url, key, value);
		System.out.println("[addQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa&" + key));

		System.out.println("--- test addQueryParam end ---");
	}

	@Test
	public void testRemoveQueryParam() throws UnsupportedEncodingException {
		
		System.out.println("--- test removeQueryParam start ---");
		
		// クエリ文字列なし
		String url = "http://myservice.vte.cx/d/15/source";
		String key = "jp.reflexworks.sample";
		
		String editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url));
		
		// 別のクエリ文字列あり (1)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.samples";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url));
		
		// 別のクエリ文字列あり (2)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.samples=Blank.java";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals(url));
		
		// クエリ文字列あり (1)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source"));
		
		// クエリ文字列あり (1) 値あり
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample=Blank.java";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);

		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source"));
		
		// クエリ文字列あり (2)
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample&test=aa";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa"));
		
		// クエリ文字列あり (2) 値あり
		url = "http://myservice.vte.cx/d/15/source?jp.reflexworks.sample=Blank.java&test=aa";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa"));
		
		// クエリ文字列あり (3)
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample&";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa"));
		
		// クエリ文字列あり (3) 値あり
		url = "http://myservice.vte.cx/d/15/source?test=aa&jp.reflexworks.sample=Blank.java";
		key = "jp.reflexworks.sample";
		
		editUrl = HeaderUtil.removeQueryParam(url, key);
		System.out.println("[removeQueryParam]" + editUrl);
		
		assertTrue(editUrl.equals("http://myservice.vte.cx/d/15/source?test=aa"));

		System.out.println("--- test removeQueryParam end ---");
	}

	@Test
	public void testContentType() {
		String uri = null;
		String contentType = null;
		
		uri = "picture.jpg";
		contentType = HeaderUtil.getContentTypeByFilename(uri);
		System.out.println("[testContentType] uri = " + uri + " , contentType = " + contentType);
		assertEquals(contentType, "image/jpeg");
		
		uri = "/_html/img/timer.gif";
		contentType = HeaderUtil.getContentTypeByFilename(uri);
		System.out.println("[testContentType] uri = " + uri + " , contentType = " + contentType);
		assertEquals(contentType, "image/gif");
		
		uri = "fruit.png";
		contentType = HeaderUtil.getContentTypeByFilename(uri);
		System.out.println("[testContentType] uri = " + uri + " , contentType = " + contentType);
		assertEquals(contentType, "image/png");
		
		uri = null;
		contentType = HeaderUtil.getContentTypeByFilename(uri);
		System.out.println("[testContentType] uri = " + uri + " , contentType = " + contentType);
		assertEquals(contentType, null);

	}

}
