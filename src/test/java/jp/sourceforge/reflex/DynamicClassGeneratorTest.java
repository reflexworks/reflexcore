package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.CannotCompileException;
import javassist.NotFoundException;

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
			
	public static void main(String args[]) throws NotFoundException, CannotCompileException {

		DynamicClassGenerator dg = new DynamicClassGenerator();
		
		List<Entity_meta> metalist = dg.getMetalist(entitysrc);
		dg.generateClass("testsvc", metalist);
		
	}


}
