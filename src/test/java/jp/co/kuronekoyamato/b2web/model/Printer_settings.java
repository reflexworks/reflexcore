package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Printer_settings implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** プリンタ種別 */
	public String b2$printer_type;
	/** フリーデザイン左 */
	public Print_design_left print_design_left;
	/** フリーデザイン右 */
	public Print_design_right print_design_right;



	/**
	 * プリンタ種別を取得します。
	 * @return プリンタ種別
	 */
	public String getB2$printer_type() {
	    return b2$printer_type;
	}
	/**
	 * プリンタ種別を設定します。
	 * @param b2$printer_type プリンタ種別
	 */
	public void setB2$printer_type(String b2$printer_type) {
	    this.b2$printer_type = b2$printer_type;
	}
	/**
	 * フリーデザイン左を取得します。
	 * @return フリーデザイン左
	 */
	public Print_design_left getPrint_design_left() {
	    return print_design_left;
	}
	/**
	 * フリーデザイン左を設定します。
	 * @param print_design_left フリーデザイン左
	 */
	public void setPrint_design_left(Print_design_left print_design_left) {
	    this.print_design_left = print_design_left;
	}
	/**
	 * フリーデザイン右を取得します。
	 * @return フリーデザイン右
	 */
	public Print_design_right getPrint_design_right() {
	    return print_design_right;
	}
	/**
	 * フリーデザイン右を設定します。
	 * @param print_design_right フリーデザイン右
	 */
	public void setPrint_design_right(Print_design_right print_design_right) {
	    this.print_design_right = print_design_right;
	}
}
