package jp.reflexworks.servlet.model.webxml;

import java.io.Serializable;

public class Session__config implements Serializable {

	public String session__timeout;

	public String getSession__timeout() {
		return session__timeout;
	}
	public void setSession__timeout(String session__timeout) {
		this.session__timeout = session__timeout;
	}

	@Override
	public String toString() {
		return "Session__config [session__timeout=" + session__timeout + "]";
	}

}
