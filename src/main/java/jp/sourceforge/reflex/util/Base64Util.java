package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

import jp.reflexworks.servlet.ReflexServletConst;

/**
 * Base64エンコードの文字列から記号を除去するユーティリティ.
 */
public class Base64Util {
	
	/** Base64に使用される記号の正規表現 (+, /, =) */
	public static final String REGEX_BASE64_SYMBOL = "\\/|\\+|=";
	
	/**
	 * Base64エンコードの文字列から記号を除去する.
	 * @param bytes バイト配列
	 * @return Base64エンコードし、記号を除去した文字列
	 */
	public static String encodeAndRemoveSymbol(byte[] bytes) {
		return encodeAndReplace(bytes, REGEX_BASE64_SYMBOL, "");
	}
	
	/**
	 * Base64エンコードの文字列から記号を除去する.
	 * @param str Base64エンコード文字列
	 * @return Base64エンコードし、記号を除去した文字列
	 */
	public static String removeSymbol(String str) {
		return replace(str, REGEX_BASE64_SYMBOL, "");
	}

	/**
	 * Base64エンコードの文字列から記号を指定の値に変換する.
	 * @param bytes バイト配列
	 * @param regex 除去対象正規表現
	 * @param replacement 変換文字
	 * @return Base64エンコードし、記号を変換した文字列
	 */
	public static String encodeAndReplace(byte[] bytes, String regex,
			String replacement) {
		try {
			String str = new String(Base64.encodeBase64(bytes), 
					ReflexServletConst.ENCODING);
			
			// 記号を変換
			return replace(str, regex, replacement);
			
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 変換処理.
	 * @param str 文字列
	 * @param regex 変換対象
	 * @param replacement 変換文字
	 * @return 変換後の文字列
	 */
	private static String replace(String str, String regex, String replacement) {
		if (str != null && regex != null && replacement != null) {
			// 記号を変換
			return str.replaceAll(regex, replacement);
		}
		return str;
	}

}
