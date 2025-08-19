package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLSerializer {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	public void marshal(Object source, Writer out) {
		XMLContext context = new XMLContext(out);
		marshal(context,source.getClass().getName(),source,false);
//		marshal(context,source.getClass().getName(),null);
	}

	public void marshal(XMLContext context,String nodename,Object source,boolean isList) {
		try {
		    if (source!=null&&source.getClass().getTypeName().equals("java.util.ArrayList")) {
		        List list = (List) source;
		          for (int i = 0; i < list.size(); i++) {
		              this.marshal(context,null,list.get(i),true);
		          }
		    }else 
			if (source==null) {
				context.outNodenameOne(nodename);
				context.flush();
			}else {
			    Field[] fields = source.getClass().getFields();
			    if (nodename!=null&&!nodename.equals("")) {
				    	if (nodename.startsWith("_")) nodename = nodename.substring(1);
				        context.outNodename(nodename,source);   
				        context.pushClosenode(nodename);
			    }

			    for (int fn = 0; fn < fields.length; fn++) {

			        if (fields[fn].get(source) == null)
			          continue;
			        if (isRealClass(fields[fn])) {
			            Object child = fields[fn].get(source);
			            if (child != null) {
			              this.marshal(context, fields[fn].getName() ,child,false);
			            }
				      }else if (isTextNode(fields[fn])) {
			    		  context.setNode(fields[fn],source);
			    		  
			    		  for (;(fn < fields.length)&&!isList; fn++) {
			    				  if (!context.addattr(fields[fn],source)) {			    					  
			    					  if ((fields.length>fn+1)&&!context.isSameNode(fields[fn].getName(),fields[fn+1].getName())) break;
			    				  }
				    			  if ((fields.length>fn+1)&&!isTextNode(fields[fn+1])) break;
					      }
					      
					      context.outNode();
	    				  						      

			          }else if (isList(fields[fn])) {	
			              List list = (List) fields[fn].get(source);
			              for (int i = 0; i < list.size(); i++) {
			                  this.marshal(context,fields[fn].getName(),list.get(i),true);
			              }
			          }
			        
			    }
			    
				context.popout();
				
			}
		} catch (IOException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		} catch (IllegalArgumentException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		} catch (IllegalAccessException e) {
			logger.log(Level.WARNING, e.getClass().getName(), e);
		}

	}
	
	private boolean isRealClass(Field field) {
		    if (Modifier.isFinal(field.getModifiers()))
		      return false;
		    
		    String fieldname = field.getName();
		    if (fieldname!=null) {
		    	int i=0;
		    	if (fieldname.startsWith("_")) i++;
		      // 先頭に_が付く前提
		      String classname = fieldname.substring(i, i+1).toUpperCase(Locale.ENGLISH) + fieldname.substring(i+1);
		      if (field.getType().getName().indexOf(classname)>0) return true;
		    }
		    return false;
		  }
	  private boolean isList(Field field) {
	    if (Modifier.isFinal(field.getModifiers()))
	      return false;
	    return (field.getType().getName().equals("java.util.List"));
	  }
	  
	  private boolean isTextNode(Field field) {
		  if (Modifier.isFinal(field.getModifiers()))
			 return false;
		  if (field.getType().getName().equals("java.lang.String")) {
			  return true;			  
		  }else
		  if (field.getType().getName().equals("int") || field.getType().getName().equals("java.lang.Integer")) {
			  return true;
		  }else if (field.getType().getName().equals("long") || field.getType().getName().equals("java.lang.Long")) {
			  return true;
		  }else if (field.getType().getName().equals("float") || field.getType().getName().equals("java.lang.Float")) {
			  return true;
		  }else if (field.getType().getName().equals("double") || field.getType().getName().equals("java.lang.Double")) {
			  return true;
		  }else if (field.getType().getName().equals("boolean") || field.getType().getName().equals("java.lang.Boolean")) {
			  return true;			  
		  }else if (field.getType().getName().equals("java.util.Date")) {
			  return true;
		  }
			  return false;		   
	  }	  
}