package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Servlet implements Serializable {
	
	public String icon;
	public String servlet__name;
	public String display__name;
	public String description;
	public String servlet__class;
	public String jsp__file;
	public List<Init__param> init__param;
	public String load__on__startup;
	public Run__as run__as;
	public List<Security__role__ref> security__role__ref;

	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getServlet__name() {
		return servlet__name;
	}
	public void setServlet__name(String servlet__name) {
		this.servlet__name = servlet__name;
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
	public String getServlet__class() {
		return servlet__class;
	}
	public void setServlet__class(String servlet__class) {
		this.servlet__class = servlet__class;
	}
	public String getJsp__file() {
		return jsp__file;
	}
	public void setJsp__file(String jsp__file) {
		this.jsp__file = jsp__file;
	}
	public List<Init__param> getInit__param() {
		return init__param;
	}
	public void setInit__param(List<Init__param> init__param) {
		this.init__param = init__param;
	}
	public String getLoad__on__startup() {
		return load__on__startup;
	}
	public void setLoad__on__startup(String load__on__startup) {
		this.load__on__startup = load__on__startup;
	}
	public Run__as getRun__as() {
		return run__as;
	}
	public void setRun__as(Run__as run__as) {
		this.run__as = run__as;
	}
	public List<Security__role__ref> getSecurity__role__ref() {
		return security__role__ref;
	}
	public void setSecurity__role__ref(List<Security__role__ref> security__role__ref) {
		this.security__role__ref = security__role__ref;
	}

	@Override
	public String toString() {
		return "Servlet [icon=" + icon + ", servlet__name=" + servlet__name
				+ ", display__name=" + display__name + ", description="
				+ description + ", servlet__class=" + servlet__class
				+ ", jsp__file=" + jsp__file + ", init__param=" + init__param
				+ ", load__on__startup=" + load__on__startup + ", run__as="
				+ run__as + ", security__role__ref=" + security__role__ref
				+ "]";
	}
	
}
