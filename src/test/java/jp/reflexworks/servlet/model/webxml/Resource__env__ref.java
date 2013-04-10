package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Resource__env__ref implements Serializable {

	public String description;
	public String resource__env__ref__name;
	public String resource__env__ref__type;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getResource__env__ref__name() {
		return resource__env__ref__name;
	}
	public void setResource__env__ref__name(String resource__env__ref__name) {
		this.resource__env__ref__name = resource__env__ref__name;
	}
	public String getResource__env__ref__type() {
		return resource__env__ref__type;
	}
	public void setResource__env__ref__type(String resource__env__ref__type) {
		this.resource__env__ref__type = resource__env__ref__type;
	}
	
	@Override
	public String toString() {
		return "Resource__env__ref [description=" + description
				+ ", resource__env__ref__name=" + resource__env__ref__name
				+ ", resource__env__ref__type=" + resource__env__ref__type
				+ "]";
	}

}
