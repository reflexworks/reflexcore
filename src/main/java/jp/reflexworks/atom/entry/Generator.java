package jp.reflexworks.atom.entry;

import java.io.Serializable;

import org.msgpack.annotation.Index;

public class Generator implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	@Index(0)
	public String _$uri;
	@Index(1)
	public String _$version;
	@Index(2)
	public String _$$text;

	public String get$uri() {
		return _$uri;
	}

	public void set$uri(String _$uri) {
		this._$uri = _$uri;
	}

	public String get$version() {
		return _$version;
	}

	public void set$version(String _$version) {
		this._$version = _$version;
	}

	public String get$$text() {
		return _$$text;
	}

	public void set$$text(String _$$text) {
		this._$$text = _$$text;
	}

	@Override
	public String toString() {
		return "Generator [" + _$$text + "]";
	}

}
