package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Error_list implements Serializable {

	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** エラー情報 */
	public List<Error> b2$error;



	/**
	 * エラー情報を取得します。
	 * @return エラー情報
	 */
	public List<Error> getB2$error() {
	    return b2$error;
	}

	/**
	 * エラー情報を設定します。
	 * @param b2$error エラー情報
	 */
	public void setB2$error(List<Error> b2$error) {
	    this.b2$error = b2$error;
	}
}
