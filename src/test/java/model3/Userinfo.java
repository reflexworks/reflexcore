package model3;

import model3.sub.SubInfo;

import java.util.List;

import org.msgpack.annotation.Message;

import model3.Error;

@Message
public class Userinfo {

	public String _id;
	public String _email;
	public Boolean _verified_email;
	public String _name;
	public String _given_name;
	public String _family_name;
	public Error _error;
	public List<SubInfo> _subInfo;
	

	@Override
	public String toString() {
		return "Userinfo [id=" + _id + ", email=" + _email + ", verified_email="
				+ _verified_email + ", name=" + _name + ", given_name="
				+ _given_name + ", family_name=" + _family_name + ", error=" + _error
				+ ", subInfo=" + _subInfo + "]";
	}

}
