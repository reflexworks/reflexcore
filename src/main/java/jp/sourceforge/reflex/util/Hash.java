package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class Hash {
	
	/** エンコード */
	public static final String ENCODING = "UTF-8";

	public static String hashString(String str, String algorithm) {
		byte[] digest = hash(str, algorithm);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	public static String hashString(byte[] v, String algorithm) {
		byte[] digest = hash(v, algorithm);
		if (digest != null) {
			try {
				return new String(Base64.encodeBase64(digest), ENCODING);
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}

	public static byte[] hash(String str, String algorithm) {
		if (StringUtils.isBlank(str) || StringUtils.isBlank(algorithm)) {
			return null;
		}
		try {
			byte[] v = str.getBytes(ENCODING);
			return hash(v, algorithm);

		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static byte[] hash(byte[] v, String algorithm) {
		try {
			MessageDigest md = MessageDigest.getInstance(algorithm);
			md.update(v);
			return md.digest();

		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

}
