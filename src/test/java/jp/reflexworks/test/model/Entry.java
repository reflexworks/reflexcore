package jp.reflexworks.test.model;

import java.text.ParseException;
import java.util.List;

import jp.reflexworks.atom.api.Condition;
import jp.reflexworks.atom.entry.EntryBase;

public class Entry extends EntryBase {

	@Override
	public Object getValue(String fieldname) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public void encrypt(Object cipher) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void decrypt(Object cipher) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean isMatch(Condition[] conditions) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public boolean validate(String ucode, List<String> groups)
			throws ParseException {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

	@Override
	public void maskprop(String ucode, List<String> groups) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	/*
	// 独自名前空間があり、デフォルトの名前空間項目が親クラスに定義されている場合、
	// デフォルトの名前空間がXMLの外に出力されてしまう不具合があるため、デフォルト名前空間項目を子クラスにも定義する。
	public String _$xmlns;

	// ---- 名前空間定義 ----
	public String _$xmlns$vt = "http://reflexworks.jp/test/1.0";

	@Override
	public String get$xmlns() {
		return _$xmlns;
	}
	@Override
	public void set$xmlns(String _$xmlns) {
		this._$xmlns = _$xmlns;
	}
	public String get_$xmlns$vt() {
		return _$xmlns$vt;
	}
	public void set_$xmlns$vt(String _$xmlns$vt) {
		this._$xmlns$vt = _$xmlns$vt;
	}
	@Override
	public Object getValue(String fieldname) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	@Override
	public void encrypt(Object cipher) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public void decrypt(Object cipher) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	@Override
	public boolean isMatch(ConditionBase[] conditions) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public boolean validate(String ucode, List<String> groups)
			throws ParseException {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}
	@Override
	public void maskprop(String ucode, List<String> groups) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	/*
	public String _$xmlns$test;

    public Integer test$flg;
    public String test$userId;
    
    @Encrypt("na")
    public String test$customerName;
    @Encrypt("addrpassword")
    public String test$customerAddr;

	public String get_$xmlns$test() {
		return _$xmlns$test;
	}
	public void set_$xmlns$test(String _$xmlns$test) {
		this._$xmlns$test = _$xmlns$test;
	}
	public Integer getTest$flg() {
		return test$flg;
	}
	public void setTest$flg(Integer test$flg) {
		this.test$flg = test$flg;
	}
	public String getTest$userId() {
		return test$userId;
	}
	public void setTest$userId(String test$userId) {
		this.test$userId = test$userId;
	}
	public String getTest$customerName() {
		return test$customerName;
	}
	public void setTest$customerName(String test$customerName) {
		this.test$customerName = test$customerName;
	}
	public String getTest$customerAddr() {
		return test$customerAddr;
	}
	public void setTest$customerAddr(String test$customerAddr) {
		this.test$customerAddr = test$customerAddr;
	}
	@Override
	public String toString() {
		return "Entry [_$xmlns=" + _$xmlns + ", _$xmlns$test=" + _$xmlns$test
				+ ", test$flg=" + test$flg + ", test$userId=" + test$userId
				+ ", test$customerName=" + test$customerName
				+ ", test$customerAddr=" + test$customerAddr + ", " + super.toString() + "]";
	}
	*/

	@Override
	public int getsize() {
		return 0;
	}

}
