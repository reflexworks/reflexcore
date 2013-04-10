package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Auth__constraint implements Serializable {

	public String description;
	public List<Role__name> role__name;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Role__name> getRole__name() {
		return role__name;
	}
	public void setRole__name(List<Role__name> role__name) {
		this.role__name = role__name;
	}

	@Override
	public String toString() {
		return "Auth__constraint [description=" + description + ", role__name="
				+ role__name + "]";
	}

}
