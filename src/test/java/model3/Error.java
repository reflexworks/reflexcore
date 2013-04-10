package model3;

import java.util.List;

public class Error {
	
	public List<Errors> errors;
	public Integer code;
	public String message;

	@Override
	public String toString() {
		return "Error [errors=" + errors + ", code=" + code + ", message="
				+ message + "]";
	}

}
