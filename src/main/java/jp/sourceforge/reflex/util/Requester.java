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
import java.util.List;
import java.util.logging.Logger;

import jp.reflexworks.servlet.ReflexServletConst;

/**
 * HTTPリクエストを行うクラス.
 * <p>
 * java.net.HttpURLConnectionを使用してHTTP通信を行います。
 * </p>
 */
public class Requester implements ReflexServletConst {
	
	private static final int ERROR_STATUS = 400;
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
		InputStream in = null;
		if (http.getResponseCode() >= ERROR_STATUS) {
			in = http.getErrorStream();
		} else {
			in = http.getInputStream();
		}
		write(in, out);
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
		InputStream in = null;
		if (http.getResponseCode() >= ERROR_STATUS) {
			in = http.getErrorStream();
		} else {
			in = http.getInputStream();
		}
		write(in, out);
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
		return request(urlStr, method, inputData, property, -1);
	}
	
	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0は無制限とならず、デフォルトになります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, InputStream inputData, 
			Map<String, String> property, int timeoutMillis) throws IOException {
		HttpURLConnection http = prepare(urlStr, method, inputData, property, timeoutMillis);
		http.getResponseCode();	// ここでサーバに接続
		
		return http;
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0(無制限)は無効とし、デフォルト設定になります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			Map<String, String> property, int timeoutMillis) 
	throws IOException {
		return prepare(urlStr, method, (InputStream)null, property, timeoutMillis);
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			Map<String, String> property) 
	throws IOException {
		return prepare(urlStr, method, (InputStream)null, property);
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param inputData リクエストデータ
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			byte[] inputData, Map<String, String> property) 
	throws IOException {
		ByteArrayInputStream bin = null;
		if (inputData != null) {
			bin = new ByteArrayInputStream(inputData);
		}
		return prepare(urlStr, method, bin, property);
	}
	
	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param inputData リクエストデータ
	 * @param property リクエストヘッダ
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			InputStream inputData, Map<String, String> property) 
	throws IOException {
		return prepare(urlStr, method, inputData, property, -1);
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param inputData リクエストデータ
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0(無制限)は無効とし、デフォルト設定になります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			byte[] inputData, Map<String, String> property, int timeoutMillis) 
	throws IOException {
		ByteArrayInputStream bin = null;
		if (inputData != null) {
			bin = new ByteArrayInputStream(inputData);
		}
		return prepare(urlStr, method, bin, property, timeoutMillis);
	}

	/**
	 * HTTPリクエスト送信準備
	 * @param urlStr URL
	 * @param method method
	 * @param inputData リクエストデータ
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0(無制限)は無効とし、デフォルト設定になります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection prepare(String urlStr, String method, 
			InputStream inputData, Map<String, String> property, int timeoutMillis) 
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
		if (timeoutMillis > 0) {
			http.setConnectTimeout(timeoutMillis);
			http.setReadTimeout(timeoutMillis);
		}

		if ("GET".equalsIgnoreCase(method) || "DELETE".equalsIgnoreCase(method)) {
			http.connect();
		} else {
			http.setDoOutput(true);	// 後続処理でinputDataを設定する場合があるので、inputDataのnull判定不要。
			http.connect();

			if (inputData != null) {
				BufferedOutputStream bout = null;
				try {
					bout = new BufferedOutputStream(http.getOutputStream());
					
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
		
		return http;
	}

	/**
	 * InputStreamから読み出した内容を、OutputStreamに出力します。
	 * @param in InputStream
	 * @param out OutputStream
	 * @throws IOException
	 */
	public void write(InputStream in, OutputStream out) throws IOException {
		if (in == null || out == null) {
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
			try {
				out.close();
			} catch (Exception e) {}	// Do nothing.
			try {
				in.close();
			} catch (Exception e) {}	// Do nothing.
		}
	}

	/**
	 * InputStreamから読み出した内容を、OutputStreamに出力します。
	 * @param in InputStream
	 * @param outList OutputStreamのリスト
	 * @throws IOException
	 */
	public void write(InputStream in, List<OutputStream> outList) throws IOException {
		if (in == null || outList == null || outList.size() == 0) {
			return;
		}
		try {
			// default buffer size = 8192
			BufferedInputStream bis = new BufferedInputStream(in);		
			int size;
			while ((size = bis.read()) != -1) {
				for (OutputStream out : outList) {
					out.write(size);
				}
			}
		
		} finally {
			for (OutputStream out : outList) {
				try {
					out.close();
				} catch (Exception e) {}	// Do nothing.
			}
			try {
				in.close();
			} catch (Exception e) {}	// Do nothing.
		}
	}

}
