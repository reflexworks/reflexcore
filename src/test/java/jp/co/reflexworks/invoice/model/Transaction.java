package jp.co.reflexworks.invoice.model;

import java.io.Serializable;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	public String number;		// 見積No
	public String date;			// 発行日
	public String job;			// 件名
	public String subtotal;		// 小計金額
	public String salestax;		// 消費税
	public String total;		// 請求金額

	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(String subtotal) {
		this.subtotal = subtotal;
	}
	public String getSalestax() {
		return salestax;
	}
	public void setSalestax(String salestax) {
		this.salestax = salestax;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}

	

}
