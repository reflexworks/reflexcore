package jp.reflexworks.atom.mapper;

import java.util.List;

public class MaskpropContext {

	
	public String parent;
	public String uid;
	public List groups;
	public String myself;
	
	public MaskpropContext(String uid, java.util.List groups, String myself) {
		this.uid = uid;
		this.groups = groups;
		this.myself = myself;
	}
	
}
