package jp.co.reflexworks.invoice.model;

import java.io.Serializable;

public class My_company implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 社名 */
	public String my_name;
	/** 郵便番号 */
	public String my_zip;
	/** 住所 */
	public String my_address;
	/** 電話番号 */
	public String my_telephone;
	public String getMy_name() {
		return my_name;
	}
	public void setMy_name(String my_name) {
		this.my_name = my_name;
	}
	public String getMy_zip() {
		return my_zip;
	}
	public void setMy_zip(String my_zip) {
		this.my_zip = my_zip;
	}
	public String getMy_address() {
		return my_address;
	}
	public void setMy_address(String my_address) {
		this.my_address = my_address;
	}
	public String getMy_telephone() {
		return my_telephone;
	}
	public void setMy_telephone(String my_telephone) {
		this.my_telephone = my_telephone;
	}

	

}
