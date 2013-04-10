package jp.sourceforge.reflex.util;

public class HtmlEncoder {

	/**
	 * HTMLエンコード処理.
	 *   &,",<,>の置換
	 */
	public static String encode(String str) {
		if (str == null) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '<') {
				sb.append("&lt;");
			} else if (str.charAt(i) == '>') {
				sb.append("&gt;");
			} else if (str.charAt(i) == '"') {
				sb.append("&quot;");
			} else if (str.charAt(i) == '&') {
				sb.append("&amp;");
			} else {
				sb.append(str.charAt(i));
			}
		}
		return sb.toString();
	}

}
