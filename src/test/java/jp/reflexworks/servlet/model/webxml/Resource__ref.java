package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Resource__ref implements Serializable {

	public String description;
	public String res__ref__name;
	public String res__type;
	public String res__auth;
	public String res__sharing__scope;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRes__ref__name() {
		return res__ref__name;
	}
	public void setRes__ref__name(String res__ref__name) {
		this.res__ref__name = res__ref__name;
	}
	public String getRes__type() {
		return res__type;
	}
	public void setRes__type(String res__type) {
		this.res__type = res__type;
	}
	public String getRes__auth() {
		return res__auth;
	}
	public void setRes__auth(String res__auth) {
		this.res__auth = res__auth;
	}
	public String getRes__sharing__scope() {
		return res__sharing__scope;
	}
	public void setRes__sharing__scope(String res__sharing__scope) {
		this.res__sharing__scope = res__sharing__scope;
	}

	@Override
	public String toString() {
		return "Resource__ref [description=" + description
				+ ", res__ref__name=" + res__ref__name + ", res__type="
				+ res__type + ", res__auth=" + res__auth
				+ ", res__sharing__scope=" + res__sharing__scope + "]";
	}

}
