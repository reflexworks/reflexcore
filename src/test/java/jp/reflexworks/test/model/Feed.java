package jp.reflexworks.test.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import jp.reflexworks.atom.entry.FeedBase;

public class Feed extends FeedBase implements Serializable {

	private static final long serialVersionUID = 1L;

	public String _$xmlns = "http://www.w3.org/2005/Atom";

	// ---- 名前空間定義 ----
	public String _$xmlns$vt = "http://reflexworks.jp/test/1.0";

	public String get_$xmlns() {
		return _$xmlns;
	}
	public void set_$xmlns(String _$xmlns) {
		this._$xmlns = _$xmlns;
	}
	public String get_$xmlns$vt() {
		return _$xmlns$vt;
	}
	public void set_$xmlns$vt(String _$xmlns$vt) {
		this._$xmlns$vt = _$xmlns$vt;
	}
	
	@Override
	public boolean validate(String ucode, List groups) throws ParseException {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public void maskprop(String ucode, List groups) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

}
