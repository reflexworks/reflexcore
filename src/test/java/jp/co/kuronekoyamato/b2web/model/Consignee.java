package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Consignee implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** お届け先・コード */
	public String b2$consignee_code;
	/** お届け先・電話番号(表示用) */
	public String b2$consignee_telephone_display;
	/** お届け先・電話番号枝番 */
	public String b2$consignee_telephone_ext;
	/** お届け先・名 */
	public String b2$consignee_name;
	/** お届け先・郵便番号 */
	public String b2$consignee_zip_code;
	/** お届け先・入力住所 */
	public String b2$consignee_address;
	/** お届け先・都道府県名 */
	public String b2$consignee_address1;
	/** お届け先・市区郡町村名 */
	public String b2$consignee_address2;
	/** お届け先・番地 */
	public String b2$consignee_address3;
	/** お届け先・建物名 */
	public String b2$consignee_address4;
	/** お届け先・部門名1 */
	public String b2$consignee_department1;
	/** お届け先・部門名2 */
	public String b2$consignee_department2;
	/** お届け先・略称カナ */
	public String b2$consignee_name_kana;
	/** お届け先・敬称 */
	public String b2$consignee_title;
	/** 止置きサービス利用フラグ */
	public String b2$is_using_center_service;
	/** 止め置き営業所コード */
	public String b2$consignee_center_code;
	/** 止め置き営業所名 */
	public String b2$consignee_center_name;
	/** お届け先・電話番号＋枝番 */
	public String b2$consignee_telephone_key;
	/** お届け先・電話番号 */
	public String b2$consignee_telephone;
	/** 送信停止フラグ */
	public String b2$is_sending_email;
	/** お届け先エラーフラグ */
	public String b2$consignee_error_flg;
	/** 共有お届け先件数 */
	public String b2$share_consignee_no;



	/**
	 * お届け先・コードを取得します。
	 * @return お届け先・コード
	 */
	public String getB2$consignee_code() {
	    return b2$consignee_code;
	}
	/**
	 * お届け先・コードを設定します。
	 * @param b2$consignee_code お届け先・コード
	 */
	public void setB2$consignee_code(String b2$consignee_code) {
	    this.b2$consignee_code = b2$consignee_code;
	}
	/**
	 * お届け先・電話番号を取得します。
	 * @return お届け先・電話番号
	 */
	public String getB2$consignee_telephone() {
	    return b2$consignee_telephone;
	}
	/**
	 * お届け先・電話番号を設定します。
	 * @param b2$consignee_telephone お届け先・電話番号
	 */
	public void setB2$consignee_telephone(String b2$consignee_telephone) {
	    this.b2$consignee_telephone = b2$consignee_telephone;
	}
	/**
	 * お届け先・電話番号(表示用)を取得します。
	 * @return お届け先・電話番号(表示用)
	 */
	public String getB2$consignee_telephone_display() {
	    return b2$consignee_telephone_display;
	}
	/**
	 * お届け先・電話番号(表示用)を設定します。
	 * @param b2$consignee_telephone_display お届け先・電話番号(表示用)
	 */
	public void setB2$consignee_telephone_display(String b2$consignee_telephone_display) {
	    this.b2$consignee_telephone_display = b2$consignee_telephone_display;
	}
	/**
	 * お届け先・電話番号枝番を取得します。
	 * @return お届け先・電話番号枝番
	 */
	public String getB2$consignee_telephone_ext() {
	    return b2$consignee_telephone_ext;
	}
	/**
	 * お届け先・電話番号枝番を設定します。
	 * @param b2$consignee_telephone_ext お届け先・電話番号枝番
	 */
	public void setB2$consignee_telephone_ext(String b2$consignee_telephone_ext) {
	    this.b2$consignee_telephone_ext = b2$consignee_telephone_ext;
	}
	/**
	 * お届け先・名を取得します。
	 * @return お届け先・名
	 */
	public String getB2$consignee_name() {
	    return b2$consignee_name;
	}
	/**
	 * お届け先・名を設定します。
	 * @param b2$consignee_name お届け先・名
	 */
	public void setB2$consignee_name(String b2$consignee_name) {
	    this.b2$consignee_name = b2$consignee_name;
	}
	/**
	 * お届け先・郵便番号を取得します。
	 * @return お届け先・郵便番号
	 */
	public String getB2$consignee_zip_code() {
	    return b2$consignee_zip_code;
	}
	/**
	 * お届け先・郵便番号を設定します。
	 * @param b2$consignee_zip_code お届け先・郵便番号
	 */
	public void setB2$consignee_zip_code(String b2$consignee_zip_code) {
	    this.b2$consignee_zip_code = b2$consignee_zip_code;
	}
	/**
	 * お届け先・入力住所を取得します。
	 * @return お届け先・入力住所
	 */
	public String getB2$consignee_address() {
	    return b2$consignee_address;
	}
	/**
	 * お届け先・入力住所を設定します。
	 * @param b2$consignee_address お届け先・入力住所
	 */
	public void setB2$consignee_address(String b2$consignee_address) {
	    this.b2$consignee_address = b2$consignee_address;
	}
	/**
	 * お届け先・都道府県名を取得します。
	 * @return お届け先・都道府県名
	 */
	public String getB2$consignee_address1() {
	    return b2$consignee_address1;
	}
	/**
	 * お届け先・都道府県名を設定します。
	 * @param b2$consignee_address1 お届け先・都道府県名
	 */
	public void setB2$consignee_address1(String b2$consignee_address1) {
	    this.b2$consignee_address1 = b2$consignee_address1;
	}
	/**
	 * お届け先・市区郡町村名を取得します。
	 * @return お届け先・市区郡町村名
	 */
	public String getB2$consignee_address2() {
	    return b2$consignee_address2;
	}
	/**
	 * お届け先・市区郡町村名を設定します。
	 * @param b2$consignee_address2 お届け先・市区郡町村名
	 */
	public void setB2$consignee_address2(String b2$consignee_address2) {
	    this.b2$consignee_address2 = b2$consignee_address2;
	}
	/**
	 * お届け先・番地を取得します。
	 * @return お届け先・番地
	 */
	public String getB2$consignee_address3() {
	    return b2$consignee_address3;
	}
	/**
	 * お届け先・番地を設定します。
	 * @param b2$consignee_address3 お届け先・番地
	 */
	public void setB2$consignee_address3(String b2$consignee_address3) {
	    this.b2$consignee_address3 = b2$consignee_address3;
	}
	/**
	 * お届け先・建物名を取得します。
	 * @return お届け先・建物名
	 */
	public String getB2$consignee_address4() {
	    return b2$consignee_address4;
	}
	/**
	 * お届け先・建物名を設定します。
	 * @param b2$consignee_address4 お届け先・建物名
	 */
	public void setB2$consignee_address4(String b2$consignee_address4) {
	    this.b2$consignee_address4 = b2$consignee_address4;
	}
	/**
	 * お届け先・部門名1を取得します。
	 * @return お届け先・部門名1
	 */
	public String getB2$consignee_department1() {
	    return b2$consignee_department1;
	}
	/**
	 * お届け先・部門名1を設定します。
	 * @param b2$consignee_department1 お届け先・部門名1
	 */
	public void setB2$consignee_department1(String b2$consignee_department1) {
	    this.b2$consignee_department1 = b2$consignee_department1;
	}
	/**
	 * お届け先・部門名2を取得します。
	 * @return お届け先・部門名2
	 */
	public String getB2$consignee_department2() {
	    return b2$consignee_department2;
	}
	/**
	 * お届け先・部門名2を設定します。
	 * @param b2$consignee_department2 お届け先・部門名2
	 */
	public void setB2$consignee_department2(String b2$consignee_department2) {
	    this.b2$consignee_department2 = b2$consignee_department2;
	}
	/**
	 * お届け先・略称カナを取得します。
	 * @return お届け先・略称カナ
	 */
	public String getB2$consignee_name_kana() {
	    return b2$consignee_name_kana;
	}
	/**
	 * お届け先・略称カナを設定します。
	 * @param b2$consignee_name_kana お届け先・略称カナ
	 */
	public void setB2$consignee_name_kana(String b2$consignee_name_kana) {
	    this.b2$consignee_name_kana = b2$consignee_name_kana;
	}
	/**
	 * お届け先・敬称を取得します。
	 * @return お届け先・敬称
	 */
	public String getB2$consignee_title() {
	    return b2$consignee_title;
	}
	/**
	 * お届け先・敬称を設定します。
	 * @param b2$consignee_title お届け先・敬称
	 */
	public void setB2$consignee_title(String b2$consignee_title) {
	    this.b2$consignee_title = b2$consignee_title;
	}
	/**
	 * 止置きサービス利用フラグを取得します。
	 * @return 止置きサービス利用フラグ
	 */
	public String getB2$is_using_center_service() {
	    return b2$is_using_center_service;
	}
	/**
	 * 止置きサービス利用フラグを設定します。
	 * @param b2$is_using_center_service 止置きサービス利用フラグ
	 */
	public void setB2$is_using_center_service(String b2$is_using_center_service) {
	    this.b2$is_using_center_service = b2$is_using_center_service;
	}
	/**
	 * 止め置き営業所コードを取得します。
	 * @return 止め置き営業所コード
	 */
	public String getB2$consignee_center_code() {
	    return b2$consignee_center_code;
	}
	/**
	 * 止め置き営業所コードを設定します。
	 * @param b2$consignee_center_code 止め置き営業所コード
	 */
	public void setB2$consignee_center_code(String b2$consignee_center_code) {
	    this.b2$consignee_center_code = b2$consignee_center_code;
	}
	/**
	 * 止め置き営業所名を取得します。
	 * @return 止め置き営業所名
	 */
	public String getB2$consignee_center_name() {
	    return b2$consignee_center_name;
	}
	/**
	 * 止め置き営業所名を設定します。
	 * @param b2$consignee_center_name 止め置き営業所名
	 */
	public void setB2$consignee_center_name(String b2$consignee_center_name) {
	    this.b2$consignee_center_name = b2$consignee_center_name;
	}
	/**
	 * お届け先・電話番号＋枝番を取得します。
	 * @return お届け先・電話番号＋枝番
	 */
	public String getB2$consignee_telephone_key() {
	    return b2$consignee_telephone_key;
	}
	/**
	 * お届け先・電話番号＋枝番を設定します。
	 * @param b2$consignee_telephone_key お届け先・電話番号＋枝番
	 */
	public void setB2$consignee_telephone_key(String b2$consignee_telephone_key) {
	    this.b2$consignee_telephone_key = b2$consignee_telephone_key;
	}
	/**
	 * 送信停止フラグを取得します。
	 * @return 送信停止フラグ
	 */
	public String getB2$is_sending_email() {
	    return b2$is_sending_email;
	}
	/**
	 * 送信停止フラグを設定します。
	 * @param b2$is_sending_email 送信停止フラグ
	 */
	public void setB2$is_sending_email(String b2$is_sending_email) {
	    this.b2$is_sending_email = b2$is_sending_email;
	}
	/**
	 * お届け先エラーフラグを取得します。
	 * @return お届け先エラーフラグ
	 */
	public String getB2$consignee_error_flg() {
	    return b2$consignee_error_flg;
	}
	/**
	 * お届け先エラーフラグを設定します。
	 * @param b2$consignee_error_flg お届け先エラーフラグ
	 */
	public void setB2$consignee_error_flg(String b2$consignee_error_flg) {
	    this.b2$consignee_error_flg = b2$consignee_error_flg;
	}
	/**
	 * 共有お届け先件数を取得します。
	 * @return 共有お届け先件数
	 */
	public String getB2$share_consignee_no() {
	    return b2$share_consignee_no;
	}
	/**
	 * 共有お届け先件数を設定します。
	 * @param b2$share_consignee_no 共有お届け先件数
	 */
	public void setB2$share_consignee_no(String b2$share_consignee_no) {
	    this.b2$share_consignee_no = b2$share_consignee_no;
	}


}
