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
import java.util.Locale;

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
	private final ThreadLocal<HashMap<String,String>> nsmap = 
			new ThreadLocal<HashMap<String,String>>();

	private final ThreadLocal<Integer> printns = 
			new ThreadLocal<Integer>();		// 1(true), 0(false), -1(N/A)

	/**
	 * RXMapperのコンストラクタ
	 * @param wrapped
	 * @param rx
	 */
	public RXMapper(ClassMapper wrapped, ResourceMapper rx) {
		super(wrapped);
		this.wrapped = wrapped;
		this.rx = rx;
		this.rxutil = new RXUtil();
	}

	/**
	 * 名前空間を表示するか
	 * @return 1(true), 0(false), -1(N/A)
	 */
	public Integer getPrintns() {
		return printns.get();
	}

	/**
	 * 名前空間を出力しない
	 */
	public void setPrintnsFalse() {
		printns.set(new Integer(0));
	}

	/**
	 * packagemapを取得する
	 * @return packagemap
	 */
	public LinkedHashMap<String, String> getJo_packagemap() {
		return packagemap.get();
	}

	/**
	 * packagemapをセットする
	 * @param jo_packagemap
	 */
	public void setJo_packagemap(LinkedHashMap<String, String> jo_packagemap) {
		packagemap.set(jo_packagemap);
		copynsmap();
		printns.set(new Integer(1));
	}

	/**
	 * packagemapをセットする(名前空間表示指定つき）
	 * @param jo_packagemap
	 */
	public void setJo_packagemap(LinkedHashMap<String, String> jo_packagemap,boolean printns) {
		packagemap.set(jo_packagemap);
		copynsmap();
		if (printns) {
			this.printns.set(new Integer(1));
		}else {
			this.printns.set(new Integer(-1));
		}
	}

	private void copynsmap() {

		nsmap.set(new HashMap<String,String>());

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
			if (getJo_nsmap().get(ns)==null) {
				getJo_nsmap().put(ns,prefix);
			}
			}			
		}
	}


	/**
	 * 名前空間マップ(nsmap)を取得する
	 * @return nsmap
	 */
	public HashMap<String, String> getJo_nsmap() {
		HashMap<String,String> nmap = nsmap.get();
		if (nmap==null) {
			nsmap.set(new HashMap<String,String>());
			nmap = nsmap.get();
		}
		return nmap;
	}
	
	/**
	 * 名前空間マップ(nsmap)を初期化する
	 */
	public void clearJo_nsmap() {
		nsmap.remove();
	}

	/* (非 Javadoc)
	 * @see com.thoughtworks.xstream.mapper.MapperWrapper#serializedClass(java.lang.Class)
	 */
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
			String clsname = type.getName();
			if (!rx.isCamel)
				clsname = clsname.substring(0, 1).toLowerCase(Locale.ENGLISH) + clsname.substring(1); // to
			// LowerCase
			return prefix + clsname;
		} else {
			String clsname = type.getName().substring(
					type.getName().lastIndexOf(".") + 1);
			if (!rx.isCamel)
				clsname = clsname.substring(0, 1).toLowerCase(Locale.ENGLISH) + clsname.substring(1); // to
			// LowerCase
			return rxutil.fld2node(prefix + clsname);
		}
	}

	/**
	 * prefixを取得する
	 * @param type
	 * @return prefix
	 */
	public String getPrefix(Class type) {

		Class superclass =type.getSuperclass();
		if (superclass!=null&&!superclass.getName().equals("java.lang.Object")) {
			type = superclass;
		}
		
		return getPrefix(type.getName());

	}

	private String getPrefix(String clsname) {

		String prefix =null;
		if (clsname.lastIndexOf(".")>0) {
			prefix = (String) getJo_packagemap().get(clsname.substring(0, clsname.lastIndexOf(".")));
		}
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
	
	/**
	 * 名前空間を取得する
	 * @param type
	 * @return ns
	 */
	public String getNs(Class type) {
		return cutPrefixFromNs((String) getJo_packagemap().get(type.getName().substring(0, type.getName().lastIndexOf("."))));
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
	
	
	/* (非 Javadoc)
	 * @see com.thoughtworks.xstream.mapper.MapperWrapper#realClass(java.lang.String)
	 */
	public Class realClass(String elemName) {

		int idx = elemName.indexOf(":"); // to handle namespace of Class
		String prefix = "";
		String clsname = "";

		if (idx > 0) {
			prefix = elemName.substring(0, idx);
			elemName = elemName.substring(idx + 1);
		}
		if (!rx.isCamel)
			clsname = elemName.substring(0, 1).toUpperCase(Locale.ENGLISH)
					+ elemName.substring(1); // to UpperCase

		clsname = clsname.replace(':', '$').replace( "-", "__");
		
		String	packagename = findPackagename(clsname);

		if (packagename != null) {
			return wrapped.realClass(packagename + "." + clsname);
		} else {
			return wrapped.realClass(clsname);

		}
	}
	
	private String findPackagename(String clsname) {

		Iterator iter = getJo_packagemap().keySet().iterator();
		while (iter.hasNext()) {
			String packagename = (String) iter.next();
			String validname = packagename + "." + clsname;

			Object obj = wrapped.lookupType(validname);
			if (obj != null) return packagename;
			
		}
		return null;

	}
	
	/* (非 Javadoc)
	 * @see com.thoughtworks.xstream.mapper.MapperWrapper#realMember(java.lang.Class, java.lang.String)
	 */
	public String realMember(Class type, String serialized) {
		return serialized;
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
