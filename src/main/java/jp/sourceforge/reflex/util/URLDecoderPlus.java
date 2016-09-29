package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLDecoderPlus {

	public static final String ENCODING = "UTF-8";

	/** ロガー. */
	private static Logger logger = Logger.getLogger(URLDecoderPlus.class.getName());

	/**
	 * URLデコード
	 * <p>
	 * java.net.URLDecoderでURLデコードすると、+はスペースに変換されるため、
	 * +を"%2b"に置き換えてデコードを実行します。
	 * </p>
	 * @param source 変換前文字列
	 * @return デコード後の文字列
	 */
	public static String urlDecode(String source) {
		return urlDecode(source, ENCODING);
	}
	
	/**
	 * URLデコード
	 * <p>
	 * java.net.URLDecoderでURLデコードすると、+はスペースに変換されるため、
	 * +を"%2b"に置き換えてデコードを実行します。
	 * </p>
	 * @param source 変換前文字列
	 * @param encoding 文字コード
	 * @return デコード後の文字列
	 * @throws IllegalArgumentException デコードエラー
	 */
	public static String urlDecode(String source, String encoding) {
		String ret = source;	// エラーの場合は引数の文字列を返す。
		// URLデコードすると、+はスペースに変換されるため、+を"%2b"に置き換えておく。
		if (source != null) {
			String tmpSource = source.replaceAll("\\+", "%2b");
			try {
				ret = URLDecoder.decode(tmpSource, encoding);
			} catch (UnsupportedEncodingException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}
		return ret;
	}

}
