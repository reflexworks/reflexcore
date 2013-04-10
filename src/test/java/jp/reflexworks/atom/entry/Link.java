package jp.reflexworks.atom.entry;

import java.io.Serializable;

/**
 * Link.
 * <p>
 * linkタグは、rel属性により様々な役割を持ちます。<br>
 * <br>
 * <b>キー</b><br><br>
 * このエントリーのキーを、rel="self"の、href属性に設定します。<br>
 * <br>
 * <b>エイリアス</b><br><br>
 * rel="alternate"の、href属性に設定できます。複数指定可能です。<br>
 * エイリアスで検索したり、ACLを適用させることができます。<br>
 * エイリアスで検索した場合、キーであるrel="self"のhref属性にエイリアスの値が設定されます。ただしidタグは本体のキーが使用されます。<br>
 * <br>
 * <b>コンテンツ</b><br><br>
 * rel="related"の場合、href属性に外部コンテンツのURLを指定します。<br>
 * GETでURLにリダイレクトすることができます。<br>
 * <br>
 * <b>Blobstore　(GAE用)</b><br><br>
 * rel="related"で、type="blobstore"の場合、Blobstoreのデータを表します。<br>
 * href属性にBlobKeyの文字列、title属性に名前を指定できます。<br>
 * <br>
 * <b>WebHook</b><br><br>
 * rel="via"、type="webhook"の場合、href属性に指定されたURLにリクエストします。<br>
 * リクエストのタイミングは、エントリーの登録・更新後です。<br>
 * title属性にGET、POST、PUT、DELETEが指定できます。この場合、配下のエントリーが検索・登録・更新・削除された場合リクエストが実行されます。自身の登録・更新時には実行されません。<br>
 * hrefのURLに?async={数字}パラメータが設定されている場合、{数字}秒後にリクエストを実行します。<br>
 * <br>
 * <b>JavaScript</b><br><br>
 * rel="via"、type="text/javascript"の場合、href="{キー}#{関数名}"でJavascript実行を指定できます。<br>
 * Javascriptはキーで指定されたエントリーのcontentに格納されている必要があります。<br>
 * title属性にGET、POST、PUTが指定できます。この場合、配下のエントリーに対して実行されます。自身には実行されません。<br>
 * Javascriptはエントリー登録・更新後に実行され、実行結果をデータストアに格納されます。<br>
 * JavascriptがGET指定されている場合、検索後に実行され、実行結果をレスポンスデータに設定します。<br>
 * Javascript実行時、rel="related"、type="text/javascript"で指定されたエントリーのcontentをJavascriptのコードに加えることができます。<br>
 * </p>
 */
public class Link implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String REL_SELF = "self";
	public static final String REL_ALTERNATE = "alternate";
	public static final String REL_RELATED = "related";
	public static final String REL_VIA = "via";
	public static final String REL_ENCLOSURE = "enclosure";
	public static final String NUMBERING = "#";

	public String _$xml$lang;
	public String _$xml$base;
	public String _$href;
	public String _$rel;
	public String _$type;
	public String _$hreflang;
	public String _$title;
	public String _$length;

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

	public String get_$href() {
		return _$href;
	}

	public void set_$href(String _$href) {
		this._$href = _$href;
	}

	public String get_$rel() {
		return _$rel;
	}

	public void set_$rel(String _$rel) {
		this._$rel = _$rel;
	}

	public String get_$type() {
		return _$type;
	}

	public void set_$type(String _$type) {
		this._$type = _$type;
	}

	public String get_$hreflang() {
		return _$hreflang;
	}

	public void set_$hreflang(String _$hreflang) {
		this._$hreflang = _$hreflang;
	}

	public String get_$title() {
		return _$title;
	}

	public void set_$title(String _$title) {
		this._$title = _$title;
	}

	public String get_$length() {
		return _$length;
	}

	public void set_$length(String _$length) {
		this._$length = _$length;
	}
	
	/*
	@Override
	public Link clone() {
		Link clone = new Link();
		clone._$xml$lang = this._$xml$lang;
		clone._$xml$base = this._$xml$base;
		clone._$href = this._$href;
		clone._$rel = this._$rel;
		clone._$type = this._$type;
		clone._$hreflang = this._$hreflang;
		clone._$title = this._$title;
		clone._$length = this._$length;
		return clone;
	}
	*/

	@Override
	public String toString() {
		return "Link [_$rel=" + _$rel + ", _$href=" + _$href + ", _$title="
				+ _$title + ", _$type=" + _$type + "]";
	}

}
