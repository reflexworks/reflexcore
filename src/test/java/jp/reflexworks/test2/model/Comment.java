package jp.reflexworks.test2.model;

public class Comment {
	
	public String _$$text;
	public String nickname;
	public String secret;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String get$$text() {
		return _$$text;
	}

	public void set$$text(String _$$text) {
		this._$$text = _$$text;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public String toString() {
		return "Comment [nickname=" + nickname + ", $$text=" + _$$text + " secret=" + secret + "]";
	}
	
	public Object getValue(String fldname) {if (fldname.equals("comment.$$text")) return _$$text;if (fldname.equals("comment.nickname")) return nickname;return null;}
	public void encrypt(String id, Object cipher, String secretkey) {if (secret!=null)secret=(String) jp.reflexworks.atom.mapper.CipherUtil.doEncrypt(""+secret, "testsecret123"+id, cipher);}
	public void decrypt(String id, Object cipher, String secretkey) {if (secret!=null)secret=(String) jp.reflexworks.atom.mapper.CipherUtil.doDecrypt(""+secret, "testsecret123"+id, cipher);}	public void isMatch(jp.reflexworks.atom.mapper.ConditionContext context) {if (_$$text!=null) {context.fldname="comment.$$text";context.type="String";context.obj=_$$text;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}if (nickname!=null) {context.fldname="comment.nickname";context.type="String";context.obj=nickname;jp.reflexworks.atom.mapper.ConditionContext.checkCondition(context);}};
	public boolean validate(String uid, java.util.List groups, String myself) throws java.text.ParseException {return true;};
	public void maskprop(String uid, java.util.List groups, String myself) {};
	
}
