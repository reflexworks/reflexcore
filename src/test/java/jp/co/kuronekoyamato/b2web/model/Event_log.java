package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Event_log implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** ログ種別 */
	public String b2$log_type;
	/** イベント発生日時 */
	public String b2$event_date;
	/** ログインID */
	public String b2$login_id;
	/** 操作画面 */
	public String b2$screen_name;
	/** 操作内容 */
	public String b2$description;
	/** 件数 */
	public String b2$counts;
	/** ブラウザ名 */
	public String b2$browser_name;
	/** ブラウザバージョン */
	public String b2$browser_version;
	/** プラットフォームOS */
	public String b2$os;
	/** スクリーン幅 */
	public String b2$screen_width;
	/** スクリーン高さ */
	public String b2$screen_height;
	/** ファイル名 */
	public String b2$filename;
	/** イベント発生日時(降順ソート用) */
	public String b2$event_date_sort;



	/**
	 * ログ種別を取得します。
	 * @return ログ種別
	 */
	public String getB2$log_type() {
	    return b2$log_type;
	}
	/**
	 * ログ種別を設定します。
	 * @param b2$log_type ログ種別
	 */
	public void setB2$log_type(String b2$log_type) {
	    this.b2$log_type = b2$log_type;
	}
	/**
	 * イベント発生日時を取得します。
	 * @return イベント発生日時
	 */
	public String getB2$event_date() {
	    return b2$event_date;
	}
	/**
	 * イベント発生日時を設定します。
	 * @param b2$event_date イベント発生日時
	 */
	public void setB2$event_date(String b2$event_date) {
	    this.b2$event_date = b2$event_date;
	}
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
	 * 操作画面を取得します。
	 * @return 操作画面
	 */
	public String getB2$screen_name() {
	    return b2$screen_name;
	}
	/**
	 * 操作画面を設定します。
	 * @param b2$screen_name 操作画面
	 */
	public void setB2$screen_name(String b2$screen_name) {
	    this.b2$screen_name = b2$screen_name;
	}
	/**
	 * 操作内容を取得します。
	 * @return 操作内容
	 */
	public String getB2$description() {
	    return b2$description;
	}
	/**
	 * 操作内容を設定します。
	 * @param b2$description 操作内容
	 */
	public void setB2$description(String b2$description) {
	    this.b2$description = b2$description;
	}
	/**
	 * 件数を取得します。
	 * @return 件数
	 */
	public String getB2$counts() {
	    return b2$counts;
	}
	/**
	 * 件数を設定します。
	 * @param b2$counts 件数
	 */
	public void setB2$counts(String b2$counts) {
	    this.b2$counts = b2$counts;
	}
	/**
	 * ブラウザ名を取得します。
	 * @return ブラウザ名
	 */
	public String getB2$browser_name() {
	    return b2$browser_name;
	}
	/**
	 * ブラウザ名を設定します。
	 * @param b2$browser_name ブラウザ名
	 */
	public void setB2$browser_name(String b2$browser_name) {
	    this.b2$browser_name = b2$browser_name;
	}
	/**
	 * ブラウザバージョンを取得します。
	 * @return ブラウザバージョン
	 */
	public String getB2$browser_version() {
	    return b2$browser_version;
	}
	/**
	 * ブラウザバージョンを設定します。
	 * @param b2$browser_version ブラウザバージョン
	 */
	public void setB2$browser_version(String b2$browser_version) {
	    this.b2$browser_version = b2$browser_version;
	}
	/**
	 * プラットフォームOSを取得します。
	 * @return プラットフォームOS
	 */
	public String getB2$os() {
	    return b2$os;
	}
	/**
	 * プラットフォームOSを設定します。
	 * @param b2$os プラットフォームOS
	 */
	public void setB2$os(String b2$os) {
	    this.b2$os = b2$os;
	}
	/**
	 * スクリーン幅を取得します。
	 * @return スクリーン幅
	 */
	public String getB2$screen_width() {
	    return b2$screen_width;
	}
	/**
	 * スクリーン幅を設定します。
	 * @param b2$screen_width スクリーン幅
	 */
	public void setB2$screen_width(String b2$screen_width) {
	    this.b2$screen_width = b2$screen_width;
	}
	/**
	 * スクリーン高さを取得します。
	 * @return スクリーン高さ
	 */
	public String getB2$screen_height() {
	    return b2$screen_height;
	}
	/**
	 * スクリーン高さを設定します。
	 * @param b2$screen_height スクリーン高さ
	 */
	public void setB2$screen_height(String b2$screen_height) {
	    this.b2$screen_height = b2$screen_height;
	}
	/**
	 * ファイル名を取得します。
	 * @return ファイル名
	 */
	public String getB2$filename() {
	    return b2$filename;
	}
	/**
	 * ファイル名を設定します。
	 * @param b2$filename ファイル名
	 */
	public void setB2$filename(String b2$filename) {
	    this.b2$filename = b2$filename;
	}
	/**
	 * イベント発生日時(降順ソート用)を取得します。
	 * @return イベント発生日時(降順ソート用)
	 */
	public String getB2$event_date_sort() {
	    return b2$event_date_sort;
	}
	/**
	 * イベント発生日時(降順ソート用)を設定します。
	 * @param b2$event_date_sort イベント発生日時(降順ソート用)
	 */
	public void setB2$event_date_sort(String b2$event_date_sort) {
	    this.b2$event_date_sort = b2$event_date_sort;
	}
}
