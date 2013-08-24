package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;


public class Import_pattern implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 取込みパターン名 */
	public String b2$import_pattern_name;
	/** 取込み種別 */
	public String b2$import_type;
	/** データ形式 */
	public String b2$source_format;
	/** 開始行 */
	public String b2$first_row;
	/** パターンリスト */
	public Pattern_list pattern_list;



	/**
	 * 取込みパターン名を取得します。
	 * @return 取込みパターン名
	 */
	public String getB2$import_pattern_name() {
	    return b2$import_pattern_name;
	}
	/**
	 * 取込みパターン名を設定します。
	 * @param b2$import_pattern_name 取込みパターン名
	 */
	public void setB2$import_pattern_name(String b2$import_pattern_name) {
	    this.b2$import_pattern_name = b2$import_pattern_name;
	}
	/**
	 * 取込み種別を取得します。
	 * @return 取込み種別
	 */
	public String getB2$import_type() {
	    return b2$import_type;
	}
	/**
	 * 取込み種別を設定します。
	 * @param b2$import_type 取込み種別
	 */
	public void setB2$import_type(String b2$import_type) {
	    this.b2$import_type = b2$import_type;
	}
	/**
	 * データ形式を取得します。
	 * @return データ形式
	 */
	public String getB2$source_format() {
	    return b2$source_format;
	}
	/**
	 * データ形式を設定します。
	 * @param b2$source_format データ形式
	 */
	public void setB2$source_format(String b2$source_format) {
	    this.b2$source_format = b2$source_format;
	}
	/**
	 * 開始行を取得します。
	 * @return 開始行
	 */
	public String getB2$first_row() {
	    return b2$first_row;
	}
	/**
	 * 開始行を設定します。
	 * @param b2$first_row 開始行
	 */
	public void setB2$first_row(String b2$first_row) {
	    this.b2$first_row = b2$first_row;
	}
	/**
	 * パターンリストを取得します。
	 * @return パターンリスト
	 */
	public Pattern_list getPattern_list() {
	    return pattern_list;
	}
	/**
	 * パターンリストを設定します。
	 * @param pattern_list パターンリスト
	 */
	public void setPattern_list(Pattern_list pattern_list) {
	    this.pattern_list = pattern_list;
	}
}
