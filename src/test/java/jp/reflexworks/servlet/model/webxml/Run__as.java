package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Run__as implements Serializable {
	
	public String description;
	public String role__name;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRole__name() {
		return role__name;
	}
	public void setRole__name(String role__name) {
		this.role__name = role__name;
	}

	@Override
	public String toString() {
		return "Run__as [description=" + description + ", role__name="
				+ role__name + "]";
	}

}
