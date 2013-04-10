package jp.reflexworks.servlet.model.webxml;

import java.util.List;
import java.io.Serializable;

public class Web__app implements Serializable {
	
	public List<Context__param> context__param;
	public List<Filter> filter;
	public List<Filter__mapping> filter__mapping;
	public List<Listener> listener;
	public List<Servlet> servlet;
	public List<Servlet__mapping> servlet__mapping;
	public Session__config session__config;
	public List<Mime__mapping> mime__mapping;
	public Welcome__file__list welcome__file__list;
	public List<Error__page> error__page;
	public List<Taglib> taglib;
	public List<Resource__env__ref> resource__env__ref;
	public List<Resource__ref> resource__ref;
	public List<Security__constraint> security__constraint;
	public Login__config login__config;
	public List<Security__role> security__role;
	public List<Env__entry> env__entry;
	public List<Ejb__ref> ejb__ref;
	public List<Ejb__local__ref> ejb__local__ref;

	public List<Context__param> getContext__param() {
		return context__param;
	}
	public void setContext__param(List<Context__param> context__param) {
		this.context__param = context__param;
	}
	public List<Filter> getFilter() {
		return filter;
	}
	public void setFilter(List<Filter> filter) {
		this.filter = filter;
	}
	public List<Filter__mapping> getFilter__mapping() {
		return filter__mapping;
	}
	public void setFilter__mapping(List<Filter__mapping> filter__mapping) {
		this.filter__mapping = filter__mapping;
	}
	public List<Listener> getListener() {
		return listener;
	}
	public void setListener(List<Listener> listener) {
		this.listener = listener;
	}
	public List<Servlet> getServlet() {
		return servlet;
	}
	public void setServlet(List<Servlet> servlet) {
		this.servlet = servlet;
	}
	public List<Servlet__mapping> getServlet__mapping() {
		return servlet__mapping;
	}
	public void setServlet__mapping(List<Servlet__mapping> servlet__mapping) {
		this.servlet__mapping = servlet__mapping;
	}
	public Session__config getSession__config() {
		return session__config;
	}
	public void setSession__config(Session__config session__config) {
		this.session__config = session__config;
	}
	public List<Mime__mapping> getMime__mapping() {
		return mime__mapping;
	}
	public void setMime__mapping(List<Mime__mapping> mime__mapping) {
		this.mime__mapping = mime__mapping;
	}
	public Welcome__file__list getWelcome__file__list() {
		return welcome__file__list;
	}
	public void setWelcome__file__list(Welcome__file__list welcome__file__list) {
		this.welcome__file__list = welcome__file__list;
	}
	public List<Error__page> getError__page() {
		return error__page;
	}
	public void setError__page(List<Error__page> error__page) {
		this.error__page = error__page;
	}
	public List<Taglib> getTaglib() {
		return taglib;
	}
	public void setTaglib(List<Taglib> taglib) {
		this.taglib = taglib;
	}
	public List<Resource__env__ref> getResource__env__ref() {
		return resource__env__ref;
	}
	public void setResource__env__ref(List<Resource__env__ref> resource__env__ref) {
		this.resource__env__ref = resource__env__ref;
	}
	public List<Resource__ref> getResource__ref() {
		return resource__ref;
	}
	public void setResource__ref(List<Resource__ref> resource__ref) {
		this.resource__ref = resource__ref;
	}
	public List<Security__constraint> getSecurity__constraint() {
		return security__constraint;
	}
	public void setSecurity__constraint(
			List<Security__constraint> security__constraint) {
		this.security__constraint = security__constraint;
	}
	public Login__config getLogin__config() {
		return login__config;
	}
	public void setLogin__config(Login__config login__config) {
		this.login__config = login__config;
	}
	public List<Security__role> getSecurity__role() {
		return security__role;
	}
	public void setSecurity__role(List<Security__role> security__role) {
		this.security__role = security__role;
	}
	public List<Env__entry> getEnv__entry() {
		return env__entry;
	}
	public void setEnv__entry(List<Env__entry> env__entry) {
		this.env__entry = env__entry;
	}
	public List<Ejb__ref> getEjb__ref() {
		return ejb__ref;
	}
	public void setEjb__ref(List<Ejb__ref> ejb__ref) {
		this.ejb__ref = ejb__ref;
	}
	public List<Ejb__local__ref> getEjb__local__ref() {
		return ejb__local__ref;
	}
	public void setEjb__local__ref(List<Ejb__local__ref> ejb__local__ref) {
		this.ejb__local__ref = ejb__local__ref;
	}

	@Override
	public String toString() {
		return "Web__app [context__param=" + context__param + ", filter="
				+ filter + ", filter__mapping=" + filter__mapping
				+ ", listener=" + listener + ", servlet=" + servlet
				+ ", servlet__mapping=" + servlet__mapping
				+ ", session__config=" + session__config + ", mime__mapping="
				+ mime__mapping + ", welcome__file__list="
				+ welcome__file__list + ", error__page=" + error__page
				+ ", taglib=" + taglib + ", resource__env__ref="
				+ resource__env__ref + ", resource__ref=" + resource__ref
				+ ", security__constraint=" + security__constraint
				+ ", login__config=" + login__config + ", security__role="
				+ security__role + ", env__entry=" + env__entry + ", ejb__ref="
				+ ejb__ref + ", ejb__local__ref=" + ejb__local__ref + "]";
	}

}
