package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 implements HashFunction {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	public int hash(Object key)  {
		MD5 d = new MD5();
		String hex = d.getStringDigest((String)key).substring(0, 4);
		return Integer.parseInt(hex, 16);
	}

	public String getStringDigest(String data) {	// 文字列からダイジェストを生成する
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(data.getBytes("UTF-8"));

			StringBuffer buf = new StringBuffer();
			for(int i= 0; i< digest.length; i++){
				int d = digest[i];
				if (d < 0) {
					d += 256;
				}
				if (d < 16) {
					buf.append("0");
				}
				buf.append(Integer.toString(d, 16));
			}
			String result = buf.toString();
			return result;

		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
		return "0000";
	}

}
