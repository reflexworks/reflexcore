package jp.reflexworks.testvt.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import jp.reflexworks.atom.api.Condition;
import jp.reflexworks.atom.entry.EntryBase;

public class Entry extends EntryBase implements Serializable {

	// 独自名前空間があり、デフォルトの名前空間項目が親クラスに定義されている場合、デフォルト名前空間項目を子クラスにも定義する。

	//public String _$xmlns = "http://www.w3.org/2005/Atom";
	private static final long serialVersionUID = 1L;

	// ---- VT名前空間定義 ----
	//public String _$xmlns$vt = "http://reflexworks.jp/vt/1.0";
	public String _$xmlns$vt;

	// ---- VT名前空間　ここから ----
	public Column column;
	public Column2 column2;
	
	public String vt$item1;
	public String vt$item2;

	/**
	 * _$xmlns$vtを取得します。
	 * @return _$xmlns$vt
	 */
	public String get$xmlns$vt() {
	    return _$xmlns$vt;
	}

	/**
	 * $xmlns$vtを設定します。
	 * @param $xmlns$b2 $xmlns$vt
	 */
	public void set$xmlns$vt(String $xmlns$vt) {
	    this._$xmlns$vt = $xmlns$vt;
	}

	/**
	 * Columnを取得します。
	 * @return column
	 */
	public Column getColumn() {
	    return column;
	}

	/**
	 * columnを設定します。
	 * @param column column
	 */
	public void setColumnt(Column column) {
	    this.column = column;
	}

	public String getVt$item1() {
		return vt$item1;
	}
	public void setVt$item1(String vt$item1) {
		this.vt$item1 = vt$item1;
	}
	
	public String getVt$item2() {
		return vt$item2;
	}
	public void setVt$item2(String vt$item2) {
		this.vt$item2 = vt$item2;
	}
	
	public Object getValue(String fieldname) {
		// TODO
		return null;
	}
	public void encrypt(Object cipher) {
		// TODO
	}
	public void decrypt(Object cipher) {
		// TODO
	}
	public boolean isMatch(Condition[] conditions) {
		// TODO
		return false;
	}
	public boolean validate() throws ParseException {
		// TODO
		return false;
	}
	@Override
	public boolean validate(String ucode, List groups) throws ParseException {
		// TODO
		return false;
	}
	@Override
	public void maskprop(String ucode, List groups) {
		// TODO
	}
	public int getsize() {
		// TODO
		return -1;
	}

}
