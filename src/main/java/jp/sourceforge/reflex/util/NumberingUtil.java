package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import jp.reflexworks.servlet.ReflexServletConst;
import jp.reflexworks.servlet.util.AuthTokenUtil;

import org.apache.commons.codec.binary.Base64;

/**
 * 採番クラス
 */
public class NumberingUtil {
	
	/**
	 * 番号を取得します.
	 * <p>
	 * System.currentTimeMillis()を実行し、値を返却します。
	 * </p>
	 * @return 番号
	 */
	public static long getNumber() {
		return System.currentTimeMillis();
	}
	
	/**
	 * 番号に1を加算します.
	 * @param num 番号
	 * @return 加算後の番号
	 */
	public static long correct(long num) {
		return num + 1;
	}
	
	/**
	 * 指定された範囲の中でランダムな値を返します.
	 * @param start 範囲開始
	 * @param end 範囲終了
	 * @return ランダムな数値
	 */
	public static int random(int start, int end) {
		int m = start - 1;
		int n = end + 1;
		double i = Math.floor(Math.random() * (m - n + 1)) + n;
		return (int)i;
	}

	/**
	 * ランダムな文字列を生成します
	 * @param len バイト数
	 * @return 生成された文字列
	 */
	public static String randomString(int len) {
		byte[] passB = new byte[len];
		String pass = null;
		try {
			SecureRandom.getInstance(AuthTokenUtil.RANDOM_ALGORITHM).nextBytes(passB);
			pass = new String(Base64.encodeBase64(passB), ReflexServletConst.ENCODING);
			pass = pass.substring(0, len);

		} catch (NoSuchAlgorithmException e) {
			// Do nothing.
		} catch (UnsupportedEncodingException e) {
			// Do nothing.
		}
		
		return pass;
	}
}
