package model3;

import model3.sub.SubInfo;
import model3.Error;

public class Userinfo {

	public String id;
	public String email;
	public Boolean verified_email;
	public String name;
	public String given_name;
	public String family_name;
	public Error error;
	private SubInfo subInfo;
	
	public SubInfo getSubInfo() {
		return subInfo;
	}
	public void setSubInfo(SubInfo subInfo) {
		this.subInfo = subInfo;
	}

	@Override
	public String toString() {
		return "Userinfo [id=" + id + ", email=" + email + ", verified_email="
				+ verified_email + ", name=" + name + ", given_name="
				+ given_name + ", family_name=" + family_name + ", error=" + error
				+ ", subInfo=" + subInfo + "]";
	}

}
