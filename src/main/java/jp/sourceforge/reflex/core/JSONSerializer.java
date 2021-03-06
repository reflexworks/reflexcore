package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Locale;

import jp.sourceforge.reflex.IResourceMapper;
import jp.sourceforge.reflex.exception.JSONException;

/**
 * JSONSerializerクラス
 *
 * @author Takezaki
 *
 */
public class JSONSerializer implements IResourceMapper {

  private Logger logger = Logger.getLogger(this.getClass().getName());

  public String Q = "\""; // Quote （シングルクォートにしたい場合はここを変更）
  public boolean F = false;	// trueで互換モード

  public JSONSerializer(boolean F) {
	  this.F = F;
  }

  /**
   * @param entity Object 　
   * @return writer.toString();
   */
  public String toJSON(Object entity) {
    Writer writer = new StringWriter();
    marshal(entity, writer);
    return writer.toString();
  }

  /**
   * @param entity Object
   * @param writer Writer
   */
  public void toJSON(Object entity, Writer writer) {
    marshal(entity, writer);
  }

  public String toJSON(Object entity, boolean dispChildNum) {
	    Writer writer = new StringWriter();
	    marshal(entity, writer,dispChildNum);
	    return writer.toString();
	}

	public void toJSON(Object entity, Writer writer, boolean dispChildNum) {
	    marshal(entity, writer,dispChildNum);
	}

  public Object fromJSON(String json) {
    // please use ResourceMapper
    throw new UnsupportedOperationException();
  }

  public Object fromJSON(Reader json) {
    // please use ResourceMapper
    throw new UnsupportedOperationException();
  }

  /**
   * @param xml String
   * 
   * @return nothing nothing
   */
  public Object fromXML(String xml) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param xml Reader
   * 
   * @return nothing nothing
   */
  public Object fromXML(Reader xml) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param entity Object
   *
   * @return nothing nothing
   */
  public String toXML(Object entity) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param entity Object
   * @param printns true/false
   *
   * @return nothing nothing
   */
  public String toXML(Object entity, boolean printns) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param entity Object
   * @param writer Writer
   */
  public void toXML(Object entity, Writer writer) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param entity Object
   * @param writer Writer
   * @param printns true/false
   */
  public void toXML(Object entity, Writer writer, boolean printns) {
    throw new UnsupportedOperationException();
  }

  /**
   * @param source Object
   * @param out Writer
   */
  public void marshal(Object source, Writer out) {
	  this.marshal(source, out,false);
  }
  /**
   * @param source Object
   * @param out Writer
   */
  public void marshal(Object source, Writer out,boolean dispchildnum) {

    try {
    if(dispchildnum) {
        JSONContext context = new JSONContext(out, this.Q,this.F,dispchildnum);
        context.push(context.HASH);
        marshal(context,"", source);
        out.flush();
    	
    }else {
      // out.append('{');
      out.write(new char[] { '{' });
      JSONContext context = new JSONContext(out, this.Q,this.F,dispchildnum);
      context.push(context.HASH);
      marshal(context,source.getClass().getName(), source);
      // out.append('}');
      out.write(new char[] { '}' });
      out.flush();
    }
    } catch (IllegalArgumentException e) {
        logger.log(Level.WARNING, e.getClass().getName(), e);
    } catch (IOException e) {
        logger.log(Level.WARNING, e.getClass().getName(), e);
    } catch (IllegalAccessException e) {
        logger.log(Level.WARNING, e.getClass().getName(), e);
    }
  }

  /**
   * @param field
   * @return boolean
   */
  private boolean isList(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("java.util.List"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isString(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("java.lang.String"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isInt(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("int") || field.getType().getName().equals("java.lang.Integer"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isLong(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("long") || field.getType().getName().equals("java.lang.Long"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isFloat(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("float") || field.getType().getName().equals("java.lang.Float"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isDouble(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("double") || field.getType().getName().equals("java.lang.Double"));
  }

  /**
   * @param field Field
   * @return boolean
   */
  private boolean isDate(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("java.util.Date"));
  }

  /**
   * @param field Field
   * @return boolean
   */
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
  
  /**
   * @param field Field
   * @return boolean
   */
  private boolean isBoolean(Field field) {
    if (Modifier.isFinal(field.getModifiers()))
      return false;
    return (field.getType().getName().equals("boolean") || field.getType().getName().equals("java.lang.Boolean"));
  }

  /**
   * @param context
   *            JSONContext
   * @param source
   *            Object
   * @throws IOException
   *             exception
   * @throws IllegalArgumentException
   *             exception
   * @throws IllegalAccessException
   *             exception
   */
  public void marshal(JSONContext context, String nodename,Object source) throws IOException,
      IllegalArgumentException, IllegalAccessException {
    this.marshal(context, nodename, source, false,null);
  }

  /**
   * @param context
   *            JSONContext
   * @param source
   *            Object
   * @param flgArray
   *            配列かどうか
   * @throws IOException
   *             exception
   * @throws IllegalArgumentException
   *             exception
   * @throws IllegalAccessException
   *             exception
   */
  public void marshal(JSONContext context, String nodename,Object source, boolean flgArray,Map<String,Integer> nummap) throws IOException,
      IllegalArgumentException, IllegalAccessException {

    int mode;
    if (source==null) {
      context.printNodeName(nodename);
      context.pushout();
    	
    }else {
    
    Field[] fields = source.getClass().getFields();
    if (nodename!=null&&!nodename.equals("")) {
    	if (nodename.startsWith("_")) nodename = nodename.substring(1);
    	context.printNodeName(nodename);
    }
    context.pushout();

    for (int fn = 0; fn < fields.length; fn++) {

      if (fields[fn].get(source) == null)
        continue;

      if (isList(fields[fn])) {
        List list = (List) fields[fn].get(source);
        
        boolean isArray = false;
        int arrayCol = 0;

        if (list != null) {

          // 配列かどうかのチェック
          if (list.size() > 0) {
            Object objTmp = list.get(0);
            if (objTmp!=null) {
              Field[] fldTmp = objTmp.getClass().getFields();
              for (int t = 0; t < fldTmp.length; t++) {
                if ("_$$col".equals(fldTmp[t].getName())) {
                  // 配列
                  isArray = true;
                  arrayCol = ((Integer) fldTmp[t].get(objTmp))
                      .intValue();
                }
              }
            }
          }

          for (int ln = 0; ln < list.size(); ln++) {

            if (list.get(ln) instanceof String) {

              context.outcomma();
              context.out((String)list.get(ln));

            } else {
              context.outcomma();
              if ((list.size() > 0)) {

                if (isArray) {
                  if (ln > 0) {
                    if (ln == list.size() - 1) {
                      if (arrayCol == 0) {
                        mode = context.ARRAY2E0;
                      } else if (ln % arrayCol == 0) {
                        mode = context.ARRAY2CE;
                      } else {
                        mode = context.ARRAY2E;
                      }
                    } else {
                      if (arrayCol == 0) {
                        mode = context.ARRAY2;
                      } else if (arrayCol == 1) {
                        mode = context.ARRAY2CSE;
                      } else if (ln % arrayCol == 0) {
                        mode = context.ARRAY2SC;
                      } else if (ln % arrayCol == arrayCol - 1) {
                        mode = context.ARRAY2EC;
                      } else {
                        mode = context.ARRAY2;
                      }
                    }
                  } else {
                    if (list.size() == 1) {
                      if (arrayCol == 0) {
                        mode = context.ARRAY2SE0;
                      } else {
                        mode = context.ARRAY2SE;
                      }
                    } else if (arrayCol == 0) {
                      mode = context.ARRAY2S0;
                    } else if (arrayCol == 1) {
                      mode = context.ARRAY2CS;
                    } else {
                      mode = context.ARRAY2S;
                    }
                  }

                } else {
                  if (ln > 0) {
                    if (ln == list.size() - 1) {
                      mode = context.ARRAYE;
                    } else {
                      mode = context.ARRAY;
                    }
                  } else {
                    if (list.size() == 1) {
                      mode = context.ARRAYSE;
                    } else {
                      mode = context.ARRAYS;
                    }
                  }
                }
              } else {
                mode = context.HASH;
              }
              context.push(mode);
              if (nummap==null) nummap = new HashMap<String,Integer>();
              nummap.put(fields[fn].getName(), ln);
              this.marshal(context, fields[fn].getName(),list.get(ln), isArray,nummap);
              nummap.remove(fields[fn].getName());
            }
          }
        }

      } else if (isString(fields[fn])) {

        String string = (String) fields[fn].get(source);

        if (flgArray) {
          if ("_$$text".equals(fields[fn].getName())) {
            context.out(string);
          } else {
            context.out(fields[fn].getName(), string);
          }
        } else {
          context.out(fields[fn].getName(), string);
        }

      } else if (isInt(fields[fn])) {
        int i = ((Integer) fields[fn].get(source)).intValue();
        context.out(fields[fn].getName(), i);

      } else if (isLong(fields[fn])) {
        long i = ((Long) fields[fn].get(source)).longValue();
        context.out(fields[fn].getName(), i);

      } else if (isFloat(fields[fn])) {
        float i = ((Float) fields[fn].get(source)).floatValue();
        context.out(fields[fn].getName(), i);

      } else if (isDouble(fields[fn])) {
        double i = ((Double) fields[fn].get(source)).doubleValue();
        context.out(fields[fn].getName(), i);

      } else if (isBoolean(fields[fn])) {
        boolean i = ((Boolean) fields[fn].get(source)).booleanValue();
        context.out(fields[fn].getName(), i);

      } else if (isText(fields[fn].getType())) {
        String textStr = getTextValue(fields[fn].get(source));
        context.out(fields[fn].getName(), textStr);

      } else if (isDate(fields[fn])) {

        Date date = (Date) fields[fn].get(source);
        String string = context.dateformat(date);
        context.out(fields[fn].getName(), string);

      } else if (isRealClass(fields[fn])) {
        Object child = fields[fn].get(source);
        if (child != null) {
          mode = context.HASH;
          context.push(mode);
          context.outcomma();
          this.marshal(context, fields[fn].getName() ,child);
        }
      }
      
    }
	  if (nummap!=null&&context.dispChildNum) {
		  for(Map.Entry<String, Integer> e : nummap.entrySet()) {
  			  context.outcomma();
  			  context.out("____num",e.getValue());
  			  break;
			}
	  }
    }
	  context.popout();

  }

	private static final String TEXT = "com.google.appengine.api.datastore.Text";

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

  public byte[] toMessagePack(Object entity) throws IOException {
    throw new IllegalStateException();
  }

  public void toMessagePack(Object entity, OutputStream out) throws IOException {
    throw new IllegalStateException();
  }

  public Object fromMessagePack(byte[] msg) throws IOException {
    throw new IllegalStateException();
  }

  public Object fromMessagePack(InputStream msg) throws IOException {
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
