package jp.reflexworks.test2.model;

import jp.reflexworks.atom.mapper.CipherContext;

public class Info {
	
	public String name;
	public String category;
	public String color;
	public String size;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return "Info [name=" + name + ", category=" + category + ", color="
				+ color + ", size=" + size + "]";
	}

	public Object getValue(String fldname) {if (fldname.equals("info.name")) return name;if (fldname.equals("info.category")) return category;if (fldname.equals("info.color")) return color;if (fldname.equals("info.size")) return size;return null;};
	public void encrypt(CipherContext context) {};
	public void decrypt(CipherContext context) {};
	public void isMatch(jp.reflexworks.atom.mapper.ConditionContext context) {if (name!=null) {context.fldname="info.name";context.type="String";context.obj=name;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}if (category!=null) {context.fldname="info.category";context.type="String";context.obj=category;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}if (color!=null) {context.fldname="info.color";context.type="String";context.obj=color;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}if (size!=null) {context.fldname="info.size";context.type="String";context.obj=size;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}};
	public boolean validate(String uid, java.util.List groups, String myself) throws java.text.ParseException {if (name==null) throw new java.text.ParseException("Required property 'name' not specified.",0);if (uid!=null&&groups!=null&&groups.size()>=0&&category!=null) {boolean ex=false;java.util.ArrayList groups2 = new java.util.ArrayList(groups);groups2.add(""+uid);for(int i=0;i<groups2.size();i++) {java.util.regex.Pattern p = java.util.regex.Pattern.compile("^/@[^/]*/@testservice/1/group/office$|^/@testservice/1/group/office$");java.util.regex.Matcher m = p.matcher(""+groups2.get(i));if (m.find()) ex=true;}if (!ex) throw new java.text.ParseException("Property 'info.category' is not writeable.",0);}if (size!=null&&size.length()>0) {java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[a-zA-Z0-9]{1,2}$");java.util.regex.Matcher matcher = pattern.matcher(""+size);if (!matcher.find()) throw new java.text.ParseException("Property 'size' is not valid.(regex=^[a-zA-Z0-9]{1,2}$, value="+size+")",0);}return true;};
	public void maskprop(jp.reflexworks.atom.mapper.MaskpropContext context) {if (category!=null) {boolean ex=false;if (context.groups==null) context.groups = new java.util.ArrayList();java.util.ArrayList groups2 = new java.util.ArrayList(context.groups);groups2.add(""+context.uid);for(int i=0;i<groups2.size();i++) {java.util.regex.Pattern p = java.util.regex.Pattern.compile("^/@[^/]*/@testservice/1/group/office$|^/@testservice/1/group/office$");java.util.regex.Matcher m = p.matcher(""+groups2.get(i));if (m.find()) ex=true;}if (!ex) category=null;}};
	
	
}
