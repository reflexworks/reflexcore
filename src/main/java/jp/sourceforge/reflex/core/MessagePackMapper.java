package jp.sourceforge.reflex.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Stack;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.Modifier;
import javassist.NotFoundException;
import javassist.bytecode.BadBytecode;
import javassist.bytecode.SignatureAttribute;
import jp.reflexworks.atom.entry.Element;
import jp.sourceforge.reflex.dto.CamelBean;
import jp.sourceforge.reflex.util.ClassFinder;
import jp.sourceforge.reflex.util.DateUtil;
import jp.sourceforge.reflex.util.FieldMapper;
import org.json.JSONException;
import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;
import org.msgpack.type.Value;
import org.msgpack.util.json.JSONBufferUnpacker;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.mapper.DefaultMapper;
import java.util.logging.Level;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class MessagePackMapper extends ResourceMapper {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	// *・・必須項目 TODO デフォルト値を付けるか？
	// 
	private static final String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?((?:#|%[0-9a-zA-Z]+|\\[([0-9]+)?\\]|\\{([\\-0-9]*)~?([\\-0-9]+)?\\})?)(\\*?)(?::(.+))?$";

	private static final String MANDATORY = "*";
	private static final String ENCRYPTED = "%";
	private static final String INDEX = "#";
	private static final String ARRAY = "[";

		
	// atom クラス（順番は重要、TODO これらは taggingserviceのConstantsに移すべきか?）
	// このクラス内でatomクラスを区別するのは難しい
	
	public static final String[] ATOMCLASSES = { "jp.reflexworks.atom.source.Author",
			"jp.reflexworks.atom.source.Category",
			"jp.reflexworks.atom.source.Contributor",
			"jp.reflexworks.atom.source.Generator",
			"jp.reflexworks.atom.source.Link",
			"jp.reflexworks.atom.source.Source",
			"jp.reflexworks.atom.entry.Author",
			"jp.reflexworks.atom.entry.Category",
			"jp.reflexworks.atom.entry.Content",
			"jp.reflexworks.atom.entry.Contributor",
			"jp.reflexworks.atom.entry.Link",
			"jp.reflexworks.atom.entry.Element",		// Elementは本来はATOMクラスではないがここに必要
			"jp.reflexworks.atom.feed.Author",
			"jp.reflexworks.atom.feed.Category",
			"jp.reflexworks.atom.feed.Generator",
			"jp.reflexworks.atom.feed.Contributor",
			"jp.reflexworks.atom.feed.Link"
			 };

	public static final String[] B2CLASSES = {"jp.co.kuronekoyamato.b2web.model.Import_item",
		"jp.co.kuronekoyamato.b2web.model.Payment",
		"jp.co.kuronekoyamato.b2web.model.Pattern",
		"jp.co.kuronekoyamato.b2web.model.Pattern_list",
		"jp.co.kuronekoyamato.b2web.model.Error",
		"jp.co.kuronekoyamato.b2web.model.Error_list",
		"jp.co.kuronekoyamato.b2web.model.Shipper_label",
		"jp.co.kuronekoyamato.b2web.model.Payment_list",
		"jp.co.kuronekoyamato.b2web.model.Email_setting",
		"jp.co.kuronekoyamato.b2web.model.Deffered_status",
		"jp.co.kuronekoyamato.b2web.model.Delivery_email_message",
		"jp.co.kuronekoyamato.b2web.model.Shipment_email_message",
		"jp.co.kuronekoyamato.b2web.model.Print_design_left",
		"jp.co.kuronekoyamato.b2web.model.Print_design_right",
		"jp.co.kuronekoyamato.b2web.model.Invoice",
		"jp.co.kuronekoyamato.b2web.model.Request_from",
		"jp.co.kuronekoyamato.b2web.model.Ship_post",
		"jp.co.kuronekoyamato.b2web.model.Master_share",
		"jp.co.kuronekoyamato.b2web.model.Email_setting_list",
		"jp.co.kuronekoyamato.b2web.model.Deffered_status_list",
		"jp.co.kuronekoyamato.b2web.model.Collect_shipped",
		"jp.co.kuronekoyamato.b2web.model.Collect_new",
		"jp.co.kuronekoyamato.b2web.model.Collect_issued",
		"jp.co.kuronekoyamato.b2web.model.Hatsu_shipped",
		"jp.co.kuronekoyamato.b2web.model.Hatsu_new",
		"jp.co.kuronekoyamato.b2web.model.Hatsu_issued",
		"jp.co.kuronekoyamato.b2web.model.Issue_room_setting",
		"jp.co.kuronekoyamato.b2web.model.Delivery_email_message_list",
		"jp.co.kuronekoyamato.b2web.model.Shipment_email_message_list",
		"jp.co.kuronekoyamato.b2web.model.Printer_settings",
		"jp.co.kuronekoyamato.b2web.model.General_settings",
		"jp.co.kuronekoyamato.b2web.model.Invoice_list",
		"jp.co.kuronekoyamato.b2web.model.Request_from_list",
		"jp.co.kuronekoyamato.b2web.model.Ship_post_list",
		"jp.co.kuronekoyamato.b2web.model.Master_share_list",
		"jp.co.kuronekoyamato.b2web.model.Image_choice",
		"jp.co.kuronekoyamato.b2web.model.Package_size",
		"jp.co.kuronekoyamato.b2web.model.Sorting_code",
		"jp.co.kuronekoyamato.b2web.model.Center",
		"jp.co.kuronekoyamato.b2web.model.Telephone_address",
		"jp.co.kuronekoyamato.b2web.model.Address",
		"jp.co.kuronekoyamato.b2web.model.System_date",
		"jp.co.kuronekoyamato.b2web.model.Email_notification",
		"jp.co.kuronekoyamato.b2web.model.Status_count",
		"jp.co.kuronekoyamato.b2web.model.Event_log",
		"jp.co.kuronekoyamato.b2web.model.Shipment_group",
		"jp.co.kuronekoyamato.b2web.model.Import_pattern",
		"jp.co.kuronekoyamato.b2web.model.Content_details",
		"jp.co.kuronekoyamato.b2web.model.Tracking",
		"jp.co.kuronekoyamato.b2web.model.Customer",
		"jp.co.kuronekoyamato.b2web.model.Consignee",
		"jp.co.kuronekoyamato.b2web.model.Shipper",
		"jp.co.kuronekoyamato.b2web.model.Shipment",
		"jp.co.kuronekoyamato.b2web.model.Entry",
		"jp.co.kuronekoyamato.b2web.model.Feed"
		};
	
	private static final String ENTRYBASE = "jp.reflexworks.atom.entry.EntryBase";
	private static final String FEEDBASE = "jp.reflexworks.atom.feed.FeedBase";

	private static final int CONTENT = 8;
	private static final int CONTRIBUTOR = 9;
	private static final int ENTRYLINK = 10;

	
	// Arrayの要素クラス
	public static final String ELEMENTCLASS = "jp.reflexworks.atom.entry.Element";
	private static final String ELEMENTSIG = "Ljava/util/List<Ljp/reflexworks/atom/entry/Element;>;";


	/** ATOM : Feed package */
	public static final String ATOM_PACKAGE_FEED = "jp.reflexworks.atom.feed";
	/** ATOM : Entry package */
	public static final String ATOM_PACKAGE_ENTRY = "jp.reflexworks.atom.entry";
	/** ATOM : Source package */
	public static final String ATOM_PACKAGE_SOURCE = "jp.reflexworks.atom.source";
	/** ATOM : Package map */
	public static Map<String, String> ATOM_PACKAGE;
	static {
		ATOM_PACKAGE = new HashMap<String, String>();
		ATOM_PACKAGE.put(ATOM_PACKAGE_FEED, "");
		ATOM_PACKAGE.put(ATOM_PACKAGE_ENTRY, "");
		ATOM_PACKAGE.put(ATOM_PACKAGE_SOURCE, "");
	}

	private MessagePack msgpack = new MessagePack();
	private List<Meta> metalist;
	private TemplateRegistry registry;
	private ReflectionTemplateBuilder builder;
	private ClassPool pool;
	private Loader loader;
	protected String packagename;	
	
	/**
	 * コンストラクタ
	 * @param jo_packages
	 * @throws ParseException
	 */
	public MessagePackMapper(Object jo_packages) throws ParseException {
		this(jo_packages, false, false);
	}
	
	public MessagePackMapper(Object jo_packages, boolean isCamel) throws ParseException {
		this(jo_packages, isCamel, false);
	}
	public MessagePackMapper(Object jo_packages, ReflectionProvider reflectionProvider) throws ParseException {
		this(jo_packages, false, false, reflectionProvider);
	}
	public MessagePackMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote) throws ParseException {
		this(jo_packages, isCamel, useSingleQuote, null);
	}
	
	public MessagePackMapper(Object jo_packages, boolean isCamel,
			boolean useSingleQuote, ReflectionProvider reflectionProvider) throws ParseException {
		super(getJo_packages(jo_packages), isCamel, useSingleQuote, reflectionProvider);

		this.pool = new ClassPool();
		this.pool.appendSystemPath();
	    this.loader = new Loader(this.pool);

	    // XMLデシリアライザのRXMapperのClassloaderにセットする。サーブレットでのメモリ増加に注意
			((RXMapper)this.getClassMapper()).wrapped = new DefaultMapper(this.loader); 
	    
		registry = new TemplateRegistry(null);
		builder = new ReflectionTemplateBuilder(registry); // msgpack準備(Javassistで動的に作成したクラスはReflectionTemplateBuilderを使わないとエラーになる)

		if (jo_packages instanceof String[]|jo_packages instanceof List) {
			// Entityテンプレートからメタ情報を作成する
			metalist = getMetalist(jo_packages);
			registerClasses();
			
		}else if (jo_packages instanceof Map|jo_packages instanceof String) {

			// package名からregistClass
			// パッケージ名からクラス一覧を取得
			ClassFinder classFinder = new ClassFinder(loader);
			Set<String> classnames = null;  
			if (jo_packages != null) {
				try {
					if (jo_packages instanceof String) {
						packagename = (String) jo_packages;			// 業務アプリのEntityクラス
						classnames = new LinkedHashSet<String>();
						classnames.addAll(new ArrayList(Arrays.asList(ATOMCLASSES)));
						classnames.addAll(classFinder.getClassNamesFromPackage((String)jo_packages));	// ATOM Classesは先に読む必要がある
					} else if (jo_packages instanceof Map) {
						classnames = new LinkedHashSet<String>();
						classnames.addAll(new ArrayList(Arrays.asList(ATOMCLASSES))); // ATOM Classesは先に読む必要がある
						
						for (String key : ((Map<String,String>)jo_packages).keySet()) {
							if (key.indexOf(".atom.")<0) {
								packagename = key;			// 業務アプリのEntityクラス
								classnames.addAll(classFinder.getClassNamesFromPackage(key));
							}
						}
					}
				} catch (IOException e) {
					logger.log(Level.WARNING, e.getClass().getName(), e);
					throw new ParseException(e.getMessage(), 0);
				}
			}

			// MessagePackにクラスを登録
			if (classnames != null) {
				Set<Class<?>> registSet = new HashSet<Class<?>>();
				for (String classname : classnames) {
					try {
						loader.delegateLoadingOf(classname);			// 既存classは先に読めるようにする
						Class<?> cls = loader.loadClass(classname);
						registerStaticClasses(cls, registSet);
					} catch (ClassNotFoundException e) {
						logger.warning("ClassNotFoundException : " + e.getMessage());
						throw new ParseException(e.getMessage(), 0);
					}
				}
			}

		}
	}

	private void registerStaticClasses(Class<?> cls, Set<Class<?>> registerSet) throws ParseException {
		if (registerSet.contains(cls)) {
			return;
		}
		
		registerSet.add(cls);
		
		// 自クラス
		Field[] fields = cls.getDeclaredFields();
		registFieldsClass(fields, registerSet);

		// スーパークラス
		Class<?> superCls = cls.getSuperclass();
		while (superCls != null && !Object.class.equals(superCls)) {
			fields = superCls.getDeclaredFields();
			registFieldsClass(fields, registerSet);
			superCls = superCls.getSuperclass();
		}
		// abstruct classを除外
		if (!Modifier.isAbstract(cls.getModifiers())) {
			Template template = builder.buildTemplate(cls);
			registry.register(cls,template);
			// EntryBaseはEntryとして登録
			if (isEntry(cls.getName())) {
				msgpack.register(cls, template);
				try {
					loader.delegateLoadingOf(ENTRYBASE);			
					cls = loader.loadClass(ENTRYBASE);
				} catch (ClassNotFoundException e) {
					throw new ParseException(e.getMessage(), 0);
				}
				registry.register(cls, template);
			}else if (isFeed(cls.getName())) {
				msgpack.register(cls, template);
				try {
					cls = loader.loadClass(FEEDBASE);
					loader.delegateLoadingOf(FEEDBASE);		
				} catch (ClassNotFoundException e) {
					throw new ParseException(e.getMessage(), 0);
				}
				registry.register(cls, template);
			}
		}
	}
	
	/**
	 * MessagePackにクラス内フィールドの使用クラスを登録する。
	 * @throws ParseException 
	 */
	private void registFieldsClass(Field[] fields, Set<Class<?>> registerSet) throws ParseException {
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
								registerStaticClasses(actualType, registerSet);
							}
						}
					}

				} else {
					registerStaticClasses(type, registerSet);
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
		if (isBaseclass(type.getName())) {
			return true;
		}
		
		return false;
	}
	
	// プリミティブ型、java〜パッケージは除く。
	// Listの場合、ジェネリックタイプも調べる。

	private boolean isBaseclass(String name) {
		if (name==null) return false;
		return name.indexOf(ENTRYBASE)>=0||name.indexOf(FEEDBASE)>=0;
	}
	
	/*
	 * root entry
	 */
	private String getRootEntry(boolean isFeed) {
		return isFeed ? packagename + ".Feed":packagename + ".Entry";
	}
	
	private boolean isEntry(String classname) {
		int dot = classname.lastIndexOf(".");
		if (dot>0) {
			String token = classname.substring(dot);
			return token.equals(".Entry");
		}
		return false;
	}

	private boolean isFeed(String classname) {
		int dot = classname.lastIndexOf(".");
		if (dot>0) {
			String token = classname.substring(dot);
			return token.equals(".Feed");
		}
		return false;
	}
	
	private static String toCamelcase(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * ATOM Packageとユーザ Packageを取得する
	 * @param jo_packages
	 * @return
	 */
	private static Map<String, String> getJo_packages(Object jo_packages) {
		Map result_packages = new LinkedHashMap<String,String>();
		result_packages.putAll(ATOM_PACKAGE);
		if (jo_packages instanceof String[]) {
			result_packages.put(parseLine0(((String[]) jo_packages)[0]), "");
		}else if (jo_packages instanceof List) {
			result_packages.put(parseLine0(""+((List) jo_packages).get(0)), "");
		}else if (jo_packages instanceof Map) {
			return (Map) jo_packages;
		}
		return result_packages;
	}
	
	/*
	 * Entityメタ情報インナークラス
	 */
	private class Meta {

		public int level; // 階層のレベル
		public String type; // タイプ
		public String parent; // 属しているクラス
		public String self; // 項目名
		public String privatekey; // 暗号化のための秘密鍵
		public boolean isIndex; // インデックス
		public boolean isMandatory; // 必須項目
		public String regex; // バリーデーション用正規表現

		public boolean isArray; // 配列
		public boolean isMap;
		public String min;		// 最小 ""の場合は指定なし
		public String max;		// 最大　""の場合は指定なし

		public String getSelf() {
			if (self==null) return null;
			return toCamelcase(self);
		}
		
		public boolean hasChild() {
			return type.indexOf(getSelf())>0;
		}
		// int,long,float,double
		public boolean isNumeric() {
			if (type.equals("Integer")||type.equals("Long")||type.equals("Float")||type.equals("Double")) {
				return true;
			}else {
				return false;
			}
		}
		
	}
	
	public Class getClass(String classname) throws ClassNotFoundException {
		// ATOMの優先すべき項目名の場合はATOMクラスを読む
		int dot = classname.lastIndexOf(".");
		if (dot>0) {
		String token = classname.substring(dot);
		if (token.equals(".Link")) {
			classname = ATOMCLASSES[ENTRYLINK];
		}else 
		if (token.equals(".Contributor")) {
			classname = ATOMCLASSES[CONTRIBUTOR];
		}else 
		if (token.equals(".Content")) {
			classname = ATOMCLASSES[CONTENT];
		}
		}
		return loader.loadClass(classname);   
	}
	
	public ClassPool getPool() {
		return pool;
	}

	public byte[] toMessagePack(Object entity) throws IOException  {
		return msgpack.write(entity);
	}

	public void toMessagePack(Object entity, OutputStream out)
			throws IOException {
		msgpack.write(out, entity);
	}

	public Object fromMessagePack(byte[] msg,boolean isFeed) throws IOException, ClassNotFoundException  {
		return msgpack.read(msg, loader.loadClass(getRootEntry(isFeed)));
	}

	public Object toArray(byte[] msg) throws IOException,
			ClassNotFoundException {
		return msgpack.read(msg);
	}

	public Object fromMessagePack(InputStream msg,boolean isFeed) throws IOException,
			ClassNotFoundException {
		return msgpack.read(msg, loader.loadClass(getRootEntry(isFeed)));
	}
	
	/**
	 * MessagePackを使ってJSONをデシリアライズする
	 */
	public Object fromJSON(String json) throws JSONException{
        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap(json.getBytes());
        	Value v;
			try {
				v = u.readValue();
			} catch (IOException e) {
	        	throw new JSONException(e);
			}
        	return parseValue("",v);
	}

	public Object fromArray(String array,boolean isFeed) throws JSONException{
        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap(array.getBytes());
        try {
        	return msgpack.convert(u.readValue(),loader.loadClass(getRootEntry(isFeed)));
        } catch(Exception e) {
        	throw new JSONException(e);
        }
	}

	private String getSignature(String classname) {
		String signature = "Ljava/util/List<L"+classname.replace(".", "/")+";>;";
		return signature;
	}
	
	
	/**
	 * メタ情報から動的クラスを生成する
	 * 
	 * @param metalist
	 * @return 生成したクラス名のSet
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 */
	private Set<String> generateClass()
			throws CannotCompileException {

		pool.importPackage("java.util.Date");

		Set<String> classnames = getClassnames();

		for (String classname : classnames) {
			CtClass cc;
			try {
				// ATOM classなど既に登録されていたらそれを使う
				cc = pool.get(classname);
			} catch (NotFoundException ne1) {
				cc = pool.makeClass(classname);
				if (isEntry(classname)) {
					CtClass cs;
					try {
						loader.delegateLoadingOf(ENTRYBASE);			// 既存classは先に読めるようにする
						cs = pool.get(ENTRYBASE);
						cc.setSuperclass(cs); // superclassの定義
					} catch (NotFoundException e) {
						throw new CannotCompileException(e);
					}
				} else if (isFeed(classname)) {
					CtClass cs;
					try {
						loader.delegateLoadingOf(FEEDBASE);			// 既存classは先に読めるようにする
						cs = pool.get(FEEDBASE);
						cc.setSuperclass(cs); // superclassの定義
				        
					} catch (NotFoundException e) {
						throw new CannotCompileException(e);
					}
				} 
			}

			StringBuffer validation = new StringBuffer();
			validation.append(validateFuncS);
			
			for (int i = 0; i < matches(classname); i++) {

				Meta meta = getMetaByLevel(classname, i);
				String type = "public " + meta.type + " ";
				String field = meta.self + ";";
				try {
					// ATOM classなど既に登録されていたらそれを使う
					CtField f = cc.getField(meta.self);
					
				} catch (NotFoundException ne2) {
					
					// for Array
					if (meta.isArray) {
						try {
						
					    CtClass objClass = pool.get("java.util.List");
					    CtField arrayfld = new CtField(objClass, meta.self, cc); 
					    arrayfld.setModifiers(Modifier.PUBLIC);
				        SignatureAttribute.ObjectType st = SignatureAttribute.toFieldSignature(ELEMENTSIG);
				        arrayfld.setGenericSignature(st.encode());    // <T:Ljava/lang/Object;>Ljava/lang/Object;
				        cc.addField(arrayfld);
				        
						} catch (NotFoundException e) {
							throw new CannotCompileException(e);
						} catch (BadBytecode e) {
							throw new CannotCompileException(e);
						}
					}else if (meta.isMap) {

						try {
							
						    CtClass objClass = pool.get("java.util.List");
						    CtField arrayfld = new CtField(objClass, meta.self, cc); 
						    arrayfld.setModifiers(Modifier.PUBLIC);
					        SignatureAttribute.ObjectType st = SignatureAttribute.toFieldSignature(getSignature(packagename+"."+meta.getSelf()));
					        arrayfld.setGenericSignature(st.encode());    // <T:Ljava/lang/Object;>Ljava/lang/Object;
					        cc.addField(arrayfld);
					        
							} catch (NotFoundException e) {
								throw new CannotCompileException(e);
							} catch (BadBytecode e) {
								throw new CannotCompileException(e);
							}
					
					}
					else {
						CtField f2 = CtField.make(type + field, cc); // フィールドの定義
						cc.addField(f2);
					
						CtMethod m = CtNewMethod.make(type + "get" + meta.getSelf()
								+ "() {" + "  return " + meta.self + "; }", cc);
						cc.addMethod(m);
						m = CtNewMethod.make("public void set" + meta.getSelf()
							+ "(" + meta.type + " " + meta.self + ") { this."
							+ meta.self + "=" + meta.self + ";}", cc);
						cc.addMethod(m);
					}

					// 暗号キー
					if (meta.privatekey!=null) {
						CtMethod m2 = CtNewMethod.make("public String _"+meta.getSelf()+"() {return \"" + meta.privatekey+"\";}", cc);
						cc.addMethod(m2);
					}
				}
								
				// バリデーションチェック
				if (meta.isArray) {
					validation.append(getValidatorLogicArray(meta));
				}else {
					validation.append(getValidatorLogic(meta));
				
				// 子要素のValidation
				if (meta.hasChild()) {
					if (meta.isMap) {
						validation.append("if ("+meta.self+"!=null) for (int i=0;i<"+meta.self+".size();i++) { (("+meta.type +")"+meta.self+".get(i)).validate();}"); 
					}
					else {
						validation.append("if ("+meta.self+"!=null) "+ meta.self+".validate();");
					}
				}
				}

			}
			// Validation Method追加
				validation.append(validateFuncE);
				CtMethod m = CtNewMethod.make(validation.toString(), cc);
				cc.addMethod(m);
		}
		
		return classnames;
	}
	
	
	/**
	 * バリデーションロジック（必須チェックと正規表現チェック）
	 * @param meta
	 * @return
	 */
	private final String validateFuncS = "public boolean validate() throws java.text.ParseException {";
	private final String validateFuncE = "return true;}";
	
	private String getValidatorLogic(Meta meta) {
		String line = "";
		if (meta.isMandatory) {
			line = "if ("+meta.self+"==null) throw new java.text.ParseException(\"Required property '" + meta.self + "' not specified.\",0);";
		}
		if (meta.regex!=null&&!meta.regex.isEmpty()) {
			line += "if ("+meta.self+"!=null) {";
			line += "java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(\""+meta.regex+"\");";
			line += "java.util.regex.Matcher matcher = pattern.matcher(\"\"+"+meta.self+");";
			line += "if (!matcher.find()) throw new java.text.ParseException(\"Property '"+ meta.self + "' is not valid.(regex="+meta.regex+", value=\"+"+meta.self+"+\")\",0);";
			line += "}";			
		}
		line += getMinmax(meta);

		return line;
	}

	private String getValidatorLogicArray(Meta meta) {
		String line = "";
		if (meta.isMandatory) {
			line = "if ("+meta.self+"==null) throw new java.text.ParseException(\"Required property '" + meta.self + "' not specified.\",0);";
		}
		if (meta.regex!=null&&!meta.regex.isEmpty()) {
			line += "if ("+meta.self+"!=null) for (int i=0;i<"+meta.self+".size();i++) {";
			line += "java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(\""+meta.regex+"\");";
			line += "String val=(("+ELEMENTCLASS +")"+meta.self+".get(i))._$$text;";
			line += "java.util.regex.Matcher matcher = pattern.matcher(\"+val+\");";
			line += "if (!matcher.find()) throw new java.text.ParseException(\"Property '"+ meta.self + "' is not valid.(regex="+meta.regex+", value=\"+val+\")\",0);";
			line += "}";			
		}
		
		line += getMinmax(meta);
		
		return line;
	}

	private String getMinmax(Meta meta) {
		String line = "";
		if (meta.min!=null&&!meta.min.isEmpty()) {
			long max;
			long min = Long.parseLong(meta.min);
			if (meta.max!=null&&!meta.max.isEmpty()) {
				// min~maxチェック
				max = Long.parseLong(meta.max);
				if (meta.isNumeric()) {
					line += "if ("+meta.self+"!=null&&"+meta.self+".longValue()<"+min+") throw new java.text.ParseException(\"Minimum number of '" + meta.self + "' not met.\",0);";
					line += "if ("+meta.self+"!=null&&"+max+"<"+meta.self+".longValue()) throw new java.text.ParseException(\"Maximum number of '" + meta.self + "' exceeded.\",0);";
				}else if (meta.isArray||meta.hasChild()) {
					line += "if ("+meta.self+"!=null&&"+meta.self+".size()<"+min+") throw new java.text.ParseException(\"Minimum number of '" + meta.self + "' not met.\",0);";
					line += "if ("+meta.self+"!=null&&"+max+"<"+meta.self+".size()) throw new java.text.ParseException(\"Maximum number of '" + meta.self + "' exceeded.\",0);";
				}
			}else {
				// maxチェックのみ
				max = min;
				if (meta.isNumeric()) {
					line += "if ("+meta.self+"!=null&&"+max+"<"+meta.self+".longValue()) throw new java.text.ParseException(\"Maximum number of '" + meta.self + "' exceeded.\",0);";
				}else if (meta.isArray||meta.hasChild()) {
					line += "if ("+meta.self+"!=null&&"+max+"<"+meta.self+".size()) throw new java.text.ParseException(\"Maximum number of '" + meta.self + "' exceeded.\",0);";
				}
			}
		}
		return line;
	}
	
	private static String parseLine0(String line) {
		Pattern patternf = Pattern.compile(field_pattern);
		Matcher matcherf = patternf.matcher(line);

			if (matcherf.find()) {
				return matcherf.group(2);
			}else 
				return "";

	}

	/**
	 * Entity Templateからメタ情報を作成する
	 * 
	 * @param entitytmpl
	 * @return メタ情報
	 * @throws ParseException
	 */
	private List<Meta> getMetalist(Object entitytempl) throws ParseException {
		
		String[] entitytmpl = null;
		if (entitytempl instanceof String[]) {
			entitytmpl = (String[]) entitytempl;
		}else if (entitytempl instanceof List) {
			entitytmpl = (String[]) ((List) entitytempl).toArray(new String[((List<String>) entitytempl).size()]);;
			
		}
		// 先頭のパッケージ名を退避してentryに置き換える
		this.packagename = parseLine0(entitytmpl[0]);
				
		List<Meta> metalist = new ArrayList<Meta>();

		Pattern patternf = Pattern.compile(field_pattern);

		Meta meta = new Meta();
		Matcher matcherf;
		Stack<String> stack = new Stack<String>();
		String classname = getRootEntry(true);
		stack.push(classname);
		int level = 0;
		
		for (int l=0;l<entitytmpl.length;l++) {
			String line = entitytmpl[l];
			matcherf = patternf.matcher(line);

			if (matcherf.find()) {
				if (meta.level != matcherf.group(1).length()) {
					level = matcherf.group(1).length();
					if (meta.level < level) {
						//２段階下がるとエラー
						if (meta.level+1<level) {
							throw new ParseException("Wrong Indentation:" + line, 0);	
						}
						classname = packagename + "." + meta.getSelf();
						stack.push(classname);
						if (!meta.type.equals("String")) throw new ParseException("Can't specify (Type) for Map type:" + line, 0);
						meta.type = classname; // 子要素を持っている場合にタイプを自分にする
					} else {
						for (int i = 0; i < meta.level - level; i++) {
							stack.pop();
							classname = stack.peek();
						}
					}
				}
				if (meta.self != null) {
					if (meta.regex!=null&&meta.regex.length()>0&&meta.hasChild()) {
							throw new ParseException("Syntax error(illegal character in property or regex uses in parent object):" + meta.self, 0);
					}
					metalist.add(meta);
				}
				meta = new Meta();
				meta.level = level;
				meta.parent = classname;
				meta.privatekey = null;
				meta.isIndex = false;
				meta.isMandatory = matcherf.group(9).equals(MANDATORY);
				
				meta.regex = matcherf.group(10);
				if (l==0) {
					meta.self = "entry";
				}else {
					meta.self = matcherf.group(2);
				}
				meta.min = matcherf.group(7);
				meta.max = matcherf.group(8);

				if (matcherf.group(4) != null) {
					String typestr = matcherf.group(4).toLowerCase();
					if (typestr.equals("date")) {
						meta.type = "Date";
					} else if (typestr.equals("int")) {
						meta.type = "Integer";
					} else if (typestr.equals("long")) {
						meta.type = "Long";
					} else if (typestr.equals("float")) {
						meta.type = "Float";
					} else if (typestr.equals("double")) {
						meta.type = "Double";
					} else if (typestr.equals("boolean")) {
						meta.type = "Boolean";
						if (meta.min!=null) throw new ParseException("Can't specify (Type) for Boolean type:" + line, 0);
					} else {
						meta.type = "String"; // その他
					}
				} else {
					if (meta.type==null) {
						meta.type = "String"; // 省略時
					}
				}
				
				if (meta.min!=null&&meta.type.equals("String")) {
					meta.isMap = true;
				} else {
					if (matcherf.group(5).equals(INDEX)) {
						meta.isIndex = true;
					} else if (matcherf.group(5).startsWith(ENCRYPTED)) {
						meta.privatekey = matcherf.group(5);	// %付きで保存
					} 
					else if (matcherf.group(5).indexOf(ARRAY)>=0) {
						// for Array
						meta.isArray = true;
						meta.min = matcherf.group(6);	// maxの要素数をminに入れる
					}
					
					
				}
				
			} else {
				throw new ParseException("Unexpected Format:" + line, 0);
			}
		}
		metalist.add(meta);
		return metalist;

	}

	/**
	 * 動的に生成するクラス名を下位から順にして返す
	 * @param metalist
	 * @return クラス名のSet
	 */
	private Set<String> getClassnames() {

		HashSet<String> classnames = new LinkedHashSet<String>();
		int size = metalist.size();

		int levelmax = 0;
		for (Meta meta : metalist) {
			if (levelmax < meta.level) {
				levelmax = meta.level;
			}
		}

		for (int l = levelmax; l >= 0; l--) {
			for (int i = size - 1; i >= 0; i--) {
				if (metalist.get(i).level == l) {
					classnames.add(metalist.get(i).parent);
				}
			}

		}
		return classnames;
	}
	
	/**
	 * 同一levelのクラスについて検索しメタ情報を返す
	 * @param metalist
	 * @param classname
	 * @param level
	 * @return
	 */
	private Meta getMetaByLevel(String classname, int level) {

		for (Meta meta : metalist) {
			if (meta.parent.equals(classname)) {
				level--;
				if (level < 0) {
					return meta;
				}
			}
		}
		return null;
	}

	/**
	 * メタ情報でclassnameに名前が一致するものをカウントする
	 * @param metalist
	 * @param classname
	 * @return
	 */
	private int matches(String classname) {

		int i = 0;
		for (Meta meta : metalist) {
			if (meta.parent.equals(classname)) {
				i++;
			}
		}
		return i;
	}

	/**
	 * Entity Templateからクラスを動的に作成しmsgpackに登録する
	 * 
	 * @throws ParseException
	 */
	private void registerClasses() throws ParseException {

		List<String> classnames = new ArrayList<String>();
		classnames.addAll(new ArrayList(Arrays.asList(ATOMCLASSES)));
		
		try {
			classnames.addAll(generateClass());
			registerClass(classnames);
		} catch (CannotCompileException e) {
			throw new ParseException("Cannot Compile:" + e.getMessage(), 0);
		}
	}

	/**
	 * クラスをmsgpackに登録する
	 * 
	 * @param classnames
	 * @throws CannotCompileException
	 */
	private void registerClass(List<String> classnames)
			throws CannotCompileException {

		Class<?> cls = null;
		Template template = null;

		for (String classname : classnames) {
			// 静的なクラスであるatomパッケージは親のクラスローダにロード(これがないとClassCastException)
			if (classname.indexOf(".atom.") > 0) {
				loader.delegateLoadingOf(classname);
			}
			
			if (!isBaseclass(classname)) {
				try {	
					cls = loader.loadClass(classname);
				
				template = builder.buildTemplate(cls);
				// 途中はregistryに登録
				registry.register(cls, template);
				// EntryBaseはEntryとして登録
				if (isEntry(classname)) {
					cls = loader.loadClass(ENTRYBASE);
					registry.register(cls, template);
				}
				} catch (ClassNotFoundException e) {
					throw new CannotCompileException(e);
				}
				
				// EntryやFeedの場合はmsgpackに登録
				if (isEntry(classname)||isFeed(classname)) {
					msgpack.register(cls, template);
				}
			}
		}
	}
	
	/**
	 * MessagePackのValueオブジェクトからEntityクラスを作成する
	 * @param classname
	 * @param value
	 * @return
	 * @throws JSONException 
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws ParseException 
	 */
	private Object parseValue(String classname,Value value) throws JSONException  {

		Object parent = null;
		Class cc = null;
		Field f = null;
		try {
        if (value.isMapValue()) {
        	boolean isCreated = false;
        	for(Entry<Value,Value> e:value.asMapValue().entrySet()) {
    			String fld = e.getKey().toString().replace("\"", "").replace("-", "__");  // フィールド名の変換。ハイフン
        		if (!classname.isEmpty()) {
        			cc = this.getClass(classname);
    				f = cc.getField(fld);
        		}
        		
        		if ((e.getValue()).isMapValue()) {
        			String childclsname = packagename+"."+toCamelcase(fld);
        			Object child = parseValue(childclsname,e.getValue());
            		if (!classname.isEmpty()) {
            			if (!isCreated) parent = (Object) cc.newInstance();
    					f.set(parent, child);
    					isCreated = true;
            		}else {
            			return child;
            		}
        			
        		}else {
        			if (e.getValue().isArrayValue()) {
        				if (!isCreated) {
        					parent = (Object) cc.newInstance();
        					isCreated = true;
        				}
        				List child = new ArrayList();
        				for(Value v:e.getValue().asArrayValue().getElementArray()) {
        					if (v.isMapValue()) {
        	        			String childclsname = packagename+"."+toCamelcase(fld);
               					child.add(parseValue(childclsname,v));
               					f.set(parent, child);
        					}else if (v.isRawValue()) {
               					Element element = new Element();
               					element._$$text = v.toString().replace("\"", "");
               					child.add(element);
               					f.set(parent, child);
        					}
        				}
        				
        			}
        			else {
        				if (!isCreated) {
        					parent = (Object) cc.newInstance();
        					isCreated = true;
        				}
                		if (e.getValue().isBooleanValue()) f.set(parent, e.getValue().asBooleanValue().getBoolean());
                		else if (e.getValue().isIntegerValue()) f.set(parent, e.getValue().asIntegerValue().getInt());
                		else if (e.getValue().isFloatValue()) f.set(parent, e.getValue().asFloatValue().getFloat());
                		else if (e.getValue().isRawValue()) {
                			String v = e.getValue().toString().replace("\"","");
                			if (f.getType().getName().equals("java.util.Date")) {
                				try {
                					Date d = DateUtil.getDate(v);
                					f.set(parent, d);
                				}catch(Exception de) {
                					throw new ParseException(de.getMessage()+" / "+ v, 0);
                				}
                			}else {
                				f.set(parent, v);
                			}
                		}
                			

        			}
        		}
        	}
        }
    	return parent;
		}catch (Exception e) {
        	throw new JSONException(e);
		}
		
	}

	
	/**
	 * 圧縮する
	 * 
	 * @param dataByte
	 * @return
	 * @throws IOException
	 */
	public byte[] deflate(byte[] dataByte) throws IOException {

		Deflater def = new Deflater();
		def.setLevel(Deflater.BEST_SPEED);
		def.setInput(dataByte);
		def.finish();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(
				dataByte.length);
		byte[] buf = new byte[1024];
		while (!def.finished()) {
			int compByte = def.deflate(buf);
			byteArrayOutputStream.write(buf, 0, compByte);
		}
		byteArrayOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 解凍する
	 * 
	 * @param dataByte
	 * @return
	 * @throws IOException
	 * @throws DataFormatException
	 */
	public byte[] inflate(byte[] dataByte) throws IOException,
			DataFormatException {

		Inflater inf = new Inflater();
		inf.setInput(dataByte);

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		while (!inf.finished()) {
			int resultByte = inf.inflate(buf);
			byteArrayOutputStream.write(buf, 0, resultByte);
		}
		byteArrayOutputStream.close();

		return byteArrayOutputStream.toByteArray();
	}


}
