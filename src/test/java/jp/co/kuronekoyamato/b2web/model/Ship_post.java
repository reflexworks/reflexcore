package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Ship_post implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 遠隔地発行先顧客コード */
	public String b2$ship_post_code;
	/** 遠隔地発行先分類コード */
	public String b2$ship_post_code_ext;
	/** 遠隔地発行先会社名 */
	public String b2$ship_post_name;
	/** 遠隔地発行先顧客担当店 */
	public String b2$ship_post_center_code;



	/**
	 * 遠隔地発行先顧客コードを取得します。
	 * @return 遠隔地発行先顧客コード
	 */
	public String getB2$ship_post_code() {
	    return b2$ship_post_code;
	}
	/**
	 * 遠隔地発行先顧客コードを設定します。
	 * @param b2$ship_post_code 遠隔地発行先顧客コード
	 */
	public void setB2$ship_post_code(String b2$ship_post_code) {
	    this.b2$ship_post_code = b2$ship_post_code;
	}
	/**
	 * 遠隔地発行先分類コードを取得します。
	 * @return 遠隔地発行先分類コード
	 */
	public String getB2$ship_post_code_ext() {
	    return b2$ship_post_code_ext;
	}
	/**
	 * 遠隔地発行先分類コードを設定します。
	 * @param b2$ship_post_code_ext 遠隔地発行先分類コード
	 */
	public void setB2$ship_post_code_ext(String b2$ship_post_code_ext) {
	    this.b2$ship_post_code_ext = b2$ship_post_code_ext;
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
	 * 遠隔地発行先顧客担当店を取得します。
	 * @return 遠隔地発行先顧客担当店
	 */
	public String getB2$ship_post_center_code() {
	    return b2$ship_post_center_code;
	}
	/**
	 * 遠隔地発行先顧客担当店を設定します。
	 * @param b2$ship_post_center_code 遠隔地発行先顧客担当店
	 */
	public void setB2$ship_post_center_code(String b2$ship_post_center_code) {
	    this.b2$ship_post_center_code = b2$ship_post_center_code;
	}
}
