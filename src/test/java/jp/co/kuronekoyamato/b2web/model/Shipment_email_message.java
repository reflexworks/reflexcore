package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Shipment_email_message implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** メッセージ番号 */
	public String b2$email_message_no;
	/** お届け予定メッセージ */
	public String b2$shipment_message;



	/**
	 * メッセージ番号を取得します。
	 * @return メッセージ番号
	 */
	public String getB2$email_message_no() {
	    return b2$email_message_no;
	}

	/**
	 * メッセージ番号を設定します。
	 * @param b2$email_message_no メッセージ番号
	 */
	public void setB2$email_message_no(String b2$email_message_no) {
	    this.b2$email_message_no = b2$email_message_no;
	}

	/**
	 * お届け予定メッセージを取得します。
	 * @return お届け予定メッセージ
	 */
	public String getB2$shipment_message() {
	    return b2$shipment_message;
	}

	/**
	 * お届け予定メッセージを設定します。
	 * @param b2$shipment_message お届け予定メッセージ
	 */
	public void setB2$shipment_message(String b2$shipment_message) {
	    this.b2$shipment_message = b2$shipment_message;
	}
}
