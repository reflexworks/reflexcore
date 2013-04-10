package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;
import java.util.List;

public class Web__resource__collection implements Serializable {

	public String web__resource__name;
	public String description;
	public List<Url__pattern> url__pattern;
	public List<Http__method> http__method;

	public String getWeb__resource__name() {
		return web__resource__name;
	}
	public void setWeb__resource__name(String web__resource__name) {
		this.web__resource__name = web__resource__name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<Url__pattern> getUrl__pattern() {
		return url__pattern;
	}
	public void setUrl__pattern(List<Url__pattern> url__pattern) {
		this.url__pattern = url__pattern;
	}
	public List<Http__method> getHttp__method() {
		return http__method;
	}
	public void setHttp__method(List<Http__method> http__method) {
		this.http__method = http__method;
	}

	@Override
	public String toString() {
		return "Web__resource__collection [web__resource__name="
				+ web__resource__name + ", description=" + description
				+ ", url__pattern=" + url__pattern + ", http__method="
				+ http__method + "]";
	}
	
}
