package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DynamicClassGeneratorTest {
	
	public static String entitysrc[] = {
		"id",
		"email",
		"verified_email(Boolean)",
		"name",
		"given_name",
		"family_name",
		"error",
		" errors*",
		"  domain",
		"  reason",
		"  message",
		"  locationType",
		"  location",
		"subInfo",
		" favorite",
		"  food",
		"  music",
		" hobby*",
		"  _$$text"
	};
	
	private static String field_pattern = "^( *)([0-9a-zA-Z_$]+)(\\(([0-9a-zA-Z_$]+)\\))?(\\*?)$";
		
	public static void main(String args[]) {

		List<Entity_meta> entity_list = new ArrayList<Entity_meta>();
		
		Pattern pattern = Pattern.compile(field_pattern);
		Stack<String> stack = new Stack<String>();
		stack.push("");	// root
		
		Entity_meta meta = new Entity_meta();
		Matcher matcher;
		String parent = null;
		int level = 0;
		
		for (String line:entitysrc) {
		matcher = pattern.matcher(line);
		
		if (matcher.find()) {
			if (meta.level!=matcher.group(1).length()) {
				level = matcher.group(1).length();
				if (meta.level<level) {
					parent = meta.self;
					stack.push(parent);
				}else {
					for (int i=0;i<meta.level-level+1;i++) {
						parent = stack.pop();
					}
				}
			}
			meta = new Entity_meta();
			meta.level = level;
			meta.parent = parent;

			meta.self = matcher.group(2);
			if (matcher.group(5).equals("*")) {
				meta.type = "List<"+meta.self.substring(0,1).toUpperCase()+meta.self.substring(1).toLowerCase()+">";
			}else if (matcher.group(4)!=null){				
				meta.type = matcher.group(4);	// ()の中
			}else {
				meta.type = "String";	// 省略時
			}
			entity_list.add(meta);
		}

		System.out.println("self="+meta.self+" parent="+meta.parent+" level="+meta.level+" type="+meta.type);

		}
	}

}
