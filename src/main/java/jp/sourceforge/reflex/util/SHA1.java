package jp.sourceforge.reflex.util;

public class SHA1 {
	
	/** エンコード */
	public static final String ENCODING = "UTF-8";
	/** ハッシュ関数 */
	public static final String HASH_ALGORITHM = "SHA-1";

	public static String hashString(String str) {
		return Hash.hashString(str, HASH_ALGORITHM);
	}

	public static String hashString(byte[] v) {
		return Hash.hashString(v, HASH_ALGORITHM);
	}

	public static byte[] hash(String str) {
		return Hash.hash(str, HASH_ALGORITHM);
	}
	
	public static byte[] hash(byte[] v) {
		return Hash.hash(v, HASH_ALGORITHM);
	}

}
