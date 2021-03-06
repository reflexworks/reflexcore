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
 */

package jp.sourceforge.reflex.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import jp.sourceforge.reflex.IResourceMapper;
import jp.sourceforge.reflex.exception.JSONException;

import org.json.JSONObject;

import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.MapperWrapper;

public class ResourceMapper extends XStream implements IResourceMapper {

	private static final int PRIORITY_NORMAL = 0;

	public boolean isCamel; // It means polite classname.
	// First letter of Element name
	// is always converted to
	// uppercase.

	public boolean useSingleQuote;
	private Map<String, String> jo_packages0;

	private JSONSerializer jsonc;

	public ResourceMapper(Object jo_packages) {
		this(jo_packages, false, false, false);
	}

	public ResourceMapper(Object jo_packages, boolean compatible) {
		this(jo_packages, false, false, compatible);
	}

	public ResourceMapper(Object jo_packages, boolean isCamel, boolean compatible) {
		this(jo_packages, isCamel, false, compatible);
	}
	public ResourceMapper(Object jo_packages, ReflectionProvider reflectionProvider) {
		this(jo_packages, false, false, false, reflectionProvider);
	}
	public ResourceMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote, boolean compatible) {
		this(jo_packages, isCamel, useSingleQuote, compatible, null);
	}

	public ResourceMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote, ReflectionProvider reflectionProvider) {
		this(jo_packages, isCamel, useSingleQuote, false, reflectionProvider);
	}

	public ResourceMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote, boolean compatible, ReflectionProvider reflectionProvider) {
		super(reflectionProvider);
		
		if (jo_packages instanceof Map) {
			this.jo_packages0 = (Map) jo_packages;
		} else {
			if (jo_packages instanceof String) {
				this.jo_packages0 = new LinkedHashMap<String, String>();
				this.jo_packages0.put((String)jo_packages, "");
			}
		}

		jsonc = new JSONSerializer(compatible);
		
		this.isCamel = isCamel;
		this.useSingleQuote = useSingleQuote;
		if (useSingleQuote) {
			jsonc.Q = "'";
		}
		this.registerConverter(new RXConverter(this.getClassMapper()));
		this.setupConverters();
	}

	protected MapperWrapper wrapMapper(MapperWrapper next) {
		return new RXMapper(next, this);
	}

	protected void setupConverters() {
		super.setupConverters();
		registerConverter(new CollectionConverter(getClassMapper()),
				PRIORITY_NORMAL);
		registerConverter(new RXISO8601DateConverter(), PRIORITY_NORMAL);
		registerConverter(new RXISO8601TimestampConverter(), PRIORITY_NORMAL);
	}

	public String toJSON(Object entity,boolean dispChildNum) {
		if (entity==null) return null;
		Writer writer = new StringWriter();
		jsonc.marshal(entity, writer,dispChildNum);
		return writer.toString();
	}

	public String toJSON(Object entity) {
		if (entity==null) return null;
		Writer writer = new StringWriter();
		jsonc.marshal(entity, writer);
		return writer.toString();
	}

	public void toJSON(Object entity, Writer writer) {
		if (entity==null) return;
		else jsonc.marshal(entity, writer);
	}

	public void toJSON(Object entity, Writer writer,boolean dispChildNum) {
		if (entity==null) return;
		else jsonc.marshal(entity, writer,dispChildNum);
	}

	public Object fromJSON(String json) throws JSONException {
		return fromXML((new XML()).toString(new JSONObject(json)));
	}

	public Object fromJSON(Reader json) throws JSONException {
		try {
			return fromXML((new XML()).toString(new JSONObject(getBody(json))));
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}

	protected String getBody(Reader reader) throws IOException {
		try {
			BufferedReader b = new BufferedReader(reader);
			StringBuilder sb = new StringBuilder();
			String str;
			while ((str = b.readLine()) != null) {
				sb.append(str);
			}
			return sb.toString();
		} finally {
			reader.close();
		}
	}


	/**
	 * Serialize an object to a pretty-printed XML String.
	 */
	public String toXML(Object obj) {
		if (obj==null) return null;
		return toXML(obj, true);
	}

	public String toXML(Object obj, boolean printns) {
		((RXMapper)this.getClassMapper()).setJo_packagemap(
				new LinkedHashMap<String, String>(jo_packages0), printns);
//		((RXMapper)this.getClassMapper()).clearJo_namespacemap();
		Writer stringWriter = new StringWriter();
		HierarchicalStreamWriter writer = hierarchicalStreamDriver
				.createWriter(stringWriter);
		marshal(obj, writer);
		writer.flush();
		writer.close();
		return stringWriter.toString();
	}

	/**
	 * Serialize an object to the given Writer as pretty-printed XML.
	 */
	public void toXML(Object obj, Writer out) {
		if (obj==null) return;
		else toXML(obj, out, true);
	}

	/**
	 * Serialize an object to the given Writer as pretty-printed XML.
	 */
	public void toXML(Object obj, Writer out, boolean printns) {
		((RXMapper)this.getClassMapper()).setJo_packagemap(
				new LinkedHashMap<String, String>(jo_packages0), printns);
//		((RXMapper)this.getClassMapper()).clearJo_namespacemap();
		HierarchicalStreamWriter writer = hierarchicalStreamDriver
				.createWriter(out);
		marshal(obj, writer);
		writer.flush();
	}

	public Object fromXML(String xml) {
		if (xml==null) return null;
		else return fromXML(new StringReader(xml));
	}

	public Object fromXML(Reader xml) {
		if (xml==null) return null;
		((RXMapper)this.getClassMapper()).setJo_packagemap(new LinkedHashMap<String, String>(jo_packages0));
		return unmarshal(hierarchicalStreamDriver.createReader(xml), null);
	}

	public Object fromXML(String xml, Object root) {
		if (xml==null) return null;
		return fromXML(new StringReader(xml), root);
	}

	public Object fromXML(Reader xml, Object root) {
		if (xml==null) return null;
		((RXMapper)this.getClassMapper()).setJo_packagemap(new LinkedHashMap<String, String>(jo_packages0));
		return unmarshal(hierarchicalStreamDriver.createReader(xml), root);
	}

	public byte[] toMessagePack(Object entity) throws IOException {
		throw new IllegalStateException();
	}

	public void toMessagePack(Object entity, OutputStream out) throws IOException {
		throw new IllegalStateException();
	}

	public Object fromMessagePack(byte[] msg) throws IOException, ClassNotFoundException {
		throw new IllegalStateException();
	}

	public Object fromMessagePack(InputStream msg) throws IOException, ClassNotFoundException {
		throw new IllegalStateException();
	}

	public Object fromArray(String array, boolean isFeed) throws JSONException {
		throw new IllegalStateException();
	}

	public Object toArray(byte[] msg) throws IOException,
			ClassNotFoundException {
		throw new IllegalStateException();
	}

}
