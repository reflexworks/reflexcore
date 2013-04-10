package jp.sourceforge.reflex.util;

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
	public static long collect(long num) {
		return num + 1;
	}

}
