package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class General_settings implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 送り状種類 */
	public String b2$service_type;
	/** 配達時間帯区分 */
	public String b2$delivery_time_zone;
	/** メール便用電話番号印字フラグ */
	public String b2$is_showing_telephone_on_mail;
	/** クール区分 */
	public String b2$is_cool;
	/** データ取込後画面表示 */
	public String b2$is_showing_error_only;
	/** 消費税率 */
	public String b2$is_tax_rate;
	/** ご依頼主・電話番号 */
	public String b2$shipper_telephone;
	/** ご依頼主・電話番号(表示用) */
	public String b2$shipper_telephone_display;
	/** ご依頼主・電話番号枝番 */
	public String b2$shipper_telephone_ext;
	/** ご依頼主・名 */
	public String b2$shipper_name;
	/** お客様番号カード払い受付反映 */
	public String b2$is_printing_shipment_number_on_card;
	/** 出荷データ検索初期表示開始日 */
	public String b2$shipment_date_from;
	/** 出荷データ検索初期表示終了日 */
	public String b2$shipment_date_to;
	/** 送り状発行順序1 */
	public String b2$issue_order1;
	/** 送り状発行順序2 */
	public String b2$issue_order2;
	/** 送り状発行順序3 */
	public String b2$issue_order3;
	/** 取込みパターン名(出荷) */
	public String b2$shipment_import_pattern_name;
	/** 取込みパターン名(お届け先) */
	public String b2$consignee_import_pattern_name;
	/** 取込みパターン名(ご依頼主) */
	public String b2$shipper_import_pattern_name;
	/** 取込みパターン名(品名) */
	public String b2$content_details_import_pattern_name;



	/**
	 * 送り状種類を取得します。
	 * @return 送り状種類
	 */
	public String getB2$service_type() {
	    return b2$service_type;
	}
	/**
	 * 送り状種類を設定します。
	 * @param b2$service_type 送り状種類
	 */
	public void setB2$service_type(String b2$service_type) {
	    this.b2$service_type = b2$service_type;
	}
	/**
	 * 配達時間帯区分を取得します。
	 * @return 配達時間帯区分
	 */
	public String getB2$delivery_time_zone() {
	    return b2$delivery_time_zone;
	}
	/**
	 * 配達時間帯区分を設定します。
	 * @param b2$delivery_time_zone 配達時間帯区分
	 */
	public void setB2$delivery_time_zone(String b2$delivery_time_zone) {
	    this.b2$delivery_time_zone = b2$delivery_time_zone;
	}
	/**
	 * メール便用電話番号印字フラグを取得します。
	 * @return メール便用電話番号印字フラグ
	 */
	public String getB2$is_showing_telephone_on_mail() {
	    return b2$is_showing_telephone_on_mail;
	}
	/**
	 * メール便用電話番号印字フラグを設定します。
	 * @param b2$is_showing_telephone_on_mail メール便用電話番号印字フラグ
	 */
	public void setB2$is_showing_telephone_on_mail(String b2$is_showing_telephone_on_mail) {
	    this.b2$is_showing_telephone_on_mail = b2$is_showing_telephone_on_mail;
	}
	/**
	 * クール区分を取得します。
	 * @return クール区分
	 */
	public String getB2$is_cool() {
	    return b2$is_cool;
	}
	/**
	 * クール区分を設定します。
	 * @param b2$is_cool クール区分
	 */
	public void setB2$is_cool(String b2$is_cool) {
	    this.b2$is_cool = b2$is_cool;
	}
	/**
	 * データ取込後画面表示を取得します。
	 * @return データ取込後画面表示
	 */
	public String getB2$is_showing_error_only() {
	    return b2$is_showing_error_only;
	}
	/**
	 * データ取込後画面表示を設定します。
	 * @param b2$is_showing_error_only データ取込後画面表示
	 */
	public void setB2$is_showing_error_only(String b2$is_showing_error_only) {
	    this.b2$is_showing_error_only = b2$is_showing_error_only;
	}
	/**
	 * 消費税率を取得します。
	 * @return 消費税率
	 */
	public String getB2$is_tax_rate() {
	    return b2$is_tax_rate;
	}
	/**
	 * 消費税率を設定します。
	 * @param b2$is_tax_rate 消費税率
	 */
	public void setB2$is_tax_rate(String b2$is_tax_rate) {
	    this.b2$is_tax_rate = b2$is_tax_rate;
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
	 * お客様番号カード払い受付反映を取得します。
	 * @return お客様番号カード払い受付反映
	 */
	public String getB2$is_printing_shipment_number_on_card() {
	    return b2$is_printing_shipment_number_on_card;
	}
	/**
	 * お客様番号カード払い受付反映を設定します。
	 * @param b2$is_printing_shipment_number_on_card お客様番号カード払い受付反映
	 */
	public void setB2$is_printing_shipment_number_on_card(String b2$is_printing_shipment_number_on_card) {
	    this.b2$is_printing_shipment_number_on_card = b2$is_printing_shipment_number_on_card;
	}
	/**
	 * 出荷データ検索初期表示開始日を取得します。
	 * @return 出荷データ検索初期表示開始日
	 */
	public String getB2$shipment_date_from() {
	    return b2$shipment_date_from;
	}
	/**
	 * 出荷データ検索初期表示開始日を設定します。
	 * @param b2$shipment_date_from 出荷データ検索初期表示開始日
	 */
	public void setB2$shipment_date_from(String b2$shipment_date_from) {
	    this.b2$shipment_date_from = b2$shipment_date_from;
	}
	/**
	 * 出荷データ検索初期表示終了日を取得します。
	 * @return 出荷データ検索初期表示終了日
	 */
	public String getB2$shipment_date_to() {
	    return b2$shipment_date_to;
	}
	/**
	 * 出荷データ検索初期表示終了日を設定します。
	 * @param b2$shipment_date_to 出荷データ検索初期表示終了日
	 */
	public void setB2$shipment_date_to(String b2$shipment_date_to) {
	    this.b2$shipment_date_to = b2$shipment_date_to;
	}
	/**
	 * 送り状発行順序1を取得します。
	 * @return 送り状発行順序1
	 */
	public String getB2$issue_order1() {
	    return b2$issue_order1;
	}
	/**
	 * 送り状発行順序1を設定します。
	 * @param b2$issue_order1 送り状発行順序1
	 */
	public void setB2$issue_order1(String b2$issue_order1) {
	    this.b2$issue_order1 = b2$issue_order1;
	}
	/**
	 * 送り状発行順序2を取得します。
	 * @return 送り状発行順序2
	 */
	public String getB2$issue_order2() {
	    return b2$issue_order2;
	}
	/**
	 * 送り状発行順序2を設定します。
	 * @param b2$issue_order2 送り状発行順序2
	 */
	public void setB2$issue_order2(String b2$issue_order2) {
	    this.b2$issue_order2 = b2$issue_order2;
	}
	/**
	 * 送り状発行順序3を取得します。
	 * @return 送り状発行順序3
	 */
	public String getB2$issue_order3() {
	    return b2$issue_order3;
	}
	/**
	 * 送り状発行順序3を設定します。
	 * @param b2$issue_order3 送り状発行順序3
	 */
	public void setB2$issue_order3(String b2$issue_order3) {
	    this.b2$issue_order3 = b2$issue_order3;
	}
	/**
	 * 取込みパターン名(出荷)を取得します。
	 * @return 取込みパターン名(出荷)
	 */
	public String getB2$shipment_import_pattern_name() {
	    return b2$shipment_import_pattern_name;
	}
	/**
	 * 取込みパターン名(出荷)を設定します。
	 * @param b2$shipment_import_pattern_name 取込みパターン名(出荷)
	 */
	public void setB2$shipment_import_pattern_name(String b2$shipment_import_pattern_name) {
	    this.b2$shipment_import_pattern_name = b2$shipment_import_pattern_name;
	}
	/**
	 * 取込みパターン名(お届け先)を取得します。
	 * @return 取込みパターン名(お届け先)
	 */
	public String getB2$consignee_import_pattern_name() {
	    return b2$consignee_import_pattern_name;
	}
	/**
	 * 取込みパターン名(お届け先)を設定します。
	 * @param b2$consignee_import_pattern_name 取込みパターン名(お届け先)
	 */
	public void setB2$consignee_import_pattern_name(String b2$consignee_import_pattern_name) {
	    this.b2$consignee_import_pattern_name = b2$consignee_import_pattern_name;
	}
	/**
	 * 取込みパターン名(ご依頼主)を取得します。
	 * @return 取込みパターン名(ご依頼主)
	 */
	public String getB2$shipper_import_pattern_name() {
	    return b2$shipper_import_pattern_name;
	}
	/**
	 * 取込みパターン名(ご依頼主)を設定します。
	 * @param b2$shipper_import_pattern_name 取込みパターン名(ご依頼主)
	 */
	public void setB2$shipper_import_pattern_name(String b2$shipper_import_pattern_name) {
	    this.b2$shipper_import_pattern_name = b2$shipper_import_pattern_name;
	}
	/**
	 * 取込みパターン名(品名)を取得します。
	 * @return 取込みパターン名(品名)
	 */
	public String getB2$content_details_import_pattern_name() {
	    return b2$content_details_import_pattern_name;
	}
	/**
	 * 取込みパターン名(品名)を設定します。
	 * @param b2$content_details_import_pattern_name 取込みパターン名(品名)
	 */
	public void setB2$content_details_import_pattern_name(String b2$content_details_import_pattern_name) {
	    this.b2$content_details_import_pattern_name = b2$content_details_import_pattern_name;
	}
}
