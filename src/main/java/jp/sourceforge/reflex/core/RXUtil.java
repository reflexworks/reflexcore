/**
 *
 *  (C) Copyright Virtual Technology 2005 All Rights Reserved
 *  Licensed Materials - Property of Virtual Technology
 *  
 * This publication is provided "AS IS" WITHOUT WARRANTY OF ANY KIND,
 * EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO, 
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
 * OR NON-INFRINGEMENT.
 * 
 *  @author S.Takezaki 2005/08/25
 *
 *
 */

package jp.sourceforge.reflex.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Pattern;


public class RXUtil {

	private static final String TEXT = "com.google.appengine.api.datastore.Text";

	public String fld2node(String fld) {

		// for reserved word
		if (fld.startsWith("_")) fld = fld.substring(1);
		return replace(fld.replace('$', ':'), "__", "-");

	}
/*
	public String node2fld(String node,boolean underscore) {

		// for reserved word
		return replace((underscore ? "_":""+node).replace(':', '$'), "-", "__");
	}
*/
	public String node2fld(String node) {

		// for reserved word
		return replace(("_"+node).replace(':', '$'), "-", "__");
	}

	// for Java 1.4 users
	public String replace(String org, String src, String tgt) {

		if (org == null) return null;
		else return Pattern.compile(src).matcher(org).replaceAll(tgt);

	}
	
	public boolean isList(Class type) {
		if (type.getName().equals("java.util.List")
				||type.getName().equals("org.datanucleus.sco.backed.List")) {
			return true;
		}
		return false;
	}

	public boolean isText(Class type) {
		if (type.getName().equals(TEXT)) {
			return true;
		}
		return false;
	}

	public String getTextValue(Object source) {
		
    	Method method;
    	try {
    		method = source.getClass().getMethod("getValue", null);
    	} catch (SecurityException e2) {
    		e2.printStackTrace();
    		return "";
    	} catch (NoSuchMethodException e2) {
    		e2.printStackTrace();
    		return "";
    	}
    	
    	Object ret;
    	try {
    		return (String) method.invoke(source, null);
    	} catch (IllegalArgumentException e3) {
    		e3.printStackTrace();
    	} catch (IllegalAccessException e3) {
    		e3.printStackTrace();
    	} catch (InvocationTargetException e3) {
    		e3.getCause().printStackTrace();
    	}
    	
		return "";
	}
	
	public Object newTextInstance(String paramValue) {

		Object obj;
		try {
			Class type = Class.forName(TEXT);
			Constructor ct = type.getConstructor(String.class);
			return ct.newInstance(paramValue); 
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	

}
