package jp.reflexworks.atom.feed;

import java.io.Serializable;

public class Author implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public String _$xml$lang;
	public String _$xml$base;
	public String name;
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
	public Author clone() {
		Author clone = new Author();
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
		return "Author [uri=" + uri + ", email=" + email + ", name=" + name + "]";
	}

}
