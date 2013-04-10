package jp.sourceforge.reflex.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正規表現のエスケープを行うクラス
 */
public class EscapeUtil {

	private static final String REGEX_STR = "[\\\\\\*\\+\\.\\?\\{\\}\\(\\)\\[\\]\\^\\$\\-\\|\\/]";
	private static final String REPLACE_STR = "[\\\\\\$]";
	private static final String REGEX_REPLACE_STR = "\\\\$0";

    private Pattern regexPattern;
    private Pattern replacePattern;

	public EscapeUtil() {
		regexPattern = Pattern.compile(REGEX_STR);
		replacePattern = Pattern.compile(REPLACE_STR);
	}
	
	/**
	 * 正規表現でエスケープが必要な文字列をエスケープします。
	 * @param source 文字列
	 * @return エスケープした正規表現
	 */
	public String escapeRegex(String source) {
		Matcher matcher = regexPattern.matcher(source);
		return matcher.replaceAll(REGEX_REPLACE_STR);
	}
	
	/**
	 * Replaceでエスケープが必要な文字列をエスケープします。
	 * @param source 文字列
	 * @return エスケープした正規表現
	 */
	public String escapeReplace(String source) {
		Matcher matcher = replacePattern.matcher(source);
		return matcher.replaceAll(REGEX_REPLACE_STR);
	}

	/**
	 * 一致条件、置換文字列をエスケープしてreplaceAllを実行します。
	 * @param source 文字列
	 * @param regex 正規表現
	 * @param replacement 置換文字列
	 * @return replaceAllの結果
	 */
	public String replaceAll(String source, String regex, String replacement) {
		if (source == null || regex == null || replacement == null) {
			return source;
		}
		return source.replaceAll(escapeRegex(regex), escapeReplace(replacement));
	}
	
}
