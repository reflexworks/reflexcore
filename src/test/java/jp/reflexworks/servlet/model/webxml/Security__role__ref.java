package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Security__role__ref implements Serializable {

	public String description;
	public String role__name;
	public String role__link;

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
	public String getRole__link() {
		return role__link;
	}
	public void setRole__link(String role__link) {
		this.role__link = role__link;
	}
	
	@Override
	public String toString() {
		return "Security__role__ref [description=" + description
				+ ", role__name=" + role__name + ", role__link=" + role__link
				+ "]";
	}
	
}
