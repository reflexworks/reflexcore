package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Login__config implements Serializable {

	public String auth__method;
	public String realm__name;
	public Form__login__config form__login__config;

	public String getAuth__method() {
		return auth__method;
	}
	public void setAuth__method(String auth__method) {
		this.auth__method = auth__method;
	}
	public String getRealm__name() {
		return realm__name;
	}
	public void setRealm__name(String realm__name) {
		this.realm__name = realm__name;
	}
	public Form__login__config getForm__login__config() {
		return form__login__config;
	}
	public void setForm__login__config(Form__login__config form__login__config) {
		this.form__login__config = form__login__config;
	}

	@Override
	public String toString() {
		return "Login__config [auth__method=" + auth__method + ", realm__name="
				+ realm__name + ", form__login__config=" + form__login__config
				+ "]";
	}
	
}
