package jp.sourceforge.reflex.util;

public class BinaryUtil {

	/**
	 * バイト配列を16進数文字列に変換します.
	 * @param data バイト配列
	 * @return 16進数文字列
	 */
	public static String bin2hex(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for (byte b : data) {
			String s = Integer.toHexString(0xff & b);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 16進数文字列をバイト配列に変換します.
	 * @param hex 16進数文字列
	 * @return バイト配列
	 */
	public static byte[] hex2bin(String hex) {
		byte[] bytes = new byte[hex.length() / 2];
		for (int index = 0; index < bytes.length; index++) {
			bytes[index] = (byte)Integer.parseInt(
					hex.substring(index * 2, (index + 1) * 2), 16);
		}
		return bytes;
	}

	/**
	 * バイト配列を数値表現の文字列に変換します.
	 * <p>
	 * デバッグプリント用です。
	 * </p>
	 * @param bin バイト配列
	 * @return 数値表現の文字列 ("[-128, -128, ... ]" 形式)
	 */
	public static String printInt(byte[] bin) {
		if (bin == null) {
			return null;
		}
		StringBuilder buf = new StringBuilder();
		buf.append("[");
		boolean isFirst = true;
		for (byte b : bin) {
			if (isFirst) {
				isFirst = false;
			} else {
				buf.append(", ");
			}
			buf.append(b);
		}
		buf.append("]");
		return buf.toString();
	}

}
