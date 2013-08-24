package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Image_choice implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** パス */
	public String b2$filepath;
	/** カテゴリ */
	public String b2$category;
	/** ファイル名 */
	public String b2$image_filename;
	/** 表示順 */
	public String b2$display_order;
	/** タイトル */
	public String b2$image_title;


	/**
	 * パスを取得します。
	 * @return パス
	 */
	public String getB2$filepath() {
	    return b2$filepath;
	}
	/**
	 * パスを設定します。
	 * @param b2$filepath パス
	 */
	public void setB2$filepath(String b2$filepath) {
	    this.b2$filepath = b2$filepath;
	}
	/**
	 * カテゴリを取得します。
	 * @return カテゴリ
	 */
	public String getB2$category() {
	    return b2$category;
	}
	/**
	 * カテゴリを設定します。
	 * @param b2$category カテゴリ
	 */
	public void setB2$category(String b2$category) {
	    this.b2$category = b2$category;
	}
	/**
	 * ファイル名を取得します。
	 * @return ファイル名
	 */
	public String getB2$image_filename() {
	    return b2$image_filename;
	}
	/**
	 * ファイル名を設定します。
	 * @param b2$image_filename ファイル名
	 */
	public void setB2$image_filename(String b2$image_filename) {
	    this.b2$image_filename = b2$image_filename;
	}
	/**
	 * 表示順を取得します。
	 * @return 表示順
	 */
	public String getB2$display_order() {
	    return b2$display_order;
	}
	/**
	 * 表示順を設定します。
	 * @param b2$display_order 表示順
	 */
	public void setB2$display_order(String b2$display_order) {
	    this.b2$display_order = b2$display_order;
	}
	/**
	 * タイトルを取得します。
	 * @return タイトル
	 */
	public String getB2$image_title() {
	    return b2$image_title;
	}
	/**
	 * タイトルを設定します。
	 * @param b2$image_title タイトル
	 */
	public void setB2$image_title(String b2$image_title) {
	    this.b2$image_title = b2$image_title;
	}

}
