package jp.co.kuronekoyamato.b2web.model;

import java.io.Serializable;

public class Invoice implements Serializable {

	// ---- B2名前空間定義 ----
	public static String _$xmlns$b2 = "http://kuronekoyamato.co.jp/b2/1.0";
	private static final long serialVersionUID = 1L;

	/** 請求先・顧客コード */
	public String b2$invoice_code;
	/** 請求先・分類コード */
	public String b2$invoice_code_ext;
	/** 請求先・運賃管理番号 */
	public String b2$invoice_freight_no;
	/** 請求先・ニックネーム */
	public String b2$invoice_name;
	/** コレクトフラグ */
	public String b2$is_collect;
	/** カード決済フラグ */
	public String b2$is_using_credit_card;
	/** 収納代行フラグ */
	public String b2$is_receiving_agent;
	/** 決済QRコード利用開始フラグ */
	public String b2$is_using_qrcode;
	/** 電子マネーフラグ */
	public String b2$is_using_electronic_money;
	/** ペイメント加盟店番号リスト */
	public Payment_list payment_list;



	/**
	 * 請求先・顧客コードを取得します。
	 * @return 請求先・顧客コード
	 */
	public String getB2$invoice_code() {
	    return b2$invoice_code;
	}
	/**
	 * 請求先・顧客コードを設定します。
	 * @param b2$invoice_code 請求先・顧客コード
	 */
	public void setB2$invoice_code(String b2$invoice_code) {
	    this.b2$invoice_code = b2$invoice_code;
	}
	/**
	 * 請求先・分類コードを取得します。
	 * @return 請求先・分類コード
	 */
	public String getB2$invoice_code_ext() {
	    return b2$invoice_code_ext;
	}
	/**
	 * 請求先・分類コードを設定します。
	 * @param b2$invoice_code_ext 請求先・分類コード
	 */
	public void setB2$invoice_code_ext(String b2$invoice_code_ext) {
	    this.b2$invoice_code_ext = b2$invoice_code_ext;
	}
	/**
	 * 請求先・運賃管理番号を取得します。
	 * @return 請求先・運賃管理番号
	 */
	public String getB2$invoice_freight_no() {
	    return b2$invoice_freight_no;
	}
	/**
	 * 請求先・運賃管理番号を設定します。
	 * @param b2$invoice_freight_no 請求先・運賃管理番号
	 */
	public void setB2$invoice_freight_no(String b2$invoice_freight_no) {
	    this.b2$invoice_freight_no = b2$invoice_freight_no;
	}
	/**
	 * 請求先・ニックネームを取得します。
	 * @return 請求先・ニックネーム
	 */
	public String getB2$invoice_name() {
	    return b2$invoice_name;
	}
	/**
	 * 請求先・ニックネームを設定します。
	 * @param b2$invoice_name 請求先・ニックネーム
	 */
	public void setB2$invoice_name(String b2$invoice_name) {
	    this.b2$invoice_name = b2$invoice_name;
	}
	/**
	 * コレクトフラグを取得します。
	 * @return コレクトフラグ
	 */
	public String getB2$is_collect() {
	    return b2$is_collect;
	}
	/**
	 * コレクトフラグを設定します。
	 * @param b2$is_collect コレクトフラグ
	 */
	public void setB2$is_collect(String b2$is_collect) {
	    this.b2$is_collect = b2$is_collect;
	}
	/**
	 * カード決済フラグを取得します。
	 * @return カード決済フラグ
	 */
	public String getB2$is_using_credit_card() {
	    return b2$is_using_credit_card;
	}
	/**
	 * カード決済フラグを設定します。
	 * @param b2$is_using_credit_card カード決済フラグ
	 */
	public void setB2$is_using_credit_card(String b2$is_using_credit_card) {
	    this.b2$is_using_credit_card = b2$is_using_credit_card;
	}
	/**
	 * 収納代行フラグを取得します。
	 * @return 収納代行フラグ
	 */
	public String getB2$is_receiving_agent() {
	    return b2$is_receiving_agent;
	}
	/**
	 * 収納代行フラグを設定します。
	 * @param b2$is_receiving_agent 収納代行フラグ
	 */
	public void setB2$is_receiving_agent(String b2$is_receiving_agent) {
	    this.b2$is_receiving_agent = b2$is_receiving_agent;
	}
	/**
	 * 決済QRコード利用開始フラグを取得します。
	 * @return 決済QRコード利用開始フラグ
	 */
	public String getB2$is_using_qrcode() {
	    return b2$is_using_qrcode;
	}
	/**
	 * 決済QRコード利用開始フラグを設定します。
	 * @param b2$is_using_qrcode 決済QRコード利用開始フラグ
	 */
	public void setB2$is_using_qrcode(String b2$is_using_qrcode) {
	    this.b2$is_using_qrcode = b2$is_using_qrcode;
	}
	/**
	 * 電子マネーフラグを取得します。
	 * @return 電子マネーフラグ
	 */
	public String getB2$is_using_electronic_money() {
	    return b2$is_using_electronic_money;
	}
	/**
	 * 電子マネーフラグを設定します。
	 * @param b2$is_using_electronic_money 電子マネーフラグ
	 */
	public void setB2$is_using_electronic_money(String b2$is_using_electronic_money) {
	    this.b2$is_using_electronic_money = b2$is_using_electronic_money;
	}
	/**
	 * ペイメント加盟店番号リストを取得します。
	 * @return ペイメント加盟店番号リスト
	 */
	public Payment_list getPayment_list() {
	    return payment_list;
	}
	/**
	 * ペイメント加盟店番号リストを設定します。
	 * @param payment_list ペイメント加盟店番号リスト
	 */
	public void setPayment_list(Payment_list payment_list) {
	    this.payment_list = payment_list;
	}

}
