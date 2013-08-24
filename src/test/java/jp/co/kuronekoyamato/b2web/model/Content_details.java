package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Content_details implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 品名コード1 */
	public String b2$item_code1;
	/** 品名コード2 */
	public String b2$item_code2;
	/** 品名称1 */
	public String b2$item_name1;
	/** 品名称2 */
	public String b2$item_name2;
	/** 荷扱い1 */
	public String b2$handling_information1;
	/** 荷扱い2 */
	public String b2$handling_information2;
	/** 記事 */
	public String b2$note;
	/** 品名コード */
	public String b2$item_code;
	/** 品名称 */
	public String b2$item_name;
	/** 品名エラーフラグ */
	public String b2$item_error_flg;
	/** 共有品名件数 */
	public String b2$share_item_no;


	/**
	 * 品名コード1を取得します。
	 * @return 品名コード1
	 */
	public String getB2$item_code1() {
	    return b2$item_code1;
	}
	/**
	 * 品名コード1を設定します。
	 * @param b2$item_code1 品名コード1
	 */
	public void setB2$item_code1(String b2$item_code1) {
	    this.b2$item_code1 = b2$item_code1;
	}
	/**
	 * 品名コード2を取得します。
	 * @return 品名コード2
	 */
	public String getB2$item_code2() {
	    return b2$item_code2;
	}
	/**
	 * 品名コード2を設定します。
	 * @param b2$item_code2 品名コード2
	 */
	public void setB2$item_code2(String b2$item_code2) {
	    this.b2$item_code2 = b2$item_code2;
	}
	/**
	 * 品名称1を取得します。
	 * @return 品名称1
	 */
	public String getB2$item_name1() {
	    return b2$item_name1;
	}
	/**
	 * 品名称1を設定します。
	 * @param b2$item_name1 品名称1
	 */
	public void setB2$item_name1(String b2$item_name1) {
	    this.b2$item_name1 = b2$item_name1;
	}
	/**
	 * 品名称2を取得します。
	 * @return 品名称2
	 */
	public String getB2$item_name2() {
	    return b2$item_name2;
	}
	/**
	 * 品名称2を設定します。
	 * @param b2$item_name2 品名称2
	 */
	public void setB2$item_name2(String b2$item_name2) {
	    this.b2$item_name2 = b2$item_name2;
	}
	/**
	 * 荷扱い1を取得します。
	 * @return 荷扱い1
	 */
	public String getB2$handling_information1() {
	    return b2$handling_information1;
	}
	/**
	 * 荷扱い1を設定します。
	 * @param b2$handling_information1 荷扱い1
	 */
	public void setB2$handling_information1(String b2$handling_information1) {
	    this.b2$handling_information1 = b2$handling_information1;
	}
	/**
	 * 荷扱い2を取得します。
	 * @return 荷扱い2
	 */
	public String getB2$handling_information2() {
	    return b2$handling_information2;
	}
	/**
	 * 荷扱い2を設定します。
	 * @param b2$handling_information2 荷扱い2
	 */
	public void setB2$handling_information2(String b2$handling_information2) {
	    this.b2$handling_information2 = b2$handling_information2;
	}
	/**
	 * 記事を取得します。
	 * @return 記事
	 */
	public String getB2$note() {
	    return b2$note;
	}
	/**
	 * 記事を設定します。
	 * @param b2$note 記事
	 */
	public void setB2$note(String b2$note) {
	    this.b2$note = b2$note;
	}
	/**
	 * 品名コードを取得します。
	 * @return 品名コード
	 */
	public String getB2$item_code() {
	    return b2$item_code;
	}
	/**
	 * 品名コードを設定します。
	 * @param b2$item_code 品名コード
	 */
	public void setB2$item_code(String b2$item_code) {
	    this.b2$item_code = b2$item_code;
	}
	/**
	 * 品名称を取得します。
	 * @return 品名称
	 */
	public String getB2$item_name() {
	    return b2$item_name;
	}
	/**
	 * 品名称を設定します。
	 * @param b2$item_name 品名称
	 */
	public void setB2$item_name(String b2$item_name) {
	    this.b2$item_name = b2$item_name;
	}
	/**
	 * 品名エラーフラグを取得します。
	 * @return 品名エラーフラグ
	 */
	public String getB2$item_error_flg() {
	    return b2$item_error_flg;
	}
	/**
	 * 品名エラーフラグを設定します。
	 * @param b2$item_error_flg 品名エラーフラグ
	 */
	public void setB2$item_error_flg(String b2$item_error_flg) {
	    this.b2$item_error_flg = b2$item_error_flg;
	}
	/**
	 * 共有品名件数を取得します。
	 * @return 共有品名件数
	 */
	public String getB2$share_item_no() {
	    return b2$share_item_no;
	}
	/**
	 * 共有品名件数を設定します。
	 * @param b2$share_item_no 共有品名件数
	 */
	public void setB2$share_item_no(String b2$share_item_no) {
	    this.b2$share_item_no = b2$share_item_no;
	}

}
