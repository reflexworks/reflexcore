package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Env__entry implements Serializable {

	public String description;
	public String env__entry__name;
	public String env__entry__value;
	public String env__entry__type;

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEnv__entry__name() {
		return env__entry__name;
	}
	public void setEnv__entry__name(String env__entry__name) {
		this.env__entry__name = env__entry__name;
	}
	public String getEnv__entry__value() {
		return env__entry__value;
	}
	public void setEnv__entry__value(String env__entry__value) {
		this.env__entry__value = env__entry__value;
	}
	public String getEnv__entry__type() {
		return env__entry__type;
	}
	public void setEnv__entry__type(String env__entry__type) {
		this.env__entry__type = env__entry__type;
	}

	@Override
	public String toString() {
		return "Env__entry [description=" + description + ", env__entry__name="
				+ env__entry__name + ", env__entry__value=" + env__entry__value
				+ ", env__entry__type=" + env__entry__type + "]";
	}
	
}
