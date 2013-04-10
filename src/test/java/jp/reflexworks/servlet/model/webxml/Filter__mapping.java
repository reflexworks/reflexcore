package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Filter__mapping implements Serializable {

	public String filter__name;
	public String url__pattern;
	public String servlet__name;

	public String getFilter__name() {
		return filter__name;
	}
	public void setFilter__name(String filter__name) {
		this.filter__name = filter__name;
	}
	public String getUrl__pattern() {
		return url__pattern;
	}
	public void setUrl__pattern(String url__pattern) {
		this.url__pattern = url__pattern;
	}
	public String getServlet__name() {
		return servlet__name;
	}
	public void setServlet__name(String servlet__name) {
		this.servlet__name = servlet__name;
	}

	@Override
	public String toString() {
		return "Filter__mapping [filter__name=" + filter__name
				+ ", url__pattern=" + url__pattern + ", servlet__name="
				+ servlet__name + "]";
	}
	
}
