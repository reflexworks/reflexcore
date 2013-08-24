package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Shipper_label implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 出荷元名 */
	public String b2$design_shipper_name;
	/** 住所1 */
	public String b2$address1;
	/** 住所2 */
	public String b2$address2;
	/** 住所3 */
	public String b2$address3;
	/** 電話番号 */
	public String b2$telephone;
	/** 備考1 */
	public String b2$biko1;
	/** 備考2 */
	public String b2$biko2;
	/** 備考3 */
	public String b2$biko3;



	/**
	 * 出荷元名を取得します。
	 * @return 出荷元名
	 */
	public String getB2$design_shipper_name() {
	    return b2$design_shipper_name;
	}
	/**
	 * 出荷元名を設定します。
	 * @param b2$design_shipper_name 出荷元名
	 */
	public void setB2$design_shipper_name(String b2$design_shipper_name) {
	    this.b2$design_shipper_name = b2$design_shipper_name;
	}
	/**
	 * 住所1を取得します。
	 * @return 住所1
	 */
	public String getB2$address1() {
	    return b2$address1;
	}
	/**
	 * 住所1を設定します。
	 * @param b2$address1 住所1
	 */
	public void setB2$address1(String b2$address1) {
	    this.b2$address1 = b2$address1;
	}
	/**
	 * 住所2を取得します。
	 * @return 住所2
	 */
	public String getB2$address2() {
	    return b2$address2;
	}
	/**
	 * 住所2を設定します。
	 * @param b2$address2 住所2
	 */
	public void setB2$address2(String b2$address2) {
	    this.b2$address2 = b2$address2;
	}
	/**
	 * 住所3を取得します。
	 * @return 住所3
	 */
	public String getB2$address3() {
	    return b2$address3;
	}
	/**
	 * 住所3を設定します。
	 * @param b2$address3 住所3
	 */
	public void setB2$address3(String b2$address3) {
	    this.b2$address3 = b2$address3;
	}
	/**
	 * 電話番号を取得します。
	 * @return 電話番号
	 */
	public String getB2$telephone() {
	    return b2$telephone;
	}
	/**
	 * 電話番号を設定します。
	 * @param b2$telephone 電話番号
	 */
	public void setB2$telephone(String b2$telephone) {
	    this.b2$telephone = b2$telephone;
	}
	/**
	 * 備考1を取得します。
	 * @return 備考1
	 */
	public String getB2$biko1() {
	    return b2$biko1;
	}
	/**
	 * 備考1を設定します。
	 * @param b2$biko1 備考1
	 */
	public void setB2$biko1(String b2$biko1) {
	    this.b2$biko1 = b2$biko1;
	}
	/**
	 * 備考2を取得します。
	 * @return 備考2
	 */
	public String getB2$biko2() {
	    return b2$biko2;
	}
	/**
	 * 備考2を設定します。
	 * @param b2$biko2 備考2
	 */
	public void setB2$biko2(String b2$biko2) {
	    this.b2$biko2 = b2$biko2;
	}
	/**
	 * 備考3を取得します。
	 * @return 備考3
	 */
	public String getB2$biko3() {
	    return b2$biko3;
	}
	/**
	 * 備考3を設定します。
	 * @param b2$biko3 備考3
	 */
	public void setB2$biko3(String b2$biko3) {
	    this.b2$biko3 = b2$biko3;
	}
}
