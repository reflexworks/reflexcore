package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Ejb__local__ref implements Serializable {

	public String description;
	public String ejb__ref__name;
	public String ejb__ref__type;
	public String local__home;
	public String local;
	public String ejb__link;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEjb__ref__name() {
		return ejb__ref__name;
	}
	public void setEjb__ref__name(String ejb__ref__name) {
		this.ejb__ref__name = ejb__ref__name;
	}
	public String getEjb__ref__type() {
		return ejb__ref__type;
	}
	public void setEjb__ref__type(String ejb__ref__type) {
		this.ejb__ref__type = ejb__ref__type;
	}
	public String getLocal__home() {
		return local__home;
	}
	public void setLocal__home(String local__home) {
		this.local__home = local__home;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getEjb__link() {
		return ejb__link;
	}
	public void setEjb__link(String ejb__link) {
		this.ejb__link = ejb__link;
	}

	@Override
	public String toString() {
		return "Ejb__local__ref [description=" + description
				+ ", ejb__ref__name=" + ejb__ref__name + ", ejb__ref__type="
				+ ejb__ref__type + ", local__home=" + local__home + ", local="
				+ local + ", ejb__link=" + ejb__link + "]";
	}

}
