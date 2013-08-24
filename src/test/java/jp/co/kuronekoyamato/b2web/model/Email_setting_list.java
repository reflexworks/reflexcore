package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;
import java.util.List;

public class Email_setting_list implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** メール送信先 */
	public List<Email_setting> b2$email_setting;

	/**
	 * メール送信先を取得します。
	 * @return メール送信先
	 */
	public List<Email_setting> getB2$email_setting() {
	    return b2$email_setting;
	}

	/**
	 * メール送信先を設定します。
	 * @param b2$email_setting メール送信先
	 */
	public void setB2$email_setting(List<Email_setting> b2$email_setting) {
	    this.b2$email_setting = b2$email_setting;
	}
}
