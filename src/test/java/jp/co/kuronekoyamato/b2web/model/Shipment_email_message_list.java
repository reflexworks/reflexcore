package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Shipment_email_message_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** お届け予定eMail */
	public List<Shipment_email_message> b2$shipment_email_message;

	/**
	 * お届け予定eMailを取得します。
	 * @return お届け予定eMail
	 */
	public List<Shipment_email_message> getB2$shipment_email_message() {
		return b2$shipment_email_message;
	}

	/**
	 * お届け予定eMailを設定します。
	 * @param b2$shipment_email_message お届け予定eMail
	 */
	public void setB2$shipment_email_message(
			List<Shipment_email_message> b2$shipment_email_message) {
		this.b2$shipment_email_message = b2$shipment_email_message;
	}


}
