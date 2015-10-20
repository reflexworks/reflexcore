package jp.reflexworks.servlet.util;

import java.io.Serializable;

public class WsseAuth implements Serializable {

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
	
	public WsseAuth(String username, String passwordDigest, String nonce, 
			String created) {
		this.username = username;
		this.passwordDigest = passwordDigest;
		this.nonce = nonce;
		this.created = created;
	}
	
	@Override
	public String toString() {
		return "WsseAuth [username=" + username + ", passwordDigest="
				+ passwordDigest + ", nonce=" + nonce + ", created="
				+ created + ", isRxid=" + isRxid
				+ "]";
	}

}
