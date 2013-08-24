package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class System_date implements Serializable{

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** システム日付 */
	public String b2$sys_date;
	/** システム時刻 */
	public String b2$sys_time;
	/**
	 * システム日付を取得します。
	 * @return システム日付
	 */
	public String getB2$sys_date() {
	    return b2$sys_date;
	}
	/**
	 * システム日付を設定します。
	 * @param b2$sys_date システム日付
	 */
	public void setB2$sys_date(String b2$sys_date) {
	    this.b2$sys_date = b2$sys_date;
	}
	/**
	 * システム時刻を取得します。
	 * @return システム時刻
	 */
	public String getB2$sys_time() {
	    return b2$sys_time;
	}
	/**
	 * システム時刻を設定します。
	 * @param b2$sys_time システム時刻
	 */
	public void setB2$sys_time(String b2$sys_time) {
	    this.b2$sys_time = b2$sys_time;
	}


}
