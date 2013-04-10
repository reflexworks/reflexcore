package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Form__login__config implements Serializable {

	public String form__login__page;
	public String form__error__page;

	public String getForm__login__page() {
		return form__login__page;
	}
	public void setForm__login__page(String form__login__page) {
		this.form__login__page = form__login__page;
	}
	public String getForm__error__page() {
		return form__error__page;
	}
	public void setForm__error__page(String form__error__page) {
		this.form__error__page = form__error__page;
	}

	@Override
	public String toString() {
		return "Form__login__config [form__login__page=" + form__login__page
				+ ", form__error__page=" + form__error__page + "]";
	}

}
