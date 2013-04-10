package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Servlet__mapping implements Serializable {

	public String servlet__name;
	public List<Url__pattern> url__pattern;

	public String getServlet__name() {
		return servlet__name;
	}
	public void setServlet__name(String servlet__name) {
		this.servlet__name = servlet__name;
	}
	public List<Url__pattern> getUrl__pattern() {
		return url__pattern;
	}
	public void setUrl__pattern(List<Url__pattern> url__pattern) {
		this.url__pattern = url__pattern;
	}

	@Override
	public String toString() {
		return "Servlet__mapping [servlet__name=" + servlet__name
				+ ", url__pattern=" + url__pattern + "]";
	}
	
}
