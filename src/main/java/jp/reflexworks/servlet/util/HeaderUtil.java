package jp.reflexworks.servlet.util;

import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.LinkedHashSet;

import jp.sourceforge.reflex.util.StringUtils;

public class HeaderUtil {
	
	public static final String VALUE_SEPARATOR = ",";
	
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
	 * 値がカンマ区切りで定義されている場合、内容をリストにして返却します。<br>
	 * 値はトリミングして返却します。
	 * </p>
	 * @param headers ヘッダ
	 * @param key キー
	 * @return カンマ指定された値をリストにして返却
	 */
	public static Set<String> getHeaderValues(Map<String, String> headers,
			String key) {
		if (headers == null || StringUtils.isBlank(key)) {
			return null;
		}
		String val = headers.get(key);
		if (val == null) {
			val = headers.get(key.toLowerCase(Locale.ENGLISH));
		}
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
		if (values != null) {
			for (String tmpVal : values) {
				if (value.equals(tmpVal)) {
					return true;
				}
			}
		}
		return false;
	}

}