package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Print_design_right implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** フリーデザイン右タイプ */
	public String b2$design_type_right;
	/** 出荷元情報 */
	public Shipper_label shipper_label;



	/**
	 * フリーデザイン右タイプを取得します。
	 * @return フリーデザイン右タイプ
	 */
	public String getB2$design_type_right() {
	    return b2$design_type_right;
	}
	/**
	 * フリーデザイン右タイプを設定します。
	 * @param b2$design_type_right フリーデザイン右タイプ
	 */
	public void setB2$design_type_right(String b2$design_type_right) {
	    this.b2$design_type_right = b2$design_type_right;
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
}
