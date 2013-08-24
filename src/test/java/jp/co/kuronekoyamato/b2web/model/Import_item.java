package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Import_item implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 取込み種別 */
	public String b2$import_type;
	/** 項目名 */
	public String b2$entity_item_name;
	/** 入力データ形式 */
	public String b2$data_type;
	/** 入力文字形式 */
	public String b2$data_pattern;
	/** 入力データ長 */
	public String b2$data_length;
	/** 未セット時既定値 */
	public String b2$dafault_value;



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
	 * 項目名を取得します。
	 * @return 項目名
	 */
	public String getB2$entity_item_name() {
	    return b2$entity_item_name;
	}
	/**
	 * 項目名を設定します。
	 * @param b2$entity_item_name 項目名
	 */
	public void setB2$entity_item_name(String b2$entity_item_name) {
	    this.b2$entity_item_name = b2$entity_item_name;
	}
	/**
	 * 入力データ形式を取得します。
	 * @return 入力データ形式
	 */
	public String getB2$data_type() {
	    return b2$data_type;
	}
	/**
	 * 入力データ形式を設定します。
	 * @param b2$data_type 入力データ形式
	 */
	public void setB2$data_type(String b2$data_type) {
	    this.b2$data_type = b2$data_type;
	}
	/**
	 * 入力文字形式を取得します。
	 * @return 入力文字形式
	 */
	public String getB2$data_pattern() {
	    return b2$data_pattern;
	}
	/**
	 * 入力文字形式を設定します。
	 * @param b2$data_pattern 入力文字形式
	 */
	public void setB2$data_pattern(String b2$data_pattern) {
	    this.b2$data_pattern = b2$data_pattern;
	}
	/**
	 * 入力データ長を取得します。
	 * @return 入力データ長
	 */
	public String getB2$data_length() {
	    return b2$data_length;
	}
	/**
	 * 入力データ長を設定します。
	 * @param b2$data_length 入力データ長
	 */
	public void setB2$data_length(String b2$data_length) {
	    this.b2$data_length = b2$data_length;
	}
	/**
	 * 未セット時既定値を取得します。
	 * @return 未セット時既定値
	 */
	public String getB2$dafault_value() {
	    return b2$dafault_value;
	}
	/**
	 * 未セット時既定値を設定します。
	 * @param b2$dafault_value 未セット時既定値
	 */
	public void setB2$dafault_value(String b2$dafault_value) {
	    this.b2$dafault_value = b2$dafault_value;
	}
}
