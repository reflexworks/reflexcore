package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Shipment implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** お客様管理番号 */
	
	public String b2$shipment_number;
	/** 送り状種類 */
	
	public String b2$service_type;
	/** クール区分 */
	public String b2$is_cool;
	/** 宅急便伝票番号 */
	
	public String b2$tracking_number;
	/** 出荷予定日 */
	
	public String b2$shipment_date;
	/** お届け予定日 */
	public String b2$delivery_date;
	/** 最短日フラグ */
	public String b2$short_delivery_date_flag;
	/** 日付印字フラグ */
	public String b2$is_printing_date;
	/** コレクト代金引換額 */
	public String b2$amount;
	/** コレクト内消費税額等 */
	public String b2$tax_amount;
	/** 個数口印字フラグ */
	public String b2$is_printing_lot;
	/** 請求先・顧客コード */
	
	public String b2$invoice_code;
	/** 請求先・分類コード */
	public String b2$invoice_code_ext;
	/** 請求先・運賃管理番号 */
	public String b2$invoice_freight_no;
	/** 請求先・ニックネーム */
	
	public String b2$invoice_name;
	/** 注文時カード払いデータ登録 */
	public String b2$payment_flg;
	/** 注文時カード払い加盟店番号 */
	public String b2$payment_number;
	/** 注文時カード払い申込受付番号1 */
	public String b2$payment_receipt_no1;
	/** 注文時カード払い申込受付番号2 */
	public String b2$payment_receipt_no2;
	/** 注文時カード払い申込受付番号3 */
	public String b2$payment_receipt_no3;

	/** 内部用くくりキー */
	public String b2$closure_key_internal;
	/** 複数口くくりキー */
	
	public String b2$closure_key;
	/** 検索キータイトル1 */
	
	public String b2$search_key_title1;
	/** 検索キー1 */
	
	public String b2$search_key1;
	/** 検索キータイトル2 */
	
	public String b2$search_key_title2;
	/** 検索キー2 */
	
	public String b2$search_key2;
	/** 検索キータイトル3 */
	
	public String b2$search_key_title3;
	/** 検索キー3 */
	
	public String b2$search_key3;
	/** 検索キータイトル4 */
	
	public String b2$search_key_title4;
	/** 検索キー4 */
	
	public String b2$search_key4;
	/** 検索キータイトル5 */
	
	public String b2$search_key_title5;
	/** 検索キー5 */
	
	public String b2$search_key5;
	/** 仕分コード */
	public String b2$sorting_code;
	/** 仕分コードAB */
	public String b2$sorting_ab;
	/** 出荷指示日時 */
	public String b2$issueing_request_date;
	/** 印刷実行日時 */
	
	public String b2$issued_date;
	/** 発行者 */
	public String b2$issuer_loginid;
	/** 送り状再発行枚数 */
	public String b2$reissue_count;
	/** 登録元システム種別 */
	public String b2$input_system_type;
	/** 出荷予定個数口 */
	public String b2$package_qty;
	/** 配達時間帯区分 */
	public String b2$delivery_time_zone;
	/** お届け予定メール利用フラグ */
	public String b2$is_using_shipment_email;
	/** お届け予定e-mailアドレス */
	public String b2$shipment_email_address;
	/** お届け予定入力機種種別 */
	public String b2$input_device_type;
	/** お届け予定メッセージ */
	public String b2$shipment_message;
	/** お届け完了メール利用フラグ */
	public String b2$is_using_delivery_email;
	/** お届け完了e-mailアドレス */
	public String b2$delivery_email_address;
	/** お届け完了メッセージ */
	public String b2$delivery_message;
	/** 出荷区分 */
	public String b2$shipment_flg;
	/** メール便用電話番号印字フラグ */
	public String b2$is_showing_telephone_on_mail;
	/** プリンタ種別 */
	public String b2$printer_type;
	/** 印刷用紙 */
	
	public String b2$printer_paper;
	/** 時間帯サービスレベル */
	public String b2$service_level;
	/** 配達可能時間帯 */
	public String b2$delivery_possible_time;
	/** ログアウト時印字フラグ */
	public String b2$is_printing_logout;
	/** エラーフラグ */
	public String b2$error_flg;
	/** 伝票ステータスのみ更新フラグ */
	public String b2$is_update_only_tracking_status;
	/** 先頭伝票フラグ */
	
	public String b2$is_previous_flg;
	/** 降順ソートキー */
	
	public String b2$desc_sort_key;

	public String b2$shipmentdata_serch_key;

	/**
	 * お客様管理番号を取得します。
	 * @return お客様管理番号
	 */
	public String getB2$shipment_number() {
	    return b2$shipment_number;
	}
	/**
	 * お客様管理番号を設定します。
	 * @param b2$shipment_number お客様管理番号
	 */
	public void setB2$shipment_number(String b2$shipment_number) {
	    this.b2$shipment_number = b2$shipment_number;
	}
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
	 * 宅急便伝票番号を取得します。
	 * @return 宅急便伝票番号
	 */
	public String getB2$tracking_number() {
	    return b2$tracking_number;
	}
	/**
	 * 宅急便伝票番号を設定します。
	 * @param b2$tracking_number 宅急便伝票番号
	 */
	public void setB2$tracking_number(String b2$tracking_number) {
	    this.b2$tracking_number = b2$tracking_number;
	}
	/**
	 * 出荷予定日を取得します。
	 * @return 出荷予定日
	 */
	public String getB2$shipment_date() {
	    return b2$shipment_date;
	}
	/**
	 * 出荷予定日を設定します。
	 * @param b2$shipment_date 出荷予定日
	 */
	public void setB2$shipment_date(String b2$shipment_date) {
	    this.b2$shipment_date = b2$shipment_date;
	}
	/**
	 * お届け予定日を取得します。
	 * @return お届け予定日
	 */
	public String getB2$delivery_date() {
	    return b2$delivery_date;
	}
	/**
	 * お届け予定日を設定します。
	 * @param b2$delivery_date お届け予定日
	 */
	public void setB2$delivery_date(String b2$delivery_date) {
	    this.b2$delivery_date = b2$delivery_date;
	}
	/**
	 * 最短日フラグを取得します。
	 * @return 最短日フラグ
	 */
	public String getB2$short_delivery_date_flag() {
	    return b2$short_delivery_date_flag;
	}
	/**
	 * 最短日フラグを設定します。
	 * @param b2$short_delivery_date_flag 最短日フラグ
	 */
	public void setB2$short_delivery_date_flag(String b2$short_delivery_date_flag) {
	    this.b2$short_delivery_date_flag = b2$short_delivery_date_flag;
	}
	/**
	 * 日付印字フラグを取得します。
	 * @return 日付印字フラグ
	 */
	public String getB2$is_printing_date() {
	    return b2$is_printing_date;
	}
	/**
	 * 日付印字フラグを設定します。
	 * @param b2$is_printing_date 日付印字フラグ
	 */
	public void setB2$is_printing_date(String b2$is_printing_date) {
	    this.b2$is_printing_date = b2$is_printing_date;
	}
	/**
	 * コレクト代金引換額を取得します。
	 * @return コレクト代金引換額
	 */
	public String getB2$amount() {
	    return b2$amount;
	}
	/**
	 * コレクト代金引換額を設定します。
	 * @param b2$amount コレクト代金引換額
	 */
	public void setB2$amount(String b2$amount) {
	    this.b2$amount = b2$amount;
	}
	/**
	 * コレクト内消費税額等を取得します。
	 * @return コレクト内消費税額等
	 */
	public String getB2$tax_amount() {
	    return b2$tax_amount;
	}
	/**
	 * コレクト内消費税額等を設定します。
	 * @param b2$tax_amount コレクト内消費税額等
	 */
	public void setB2$tax_amount(String b2$tax_amount) {
	    this.b2$tax_amount = b2$tax_amount;
	}
	/**
	 * 個数口印字フラグを取得します。
	 * @return 個数口印字フラグ
	 */
	public String getB2$is_printing_lot() {
	    return b2$is_printing_lot;
	}
	/**
	 * 個数口印字フラグを設定します。
	 * @param b2$is_printing_lot 個数口印字フラグ
	 */
	public void setB2$is_printing_lot(String b2$is_printing_lot) {
	    this.b2$is_printing_lot = b2$is_printing_lot;
	}
	/**
	 * 請求先・顧客コードを取得します。
	 * @return 請求先・顧客コード
	 */
	public String getB2$invoice_code() {
	    return b2$invoice_code;
	}
	/**
	 * 請求先・顧客コードを設定します。
	 * @param b2$invoice_code 請求先・顧客コード
	 */
	public void setB2$invoice_code(String b2$invoice_code) {
	    this.b2$invoice_code = b2$invoice_code;
	}
	/**
	 * 請求先・分類コードを取得します。
	 * @return 請求先・分類コード
	 */
	public String getB2$invoice_code_ext() {
	    return b2$invoice_code_ext;
	}
	/**
	 * 請求先・分類コードを設定します。
	 * @param b2$invoice_code_ext 請求先・分類コード
	 */
	public void setB2$invoice_code_ext(String b2$invoice_code_ext) {
	    this.b2$invoice_code_ext = b2$invoice_code_ext;
	}
	/**
	 * 請求先・運賃管理番号を取得します。
	 * @return 請求先・運賃管理番号
	 */
	public String getB2$invoice_freight_no() {
	    return b2$invoice_freight_no;
	}
	/**
	 * 請求先・運賃管理番号を設定します。
	 * @param b2$invoice_freight_no 請求先・運賃管理番号
	 */
	public void setB2$invoice_freight_no(String b2$invoice_freight_no) {
	    this.b2$invoice_freight_no = b2$invoice_freight_no;
	}
	/**
	 * 請求先・ニックネームを取得します。
	 * @return 請求先・ニックネーム
	 */
	public String getB2$invoice_name() {
	    return b2$invoice_name;
	}
	/**
	 * 請求先・ニックネームを設定します。
	 * @param b2$invoice_name 請求先・ニックネーム
	 */
	public void setB2$invoice_name(String b2$invoice_name) {
	    this.b2$invoice_name = b2$invoice_name;
	}
	/**
	 * 注文時カード払いデータ登録を取得します。
	 * @return 注文時カード払いデータ登録
	 */
	public String getB2$payment_flg() {
	    return b2$payment_flg;
	}
	/**
	 * 注文時カード払いデータ登録を設定します。
	 * @param b2$payment_flg 注文時カード払いデータ登録
	 */
	public void setB2$payment_flg(String b2$payment_flg) {
	    this.b2$payment_flg = b2$payment_flg;
	}
	/**
	 * 注文時カード払い加盟店番号を取得します。
	 * @return 注文時カード払い加盟店番号
	 */
	public String getB2$payment_number() {
	    return b2$payment_number;
	}
	/**
	 * 注文時カード払い加盟店番号を設定します。
	 * @param b2$payment_number 注文時カード払い加盟店番号
	 */
	public void setB2$payment_number(String b2$payment_number) {
	    this.b2$payment_number = b2$payment_number;
	}
	/**
	 * 注文時カード払い申込受付番号1を取得します。
	 * @return 注文時カード払い申込受付番号1
	 */
	public String getB2$payment_receipt_no1() {
	    return b2$payment_receipt_no1;
	}
	/**
	 * 注文時カード払い申込受付番号1を設定します。
	 * @param b2$payment_receipt_no1 注文時カード払い申込受付番号1
	 */
	public void setB2$payment_receipt_no1(String b2$payment_receipt_no1) {
	    this.b2$payment_receipt_no1 = b2$payment_receipt_no1;
	}
	/**
	 * 注文時カード払い申込受付番号2を取得します。
	 * @return 注文時カード払い申込受付番号2
	 */
	public String getB2$payment_receipt_no2() {
	    return b2$payment_receipt_no2;
	}
	/**
	 * 注文時カード払い申込受付番号2を設定します。
	 * @param b2$payment_receipt_no2 注文時カード払い申込受付番号2
	 */
	public void setB2$payment_receipt_no2(String b2$payment_receipt_no2) {
	    this.b2$payment_receipt_no2 = b2$payment_receipt_no2;
	}
	/**
	 * 注文時カード払い申込受付番号3を取得します。
	 * @return 注文時カード払い申込受付番号3
	 */
	public String getB2$payment_receipt_no3() {
	    return b2$payment_receipt_no3;
	}
	/**
	 * 注文時カード払い申込受付番号3を設定します。
	 * @param b2$payment_receipt_no3 注文時カード払い申込受付番号3
	 */
	public void setB2$payment_receipt_no3(String b2$payment_receipt_no3) {
	    this.b2$payment_receipt_no3 = b2$payment_receipt_no3;
	}
	/**
	 * 内部用くくりキーを取得します。
	 * @return 内部用くくりキー
	 */
	public String getB2$closure_key_internal() {
	    return b2$closure_key_internal;
	}
	/**
	 * 内部用くくりキーを設定します。
	 * @param b2$closure_key_internal 内部用くくりキー
	 */
	public void setB2$closure_key_internal(String b2$closure_key_internal) {
	    this.b2$closure_key_internal = b2$closure_key_internal;
	}
	/**
	 * 複数口くくりキーを取得します。
	 * @return 複数口くくりキー
	 */
	public String getB2$closure_key() {
	    return b2$closure_key;
	}
	/**
	 * 複数口くくりキーを設定します。
	 * @param b2$closure_key 複数口くくりキー
	 */
	public void setB2$closure_key(String b2$closure_key) {
	    this.b2$closure_key = b2$closure_key;
	}
	/**
	 * 検索キータイトル1を取得します。
	 * @return 検索キータイトル1
	 */
	public String getB2$search_key_title1() {
	    return b2$search_key_title1;
	}
	/**
	 * 検索キータイトル1を設定します。
	 * @param b2$search_key_title1 検索キータイトル1
	 */
	public void setB2$search_key_title1(String b2$search_key_title1) {
	    this.b2$search_key_title1 = b2$search_key_title1;
	}
	/**
	 * 検索キー1を取得します。
	 * @return 検索キー1
	 */
	public String getB2$search_key1() {
	    return b2$search_key1;
	}
	/**
	 * 検索キー1を設定します。
	 * @param b2$search_key1 検索キー1
	 */
	public void setB2$search_key1(String b2$search_key1) {
	    this.b2$search_key1 = b2$search_key1;
	}
	/**
	 * 検索キータイトル2を取得します。
	 * @return 検索キータイトル2
	 */
	public String getB2$search_key_title2() {
	    return b2$search_key_title2;
	}
	/**
	 * 検索キータイトル2を設定します。
	 * @param b2$search_key_title2 検索キータイトル2
	 */
	public void setB2$search_key_title2(String b2$search_key_title2) {
	    this.b2$search_key_title2 = b2$search_key_title2;
	}
	/**
	 * 検索キー2を取得します。
	 * @return 検索キー2
	 */
	public String getB2$search_key2() {
	    return b2$search_key2;
	}
	/**
	 * 検索キー2を設定します。
	 * @param b2$search_key2 検索キー2
	 */
	public void setB2$search_key2(String b2$search_key2) {
	    this.b2$search_key2 = b2$search_key2;
	}
	/**
	 * 検索キータイトル3を取得します。
	 * @return 検索キータイトル3
	 */
	public String getB2$search_key_title3() {
	    return b2$search_key_title3;
	}
	/**
	 * 検索キータイトル3を設定します。
	 * @param b2$search_key_title3 検索キータイトル3
	 */
	public void setB2$search_key_title3(String b2$search_key_title3) {
	    this.b2$search_key_title3 = b2$search_key_title3;
	}
	/**
	 * 検索キー3を取得します。
	 * @return 検索キー3
	 */
	public String getB2$search_key3() {
	    return b2$search_key3;
	}
	/**
	 * 検索キー3を設定します。
	 * @param b2$search_key3 検索キー3
	 */
	public void setB2$search_key3(String b2$search_key3) {
	    this.b2$search_key3 = b2$search_key3;
	}
	/**
	 * 検索キータイトル4を取得します。
	 * @return 検索キータイトル4
	 */
	public String getB2$search_key_title4() {
	    return b2$search_key_title4;
	}
	/**
	 * 検索キータイトル4を設定します。
	 * @param b2$search_key_title4 検索キータイトル4
	 */
	public void setB2$search_key_title4(String b2$search_key_title4) {
	    this.b2$search_key_title4 = b2$search_key_title4;
	}
	/**
	 * 検索キー4を取得します。
	 * @return 検索キー4
	 */
	public String getB2$search_key4() {
	    return b2$search_key4;
	}
	/**
	 * 検索キー4を設定します。
	 * @param b2$search_key4 検索キー4
	 */
	public void setB2$search_key4(String b2$search_key4) {
	    this.b2$search_key4 = b2$search_key4;
	}
	/**
	 * 検索キータイトル5を取得します。
	 * @return 検索キータイトル5
	 */
	public String getB2$search_key_title5() {
	    return b2$search_key_title5;
	}
	/**
	 * 検索キータイトル5を設定します。
	 * @param b2$search_key_title5 検索キータイトル5
	 */
	public void setB2$search_key_title5(String b2$search_key_title5) {
	    this.b2$search_key_title5 = b2$search_key_title5;
	}
	/**
	 * 検索キー5を取得します。
	 * @return 検索キー5
	 */
	public String getB2$search_key5() {
	    return b2$search_key5;
	}
	/**
	 * 検索キー5を設定します。
	 * @param b2$search_key5 検索キー5
	 */
	public void setB2$search_key5(String b2$search_key5) {
	    this.b2$search_key5 = b2$search_key5;
	}
	/**
	 * 仕分コードを取得します。
	 * @return 仕分コード
	 */
	public String getB2$sorting_code() {
	    return b2$sorting_code;
	}
	/**
	 * 仕分コードを設定します。
	 * @param b2$sorting_code 仕分コード
	 */
	public void setB2$sorting_code(String b2$sorting_code) {
	    this.b2$sorting_code = b2$sorting_code;
	}
	/**
	 * 仕分コードABを取得します。
	 * @return 仕分コードAB
	 */
	public String getB2$sorting_ab() {
	    return b2$sorting_ab;
	}
	/**
	 * 仕分コードABを設定します。
	 * @param b2$sorting_ab 仕分コードAB
	 */
	public void setB2$sorting_ab(String b2$sorting_ab) {
	    this.b2$sorting_ab = b2$sorting_ab;
	}
	/**
	 * 出荷指示日時を取得します。
	 * @return 出荷指示日時
	 */
	public String getB2$issueing_request_date() {
	    return b2$issueing_request_date;
	}
	/**
	 * 出荷指示日時を設定します。
	 * @param b2$issueing_request_date 出荷指示日時
	 */
	public void setB2$issueing_request_date(String b2$issueing_request_date) {
	    this.b2$issueing_request_date = b2$issueing_request_date;
	}
	/**
	 * 印刷実行日時を取得します。
	 * @return 印刷実行日時
	 */
	public String getB2$issued_date() {
	    return b2$issued_date;
	}
	/**
	 * 印刷実行日時を設定します。
	 * @param b2$issued_date 印刷実行日時
	 */
	public void setB2$issued_date(String b2$issued_date) {
	    this.b2$issued_date = b2$issued_date;
	}
	/**
	 * 発行者を取得します。
	 * @return 発行者
	 */
	public String getB2$issuer_loginid() {
	    return b2$issuer_loginid;
	}
	/**
	 * 発行者を設定します。
	 * @param b2$issuer_loginid 発行者
	 */
	public void setB2$issuer_loginid(String b2$issuer_loginid) {
	    this.b2$issuer_loginid = b2$issuer_loginid;
	}
	/**
	 * 送り状再発行枚数を取得します。
	 * @return 送り状再発行枚数
	 */
	public String getB2$reissue_count() {
	    return b2$reissue_count;
	}
	/**
	 * 送り状再発行枚数を設定します。
	 * @param b2$reissue_count 送り状再発行枚数
	 */
	public void setB2$reissue_count(String b2$reissue_count) {
	    this.b2$reissue_count = b2$reissue_count;
	}
	/**
	 * 登録元システム種別を取得します。
	 * @return 登録元システム種別
	 */
	public String getB2$input_system_type() {
	    return b2$input_system_type;
	}
	/**
	 * 登録元システム種別を設定します。
	 * @param b2$input_system_type 登録元システム種別
	 */
	public void setB2$input_system_type(String b2$input_system_type) {
	    this.b2$input_system_type = b2$input_system_type;
	}
	/**
	 * 出荷予定個数口を取得します。
	 * @return 出荷予定個数口
	 */
	public String getB2$package_qty() {
	    return b2$package_qty;
	}
	/**
	 * 出荷予定個数口を設定します。
	 * @param b2$package_qty 出荷予定個数口
	 */
	public void setB2$package_qty(String b2$package_qty) {
	    this.b2$package_qty = b2$package_qty;
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
	 * お届け予定メール利用フラグを取得します。
	 * @return お届け予定メール利用フラグ
	 */
	public String getB2$is_using_shipment_email() {
	    return b2$is_using_shipment_email;
	}
	/**
	 * お届け予定メール利用フラグを設定します。
	 * @param b2$is_using_shipment_email お届け予定メール利用フラグ
	 */
	public void setB2$is_using_shipment_email(String b2$is_using_shipment_email) {
	    this.b2$is_using_shipment_email = b2$is_using_shipment_email;
	}
	/**
	 * お届け予定e-mailアドレスを取得します。
	 * @return お届け予定e-mailアドレス
	 */
	public String getB2$shipment_email_address() {
	    return b2$shipment_email_address;
	}
	/**
	 * お届け予定e-mailアドレスを設定します。
	 * @param b2$shipment_email_address お届け予定e-mailアドレス
	 */
	public void setB2$shipment_email_address(String b2$shipment_email_address) {
	    this.b2$shipment_email_address = b2$shipment_email_address;
	}
	/**
	 * お届け予定入力機種種別を取得します。
	 * @return お届け予定入力機種種別
	 */
	public String getB2$input_device_type() {
	    return b2$input_device_type;
	}
	/**
	 * お届け予定入力機種種別を設定します。
	 * @param b2$input_device_type お届け予定入力機種種別
	 */
	public void setB2$input_device_type(String b2$input_device_type) {
	    this.b2$input_device_type = b2$input_device_type;
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
	/**
	 * お届け完了メール利用フラグを取得します。
	 * @return お届け完了メール利用フラグ
	 */
	public String getB2$is_using_delivery_email() {
	    return b2$is_using_delivery_email;
	}
	/**
	 * お届け完了メール利用フラグを設定します。
	 * @param b2$is_using_delivery_email お届け完了メール利用フラグ
	 */
	public void setB2$is_using_delivery_email(String b2$is_using_delivery_email) {
	    this.b2$is_using_delivery_email = b2$is_using_delivery_email;
	}
	/**
	 * お届け完了e-mailアドレスを取得します。
	 * @return お届け完了e-mailアドレス
	 */
	public String getB2$delivery_email_address() {
	    return b2$delivery_email_address;
	}
	/**
	 * お届け完了e-mailアドレスを設定します。
	 * @param b2$delivery_email_address お届け完了e-mailアドレス
	 */
	public void setB2$delivery_email_address(String b2$delivery_email_address) {
	    this.b2$delivery_email_address = b2$delivery_email_address;
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
	/**
	 * 出荷区分を取得します。
	 * @return 出荷区分
	 */
	public String getB2$shipment_flg() {
	    return b2$shipment_flg;
	}
	/**
	 * 出荷区分を設定します。
	 * @param b2$shipment_flg 出荷区分
	 */
	public void setB2$shipment_flg(String b2$shipment_flg) {
	    this.b2$shipment_flg = b2$shipment_flg;
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
	 * プリンタ種別を取得します。
	 * @return プリンタ種別
	 */
	public String getB2$printer_type() {
	    return b2$printer_type;
	}
	/**
	 * プリンタ種別を設定します。
	 * @param b2$printer_type プリンタ種別
	 */
	public void setB2$printer_type(String b2$printer_type) {
	    this.b2$printer_type = b2$printer_type;
	}
	/**
	 * 印刷用紙を取得します。
	 * @return 印刷用紙
	 */
	public String getB2$printer_paper() {
	    return b2$printer_paper;
	}
	/**
	 * 印刷用紙を設定します。
	 * @param b2$printer_paper 印刷用紙
	 */
	public void setB2$printer_paper(String b2$printer_paper) {
	    this.b2$printer_paper = b2$printer_paper;
	}
	/**
	 * 時間帯サービスレベルを取得します。
	 * @return 時間帯サービスレベル
	 */
	public String getB2$service_level() {
	    return b2$service_level;
	}
	/**
	 * 時間帯サービスレベルを設定します。
	 * @param b2$service_level 時間帯サービスレベル
	 */
	public void setB2$service_level(String b2$service_level) {
	    this.b2$service_level = b2$service_level;
	}
	/**
	 * 配達可能時間帯を取得します。
	 * @return 配達可能時間帯
	 */
	public String getB2$delivery_possible_time() {
	    return b2$delivery_possible_time;
	}
	/**
	 * 配達可能時間帯を設定します。
	 * @param b2$delivery_possible_time 配達可能時間帯
	 */
	public void setB2$delivery_possible_time(String b2$delivery_possible_time) {
	    this.b2$delivery_possible_time = b2$delivery_possible_time;
	}
	/**
	 * ログアウト時印字フラグを取得します。
	 * @return ログアウト時印字フラグ
	 */
	public String getB2$is_printing_logout() {
	    return b2$is_printing_logout;
	}
	/**
	 * ログアウト時印字フラグを設定します。
	 * @param b2$is_printing_logout ログアウト時印字フラグ
	 */
	public void setB2$is_printing_logout(String b2$is_printing_logout) {
	    this.b2$is_printing_logout = b2$is_printing_logout;
	}
	/**
	 * エラーフラグを取得します。
	 * @return エラーフラグ
	 */
	public String getB2$error_flg() {
	    return b2$error_flg;
	}
	/**
	 * エラーフラグを設定します。
	 * @param b2$error_flg エラーフラグ
	 */
	public void setB2$error_flg(String b2$error_flg) {
	    this.b2$error_flg = b2$error_flg;
	}
	/**
	 * 伝票ステータスのみ更新フラグを取得します。
	 * @return 伝票ステータスのみ更新フラグ
	 */
	public String getB2$is_update_only_tracking_status() {
	    return b2$is_update_only_tracking_status;
	}
	/**
	 * 伝票ステータスのみ更新フラグを設定します。
	 * @param b2$is_update_only_tracking_status 伝票ステータスのみ更新フラグ
	 */
	public void setB2$is_update_only_tracking_status(String b2$is_update_only_tracking_status) {
	    this.b2$is_update_only_tracking_status = b2$is_update_only_tracking_status;
	}
	/**
	 * 先頭伝票フラグを取得します。
	 * @return 先頭伝票フラグ
	 */
	public String getB2$is_previous_flg() {
	    return b2$is_previous_flg;
	}
	/**
	 * 先頭伝票フラグを設定します。
	 * @param b2$is_previous_flg 先頭伝票フラグ
	 */
	public void setB2$is_previous_flg(String b2$is_previous_flg) {
	    this.b2$is_previous_flg = b2$is_previous_flg;
	}
	/**
	 * 降順ソートキーを取得します。
	 * @return 降順ソートキー
	 */
	public String getB2$desc_sort_key() {
	    return b2$desc_sort_key;
	}
	/**
	 * 降順ソートキーを設定します。
	 * @param b2$desc_sort_key 降順ソートキー
	 */
	public void setB2$desc_sort_key(String b2$desc_sort_key) {
	    this.b2$desc_sort_key = b2$desc_sort_key;
	}
}
