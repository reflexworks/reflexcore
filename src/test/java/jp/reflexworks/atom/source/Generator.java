/**
*
*  (C) Copyright Virtual Technology 2005 All Rights Reserved
*  Licensed Materials - Property of Virtual Technology
*
* This publication is provided "AS IS" WITHOUT WARRANTY OF ANY KIND,
* EITHER EXPRESSED OR IMPLIED, INCLUDING, BUT NOT LIMITED TO,
* THE IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE,
* OR NON-INFRINGEMENT.
*
*  @author S.Takezaki 2005/08/25
*
*
*/

package jp.reflexworks.atom.source;

import java.io.Serializable;

public class Generator implements Serializable, Cloneable {

	private static final long serialVersionUID = 1L;

	public String _$xml$lang;
	public String _$xml$base;
	public String _$uri;
	public String _$version;
	public String _$$text;

	public String get_$xml$lang() {
		return _$xml$lang;
	}

	public void set_$xml$lang(String _$xml$lang) {
		this._$xml$lang = _$xml$lang;
	}

	public String get_$xml$base() {
		return _$xml$base;
	}

	public void set_$xml$base(String _$xml$base) {
		this._$xml$base = _$xml$base;
	}

	public String get_$uri() {
		return _$uri;
	}

	public void set_$uri(String _$uri) {
		this._$uri = _$uri;
	}

	public String get_$version() {
		return _$version;
	}

	public void set_$version(String _$version) {
		this._$version = _$version;
	}

	public String get_$$text() {
		return _$$text;
	}

	public void set_$$text(String _$$text) {
		this._$$text = _$$text;
	}
	
	/*
	@Override
	public Generator clone() {
		Generator clone = new Generator();
		clone._$xml$lang = this._$xml$lang;
		clone._$xml$base = this._$xml$base;
		clone._$uri = this._$uri;
		clone._$version = this._$version;
		clone._$$text = this._$$text;
		return clone;
	}
	*/

	@Override
	public String toString() {
		return "Generator [" + _$$text + "]";
	}

}
