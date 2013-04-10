package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Taglib implements Serializable {

	public String taglib__uri;
	public String taglib__location;

	public String getTaglib__uri() {
		return taglib__uri;
	}
	public void setTaglib__uri(String taglib__uri) {
		this.taglib__uri = taglib__uri;
	}
	public String getTaglib__location() {
		return taglib__location;
	}
	public void setTaglib__location(String taglib__location) {
		this.taglib__location = taglib__location;
	}

	@Override
	public String toString() {
		return "Taglib [taglib__uri=" + taglib__uri + ", taglib__location="
				+ taglib__location + "]";
	}

}
