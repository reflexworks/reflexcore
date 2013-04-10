package jp.co.reflexworks.invoice.model;

import java.io.Serializable;
import java.util.List;

public class Item_list implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 明細情報 */
	public List<Item> item; // 明細情報

	public List<Item> getItem() {
		return item;
	}

	public void setItem(List<Item> item) {
		this.item = item;
	}

}
