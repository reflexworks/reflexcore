package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SHA256 {
	
	/** エンコード */
	public static final String ENCODING = "UTF-8";
	/** ハッシュ関数 */
	public static final String HASH_ALGORITHM = "SHA-256";

	public static String hashString(String str) {
		byte[] digest = hash(str);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				// Do nothing.
			}
		}
		return null;
	}

	public static String hashString(byte[] v) {
		byte[] digest = hash(v);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				// Do nothing.
			}
		}
		return null;
	}

	public static byte[] hash(String str) {
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			byte[] v = str.getBytes(ENCODING);
			return hash(v);

		} catch (UnsupportedEncodingException e) {
			// Do nothing.
		}
		return null;
	}
	
	public static byte[] hash(byte[] v) {
		try {
			MessageDigest md = MessageDigest.getInstance(HASH_ALGORITHM);
			md.update(v);
			return md.digest();

		} catch (NoSuchAlgorithmException e) {
			// Do nothing.
		}
		return null;
	}

}
