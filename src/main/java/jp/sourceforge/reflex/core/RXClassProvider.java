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

import java.lang.reflect.Field;
import java.util.List;

//import com.google.appengine.api.datastore.Text;
import com.thoughtworks.xstream.converters.reflection.ObjectAccessException;

public class RXClassProvider extends RXUtil {

	public Object newInstance(Class type) {
		try {
			return type.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean propertyDefinedInClass(String node, Class source) {

		Field[] fields = source.getFields();
		String fldname = node2fld(node);

		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().equals(fldname))
				return true;
		}
		return false;
	}

	public void writeProperty(Object object, String node, Object value) {
		try {

			String fldname = node2fld(node);
			Field field = object.getClass().getField(fldname);

			if (isList(field.getType())) {
				List listobj = (List) field.get(object);
				if (listobj != null) {
					// ((List) value).addAll(listobj); // debug 2005/10/20
					listobj.addAll((List) value);
					value = listobj;
				}
			}
			if (isText(field.getType())) {
				if (!isText(value.getClass())) {
					value = newTextInstance((String)value);
				}
			}
			field.set(object, value);
		} catch (IllegalArgumentException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		} catch (SecurityException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		} catch (NoSuchFieldException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		} catch (IllegalAccessException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		}
	}

	public Class getPropertyType(Object object, String node) {

		try {
			return getClassByNode(object, node);

		} catch (IllegalArgumentException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		} catch (SecurityException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		} catch (NoSuchFieldException e) {
			throw new ObjectAccessException("Could not set property "
					+ object.getClass() + "." + node, e);
		}
	}

	public Class getClassByNode(Object object, String node)
			throws SecurityException, NoSuchFieldException {

		String fldname = node2fld(node);

		try {
			Class cls = object.getClass().getField(fldname).getType();
			return cls;
		} catch (Exception e) {
			int s = fldname.indexOf("$");
			if (s > 0)
				fldname = fldname.substring(s + 1);
			return object.getClass().getField(fldname).getType();
		}

	}

}
