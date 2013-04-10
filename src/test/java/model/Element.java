package model;

public class Element {

	// public final int _$$col = 3; // 配列の要素数
	// public final int _$$col = 1;
	public final int _$$col = 0;

	public String _$$text;
	// public String val;
	
	public Element() {
	}
		
	public Element(String text) {
		_$$text = text;
	}

	@Override
	public String toString() {
		return _$$text;
	}

}
