package jp.co.reflexworks.invoice.model;

import java.io.Serializable;

public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	public String seq;			// 明細No
	public String name;			// 品名
	public String description;	// 詳細
	public String quantity;		// 数量
	public String unit_price;	// 金額
	public String line_total;	// 合計
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getLine_total() {
		return line_total;
	}
	public void setLine_total(String line_total) {
		this.line_total = line_total;
	}

	

}
