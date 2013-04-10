package jp.reflexworks.atom.entry;

import java.io.Serializable;


/**
 * 認証・認可情報定義.
 * <p>
 * <b>WSSE指定</b><br><br>
 * /_user/{username} をキーとするエントリーのｃontributorタグのuriタグに、以下の書式でACLを設定します。
 * <ul>
 * <li>urn:virtual-tech.net:wsse:{username},{password}</li>
 * </ul>
 * <br>
 * <b>ACL指定</b><br><br>
 * uriタグに、以下の書式でACLを設定します。<br>
 * <ul>
 * <li>urn:virtual-tech.net:acl:{username},{C|R|U|D|A|E}</li>
 * </ul><br>
 * このACLは、配下のエントリーに対し有効です。<br>
 * 配下のエントリーにACLの設定がある場合、上位階層で設定されたACLは全て無効となり、配下のACLのみ有効となります。<br>
 * <ul>
 * <li><b>username</b><br><br>
 * ログインユーザを指定します。先頭と末尾にワイルドカード(*)が指定できます。<br>
 * *のみを指定した場合、ログインしていないユーザを含むすべてのユーザに対しACLが適用されます。<br>
 * ?を指定した場合、ログインしているすべてのユーザに対しACLが適用されます。<br>
 * <br></li>
 * <li><b>ACLの種類</b><br><br>
 * 以下の種類があります。複数指定可能です。<br>
 * <ul>
 * <li>C : 登録処理を許可</li>
 * <li>R : 検索処理を許可</li>
 * <li>U : 更新処理を許可</li>
 * <li>D : 削除処理を許可</li>
 * <li>A : 管理者 (CRUD権限に加え、権限の付与および参照が可能)</li>
 * <li>E : 外部サービス呼び出しからのみデータ操作可で、Reflexサービスから直接データ操作が不可。</li>
 * </ul>
 * </li>
 * </p>
 */
public class Contributor implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public String _$xml$lang;
	public String _$xml$base;
	public String name;
//	@Encrypt("20111110")
	public String uri;
	public String email;

	public String get_$xml$lang() {
		return _$xml$lang;
	}

	public void set_$xml$lang(String _$xml$lang) {
		this._$xml$lang = _$xml$lang;
	}

	public String get_$xml$base() {
		return _$xml$base;
	}

	public void set_$xml$base(String _$xml$base) {
		this._$xml$base = _$xml$base;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/*
	@Override
	public Contributor clone() {
		Contributor clone = new Contributor();
		clone._$xml$lang = this._$xml$lang;
		clone._$xml$base = this._$xml$base;
		clone.name = this.name;
		clone.uri = this.uri;
		clone.email = this.email;
		return clone;
	}
	*/

	@Override
	public String toString() {
		return "Contributor [uri=" + uri + ", email=" + email + ", name=" + name + "]";
	}

}
