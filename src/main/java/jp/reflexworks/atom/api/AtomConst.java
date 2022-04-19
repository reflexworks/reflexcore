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

	/** サービスステータス : 新規作成中 */
	public static final String SERVICE_STATUS_CREATING = "creating";
	/** サービスステータス : 非公開 */
	public static final String SERVICE_STATUS_INACTIVE = "inactive";
	/** サービスステータス : 公開中 */
	public static final String SERVICE_STATUS_RUNNING = "running";
	/** サービスステータス : 強制停止 */
	public static final String SERVICE_STATUS_BLOCKED = "blocked";
	/** サービスステータス : リセット中 */
	public static final String SERVICE_STATUS_RESETTING = "resetting";
	/** サービスステータス : 削除中 */
	public static final String SERVICE_STATUS_DELETING = "deleting";
	/** サービスステータス : 登録失敗 */
	public static final String SERVICE_STATUS_FAILURE = "failure";

	/** URI : settings (value) */
	public static final String URI_SETTINGS_VAL = "_settings";
	/** URI : settings */
	public static final String URI_SETTINGS = "/" + URI_SETTINGS_VAL;
	/** URI : settings - admin */
	public static final String URI_SETTINGS_PROPERTIES = URI_SETTINGS + "/properties";
	/** URI : settings - adduser */
	public static final String URI_SETTINGS_ADDUSER = URI_SETTINGS + "/adduser";
	/** URI : settings - adduserinfo */
	public static final String URI_SETTINGS_ADDUSERINFO = URI_SETTINGS + "/adduserinfo";
	/** URI : settings - passreset */
	public static final String URI_SETTINGS_PASSRESET = URI_SETTINGS + "/passreset";
	/** URI : settings - adduserByAdmin */
	public static final String URI_SETTINGS_ADDUSER_BYADMIN = URI_SETTINGS + "/adduserByAdmin";
	/** URI : settings - changeaccount */
	public static final String URI_SETTINGS_CHANGEACCOUNT = URI_SETTINGS + "/changeaccount";
	/** URI : settings - logalert */
	public static final String URI_SETTINGS_LOGALERT = URI_SETTINGS + "/logalert";
	/** URI : user init (value) */
	public static final String URI_USERINIT_VAL = "userinit.xml";
	/** URI : settings - user init */
	public static final String URI_SETTINGS_USERINIT = URI_SETTINGS + "/" + URI_USERINIT_VAL;
	/** URI : feed template (value) */
	public static final String URI_TEMPLATE_VAL = "template";
	/** URI : settings - feed template */
	public static final String URI_SETTINGS_TEMPLATE = URI_SETTINGS + "/" + URI_TEMPLATE_VAL;
	/** URI : group */
	public static final String URI_GROUP = "/_group";
	/** URI : system group prefix */
	public static final String URI_SERVICE_GROUP_PREFIX = "/$";
	/** URI : $admin */
	public static final String URI_$ADMIN = URI_SERVICE_GROUP_PREFIX + "admin";
	/** URI : $content */
	public static final String URI_$CONTENT = URI_SERVICE_GROUP_PREFIX + "content";
	/** URI : $useradmin */
	public static final String URI_$USERADMIN = URI_SERVICE_GROUP_PREFIX + "useradmin";
	/** URI : group - admin */
	public static final String URI_GROUP_ADMIN = URI_GROUP + URI_$ADMIN;
	/** URI : group - content */
	public static final String URI_GROUP_CONTENT = URI_GROUP + URI_$CONTENT;
	/** URI : group - useradmin */
	public static final String URI_GROUP_USERADMIN = URI_GROUP + URI_$USERADMIN;
	/** URI : auth (value) */
	public static final String URI_LAYER_AUTH_VAL = "auth";
	/** URI : auth (layer) */
	public static final String URI_LAYER_AUTH = "/" + URI_LAYER_AUTH_VAL;
	/** URI : platform (layer) */
	public static final String URI_LAYER_PLATFORM = "/platform";
	/** URI : group (layer) */
	public static final String URI_LAYER_GROUP = "/group";
	/** URI : html */
	public static final String URI_HTML = "/_html";
	/** URI : user */
	public static final String URI_USER_VAL = "_user";
	/** URI : user */
	public static final String URI_USER = "/" + URI_USER_VAL;

	/** type : webhook */
	public static final String TYPE_WEBHOOK = "webhook";
	/** type : websocket */
	public static final String TYPE_WEBSOCKET = "websocket";
	/** type : mobile push */
	public static final String TYPE_MOBILEPUSH = "push";
	/** type : メール通知 */
	public static final String TYPE_EMAIL = "email";
	/** type : node */
	public static final String TYPE_NODE = "node";
	
	/** ユーザステータス : 登録なし */
	public static final String USERSTATUS_NOTHING = "Nothing";
	/** ユーザステータス : 仮登録 */
	public static final String USERSTATUS_INTERIM = "Interim";
	/** ユーザステータス : 本登録 */
	public static final String USERSTATUS_ACTIVATED = "Activated";
	/** ユーザステータス : 無効 */
	public static final String USERSTATUS_REVOKED = "Revoked";
	/** ユーザステータス : 退会 */
	public static final String USERSTATUS_CANCELLED = "Cancelled";

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
