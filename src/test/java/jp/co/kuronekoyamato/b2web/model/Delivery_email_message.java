package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Delivery_email_message implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** メッセージ番号 */
	public String b2$email_message_no;
	/** お届け完了メッセージ */
	public String b2$delivery_message;



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
	 * お届け完了メッセージを取得します。
	 * @return お届け完了メッセージ
	 */
	public String getB2$delivery_message() {
	    return b2$delivery_message;
	}

	/**
	 * お届け完了メッセージを設定します。
	 * @param b2$delivery_message お届け完了メッセージ
	 */
	public void setB2$delivery_message(String b2$delivery_message) {
	    this.b2$delivery_message = b2$delivery_message;
	}
}
