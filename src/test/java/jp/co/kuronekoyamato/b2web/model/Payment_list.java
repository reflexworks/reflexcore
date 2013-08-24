package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Payment_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** ペイメント加盟店番号 */
	public List<Payment> b2$payment;



	/**
	 * ペイメント加盟店番号を取得します。
	 * @return ペイメント加盟店番号
	 */
	public List<Payment> getB2$payment() {
	    return b2$payment;
	}

	/**
	 * ペイメント加盟店番号を設定します。
	 * @param b2$payment ペイメント加盟店番号
	 */
	public void setB2$payment(List<Payment> b2$payment) {
	    this.b2$payment = b2$payment;
	}
}
