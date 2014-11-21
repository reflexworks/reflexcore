package jp.reflexworks.servlet.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.logging.Logger;

import jp.sourceforge.reflex.util.StringUtils;
import jp.reflexworks.servlet.ReflexServletConst;

public class HeaderUtil {
	
	public static final String VALUE_SEPARATOR = ",";
	public enum Option {ALL, FIRST, LAST};
	public static final String FORMAT_EXPIRE_1 = "EEE, dd-MMM-yy HH:mm:ss z";
	public static final String FORMAT_EXPIRE_2 = "EEE, dd MMM yy HH:mm:ss z";
	public static final String EXPIRES = "Expires";
	
	private static Logger logger = Logger.getLogger(HeaderUtil.class.getName());

	/**
	 * ヘッダに指定されたキーと値を追加します.
	 * <p>
	 * ヘッダに指定されたキーが存在する場合、カンマで区切って指定された値を追記します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @param val 値
	 */
	public static void addHeader(Map<String, String> headers, 
			String key, String value) {
		if (headers == null || StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return;
		}
		String trimVal = value.trim();
		Set<String> currentValues = getHeaderValues(headers, key);
		if (currentValues != null && currentValues.contains(trimVal)) {
			return;
		}
		currentValues.add(trimVal);
		headers.put(key, editHeaderValues(currentValues));
	}
	
	/**
	 * ヘッダから指定されたキーの値を取得します.
	 * <p>
	 * 指定されたキーで取得できない場合、キーを小文字にして取得します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @return 値
	 */
	public static String getHeaderValue(Map<String, String> headers,
			String key) {
		if (headers == null || StringUtils.isBlank(key)) {
			return null;
		}
		String val = headers.get(key);
		if (val == null) {
			val = headers.get(key.toLowerCase(Locale.ENGLISH));
		}
		return val;
	}
	
	/**
	 * ヘッダから指定されたキーの値を取得します.
	 * <p>
	 * 指定されたキーで取得できない場合、キーを小文字にして取得します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @return 値
	 */
	public static List<String> getHeaderValueList(Map<String, List<String>> headers,
			String key) {
		if (headers == null || StringUtils.isBlank(key)) {
			return null;
		}
		List<String> val = headers.get(key);
		if (val == null) {
			val = headers.get(key.toLowerCase(Locale.ENGLISH));
		}
		return val;
	}

	/**
	 * ヘッダから指定されたキーの値を取得します.
	 * <p>
	 * 指定されたキーで取得できない場合、キーを小文字にして取得します。<br>
	 * 値がカンマ区切りで定義されている場合、内容をリストにして返却します。<br>
	 * 値はトリミングして返却します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @return カンマ指定された値をリストにして返却
	 */
	public static Set<String> getHeaderValues(Map<String, String> headers,
			String key) {
		String val = getHeaderValue(headers, key);
		return getHeaderValues(val);
	}

	/**
	 * ヘッダから指定されたキーの値を取得します.
	 * <p>
	 * 指定されたキーで取得できない場合、キーを小文字にして取得します。<br>
	 * 値がカンマ区切りで定義されている場合、内容をリストにして返却します。<br>
	 * 値はトリミングして返却します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @param option 指定されたキーのヘッダが複数ある場合。nullの場合LASTとします。<br>
	 * <ul>
	 *   <li>ALL: 全て</li>
	 *   <li>FIRST: 先頭の値を有効とする</li>
	 *   <li>LAST: 最後の値を有効とする</li>
	 * </ul>
	 * @return カンマ指定された値をリストにして返却
	 */
	public static Set<String> getHeaderValuesList(Map<String, List<String>> headers,
			String key, Option option) {
		List<String> val = getHeaderValueList(headers, key);
		if (val != null && val.size() > 0) {
			if (option == null) {
				option = Option.LAST;	// default
			}
			if (option == Option.FIRST) {
				return getHeaderValues(val.get(0));
			} else if (option == Option.LAST) {
				return getHeaderValues(val.get(val.size() - 1));
			} else {	// ALL
				Set<String> ret = null;
				for (String str : val) {
					Set<String> tmp = getHeaderValues(str);
					if (tmp != null) {
						if (ret == null) {
							ret = new LinkedHashSet<String>();
						}
						ret.addAll(tmp);
					}
				}
				return ret;
			}
		}
		return null;
	}

	/**
	 * カンマ区切りのヘッダの内容をリストにして返却します.
	 * <p>
	 * 値はトリミングして返却します。
	 * </p>
	 * @param val 値
	 * @return カンマ指定された値をリストにして返却
	 */
	public static Set<String> getHeaderValues(String val) {
		if (val == null) {
			return null;
		}
		Set<String> values = new LinkedHashSet<String>();
		String[] valParts = val.split(VALUE_SEPARATOR);
		for (String valPart : valParts) {
			values.add(valPart.trim());
		}
		return values;
	}

	/**
	 * リストに指定された値をカンマ区切り文字列にします.
	 * @param values リストに指定された値
	 * @return カンマ区切り文字列
	 */
	public static String editHeaderValues(Set<String> values) {
		if (values == null || values.size() == 0) {
			return null;
		}
		boolean isFirst = true;
		StringBuilder buf = new StringBuilder();
		for (String value : values) {
			if (isFirst) {
				isFirst = false;
			} else {
				buf.append(VALUE_SEPARATOR);
			}
			buf.append(value);
		}
		return buf.toString();
	}
	
	/**
	 * ヘッダに指定されたキーの指定された値が設定されているかチェックします.
	 * <p>
	 * ヘッダの値がカンマ区切りになっている場合、カンマで区切られた値を一つづつチェックします。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @param value 値
	 * @return ヘッダに指定されたキーの指定された値が設定されている場合true
	 */
	public static boolean containsHeader(Map<String, String> headers, String key,
			String value) {
		if (headers == null || StringUtils.isBlank(key) || 
				StringUtils.isBlank(value) || !headers.containsKey(key)) {
			return false;
		}
		Set<String> values = getHeaderValues(headers, key);
		return containsHeader(values, value);
	}
	
	/**
	 * ヘッダに指定されたキーの指定された値が設定されているかチェックします.
	 * <p>
	 * ヘッダの値がカンマ区切りになっている場合、カンマで区切られた値を一つづつチェックします。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @param value 値
	 * @return ヘッダに指定されたキーの指定された値が設定されている場合true
	 */
	public static boolean containsHeaderList(Map<String, List<String>> headers, 
			String key, String value, Option option) {
		if (headers == null || StringUtils.isBlank(key) || 
				StringUtils.isBlank(value) || !headers.containsKey(key)) {
			return false;
		}
		Set<String> values = getHeaderValuesList(headers, key, option);
		return containsHeader(values, value);
	}

	/**
	 * ヘッダに値が設定されているかチェックします.
	 * <p>
	 * ヘッダの値がカンマ区切りになっている場合、カンマで区切られた値を一つづつチェックします。
	 * </p>
	 * @param headerValue ヘッダの値
	 * @param value 値
	 * @return ヘッダに値が設定されている場合true
	 */
	public static boolean containsHeader(String headerValue,
			String value) {
		if (StringUtils.isBlank(headerValue) || StringUtils.isBlank(value)) {
			return false;
		}
		Set<String> values = getHeaderValues(headerValue);
		return containsHeader(values, value);
	}

	/**
	 * ヘッダの内容リストに指定されたキーの指定された値が設定されているかチェックします.
	 * @param values ヘッダの内容リスト
	 * @param value 値
	 * @return リストに指定されたキーの指定された値が設定されている場合true
	 */
	public static boolean containsHeader(Set<String> values, String value) {
		if (values != null && value != null) {
			for (String tmpVal : values) {
				if (value.equals(tmpVal)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * ヘッダからSet-Cookieの指定したキーの値を取得します.
	 * <p>
	 * Set-Cookie は、Set-Cookie・キー共に複数指定されている事がある。<br>
	 * ブラウザでは最後の値が有効になるため、戻り値も最後の値を返却する。
	 * </p>
	 * @param headers ヘッダ
	 * @param cookieName Cookieのキー
	 * @return Cookieの値
	 */
	public static String getSetCookieValue(Map<String, List<String>> headers,
			String cookieName) {
		String ret = null;
		if (headers != null && headers.containsKey(ReflexServletConst.HEADER_SET_COOKIE) &&
				!StringUtils.isBlank(cookieName)) {
			List<String> values = headers.get(ReflexServletConst.HEADER_SET_COOKIE);
			for (String value : values) {
				String tmp = getSetCookieValue(cookieName, value);
				if (tmp != null) {
					ret = tmp;
				}
			}
		}
		return ret;
	}
	
	private static String getSetCookieValue(String key, String value) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(value)) {
			return null;
		}
		int idx2 = value.indexOf(";");
		if (idx2 == -1) {
			idx2 = value.length();
		}
		int idx1 = value.indexOf("=");
		if (idx1 == -1) {
			idx1 = idx2;
		}
		String name = value.substring(0, idx1);
		if (key.equals(name)) {
			if (idx1 >= idx2) {
				return "";
			} else {
				return value.substring(idx1 + 1, idx2);
			}
		}
		return null;
	}
	
	/**
	 * Set-Cookieの文字列から、キーと値の部分だけを取り出して返却します.
	 * <p>
	 * Cookieの形式「Set-Cookie:{name}={value};Path={path};Expires={expires}」
	 * から、{value}の部分だけを抽出して返却します。<br>
	 * Expiresがシステム日時より過去の場合、値を空文字("")で返却します。<br>
	 * Expiresは以下のフォーマットに対応します。
	 * <ol>
	 *   <li>"EEE, dd MMM yyyy HH:mm:ss z"</li>
	 *   <li>"EEE, dd-MMM-yyyy HH:mm:ss z"</li>
	 * </p>
	 * @param value Set-Cookiesの文字列
	 * @return [0]キー、[1]値
	 */
	public static String[] getSetCookieValue(String value) {
		String[] ret = null;
		if (!StringUtils.isBlank(value)) {
			DateFormat format1 = new SimpleDateFormat(FORMAT_EXPIRE_1, Locale.US);
			DateFormat format2 = new SimpleDateFormat(FORMAT_EXPIRE_2, Locale.US);
			Date now = new Date();
			String[] valueParts = value.split(";");
			boolean isFirst = true;
			String key = null;
			String val = null;
			for (String valuePart : valueParts) {
				int idx = valuePart.indexOf("=");
				String tmpKey = null;
				String tmpVal = null;
				if (idx == -1) {
					tmpKey = valuePart;
					tmpVal = "";
				} else {
					tmpKey = valuePart.substring(0, idx);
					tmpVal = valuePart.substring(idx + 1);
				}
				if (isFirst) {
					// 先頭はkey=value
					key = tmpKey;
					val = tmpVal;
					isFirst = false;
				} else {
					// 2番目以降はCookieの属性(path, expires等)
					if (EXPIRES.equalsIgnoreCase(tmpKey)) {
						Date cookieDate = null;
						try {
							cookieDate = format1.parse(tmpVal);
						} catch (ParseException e) {
							try {
								cookieDate = format2.parse(tmpVal);
							} catch (ParseException ee) {
								logger.info("ParseException expires = " + tmpVal);
								val = "";
							}
						}
						if (cookieDate != null && now.before(cookieDate)) {
							// OK
						} else {
							val = "";
						}
					}
				}
			}
			if (!isFirst) {
				ret = new String[]{key, val};
			}
		}
		return ret;
	}
	
	/**
	 * URLに、指定したキーと値をクエリパラメータとして追加します.
	 * <p>
	 * {url}{?|&}{key}={value} の形式で追加します。<br>
	 * urlに同じキーが指定されている場合、新しい値に置き換えます。
	 * </p>
	 * @param url URL
	 * @param key キー
	 * @param value 値
	 * @return 編集したURL
	 */
	public static String addQueryParam(String url, String key, String value) {
		if (StringUtils.isBlank(key)) {
			return url;
		}
		String editUrl = StringUtils.null2blank(removeQueryParam(url, key));
		StringBuilder sb = new StringBuilder();
		sb.append(editUrl);
		sb.append(getQueryConnector(editUrl));
		sb.append(key);
		if (!StringUtils.isBlank(value)) {
			sb.append("=");
			sb.append(value);
		}
		return sb.toString();
	}
	
	/**
	 * URLから、指定したキーのクエリパラメータを削除します.
	 * @param url URL
	 * @param key キー
	 * @return 編集したURL
	 */
	public static String removeQueryParam(String url, String key) {
		if (StringUtils.isBlank(key)) {
			return null;
		}
		String[] queryAndParam = getURLandQueryString(url);
		String queryString = queryAndParam[1];
		if (queryString != null) {
			String[] queryParts = queryString.split("&");
			StringBuilder sb = new StringBuilder();
			sb.append(queryAndParam[0]);
			boolean isFirst = true;
			for (String queryPart : queryParts) {
				if (!key.equals(queryPart) &&
						!queryPart.startsWith(key + "=")) {
					if (isFirst) {
						isFirst = false;
						sb.append("?");
					} else {
						sb.append("&");
					}
					sb.append(queryPart);
				}
			}
			return sb.toString();
		}
		return url;
	}
	
	/**
	 * URLからクエリ文字列を抽出します.
	 * <p>
	 * URL(http://xxxx.xx/xxx?xxx=xx) の?以降の部分を返却します。
	 * </p>
	 * @param url URL
	 * @return [0]PathInfoまで。[1]クエリ文字列
	 */
	public static String[] getURLandQueryString(String url) {
		String[] ret = new String[2];
		if (!StringUtils.isBlank(url)) {
			int idx = url.indexOf("?");
			if (idx > -1) {
				ret[0] = url.substring(0, idx);
				ret[1] = url.substring(idx + 1);
			} else {
				ret[0] = url;
			}
		}
		return ret;
	}

	/**
	 * URLからクエリ文字列を抽出します.
	 * <p>
	 * URL(http://xxxx.xx/xxx?xxx=xx) の?以降の部分を返却します。
	 * </p>
	 * @param url URL
	 * @return クエリ文字列
	 */
	public static String getQueryString(String url) {
		String[] urlAndQuery = getURLandQueryString(url);
		return urlAndQuery[1];
	}
	
	/**
	 * URLのクエリ文字列に追加する文字を返却します.
	 * <p>
	 * 指定されたURLにクエリ文字列が無い場合は"?"、クエリ文字列が既にある場合は"&"を返します。
	 * </p>
	 * @param url URL
	 * @return クエリ文字列に追加する文字 ("?"か"&")
	 */
	public static String getQueryConnector(String url) {
		if (url != null && url.indexOf("?") > -1) {
			return "&";
		}
		return "?";
	}

}
