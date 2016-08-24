package jp.sourceforge.reflex.util;

public class SHA1 {
	
	/** エンコード */
	public static final String ENCODING = "UTF-8";
	/** ハッシュ関数 */
	public static final String HASH_ALGORITHM = "SHA-1";

	public static String hashString(String str) {
		/*
		byte[] digest = hash(str);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
		*/
		return Hash.hashString(str, HASH_ALGORITHM);
	}

	public static String hashString(byte[] v) {
		/*
		byte[] digest = hash(v);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
		*/
		return Hash.hashString(v, HASH_ALGORITHM);
	}

	public static byte[] hash(String str) {
		/*
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			byte[] v = str.getBytes(ENCODING);
			return hash(v);

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		*/
		return Hash.hash(str, HASH_ALGORITHM);
	}
	
	public static byte[] hash(byte[] v) {
		/*
		try {
			MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
			md.update(v);
			return md.digest();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
		*/
		return Hash.hash(v, HASH_ALGORITHM);
	}

}
