package jp.sourceforge.reflex.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.json.JSONException;
import org.msgpack.MessagePack;
import org.msgpack.template.ListTemplate;
import org.msgpack.template.StringTemplate;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;
import org.msgpack.type.Value;
import org.msgpack.util.json.JSONBufferUnpacker;

import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.NotFoundException;
import jp.sourceforge.reflex.core.ResourceMapper;

public class MessagePackMapper extends ResourceMapper {

	private Logger logger = Logger.getLogger(this.getClass().getName());
	// @・・必須項目 TODO デフォルト値を付けるか？
	// 
	private static final String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?((?:[\\*#]|%[0-9a-zA-Z]+)?)(@?)(?::(.+))?$";

	private static final String MANDATORY = "@";
	private static final String ENCRYPTED = "%";
	private static final String INDEX = "#";
	private static final String LIST = "*";
		
	// atom クラス（順番は重要、TODO これらは taggingserviceのConstantsに移すべきか?）
	// このクラス内でatomクラスを区別するのは難しい
	private static final String[] atom = { "jp.reflexworks.atom.source.Author",
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
			"jp.reflexworks.atom.entry.EntryBase",
			"jp.reflexworks.atom.feed.Author",
			"jp.reflexworks.atom.feed.Category",
			"jp.reflexworks.atom.feed.Generator",
			"jp.reflexworks.atom.feed.Contributor",
			"jp.reflexworks.atom.feed.Link",
			"jp.reflexworks.atom.entry.FeedBase" };

	private static final String ENTRYBASE = "jp.reflexworks.atom.entry.EntryBase";
	private static final String FEEDBASE = "jp.reflexworks.atom.entry.FeedBase";

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
	private TemplateRegistry registry;
	private ReflectionTemplateBuilder builder;
	private ClassPool pool;
	private Loader loader;
	protected String packagename;

	/*
	 * root entry
	 */
	private String getRootEntry() {
		return packagename + ".Entry";
	}

	/**
	 * コンストラクタ
	 * @param jo_packages
	 * @throws ParseException
	 */
	public MessagePackMapper(Object etitytempl) throws ParseException {
		this(etitytempl, false, false);
	}
	
	public MessagePackMapper(Object etitytempl, boolean isCamel) throws ParseException {
		this(etitytempl, isCamel, false);
	}
	public MessagePackMapper(Object etitytempl, ReflectionProvider reflectionProvider) throws ParseException {
		this(etitytempl, false, false, reflectionProvider);
	}
	public MessagePackMapper(Object etitytempl, boolean isCamel,
			boolean useSingleQuote) throws ParseException {
		this(etitytempl, isCamel, useSingleQuote, null);
	}
	
	public MessagePackMapper(Object entitytempl, boolean isCamel,
			boolean useSingleQuote, ReflectionProvider reflectionProvider) throws ParseException {
		super(getJo_packages(entitytempl), isCamel, useSingleQuote, reflectionProvider);

		this.pool = new ClassPool();
		this.pool.appendSystemPath();
		registry = new TemplateRegistry(null);
		builder = new ReflectionTemplateBuilder(registry); // msgpack準備(Javassistで動的に作成したクラスはReflectionTemplateBuilderを使わないとエラーになる)
		loader = new Loader(this.pool);

		registry.register(ArrayList.class, new ListTemplate(StringTemplate.getInstance()));

		if (entitytempl instanceof String[]) {
			this.packagename = ((String[]) entitytempl)[0];
			registClass(((String[]) entitytempl));
		}else if (entitytempl instanceof List) {
			String[] entitytemplstr = (String[]) ((List) entitytempl).toArray(new String[((List<String>) entitytempl).size()]);;
			this.packagename = entitytemplstr[0];
			registClass(entitytemplstr);
			
		}

	}
	
	/**
	 * ATOM Packageとユーザ Packageを取得する
	 * @param entitytempl
	 * @return
	 */
	private static Map<String, String> getJo_packages(Object entitytempl) {
		Map jo_packages = new LinkedHashMap<String,String>();
		jo_packages.putAll(ATOM_PACKAGE);
		if (entitytempl instanceof String[]) {
			jo_packages.put(((String[]) entitytempl)[0], "");
		}else if (entitytempl instanceof List) {
			jo_packages.put(((List) entitytempl).get(0), "");
		}
		return jo_packages;
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

		public String getSelf() {
			if (self==null) return null;
			return self.substring(0, 1).toUpperCase() + self.substring(1);
		}
		
		public boolean hasChild() {
			return type.indexOf(getSelf())>0;
		}
	}

	public Class getClass(String clsName) throws ClassNotFoundException {
		return loader.loadClass(clsName);
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

	public Object fromMessagePack(byte[] msg) throws IOException, ClassNotFoundException  {
		return msgpack.read(msg, loader.loadClass(getRootEntry()));
	}

	public Object toArray(byte[] msg) throws IOException,
			ClassNotFoundException {
		return msgpack.read(msg);
	}

	public Object fromMessagePack(InputStream msg) throws IOException,
			ClassNotFoundException {
		return msgpack.read(msg, loader.loadClass(getRootEntry()));
	}

	/**
	 * メタ情報から動的クラスを生成する
	 * 
	 * @param metalist
	 * @return 生成したクラス名のSet
	 * @throws NotFoundException
	 * @throws CannotCompileException
	 */
	private Set<String> generateClass(List<Meta> metalist)
			throws CannotCompileException {

		pool.importPackage(packagename);
		pool.importPackage("java.util.Date");

		Set<String> classnames = getClassnames(metalist);

		for (String classname : classnames) {
			CtClass cc;
			try {
				cc = pool.get(classname);

			} catch (NotFoundException ne1) {
				cc = pool.makeClass(classname);
				
				if (classname.indexOf("Entry") >= 0) {
					CtClass cs;
					try {
						cs = pool.get(ENTRYBASE);
						cc.setSuperclass(cs); // superclassの定義
					} catch (NotFoundException e) {
						throw new CannotCompileException(e);
					}
				} else if (classname.indexOf("Feed") >= 0) {
					CtClass cs;
					try {
						cs = pool.get(FEEDBASE);
						cc.setSuperclass(cs); // superclassの定義
					} catch (NotFoundException e) {
						throw new CannotCompileException(e);
					}
				}

			}

			StringBuffer validation = new StringBuffer();
			validation.append(isValidFuncS);
			
			for (int i = 0; i < matches(metalist, classname); i++) {

				Meta meta = getMetaOfLevel(metalist, classname, i);
				String type = "public " + meta.type + " ";
				String field = meta.self + ";";
				try {
					cc.getDeclaredField(type + field);
				} catch (NotFoundException ne2) {
					CtField f2 = CtField.make(type + field, cc); // フィールドの定義
					cc.addField(f2);
					
					CtMethod m = CtNewMethod.make(type + "get" + meta.getSelf()
							+ "() {" + "  return " + meta.self + "; }", cc);
					cc.addMethod(m);
					m = CtNewMethod.make("public void set" + meta.getSelf()
							+ "(" + meta.type + " " + meta.self + ") { this."
							+ meta.self + "=" + meta.self + ";}", cc);
					cc.addMethod(m);
					// 暗号キー
					if (meta.privatekey!=null) {
						CtMethod m2 = CtNewMethod.make("public String _"+meta.getSelf()+"() {return \"" + meta.privatekey+"\";}", cc);
						cc.addMethod(m2);
					}
				}
				
				// バリデーションチェック
				validation.append(getValidatorLogic(meta));
				// 子要素のValidation
				if (meta.hasChild()) {
					validation.append("if ("+meta.self+"!=null) "+ meta.self+".isValid();");
				}
			}
			
			// Validation Method追加
			validation.append(isValidFuncE);
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
	private final String isValidFuncS = "public boolean isValid() throws java.text.ParseException {";
	private final String isValidFuncE = "return true;}";
	
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

		return line;
	}
	
	
	/**
	 * Entity Templateからメタ情報を作成する
	 * 
	 * @param entitytmpl
	 * @return メタ情報
	 * @throws ParseException
	 */
	private List<Meta> getMetalist(String entitytmpl[]) throws ParseException {
		List<Meta> metalist = new ArrayList<Meta>();

		Pattern patternf = Pattern.compile(field_pattern);

		Meta meta = new Meta();
		Matcher matcherf;
		String classname = getRootEntry();
		Stack<String> stack = new Stack<String>();
		stack.push(classname);
		int level = 0;

		for (int l=1;l<entitytmpl.length;l++) {
			String line = entitytmpl[l];
			matcherf = patternf.matcher(line);

			if (matcherf.find()) {
				if (meta.level != matcherf.group(1).length()) {
					level = matcherf.group(1).length();
					if (meta.level < level) {
						//最初の行にインデントがあるとエラー、また、２段階下がるとエラー
						if (l==0||meta.level+1<level) {
							throw new ParseException("Wrong Indentation:" + line, 0);	
						}
						classname = packagename + "." + meta.getSelf();
						stack.push(classname);
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
//					System.out.println(" self="+meta.self+" parent="+meta.parent+" level="+meta.level+" type="+meta.type+" mandatory="+meta.isMandatory+" regex:"+meta.regex+" hasChild:"+meta.hasChild()+" array:"+meta.arraycnt);
				}
				meta = new Meta();
				meta.level = level;
				meta.parent = classname;
				meta.privatekey = null;
				meta.isIndex = false;
				meta.isMandatory = matcherf.group(6).equals(MANDATORY);
				meta.regex = matcherf.group(7);
//				System.out.println("桁:"+matcherf.group(5)+" "+matcherf.group(6)+" man:"+matcherf.group(7)+" regex:"+matcherf.group(8));
				meta.self = matcherf.group(2);
				if (matcherf.group(5).equals(LIST)) {
//					meta.type = "List<" + meta.getSelf() + ">";
				} else {
					if (matcherf.group(5).equals(INDEX)) {
						meta.isIndex = true;
					} else if (matcherf.group(5).startsWith(ENCRYPTED)) {
						meta.privatekey = matcherf.group(5);	// %付きで保存
					} 
					
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
						} else {
							meta.type = "String"; // その他
						}
					} else {
						if (meta.type==null) {
							meta.type = "String"; // 省略時
						}
					}
					
				}
				
			} else {
				throw new ParseException("Unexpected Format:" + line, 0);
			}
		}
		metalist.add(meta);
		// System.out.println("self="+meta.self+" classname="+meta.parent+" level="+meta.level+" type="+meta.type);

		return metalist;

	}

	/**
	 * 動的に生成するクラス名を下位から順にして返す
	 * @param metalist
	 * @return クラス名のSet
	 */
	private Set<String> getClassnames(List<Meta> metalist) {

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
	private Meta getMetaOfLevel(List<Meta> metalist, String classname, int level) {

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
	private int matches(List<Meta> metalist, String classname) {

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
	 * @param entitytempl
	 * @throws ParseException
	 */
	private void registClass(String[] entitytempl) throws ParseException {
		HashSet<String> classnames = new LinkedHashSet<String>();
		classnames.addAll(new ArrayList(Arrays.asList(atom)));

		try {
			classnames.addAll(generateClass(getMetalist(entitytempl)));
			registClass(classnames);
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
	private void registClass(Set<String> classnames)
			throws CannotCompileException {

		for (String clsName : classnames) {
			// 静的なクラスであるatomパッケージは親のクラスローダにロード(これがないとClassCastException)
			if (clsName.indexOf(".atom.") > 0) {
				loader.delegateLoadingOf(clsName);
			}
			if (clsName.indexOf("Base") < 0) {
				Class<?> cls;
				try {
					cls = loader.loadClass(clsName);
				} catch (ClassNotFoundException e) {
					throw new CannotCompileException(e);
				}
				Template template = builder.buildTemplate(cls);
				registry.register(cls, template);
				msgpack.register(cls, template);
			}
		}
	}

	/**
	 * MessagePackを使ってJSONをデシリアライズする
	 */
	public Object fromJSON(String json) throws JSONException{
        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap(json.getBytes());
        try {
        	return parseValue("",u.readValue());
        } catch(Exception e) {
        	throw new JSONException(e);
        }
	}

	public Object fromArray(String array) throws JSONException{
        JSONBufferUnpacker u = new JSONBufferUnpacker(msgpack).wrap(array.getBytes());
        try {
        	return msgpack.convert(u.readValue(),loader.loadClass(getRootEntry()));
        } catch(Exception e) {
        	throw new JSONException(e);
        }
	}
	
	/**
	 * MessagePackのValueオブジェクトからEntityクラスを作成する
	 * @param classname
	 * @param value
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 */
	private Object parseValue(String classname,Value value) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SecurityException, NoSuchFieldException {

		Object parent = null;
		Class cc = null;
		Field f = null;
		
        if (value.isMapValue()) {
        	boolean isCreated = false;
        	for(Entry<Value,Value> e:value.asMapValue().entrySet()) {
    			String fld = e.getKey().toString().replace("\"", "").replace("-", "__");  // フィールド名の変換。ハイフン
        		if (!classname.isEmpty()) {
        			cc = this.getClass(classname);
    				f = cc.getField(fld);
        		}
        		
        		if ((e.getValue()).isMapValue()) {
        			String childclsname = packagename+"."+fld.substring(0, 1).toUpperCase() + fld.substring(1);
        			Object child = parseValue(childclsname,e.getValue());
            		if (!classname.isEmpty()) {
            			if (!isCreated) parent = (Object) cc.newInstance();
    					f.set(parent, child);
    					isCreated = true;
//            			System.out.println("parent="+classname+" child="+childclsname);
            		}else {
            			return child;
            		}
        			
        		}else {
        			if (e.getValue().isArrayValue()) break;
        			else {
        				if (!isCreated) {
//        					System.out.println("new classname:"+classname);
        					parent = (Object) cc.newInstance();
        					isCreated = true;
        				}
//                		System.out.println("key="+classname+"."+fld+" value="+e.getValue());
                		if (e.getValue().isBooleanValue()) f.set(parent, e.getValue().asBooleanValue().getBoolean());
                		else if (e.getValue().isIntegerValue()) f.set(parent, e.getValue().asIntegerValue().getInt());
                		else if (e.getValue().isFloatValue()) f.set(parent, e.getValue().asFloatValue().getFloat());
                		else if (e.getValue().isRawValue()) f.set(parent, e.getValue().toString().replace("\"",""));
                			

        			}
        		}
        	}
        }
    	return parent;
		
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
