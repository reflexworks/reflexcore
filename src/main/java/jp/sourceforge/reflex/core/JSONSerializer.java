package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

import jp.sourceforge.reflex.IResourceMapper;

/**
 * JSONSerializerクラス
 *
 * @author Takezaki
 *
 */
public class JSONSerializer implements IResourceMapper {

	public String Q = "\""; // Quote （シングルクォートにしたい場合はここを変更）

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

		try {
			// out.append('{');
			out.write(new char[] { '{' });
			JSONContext context = new JSONContext(out, this.Q);
			context.push(context.HASH);
			marshal(context,source.getClass().getName(), source);
			// out.append('}');
			out.write(new char[] { '}' });
			out.flush();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
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
		String validname = field.getType().getName();
		Object obj;
		try {
			obj = Class.forName(validname).newInstance();
			return (obj != null);
		} catch (Exception e) {
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
		this.marshal(context, nodename, source, false);
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
	public void marshal(JSONContext context, String nodename,Object source, boolean flgArray) throws IOException,
			IllegalArgumentException, IllegalAccessException {

		RXUtil rxUtil = new RXUtil();

		int mode;

		Field[] fields = source.getClass().getFields();
		
		context.printNodeName(nodename);
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
							this.marshal(context, list.get(ln).getClass().getName(),list.get(ln), isArray);
						}
					}
				}

			} else if (isString(fields[fn])) {

				String string = (String) fields[fn].get(source);
				context.outcomma();

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
				context.outcomma();
				context.out(fields[fn].getName(), i);

			} else if (isLong(fields[fn])) {
				long i = ((Long) fields[fn].get(source)).longValue();
				context.outcomma();
				context.out(fields[fn].getName(), i);

			} else if (isFloat(fields[fn])) {
				float i = ((Float) fields[fn].get(source)).floatValue();
				context.outcomma();
				context.out(fields[fn].getName(), i);

			} else if (isDouble(fields[fn])) {
				double i = ((Double) fields[fn].get(source)).doubleValue();
				context.outcomma();
				context.out(fields[fn].getName(), i);

			} else if (isBoolean(fields[fn])) {
				boolean i = ((Boolean) fields[fn].get(source)).booleanValue();
				context.outcomma();
				context.out(fields[fn].getName(), i);

			} else if (rxUtil.isText(fields[fn].getType())) {
				String textStr = rxUtil.getTextValue(fields[fn].get(source));
				context.outcomma();
				context.out(fields[fn].getName(), textStr);

			} else if (isDate(fields[fn])) {

				Date date = (Date) fields[fn].get(source);
				String string = context.dateformat(date);

				context.outcomma();
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

		context.popout();

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

}
