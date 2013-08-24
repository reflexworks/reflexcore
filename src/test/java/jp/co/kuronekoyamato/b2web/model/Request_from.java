package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Request_from implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 遠隔地発行元顧客コード */
	public String b2$request_from_code;
	/** 遠隔地発行元分類コード */
	public String b2$request_from_code_ext;
	/** 遠隔地発行元会社名 */
	public String b2$request_from_name;



	/**
	 * 遠隔地発行元顧客コードを取得します。
	 * @return 遠隔地発行元顧客コード
	 */
	public String getB2$request_from_code() {
	    return b2$request_from_code;
	}
	/**
	 * 遠隔地発行元顧客コードを設定します。
	 * @param b2$request_from_code 遠隔地発行元顧客コード
	 */
	public void setB2$request_from_code(String b2$request_from_code) {
	    this.b2$request_from_code = b2$request_from_code;
	}
	/**
	 * 遠隔地発行元分類コードを取得します。
	 * @return 遠隔地発行元分類コード
	 */
	public String getB2$request_from_code_ext() {
	    return b2$request_from_code_ext;
	}
	/**
	 * 遠隔地発行元分類コードを設定します。
	 * @param b2$request_from_code_ext 遠隔地発行元分類コード
	 */
	public void setB2$request_from_code_ext(String b2$request_from_code_ext) {
	    this.b2$request_from_code_ext = b2$request_from_code_ext;
	}
	/**
	 * 遠隔地発行元会社名を取得します。
	 * @return 遠隔地発行元会社名
	 */
	public String getB2$request_from_name() {
	    return b2$request_from_name;
	}
	/**
	 * 遠隔地発行元会社名を設定します。
	 * @param b2$request_from_name 遠隔地発行元会社名
	 */
	public void setB2$request_from_name(String b2$request_from_name) {
	    this.b2$request_from_name = b2$request_from_name;
	}
}
