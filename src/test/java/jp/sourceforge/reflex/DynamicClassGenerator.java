package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;

public class DynamicClassGenerator {

	private static String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?(\\*?)$";

	public void generateClass(String packagename, List<Entity_meta> metalist)
			throws NotFoundException, CannotCompileException {

		DynamicClassGeneratorTest dc = new DynamicClassGeneratorTest();
		ClassPool pool = ClassPool.getDefault();
		HashSet<String> classnames = getClassnames(metalist);

		for (String classname : classnames) {
			String packageclassname = packagename + "." + classname;

			CtClass cc;
			try {
				cc = pool.get(packageclassname);
			} catch (NotFoundException ne1) {
				cc = pool.makeClass(packageclassname);
				CtClass cs = pool.get("model.Array");
				cc.setSuperclass(cs); // superclassの定義
			}

			for (int i = 0; i < count(metalist, classname); i++) {

				Entity_meta meta = getMetaByClassname(metalist, classname, i);
				try {
					cc.getDeclaredField(meta.self);
					System.out.println(meta.self + " is already defined");
				} catch (NotFoundException ne2) {
					String type = "public " + meta.type + " ";
					String field = meta.self + ";";
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
				}
			}
		}

	}

	public List<Entity_meta> getMetalist(String entitysrc[]) {
		List<Entity_meta> metalist = new ArrayList<Entity_meta>();
		
		Pattern pattern = Pattern.compile(field_pattern);
		
		Entity_meta meta = new Entity_meta();
		Matcher matcher;
		String classname = "Entry"; // root
		int level = 0;

		Stack<String> stack = new Stack<String>();
		stack.push(classname);		// root

		for (String line:entitysrc) {
		matcher = pattern.matcher(line);
		
		if (matcher.find()) {
			if (meta.level!=matcher.group(1).length()) {
				level = matcher.group(1).length();
				if (meta.level<level) {
					classname = meta.getSelf();
					stack.push(classname);
					meta.type = classname;
				}else {
					for (int i=0;i<meta.level-level+1;i++) {
						classname = stack.pop();
					}
				}
			}
			if (meta.self!=null) {
				metalist.add(meta);
				System.out.println("self="+meta.self+" classname="+meta.classname+" level="+meta.level+" type="+meta.type);
			}
			meta = new Entity_meta();
			meta.level = level;
			meta.classname = classname;

			meta.self = matcher.group(2);
			if (matcher.group(5).equals("*")) {
				meta.type = "List<"+meta.getSelf()+">";
			}else if (matcher.group(4)!=null){				
				meta.type = matcher.group(4);	// ()の中
			}else {
				meta.type = "String";	// 省略時
			}
		}
		}
		metalist.add(meta);
		System.out.println("self="+meta.self+" classname="+meta.classname+" level="+meta.level+" type="+meta.type);

		return metalist;

	}
	
	private HashSet<String> getClassnames(List<Entity_meta> metalist) {
		
		HashSet<String> classnames = new HashSet<String>();
		
		for(Entity_meta meta:metalist) {
			classnames.add(meta.classname);
		}
		return classnames;
	}
	
	private Entity_meta getMetaByClassname(List<Entity_meta> metalist,String classname,int i) {

		for(Entity_meta meta:metalist) {
			if (meta.classname.equals(classname)) {
				i--;
				if (i<0) {
					return meta;
				}
			}
		}
		return null;
	}

	private int count(List<Entity_meta> metalist,String classname) {

		int i =0;
		for(Entity_meta meta:metalist) {
			if (meta.classname.equals(classname)) {
				i++;
			}
		}
		return i;
	}

}
