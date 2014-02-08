package model3.sub;

import java.util.List;

import org.msgpack.annotation.Message;

@Message
public class SubInfo {
	
	public Favorite _favorite;
	public List<Hobby> _hobby;

}
