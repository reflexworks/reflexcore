package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Email_setting implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 送信先メールアドレス */
	public String b2$email_address;
	/** メール送信区分 */
	public String b2$sending_flag;
	/** メール送信可否フラグ */
	public String b2$is_not_available;
	/** エラー情報リスト */
	public Error_list error_list;


	/**
	 * 送信先メールアドレスを取得します。
	 * @return 送信先メールアドレス
	 */
	public String getB2$email_address() {
	    return b2$email_address;
	}
	/**
	 * 送信先メールアドレスを設定します。
	 * @param b2$email_address 送信先メールアドレス
	 */
	public void setB2$email_address(String b2$email_address) {
	    this.b2$email_address = b2$email_address;
	}
	/**
	 * メール送信区分を取得します。
	 * @return メール送信区分
	 */
	public String getB2$sending_flag() {
	    return b2$sending_flag;
	}
	/**
	 * メール送信区分を設定します。
	 * @param b2$sending_flag メール送信区分
	 */
	public void setB2$sending_flag(String b2$sending_flag) {
	    this.b2$sending_flag = b2$sending_flag;
	}
	/**
	 * メール送信可否フラグを取得します。
	 * @return メール送信可否フラグ
	 */
	public String getB2$is_not_available() {
	    return b2$is_not_available;
	}
	/**
	 * メール送信可否フラグを設定します。
	 * @param b2$is_not_available メール送信可否フラグ
	 */
	public void setB2$is_not_available(String b2$is_not_available) {
	    this.b2$is_not_available = b2$is_not_available;
	}
	/**
	 * エラー情報リストを取得します。
	 * @return エラー情報リスト
	 */
	public Error_list getError_list() {
	    return error_list;
	}
	/**
	 * エラー情報リストを設定します。
	 * @param error_list エラー情報リスト
	 */
	public void setError_list(Error_list error_list) {
	    this.error_list = error_list;
	}

}
