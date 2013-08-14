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
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.thoughtworks.xstream.alias.ClassMapper;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class RXConverter implements Converter {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	private RXMapper classMapper;
	private RXClassProvider classProvider;

	public RXConverter(ClassMapper classMapper) {
		this.classMapper = (RXMapper) classMapper;
		this.classProvider = new RXClassProvider();
	}

	public boolean canConvert(Class type) {
		// some way to detect if this is one of your package.
		return classMapper.getNamespace(type) != null;
	}

	public void marshal(Object source, HierarchicalStreamWriter writer,
			MarshallingContext context) {

		Field[] fields = source.getClass().getFields();
		boolean sw = false;
				
		// 最初にtextnodeとattributeを出力する。
		for (int i = 0; i < fields.length; i++) {
			try {
				Field fld = fields[i];
				String fldname = fld.getName();
				if (Modifier.isFinal(fld.getModifiers()))
					continue; // ignore final modifier

				if (fldname.indexOf("_$") > -1) {
					// textnode
					if (fldname.equals("_$$text")) {
						Object value = fld.get(source);
						if (value != null) {
							if (classMapper.getRxutil()
									.isText(value.getClass())) {
								writer.setValue(classMapper.getRxutil()
										.getTextValue(value));
							} else {
								writer.setValue("" + value);
							}
						}
					} else {
						
						if (classMapper.getPrintns()==1) {
							
							// namespace表示
							for (Map.Entry<String, String> e:classMapper.getJo_namespacemap().entrySet()) {
								
								String ns = e.getKey();

								if (ns!=null) {
									String prefix = "xmlns";
									if (!e.getValue().equals("")) {
										prefix += ":"+e.getValue().replace(":", "");
									}
									// 名前空間が""のときは表示しない
									if (!ns.isEmpty())
										writer.addAttribute(prefix, ns);
								}
							}
							// 名前空間表示は一度きり
							classMapper.setPrintnsFalse();
						}

						
						// attribute
						if (fldname.startsWith("_")) {
							String attrname = classMapper.getRxutil()
									.fld2node(
											fldname.substring(fldname
													.indexOf("_$") + 2));
							String attrvalue = (String) fld.get(source);
							if (attrvalue != null
									&& attrname.startsWith("xmlns")) {
								//if (attrname.equals("xmlns"))
								//	defaultns = attrvalue;
								/* 名前空間をプロパティから取得するところを廃止(2012/8/7) TODO 下位互換性
								if (((RXMapper)classMapper).getJo_namespacemap()
										.get(attrvalue) == null) {
									writer.addAttribute(attrname, attrvalue);
									String ns = attrname.substring(5); // "xmlns"
									if (!ns.equals(""))
										ns = ns.substring(1) + ":";
									((RXMapper)classMapper).getJo_namespacemap()
											.put(attrvalue, ns); // save
											
								}*/
							} else if (attrvalue != null)
								writer.addAttribute(attrname, attrvalue);
						}
					}
				}
			} catch (Exception e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}

		// 次にタグ内の要素を出力する。
		for (int i = 0; i < fields.length; i++) {
			try {
				Field fld = fields[i];
				String fldname = fld.getName();
				if (Modifier.isFinal(fld.getModifiers()))
					continue; // ignore final modifier

				if (fldname.indexOf("_$") > -1) {
					//
				} else {

					Object value = fld.get(source);
					if (value != null) {
						String node = classMapper.getRxutil().fld2node(fldname);

						String ns = "";
						String prefix ="";
						
						if (classMapper.getRxutil().isList(fld.getType())) {
							sw = true;  // Arrayの場合は親で括ることにした
						} else {
							if (classMapper.getPrintns()>=0) {
							String classname2 = fld.getType().getName();
							if (classname2.startsWith("java.lang")) {
								prefix = (String) ((RXMapper)classMapper).getPrefix(source.getClass());
							}else {
							prefix = (String) ((RXMapper)classMapper).getPrefix(fld.getType());
							}
							}
							sw = true;
						}
						
						if (prefix == null)
							prefix = "";
						
						// プロパティに:が含まれている場合はprefixを付けない(下位互換性のため）
						if (node.contains(":")) 
							prefix = "";
							
						node = prefix + node;

						if (sw)
							writer.startNode(node);

						List attrs = getAttrproperty(fields, fldname);

						for (int j = 0; j < attrs.size(); j++) {
							Field attr = (Field) attrs.get(j);
							String attrname = classMapper.getRxutil().fld2node(
									attr.getName().substring(
											attr.getName().indexOf("_$") + 2));
							String attrvalue = (String) attr.get(source);
							if (attrvalue != null)
								writer.addAttribute(attrname, attrvalue);
						}

						context.convertAnother(value);
						if (sw)
							writer.endNode();
					}
				}
			} catch (Exception e) {
				//e.printStackTrace();
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}

	}

	public Object unmarshal(final HierarchicalStreamReader reader,
			final UnmarshallingContext context) {
		final Object result = instantiateNewInstance(context);

		// attribute
		Iterator ita = reader.getAttributeNames();
		while (ita.hasNext()) {
			String attrname = (String) ita.next();
			String attrvalue = reader.getAttribute(attrname);
			String attrproperty = "_$" + attrname;

			boolean propertyExistsInClass = classProvider
					.propertyDefinedInClass(attrproperty, result.getClass());
			if (propertyExistsInClass) {
				classProvider.writeProperty(result, attrproperty, attrvalue);
			}
		}

		while (reader.hasMoreChildren()) {

			reader.moveDown();

			//
			String propertyName = classMapper.realMember(result.getClass(),
					reader.getNodeName());

			Iterator it = reader.getAttributeNames();

			while (it.hasNext()) {
				String attrname = (String) it.next();
				String attrvalue = reader.getAttribute(attrname);
				String attrproperty = propertyName + "_$" + attrname;

				boolean propertyExistsInClass = classProvider
						.propertyDefinedInClass(attrproperty, result.getClass());
				if (propertyExistsInClass) {
					classProvider
							.writeProperty(result, attrproperty, attrvalue);
				}
			}

			boolean propertyExistsInClass = classProvider
					.propertyDefinedInClass(propertyName, result.getClass());
			if (!propertyExistsInClass) {
				int s = propertyName.indexOf(":");
				propertyName = propertyName.substring(s + 1);
				propertyExistsInClass = classProvider.propertyDefinedInClass(
						propertyName, result.getClass());
			}

			Field field;
			try {
				field = result.getClass().getField(
						classMapper.getRxutil().node2fld(propertyName));
				if (classMapper.getRxutil().isList(field.getType())) {

					Pattern pattern = Pattern
							.compile("java.util.List<(.*)\\.(.*)>");
					Matcher matcher = pattern.matcher(field.getGenericType()
							.toString());

					if (matcher.find()) {
						LinkedHashMap<String, String> jo_packagemapnew = new LinkedHashMap<String, String>();
						jo_packagemapnew.put((String) matcher.group(1),
								(String) classMapper.getJo_packagemap().get(matcher.group(1)));

						classMapper.getJo_packagemap().remove(matcher.group(1));

						Iterator iter = classMapper.getJo_packagemap().entrySet().iterator();

						while (iter.hasNext()) {
							Map.Entry entry = (Map.Entry) iter.next();
							jo_packagemapnew.put((String) entry.getKey(),
									(String) entry.getValue());
						}

						classMapper.setJo_packagemap(jo_packagemapnew);

					}
				}

				Class type = determineType(reader, result, propertyName);
				Object value = context.convertAnother(result, type);

				if (propertyExistsInClass) {
					classProvider.writeProperty(result, propertyName, value);
				}

			} catch (SecurityException e) {
				//e.printStackTrace();
				logger.log(Level.WARNING, e.getClass().getName(), e);
			} catch (NoSuchFieldException e) {
				//e.printStackTrace();
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}

			reader.moveUp();

		}

		// textnode
		String propertyName = "_$$text";
		boolean propertyExistsInClass = classProvider.propertyDefinedInClass(
				propertyName, result.getClass());
		if (propertyExistsInClass) {
			Object value = reader.getValue();
			if (((String) value).length() > 0)
				classProvider.writeProperty(result, propertyName, value);
		}

		return result;
	}

	private Object instantiateNewInstance(UnmarshallingContext context) {
		Object result = context.currentObject();
		if (result == null) {
			result = classProvider.newInstance(context.getRequiredType());
		}
		return result;
	}

	private Class determineType(HierarchicalStreamReader reader, Object result,
			String fieldName) {
		return classMapper.defaultImplementationOf(classProvider
				.getPropertyType(result, fieldName));
	}

	private List getAttrproperty(Field[] fields, String fldname) {

		List attrs = new ArrayList();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getName().startsWith(fldname + "_$"))
				attrs.add(fields[i]);
		}
		return attrs;
	}

}
