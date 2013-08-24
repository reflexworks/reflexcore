package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Print_design_left implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** フリーデザイン左タイプ */
	public String b2$design_type_left;
	/** 出荷元情報 */
	public Shipper_label shipper_label;
	/** フリーデザイン画像URL */
	public String b2$print_design_url;



	/**
	 * フリーデザイン左タイプを取得します。
	 * @return フリーデザイン左タイプ
	 */
	public String getB2$design_type_left() {
	    return b2$design_type_left;
	}
	/**
	 * フリーデザイン左タイプを設定します。
	 * @param b2$design_type_left フリーデザイン左タイプ
	 */
	public void setB2$design_type_left(String b2$design_type_left) {
	    this.b2$design_type_left = b2$design_type_left;
	}
	/**
	 * 出荷元情報を取得します。
	 * @return 出荷元情報
	 */
	public Shipper_label getShipper_label() {
	    return shipper_label;
	}
	/**
	 * 出荷元情報を設定します。
	 * @param shipper_label 出荷元情報
	 */
	public void setShipper_label(Shipper_label shipper_label) {
	    this.shipper_label = shipper_label;
	}
	/**
	 * フリーデザイン画像URLを取得します。
	 * @return フリーデザイン画像URL
	 */
	public String getB2$print_design_url() {
	    return b2$print_design_url;
	}
	/**
	 * フリーデザイン画像URLを設定します。
	 * @param b2$print_design_url フリーデザイン画像URL
	 */
	public void setB2$print_design_url(String b2$print_design_url) {
	    this.b2$print_design_url = b2$print_design_url;
	}
}
