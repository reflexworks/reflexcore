package jp.reflexworks.servlet.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.Map;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.SHA256;
import jp.sourceforge.reflex.util.StringUtils;

import org.apache.commons.codec.binary.Base64;

import jp.reflexworks.servlet.ReflexServletConst;

public class AuthTokenUtil implements ReflexServletConst {

	private static Logger logger = Logger.getLogger(AuthTokenUtil.class.getName());

	// リクエストヘッダの項目名
	/** WSSEヘッダのキー */
	public static final String WSSE = "X-WSSE";
	/** トークン */
	public static final String TOKEN = "UsernameToken";
	/** Username */
	public static final String USER = "Username";
	/** PasswordDigest */
	public static final String PASSWORDDIGEST = "PasswordDigest";
	/** Nonce */
	public static final String NONCE = "Nonce";
	/** Created */
	public static final String CREATED = "Created";
	/** RXIDヘッダのキー */
	protected static final int HEADER_AUTHORIZATION_TOKEN_LEN = HEADER_AUTHORIZATION_TOKEN.length();
	/** ハッシュ関数 */
	//public static final String HASH_ALGORITHM = "SHA-1";
	//public static final String HASH_ALGORITHM = "SHA-256";
	/** 乱数生成のための関数 */
	public static final String RANDOM_ALGORITHM = "SHA1PRNG";
	
	/** RXID */
	public static final String RXID = "_RXID";	// URLパラメータのみ
	
	/** エンコード */
	//public static final String ENCODING = "UTF-8";
	
	/** RXID Delimiter **/
	protected static final String RXID_DELIMITER = "-";
	
	/**
	 * ユーザ名とパスワードとAPIKeyからRXID文字列を作成します.
	 * @param username ユーザ名
	 * @param password パスワード
	 * @param apiKey APIKey
	 * @return RXID文字列
	 */
	public static String createRXIDString(String username, String password, 
			String serviceName, String apiKey) {
		WsseAuth auth = createRxidAuth(username, password, serviceName, apiKey);
		return getRXIDString(auth);
	}
	
	/**
	 * RXIDの先頭に"Token "を付けて返却します.
	 * @param rxid RXID
	 * @return "Token {RXID}"
	 */
	public static String editRXIDHeader(String rxid) {
		if (!StringUtils.isBlank(rxid)) {
			return HEADER_AUTHORIZATION_TOKEN + rxid;
		}
		return null;
	}
	
	/**
	 * アクセスキーの先頭に"AccessKey "を付けて返却します.
	 * @param accesskey アクセスキー
	 * @return "AccessKey {アクセスキー}"
	 */
	public static String editAccessKeyHeader(String accesskey) {
		if (!StringUtils.isBlank(accesskey)) {
			return HEADER_AUTHORIZATION_ACCESSKEY + accesskey;
		}
		return null;
	}

	/**
	 * WSSE文字列を作成します(RequestHeader用)
	 * @param username ユーザ名
	 * @param password パスワード
	 * @return RequestHeaderに設定するWSSE情報
	 */
	public static String createWsseHeaderValue(String username, String password) {
		return getWsseHeaderValue(createWsseAuth(username, password));
	}

	/**
	 * WSSE文字列を作成します(RequestHeader用)
	 * @param auth WSSE認証情報
	 * @return RequestHeaderに設定するWSSE情報
	 */
	public static String getWsseHeaderValue(WsseAuth auth) {
		if (auth == null) {
			return "";
		}
		
		StringBuffer buf = new StringBuffer();
		buf.append(TOKEN);
		buf.append(" ");
		buf.append(USER);
		buf.append("=\"");
		buf.append(auth.username);
		buf.append("\", ");
		buf.append(PASSWORDDIGEST);
		buf.append("=\"");
		buf.append(auth.passwordDigest);
		buf.append("\", ");
		buf.append(NONCE);
		buf.append("=\"");
		buf.append(auth.nonce);
		buf.append("\", ");
		buf.append(CREATED);
		buf.append("=\"");
		buf.append(auth.created);
		buf.append('"');
		
		return buf.toString();
	}

	/**
	 * WSSE認証情報を作成します
	 * @param username ユーザ名
	 * @param password パスワード
	 */
	public static WsseAuth createWsseAuth(String username, String password) {
		return createRxidAuth(username, password, null, null);
	}

	/**
	 * WSSE認証情報を作成します
	 * @param username ユーザ名
	 * @param password パスワード
	 * @param apiKey APIKey (RXIDの場合APIKeyを指定します。)
	 */
	public static WsseAuth createRxidAuth(String username, String password, 
			String serviceName, String apiKey) {
		if (StringUtils.isBlank(username) || password ==  null) {
			return null;
		}
		
		WsseAuth auth = null;
		
		byte[] nonceB = new byte[8];
		try {
			SecureRandom.getInstance(RANDOM_ALGORITHM).nextBytes(nonceB);

			Date now = new Date();
			String created = DateUtil.getDateTime(now);

			byte[] createdB = created.getBytes(ENCODING);
			byte[] passwordB = password.getBytes(ENCODING);
			byte[] apiKeyB = null;
			if (apiKey != null) {
				apiKeyB = apiKey.getBytes(ENCODING);
			}

			int len = nonceB.length + createdB.length + passwordB.length;
			int apiKeyLen = 0;
			if (apiKey != null) {
				// APIKeyを含む
				apiKeyLen = apiKeyB.length;
				len += apiKeyLen;
			}
			byte[] v = new byte[len];
			if (apiKey != null) {
				// APIKeyを含む
				System.arraycopy(apiKeyB, 0, v, 0, apiKeyLen);
			}
			System.arraycopy(nonceB, 0, v, apiKeyLen, nonceB.length);
			System.arraycopy(createdB, 0, v, apiKeyLen + nonceB.length, createdB.length);
			System.arraycopy(passwordB, 0, v, apiKeyLen + nonceB.length + createdB.length, 
					passwordB.length);

			//MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
			//md.update(v);
			//byte[] digest = md.digest();
			//
			//String passwordDigestStr = new String(Base64.encodeBase64(digest), ENCODING);
			
			String passwordDigestStr = SHA256.hashString(v);
			String nonceStr = new String(Base64.encodeBase64(nonceB), ENCODING);

			if (!StringUtils.isBlank(serviceName)) {
				username = username + ":" + serviceName;
			}
			auth = new WsseAuth(username, passwordDigestStr, nonceStr, created);
			auth.password = password;

		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
		return auth;
	}
	
	/**
	 * rotate13(簡易暗号化)
	 * @param s
	 * @return 暗号化文字列
	 */
	public static String rot13(String s) {
		if (s == null) {
			return null;
		}

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '@') c = '!';
			else if (c == '!') c = '@';
			else if (c == '/') c = '~';
			else if (c == '~') c = '/';
			else if (c == '+') c = '*';
			else if (c == '*') c = '+';
			else if (c >= 'a' && c <= 'm') c += 13;
			else if (c >= 'A' && c <= 'M') c += 13;
			else if (c >= 'n' && c <= 'z') c -= 13;
			else if (c >= 'N' && c <= 'Z') c -= 13;
			
			if (c != '=') {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	/**
	 * "AccessKey {アクセスキー}"文字列からアクセスキーを取り出します.
	 * @param authorizationStr "AccessKey {アクセスキー}"文字列
	 * @return アクセスキー
	 */
	public static String extractRXID(String authorizationStr) {
		return extractAuthorization(authorizationStr, HEADER_AUTHORIZATION_TOKEN);
	}
	
	/**
	 * "Token {RXID}"文字列からRXIDを取り出します.
	 * @param authorizationStr "Token {RXID}"文字列
	 * @return RXID
	 */
	public static String extractAccessKey(String authorizationStr) {
		return extractAuthorization(authorizationStr, HEADER_AUTHORIZATION_ACCESSKEY);
	}
	
	/**
	 * "{prefix} {認証キー}"文字列から認証キーを取り出します.
	 * @param authorizationStr "{prefix} {認証キー}"
	 * @return 認証キー
	 */
	public static String extractAuthorization(String authorizationStr, 
			String prefix) {
		if (authorizationStr == null || StringUtils.isBlank(prefix) ||
				!authorizationStr.startsWith(prefix)) {
			return null;
		}
		return authorizationStr.substring(prefix.length());
	}

	/**
	 * RXIDのcreatedをWSSE用("yyyy-MM-dd'T'HH:mm:ss+99:99"形式の文字列)に変換します
	 * @param rxidcreated RXIDのcreated
	 * @return dateの文字列
	 * @throws ParseException 
	 */
	protected static String getDateTimeOfWSSE(String rxidcreated) throws ParseException {
		String dateStr = rxidcreated.replace("P", "+").replace("M", "-");
		int idx = dateStr.lastIndexOf("+");
		if (idx < 0) {
			idx = dateStr.lastIndexOf("-");
		}
		String timeZoneId = null;
		if (idx > 0) {
			timeZoneId = "GMT" + dateStr.substring(idx) + ":00";
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssZ");
		if (timeZoneId != null) {
			dateStr += "00";
			TimeZone timeZone = TimeZone.getTimeZone(timeZoneId);
			format.setTimeZone(timeZone);
			Date date = format.parse(dateStr);
			return DateUtil.getDateTime(date, timeZoneId);
		}
		return null;
	}

	/**
	 * WSSEのcreatedをRXID用("yyyyMMddHHmmssP99"形式の文字列)に変換します
	 * @param wssecreated WSSEのcreated
	 * @return dateの文字列
	 * @throws ParseException 
	 */
	protected static String getDateTimeOfRXID(String wssecreated) throws ParseException {
		int idx = wssecreated.lastIndexOf("+");
		if (idx < 0) {
			idx = wssecreated.lastIndexOf("-");
		}
		TimeZone timeZone = null;
		if (idx > 0) {
			String id = "GMT" + wssecreated.substring(idx);
			timeZone = TimeZone.getTimeZone(id);
		}
		
		Date date = DateUtil.getDate(wssecreated);
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		String datestring = format.format(date);
		format = new SimpleDateFormat("Z");
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		String zone = format.format(date);
		datestring += zone.substring(0, 3).replace("+", "P").replace("-", "M");
		return datestring;
	}
	
	/**
	 * WSSE認証情報からRXID文字列を作成します
	 * @param auth WSSE認証情報
	 * @return RXID文字列
	 */
	public static String getRXIDString(WsseAuth auth) {
		if (auth != null && auth.username != null && auth.passwordDigest != null &&
				auth.nonce != null && auth.created != null) {
			// 短縮形式
			try {
				StringBuilder buf = new StringBuilder();
				buf.append(getDateTimeOfRXID(auth.created));
				buf.append(RXID_DELIMITER);
				buf.append(rot13(auth.nonce));
				buf.append(RXID_DELIMITER);
				buf.append(rot13(auth.passwordDigest));
				buf.append(RXID_DELIMITER);
				buf.append(rot13(auth.username));
				return buf.toString();
				
			} catch (ParseException e) {}	// Do nothing.
		}

		return null;
	}
	
	/**
	 * レスポンスヘッダからsessionIdを取り出します.
	 * <p>
	 * sessionIdはSet-Cookieで設定されるため、Set-CookieからJSESSIONIDをの値を抽出します.
	 * </p>
	 * @param headerMaps レスポンスヘッダ
	 * @return sessionId
	 */
	public static String extractSessionId(Map headerMaps) {
		if (headerMaps != null && headerMaps.containsKey("Set-Cookie")) {
			Object obj = headerMaps.get("Set-Cookie");
			if (obj instanceof String) {
				return extractSessionId((String)obj);
			} else {	// List<String>
				List<String> headers = (List<String>)obj;
				for (String setCookie : headers) {
					String sessionId = extractSessionId(setCookie);
					if (!StringUtils.isBlank(sessionId)) {
						return sessionId;
					}
				}
			}
		}
		return null;
	}
	
	/**
	 * レスポンスヘッダからsessionIdを取り出します.
	 * <p>
	 * sessionIdはSet-Cookieで設定されるため、Set-CookieからJSESSIONIDをの値を抽出します.
	 * </p>
	 * @param cookieStr Set-Cookieの値
	 * @return sessionId
	 */
	public static String extractSessionId(String setCookie) {
		int i = setCookie.indexOf(COOKIE_JSESSIONID);
		if (i > -1) {
			int i2 = setCookie.indexOf(";", i);
			if (i2 == -1) {
				i2 = setCookie.length();
			}
			return setCookie.substring(i, i2);
		}
		return null;
	}
	
	/**
	 * 文字列をハッシュ化する
	 * @param str 文字列
	 * @return ハッシュ化し、Base64エンコーディングした文字列
	 */
	public static String hash(String str) {
		return SHA256.hashString(str);
	}

}
