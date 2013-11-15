package jp.sourceforge.reflex.util;

import java.io.File;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.Iterator;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Array;
import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;

/**
 * オブジェクトのフィールド編集クラス.
 */
public class FieldMapper {

	public static final String PERSISTENT = "@javax.jdo.annotations.Persistent";
	public static final String PRIMARY_KEY = "@javax.jdo.annotations.PrimaryKey";
	
	protected boolean isReflexField;
	
	public FieldMapper(boolean isReflexField) {
		this.isReflexField = isReflexField;
	}

	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。<br>
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * </p>
	 * @param source コピー元
	 * @param target コピー先
	 */
	public void setValue(Object source, Object target) {
		setValue(source, target, false);	// annotationチェックしない
	}

	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * </p>
	 * @param source コピー元
	 * @param target コピー先
	 * @param level 上位クラスのコピー実施階層
	 * <ol>
	 *   <li>0以下の場合上限なし。全ての継承クラスのフィールドをコピーします。</li>
	 *   <li>1の場合自クラスのみ</li>
	 *   <li>2の場合自クラスと、自クラスが継承しているクラス</li>
	 *   <li>3の場合自クラスと、自クラスが継承しているクラスと、自クラスの継承しているクラスの継承クラス</li>
	 *   <li> ... </li>
	 * </ol>
	 */
	public void setValue(Object source, Object target, int level) {
		setValue(source, target, false, false, level);	// annotationチェックしない
	}

	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * </p>
	 * @param source コピー元
	 * @param target コピー先
	 * @param isCheckAnnotation annotationをチェックするかどうか 。<br>
	 *   trueの場合、@Persistentのみ項目移送します。
	 */
	public void setValue(Object source, Object target, boolean isCheckAnnotation) {
		setValue(source, target, isCheckAnnotation, false);
	}

	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。<br>
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * プリミティブ型と以下のクラス以外はセット対象外です。
	 * <ul>
	 * <li>
	 *     String, Integer, Long, Float, Double, Date, Short, Character, Byte, Boolean, Number,
	 *     BigInteger, BigDecimal, StringBuffer, URL, Timestamp, Time, java.sql,Date, File, Locale, 
	 *     Calendar, com.google.appengine.api.datastore.Text
	 * </li>
	 * </ul>
	 * </p>
	 * @param source コピー元
	 * @param target コピー先
	 */
	public void setValueExcludingMultiObj(Object source, Object target) {
		setValue(source, target, false, true);
	}

	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * <p>
	 * @param source コピー元
	 * @param target コピー先
	 * @param isCheckAnnotation annotationをチェックするかどうか 。<br>
	 *   trueの場合、@Persistentのみ項目移送します。
	 * @param isCheckMultiObj IterableとMapを移送しないようチェックするかどうか。 <br>
	 *   trueの場合、プリミティブ型と以下のクラス以外はセット対象外です。
	 *   <ul><li>
	 *     String, Integer, Long, Float, Double, Date, Short, Character, Byte, Boolean, Number,
	 *     BigInteger, BigDecimal, StringBuffer, URL, Timestamp, Time, java.sql,Date, File, Locale, 
	 *     Calendar, com.google.appengine.api.datastore.Text
	 *   </li></ul>
	 */
	public void setValue(Object source, Object target, boolean isCheckAnnotation, 
			boolean isCheckMultiObj) {
		setValue(source, target, isCheckAnnotation, isCheckMultiObj, 0);
	}
		
	/**
	 * sourceの各値を、targetの各フィールドにセットします.
	 * <p>
	 * 値がnullでなく、targetの値とイコールでない場合にtargetにセットします。<br>
	 * sourceとtargetは同じsetter、getterを持っている必要があります。
	 * 参照コピーのため、プリミティブ型やString以外のクラスのコピーはコピー元と同じオブジェクトを参照します。<br>
	 * <p>
	 * @param source コピー元
	 * @param target コピー先
	 * @param isCheckAnnotation annotationをチェックするかどうか 。<br>
	 *   trueの場合、@Persistentのみ項目移送します。
	 * @param isCheckMultiObj IterableとMapを移送しないようチェックするかどうか。 <br>
	 *   trueの場合、プリミティブ型と以下のクラス以外はセット対象外です。
	 *   <ul><li>
	 *     String, Integer, Long, Float, Double, Date, Short, Character, Byte, Boolean, Number,
	 *     BigInteger, BigDecimal, StringBuffer, URL, Timestamp, Time, java.sql,Date, File, Locale, 
	 *     Calendar, com.google.appengine.api.datastore.Text
	 *   </li></ul>
	 * @param level 上位クラスのコピー実施階層
	 * <ol>
	 *   <li>0以下の場合上限なし。全ての継承クラスのフィールドをコピーします。</li>
	 *   <li>1の場合自クラスのみ</li>
	 *   <li>2の場合自クラスと、自クラスが継承しているクラス</li>
	 *   <li>3の場合自クラスと、自クラスが継承しているクラスと、自クラスの継承しているクラスの継承クラス</li>
	 *   <li> ... </li>
	 * </ol>
	 */
	public void setValue(Object source, Object target, boolean isCheckAnnotation, 
			boolean isCheckMultiObj, int level) {
		// 自クラス
		Class targetClass = target.getClass();
		Field[] fields = targetClass.getDeclaredFields();
		setValue(fields, source, target, isCheckAnnotation, isCheckMultiObj);

		// スーパークラス
		Class superClass = targetClass.getSuperclass();
		int cnt = 0;
		while (superClass != null && !Object.class.equals(superClass) &&
				(level <= 0 || level > cnt)) {
			Class tmpClass = superClass;
			fields = tmpClass.getDeclaredFields();
			setValue(fields, source, target, isCheckAnnotation, isCheckMultiObj);
			superClass = tmpClass.getSuperclass();
			cnt++;
		}
	}
	
	private void setValue(Field[] fields, Object source, Object target, 
			boolean isCheckAnnotation, boolean isCheckMultiObj) {
		for (Field fld : fields) {

			if (!isFinal(fld) && (!isCheckAnnotation || isPersistent(fld))) {

				String setter = getSetter(fld, isReflexField);
				String getter = getGetter(fld, isReflexField);

				try {
					Object sourcevalue = this.getValue(source, getter);
					
					if (sourcevalue != null) {
						if (!isCheckMultiObj || !isMultipleObj(sourcevalue)) {
							Object targetvalue = this.getValue(target, getter);

							if (sourcevalue != targetvalue) {
								this.setValue(target, fld.getType(), sourcevalue, setter);
							}
						}
					}

				} catch (IllegalArgumentException e) {
					// Do nothing
				}
			}
		}
	}

	/**
	 * sourceのクローンを作成します.
	 * <p>
	 * 変数がプリミティブ型かStringでない場合、そのクラスのcloneメソッドを呼びます。
	 * </p>
	 * @param source オブジェクト
	 * @return 複製したオブジェクト
	 */
	public Object clone(Object source) {
		if (source == null) {
			return null;
		}
		if (isPrimitive(source)) {
			return source;
		}
		if (isString(source)) {
			return cloneString((String)source);
		}
		if (isNumber(source)) {
			return cloneNumber((Number)source);
		}
		if (isBoolean(source)) {
			return cloneBoolean((Boolean)source);
		}
		if (isCollection(source)) {
			return cloneCollection((Collection)source);
		}
		if (isArray(source)) {
			return cloneArray(source);
		}
		if (isMap(source)) {
			return cloneMap((Map)source);
		}
		if (isCloneable(source)) {
			return invokeClone(source);
		}
		
		// フィールドごとに処理
		Class targetClass = source.getClass();
		Field[] fields = targetClass.getDeclaredFields();
		Object target = newInstance(targetClass);
		if (target == null) {
			return null;
		}

		for (Field fld : fields) {
			if (!isFinal(fld)) {
				String setter = getSetter(fld, isReflexField);
				String getter = getGetter(fld, isReflexField);

				Object sourcevalue = this.getValue(source, getter);
				this.setValue(target, fld.getType(), clone(sourcevalue), setter);
			}
		}
		
		// 他のクラスを継承している場合、スーパークラスの変数についてもコピーする。
		Class superClass = targetClass.getSuperclass();
		while (superClass != null && !Object.class.equals(superClass)) {
			Class tmpClass = superClass;
			fields = tmpClass.getDeclaredFields();
			for (Field fld : fields) {
				if (!isFinal(fld)) {
					String setter = getSetter(fld, isReflexField);
					String getter = getGetter(fld, isReflexField);

					Object sourcevalue = this.getValue(source, getter);
					this.setValue(target, fld.getType(), clone(sourcevalue), setter);
				}
			}
			superClass = tmpClass.getSuperclass();
		}

		return target;
	}

	/**
	 * オブジェクトのsetterを実行して、値をセットします。
	 * @param source オブジェクト
	 * @param type setterでセットするクラス
	 * @param value setterでセットする値
	 * @param setter setterメソッドの文字列表記
	 */
	public void setValue(Object source, Class type, Object value, String setter) {

		Method method = null;
		try {
			method = source.getClass().getMethod(setter, type);
			method.invoke(source, value);

		} catch (SecurityException e2) {
			// Do Nothing
		} catch (NoSuchMethodException e2) {
			// Do Nothing
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (IllegalAccessException e) {
			// Do Nothing
		} catch (InvocationTargetException e) {
			// Do Nothing
		}
	}

	/**
	 * オブジェクトからgetterを実行して、値を取得します。
	 * @param source オブジェクト
	 * @param getter getterメソッドの文字列表記
	 * @return getterで取得した値
	 */
	public Object getValue(Object source, String getter) {

		Method method = null;
		Object ret = null;

		try {
			method = source.getClass().getMethod(getter);
			ret = method.invoke(source);

		} catch (SecurityException e2) {
			// Do Nothing
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (IllegalAccessException e) {
			// Do Nothing
		} catch (InvocationTargetException e) {
			// Do Nothing
		} catch (NoSuchMethodException e) {
			// Do Nothing
		}

		return ret;
	}

	/**
	 * cloneメソッド実行
	 * @param source オブジェクト
	 * @return cloneで作成されたオブジェクト
	 */
	public Object invokeClone(Object source) {
		Method method = null;
		Object ret = null;

		try {
			method = source.getClass().getMethod("clone");
			ret = method.invoke(source);

		} catch (SecurityException e2) {
			// Do Nothing
		} catch (NoSuchMethodException e2) {
			// Do Nothing
		} catch (IllegalArgumentException e) {
			// Do Nothing
		} catch (IllegalAccessException e) {
			// Do Nothing
		} catch (InvocationTargetException e) {
			// Do Nothing
		}

		return ret;
	}
	
	/**
	 * インスタンスの作成
	 * @param cls インスタンスを作成したいクラス
	 * @return インスタンスを作成したオブジェクト
	 */
	public Object newInstance(Class cls) {
		try {
			return cls.newInstance();
			
		} catch (IllegalAccessException e) {
			// Do nothing.
		} catch (InstantiationException e) {
			// Do nothing.
		}
		return null;
	}

	/**
	 * Persistentかどうか (GAE用)
	 * @deprecated ReflexではJDOを使用しなくなったため、Persistentアノテーションは使用しません。
	 * @param fld Field
	 * @return Persistentアノテーションが設定されている場合true
	 */
	public boolean isPersistent(Field fld) {
		boolean ret = false;

		Annotation[] annotations = fld.getDeclaredAnnotations();
		for (Annotation anno : annotations) {
			if (anno.toString().startsWith(PERSISTENT)) {
				ret = true;
			}
		}

		return ret;
	}

	/**
	 * PrimaryKeyかどうか (GAE用)
	 * @deprecated ReflexではJDOを使用しなくなったため、PrimaryKeyアノテーションは使用しません。
	 * @param fld Field
	 * @return PrimaryKeyアノテーションが設定されている場合true
	 */
	public boolean isPrimaryKey(Field fld) {
		boolean ret = false;

		Annotation[] annotations = fld.getDeclaredAnnotations();
		for (Annotation anno : annotations) {
			if (anno.toString().startsWith(PRIMARY_KEY)) {
				ret = true;
			}
		}

		return ret;
	}
	
	/**
	 * プリミティブ型、GAEのText型、上記以外の"java.～"パッケージのクラスはfalseを返します。
	 * @param obj オブジェクト
	 * @return プリミティブ型、GAEのText型、上記以外の"java.～"パッケージのクラスの場合false
	 */
	public static boolean isMultipleObj(Object obj) {
		Class cls = obj.getClass();
		if (cls.isPrimitive()) {
			return false;
		}
		if (obj instanceof String || obj instanceof Integer || obj instanceof Long ||
				obj instanceof Float || obj instanceof Double || obj instanceof Date ||
				obj instanceof Short || obj instanceof Character || obj instanceof Byte ||
				obj instanceof Boolean || obj instanceof Number || obj instanceof BigInteger ||
				obj instanceof BigDecimal || obj instanceof StringBuffer || obj instanceof URL ||
				obj instanceof Timestamp || obj instanceof Time || obj instanceof java.sql.Date ||
				obj instanceof File || obj instanceof Locale || obj instanceof Calendar) {
			return false;
		}
		String clsname = cls.getName();
		if (clsname.equals("com.google.appengine.api.datastore.Text")) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * プリミティブ型、GAEのText型、上記以外の"java.～"パッケージのクラスはfalseを返します。
	 * @param cls Class
	 * @return プリミティブ型、GAEのText型、上記以外の"java.～"パッケージのクラスの場合false
	 */
	public static boolean isMultipleObj(Class cls) {
		if (cls.isPrimitive()) {
			return false;
		}
		if (String.class.isAssignableFrom(cls) || Integer.class.isAssignableFrom(cls) || 
				Long.class.isAssignableFrom(cls) || Float.class.isAssignableFrom(cls) || 
				Double.class.isAssignableFrom(cls) || Date.class.isAssignableFrom(cls) ||
				Short.class.isAssignableFrom(cls) || Character.class.isAssignableFrom(cls) || 
				Byte.class.isAssignableFrom(cls) || Boolean.class.isAssignableFrom(cls) || 
				Number.class.isAssignableFrom(cls) || BigInteger.class.isAssignableFrom(cls) ||
				BigDecimal.class.isAssignableFrom(cls) || 
				StringBuffer.class.isAssignableFrom(cls) || URL.class.isAssignableFrom(cls) ||
				Timestamp.class.isAssignableFrom(cls) || Time.class.isAssignableFrom(cls) || 
				java.sql.Date.class.isAssignableFrom(cls) ||
				File.class.isAssignableFrom(cls) || Locale.class.isAssignableFrom(cls) || 
				Calendar.class.isAssignableFrom(cls)) {
			return false;
		}
		String clsname = cls.getName();
		if (clsname.equals("com.google.appengine.api.datastore.Text")) {
			return false;
		}
		
		return true;
	}

	/**
	 * プリミティブ型のクラスはtrueを返します。
	 * @param obj オブジェクト
	 * @return プリミティブ型のクラスの場合true
	 */
	public static boolean isPrimitive(Object obj) {
		Class cls = obj.getClass();
		return isPrimitive(cls);
	}

	/**
	 * プリミティブ型のクラスはtrueを返します。
	 * @param cls Class
	 * @return プリミティブ型のクラスの場合true
	 */
	public static boolean isPrimitive(Class cls) {
		if (cls.isPrimitive()) {
			return true;
		}
		return false;
	}

	/**
	 * Numberクラスを継承するクラスはtrueを返します。
	 * @param obj オブジェクト
	 * @return Numberクラスを継承するクラスの場合true
	 */
	public static boolean isNumber(Object obj) {
		if (obj instanceof Number) {
			return true;
		}
		return false;
	}

	/**
	 * Numberクラスを継承するクラスはtrueを返します。
	 * @param cls Class
	 * @return Numberクラスを継承するクラスの場合true
	 */
	public static boolean isNumber(Class cls) {
		if (Number.class.isAssignableFrom(cls)) {
			return true;
		}
		return false;
	}

	/**
	 * Booleanクラスを継承するクラスはtrueを返します。
	 * @param obj オブジェクト
	 * @return Booleanクラスを継承するクラスの場合true
	 */
	public static boolean isBoolean(Object obj) {
		if (obj instanceof Boolean) {
			return true;
		}
		return false;
	}

	/**
	 * Booleanクラスを継承するクラスはtrueを返します。
	 * @param cls Class
	 * @return Booleanクラスを継承するクラスの場合true
	 */
	public static boolean isBoolean(Class cls) {
		if (Boolean.class.isAssignableFrom(cls)) {
			return true;
		}
		return false;
	}

	/**
	 * Collectionクラスを継承するクラスはtrueを返します。
	 * @param obj オブジェクト
	 * @return Collectionクラスを継承するクラスの場合true
	 */
	public static boolean isCollection(Object obj) {
		if (obj instanceof Collection) {
			return true;
		}
		return false;
	}

	/**
	 * Collectionクラスを継承するクラスはtrueを返します。
	 * @param cls Class
	 * @return Collectionクラスを継承するクラスの場合true
	 */
	public static boolean isCollection(Class cls) {
		if (Collection.class.isAssignableFrom(cls)) {
			return true;
		}
		return false;
	}
	
	/**
	 * Mapクラスを継承するクラスはtrueを返します。
	 * @param obj オブジェクト
	 * @return Mapクラスを継承するクラスの場合true
	 */
	public static boolean isMap(Object obj) {
		if (obj instanceof Map) {
			return true;
		}
		return false;
	}
	
	/**
	 * Mapクラスを継承するクラスはtrueを返します。
	 * @param cls Class
	 * @return Mapクラスを継承するクラスの場合true
	 */
	public static boolean isMap(Class cls) {
		if (Map.class.isAssignableFrom(cls)) {
			return true;
		}
		return false;
	}

	/**
	 * finalフィールドはtrueを返します。
	 * @param fld Field
	 * @return finalフィールドの場合true
	 */
	public static boolean isFinal(Field fld) {
		String[] parts = fld.toString().split(" ");
		for (String part : parts) {
			if ("final".equals(part)) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * staticフィールドはtrueを返します。
	 * @param fld Field
	 * @return staticフィールドの場合true
	 */
	public static boolean isStatic(Field fld) {
		String[] parts = fld.toString().split(" ");
		for (String part : parts) {
			if ("static".equals(part)) {
				return true;
			}
		}
		
		return false;
	}

	/**
	 * Clone可能な型はtrueを返します。
	 * @param obj オブジェクト
	 * @return Clone可能な型の場合true
	 */
	public static boolean isCloneable(Object obj) {
		Class cls = obj.getClass();
		if (cls.isPrimitive()) {
			return false;
		}
		if (cls.isArray()) {
			return false;
		}
		if (obj instanceof Cloneable) {
			return true;
		}
		
		return false;
	}
	
	/**
	 * 配列型はtrueを返します。
	 * @param obj オブジェクト
	 * @return 配列型の場合true
	 */
	public static boolean isArray(Object obj) {
		Class cls = obj.getClass();
		return isArray(cls);
	}
	
	/**
	 * 配列型はtrueを返します。
	 * @param cls Class
	 * @return 配列型の場合true
	 */
	public static boolean isArray(Class cls) {
		if (cls.isArray()) {
			return true;
		}
		return false;
	}

	/**
	 * Stringクラスであればtrueを返します。
	 * @param obj オブジェクト
	 * @return Stringの場合true
	 */
	public static boolean isString(Object obj) {
		if (obj instanceof String) {
			return true;
		}
		return false;
	}

	/**
	 * Stringクラスであればtrueを返します。
	 * @param cls Class
	 * @return Stringの場合true
	 */
	public static boolean isString(Class cls) {
		if (String.class.isAssignableFrom(cls)) {
			return true;
		}
		return false;
	}

	/**
	 * Fieldからgetterを返します
	 * @param fld Field
	 * @return getterメソッドの文字列表記
	 */
	/*
	public static String getGetter(Field fld) {
		if (fld != null) {
			return getGetter(fld.getName(), fld.getType());
		}
		return null;
	}
	*/

	/**
	 * Fieldからgetterを返します
	 * @param fld Field
	 * @param isReflexField 先頭に"_"がついたフィールドかどうか
	 * @return getterメソッドの文字列表記
	 */
	public String getGetter(Field fld, boolean isReflexField) {
		if (fld != null) {
			String fldName = fld.getName();
			if (isReflexField && fldName.startsWith("_") && !fldName.startsWith("_$")) {
				fldName = fldName.substring(1);
			}
			return getGetter(fldName, fld.getType());
		}
		return null;
	}

	/**
	 * Fieldからgetterを返します
	 * @param name フィールド名
	 * @param type フィールドのクラス
	 * @return getterメソッドの文字列表記
	 */
	public String getGetter(String name, Class type) {
		String prefix = null;
		if (type.equals(boolean.class)) {
			prefix = "is";
			if (name.startsWith("is") && name.length() > 2) {
				name = name.substring(2);
			}
		} else {
			prefix = "get";
		}
		return getMethodName(name, prefix);
	}

	/**
	 * Fieldからsetterを返します
	 * @param fld Field
	 * @return setterメソッドの文字列表記
	 */
	/*
	public static String getSetter(Field fld) {
		if (fld != null) {
			return getSetter(fld.getName(), fld.getType());
		}
		return null;
	}
	*/

	/**
	 * Fieldからsetterを返します
	 * @param fld Field
	 * @param isReflexField 先頭に"_"がついたフィールドかどうか
	 * @return setterメソッドの文字列表記
	 */
	public String getSetter(Field fld, boolean isReflexField) {
		if (fld != null) {
			String fldName = fld.getName();
			if (isReflexField && fldName.startsWith("_") && !fldName.startsWith("_$")) {
				fldName = fldName.substring(1);
			}
			return getSetter(fldName, fld.getType());
		}
		return null;
	}

	/**
	 * Fieldからsetterを返します
	 * @param name フィールド名
	 * @param type フィールドのクラス
	 * @return setterメソッドの文字列表記
	 */
	public String getSetter(String name, Class type) {
		String prefix = "set";
		if (type.equals(boolean.class)) {
			if (name.startsWith("is") && name.length() > 2) {
				name = name.substring(2);
			}
		}
		return getMethodName(name, prefix);
	}
	
	/*
	 * Fieldからgetterを返します
	 */
	private static String getMethodName(String name, String prefix) {
		StringBuffer buf = new StringBuffer();
		buf.append(prefix);
		buf.append(name.substring(0,1).toUpperCase());
		if (name.length() > 1) {
			buf.append(name.substring(1));
		}
		return buf.toString();
	}

	/**
	 * Numberを複製します。
	 * @param obj Number型のオブジェクト
	 * @return 複製したNumber
	 */
	public Number cloneNumber(Number obj) {
		if (obj instanceof Integer) {
			return new Integer(obj.intValue());
		} else if (obj instanceof Long) {
			return new Long(obj.longValue());
		} else if (obj instanceof Float) {
			return new Float(obj.floatValue());
		} else if (obj instanceof Double) {
			return new Double(obj.doubleValue());
		} else if (obj instanceof Byte) {
			return new Byte(obj.byteValue());
		} else if (obj instanceof Short) {
			return new Short(obj.shortValue());
		} else if (obj instanceof BigDecimal) {
			return new BigDecimal(obj.doubleValue());
		} else if (obj instanceof BigInteger) {
			return new BigInteger(obj.toString());
		}
		return null;
	}

	/**
	 * Booleanを複製します。
	 * @param obj Boolean型のオブジェクト
	 * @return 複製したBoolean
	 */
	public Boolean cloneBoolean(Boolean obj) {
		return new Boolean(obj.booleanValue());
	}
		
	/**
	 * Collectionを複製します。
	 * @param obj Collection型のオブジェクト
	 * @return 複製したCollection
	 */
	public Collection cloneCollection(Collection obj) {
		Class targetClass = obj.getClass();
		Object target = newInstance(targetClass);
		if (target == null) {
			return null;
		}
		Collection collection = (Collection)target;
		Iterator it = obj.iterator();
		while (it.hasNext()) {
			Object o = it.next();
			collection.add(clone(o));
		}
		return collection;
	}
	
	/**
	 * Mapを複製します。
	 * @param obj Map型のオブジェクト
	 * @return 複製したMap
	 */
	public Map cloneMap(Map obj) {
		Class targetClass = obj.getClass();
		Object target = newInstance(targetClass);
		if (target == null) {
			return null;
		}
		Map map = (Map)target;
		Set entrySet = obj.entrySet();
		Iterator it = entrySet.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			map.put(clone(entry.getKey()), clone(entry.getValue()));
		}
		return map;
	}
	
	/**
	 * 配列を複製します。
	 * @param obj 配列型のオブジェクト
	 * @return 複製した配列
	 */
	public Object cloneArray(Object obj) {
		Class targetClass = obj.getClass();
		Class type =  targetClass.getComponentType();
		int len = Array.getLength(obj);
		Object ret = Array.newInstance(type, len);
		for (int i = 0; i < len; i++) {
			Object a = Array.get(obj, i);
			Object ca = clone(a);
			Array.set(ret, i, ca);
		}
		return ret;
	}

	/**
	 * 文字列を複製します。
	 * @param obj 文字列型のオブジェクト
	 * @return 複製した文字列
	 */
	public String cloneString(String obj) {
		return new StringBuffer(obj).toString();
	}
}
