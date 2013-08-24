package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Payment implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 加盟店番号 */
	public String b2$payment_number;



	/**
	 * 加盟店番号を取得します。
	 * @return 加盟店番号
	 */
	public String getB2$payment_number() {
	    return b2$payment_number;
	}

	/**
	 * 加盟店番号を設定します。
	 * @param b2$payment_number 加盟店番号
	 */
	public void setB2$payment_number(String b2$payment_number) {
	    this.b2$payment_number = b2$payment_number;
	}
}
