package jp.sourceforge.reflex;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.EmptyStackException;
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
import javassist.NotFoundException;

public class DynamicClassGenerator {

	private static String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?([\\*#%]?)$";

	private MessagePack msgpack = new MessagePack();
	private TemplateRegistry registry; 
	private ReflectionTemplateBuilder builder; 
	private ClassPool pool;

	public DynamicClassGenerator(ClassPool pool) throws NotFoundException {
		this.pool = pool;
		// msgpack準備(Javassistで動的に作成したクラスはReflectionTemplateBuilderを使わないとエラーになる)
		registry = new TemplateRegistry(null);
		builder = new ReflectionTemplateBuilder(registry);

	}
	
	public HashSet<String> generateClass(String packagename, String entitysrc[])
			throws NotFoundException, CannotCompileException, ParseException {
		return generateClass(packagename, getMetalist(entitysrc,packagename));
	}
	
	public HashSet<String> generateClass(String packagename, List<Meta> metalist)
			throws NotFoundException, CannotCompileException {

		//ClassPool pool = ClassPool.getDefault();
		pool.importPackage(packagename);
		pool.importPackage("java.util.Date");
		 
		HashSet<String> packageclassnames = getClassnames(packagename,metalist);
		
		for (String packageclassname : packageclassnames) {
			CtClass cc;
			try {
				cc = pool.get(packageclassname);
			} catch (NotFoundException ne1) {
				System.out.println("make:"+packageclassname);
				cc = pool.makeClass(packageclassname);
				if (packageclassname.indexOf("Entry")>=0) {
					CtClass cs = pool.get("jp.reflexworks.atom.entry.EntryBase");
					cc.setSuperclass(cs); // superclassの定義
				}

			}

			for (int i = 0; i < count(metalist, packageclassname); i++) {

				Meta meta = getMetaByClassname(metalist, packageclassname, i);
				String type = "public " + meta.type + " ";
				String field = meta.self + ";";
				try {
					cc.getDeclaredField(type+field);
					System.out.println(type+field + " is already defined");
				} catch (NotFoundException ne2) {
					// CtField f2 = CtField.make("public String _$$text;", cc);
					// // フィールドの定義
					System.out.println(type+field);
					CtField f2 = CtField.make(type+field, cc); // フィールドの定義
					cc.addField(f2);
					System.out.println(type + "get" + meta.getSelf()
							+ "() {" + "  return " + meta.self + "; }");
					CtMethod m = CtNewMethod.make(type + "get" + meta.getSelf()
							+ "() {" + "  return " + meta.self + "; }",
							cc);
					cc.addMethod(m);
					System.out.println(
							"public void set" + meta.getSelf()
							+ "("+ meta.type + " "+meta.self +") { this." +meta.self +"=" + meta.self +";}"
							);
					m = CtNewMethod.make("public void set" + meta.getSelf()
							+ "("+ meta.type + " "+meta.self +") { this." +meta.self +"=" + meta.self +";}",
							cc);
					cc.addMethod(m);
				}
			}
		}
		return packageclassnames;
	}
	

	public List<Meta> getMetalist(String entitysrc[],String packagename) throws ParseException {
		List<Meta> metalist = new ArrayList<Meta>();
		
		Pattern patternf = Pattern.compile(field_pattern);
		
		Meta meta = new Meta();
		Matcher matcherf;
		String packageclassname = packagename+".Entry"; // root
		Stack<String> stack = new Stack<String>();
		stack.push(packageclassname);
		int level = 0;

		for (String line:entitysrc) {
		matcherf = patternf.matcher(line);
		
		if (matcherf.find()) {
			if (meta.self!=null) {
				metalist.add(meta);
				System.out.println(" self="+meta.self+" parent="+meta.parent+" level="+meta.level+" type="+meta.type);
			}
			if (meta.level!=matcherf.group(1).length()) {
				level = matcherf.group(1).length();
				if (meta.level<level) {
					packageclassname = packagename+"."+meta.getSelf();
					stack.push(packageclassname);
					meta.type = packageclassname;
				}else {
					stack.pop();
					packageclassname = stack.peek();
				}
			}
			meta = new Meta();
			meta.level = level;
			meta.parent = packageclassname;
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
	
	private HashSet<String> getClassnames(String packagename,List<Meta> metalist) {
		
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
//				classnames.add(packagename+"."+metalist.get(i).classname);
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

	private int count(List<Meta> metalist,String packageclassname) {

		int i =0;
		for(Meta meta:metalist) {
			if (meta.parent.equals(packageclassname)) {
				i++;
			}
		}
		return i;
	}
	
	public void registClass(Set<String> classNames) {

		// MessagePackにクラスを登録
		if (classNames != null) {
			Set<Class<?>> registSet = new HashSet<Class<?>>();
			for (String clsName : classNames) {
				try {
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
						ce.printStackTrace();
					} catch (NotFoundException ne) {
						ne.printStackTrace();
					}
//				}
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


}
