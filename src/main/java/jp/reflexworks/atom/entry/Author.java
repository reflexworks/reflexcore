package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.List;

import org.msgpack.annotation.Index;

import jp.reflexworks.atom.mapper.ConditionContext;
import jp.reflexworks.atom.mapper.CipherContext;
import jp.reflexworks.atom.mapper.MaskpropContext;
import jp.reflexworks.atom.mapper.SizeContext;

/**
 * 更新者.
 * <p>
 * Reflex内で登録・更新時、uriに更新者情報を設定します。<br>
 * urn:vte.cx:{created|updated|deleted}:{username} の形式です。
 * </p>
 */
public class Author implements Serializable, Cloneable, SoftSchema {

	private static final long serialVersionUID = 1L;

	@Index(0)
	public String name;
	@Index(1)
	public String uri;
	@Index(2)
	public String email;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Author [uri=" + uri + ", email=" + email + ", name=" + name + "]";
	}
	
	public Object getValue(String fldname) {
		if (fldname.equals("author.name")) return name;
		if (fldname.equals("author.uri")) return uri;
		if (fldname.equals("author.email")) return email;
		return null;
	}

	public void encrypt(CipherContext context) {}
	public void decrypt(CipherContext context) {}
	
	public void isMatch(ConditionContext context) {
		if (name != null) {
			context.fldname = "author.name";
			context.type = "String";
			context.obj = name;
			ConditionContext.checkCondition(context);
		}
		if (uri != null) {
			context.fldname = "author.uri";
			context.type = "String";
			context.obj = uri;
			ConditionContext.checkCondition(context);
		}
		if (email != null) {
			context.fldname = "author.email";
			context.type = "String";
			context.obj = email;
			ConditionContext.checkCondition(context);
		}
	}

	public boolean validate(String uid, List<String> groups, String myself) 
	throws java.text.ParseException {return true;}

	public void maskprop(MaskpropContext context) {}

	@Override
	public void getsize(SizeContext context) {
		if (name!=null) {context.size += name.length();context.count++;context.keysize+=4; }
		if (uri!=null) {context.size += uri.length();context.count++;context.keysize+=3; }
		if (email!=null) {context.size += email.length();context.count++;context.keysize+=5; }
	}

}
