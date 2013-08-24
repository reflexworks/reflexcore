package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Tracking implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 出荷日 */
	
	public String b2$shipment_result_date;
	/** サイズ品目コード */
	public String b2$package_size_code;
	/** サイズ品目名称 */
	public String b2$package_size_name;
	/** 貨物追跡荷物状況 */
	public String b2$tracking_status;
	/** 貨物追跡荷物状況名称 */
	public String b2$tracking_status_name;
	/** 貨物追跡日 */
	public String b2$tracking_date;
	/** 貨物追跡時刻 */
	public String b2$tracking_time;
	/** 貨物追跡営業所コード */
	public String b2$tracking_center_code;
	/** 貨物追跡営業所名 */
	public String b2$tracking_center_name;
	/** 問い合わせ日時 */
	public String b2$query_date_time;


	/**
	 * 出荷日を取得します。
	 * @return 出荷日
	 */
	public String getB2$shipment_result_date() {
	    return b2$shipment_result_date;
	}
	/**
	 * 出荷日を設定します。
	 * @param b2$shipment_result_date 出荷日
	 */
	public void setB2$shipment_result_date(String b2$shipment_result_date) {
	    this.b2$shipment_result_date = b2$shipment_result_date;
	}
	/**
	 * サイズ品目コードを取得します。
	 * @return サイズ品目コード
	 */
	public String getB2$package_size_code() {
	    return b2$package_size_code;
	}
	/**
	 * サイズ品目コードを設定します。
	 * @param b2$package_size_code サイズ品目コード
	 */
	public void setB2$package_size_code(String b2$package_size_code) {
	    this.b2$package_size_code = b2$package_size_code;
	}
	/**
	 * サイズ品目名称を取得します。
	 * @return サイズ品目名称
	 */
	public String getB2$package_size_name() {
	    return b2$package_size_name;
	}
	/**
	 * サイズ品目名称を設定します。
	 * @param b2$package_size_name サイズ品目名称
	 */
	public void setB2$package_size_name(String b2$package_size_name) {
	    this.b2$package_size_name = b2$package_size_name;
	}
	/**
	 * 貨物追跡荷物状況を取得します。
	 * @return 貨物追跡荷物状況
	 */
	public String getB2$tracking_status() {
	    return b2$tracking_status;
	}
	/**
	 * 貨物追跡荷物状況を設定します。
	 * @param b2$tracking_status 貨物追跡荷物状況
	 */
	public void setB2$tracking_status(String b2$tracking_status) {
	    this.b2$tracking_status = b2$tracking_status;
	}
	/**
	 * 貨物追跡荷物状況名称を取得します。
	 * @return 貨物追跡荷物状況名称
	 */
	public String getB2$tracking_status_name() {
	    return b2$tracking_status_name;
	}
	/**
	 * 貨物追跡荷物状況名称を設定します。
	 * @param b2$tracking_status_name 貨物追跡荷物状況名称
	 */
	public void setB2$tracking_status_name(String b2$tracking_status_name) {
	    this.b2$tracking_status_name = b2$tracking_status_name;
	}
	/**
	 * 貨物追跡日を取得します。
	 * @return 貨物追跡日
	 */
	public String getB2$tracking_date() {
	    return b2$tracking_date;
	}
	/**
	 * 貨物追跡日を設定します。
	 * @param b2$tracking_date 貨物追跡日
	 */
	public void setB2$tracking_date(String b2$tracking_date) {
	    this.b2$tracking_date = b2$tracking_date;
	}
	/**
	 * 貨物追跡時刻を取得します。
	 * @return 貨物追跡時刻
	 */
	public String getB2$tracking_time() {
	    return b2$tracking_time;
	}
	/**
	 * 貨物追跡時刻を設定します。
	 * @param b2$tracking_time 貨物追跡時刻
	 */
	public void setB2$tracking_time(String b2$tracking_time) {
	    this.b2$tracking_time = b2$tracking_time;
	}
	/**
	 * 貨物追跡営業所コードを取得します。
	 * @return 貨物追跡営業所コード
	 */
	public String getB2$tracking_center_code() {
	    return b2$tracking_center_code;
	}
	/**
	 * 貨物追跡営業所コードを設定します。
	 * @param b2$tracking_center_code 貨物追跡営業所コード
	 */
	public void setB2$tracking_center_code(String b2$tracking_center_code) {
	    this.b2$tracking_center_code = b2$tracking_center_code;
	}
	/**
	 * 貨物追跡営業所名を取得します。
	 * @return 貨物追跡営業所名
	 */
	public String getB2$tracking_center_name() {
	    return b2$tracking_center_name;
	}
	/**
	 * 貨物追跡営業所名を設定します。
	 * @param b2$tracking_center_name 貨物追跡営業所名
	 */
	public void setB2$tracking_center_name(String b2$tracking_center_name) {
	    this.b2$tracking_center_name = b2$tracking_center_name;
	}
	/**
	 * 問い合わせ日時を取得します。
	 * @return 問い合わせ日時
	 */
	public String getB2$query_date_time() {
	    return b2$query_date_time;
	}
	/**
	 * 問い合わせ日時を設定します。
	 * @param b2$query_date_time 問い合わせ日時
	 */
	public void setB2$query_date_time(String b2$query_date_time) {
	    this.b2$query_date_time = b2$query_date_time;
	}
}
