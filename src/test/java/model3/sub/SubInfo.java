package model3.sub;

import java.util.List;

import org.msgpack.annotation.Message;

@Message
public class SubInfo {
	
	public Favorite _favorite;
	public List<Hobby> _hobby;
	
	@Override
	public String toString() {
		return "favorite food=" + _favorite._food + ",favorite music=" + _favorite._music +",hobby="+ _hobby.get(0)._$$text;
	}


}
