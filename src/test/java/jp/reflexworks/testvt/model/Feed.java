package jp.reflexworks.testvt.model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

import jp.reflexworks.atom.entry.FeedBase;

public class Feed extends FeedBase implements Serializable {

	/*
	private static final long serialVersionUID = 1L;

	public Object getValue(String fldname) {if (entry!=null) for (int i=0;i<entry.size();i++) { Object value =((jp.reflexworks.atom.entry.SoftSchema)entry.get(i)).getValue(fldname);if (value!=null) return value;}return null;};
	public void encrypt(Object cipher) {if (entry!=null) for (int i=0;i<entry.size();i++) { ((Entry)entry.get(i)).encrypt(cipher);}};
	public void decrypt(Object cipher) {if (entry!=null) for (int i=0;i<entry.size();i++) { ((Entry)entry.get(i)).decrypt(cipher);}};
	public boolean validate(String uid, java.util.List groups) throws java.text.ParseException {String myself = null;if (entry!=null&&100<entry.size()) throw new java.text.ParseException("Maximum number of 'entry' exceeded.",0);if (entry!=null) for (int i=0;i<entry.size();i++) { ((jp.reflexworks.atom.entry.EntryBase)entry.get(i)).validate(uid,groups);}return true;};
	public void maskprop(String uid, java.util.List groups) {String myself = null;if (entry!=null) for (int i=0;i<entry.size();i++) { ((jp.reflexworks.atom.entry.EntryBase)entry.get(i)).maskprop(uid,groups);}};
	*/

	public String _$xmlns = "http://www.w3.org/2005/Atom";
	private static final long serialVersionUID = 1L;

	// ---- VT名前空間定義 ----
	public String _$xmlns$vt = "http://reflexworks.jp/vt/1.0";

	// ---- VT名前空間　getter, setter　ここから ----
	public String get_$xmlns$vt() {
		return _$xmlns$vt;
	}

	public void set_$xmlns$vt(String _$xmlns$vt) {
		this._$xmlns$vt = _$xmlns$vt;
	}
	
	@Override
	public boolean validate(String ucode, List groups) throws ParseException {
		// TODO 
		return false;
	}
	@Override
	public void maskprop(String ucode, List groups) {
		// TODO 
	}

}
