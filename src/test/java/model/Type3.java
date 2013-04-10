package model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Type3 {

	private int revision;
	public String _$xml$lang;
	public String type_str__ing;
	public String type_str__ing_$attr1;
	public String type_str__ing_$attr2;
	public String type_str__ing_$xml$lang;
	public int type_int;
	public long type_long;
	public float type_float;
	public double type_double;
	public Date type_date;
	public Integer type_Integer;
	public Long type_Long;
	public Float type_Float;
	public Double type_Double;
	public String xml$atom;
	public String _$$text;
	private int deleted;
	public List list;
	public Map map;
	public Content content;
	public int[] array_int;
	public final String FINAL_STR = "変更できません";

	public int getRevision() {
		return this.revision;
	}
	public void setRevision(int revision) {
		this.revision = revision;
	}
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	@Override
	public String toString() {
		return "Type3 [revision=" + revision + ", _$xml$lang=" + _$xml$lang
				+ ", type_str__ing=" + type_str__ing
				+ ", type_str__ing_$attr1=" + type_str__ing_$attr1
				+ ", type_str__ing_$attr2=" + type_str__ing_$attr2
				+ ", type_str__ing_$xml$lang=" + type_str__ing_$xml$lang
				+ ", type_int=" + type_int + ", type_long=" + type_long
				+ ", type_float=" + type_float + ", type_double=" + type_double
				+ ", type_date=" + type_date + ", type_Integer=" + type_Integer
				+ ", type_Long=" + type_Long + ", type_Float=" + type_Float
				+ ", type_Double=" + type_Double + ", xml$atom=" + xml$atom
				+ ", _$$text=" + _$$text + ", deleted=" + deleted + ", list="
				+ list + ", map=" + map + ", content=" + content
				+ ", array_int=" + Arrays.toString(array_int) + ", FINAL_STR="
				+ FINAL_STR + "]";
	}
	public String get_$xml$lang() {
		return _$xml$lang;
	}
	public void set_$xml$lang(String _$xml$lang) {
		this._$xml$lang = _$xml$lang;
	}
	public String getType_str__ing() {
		return type_str__ing;
	}
	public void setType_str__ing(String type_str__ing) {
		this.type_str__ing = type_str__ing;
	}
	public String getType_str__ing_$attr1() {
		return type_str__ing_$attr1;
	}
	public void setType_str__ing_$attr1(String type_str__ing_$attr1) {
		this.type_str__ing_$attr1 = type_str__ing_$attr1;
	}
	public String getType_str__ing_$attr2() {
		return type_str__ing_$attr2;
	}
	public void setType_str__ing_$attr2(String type_str__ing_$attr2) {
		this.type_str__ing_$attr2 = type_str__ing_$attr2;
	}
	public String getType_str__ing_$xml$lang() {
		return type_str__ing_$xml$lang;
	}
	public void setType_str__ing_$xml$lang(String type_str__ing_$xml$lang) {
		this.type_str__ing_$xml$lang = type_str__ing_$xml$lang;
	}
	public int getType_int() {
		return type_int;
	}
	public void setType_int(int type_int) {
		this.type_int = type_int;
	}
	public long getType_long() {
		return type_long;
	}
	public void setType_long(long type_long) {
		this.type_long = type_long;
	}
	public float getType_float() {
		return type_float;
	}
	public void setType_float(float type_float) {
		this.type_float = type_float;
	}
	public double getType_double() {
		return type_double;
	}
	public void setType_double(double type_double) {
		this.type_double = type_double;
	}
	public Date getType_date() {
		return type_date;
	}
	public void setType_date(Date type_date) {
		this.type_date = type_date;
	}
	public Integer getType_Integer() {
		return type_Integer;
	}
	public void setType_Integer(Integer type_Integer) {
		this.type_Integer = type_Integer;
	}
	public Long getType_Long() {
		return type_Long;
	}
	public void setType_Long(Long type_Long) {
		this.type_Long = type_Long;
	}
	public Float getType_Float() {
		return type_Float;
	}
	public void setType_Float(Float type_Float) {
		this.type_Float = type_Float;
	}
	public Double getType_Double() {
		return type_Double;
	}
	public void setType_Double(Double type_Double) {
		this.type_Double = type_Double;
	}
	public String getXml$atom() {
		return xml$atom;
	}
	public void setXml$atom(String xml$atom) {
		this.xml$atom = xml$atom;
	}
	public String get_$$text() {
		return _$$text;
	}
	public void set_$$text(String _$$text) {
		this._$$text = _$$text;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public Map getMap() {
		return map;
	}
	public void setMap(Map map) {
		this.map = map;
	}
	public Content getContent() {
		return content;
	}
	public void setContent(Content content) {
		this.content = content;
	}
	public int[] getArray_int() {
		return array_int;
	}
	public void setArray_int(int[] array_int) {
		this.array_int = array_int;
	}
	public String getFINAL_STR() {
		return FINAL_STR;
	}

}
