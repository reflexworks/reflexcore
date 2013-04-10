package jp.sourceforge.reflex.core;

import java.util.Map;

import jp.sourceforge.reflex.EntityMapper;

/*
 * このRXStreamクラスは、下位バージョンの互換性のために残しています。
 * ResourceMapperを使用してください。
 */
public class RXStream extends ResourceMapper implements EntityMapper {

	public RXStream(Map jo_packages) {
		super(jo_packages);
	}

	public RXStream(Map jo_packages, boolean isCamel) {
		super(jo_packages, isCamel);
	}

	public RXStream(Map jo_packages, boolean isCamel, boolean useSingleQuate) {
		super(jo_packages, isCamel, useSingleQuate);
	}

}
