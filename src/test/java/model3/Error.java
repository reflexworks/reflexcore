package model3;

import java.util.List;

public class Error {
	
	public List<Errors> _errors;
	public Integer _code;
	public String _message;

	@Override
	public String toString() {
		return "Error [errors=" + _errors + ", code=" + _code + ", message="
				+ _message + "]";
	}

}
