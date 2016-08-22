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
	protected static final int HEADER_AUTHORIZATION_RXID_LEN = HEADER_AUTHORIZATION_RXID.length();
	/** ハッシュ関数 */
	//public static final String HASH_ALGORITHM = "SHA-1";
	//public static final String HASH_ALGORITHM = "SHA-256";
	/** 乱数生成のための関数 */
	public static final String RANDOM_ALGORITHM = "SHA1PRNG";
	
	/** RXID */
	public static final String RXID = "_RXID";	// URLパラメータのみ
	/** RXIDの旧URLパラメータ名 */
	public static final String RXID_LEGACY = "RXID";	// URLパラメータのみ

	/** RXIDのユーザ名とサービス名のセパレータ */
	public static final String RXIDNAME_SEPARATOR = ":";
	
	/** RXID Delimiter **/
	public static final String RXID_DELIMITER = "-";

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
	 * RXIDの先頭に"RXID "を付けて返却します.
	 * @param rxid RXID
	 * @return "RXID {RXID}"
	 */
	public static String editRXIDHeader(String rxid) {
		if (!StringUtils.isBlank(rxid)) {
			return HEADER_AUTHORIZATION_RXID + rxid;
		}
		return null;
	}
	
	/**
	 * アクセストークンの先頭に"Token "を付けて返却します.
	 * @param accesstoken アクセストークン
	 * @return "Token {アクセストークン}"
	 */
	public static String editAccessTokenHeader(String accesstoken) {
		if (!StringUtils.isBlank(accesstoken)) {
			return HEADER_AUTHORIZATION_TOKEN + accesstoken;
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
		
		StringBuilder sb = new StringBuilder();
		sb.append(TOKEN);
		sb.append(" ");
		sb.append(USER);
		sb.append("=\"");
		sb.append(auth.username);
		sb.append("\", ");
		sb.append(PASSWORDDIGEST);
		sb.append("=\"");
		sb.append(auth.passwordDigest);
		sb.append("\", ");
		sb.append(NONCE);
		sb.append("=\"");
		sb.append(auth.nonce);
		sb.append("\", ");
		sb.append(CREATED);
		sb.append("=\"");
		sb.append(auth.created);
		sb.append('"');
		
		return sb.toString();
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

			String passwordDigestStr = SHA256.hashString(v);
			String nonceStr = new String(Base64.encodeBase64(nonceB), ENCODING);

			if (!StringUtils.isBlank(serviceName)) {
				username = username + RXIDNAME_SEPARATOR + serviceName;
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

		StringBuilder sb = new StringBuilder();
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
	 * "RXID {RXID}"文字列からRXIDを取り出します.
	 * @param authorizationStr "RXID {RXID}"文字列
	 * @return RXID
	 */
	public static String extractRXID(String authorizationStr) {
		return extractAuthorization(authorizationStr, HEADER_AUTHORIZATION_RXID);
	}
	
	/**
	 * "Token {アクセストークン}"文字列からアクセストークンを取り出します.
	 * @param authorizationStr "Token {アクセストークン}"文字列
	 * @return アクセストークン
	 */
	public static String extractAccessToken(String authorizationStr) {
		return extractAuthorization(authorizationStr, HEADER_AUTHORIZATION_TOKEN);
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
	
	/**
	 * RXID文字列からWSSE認証情報を作成します
	 * @param value RXID文字列
	 * @param isRxid ワンタイム利用かどうか
	 * @return WSSE認証情報
	 */
	public static WsseAuth parseRXID(String value) {
		WsseAuth auth = null;
		if (value != null) {
			// 短縮形式
			// 不正文字列の場合処理しないでnullを返す
			int p1 = value.indexOf(RXID_DELIMITER);
			int p2 = -1;
			int p3 = -1;
			if (p1 > 0) {
				p2 = value.substring(p1 + 1).indexOf(RXID_DELIMITER) + 1;
			}
			if (p2 > 0) {
				p2 += p1;
				p3 = value.substring(p2 + 1).indexOf(RXID_DELIMITER) + 1;
			}
			if (p3 > 0) {
				p3 += p2;
				try {
					String createdStr = getDateTimeOfWSSE(value.substring(0, p1));
					String nonceStr = rot13(value.substring(p1 + 1, p2));
					String passwordDigestStr = rot13(value.substring(p2 + 1, p3)); 
					String username = rot13(value.substring(p3 + 1)); 
					if (createdStr != null && nonceStr != null && 
							passwordDigestStr != null && username != null) {
						auth = new WsseAuth(username, passwordDigestStr, 
								nonceStr, createdStr);
					}
				} catch (ParseException e) {}	// Do nothing.
			}
		}
		
		if (auth != null) {
			auth.isRxid = true;
		}
		return auth;
	}

	/**
	 * リクエストヘッダからWSSE認証情報を取り出します
	 * @param header リクエストヘッダに指定されたWSSE文字列
	 * @return WSSE認証情報
	 */
	public static WsseAuth parseWSSEheader(String header) {
		String authUsername = null;
		String authPassworddigest = null;
		String authNonce = null;
		String authCreated = null;

		String[] words = header.split(",");
		int idx, i;

		for (i = 0; i < words.length; i++) {
			String rec = words[i];
			int len = rec.length();

			if (((idx = rec.indexOf('=')) > 0) && (idx < (len-1)))  {
				String key = rec.substring(0, idx).trim();
				String val = rec.substring(idx+1, len).trim();
				int	stx = 0;
				int edx = val.length() - 1;
				char	c;
				if (((c = val.charAt(stx)) == '"') || (c == '\''))	stx++;
				if (((c = val.charAt(edx)) == '"') || (c == '\''))	edx--;
				val = val.substring(stx, edx+1);

				if (key.indexOf(USER) >= 0) {
					authUsername = val;
				}
				else if (key.equals(PASSWORDDIGEST)) {
					authPassworddigest = val;
				}
				else if (key.equals(NONCE)) {
					authNonce = val;
				}
				else if (key.equals(CREATED)) {
					authCreated = val;
				}
			}
		}

		//認証パラメータの取り出せない場合は終了
		if ((authUsername != null) &&
				(authPassworddigest != null) &&
				(authNonce != null) &&
				(authCreated != null)) {
			WsseAuth auth = new WsseAuth(authUsername, authPassworddigest, 
					authNonce, authCreated);
			return auth;
		}
		
		return null;
	}
	
	/**
	 * RXIDのユーザ名「{ユーザ名}:{サービス名}」から、ユーザ名(0)とサービス名(1)を分割して返却します.
	 * @param rxidname RXIDのユーザ名
	 * @return ユーザ名(0)とサービス名(1)を分割した文字列配列
	 */
	public static String[] getUsernameAndService(WsseAuth wsseAuth) {
		String rxidname = null;
		if (wsseAuth != null) {
			rxidname = wsseAuth.username;
		}
		if (rxidname != null) {
			String[] names = new String[2];
			int idx = rxidname.lastIndexOf(RXIDNAME_SEPARATOR);
			if (idx > -1) {
				names[0] = rxidname.substring(0, idx);
				names[1] = rxidname.substring(idx + 1);
			} else {
				names[0] = rxidname;
			}
			return names;
		}
		return null;
	}
	
	/**
	 * ユーザ名とサービス名から、RXIDのユーザ名「{ユーザ名}:{サービス名}」を生成します.
	 * @param username ユーザ名
	 * @param serviceName サービス名
	 * @return RXIDのユーザ名「{ユーザ名}:{サービス名}」
	 */
	public static String editUsernameAndService(String username, String serviceName) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		buf.append(username);
		if (!StringUtils.isBlank(serviceName)) {
			buf.append(RXIDNAME_SEPARATOR);
			buf.append(serviceName);
		}
		return buf.toString();
	}

}
