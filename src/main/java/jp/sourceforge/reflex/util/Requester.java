package jp.sourceforge.reflex.util;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedInputStream;
import java.io.UnsupportedEncodingException;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.Iterator;
import java.util.logging.Logger;

/**
 * HTTPリクエストを行うクラス
 */
public class Requester {
	
	public static final String ENCODING = "UTF-8";
	protected Logger logger = Logger.getLogger(this.getClass().getName());
	
	/**
	 * HTTPリクエストを行い、レスポンスをOutputStreamに出力します。
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @param property リクエストヘッダ
	 * @param out レスポンス出力先
	 * @throws IOException
	 */
	public void request(String urlStr, String method, byte[] inputData, 
			Map<String, String> property, OutputStream out) 
	throws IOException {
		if (out == null) {
			return;
		}
		
		HttpURLConnection http = request(urlStr, method, inputData, property);
		write(http.getInputStream(), out);
	}
	
	/**
	 * HTTPリクエストを行い、レスポンスをOutputStreamに出力します。
	 * @param urlStr URL
	 * @param method method
	 * @param inputDataStr POSTデータ
	 * @param property リクエストヘッダ
	 * @param out レスポンス出力先
	 * @throws IOException
	 */
	public void requestString(String urlStr, String method, String inputDataStr, 
			Map<String, String> property, OutputStream out) 
	throws IOException {
		if (out == null) {
			return;
		}
		
		HttpURLConnection http = requestString(urlStr, method, inputDataStr, property);
		write(http.getInputStream(), out);
	}

	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputDataStr POSTデータ
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 * @throws IOException
	 */
	public HttpURLConnection requestString(String urlStr, String method, 
			String inputDataStr, Map<String, String> property) throws IOException {
		byte[] inputData = null;
		try {
			if (inputDataStr != null) {
				inputData = inputDataStr.getBytes(ENCODING);
			}
		} catch (UnsupportedEncodingException e) {
			logger.warning(e.getMessage());
		}
		return request(urlStr, method, inputData, property);
	}
	
	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method) 
	throws IOException {
		return request(urlStr, method, (InputStream)null, null);
	}
	
	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, byte[] inputData) 
	throws IOException {
		return request(urlStr, method, inputData, null);
	}
	
	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, InputStream inputData) 
	throws IOException {
		return request(urlStr, method, inputData, null);
	}

	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, 
			Map<String, String> property) throws IOException {
		return request(urlStr, method, (InputStream)null, property);
	}

	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, byte[] inputData, 
			Map<String, String> property) throws IOException {
		ByteArrayInputStream bin = null;
		if (inputData != null) {
			bin = new ByteArrayInputStream(inputData);
		}
		return request(urlStr, method, bin, property);
	}	
	
	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, InputStream inputData, 
			Map<String, String> property) throws IOException {

		HttpURLConnection http = prepare(urlStr, method, property);
		if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
		} else {
			if (inputData != null) {
				BufferedOutputStream bout = null;
				try {
					bout = new BufferedOutputStream(http.getOutputStream());
					
					//bout.write(inputData);
					
					int len = 0;
					byte[] buffer = new byte[2048];
					
					while ((len = inputData.read(buffer)) > -1) {
						bout.write(buffer, 0, len);
					}
					
				} finally {
					if (bout != null) {
						bout.close();
					}
				}
			}
		}

		http.getResponseCode();	// ここでサーバに接続
		
		return http;
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, Map<String, String> property) 
	throws IOException {
		URL url = new URL(urlStr);
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod(method);
		if (property != null && !property.isEmpty()) {
			Iterator<String> it = property.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String val = property.get(key);
				http.setRequestProperty(key, val);
			}
		}

		if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
			http.connect();
		} else {
			http.setDoOutput(true);
			http.connect();
		}
		return http;
	}
	
	/**
	 * InputStreamから読み出した内容を、OutputStreamに出力します。
	 * @param in InputStream
	 * @param out OutputStream
	 * @throws IOException
	 */
	public void write(InputStream in, OutputStream out) throws IOException {
		if (in == null) {
			return;
		}
		try {
			// default buffer size = 8192
			BufferedInputStream bis = new BufferedInputStream(in);		
			int size;
			while ((size = bis.read()) != -1) {
				out.write(size);
			}
		
		} finally {
			out.close();
			in.close();
		}
	}

}
