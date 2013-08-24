package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Master_share implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 共有分類コード */
	public String b2$with_code_ext;



	/**
	 * 共有分類コードを取得します。
	 * @return 共有分類コード
	 */
	public String getB2$with_code_ext() {
	    return b2$with_code_ext;
	}

	/**
	 * 共有分類コードを設定します。
	 * @param b2$with_code_ext 共有分類コード
	 */
	public void setB2$with_code_ext(String b2$with_code_ext) {
	    this.b2$with_code_ext = b2$with_code_ext;
	}
}
