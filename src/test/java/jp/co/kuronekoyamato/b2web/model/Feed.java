package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

import jp.reflexworks.atom.feed.FeedBase;

public class Feed extends FeedBase implements Serializable {

	public String _$xmlns = "http://www.w3.org/2005/Atom";
	private static final long serialVersionUID = 1L;

	// ---- B2名前空間定義 ----
	public String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";

	// ---- B2名前空間　getter, setter　ここから ----
	public String get_$xmlns$b2() {
		return _$xmlns$b2;
	}

	public void set_$xmlns$b2(String _$xmlns$b2) {
		this._$xmlns$b2 = _$xmlns$b2;
	}

}
