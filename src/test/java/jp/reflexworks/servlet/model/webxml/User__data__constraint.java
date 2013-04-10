package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class User__data__constraint implements Serializable {

	public String description;
	public String transport__guarantee;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransport__guarantee() {
		return transport__guarantee;
	}
	public void setTransport__guarantee(String transport__guarantee) {
		this.transport__guarantee = transport__guarantee;
	}

	@Override
	public String toString() {
		return "User__data__constraint [description=" + description
				+ ", transport__guarantee=" + transport__guarantee + "]";
	}

}
