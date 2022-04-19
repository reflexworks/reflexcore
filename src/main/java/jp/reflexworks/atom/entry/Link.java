package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.List;

import org.msgpack.annotation.Index;

import jp.reflexworks.atom.api.AtomConst;
import jp.reflexworks.atom.mapper.CipherContext;
import jp.reflexworks.atom.mapper.ConditionContext;
import jp.reflexworks.atom.mapper.MaskpropContext;
import jp.reflexworks.atom.mapper.SizeContext;

/**
 * Link.
 * <p>
 * linkタグは、rel属性により様々な役割を持ちます。<br>
 * <br>
 * <b>キー</b><br><br>
 * このエントリーのキーを、rel="self"の、href属性に設定します。<br>
 * <br>
 * <b>エイリアス</b><br><br>
 * rel="alternate"の、href属性に設定できます。複数指定可能です。<br>
 * エイリアスで検索したり、ACLを適用させることができます。<br>
 * エイリアスで検索した場合、キーであるrel="self"のhref属性にエイリアスの値が設定されます。ただしidタグは本体のキーが使用されます。<br>
 * <br>
 * <b>コンテンツ</b><br><br>
 * rel="related"の場合、href属性に外部コンテンツのURLを指定します。<br>
 * GETでURLにリダイレクトすることができます。<br>
 * <br>
 * <b>WebHook</b><br><br>
 * rel="via"、type="webhook"の場合、href属性に指定されたURLにリクエストします。<br>
 * リクエストのタイミングは、エントリーの登録・更新後です。<br>
 * title属性にGET、POST、PUT、DELETEが指定できます。この場合、配下のエントリーが検索・登録・更新・削除された場合リクエストが実行されます。自身の登録・更新時には実行されません。<br>
 * hrefのURLに?async={数字}パラメータが設定されている場合、{数字}秒後にリクエストを実行します。<br>
 * </p>
 */
public class Link implements Serializable, SoftSchema {

	private static final long serialVersionUID = 1L;

	public static final String REL_SELF = "self";
	public static final String REL_ALTERNATE = "alternate";
	public static final String REL_RELATED = "related";
	public static final String REL_VIA = "via";
	public static final String REL_ENCLOSURE = "enclosure";
	public static final String NUMBERING = "#";
	public static final String REL_NEXT = "next";

	@Index(0)
	public String _$href;
	@Index(1)
	public String _$rel;
	@Index(2)
	public String _$type;
	@Index(3)
	public String _$title;
	@Index(4)
	public String _$length;

	public String get$href() {
		return _$href;
	}

	public void set$href(String _$href) {
		this._$href = _$href;
	}

	public String get$rel() {
		return _$rel;
	}

	public void set$rel(String _$rel) {
		this._$rel = _$rel;
	}

	public String get$type() {
		return _$type;
	}

	public void set$type(String _$type) {
		this._$type = _$type;
	}

	public String get$title() {
		return _$title;
	}

	public void set$title(String _$title) {
		this._$title = _$title;
	}

	public String get$length() {
		return _$length;
	}

	public void set$length(String _$length) {
		this._$length = _$length;
	}

	@Override
	public String toString() {
		return "Link [_$rel=" + _$rel + ", _$href=" + _$href + ", _$title="
				+ _$title + ", _$type=" + _$type + "]";
	}

	public Object getValue(String fldname) {
		if (fldname.equals("link.$href")) return _$href;
		if (fldname.equals("link.$type")) return _$type;
		if (fldname.equals("link.$rel")) return _$rel;
		if (fldname.equals("link.$title")) return _$title;
		if (fldname.equals("link.$length")) return _$length;
		return null;
	}

	public void encrypt(CipherContext context) {}
	public void decrypt(CipherContext context) {}

	public void isMatch(ConditionContext context) {
		if (_$href != null) {
			context.fldname = "link.$href";
			context.type = "String";
			context.obj = _$href;
			ConditionContext.checkCondition(context);
		}
		if (_$rel != null){
			context.fldname = "link.$rel";
			context.type = "String";
			context.obj = _$rel;
			ConditionContext.checkCondition(context);
		}
		if (_$type != null) {
			context.fldname = "link.$type";
			context.type = "String";
			context.obj = _$type;
			ConditionContext.checkCondition(context);
		}
		if (_$title != null) {
			context.fldname = "link.$title";
			context.type = "String";
			context.obj = _$title;
			ConditionContext.checkCondition(context);
		}
		if (_$length != null) {
			context.fldname = "link.$length";
			context.type = "String";
			context.obj = _$length;
			ConditionContext.checkCondition(context);
		}
	}

	public String getUid() {
		if (_$href != null) {
			String token[] = _$href.split("/");
			if (token.length > 2) {
				return token[2];
			}
		}
		return null;
	}

	public boolean validate(String uid, List<String> groups, String myself)
	throws java.text.ParseException {
		/* link._$title のvalidateチェックはここでは行わない。必要に応じて呼び出し元で行う。
		if (_$title != null) {
			if (uid == null)
				throw new java.text.ParseException("Property 'link#title' is not writeable.", 0);
			if (myself != null) {
				if (_$rel.equals("self") && !uid.equals(myself))
					throw new java.text.ParseException("Property 'link#title' is not writeable.", 0);
				if (_$rel.equals("alternate") && !uid.equals(getUid()))
					throw new java.text.ParseException("Property 'link#title' is not writeable.", 0);
			}
		}
		*/
		return true;
	}

	public void maskprop(MaskpropContext context) {
	}

	public void addSvcname(String svcname) {
		// rel="self" または "alternate" の場合のみサービス名編集
		if ((Link.REL_SELF.equals(_$rel) || Link.REL_ALTERNATE.equals(_$rel)) &&
				_$href != null && !_$href.startsWith(AtomConst.SVC_PREFIX)) {
			StringBuilder buf = new StringBuilder();
			buf.append(AtomConst.SVC_PREFIX);
			buf.append(svcname);
			if (!EntryBase.isSlash(_$href)) {
				buf.append(_$href);
			}
			_$href = buf.toString();
		}
	}

	public void cutSvcname(String svcname) {
		String serviceTopUri = AtomConst.SVC_PREFIX + svcname;
		// rel="self" または "alternate" の場合のみサービス名編集
		if ((Link.REL_SELF.equals(_$rel) || Link.REL_ALTERNATE.equals(_$rel)) &&
				_$href != null && _$href.startsWith(serviceTopUri)) {
			_$href = _$href.substring(serviceTopUri.length());
			if (_$href.length() == 0) {
				_$href = "/";
			}
		}
	}

	@Override
	public void getsize(SizeContext context) {
		if (_$href!=null) {context.size += _$href.length();context.count++;context.keysize+=5; }
		if (_$rel!=null) {context.size += _$rel.length();context.count++;context.keysize+=4; }
		if (_$type!=null) {context.size += _$type.length();context.count++;context.keysize+=5; }
		if (_$title!=null) {context.size += _$title.length();context.count++;context.keysize+=6; }
		if (_$length!=null) {context.size += _$length.length();context.count++;context.keysize+=7; }
	}

}
