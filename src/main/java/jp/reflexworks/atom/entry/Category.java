package jp.reflexworks.atom.entry;

import java.io.Serializable;
import java.util.List;

import org.msgpack.annotation.Index;

import jp.reflexworks.atom.mapper.ConditionContext;
import jp.reflexworks.atom.mapper.CipherContext;
import jp.reflexworks.atom.mapper.MaskpropContext;
import jp.reflexworks.atom.mapper.SizeContext;

/**
 * カテゴリ.
 */
public class Category implements Serializable, Cloneable, SoftSchema {

	private static final long serialVersionUID = 1L;

	@Index(0)
	public String _$term;
	@Index(1)
	public String _$scheme;
	@Index(2)
	public String _$label;

	public String get$term() {
		return _$term;
	}

	public void set$term(String term) {
		this._$term = term;
	}

	public String get$scheme() {
		return _$scheme;
	}

	public void set$scheme(String scheme) {
		this._$scheme = scheme;
	}

	public String get$label() {
		return _$label;
	}

	public void set$label(String label) {
		this._$label = label;
	}

	@Override
	public String toString() {
		return "Category [_$term=" + _$term + ", _$scheme=" + _$scheme
				+ ", _$label=" + _$label + "]";
	}

	public Object getValue(String fldname) {
		if (fldname.equals("category.$term")) return _$term;
		if (fldname.equals("category.$scheme")) return _$scheme;
		if (fldname.equals("category.$label")) return _$label;
		return null;
	}

	public void encrypt(CipherContext context) {}
	public void decrypt(CipherContext context) {}
	
	public void isMatch(ConditionContext context) {
		if (_$term != null) {
			context.fldname = "category.$term";
			context.type = "String";
			context.obj = _$term;
			ConditionContext.checkCondition(context);
		}
		if (_$scheme != null) {
			context.fldname = "category.$scheme";
			context.type = "String";
			context.obj = _$scheme;
			ConditionContext.checkCondition(context);
		}
		if (_$label != null) {
			context.fldname = "category.$label";
			context.type = "String";
			context.obj = _$label;
			ConditionContext.checkCondition(context);
		}
	}

	public boolean validate(String uid, List<String> groups, String myself) 
			throws java.text.ParseException {return true;}

	public void maskprop(MaskpropContext context) {}

	@Override
	public void getsize(SizeContext context) {
		if (_$term!=null) {context.size += _$term.length();context.count++;context.keysize+=5; }
		if (_$scheme!=null) {context.size += _$scheme.length();context.count++;context.keysize+=7; }
		if (_$label!=null) {context.size += _$label.length();context.count++;context.keysize+=5; }
	}

}
