package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Delivery_email_message_list implements Serializable {


	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** お届け完了eMail */
	public List<Delivery_email_message> b2$delivery_email_message;



	/**
	 * お届け完了eMailを取得します。
	 * @return お届け完了eMail
	 */
	public List<Delivery_email_message> getB2$delivery_email_message() {
	    return b2$delivery_email_message;
	}

	/**
	 * お届け完了eMailを設定します。
	 * @param b2$delivery_email_message お届け完了eMail
	 */
	public void setB2$delivery_email_message(List<Delivery_email_message> b2$delivery_email_message) {
	    this.b2$delivery_email_message = b2$delivery_email_message;
	}
}
