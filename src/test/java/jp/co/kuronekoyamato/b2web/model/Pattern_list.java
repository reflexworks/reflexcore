package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Pattern_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** パターン */
	public List<Pattern> b2$pattern;



	/**
	 * パターンを取得します。
	 * @return パターン
	 */
	public List<Pattern> getB2$pattern() {
	    return b2$pattern;
	}

	/**
	 * パターンを設定します。
	 * @param b2$pattern パターン
	 */
	public void setB2$pattern(List<Pattern> b2$pattern) {
	    this.b2$pattern = b2$pattern;
	}
}
