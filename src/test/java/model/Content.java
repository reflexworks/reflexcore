package model;

import java.util.List;

import jp.sourceforge.reflex.util.FieldMapper;

public class Content {

	public String content;
	public List content2;
	
	
	public Content clone() {
		Content cont = new Content();
		FieldMapper fieldMapper = new FieldMapper(false);
		return (Content)fieldMapper.clone(this);
		//cont.content = new String(this.content);
	}

	public String toString() {
		return "Content[" + content + "]";
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
