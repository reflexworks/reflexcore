package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Hatsu_shipped implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 一般 */
	public String b2$standard;
	/** 冷蔵 */
	public String b2$cold;
	/** 冷凍 */
	public String b2$freezing;
	/**
	 * 一般を取得します。
	 * @return 一般
	 */
	public String getB2$standard() {
	    return b2$standard;
	}
	/**
	 * 一般を設定します。
	 * @param b2$standard 一般
	 */
	public void setB2$standard(String b2$standard) {
	    this.b2$standard = b2$standard;
	}
	/**
	 * 冷蔵を取得します。
	 * @return 冷蔵
	 */
	public String getB2$cold() {
	    return b2$cold;
	}
	/**
	 * 冷蔵を設定します。
	 * @param b2$cold 冷蔵
	 */
	public void setB2$cold(String b2$cold) {
	    this.b2$cold = b2$cold;
	}
	/**
	 * 冷凍を取得します。
	 * @return 冷凍
	 */
	public String getB2$freezing() {
	    return b2$freezing;
	}
	/**
	 * 冷凍を設定します。
	 * @param b2$freezing 冷凍
	 */
	public void setB2$freezing(String b2$freezing) {
	    this.b2$freezing = b2$freezing;
	}
}
