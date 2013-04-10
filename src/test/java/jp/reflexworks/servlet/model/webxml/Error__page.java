package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Error__page implements Serializable {

	public String error__code;
	public String exception__type;
	public String location;

	public String getError__code() {
		return error__code;
	}
	public void setError__code(String error__code) {
		this.error__code = error__code;
	}
	public String getException__type() {
		return exception__type;
	}
	public void setException__type(String exception__type) {
		this.exception__type = exception__type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Error__page [error__code=" + error__code + ", exception__type="
				+ exception__type + ", location=" + location + "]";
	}
	
}
