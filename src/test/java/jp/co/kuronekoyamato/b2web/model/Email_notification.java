package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Email_notification implements Serializable{

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** テストメールフラグ */
	public String b2$is_testmail;
	/** 顧客名 */
	public String b2$customer_name;
	/** 利用メニュー */
	public String b2$customer_role;
	/** メール送信先リスト */
	public Email_setting_list email_setting_list;



	/**
	 * テストメールフラグを取得します。
	 * @return テストメールフラグ
	 */
	public String getB2$is_testmail() {
	    return b2$is_testmail;
	}
	/**
	 * テストメールフラグを設定します。
	 * @param b2$is_testmail テストメールフラグ
	 */
	public void setB2$is_testmail(String b2$is_testmail) {
	    this.b2$is_testmail = b2$is_testmail;
	}
	/**
	 * 顧客名を取得します。
	 * @return 顧客名
	 */
	public String getB2$customer_name() {
	    return b2$customer_name;
	}
	/**
	 * 顧客名を設定します。
	 * @param b2$customer_name 顧客名
	 */
	public void setB2$customer_name(String b2$customer_name) {
	    this.b2$customer_name = b2$customer_name;
	}
	/**
	 * 利用メニューを取得します。
	 * @return 利用メニュー
	 */
	public String getB2$customer_role() {
	    return b2$customer_role;
	}
	/**
	 * 利用メニューを設定します。
	 * @param b2$customer_role 利用メニュー
	 */
	public void setB2$customer_role(String b2$customer_role) {
	    this.b2$customer_role = b2$customer_role;
	}
	/**
	 * メール送信先リストを取得します。
	 * @return メール送信先リスト
	 */
	public Email_setting_list getEmail_setting_list() {
	    return email_setting_list;
	}
	/**
	 * メール送信先リストを設定します。
	 * @param email_setting_list メール送信先リスト
	 */
	public void setEmail_setting_list(Email_setting_list email_setting_list) {
	    this.email_setting_list = email_setting_list;
	}
}

