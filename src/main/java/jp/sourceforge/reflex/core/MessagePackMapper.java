package jp.sourceforge.reflex.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.NotFoundException;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;
import org.msgpack.template.builder.TemplateBuilder;

import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;

import jp.sourceforge.reflex.util.ClassFinder;
import jp.sourceforge.reflex.util.FieldMapper;

public class MessagePackMapper extends ResourceMapper {
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	private MessagePack msgpack = new MessagePack();
	private TemplateRegistry registry; 
	private ReflectionTemplateBuilder builder; 

	public MessagePackMapper(Object jo_packages) {
		this(jo_packages, false, false);
	}
	
	public MessagePackMapper(Object jo_packages, boolean isCamel) {
		this(jo_packages, isCamel, false);
	}
	public MessagePackMapper(Object jo_packages, ReflectionProvider reflectionProvider) {
		this(jo_packages, false, false, reflectionProvider);
	}
	public MessagePackMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote) {
		this(jo_packages, isCamel, useSingleQuote, null);
	}
	public MessagePackMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote, ReflectionProvider reflectionProvider) {
		super(jo_packages, isCamel, useSingleQuote, reflectionProvider);
		
		// パッケージ名からクラス一覧を取得
		ClassFinder classFinder = new ClassFinder();
		
		// msgpack準備(Javassistで動的に作成したクラスはReflectionTemplateBuilderを使わないとエラーになる)
		registry = new TemplateRegistry(null);
		builder = new ReflectionTemplateBuilder(registry);

		Set<String> classNames = null;
		if (jo_packages != null) {
			try {
				if (jo_packages instanceof String) {
					classNames = classFinder.getClassNamesFromPackage((String)jo_packages);
				} else if (jo_packages instanceof Map) {
					classNames = new HashSet<String>();
					for (Object key : ((Map)jo_packages).keySet()) {
						classNames.addAll(classFinder.getClassNamesFromPackage((String)key));
					}
				}else if (jo_packages instanceof Set) {
					classNames = (Set<String>) jo_packages;
				}
			} catch (IOException e) {
				logger.log(Level.WARNING, e.getClass().getName(), e);
			}
		}
		registClass(classNames);
	}
	
	public void registClass(Set<String> classNames) {
		ClassPool pool = ClassPool.getDefault();

		// MessagePackにクラスを登録
		if (classNames != null) {
			Set<Class<?>> registSet = new HashSet<Class<?>>();
			for (String clsName : classNames) {
				try {
//					Class<?> cls = Class.forName(clsName);
//					registClass(cls, registSet);
//				} catch (ClassNotFoundException e) {
//					try {
						Class<?> cls = pool.get(clsName).toClass();
						if (cls.getName().indexOf("Base")<0) {
							System.out.println("clsName="+clsName);
						if (cls.getName().equals("testsvc.Entry")) {
							System.out.println("Entry");
						}
						Template template = builder.buildTemplate(cls);
						registry.register(cls, template);
						msgpack.register(cls,template);
						}
					} catch (CannotCompileException ce) {
						logger.warning("CannotCompileException : " + ce.getMessage());
					} catch (NotFoundException ne) {
						logger.warning("ClassNotFoundException : " + ne.getMessage());
//					}
				}
			}
		}
	}

	public byte[] toMessagePack(Object entity) throws IOException {
		return msgpack.write(entity);
	}
	public void toMessagePack(Object entity, OutputStream out) throws IOException {
		msgpack.write(out, entity);
	}
	public Object fromMessagePack(byte[] msg) throws IOException {
		return msgpack.read(msg);
	}
	public Object fromMessagePack(InputStream msg) throws IOException {
		return msgpack.read(msg);
	}
	
	private void registClass(Class<?> cls, Set<Class<?>> registSet) {
		if (registSet.contains(cls)) {
			return;
		}
		
		registSet.add(cls);
		
		// 自クラス
		Field[] fields = cls.getDeclaredFields();
		registFieldsClass(fields, registSet);

		// スーパークラス
		Class<?> superCls = cls.getSuperclass();
		while (superCls != null && !Object.class.equals(superCls)) {
			fields = superCls.getDeclaredFields();
			registFieldsClass(fields, registSet);
			superCls = superCls.getSuperclass();
		}

			msgpack.register(cls);
	}
	
	/**
	 * MessagePackにクラス内フィールドの使用クラスを登録する。
	 */
	private void registFieldsClass(Field[] fields, Set<Class<?>> registSet) {
		for (Field fld : fields) {
			Class<?> type = fld.getType();
			if (!isSkip(type)) {
				if (FieldMapper.isCollection(type)) {
					// Collectionの場合、ジェネリックタイプも調べる。
					Type genericType = fld.getGenericType();
					if (genericType != null && genericType instanceof ParameterizedType) {
						ParameterizedType paramType = (ParameterizedType)genericType;
						Type[] actualTypes = paramType.getActualTypeArguments();
						if (actualTypes.length > 0) {
							Class<?> actualType = (Class<?>)actualTypes[0];
							if (!isSkip(actualType)) {
								registClass(actualType, registSet);
							}
						}
					}

				} else {
					registClass(type, registSet);
				}
			}
		}
	}
			
	private boolean isSkip(Class<?> type) {
		if (type.isPrimitive()) {
			// プリミティブ型はスキップする。
			return true;
		}
		if (type.getName().startsWith("java.")) {
			if (!FieldMapper.isCollection(type)) {
				return true;
			}
		}
		return false;
	}
	
	// プリミティブ型、java〜パッケージは除く。
	// Listの場合、ジェネリックタイプも調べる。

}
