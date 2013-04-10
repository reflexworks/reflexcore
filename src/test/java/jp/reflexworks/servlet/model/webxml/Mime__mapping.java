package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Mime__mapping implements Serializable {

	public String extension;
	public String mime__type;

	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getMime__type() {
		return mime__type;
	}
	public void setMime__type(String mime__type) {
		this.mime__type = mime__type;
	}

	@Override
	public String toString() {
		return "Mime__mapping [extension=" + extension + ", mime__type="
				+ mime__type + "]";
	}
	
}
