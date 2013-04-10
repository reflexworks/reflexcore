package jp.reflexworks.atom.source;

import java.io.Serializable;

public class Link implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

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
		return "Link [_$href=" + _$href + ", _$hreflang=" + _$hreflang
				+ ", _$length=" + _$length + ", _$rel=" + _$rel + ", _$title="
				+ _$title + ", _$type=" + _$type + "]";
	}

}
