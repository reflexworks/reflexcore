package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Invoice_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 請求先 */
	public List<Invoice> b2$invoice;



	/**
	 * 請求先を取得します。
	 * @return 請求先
	 */
	public List<Invoice> getB2$invoice() {
	    return b2$invoice;
	}

	/**
	 * 請求先を設定します。
	 * @param b2$invoice 請求先
	 */
	public void setB2$invoice(List<Invoice> b2$invoice) {
	    this.b2$invoice = b2$invoice;
	}
}
