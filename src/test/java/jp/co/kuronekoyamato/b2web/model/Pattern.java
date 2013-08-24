package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Pattern implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 連番 */
	public String b2$seqno;
	/** 紐付け順序 */
	public String b2$add_seqno;
	/** 項目名 */
	public String b2$entity_item_name;
	/** 固定値 */
	public String b2$fixed_value;



	/**
	 * 連番を取得します。
	 * @return 連番
	 */
	public String getB2$seqno() {
	    return b2$seqno;
	}
	/**
	 * 連番を設定します。
	 * @param b2$seqno 連番
	 */
	public void setB2$seqno(String b2$seqno) {
	    this.b2$seqno = b2$seqno;
	}
	/**
	 * 固定値を取得します。
	 * @return 固定値
	 */
	public String getB2$fixed_value() {
	    return b2$fixed_value;
	}
	/**
	 * 固定値を設定します。
	 * @param b2$fixed_value 固定値
	 */
	public void setB2$fixed_value(String b2$fixed_value) {
	    this.b2$fixed_value = b2$fixed_value;
	}
	/**
	 * 紐付け順序を取得します。
	 * @return 紐付け順序
	 */
	public String getB2$add_seqno() {
	    return b2$add_seqno;
	}
	/**
	 * 紐付け順序を設定します。
	 * @param b2$add_seqno 紐付け順序
	 */
	public void setB2$add_seqno(String b2$add_seqno) {
	    this.b2$add_seqno = b2$add_seqno;
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
}
