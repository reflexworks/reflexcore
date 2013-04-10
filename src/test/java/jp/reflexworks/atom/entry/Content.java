package jp.reflexworks.atom.entry;

import java.io.Serializable;


/**
 * コンテンツ.
 * <p>
 * HTMLなどのテキストコンテンツや、画像などのリンク先を設定します。<br>
 * テキストコンテンツを設定する場合、_$$text項目に設定してください。(XMLにシリアライズした際、contentタグの値となります。)<br>
 * 画像などのリンク先を設定する場合、_$srcにURLを設定してください。<br>
 * </p>
 */
public class Content implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public String _$xml$lang;
	public String _$xml$base;
	public String _$src;
	public String _$type;
//	@Encrypt("20110914")
	public String _$$text;

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

	public String get_$src() {
		return _$src;
	}

	public void set_$src(String _$src) {
		this._$src = _$src;
	}

	public String get_$type() {
		return _$type;
	}

	public void set_$type(String _$type) {
		this._$type = _$type;
	}

	public String get_$$text() {
		return _$$text;
	}

	public void set_$$text(String _$$text) {
		this._$$text = _$$text;
	}
	
	/*
	@Override
	public Content clone() {
		Content clone = new Content();
		clone._$xml$lang = this._$xml$lang;
		clone._$xml$base = this._$xml$base;
		clone._$src = this._$src;
		clone._$type = this._$type;
		clone._$$text = this._$$text;
		return clone;
	}
	*/

	@Override
	public String toString() {
		return "Content [" + _$$text + "]";
	}

}
