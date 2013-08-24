package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Sorting_code implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** キーコード */
	public String b2$keycode;
	/** 仕分コード(宅急便用) */
	public String b2$sorting_code_taku;
	/** 仕分コード(メール用) */
	public String b2$sorting_code_mail;
	/** 適用開始日 */
	public String b2$date_from;
	/** 行政コード */
	public String b2$gyosei_code;
	/** 仕分コード */
	public String b2$sorting_code;



	/**
	 * キーコードを取得します。
	 * @return キーコード
	 */
	public String getB2$keycode() {
	    return b2$keycode;
	}
	/**
	 * キーコードを設定します。
	 * @param b2$keycode キーコード
	 */
	public void setB2$keycode(String b2$keycode) {
	    this.b2$keycode = b2$keycode;
	}
	/**
	 * 仕分コード(宅急便用)を取得します。
	 * @return 仕分コード(宅急便用)
	 */
	public String getB2$sorting_code_taku() {
	    return b2$sorting_code_taku;
	}
	/**
	 * 仕分コード(宅急便用)を設定します。
	 * @param b2$sorting_code_taku 仕分コード(宅急便用)
	 */
	public void setB2$sorting_code_taku(String b2$sorting_code_taku) {
	    this.b2$sorting_code_taku = b2$sorting_code_taku;
	}
	/**
	 * 仕分コード(メール用)を取得します。
	 * @return 仕分コード(メール用)
	 */
	public String getB2$sorting_code_mail() {
	    return b2$sorting_code_mail;
	}
	/**
	 * 仕分コード(メール用)を設定します。
	 * @param b2$sorting_code_mail 仕分コード(メール用)
	 */
	public void setB2$sorting_code_mail(String b2$sorting_code_mail) {
	    this.b2$sorting_code_mail = b2$sorting_code_mail;
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
	/**
	 * 行政コードを取得します。
	 * @return 行政コード
	 */
	public String getB2$gyosei_code() {
	    return b2$gyosei_code;
	}
	/**
	 * 行政コードを設定します。
	 * @param b2$gyosei_code 行政コード
	 */
	public void setB2$gyosei_code(String b2$gyosei_code) {
	    this.b2$gyosei_code = b2$gyosei_code;
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
}
