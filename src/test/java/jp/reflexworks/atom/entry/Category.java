package jp.reflexworks.atom.entry;

import java.io.Serializable;

/**
 * カテゴリ.
 * <p>
 * termにプロパティ名、labelに値をセットすることで、検索項目に使用できます。<br>
 * termに型を指定することもできます。型とプロパティ名をコロンでつないでください。指定できる型は以下の通りです。
 * <ul>
 * <li>String</li>
 * <li>Integer</li>
 * <li>long</li>
 * <li>Long</li>
 * <li>float</li>
 * <li>Float</li>
 * <li>double</li>
 * <li>Double</li>
 * </ul>
 * </p>
 */
public class Category implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public String _$xml$lang;
	public String _$xml$base;
	public String _$term;
	public String _$scheme;
	public String _$label;

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

	public String get_$term() {
		return _$term;
	}

	public void set_$term(String term) {
		this._$term = term;
	}

	public String get_$scheme() {
		return _$scheme;
	}

	public void set_$scheme(String scheme) {
		this._$scheme = scheme;
	}

	public String get_$label() {
		return _$label;
	}

	public void set_$label(String label) {
		this._$label = label;
	}
	
	/*
	@Override
	public Category clone() {
		Category clone = new Category();
		clone._$xml$lang = this._$xml$lang;
		clone._$xml$base = this._$xml$base;
		clone._$term = this._$term;
		clone._$scheme = this._$scheme;
		clone._$label = this._$label;
		return clone;
	}
	*/

	@Override
	public String toString() {
		return "Category [_$term=" + _$term + ", _$scheme=" + _$scheme
				+ ", _$label=" + _$label + "]";
	}

}
