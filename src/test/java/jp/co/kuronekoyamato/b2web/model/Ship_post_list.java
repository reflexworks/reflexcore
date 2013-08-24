package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Ship_post_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 発行先 */
	public List<Ship_post> b2$ship_post;



	/**
	 * 発行先を取得します。
	 * @return 発行先
	 */
	public List<Ship_post> getB2$ship_post() {
	    return b2$ship_post;
	}

	/**
	 * 発行先を設定します。
	 * @param b2$ship_post 発行先
	 */
	public void setB2$ship_post(List<Ship_post> b2$ship_post) {
	    this.b2$ship_post = b2$ship_post;
	}
}
