package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Master_share_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** データ共有範囲 */
	public List<Master_share> b2$master_share;

	/**
	 * データ共有範囲を取得します。
	 * @return データ共有範囲
	 */
	public List<Master_share> getB2$master_share() {
	    return b2$master_share;
	}

	/**
	 * データ共有範囲を設定します。
	 * @param b2$master_share データ共有範囲
	 */
	public void setB2$master_share(List<Master_share> b2$master_share) {
	    this.b2$master_share = b2$master_share;
	}
}
