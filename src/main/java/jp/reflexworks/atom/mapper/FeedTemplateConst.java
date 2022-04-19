package jp.reflexworks.atom.mapper;

import jp.sourceforge.reflex.util.BinaryUtil;

public interface FeedTemplateConst {
	
	/** MessagePack Entry byte配列 16進数表記 */
	public static final String MSGPACK_BYTES_HEX_ENTRY = 
			"DC0010C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0C0";
	/** MessagePack Entry byte配列 */
	public static final byte[] MSGPACK_BYTES_ENTRY = 
			BinaryUtil.hex2bin(MSGPACK_BYTES_HEX_ENTRY);
	/** MessagePack Feed byte配列 16進数表記 */
	public static final String MSGPACK_BYTES_HEX_FEED = 
			"9FC0C0C0C0C0C0C0C0C0C0C0C0C0C0C0";
	/** MessagePack Feed byte配列 */
	public static final byte[] MSGPACK_BYTES_FEED = 
			BinaryUtil.hex2bin(MSGPACK_BYTES_HEX_FEED);
	/** MessagePack byte配列 最初の1バイト (Feedの場合) */
	public static final byte MSGPACK_PREFIX = MSGPACK_BYTES_FEED[0];
	
	/** Meta type : Integer */
	static final String META_TYPE_INTEGER = "Integer";
	/** Meta type : Long */
	static final String META_TYPE_LONG = "Long";
	/** Meta type : Float */
	static final String META_TYPE_FLOAT = "Float";
	/** Meta type : Double */
	static final String META_TYPE_DOUBLE = "Double";
	/** Meta type : Date */
	static final String META_TYPE_DATE = "Date";
	/** Meta type : Boolean */
	static final String META_TYPE_BOOLEAN = "Boolean";
	/** Meta type : String */
	static final String META_TYPE_STRING = "String";

}
