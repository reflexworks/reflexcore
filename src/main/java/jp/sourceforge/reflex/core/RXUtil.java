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
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;


public class RXUtil {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	private static final String TEXT = "com.google.appengine.api.datastore.Text";

	public String fld2node(String fld) {

		// for reserved word
		if (fld.startsWith("_")) fld = fld.substring(1);
		return replace(fld.replace('$', ':'), "__", "-");

	}
	
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
			logger.log(Level.WARNING, e2.getClass().getName(), e2);
    		return "";
    	} catch (NoSuchMethodException e2) {
			logger.log(Level.WARNING, e2.getClass().getName(), e2);
    		return "";
    	}
    	
    	Object ret;
    	try {
    		return (String) method.invoke(source, null);
    	} catch (IllegalArgumentException e3) {
			logger.log(Level.WARNING, e3.getClass().getName(), e3);
    	} catch (IllegalAccessException e3) {
			logger.log(Level.WARNING, e3.getClass().getName(), e3);
    	} catch (InvocationTargetException e3) {
			logger.log(Level.WARNING, e3.getClass().getName(), e3);
    	}
    	
		return "";
	}
	
	public Object newTextInstance(String paramValue) {

		Object obj;
			Class type;
			try {
				type = Class.forName(TEXT);
				Constructor ct = type.getConstructor(String.class);
				return ct.newInstance(paramValue); 
			} catch (ClassNotFoundException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (NoSuchMethodException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (SecurityException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (InstantiationException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (IllegalAccessException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (IllegalArgumentException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (InvocationTargetException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
			
			return null;
	}
	
	

}
