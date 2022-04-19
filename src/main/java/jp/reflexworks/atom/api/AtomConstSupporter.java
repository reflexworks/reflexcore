package jp.reflexworks.atom.api;

import java.util.Map;
import java.util.HashMap;

public class AtomConstSupporter {
	
	static Map<String, String> createModelPackage() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(AtomConst.ATOM_PACKAGE_ENTRY, "");
		return map;
	}

}
