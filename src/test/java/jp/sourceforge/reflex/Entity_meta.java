package jp.sourceforge.reflex;

public class Entity_meta {

	public int level;
	public String type;
	public String classname;
	public String self;

	public String getSelf() {
		return self.substring(0,1).toUpperCase()+self.substring(1);
	}

}
