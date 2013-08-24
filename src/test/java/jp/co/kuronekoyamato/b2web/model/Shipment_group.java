package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Shipment_group implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** グループ番号 */
	public String b2$group_no;
	/** 出荷先グループ名 */
	public String b2$shipment_group_name;
	/** 出荷先グループメモ */
	public String b2$shipment_group_memo;



	/**
	 * グループ番号を取得します。
	 * @return グループ番号
	 */
	public String getB2$group_no() {
	    return b2$group_no;
	}
	/**
	 * グループ番号を設定します。
	 * @param b2$group_no グループ番号
	 */
	public void setB2$group_no(String b2$group_no) {
	    this.b2$group_no = b2$group_no;
	}
	/**
	 * 出荷先グループ名を取得します。
	 * @return 出荷先グループ名
	 */
	public String getB2$shipment_group_name() {
	    return b2$shipment_group_name;
	}
	/**
	 * 出荷先グループ名を設定します。
	 * @param b2$shipment_group_name 出荷先グループ名
	 */
	public void setB2$shipment_group_name(String b2$shipment_group_name) {
	    this.b2$shipment_group_name = b2$shipment_group_name;
	}
	/**
	 * 出荷先グループメモを取得します。
	 * @return 出荷先グループメモ
	 */
	public String getB2$shipment_group_memo() {
	    return b2$shipment_group_memo;
	}
	/**
	 * 出荷先グループメモを設定します。
	 * @param b2$shipment_group_memo 出荷先グループメモ
	 */
	public void setB2$shipment_group_memo(String b2$shipment_group_memo) {
	    this.b2$shipment_group_memo = b2$shipment_group_memo;
	}
}
