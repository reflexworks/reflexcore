package model3;

public class Errors {

	public String domain;
	public String reason;
	public String message;
	public String locationType;
	public String location;

	@Override
	public String toString() {
		return "Errors [domain=" + domain + ", reason=" + reason + ", message="
				+ message + ", locationType=" + locationType + ", location="
				+ location + "]";
	}
	
}
