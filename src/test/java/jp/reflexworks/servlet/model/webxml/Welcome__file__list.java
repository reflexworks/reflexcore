package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Welcome__file__list implements Serializable {

	public List<Welcome__file> welcome__file;

	public List<Welcome__file> getWelcome__file() {
		return welcome__file;
	}
	public void setWelcome__file(List<Welcome__file> welcome__file) {
		this.welcome__file = welcome__file;
	}

	@Override
	public String toString() {
		return "Welcome__file__list [welcome__file=" + welcome__file + "]";
	}

}
