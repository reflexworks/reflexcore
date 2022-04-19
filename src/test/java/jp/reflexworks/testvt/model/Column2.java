package jp.reflexworks.testvt.model;

import java.io.Serializable;

public class Column2 implements Serializable {

	// ---- VT名前空間定義 ----
	public static String _$xmlns$vt = "http://reflexworks.jp/test/1.0";
	private static final long serialVersionUID = 1L;

	public String vt$color;
	public String vt$size;
	public String vt$memo;

	public String getVt$color() {
		return vt$color;
	}
	public void setVt$color(String vt$color) {
		this.vt$color = vt$color;
	}
	public String getVt$size() {
		return vt$size;
	}
	public void setVt$size(String vt$size) {
		this.vt$size = vt$size;
	}
	public String getVt$memo() {
		return vt$memo;
	}
	public void setVt$memo(String vt$memo) {
		this.vt$memo = vt$memo;
	}
	
}
