package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.List;

import org.msgpack.annotation.Index;

import jp.reflexworks.atom.mapper.CipherContext;
import jp.reflexworks.atom.mapper.ConditionContext;
import jp.reflexworks.atom.mapper.MaskpropContext;
import jp.reflexworks.atom.mapper.SizeContext;

/**
 * コンテンツ.
 * <p>
 * HTMLなどのテキストコンテンツや、画像などのリンク先を設定します。<br>
 * テキストコンテンツを設定する場合、_$$text項目に設定してください。(XMLにシリアライズした際、contentタグの値となります。)<br>
 * 画像などのリンク先を設定する場合、_$srcにURLを設定してください。<br>
 * </p>
 */
public class Content implements Serializable, Cloneable, SoftSchema {

	private static final long serialVersionUID = 1L;

	/** 項目ACLチェックパターン */
	//private static Pattern PATTERN = Pattern.compile("^/_group/\\$content$|^/@[^/]+/_group/\\$content$");

	@Index(0)
	public String _$src;
	@Index(1)
	public String _$type;
	@Index(2)
	public String _$$text;

	public String get$src() {
		return _$src;
	}

	public void set$src(String _$src) {
		this._$src = _$src;
	}

	public String get$type() {
		return _$type;
	}

	public void set$type(String _$type) {
		this._$type = _$type;
	}

	public String get$$text() {
		return _$$text;
	}

	public void set$$text(String _$$text) {
		this._$$text = _$$text;
	}

	@Override
	public String toString() {
		return "Content [" + _$$text + "]";
	}

	public boolean validate(String uid, List<String> groups, String myself)
	throws java.text.ParseException {
		/*
		if (this._$$text != null || this._$type != null || this._$src != null) {
			if (uid!=null && groups != null && groups.size() >= 0) {
				boolean ex = false;
				for (int i = 0; i < groups.size(); i++) {
					// $contentグループでなければ更新できない -> /@{サービス名}/_group/$content
					// -> サービス名階層は廃止。コンテンツ更新グループは"/_group/$content"
					Matcher m = PATTERN.matcher(groups.get(i));
					if (m.find()) ex=true;
				}
				if (_$type!=null&&(_$type.equals("image/jpeg") ||
						_$type.equals("image/png") ||
						_$type.equals("image/gif") ||
						_$type.equals("application/octet-stream"))) ex=true;
				if (!ex) throw new java.text.ParseException(
						"Property 'content' is not writeable.", 0);
			}
		}
		*/
		return true;
	}

	public Object getValue(String fldname) {
		if (fldname.equals("content.$$text")) return _$$text;
		if (fldname.equals("content.$type")) return _$type;
		if (fldname.equals("content.$src")) return _$src;
		return null;
	}

	public void encrypt(CipherContext context) {}
	public void decrypt(CipherContext context) {}

	public void isMatch(ConditionContext context) {
		if (_$$text != null) {
			context.fldname = "content.$$text";
			context.type = "String";
			context.obj = _$$text;
			ConditionContext.checkCondition(context);
		}
		if (_$type != null) {
			context.fldname = "content.$type";
			context.type = "String";
			context.obj = _$type;
			ConditionContext.checkCondition(context);
		}
		if (_$src != null) {
			context.fldname = "content.$src";
			context.type = "String";
			context.obj = _$src;
			ConditionContext.checkCondition(context);
		}
	}

	public void maskprop(MaskpropContext context) {}

	@Override
	public void getsize(SizeContext context) {
		if (_$src!=null) {context.size += _$src.length();context.count++;context.keysize+=4; }
		if (_$type!=null) {context.size += _$type.length();context.count++;context.keysize+=5; }
		if (_$$text!=null) {context.size += _$$text.length();context.count++;context.keysize+=6; }
	}

}
