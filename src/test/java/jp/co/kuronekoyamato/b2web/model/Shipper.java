package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Shipper implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** ご依頼主・コード */
	
	public String b2$shipper_code;
	/** ご依頼主・電話番号(表示用) */
	public String b2$shipper_telephone_display;
	/** ご依頼主・電話番号枝番 */
	public String b2$shipper_telephone_ext;
	/** ご依頼主・名 */
	
	public String b2$shipper_name;
	/** ご依頼主・敬称 */
	public String b2$shipper_title;
	/** ご依頼主・郵便番号 */
	public String b2$shipper_zip_code;
	/** ご依頼主・入力住所 */
	public String b2$shipper_address;
	/** ご依頼主・都道府県名 */
	
	public String b2$shipper_address1;
	/** ご依頼主・市区郡町村名 */
	
	public String b2$shipper_address2;
	/** ご依頼主・番地 */
	public String b2$shipper_address3;
	/** ご依頼主・建物名 */
	public String b2$shipper_address4;
	/** ご依頼主・略称カナ */
	
	public String b2$shipper_name_kana;
	/** 担当店所コード */
	public String b2$shipper_center_code;
	/** 担当店所名 */
	public String b2$shipper_center_name;
	/** 出荷指示先・顧客コード */
	public String b2$consignment_code;
	/** 出荷指示先・分類コード */
	public String b2$consignment_code_ext;
	/** 利用メニュー */
	public String b2$customer_role;
	/** ご依頼主・電話番号＋枝番 */
	
	public String b2$shipper_telephone_key;
	/** ご依頼主・電話番号 */
	
	public String b2$shipper_telephone;
	/** 発ベースNo */
	public String b2$base_no;
	/** 遠隔地発行先会社名 */
	public String b2$ship_post_name;
	/** 遠隔地発行元会社名 */
	public String b2$request_from_name;
	/** ご依頼主エラーフラグ */
	public String b2$shipper_error_flg;
	/** 共有ご依頼主件数 */
	public String b2$share_shipper_no;


	/**
	 * ご依頼主・コードを取得します。
	 * @return ご依頼主・コード
	 */
	public String getB2$shipper_code() {
	    return b2$shipper_code;
	}
	/**
	 * ご依頼主・コードを設定します。
	 * @param b2$shipper_code ご依頼主・コード
	 */
	public void setB2$shipper_code(String b2$shipper_code) {
	    this.b2$shipper_code = b2$shipper_code;
	}
	/**
	 * ご依頼主・電話番号を取得します。
	 * @return ご依頼主・電話番号
	 */
	public String getB2$shipper_telephone() {
	    return b2$shipper_telephone;
	}
	/**
	 * ご依頼主・電話番号を設定します。
	 * @param b2$shipper_telephone ご依頼主・電話番号
	 */
	public void setB2$shipper_telephone(String b2$shipper_telephone) {
	    this.b2$shipper_telephone = b2$shipper_telephone;
	}
	/**
	 * ご依頼主・電話番号(表示用)を取得します。
	 * @return ご依頼主・電話番号(表示用)
	 */
	public String getB2$shipper_telephone_display() {
	    return b2$shipper_telephone_display;
	}
	/**
	 * ご依頼主・電話番号(表示用)を設定します。
	 * @param b2$shipper_telephone_display ご依頼主・電話番号(表示用)
	 */
	public void setB2$shipper_telephone_display(String b2$shipper_telephone_display) {
	    this.b2$shipper_telephone_display = b2$shipper_telephone_display;
	}
	/**
	 * ご依頼主・電話番号枝番を取得します。
	 * @return ご依頼主・電話番号枝番
	 */
	public String getB2$shipper_telephone_ext() {
	    return b2$shipper_telephone_ext;
	}
	/**
	 * ご依頼主・電話番号枝番を設定します。
	 * @param b2$shipper_telephone_ext ご依頼主・電話番号枝番
	 */
	public void setB2$shipper_telephone_ext(String b2$shipper_telephone_ext) {
	    this.b2$shipper_telephone_ext = b2$shipper_telephone_ext;
	}
	/**
	 * ご依頼主・名を取得します。
	 * @return ご依頼主・名
	 */
	public String getB2$shipper_name() {
	    return b2$shipper_name;
	}
	/**
	 * ご依頼主・名を設定します。
	 * @param b2$shipper_name ご依頼主・名
	 */
	public void setB2$shipper_name(String b2$shipper_name) {
	    this.b2$shipper_name = b2$shipper_name;
	}
	/**
	 * ご依頼主・敬称を取得します。
	 * @return ご依頼主・敬称
	 */
	public String getB2$shipper_title() {
	    return b2$shipper_title;
	}
	/**
	 * ご依頼主・敬称を設定します。
	 * @param b2$shipper_title ご依頼主・敬称
	 */
	public void setB2$shipper_title(String b2$shipper_title) {
	    this.b2$shipper_title = b2$shipper_title;
	}
	/**
	 * ご依頼主・郵便番号を取得します。
	 * @return ご依頼主・郵便番号
	 */
	public String getB2$shipper_zip_code() {
	    return b2$shipper_zip_code;
	}
	/**
	 * ご依頼主・郵便番号を設定します。
	 * @param b2$shipper_zip_code ご依頼主・郵便番号
	 */
	public void setB2$shipper_zip_code(String b2$shipper_zip_code) {
	    this.b2$shipper_zip_code = b2$shipper_zip_code;
	}
	/**
	 * ご依頼主・入力住所を取得します。
	 * @return ご依頼主・入力住所
	 */
	public String getB2$shipper_address() {
	    return b2$shipper_address;
	}
	/**
	 * ご依頼主・入力住所を設定します。
	 * @param b2$shipper_address ご依頼主・入力住所
	 */
	public void setB2$shipper_address(String b2$shipper_address) {
	    this.b2$shipper_address = b2$shipper_address;
	}
	/**
	 * ご依頼主・都道府県名を取得します。
	 * @return ご依頼主・都道府県名
	 */
	public String getB2$shipper_address1() {
	    return b2$shipper_address1;
	}
	/**
	 * ご依頼主・都道府県名を設定します。
	 * @param b2$shipper_address1 ご依頼主・都道府県名
	 */
	public void setB2$shipper_address1(String b2$shipper_address1) {
	    this.b2$shipper_address1 = b2$shipper_address1;
	}
	/**
	 * ご依頼主・市区郡町村名を取得します。
	 * @return ご依頼主・市区郡町村名
	 */
	public String getB2$shipper_address2() {
	    return b2$shipper_address2;
	}
	/**
	 * ご依頼主・市区郡町村名を設定します。
	 * @param b2$shipper_address2 ご依頼主・市区郡町村名
	 */
	public void setB2$shipper_address2(String b2$shipper_address2) {
	    this.b2$shipper_address2 = b2$shipper_address2;
	}
	/**
	 * ご依頼主・番地を取得します。
	 * @return ご依頼主・番地
	 */
	public String getB2$shipper_address3() {
	    return b2$shipper_address3;
	}
	/**
	 * ご依頼主・番地を設定します。
	 * @param b2$shipper_address3 ご依頼主・番地
	 */
	public void setB2$shipper_address3(String b2$shipper_address3) {
	    this.b2$shipper_address3 = b2$shipper_address3;
	}
	/**
	 * ご依頼主・建物名を取得します。
	 * @return ご依頼主・建物名
	 */
	public String getB2$shipper_address4() {
	    return b2$shipper_address4;
	}
	/**
	 * ご依頼主・建物名を設定します。
	 * @param b2$shipper_address4 ご依頼主・建物名
	 */
	public void setB2$shipper_address4(String b2$shipper_address4) {
	    this.b2$shipper_address4 = b2$shipper_address4;
	}
	/**
	 * ご依頼主・略称カナを取得します。
	 * @return ご依頼主・略称カナ
	 */
	public String getB2$shipper_name_kana() {
	    return b2$shipper_name_kana;
	}
	/**
	 * ご依頼主・略称カナを設定します。
	 * @param b2$shipper_name_kana ご依頼主・略称カナ
	 */
	public void setB2$shipper_name_kana(String b2$shipper_name_kana) {
	    this.b2$shipper_name_kana = b2$shipper_name_kana;
	}
	/**
	 * 担当店所コードを取得します。
	 * @return 担当店所コード
	 */
	public String getB2$shipper_center_code() {
	    return b2$shipper_center_code;
	}
	/**
	 * 担当店所コードを設定します。
	 * @param b2$shipper_center_code 担当店所コード
	 */
	public void setB2$shipper_center_code(String b2$shipper_center_code) {
	    this.b2$shipper_center_code = b2$shipper_center_code;
	}
	/**
	 * 担当店所名を取得します。
	 * @return 担当店所名
	 */
	public String getB2$shipper_center_name() {
	    return b2$shipper_center_name;
	}
	/**
	 * 担当店所名を設定します。
	 * @param b2$shipper_center_name 担当店所名
	 */
	public void setB2$shipper_center_name(String b2$shipper_center_name) {
	    this.b2$shipper_center_name = b2$shipper_center_name;
	}
	/**
	 * 出荷指示先・顧客コードを取得します。
	 * @return 出荷指示先・顧客コード
	 */
	public String getB2$consignment_code() {
	    return b2$consignment_code;
	}
	/**
	 * 出荷指示先・顧客コードを設定します。
	 * @param b2$consignment_code 出荷指示先・顧客コード
	 */
	public void setB2$consignment_code(String b2$consignment_code) {
	    this.b2$consignment_code = b2$consignment_code;
	}
	/**
	 * 出荷指示先・分類コードを取得します。
	 * @return 出荷指示先・分類コード
	 */
	public String getB2$consignment_code_ext() {
	    return b2$consignment_code_ext;
	}
	/**
	 * 出荷指示先・分類コードを設定します。
	 * @param b2$consignment_code_ext 出荷指示先・分類コード
	 */
	public void setB2$consignment_code_ext(String b2$consignment_code_ext) {
	    this.b2$consignment_code_ext = b2$consignment_code_ext;
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
	 * ご依頼主・電話番号＋枝番を取得します。
	 * @return ご依頼主・電話番号＋枝番
	 */
	public String getB2$shipper_telephone_key() {
	    return b2$shipper_telephone_key;
	}
	/**
	 * ご依頼主・電話番号＋枝番を設定します。
	 * @param b2$shipper_telephone_key ご依頼主・電話番号＋枝番
	 */
	public void setB2$shipper_telephone_key(String b2$shipper_telephone_key) {
	    this.b2$shipper_telephone_key = b2$shipper_telephone_key;
	}
	/**
	 * 発ベースNoを取得します。
	 * @return 発ベースNo
	 */
	public String getB2$base_no() {
	    return b2$base_no;
	}
	/**
	 * 発ベースNoを設定します。
	 * @param b2$base_no 発ベースNo
	 */
	public void setB2$base_no(String b2$base_no) {
	    this.b2$base_no = b2$base_no;
	}
	/**
	 * 遠隔地発行先会社名を取得します。
	 * @return 遠隔地発行先会社名
	 */
	public String getB2$ship_post_name() {
	    return b2$ship_post_name;
	}
	/**
	 * 遠隔地発行先会社名を設定します。
	 * @param b2$ship_post_name 遠隔地発行先会社名
	 */
	public void setB2$ship_post_name(String b2$ship_post_name) {
	    this.b2$ship_post_name = b2$ship_post_name;
	}
	/**
	 * 遠隔地発行元会社名を取得します。
	 * @return 遠隔地発行元会社名
	 */
	public String getB2$request_from_name() {
	    return b2$request_from_name;
	}
	/**
	 * 遠隔地発行元会社名を設定します。
	 * @param b2$request_from_name 遠隔地発行元会社名
	 */
	public void setB2$request_from_name(String b2$request_from_name) {
	    this.b2$request_from_name = b2$request_from_name;
	}
	/**
	 * ご依頼主エラーフラグを取得します。
	 * @return ご依頼主エラーフラグ
	 */
	public String getB2$shipper_error_flg() {
	    return b2$shipper_error_flg;
	}
	/**
	 * ご依頼主エラーフラグを設定します。
	 * @param b2$shipper_error_flg ご依頼主エラーフラグ
	 */
	public void setB2$shipper_error_flg(String b2$shipper_error_flg) {
	    this.b2$shipper_error_flg = b2$shipper_error_flg;
	}
	/**
	 * 共有ご依頼主件数を取得します。
	 * @return 共有ご依頼主件数
	 */
	public String getB2$share_shipper_no() {
	    return b2$share_shipper_no;
	}
	/**
	 * 共有ご依頼主件数を設定します。
	 * @param b2$share_shipper_no 共有ご依頼主件数
	 */
	public void setB2$share_shipper_no(String b2$share_shipper_no) {
	    this.b2$share_shipper_no = b2$share_shipper_no;
	}

}
