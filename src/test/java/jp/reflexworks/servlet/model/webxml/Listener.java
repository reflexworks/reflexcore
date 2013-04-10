package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Listener implements Serializable {

	public String listener__class;

	public String getListener__class() {
		return listener__class;
	}
	public void setListener__class(String listener__class) {
		this.listener__class = listener__class;
	}

	@Override
	public String toString() {
		return "Listener [listener__class=" + listener__class + "]";
	}
	
}
