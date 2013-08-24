package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Deffered_status_list implements Serializable{

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 未発行状況 */
	public List<Deffered_status> b2$deffered_status;



	/**
	 * 未発行状況を取得します。
	 * @return 未発行状況
	 */
	public List<Deffered_status> getB2$deffered_status() {
	    return b2$deffered_status;
	}

	/**
	 * 未発行状況を設定します。
	 * @param b2$deffered_status 未発行状況
	 */
	public void setB2$deffered_status(List<Deffered_status> b2$deffered_status) {
	    this.b2$deffered_status = b2$deffered_status;
	}
}
