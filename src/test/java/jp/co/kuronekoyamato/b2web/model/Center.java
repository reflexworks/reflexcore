package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Center implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 営業所コード */
	public String b2$center_code;
	/** 営業所名 */
	public String b2$center_name;
	/** 仕分コード */
	public String b2$sorting_code;
	/** 発ベースNo */
	public String b2$base_no;
	/** マトリクス・サービスレベル */
	public String b2$m_service_level;
	/** 適用開始日 */
	public String b2$date_from;



	/**
	 * 営業所コードを取得します。
	 * @return 営業所コード
	 */
	public String getB2$center_code() {
	    return b2$center_code;
	}
	/**
	 * 営業所コードを設定します。
	 * @param b2$center_code 営業所コード
	 */
	public void setB2$center_code(String b2$center_code) {
	    this.b2$center_code = b2$center_code;
	}
	/**
	 * 営業所名を取得します。
	 * @return 営業所名
	 */
	public String getB2$center_name() {
	    return b2$center_name;
	}
	/**
	 * 営業所名を設定します。
	 * @param b2$center_name 営業所名
	 */
	public void setB2$center_name(String b2$center_name) {
	    this.b2$center_name = b2$center_name;
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
	 * マトリクス・サービスレベルを取得します。
	 * @return マトリクス・サービスレベル
	 */
	public String getB2$m_service_level() {
	    return b2$m_service_level;
	}
	/**
	 * マトリクス・サービスレベルを設定します。
	 * @param b2$m_service_level マトリクス・サービスレベル
	 */
	public void setB2$m_service_level(String b2$m_service_level) {
	    this.b2$m_service_level = b2$m_service_level;
	}
	/**
	 * 適用開始日を取得します。
	 * @return 適用開始日
	 */
	public String getB2$date_from() {
	    return b2$date_from;
	}
	/**
	 * 適用開始日を設定します。
	 * @param b2$date_from 適用開始日
	 */
	public void setB2$date_from(String b2$date_from) {
	    this.b2$date_from = b2$date_from;
	}
}
