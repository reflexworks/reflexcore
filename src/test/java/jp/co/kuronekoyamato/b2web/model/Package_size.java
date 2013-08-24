package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Package_size implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** サイズ品目コード */
	public String b2$package_size_code;
	/** サイズ品目名称 */
	public String b2$package_size_name;



	/**
	 * サイズ品目コードを取得します。
	 * @return サイズ品目コード
	 */
	public String getB2$package_size_code() {
	    return b2$package_size_code;
	}
	/**
	 * サイズ品目コードを設定します。
	 * @param b2$package_size_code サイズ品目コード
	 */
	public void setB2$package_size_code(String b2$package_size_code) {
	    this.b2$package_size_code = b2$package_size_code;
	}
	/**
	 * サイズ品目名称を取得します。
	 * @return サイズ品目名称
	 */
	public String getB2$package_size_name() {
	    return b2$package_size_name;
	}
	/**
	 * サイズ品目名称を設定します。
	 * @param b2$package_size_name サイズ品目名称
	 */
	public void setB2$package_size_name(String b2$package_size_name) {
	    this.b2$package_size_name = b2$package_size_name;
	}
}
