package jp.reflexworks.atom.mapper;

public class CipherContext {

	
	public String parent;
	public String id;
	public Object cipher;
	public String secretkey;
	
	public CipherContext(Object cipher,String id,String secretkey) {
		this.cipher = cipher;
		this.id = id;
		this.secretkey = secretkey;
	}
	
}
