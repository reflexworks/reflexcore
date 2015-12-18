package jp.sourceforge.reflex.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MD5 implements HashFunction {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public String hash(Object key)  {
		MD5 d = new MD5();
		return d.getStringDigest((String)key);
	}

	public String getStringDigest(String data) {	// 文字列からダイジェストを生成する
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(data.getBytes("UTF-8"));

			StringBuilder sb = new StringBuilder();
			for(int i= 0; i< digest.length; i++){
				int d = digest[i];
				if (d < 0) {
					d += 256;
				}
				if (d < 16) {
					sb.append("0");
				}
				sb.append(Integer.toString(d, 16));
			}
			String result = sb.toString();
			return result;

		} catch (NoSuchAlgorithmException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
		return "0000";
	}

}
