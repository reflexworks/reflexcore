package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Request_from_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 発行元 */
	public List<Request_from> b2$request_from;



	/**
	 * 発行元を取得します。
	 * @return 発行元
	 */
	public List<Request_from> getB2$request_from() {
	    return b2$request_from;
	}

	/**
	 * 発行元を設定します。
	 * @param b2$request_from 発行元
	 */
	public void setB2$request_from(List<Request_from> b2$request_from) {
	    this.b2$request_from = b2$request_from;
	}
}
