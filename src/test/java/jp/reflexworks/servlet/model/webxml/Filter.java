package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Filter implements Serializable {
	
	public String icon;
	public String filter__name;
	public String display__name;
	public String description;
	public String filter__class;
	public List<Init__param> init__param;

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getFilter__name() {
		return filter__name;
	}
	public void setFilter__name(String filter__name) {
		this.filter__name = filter__name;
	}
	public String getDisplay__name() {
		return display__name;
	}
	public void setDisplay__name(String display__name) {
		this.display__name = display__name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFilter__class() {
		return filter__class;
	}
	public void setFilter__class(String filter__class) {
		this.filter__class = filter__class;
	}
	public List<Init__param> getInit__param() {
		return init__param;
	}
	public void setInit__param(List<Init__param> init__param) {
		this.init__param = init__param;
	}

	@Override
	public String toString() {
		return "Filter [icon=" + icon + ", filter__name=" + filter__name
				+ ", display__name=" + display__name + ", description="
				+ description + ", filter__class=" + filter__class
				+ ", init__param=" + init__param + "]";
	}
	
}
