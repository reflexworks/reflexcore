package jp.sourceforge.reflex.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import jp.reflexworks.servlet.ReflexServletConst;

/**
 * HTTPリクエストを行うクラス.
 * <p>
 * java.net.HttpURLConnectionを使用してHTTP通信を行います。
 * </p>
 */
public class Requester implements ReflexServletConst {

	/** ステータスのエラー判定コード */
	private static final int ERROR_STATUS = 400;

	/** ロガー */
	protected Logger logger = Logger.getLogger(this.getClass().getName());

	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0は無制限とならず、デフォルトになります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method,
			Map<String, String> property, int timeoutMillis) throws IOException {
		return request(urlStr, method, (InputStream)null, property, timeoutMillis);
	}

	/**
	 * HTTPリクエスト送信
	 * @param urlStr URL
	 * @param method method
	 * @param inputData POSTデータ
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)
	 * @return HttpURLConnection
	 */
	public HttpURLConnection request(String urlStr, String method, byte[] inputData,
			Map<String, String> property, int timeoutMillis) throws IOException {
		ByteArrayInputStream bin = null;
		if (inputData != null) {
			bin = new ByteArrayInputStream(inputData);
		}
		return request(urlStr, method, bin, property, timeoutMillis);
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
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0(無制限)は無効とし、デフォルト設定になります。マイナス値もデフォルト設定。
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
	 * HTTPリクエスト送信準備.
	 * connectは実施していない状態。
	 * OutputStreamを取得するためのメソッド。
	 *
	 * 後続処理で以下の処理を行ってください。(HttpURLConnection http)
	 *   http.setDoOutput(true);
	 *   http.connect();
	 *   http.getOutputStream()
	 *
	 * @param urlStr URL
	 * @param method method
	 * @param property リクエストヘッダ
	 * @param timeoutMillis タイムアウト時間(ミリ秒)。0(無制限)は無効とし、デフォルト設定になります。
	 * @return HttpURLConnection
	 */
	public HttpURLConnection beforeConnection(String urlStr, String method,
			Map<String, String> property, int timeoutMillis)
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
			out.close();
			in.close();
		}
	}

	/**
	 * InputStreamから読み出した内容を、OutputStreamに出力します。
	 * @param in InputStream
	 * @param outList OutputStreamのリスト
	 * @throws IOException
	 */
	public void write(InputStream in, List<OutputStream> outList) throws IOException {
		if (in == null || outList == null || outList.isEmpty()) {
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
				out.close();
			}
			in.close();
		}
	}

	/**
	 * レスポンスボディを文字列として返却
	 * @param http HttpURLConnection
	 * @return レスポンスボディの文字列
	 */
	public String getResponseString(HttpURLConnection http)
	throws IOException {
		BufferedReader br = getResponseReader(http);
		if (br == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		try {
			String str = br.readLine();
			while (str != null) {
				sb.append(str);
				str = br.readLine();
				if (str != null) {
					sb.append(NEWLINE);
				}
			}
		} finally {
			br.close();
		}
		return sb.toString();
	}

	/**
	 * レスポンスボディを文字列の配列として返却.
	 * 改行ごとに配列に格納する。
	 * @param http HttpURLConnection
	 * @return レスポンスボディの文字列配列
	 */
	public String[] getResponseStringArray(HttpURLConnection http)
	throws IOException {
		BufferedReader br = getResponseReader(http);
		if (br == null) {
			return null;
		}
		List<String> list = new ArrayList<String>();
		try {
			String str = br.readLine();
			while (str != null) {
				list.add(str);
				str = br.readLine();
			}
		} finally {
			br.close();
		}
		return list.toArray(new String[0]);
	}

	/**
	 * レスポンスボディをReaderとして返却.
	 * @param http HttpURLConnection
	 * @return レスポンスボディのReader
	 */
	public BufferedReader getResponseReader(HttpURLConnection http)
	throws IOException {
		InputStream in = getResponseStream(http);
		if (in == null) {
			return null;
		}
		return new BufferedReader(new InputStreamReader(in, ENCODING));
	}

	/**
	 * レスポンスボディをInputStreamとして返却.
	 * @param http HttpURLConnection
	 * @return レスポンスボディのInputStream
	 */
	public InputStream getResponseStream(HttpURLConnection http)
	throws IOException {
		if (http == null) {
			return null;
		}
		int rc = http.getResponseCode();
		InputStream is;
		if (rc >= ERROR_STATUS) {
			is = http.getErrorStream();
		} else {
			is = http.getInputStream();
		}
		return is;
	}

}
