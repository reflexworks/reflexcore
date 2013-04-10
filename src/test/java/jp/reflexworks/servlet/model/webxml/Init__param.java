package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Init__param implements Serializable {
	
	public String param__name;
	public String param__value;
	public String description;

	public String getParam__name() {
		return param__name;
	}
	public void setParam__name(String param__name) {
		this.param__name = param__name;
	}
	public String getParam__value() {
		return param__value;
	}
	public void setParam__value(String param__value) {
		this.param__value = param__value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Init__param [param__name=" + param__name + ", param__value="
				+ param__value + ", description=" + description + "]";
	}

}
