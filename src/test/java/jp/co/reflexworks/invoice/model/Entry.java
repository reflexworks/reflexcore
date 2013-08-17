package jp.co.reflexworks.invoice.model;

import java.io.Serializable;

import jp.co.reflexworks.invoice.model.Transaction;
import jp.reflexworks.atom.entry.EntryBase;

public class Entry extends EntryBase implements Serializable {

	private static final long serialVersionUID = 1L;

	// ---- 名前空間　ここから ----
	public Transaction transaction;	// 取引情報
	public My_company my_company; // 自社情報
	public Customer customer; // 顧客情報
	public Item item; // 明細情報
	public Item_list item_list; // 明細情報

	/** 備考情報 */
	public String note;

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public My_company getMy_company() {
		return my_company;
	}

	public void setMy_company(My_company my_company) {
		this.my_company = my_company;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Item_list getItem_list() {
		return item_list;
	}

	public void setItem_list(Item_list item_list) {
		this.item_list = item_list;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Override
	public boolean isValid() {
		return false;
	}
	
	

}
