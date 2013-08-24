package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Error implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** エラー項目 */
	public String b2$error_property_name;
	/** エラーコード */
	public String b2$error_code;
	/** エラー内容 */
	public String b2$error_description;


	/**
	 * エラー項目を取得します。
	 * @return エラー項目
	 */
	public String getB2$error_property_name() {
	    return b2$error_property_name;
	}
	/**
	 * エラー項目を設定します。
	 * @param b2$error_property_name エラー項目
	 */
	public void setB2$error_property_name(String b2$error_property_name) {
	    this.b2$error_property_name = b2$error_property_name;
	}
	/**
	 * エラーコードを取得します。
	 * @return エラーコード
	 */
	public String getB2$error_code() {
	    return b2$error_code;
	}
	/**
	 * エラーコードを設定します。
	 * @param b2$error_code エラーコード
	 */
	public void setB2$error_code(String b2$error_code) {
	    this.b2$error_code = b2$error_code;
	}
	/**
	 * エラー内容を取得します。
	 * @return エラー内容
	 */
	public String getB2$error_description() {
	    return b2$error_description;
	}
	/**
	 * エラー内容を設定します。
	 * @param b2$error_description エラー内容
	 */
	public void setB2$error_description(String b2$error_description) {
	    this.b2$error_description = b2$error_description;
	}
}
