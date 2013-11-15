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

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.mapper.MapperWrapper;

/**
 * A mapper strips the packagename of fieldnames in the XML
 */
public class RXMapper extends MapperWrapper {

	public ClassMapper wrapped;
	private ResourceMapper rx;
	private RXUtil rxutil;
	private final ThreadLocal<LinkedHashMap<String,String>> packagemap = 
			new ThreadLocal<LinkedHashMap<String,String>>();
	private final ThreadLocal<HashMap<String,String>> namespacemap = 
			new ThreadLocal<HashMap<String,String>>();

	private final ThreadLocal<Integer> printns = 
			new ThreadLocal<Integer>();		// 1(true), 0(false), -1(N/A)

	public RXMapper(ClassMapper wrapped, ResourceMapper rx) {
		super(wrapped);
		this.wrapped = wrapped;
		this.rx = rx;
		this.rxutil = new RXUtil();
	}

	public Integer getPrintns() {
		return printns.get();
	}

	public void setPrintnsFalse() {
		printns.set(new Integer(0));
	}

	public LinkedHashMap<String, String> getJo_packagemap() {
		return packagemap.get();
	}

	public void setJo_packagemap(LinkedHashMap<String, String> jo_packagemap) {
		packagemap.set(jo_packagemap);
		copyNamespacemap();
		printns.set(new Integer(1));
	}

	public void setJo_packagemap(LinkedHashMap<String, String> jo_packagemap,boolean printns) {
		packagemap.set(jo_packagemap);
		copyNamespacemap();
		if (printns) {
			this.printns.set(new Integer(1));
		}else {
			this.printns.set(new Integer(-1));
		}
	}

	public void copyNamespacemap() {

		namespacemap.set(new HashMap<String,String>());

		for (Map.Entry<String, String> e:packagemap.get().entrySet()) {
			
			String ns = e.getValue();

			if (ns!=null) {
			int i = ns.indexOf("=");
			String prefix = "";

			if (i>0) {
				prefix = ns.substring(0,i)+":";
				ns = ns.substring(i+1);
			}
			
			// 既に登録済でなければ登録
			if (getJo_namespacemap().get(ns)==null) {
				getJo_namespacemap().put(ns,prefix);
			}
			}			
		}
	}


	public HashMap<String, String> getJo_namespacemap() {
		HashMap<String,String> nmap = namespacemap.get();
		if (nmap==null) {
			namespacemap.set(new HashMap<String,String>());
			nmap = namespacemap.get();
		}
		return nmap;
	}
	
	public void clearJo_namespacemap() {
		namespacemap.remove();
	}

	public String serializedClass(Class type) {

		String prefix = "";
		try {
			if (getPrintns()>=0) {
				prefix = getPrefix(type);
				if (prefix==null) {
					prefix = "";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (type.getName().indexOf(".") < -1) {
			String temp = type.getName();
			if (!rx.isCamel)
				temp = temp.substring(0, 1).toLowerCase() + temp.substring(1); // to
			// LowerCase
			return prefix + temp;
		} else {
			String temp = type.getName().substring(
					type.getName().lastIndexOf(".") + 1);
			if (!rx.isCamel)
				temp = temp.substring(0, 1).toLowerCase() + temp.substring(1); // to
			// LowerCase
			return rxutil.fld2node(prefix + temp);
		}
	}

	public String getPrefix(String classname) {
		
		int e = classname.lastIndexOf(".");
		String packagename = classname.substring(0, e);

		String prefix = (String) getJo_packagemap().get(packagename);
		if (prefix!=null) {
		int i = prefix.indexOf("=");

		if (i>0) {
			prefix = prefix.substring(0,i)+":";
		}else {
			prefix="";
		}
		}
		return prefix;
	}
	
	public String getPrefix(Class type) {

		Class superclass =type.getSuperclass();
		if (!superclass.getName().equals("java.lang.Object")) {
			type = superclass;
		}
		
		return getPrefix(type.getName());

	}

	public String getNamespace(Class type) {

		int e = type.getName().lastIndexOf(".");
		String packagename = type.getName().substring(0, e);
		String ns = (String) getJo_packagemap().get(packagename);
		return cutPrefixFromNs(ns);

	}

	private String cutPrefixFromNs(String ns) {
		if (ns!=null) {
		int i = ns.indexOf("=");

		if (i>0) {
			ns = ns.substring(i+1);
		}	
		}
		return ns;
	}
	
	
	public Class realClass(String elementName) {

		int idx = elementName.indexOf(":"); // to handle namespace of Class
		String prefix = "";
		String classname = "";

		if (idx > 0) {
			prefix = elementName.substring(0, idx);
			elementName = elementName.substring(idx + 1);
		}
		if (!rx.isCamel)
			classname = elementName.substring(0, 1).toUpperCase()
					+ elementName.substring(1); // to UpperCase

		classname = rxutil.node2fld(classname).substring(1);

//		String namespace = getNamespace(prefix, classname);
//		String jo_packagename = getPackagename(namespace);

//		if (namespace == null || (namespace != null && namespace.equals("")))
		String	jo_packagename = findPackagename(classname);

		if (jo_packagename != null) {
			return wrapped.realClass(jo_packagename + "." + classname);
		} else {
			return wrapped.realClass(classname);

		}
	}
	
	public String findPackagename(String classname) {

		Iterator iter = getJo_packagemap().keySet().iterator();
		while (iter.hasNext()) {
			String packagename = (String) iter.next();
			String validname = packagename + "." + classname;

			try {
//				Object obj = Class.forName(validname).newInstance();
				Object obj = wrapped.lookupType(validname);
				if (obj != null)
					return packagename;

			} catch (Exception e) {
				// continue;
			}

		}
		return null;

	}
/* 不具合
	public String getNamespace(String prefix, String classname) {

		try {
			Class type = Class.forName(findPackagename(classname) + "."
					+ classname);

			Field[] fields = type.getFields();
			for (int i = 0; i < fields.length; i++) {

				String fldname = fields[i].getName();
				if (prefix.equals(""))
					prefix = "_$xmlns";
				else
					prefix = "_$xmlns$" + prefix;

				if (fldname.startsWith(prefix)) {
					Field fld = type.getField(fldname);
					String _namespace = (String) fld.get(fld);
					return _namespace;
				}
			}

		} catch (Exception e) {
		}
		return null;

	}
*/
	public String realMember(Class type, String serialized) {
		return serialized;
	}

	public String getPackagename(String namespace) {

		Iterator iter = getJo_packagemap().entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();

			// entry.getValue()には {prefix}= が含まれるので除外して比較
			if (namespace!=null&&cutPrefixFromNs((String)entry.getValue()).equals(namespace)) {
				return (String) entry.getKey();
			}
		}
		return null;

	}

	public ResourceMapper getRx() {
		return rx;
	}

	public void setRx(ResourceMapper rx) {
		this.rx = rx;
	}

	public RXUtil getRxutil() {
		return rxutil;
	}

	public void setRxutil(RXUtil rxutil) {
		this.rxutil = rxutil;
	}

}
