package jp.reflexworks.servlet.util;

import java.io.Serializable;

/**
 * WSSE情報.
 *   UsernameToken Username="{アカウント}", PasswordDigest="{PasswordDigest}", Nonce="{Nonce}", Created="{Created}"
 */
public class WsseAuth implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	/** ユーザ名 */
	public String username;
	/** PasswordDigest */
	public String passwordDigest;
	/** Nonce */
	public String nonce;
	/** Created */
	public String created;
	/** パスワード */
	public String password;
	/** RXIDかどうか */
	public boolean isRxid;
	/** URLパラメータへの指定かどうか */
	public boolean isQueryString;
	/** Cookieへの指定かどうか */
	public boolean isCookie;
	
	/**
	 * コンストラクタ
	 * @param username Username
	 * @param passwordDigest PasswordDigest
	 * @param nonce Nonce
	 * @param created Created (yyyy-MM-dd'T'HH:mm:ss+99:99)
	 */
	public WsseAuth(String username, String passwordDigest, String nonce, 
			String created) {
		this.username = username;
		this.passwordDigest = passwordDigest;
		this.nonce = nonce;
		this.created = created;
	}
	
	/**
	 * このオブジェクトのWSSE文字列を取得.
	 * @return WSSE文字列
	 */
	public String getWsseString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UsernameToken Username=\"");
		sb.append(username);
		sb.append("\", PasswordDigest=\"");
		sb.append(passwordDigest);
		sb.append("\", Nonce=\"");
		sb.append(nonce);
		sb.append("\", Created=\"");
		sb.append(created);
		sb.append("\"");
		return sb.toString();
	}
	
	/**
	 * このオブジェクトの文字列表現を取得.
	 * @return このオブジェクトの文字列表現
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(getWsseString());
		sb.append(" isRxid=");
		sb.append(isRxid);
		sb.append(" isQueryString=");
		sb.append(isQueryString);
		sb.append(" isCookie=");
		sb.append(isCookie);
		return sb.toString();
	}

}
