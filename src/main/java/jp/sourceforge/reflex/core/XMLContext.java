package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class XMLContext {
	
	private Writer out;
	private Stack<String> stack = new Stack<String>();
	private Map<String,String> attrmap = new HashMap<String,String>();
	public String nodename;
	private String nodevalue;
	
	public void setNode(Field field,Object source) throws IllegalArgumentException, IllegalAccessException {
		this.nodename = getNodename(field.getName());
    	if (this.nodename.startsWith("_")) this.nodename = this.nodename.substring(1);

		if (field.get(source)!=null) {
			if (field.getType().getName().equals("java.util.Date")) {
				this.nodevalue = ""+ field.get(source);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				this.nodevalue = df.format(field.get(source));
			}else {
				this.nodevalue = convert(""+ field.get(source));				
			}
		}else {
			this.nodevalue = null;
		}

	}

	public XMLContext(Writer out) {
		this.out = out;
	}

	private String convert(String src) {
		return src.replaceAll("&", "&amp;")
				  .replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;")
				  .replaceAll("\"", "&quot;")
				  .replaceAll("'", "&apos;");
	}
	
	public void outNodename(String nodename) throws IOException {
		out.write("<"+getNodename(nodename)+">");			
	}

	public void flush() throws IOException {
		out.flush();
		
	}

	public void outNodenameOne(String nodename) throws IOException {
		out.write("<"+getNodename(nodename)+"/>");			
	}

	public void outNode() throws IOException {
		if (this.nodevalue!=null&&this.nodename.indexOf("$xml")<0) {
			if (this.nodename.equals("_$$text")) {
				out.write(this.nodevalue);						
			}else {
				out.write("<"+getNodename(this.nodename));
				for(String key:attrmap.keySet()) {
					out.write(" "+key+"=\""+convert(attrmap.get(key))+"\"");
				}
				out.write(">");
				out.write(this.nodevalue);		
				out.write("</"+getNodename(this.nodename)+">");									
			}
		}
	}

	private String getNodename(String nodename) {
		char c = Character.toLowerCase(nodename.charAt(nodename.lastIndexOf(".")+1));
		return String.valueOf(c) + nodename.substring(nodename.lastIndexOf(".")+2);
	}

	public void pushClosenode(String nodename) {
		this.stack.push("</"+getNodename(nodename)+">");
	}

	public void popout() throws IOException {
			out.write((String) this.stack.pop());
	}

	public boolean addattr(Field field, Object source) throws IllegalArgumentException, IllegalAccessException, IOException {
	  if (this.nodename.equals(field.getName())) {
		  return true;
	  }
  	  if (isSameNode(this.nodename,field.getName())) {
		  String attrname = getAttr(this.nodename,field.getName());
		  String attrvalue =  ""+ field.get(source);
		  if (attrname!=null) {
			  attrmap.put(attrname,attrvalue);
		  }
		  return true;
	  } else {
		  outNode();
		  attrmap = new HashMap<String,String>();  
		  setNode(field,source);
		  return false;
	  }
	}

	private String getAttr(String nodename,String nodename2) {
		if (nodename2.length()>=nodename.length()+2) {
			return nodename2.substring(nodename.length()+2);
		}else {
			return null;
		}
	}

	public boolean isSameNode(String nodename, String nodename2) {
		return (nodename2.indexOf(nodename)>=0)&&(nodename2.indexOf("_$")>0);
	}
	
}