package jp.reflexworks.atom.mapper;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import jp.reflexworks.atom.api.AtomConst;
import jp.reflexworks.atom.entry.Contributor;
import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.entry.FeedBase;
import jp.sourceforge.reflex.util.StringUtils;

/**
 * 暗号化ユーティリティ.
 * <p>
 * このクラスはフィールド変数に定義せず、都度生成してください。
 * </p>
 */
public final class CipherUtil {
	
    private Cipher cipher;
	private static Logger logger = Logger.getLogger(CipherUtil.class.getName());
	private static final String ALGORITHM = "AES";
	private static final String HASH = "MD5";

	/**
	 * コンストラクタ
	 */
	public CipherUtil() {
		try {
			this.cipher = Cipher.getInstance(ALGORITHM);
		} catch (GeneralSecurityException e) {
			logger.log(Level.WARNING, e.getMessage(), e);
		}
	}

	/**
	 * コンストラクタ
	 * @param cipher 暗号化アルゴリズム
	 */
	public CipherUtil(Cipher cipher) {
		if (cipher != null) {
			this.cipher = cipher;
		} else {
			try {
				this.cipher = Cipher.getInstance(ALGORITHM);
			} catch (GeneralSecurityException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}
	}
	
	public Cipher getInstance() {
		return this.cipher;
	}
	
	/**
	 * Feed内Entryの暗号化
	 * 引数のFeedが暗号化されるので、この処理の後元のデータを使用する場合はdecryptを実行してください。
	 * @param sourceFeed
	 * @return 暗号化されたFeed
	 * @throws GeneralSecurityException
	 */
	public FeedBase encrypt(FeedBase sourceFeed) 
	throws GeneralSecurityException {
		if (sourceFeed == null || sourceFeed.getEntry() == null ||
				sourceFeed.getEntry().isEmpty()) {
			return sourceFeed;
		}

		//List<EntryBase> entries = new ArrayList<EntryBase>();
		for (EntryBase sourceEntry : sourceFeed.getEntry()) {
			//entries.add(encrypt(sourceEntry));
			encrypt(sourceEntry);
		}
		//sourceFeed.setEntry(entries);
		return sourceFeed;
	}
	
	/**
	 * Feed内Entryの復号.
	 * 引数のFeed内Entry自身が復号化されます。
	 * @param sourceFeed
	 * @return 復号化されたFeed
	 * @throws GeneralSecurityException
	 */
	public FeedBase decrypt(FeedBase sourceFeed) 
	throws GeneralSecurityException {
		if (sourceFeed == null || sourceFeed.getEntry() == null ||
				sourceFeed.getEntry().isEmpty()) {
			return sourceFeed;
		}

		//List<EntryBase> entries = new ArrayList<EntryBase>();
		for (EntryBase sourceEntry : sourceFeed.getEntry()) {
			//entries.add(decrypt(sourceEntry));
			decrypt(sourceEntry);
		}
		//sourceFeed.setEntry(entries);
		return sourceFeed;
	}

	/**
	 * Entryのうち、@Encryptアノテーションが付加されているString項目と、
	 * テンプレートの暗号化指定項目について暗号化し返却します.
	 * 引数のエントリー自身が暗号化されます。
	 * @param sourceEntry エントリ
	 * @return 指定された項目のみ暗号化したEntry
	 * @throws GeneralSecurityException
	 */
	public EntryBase encrypt(EntryBase sourceEntry) 
	throws GeneralSecurityException {
		if (sourceEntry == null) {
			return null;
		}
		sourceEntry.encrypt(cipher);
		return sourceEntry;
	}
	
	/**
	 * ATOM標準項目のみ暗号化します.
	 * <p>
	 * サーバ起動中にのみ使用します.
	 * </p>
	 * @param sourceEntry エントリー
	 * @return 暗号化されたエントリー
	 */
	public EntryBase encryptAtom(EntryBase sourceEntry, String secretkey)
			throws GeneralSecurityException {
		if (sourceEntry == null) {
			return null;
		}

		// Contributor
		if (sourceEntry.getContributor() != null) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					secretkey, sourceEntry.id));
			for (Contributor contributor : sourceEntry.getContributor()) {
				if (contributor != null && !StringUtils.isBlank(contributor.uri)) {
					String str = encryptProc(contributor.uri, key);
					contributor.uri = str;
				}
			}
		}
		
		// rights
		if (!StringUtils.isBlank(sourceEntry.rights)) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					secretkey, sourceEntry.id));
			String str = encryptProc(sourceEntry.rights, key);
			sourceEntry.rights = str;
		}
		
		return sourceEntry;
	}
	
	/**
	 * Entryのうち、@Encryptアノテーションが付加されているString項目と、
	 * テンプレートの暗号化指定項目について復号化し返却します.
	 * 引数のエントリー自体が暗号化されます。
	 * @param sourceEntry エントリ
	 * @return 指定された項目のみ復号化したEntry
	 * @throws GeneralSecurityException
	 */
	public EntryBase decrypt(EntryBase sourceEntry) 
	throws GeneralSecurityException {
		if (sourceEntry == null) {
			return null;
		}
		sourceEntry.decrypt(cipher);
		return sourceEntry;
	}
	
	/**
	 * ATOM標準項目のみ復号化します.
	 * <p>
	 * サーバ起動中にのみ使用します.
	 * </p>
	 * @param sourceEntry エントリー
	 * @return 復号化されたエントリー
	 */
	public EntryBase decryptAtom(EntryBase sourceEntry, String secretkey)
			throws GeneralSecurityException {
		if (sourceEntry == null) {
			return null;
		}

		// Contributor
		if (sourceEntry.getContributor() != null) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					secretkey, sourceEntry.id));
			for (Contributor contributor : sourceEntry.getContributor()) {
				if (contributor != null && !StringUtils.isBlank(contributor.uri)) {
					String str = decryptProc(contributor.uri, key);
					contributor.uri = str;
				}
			}
		}
		
		// rights
		if (!StringUtils.isBlank(sourceEntry.rights)) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					secretkey, sourceEntry.id));
			String str = decryptProc(sourceEntry.rights, key);
			sourceEntry.rights = str;
		}
		
		return sourceEntry;
	}

	/**
	 * EntryのATOM標準項目の復号化を行います.
	 * <p>
	 * GAE版の初期処理で使用します。
	 * </p>
	 * @param sourceEntry エントリ
	 * @return 指定された項目のみ復号化したEntry
	 * @throws GeneralSecurityException
	 */
	/*
	public EntryBase decryptBase(EntryBase sourceEntry) 
	throws GeneralSecurityException {
		if (sourceEntry == null) {
			return sourceEntry;
		}

		// rights
		if (!StringUtils.isBlank(sourceEntry.rights)) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					EntryBase.RIGHTS_SECRETKEY, sourceEntry.id));
			String decryptStr = doDecryption(sourceEntry.rights, key);
			sourceEntry.rights = decryptStr;
		}
		// contributor uri
		if (sourceEntry.contributor != null && !sourceEntry.contributor.isEmpty()) {
			SecretKey key = createSecretKey(createSecretKeyStr(
					Contributor.URI_SECRETKEY, sourceEntry.id));
			for (Contributor contributor : sourceEntry.contributor) {
				if (!StringUtils.isBlank(contributor.uri)) {
					String decryptStr = doDecryption(contributor.uri, key);
					contributor.uri = decryptStr;
				}
			}
		}
		
		return sourceEntry;
	}
	*/
	
	/*
	private EntryBase cipher(EntryBase sourceEntry, boolean isEncrypt) 
	throws GeneralSecurityException {
		if (encryptSet == null || encryptSet.isEmpty()) {
			return sourceEntry;
		}

		for (CipherInfo cipherInfo : encryptSet) {
			if (cipherInfo.secretKey == null) {
				continue;
			}
			
			cipherEachField(cipherInfo, 0, sourceEntry, isEncrypt);
		}
		return sourceEntry;
	}
	*/
	
	/*
	private void cipherEachField(CipherInfo cipherInfo, int idx, Object obj,
			boolean isEncrypt) 
	throws GeneralSecurityException {
		if (obj != null) {
			int nextIdx = idx;
			if (FieldMapper.isCollection(obj)) {
				Collection collection = (Collection)obj;
				if (!collection.isEmpty()) {
					Iterator it = collection.iterator();
					while (it.hasNext()) {
						Object partObj = it.next();
						cipherEachField(cipherInfo, nextIdx, partObj, isEncrypt);
					}
				}
					
			} else {
				nextIdx++;
				Field fld = cipherInfo.fields[idx];
				Object nextObj = fieldMapper.getValue(obj, FieldMapper.getGetter(fld));
				if (cipherInfo.fields.length == idx + 1) {
					if (nextObj instanceof String) {
						// encrypt or decrypt
						String value = (String)nextObj;
						String cipherValue = null;
						if (isEncrypt) {
							cipherValue = doEncryption(value, cipherInfo.getSecretKey());
						} else {
							cipherValue = doDecryption(value, cipherInfo.getSecretKey());
						}
	
						fieldMapper.setValue(obj, String.class, cipherValue, 
								FieldMapper.getSetter(fld));
					}
					
				} else {
					cipherEachField(cipherInfo, nextIdx, nextObj, isEncrypt);
				}
			}
		}
	}
	*/
	
	/*
	 * 暗号化
	 */
	private String encryptProc(String value, SecretKey sk) 
	throws GeneralSecurityException {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		try {
			byte[] valueByte = value.getBytes(AtomConst.ENCODING);
			byte[] encByte = encryptProc(valueByte, sk);
			String enc = new String(Base64.encodeBase64(encByte), AtomConst.ENCODING);
			return enc;
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}
		return null;
	}
	
	/*
	 * 暗号化
	 */
	private byte[] encryptProc(byte[] value, SecretKey sk) 
	throws GeneralSecurityException {
		if (value == null) {
			return null;
		}
		cipher.init(Cipher.ENCRYPT_MODE, sk);
		// 暗号化
		byte[] encrypted = cipher.doFinal(value);
		return encrypted;
	}
	
	/*
	 * 復号化
	 */
	private String decryptProc(String value, SecretKey sk) 
	throws GeneralSecurityException {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		try {
			byte[] valueByte = Base64.decodeBase64(value.getBytes(AtomConst.ENCODING));
			byte[] encByte = decryptProc(valueByte, sk);
			String enc = new String(encByte, AtomConst.ENCODING);
			return enc;
		} catch (UnsupportedEncodingException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}
		return null;
	}

	/*
	 * 復号化
	 */
	private byte[] decryptProc(byte[] value, SecretKey sk) 
	throws GeneralSecurityException {
		if (value == null) {
			return null;
		}
		cipher.init(Cipher.DECRYPT_MODE, sk);
		byte[] decrypted = cipher.doFinal(value);
		return decrypted;
	}
	
	/**
	 * 暗号化.
	 * @param value 暗号対象文字列
	 * @param sk SecretKey
	 * @return 暗号化された文字列
	 */
	public static String doEncrypt(String value, String sk, Object cipher) {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		CipherUtil cp = new CipherUtil((Cipher)cipher);
		SecretKey key = createSecretKey(sk);
		try {
			return cp.encryptProc(value, key);
		} catch (GeneralSecurityException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
			return null;
		}
	}
	
	/**
	 * 復号化.
	 * @param value 暗号化された文字列
	 * @param sk SecretKey
	 * @return 復号化された文字列
	 */
	public static String doDecrypt(String value, String sk, Object cipher) {
		if (StringUtils.isBlank(value)) {
			return value;
		}
		CipherUtil cp = new CipherUtil((Cipher)cipher);
		SecretKey key = createSecretKey(sk);
		try {
			return cp.decryptProc(value, key);
		} catch (GeneralSecurityException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
			return null;
		}
	}
	
	public static String createSecretKeyStr(String pass, String id) {
		StringBuilder buf = new StringBuilder();
		if (pass != null) {
			buf.append(pass);
		}
		if (id != null) {
			buf.append(id);
		}
		return buf.toString();
	}
	
	public static SecretKey createSecretKey(String sk) {
		if (StringUtils.isBlank(sk)) {
			return null;
		}
		return new SecretKeySpec(digest(sk), ALGORITHM);
	}

	private static byte[] digest(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance(HASH); //128ビットのハッシュ
			md.update(str.getBytes(AtomConst.ENCODING));
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}

		return md.digest();
	}

	/*
	public static class CipherInfo implements Serializable {

		private static final long serialVersionUID = 1L;

		//public Field[] fields;
		private String keyString;
		private SecretKey secretKey;
		
		//public CipherInfo(Field[] fields, String keyString) {
		public CipherInfo(String keyString) {
			//this.fields = fields;
			this.keyString = keyString;
			try {
				this.secretKey = createSecretKey(keyString);
			} catch (GeneralSecurityException e) {
				throw new IllegalArgumentException(e);
			}
		}
		
		@Override
		public String toString() {
			//return fields[fields.length - 1] + " " + keyString;
			return keyString;
		}
		
		public SecretKey getSecretKey() {
			return secretKey;
		}
		
		// 秘密鍵を準備
		private SecretKey createSecretKey(String keyString) 
		throws GeneralSecurityException {
			String editKeyString = editSecretKey(keyString);
			byte[] keyByte = editKeyString.getBytes();
			DESKeySpec dk = new DESKeySpec(keyByte);
			Arrays.fill(keyByte, (byte)0x00); // セキュリティ情報を上書きして削除
			SecretKeyFactory kf = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey sk = kf.generateSecret(dk);
			return sk;
		}
		
		// 秘密鍵を8桁に補正
		private String editSecretKey(String keyString) {
			String ret = null;
			if (keyString != null) {
				int len = keyString.length();
				if (len == 8) {
					ret = keyString;
				} else if (len > 8) {
					ret = keyString.substring(0, 8);
				} else {
					ret = keyString + DEFAULT_KEY.substring(0, 8 - len);
				}
			} else {
				ret = DEFAULT_KEY;
			}
			return ret;
		}

	}
	*/
}
