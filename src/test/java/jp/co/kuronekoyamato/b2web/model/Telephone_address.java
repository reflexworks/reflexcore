package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Telephone_address implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 市外局番 */
	public String b2$telephone;
	/** 市外局番通番 */
	public String b2$areacode;
	/** 都道府県 */
	public String b2$address1;
	/** 市区郡 */
	public String b2$address2;
	/** 区町村 */
	public String b2$address3;



	/**
	 * 市外局番を取得します。
	 * @return 市外局番
	 */
	public String getB2$telephone() {
	    return b2$telephone;
	}
	/**
	 * 市外局番を設定します。
	 * @param b2$telephone 市外局番
	 */
	public void setB2$telephone(String b2$telephone) {
	    this.b2$telephone = b2$telephone;
	}
	/**
	 * 市外局番通番を取得します。
	 * @return 市外局番通番
	 */
	public String getB2$areacode() {
	    return b2$areacode;
	}
	/**
	 * 市外局番通番を設定します。
	 * @param b2$areacode 市外局番通番
	 */
	public void setB2$areacode(String b2$areacode) {
	    this.b2$areacode = b2$areacode;
	}
	/**
	 * 都道府県を取得します。
	 * @return 都道府県
	 */
	public String getB2$address1() {
	    return b2$address1;
	}
	/**
	 * 都道府県を設定します。
	 * @param b2$address1 都道府県
	 */
	public void setB2$address1(String b2$address1) {
	    this.b2$address1 = b2$address1;
	}
	/**
	 * 市区郡を取得します。
	 * @return 市区郡
	 */
	public String getB2$address2() {
	    return b2$address2;
	}
	/**
	 * 市区郡を設定します。
	 * @param b2$address2 市区郡
	 */
	public void setB2$address2(String b2$address2) {
	    this.b2$address2 = b2$address2;
	}
	/**
	 * 区町村を取得します。
	 * @return 区町村
	 */
	public String getB2$address3() {
	    return b2$address3;
	}
	/**
	 * 区町村を設定します。
	 * @param b2$address3 区町村
	 */
	public void setB2$address3(String b2$address3) {
	    this.b2$address3 = b2$address3;
	}
}
