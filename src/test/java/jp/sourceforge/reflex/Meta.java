package jp.sourceforge.reflex;

public class Meta {

	public int level;
	public String type;
	public String classname;
	public String self;
	public boolean isEncrypted;
	public boolean isIndex;

	public String getSelf() {
		return self.substring(0,1).toUpperCase()+self.substring(1);
	}

}
