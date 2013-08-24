package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Status_count implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 発払い・発行枚数 */
	public Hatsu_issued hatsu_issued;
	/** 発払い・未発行枚数 */
	public Hatsu_new hatsu_new;
	/** 発払い・出荷データ登録件数 */
	public Hatsu_shipped hatsu_shipped;
	/** コレクト・発行枚数 */
	public Collect_issued collect_issued;
	/** コレクト・未発行枚数 */
	public Collect_new collect_new;
	/** コレクト・出荷データ登録件数 */
	public Collect_shipped collect_shipped;
	/** メール便・発行枚数 */
	public String b2$mailbin_issued;
	/** メール便・未発行枚数 */
	public String b2$mailbin_new;
	/** メール便・出荷データ登録件数 */
	public String b2$mailbin_shipped;
	/** メール便速達・発行枚数 */
	public String b2$mailbinexp_issued;
	/** メール便速達・未発行枚数 */
	public String b2$mailbinexp_new;
	/** メール便速達・出荷データ登録件数 */
	public String b2$mailbinexp_shipped;
	/** 未発行状況リスト */
	public Deffered_status_list deffered_status_list;



	/**
	 * 発払い・発行枚数を取得します。
	 * @return 発払い・発行枚数
	 */
	public Hatsu_issued getHatsu_issued() {
	    return hatsu_issued;
	}
	/**
	 * 発払い・発行枚数を設定します。
	 * @param hatsu_issued 発払い・発行枚数
	 */
	public void setHatsu_issued(Hatsu_issued hatsu_issued) {
	    this.hatsu_issued = hatsu_issued;
	}
	/**
	 * 発払い・未発行枚数を取得します。
	 * @return 発払い・未発行枚数
	 */
	public Hatsu_new getHatsu_new() {
	    return hatsu_new;
	}
	/**
	 * 発払い・未発行枚数を設定します。
	 * @param hatsu_new 発払い・未発行枚数
	 */
	public void setHatsu_new(Hatsu_new hatsu_new) {
	    this.hatsu_new = hatsu_new;
	}
	/**
	 * 発払い・出荷データ登録件数を取得します。
	 * @return 発払い・出荷データ登録件数
	 */
	public Hatsu_shipped getHatsu_shipped() {
	    return hatsu_shipped;
	}
	/**
	 * 発払い・出荷データ登録件数を設定します。
	 * @param hatsu_shipped 発払い・出荷データ登録件数
	 */
	public void setHatsu_shipped(Hatsu_shipped hatsu_shipped) {
	    this.hatsu_shipped = hatsu_shipped;
	}
	/**
	 * コレクト・発行枚数を取得します。
	 * @return コレクト・発行枚数
	 */
	public Collect_issued getCollect_issued() {
	    return collect_issued;
	}
	/**
	 * コレクト・発行枚数を設定します。
	 * @param collect_issued コレクト・発行枚数
	 */
	public void setCollect_issued(Collect_issued collect_issued) {
	    this.collect_issued = collect_issued;
	}
	/**
	 * コレクト・未発行枚数を取得します。
	 * @return コレクト・未発行枚数
	 */
	public Collect_new getCollect_new() {
	    return collect_new;
	}
	/**
	 * コレクト・未発行枚数を設定します。
	 * @param collect_new コレクト・未発行枚数
	 */
	public void setCollect_new(Collect_new collect_new) {
	    this.collect_new = collect_new;
	}
	/**
	 * コレクト・出荷データ登録件数を取得します。
	 * @return コレクト・出荷データ登録件数
	 */
	public Collect_shipped getCollect_shipped() {
	    return collect_shipped;
	}
	/**
	 * コレクト・出荷データ登録件数を設定します。
	 * @param collect_shipped コレクト・出荷データ登録件数
	 */
	public void setCollect_shipped(Collect_shipped collect_shipped) {
	    this.collect_shipped = collect_shipped;
	}
	/**
	 * メール便・発行枚数を取得します。
	 * @return メール便・発行枚数
	 */
	public String getB2$mailbin_issued() {
	    return b2$mailbin_issued;
	}
	/**
	 * メール便・発行枚数を設定します。
	 * @param b2$mailbin_issued メール便・発行枚数
	 */
	public void setB2$mailbin_issued(String b2$mailbin_issued) {
	    this.b2$mailbin_issued = b2$mailbin_issued;
	}
	/**
	 * メール便・未発行枚数を取得します。
	 * @return メール便・未発行枚数
	 */
	public String getB2$mailbin_new() {
	    return b2$mailbin_new;
	}
	/**
	 * メール便・未発行枚数を設定します。
	 * @param b2$mailbin_new メール便・未発行枚数
	 */
	public void setB2$mailbin_new(String b2$mailbin_new) {
	    this.b2$mailbin_new = b2$mailbin_new;
	}
	/**
	 * メール便・出荷データ登録件数を取得します。
	 * @return メール便・出荷データ登録件数
	 */
	public String getB2$mailbin_shipped() {
	    return b2$mailbin_shipped;
	}
	/**
	 * メール便・出荷データ登録件数を設定します。
	 * @param b2$mailbin_shipped メール便・出荷データ登録件数
	 */
	public void setB2$mailbin_shipped(String b2$mailbin_shipped) {
	    this.b2$mailbin_shipped = b2$mailbin_shipped;
	}
	/**
	 * メール便速達・発行枚数を取得します。
	 * @return メール便速達・発行枚数
	 */
	public String getB2$mailbinexp_issued() {
	    return b2$mailbinexp_issued;
	}
	/**
	 * メール便速達・発行枚数を設定します。
	 * @param b2$mailbinexp_issued メール便速達・発行枚数
	 */
	public void setB2$mailbinexp_issued(String b2$mailbinexp_issued) {
	    this.b2$mailbinexp_issued = b2$mailbinexp_issued;
	}
	/**
	 * メール便速達・未発行枚数を取得します。
	 * @return メール便速達・未発行枚数
	 */
	public String getB2$mailbinexp_new() {
	    return b2$mailbinexp_new;
	}
	/**
	 * メール便速達・未発行枚数を設定します。
	 * @param b2$mailbinexp_new メール便速達・未発行枚数
	 */
	public void setB2$mailbinexp_new(String b2$mailbinexp_new) {
	    this.b2$mailbinexp_new = b2$mailbinexp_new;
	}
	/**
	 * メール便速達・出荷データ登録件数を取得します。
	 * @return メール便速達・出荷データ登録件数
	 */
	public String getB2$mailbinexp_shipped() {
	    return b2$mailbinexp_shipped;
	}
	/**
	 * メール便速達・出荷データ登録件数を設定します。
	 * @param b2$mailbinexp_shipped メール便速達・出荷データ登録件数
	 */
	public void setB2$mailbinexp_shipped(String b2$mailbinexp_shipped) {
	    this.b2$mailbinexp_shipped = b2$mailbinexp_shipped;
	}
	/**
	 * 未発行状況リストを取得します。
	 * @return 未発行状況リスト
	 */
	public Deffered_status_list getDeffered_status_list() {
	    return deffered_status_list;
	}
	/**
	 * 未発行状況リストを設定します。
	 * @param deffered_status_list 未発行状況リスト
	 */
	public void setDeffered_status_list(Deffered_status_list deffered_status_list) {
	    this.deffered_status_list = deffered_status_list;
	}
}
