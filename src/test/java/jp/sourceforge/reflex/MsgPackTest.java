package jp.sourceforge.reflex;


import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.msgpack.MessagePack;
import org.msgpack.template.Template;
import org.msgpack.template.TemplateRegistry;
import org.msgpack.template.builder.ReflectionTemplateBuilder;

import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.Loader;
import javassist.Modifier;
import javassist.bytecode.ConstPool;
import javassist.bytecode.FieldInfo;
import javassist.bytecode.SignatureAttribute;
import javassist.bytecode.SignatureAttribute.ClassSignature;
import javassist.bytecode.SignatureAttribute.ObjectType;
import jp.sourceforge.reflex.core.Element;


public class MsgPackTest {
	public static void main(String[] args) throws Exception {
//		MessagePack msgpack = new MessagePack();
//		List<String> lines = Arrays.asList("hello", "world");
		// TODO setGenericSignature CtClass
		// http://www.csg.ci.i.u-tokyo.ac.jp/~chiba/javassist/html/javassist/CtClass.html#setGenericSignature(java.lang.String)
		
		ClassPool pool = new ClassPool();
		pool.appendSystemPath();

		Loader loader = new Loader(pool);

		List<Element> lines2 = new ArrayList<Element>();
		Element a1 = new Element();
		a1._$$text = "ポップス1";
		lines2.add(a1);
		a1 = new Element();
		a1._$$text = "ポップス2";
		lines2.add(a1);
		a1 = new Element();
		a1._$$text = "ポップス3";
		lines2.add(a1);

		String signature = "Ljava/util/List<Ljp/sourceforge/reflex/Element;>;";
		
			CtClass arrayClass = pool.makeClass("testm3.Favorite");
	        CtClass objClass0 = pool.get("java.util.List");
	        
	        
		  CtField field = new CtField(objClass0, "element", arrayClass); 

		  field.setModifiers(Modifier.PUBLIC);
          SignatureAttribute.ObjectType cs = SignatureAttribute.toFieldSignature(signature);
          field.setGenericSignature(cs.encode());    // <T:Ljava/lang/Object;>Ljava/lang/Object;
		  arrayClass.addField(field);

	        CtClass objClass1 = pool.get("java.lang.String");
		  CtField field2 = new CtField(objClass1, "prop1", arrayClass); 
		  arrayClass.addField(field2);

		  
//          Class cc0 = arrayClass.toClass();
//		 loader.delegateLoadingOf("testm3.Favorite");
		 arrayClass.toClass();
		 
         Class cc0 = loader.loadClass("testm3.Favorite");
         
          Object array = cc0.newInstance();
          Field f = cc0.getDeclaredField("element");
          
	 	  f.set(array, lines2);
	 	  System.out.println("test:"+f.get(array));

	 	 Field[] flds = cc0.getDeclaredFields();
			for (Field fld:flds) {
				System.out.println("flds:"+fld);
			}
/////////////////////////////////
			
			MessagePack msgpack = new MessagePack();

			TemplateRegistry registry = new TemplateRegistry(null);
			ReflectionTemplateBuilder builder = new ReflectionTemplateBuilder(registry); 

			Class cls = loader.loadClass(ELEMENTCLASS);

			Template template = builder.buildTemplate(cls);
//			msgpack.register(Element.class,template);
			registry.register(cls,template);
//			msgpack.register(List.class, new ListTemplate(template));

			Template templatearray = builder.buildTemplate(cc0);
			msgpack.register(cc0,templatearray);

//			byte[] bytes = msgpack.write(lines2);		
			byte[] bytes = msgpack.write(array);		
			
			Object array2 = msgpack.read(bytes, cc0);
 	 	    System.out.println("test2:"+f.get(array2));

		
	}
	private static final String ELEMENTCLASS = "jp.sourceforge.reflex.Element";


}
