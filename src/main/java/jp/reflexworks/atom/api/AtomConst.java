package jp.reflexworks.atom.api;

import java.util.Map;

public interface AtomConst {
	
	/** エンコード */
	public static final String ENCODING = "UTF-8";

	/** ATOM : namespace */
	public static final String ATOM_NAMESPACE = "http://www.w3.org/2005/Atom";
	/** ATOM : Feed package */
	public static final String ATOM_PACKAGE_FEED = "jp.reflexworks.atom.feed";
	/** ATOM : Entry package */
	public static final String ATOM_PACKAGE_ENTRY = "jp.reflexworks.atom.entry";
	
	public static final Map<String, String> ATOM_PACKAGE = 
			AtomConstSupporter.createModelPackage();
	
	/** MessaaePack Entry class */
	public static final boolean MSGPACK_ENTRY = false;
	/** MessaaePack Feed class */
	public static final boolean MSGPACK_FEED = true;
	/** MessagePack template default name */
	public static final String TEMPLATE_DEFAULT = "_";
	/** MessagePack template(rights) Field ACL start */
	public static final String TEMPLATE_FIELD_ACL_START = "=";
	/** MessagePack template(rights) Index start */
	public static final String TEMPLATE_INDEX_START = ":";
	/** MessagePack template(rights) Encryption start */
	public static final String TEMPLATE_ENCRYPTION_START = "#";

	/** Field ACL self */
	public static final String FIELD_ACL_MYSELF = "@";
	/** Field ACL all */
	public static final String FIELD_ACL_ALL = "/*";
	/** Field ACL Read */
	public static final String FIELD_ACL_READ = "R";
	/** Field ACL Write */
	public static final String FIELD_ACL_WRITE = "W";
	/** Field ACL Read Write */
	public static final String FIELD_ACL_RW = FIELD_ACL_READ + FIELD_ACL_WRITE;
	/** Field ACL give */
	public static final String FIELD_ACL_REQUIRED = "+";
	/** Field ACL Delimiter */
	public static final String FIELD_ACL_DELIMITER = ",";
	/** デフォルトテンプレート */
	public static final String[] TEMPLATE_DEFAULT_ARRAY = new String[]{TEMPLATE_DEFAULT};

	/** URN : 接頭辞 */
	public static final String URN_PREFIX = "urn:vte.cx:";
	/** URN : created */
	public static final String URN_PREFIX_CREATED = URN_PREFIX + "created:";
	/** URN : updated */
	public static final String URN_PREFIX_UPDATED = URN_PREFIX + "updated:";
	/** URN : deleted */
	public static final String URN_PREFIX_DELETED = URN_PREFIX + "deleted:";
	/** URN : username */
	public static final String URN_PREFIX_SERVICE = URN_PREFIX + "username:";
	/** URN : acl */
	public static final String URN_PREFIX_ACL = URN_PREFIX + "acl:";
	/** URN : auth */
	public static final String URN_PREFIX_AUTH = URN_PREFIX + "auth:";
	/** URN : usersecret */
	public static final String URN_PREFIX_USERSECRET = URN_PREFIX + "usersecret:";
	/** URN : platformtoken */
	public static final String URN_PREFIX_PLATFORMTOKEN = URN_PREFIX + "platformtoken:";
	
	/** 登録権限 */
	public static final String ACL_TYPE_CREATE = "C";
	/** 参照権限 */
	public static final String ACL_TYPE_RETRIEVE = "R";
	/** 更新権限 */
	public static final String ACL_TYPE_UPDATE = "U";
	/** 削除権限 */
	public static final String ACL_TYPE_DELETE = "D";
	/** CRUD権限 */
	public static final String ACL_TYPE_CRUD = ACL_TYPE_CREATE + ACL_TYPE_RETRIEVE + 
			ACL_TYPE_UPDATE + ACL_TYPE_DELETE;
	/** サービスからのみアクセス可能な権限 */
	public static final String ACL_TYPE_EXTERNAL = "E";
	/** 配下のエントリーより有効である権限 */
	public static final String ACL_TYPE_LOW = "/";
	/** 指定されたエントリーのみ有効である権限 */
	public static final String ACL_TYPE_OWN = ".";
	/** 指定されたエントリーとその配下のエントリーが有効である権限 */
	public static final String ACL_TYPE_OWN_AND_LOW = ACL_TYPE_OWN + ACL_TYPE_LOW;
	/** 任意の文字列 */
	public static final String ACL_USER_ANY = "*";
	/** ログインユーザ */
	public static final String ACL_USER_LOGGEDIN = "+";
	/** selfまたはエイリアスのユーザトップエントリーのuidがログイン情報のuidと等しい */
	public static final String ACL_USER_SELFALIAS = "-";
	/** ACL Delimiter */
	public static final String ACL_DELIMITER = ",";

	/** サービスエントリーの先頭文字の値 */
	public static final String SVC_PREFIX_VAL = "@";
	/** サービスエントリーの先頭 */
	public static final String SVC_PREFIX = "/" + SVC_PREFIX_VAL;

	/** ログインユーザのUIDを表す記号 */
	public static final String LOGINUSER_VAL = "~";
	
	/** ACLグループのワイルドカード */
	public static final String GROUP_WILDCARD = "*";

	/** テンプレートのMeta情報のType : String */
	public static final String META_TYPE_STRING = "String";
	/** テンプレートのMeta情報のType : Boolean */
	public static final String META_TYPE_BOOLEAN = "Boolean";
	/** テンプレートのMeta情報のType : Integer */
	public static final String META_TYPE_INTEGER = "Integer";
	/** テンプレートのMeta情報のType : Long */
	public static final String META_TYPE_LONG = "Long";
	/** テンプレートのMeta情報のType : Float */
	public static final String META_TYPE_FLOAT = "Float";
	/** テンプレートのMeta情報のType : Double */
	public static final String META_TYPE_DOUBLE = "Double";
	/** テンプレートのMeta情報のType : Date */
	public static final String META_TYPE_DATE = "Date";

	/** テンプレートのMeta情報のBigQueryType : String */
	public static final String META_BIGQUERYTYPE_STRING = "STRING";
	/** テンプレートのMeta情報のBigQueryType : Boolean */
	public static final String META_BIGQUERYTYPE_BOOLEAN = "BOOLEAN";
	/** テンプレートのMeta情報のBigQueryType : Integer */
	public static final String META_BIGQUERYTYPE_INTEGER = "INTEGER";
	/** テンプレートのMeta情報のBigQueryType : Float */
	public static final String META_BIGQUERYTYPE_FLOAT = "FLOAT";
	/** テンプレートのMeta情報のBigQueryType : Date */
	public static final String META_BIGQUERYTYPE_DATE = "DATETIME";

	/** 改行コード */
	public static final String NEWLINE = System.lineSeparator();

}
