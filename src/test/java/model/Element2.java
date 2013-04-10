package model;

import java.io.Serializable;

public class Element2 implements Serializable {

	// public final int _$$col = 3; // 配列の要素数
	// public final int _$$col = 1;
	public final int _$$col = 0;

	public String _$$text;
	// public String val;
	
	public Element2() {
	}
		
	public Element2(String text) {
		_$$text = text;
	}

	public String get_$$text() {
		return _$$text;
	}

	public void set_$$text(String _$$text) {
		this._$$text = _$$text;
	}

	@Override
	public String toString() {
		return _$$text;
	}

}
