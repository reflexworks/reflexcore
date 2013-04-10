package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Security__constraint implements Serializable {

	public String description;
	public String display__name;
	public List<Web__resource__collection> web__resource__collection;
	public Auth__constraint auth__constraint;
	public User__data__constraint user__data__constraint;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDisplay__name() {
		return display__name;
	}
	public void setDisplay__name(String display__name) {
		this.display__name = display__name;
	}
	public List<Web__resource__collection> getWeb__resource__collection() {
		return web__resource__collection;
	}
	public void setWeb__resource__collection(
			List<Web__resource__collection> web__resource__collection) {
		this.web__resource__collection = web__resource__collection;
	}
	public Auth__constraint getAuth__constraint() {
		return auth__constraint;
	}
	public void setAuth__constraint(Auth__constraint auth__constraint) {
		this.auth__constraint = auth__constraint;
	}
	public User__data__constraint getUser__data__constraint() {
		return user__data__constraint;
	}
	public void setUser__data__constraint(
			User__data__constraint user__data__constraint) {
		this.user__data__constraint = user__data__constraint;
	}

	@Override
	public String toString() {
		return "Security__constraint [description=" + description
				+ ", display__name=" + display__name
				+ ", web__resource__collection=" + web__resource__collection
				+ ", auth__constraint=" + auth__constraint
				+ ", user__data__constraint=" + user__data__constraint + "]";
	}
	
}
