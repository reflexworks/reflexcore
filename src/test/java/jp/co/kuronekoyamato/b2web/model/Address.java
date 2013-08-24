package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Address implements Serializable {

	// ---- B2名前空間定義 ---- x
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 都道府県コード */
	public String b2$address1_code;
	/** 市区町村コード */
	public String b2$address2_code;
	/** 都道府県 */
	public String b2$address1;
	/** 市区郡町村 */
	public String b2$address2;
	/** 郵便番号 */
	public String b2$zip_code;
	/** 番地 */
	public String b2$address3;
	/** 事業所郵便番号 */
	public String b2$j_zip_code;
	/** 適用開始日 */
	public String b2$date_from;
	/** 適用終了日 */
	public String b2$date_to;



	/**
	 * 都道府県コードを取得します。
	 * @return 都道府県コード
	 */
	public String getB2$address1_code() {
	    return b2$address1_code;
	}
	/**
	 * 都道府県コードを設定します。
	 * @param b2$address1_code 都道府県コード
	 */
	public void setB2$address1_code(String b2$address1_code) {
	    this.b2$address1_code = b2$address1_code;
	}
	/**
	 * 市区町村コードを取得します。
	 * @return 市区町村コード
	 */
	public String getB2$address2_code() {
	    return b2$address2_code;
	}
	/**
	 * 市区町村コードを設定します。
	 * @param b2$address2_code 市区町村コード
	 */
	public void setB2$address2_code(String b2$address2_code) {
	    this.b2$address2_code = b2$address2_code;
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
	 * 市区郡町村を取得します。
	 * @return 市区郡町村
	 */
	public String getB2$address2() {
	    return b2$address2;
	}
	/**
	 * 市区郡町村を設定します。
	 * @param b2$address2 市区郡町村
	 */
	public void setB2$address2(String b2$address2) {
	    this.b2$address2 = b2$address2;
	}
	/**
	 * 郵便番号を取得します。
	 * @return 郵便番号
	 */
	public String getB2$zip_code() {
	    return b2$zip_code;
	}
	/**
	 * 郵便番号を設定します。
	 * @param b2$zip_code 郵便番号
	 */
	public void setB2$zip_code(String b2$zip_code) {
	    this.b2$zip_code = b2$zip_code;
	}
	/**
	 * 番地を取得します。
	 * @return 番地
	 */
	public String getB2$address3() {
	    return b2$address3;
	}
	/**
	 * 番地を設定します。
	 * @param b2$address3 番地
	 */
	public void setB2$address3(String b2$address3) {
	    this.b2$address3 = b2$address3;
	}
	/**
	 * 事業所郵便番号を取得します。
	 * @return 事業所郵便番号
	 */
	public String getB2$j_zip_code() {
	    return b2$j_zip_code;
	}
	/**
	 * 事業所郵便番号を設定します。
	 * @param b2$j_zip_code 事業所郵便番号
	 */
	public void setB2$j_zip_code(String b2$j_zip_code) {
	    this.b2$j_zip_code = b2$j_zip_code;
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
	 * 適用終了日を取得します。
	 * @return 適用終了日
	 */
	public String getB2$date_to() {
	    return b2$date_to;
	}
	/**
	 * 適用終了日を設定します。
	 * @param b2$date_to 適用終了日
	 */
	public void setB2$date_to(String b2$date_to) {
	    this.b2$date_to = b2$date_to;
	}
}
