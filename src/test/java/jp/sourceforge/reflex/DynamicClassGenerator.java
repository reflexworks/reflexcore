package jp.sourceforge.reflex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.NotFoundException;

public class DynamicClassGenerator {

	private static String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?([\\*#%]?)$";

	private MessagePack msgpack = new MessagePack();
	private TemplateRegistry registry; 
	private ReflectionTemplateBuilder builder; 
	private ClassPool pool;
    private Loader loader;
    private String packagename;


	public ClassPool getPool() {
		return pool;
	}
	
	public DynamicClassGenerator(String packagename) throws NotFoundException {
//		ClassPool pool0 = ClassPool.getDefault();
		this.pool = new ClassPool();
		this.pool.appendSystemPath();
		
		// msgpack準備(Javassistで動的に作成したクラスはReflectionTemplateBuilderを使わないとエラーになる)
		registry = new TemplateRegistry(null);
		builder = new ReflectionTemplateBuilder(registry);
		loader = new Loader(this.pool);
		this.packagename = packagename;
	}
	
	public HashSet<String> generateClass(String entitysrc[])
			throws NotFoundException, CannotCompileException, ParseException {
		return generateClass(getMetalist(entitysrc));
	}
	
	public HashSet<String> generateClass(List<Meta> metalist)
			throws NotFoundException, CannotCompileException {

		pool.importPackage(packagename);
		pool.importPackage("java.util.Date");
		 
		HashSet<String> classnames = getClassnames(metalist);
		
		for (String classname : classnames) {
			CtClass cc;
			try {
				cc = pool.get(classname);
			} catch (NotFoundException ne1) {
				System.out.println("make:"+classname);
				cc = pool.makeClass(classname);
				if (classname.indexOf("Entry")>=0) {
					CtClass cs = pool.get("jp.reflexworks.atom.entry.EntryBase");
					cc.setSuperclass(cs); // superclassの定義
				}

			}

			for (int i = 0; i < count(metalist, classname); i++) {

				Meta meta = getMetaByClassname(metalist, classname, i);
				String type = "public " + meta.type + " ";
				String field = meta.self + ";";
				try {
					cc.getDeclaredField(type+field);
				} catch (NotFoundException ne2) {
					// // フィールドの定義
					CtField f2 = CtField.make(type+field, cc); // フィールドの定義
					cc.addField(f2);
					CtMethod m = CtNewMethod.make(type + "get" + meta.getSelf()
							+ "() {" + "  return " + meta.self + "; }",
							cc);
					cc.addMethod(m);
					m = CtNewMethod.make("public void set" + meta.getSelf()
							+ "("+ meta.type + " "+meta.self +") { this." +meta.self +"=" + meta.self +";}",
							cc);
					cc.addMethod(m);
				}
			}
		}
		return classnames;
	}
	
	public List<Meta> getMetalist(String entitysrc[]) throws ParseException {
		List<Meta> metalist = new ArrayList<Meta>();
		
		Pattern patternf = Pattern.compile(field_pattern);
		
		Meta meta = new Meta();
		Matcher matcherf;
		String classname = getRootEntry(); 
		Stack<String> stack = new Stack<String>();
		stack.push(classname);
		int level = 0;

		for (String line:entitysrc) {
		matcherf = patternf.matcher(line);
		
		if (matcherf.find()) {
			if (meta.level!=matcherf.group(1).length()) {
				level = matcherf.group(1).length();
				if (meta.level<level) {
					classname = packagename+"."+meta.getSelf();
					stack.push(classname);
					meta.type = classname;	// 子要素を持っている場合にタイプを自分にする
				}else {
					for (int i=0;i<meta.level-level;i++) {
						stack.pop();
						classname = stack.peek();
					}
				}
			}
			if (meta.self!=null) {
				metalist.add(meta);
				System.out.println(" self="+meta.self+" parent="+meta.parent+" level="+meta.level+" type="+meta.type);
			}
			meta = new Meta();
			meta.level = level;
			meta.parent = classname;
			meta.isEncrypted = false;
			meta.isIndex = false;

			meta.self = matcherf.group(2);
			if (matcherf.group(5).equals("*")) {
				meta.type = "List<"+meta.getSelf()+">";
			}else { 
				if (matcherf.group(5).equals("#")) {
					meta.isIndex = true;
				}else 
				if (matcherf.group(5).equals("%")) {
					meta.isEncrypted = true;
				}
				if (matcherf.group(4)!=null){
				String typestr = matcherf.group(4).toLowerCase();
					if (typestr.equals("date")) {
						meta.type = "Date";
					}else if (typestr.equals("int")) {
						meta.type = "Integer";
					}else if (typestr.equals("long")) {
						meta.type = "Long";
					}else if (typestr.equals("float")) {
						meta.type = "Float";
					}else if (typestr.equals("double")) {
						meta.type = "Double";
					}else if (typestr.equals("boolean")) {
						meta.type = "Boolean";
					}else {
						meta.type = "String";	// その他
					}
				}else {
					meta.type = "String";	// 省略時
				}
			}
		}else {
			throw new ParseException("Unexpected Format:"+line,0);
		}
		}
		metalist.add(meta);
		System.out.println("self="+meta.self+" classname="+meta.parent+" level="+meta.level+" type="+meta.type);

		return metalist;

	}

	private String getRootEntry() {
		return packagename+".Entry";
	}
	

	private HashSet<String> getClassnames(List<Meta> metalist) {
		
		HashSet<String> classnames = new LinkedHashSet<String>();
		int size = metalist.size();

		int levelmax =0;
		for(Meta meta:metalist) {
			if (levelmax<meta.level) {
				levelmax = meta.level;
			}
		}
		
		for(int l=levelmax;l>=0;l--) {
		for(int i=size-1;i>=0;i--) {
			if (metalist.get(i).level==l) {
				classnames.add(metalist.get(i).parent);
			}
		}
		
		}
		return classnames;
	}
	
	private Meta getMetaByClassname(List<Meta> metalist,String classname,int i) {

		for(Meta meta:metalist) {
			if (meta.parent.equals(classname)) {
				i--;
				if (i<0) {
					return meta;
				}
			}
		}
		return null;
	}

	private int count(List<Meta> metalist,String classname) {

		int i =0;
		for(Meta meta:metalist) {
			if (meta.parent.equals(classname)) {
				i++;
			}
		}
		return i;
	}
	
	public void registClass(Set<String> classnames) {

		// MessagePackにクラスを登録
		if (classnames != null) {
			Set<Class<?>> registSet = new HashSet<Class<?>>();
			for (String clsName : classnames) {
				try {
//						Class<?> cls = pool.get(clsName).toClass();
						Class<?> cls = loader.loadClass(clsName);
						if (cls.getName().indexOf("Base")<0) {
							System.out.println("clsName="+clsName);
							Template template = builder.buildTemplate(cls);
							registry.register(cls, template);
							msgpack.register(cls,template);
						}
					} catch (ClassNotFoundException e) {
					// TODO 自動生成された catch ブロック
						e.printStackTrace();
					}
			}
		}
	}

	public Class getClass(String clsName) throws ClassNotFoundException{
		return loader.loadClass(clsName);
	}

	public byte[] toMessagePack(Object entity) throws IOException {
		return msgpack.write(entity);
	}
	public void toMessagePack(Object entity, OutputStream out) throws IOException {
		msgpack.write(out, entity);
	}
	public Object fromMessagePack(byte[] msg) throws IOException, ClassNotFoundException {
		return msgpack.read(msg,loader.loadClass(getRootEntry()));
	}
	public Object fromMessagePack(InputStream msg) throws IOException, ClassNotFoundException {
		return msgpack.read(msg,loader.loadClass(getRootEntry()));
	}
	

}
