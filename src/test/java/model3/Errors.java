package model3;

public class Errors {

	public String _domain;
	public String _reason;
	public String _message;
	public String _locationType;
	public String _location;

	@Override
	public String toString() {
		return "Errors [domain=" + _domain + ", reason=" + _reason + ", message="
				+ _message + ", locationType=" + _locationType + ", location="
				+ _location + "]";
	}
	
}
