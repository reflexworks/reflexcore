package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Customer implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** ログインID */
	public String b2$login_id;
	/** 顧客コード */
	public String b2$customer_code;
	/** 分類コード */
	public String b2$customer_code_ext;
	/** 利用メニュー */
	public String b2$customer_role;
	/** 顧客名 */
	public String b2$customer_name;
	/** 担当店コード */
	public String b2$customer_center_code;
	/** ログインユーザ名 */
	public String b2$login_username;
	/** 前回ログイン日時 */
	public String b2$lastlogin_date;
	/** データ共有範囲リスト */
	public Master_share_list master_share_list;
	/** 発行先リスト */
	public Ship_post_list ship_post_list;
	/** 発行元リスト */
	public Request_from_list request_from_list;
	/** 請求先リスト */
	public Invoice_list invoice_list;
	/** パスワード */
	public String b2$password;
	/** 各種設定 */
	public General_settings general_settings;
	/** プリンタ設定 */
	public Printer_settings printer_settings;
	/** お届け予定eMailリスト */
	public Shipment_email_message_list shipment_email_message_list;
	/** お届け完了eMailリスト */
	public Delivery_email_message_list delivery_email_message_list;
	/** 用紙余白設定 */
	public Issue_room_setting issue_room_setting;



	/**
	 * ログインIDを取得します。
	 * @return ログインID
	 */
	public String getB2$login_id() {
	    return b2$login_id;
	}
	/**
	 * ログインIDを設定します。
	 * @param b2$login_id ログインID
	 */
	public void setB2$login_id(String b2$login_id) {
	    this.b2$login_id = b2$login_id;
	}
	/**
	 * 顧客コードを取得します。
	 * @return 顧客コード
	 */
	public String getB2$customer_code() {
	    return b2$customer_code;
	}
	/**
	 * 顧客コードを設定します。
	 * @param b2$customer_code 顧客コード
	 */
	public void setB2$customer_code(String b2$customer_code) {
	    this.b2$customer_code = b2$customer_code;
	}
	/**
	 * 分類コードを取得します。
	 * @return 分類コード
	 */
	public String getB2$customer_code_ext() {
	    return b2$customer_code_ext;
	}
	/**
	 * 分類コードを設定します。
	 * @param b2$customer_code_ext 分類コード
	 */
	public void setB2$customer_code_ext(String b2$customer_code_ext) {
	    this.b2$customer_code_ext = b2$customer_code_ext;
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
	 * 担当店コードを取得します。
	 * @return 担当店コード
	 */
	public String getB2$customer_center_code() {
	    return b2$customer_center_code;
	}
	/**
	 * 担当店コードを設定します。
	 * @param b2$customer_center_code 担当店コード
	 */
	public void setB2$customer_center_code(String b2$customer_center_code) {
	    this.b2$customer_center_code = b2$customer_center_code;
	}
	/**
	 * ログインユーザ名を取得します。
	 * @return ログインユーザ名
	 */
	public String getB2$login_username() {
	    return b2$login_username;
	}
	/**
	 * ログインユーザ名を設定します。
	 * @param b2$login_username ログインユーザ名
	 */
	public void setB2$login_username(String b2$login_username) {
	    this.b2$login_username = b2$login_username;
	}
	/**
	 * 前回ログイン日時を取得します。
	 * @return 前回ログイン日時
	 */
	public String getB2$lastlogin_date() {
	    return b2$lastlogin_date;
	}
	/**
	 * 前回ログイン日時を設定します。
	 * @param b2$lastlogin_date 前回ログイン日時
	 */
	public void setB2$lastlogin_date(String b2$lastlogin_date) {
	    this.b2$lastlogin_date = b2$lastlogin_date;
	}
	/**
	 * データ共有範囲リストを取得します。
	 * @return データ共有範囲リスト
	 */
	public Master_share_list getMaster_share_list() {
	    return master_share_list;
	}
	/**
	 * データ共有範囲リストを設定します。
	 * @param master_share_list データ共有範囲リスト
	 */
	public void setMaster_share_list(Master_share_list master_share_list) {
	    this.master_share_list = master_share_list;
	}
	/**
	 * 発行先リストを取得します。
	 * @return 発行先リスト
	 */
	public Ship_post_list getShip_post_list() {
	    return ship_post_list;
	}
	/**
	 * 発行先リストを設定します。
	 * @param ship_post_list 発行先リスト
	 */
	public void setShip_post_list(Ship_post_list ship_post_list) {
	    this.ship_post_list = ship_post_list;
	}
	/**
	 * 発行元リストを取得します。
	 * @return 発行元リスト
	 */
	public Request_from_list getRequest_from_list() {
	    return request_from_list;
	}
	/**
	 * 発行元リストを設定します。
	 * @param request_from_list 発行元リスト
	 */
	public void setRequest_from_list(Request_from_list request_from_list) {
	    this.request_from_list = request_from_list;
	}
	/**
	 * 請求先リストを取得します。
	 * @return 請求先リスト
	 */
	public Invoice_list getInvoice_list() {
	    return invoice_list;
	}
	/**
	 * 請求先リストを設定します。
	 * @param invoice_list 請求先リスト
	 */
	public void setInvoice_list(Invoice_list invoice_list) {
	    this.invoice_list = invoice_list;
	}
	/**
	 * パスワードを取得します。
	 * @return パスワード
	 */
	public String getB2$password() {
	    return b2$password;
	}
	/**
	 * パスワードを設定します。
	 * @param b2$password パスワード
	 */
	public void setB2$password(String b2$password) {
	    this.b2$password = b2$password;
	}
	/**
	 * 各種設定を取得します。
	 * @return 各種設定
	 */
	public General_settings getGeneral_settings() {
	    return general_settings;
	}
	/**
	 * 各種設定を設定します。
	 * @param general_settings 各種設定
	 */
	public void setGeneral_settings(General_settings general_settings) {
	    this.general_settings = general_settings;
	}
	/**
	 * プリンタ設定を取得します。
	 * @return プリンタ設定
	 */
	public Printer_settings getPrinter_settings() {
	    return printer_settings;
	}
	/**
	 * プリンタ設定を設定します。
	 * @param printer_settings プリンタ設定
	 */
	public void setPrinter_settings(Printer_settings printer_settings) {
	    this.printer_settings = printer_settings;
	}
	/**
	 * お届け予定eMailリストを取得します。
	 * @return お届け予定eMailリスト
	 */
	public Shipment_email_message_list getShipment_email_message_list() {
	    return shipment_email_message_list;
	}
	/**
	 * お届け予定eMailリストを設定します。
	 * @param shipment_email_message_list お届け予定eMailリスト
	 */
	public void setShipment_email_message_list(Shipment_email_message_list shipment_email_message_list) {
	    this.shipment_email_message_list = shipment_email_message_list;
	}
	/**
	 * お届け完了eMailリストを取得します。
	 * @return お届け完了eMailリスト
	 */
	public Delivery_email_message_list getDelivery_email_message_list() {
	    return delivery_email_message_list;
	}
	/**
	 * お届け完了eMailリストを設定します。
	 * @param delivery_email_message_list お届け完了eMailリスト
	 */
	public void setDelivery_email_message_list(Delivery_email_message_list delivery_email_message_list) {
	    this.delivery_email_message_list = delivery_email_message_list;
	}
	/**
	 * 用紙余白設定を取得します。
	 * @return 用紙余白設定
	 */
	public Issue_room_setting getIssue_room_setting() {
	    return issue_room_setting;
	}
	/**
	 * 用紙余白設定を設定します。
	 * @param issue_room_setting 用紙余白設定
	 */
	public void setIssue_room_setting(Issue_room_setting issue_room_setting) {
	    this.issue_room_setting = issue_room_setting;
	}
}
