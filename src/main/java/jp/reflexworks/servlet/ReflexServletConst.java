package jp.reflexworks.servlet;

import java.util.Locale;

/**
 * ReflexServletで使用する定数
 */
public interface ReflexServletConst extends HttpStatus {

	/** エンコード*/
	public static final String ENCODING = "UTF-8";
	/** Content-Type : XML */
	public static final String CONTENT_TYPE_XML = "text/xml";
	/** Content-Type : XML, charset : UTF-8 */
	public static final String CONTENT_TYPE_REFLEX_XML = 
			CONTENT_TYPE_XML + ";charset=" + ENCODING;
	/** Content-Type : JSON */
	public static final String CONTENT_TYPE_JSON = "application/json";
	/** Content-Type : JSON, charset : UTF-8 */
	public static final String CONTENT_TYPE_REFLEX_JSON = 
			CONTENT_TYPE_JSON + ";charset=" + ENCODING;
	/** Content-Type : MessagePack */
	public static final String CONTENT_TYPE_MESSAGEPACK = "application/x-msgpack";
	/** Content-Type : multipart/form-data */
	public static final String CONTENT_TYPE_MULTIPART_FORMDATA = "multipart/form-data";
	/** Content-Type : Text */
	public static final String CONTENT_TYPE_TEXT = "text/";
	/** charset : UTF-8 */
	public static final String CHARSET = ";charset=" + ENCODING;
	/** Content-Type : HTML */
	public static final String CONTENT_TYPE_HTML = CONTENT_TYPE_TEXT + "html";
	/** Content-Type : HTML; charset */
	public static final String CONTENT_TYPE_HTML_CHARSET = CONTENT_TYPE_HTML + CHARSET;
	/** Content-Type : Plain Text */
	public static final String CONTENT_TYPE_PLAIN = CONTENT_TYPE_TEXT + "plain";
	/** Content-Type : Plain Text; charset */
	public static final String CONTENT_TYPE_PLAIN_CHARSET = CONTENT_TYPE_PLAIN + CHARSET;
	/** Content Type : image */
	public static final String CONTENT_TYPE_IMAGE = "image/";
	/** Content Type : png */
	public static final String CONTENT_TYPE_PNG = CONTENT_TYPE_IMAGE + "png";
	/** Content Type : jpeg */
	public static final String CONTENT_TYPE_JPEG = CONTENT_TYPE_IMAGE + "jpeg";
	/** Content Type : gif */
	public static final String CONTENT_TYPE_GIF = CONTENT_TYPE_IMAGE + "gif";
	/** Content Type : application */
	public static final String CONTENT_TYPE_APPLICATION = "application/";
	/** Content Type : pdf */
	public static final String CONTENT_TYPE_PDF = CONTENT_TYPE_APPLICATION + "pdf";
	/** Content Type : application/xml */
	public static final String CONTENT_TYPE_APPLICATION_XML = CONTENT_TYPE_APPLICATION + "xml";

	/** Header : Content Type */
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	/** Header : Content Type (lower case) */
	public static final String HEADER_CONTENT_TYPE_LOWERCASE = 
			HEADER_CONTENT_TYPE.toLowerCase(Locale.ENGLISH);
	/** Header : Content Length */
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	/** Header : Content Length (lower case) */
	public static final String HEADER_CONTENT_LENGTH_LOWERCASE = 
			HEADER_CONTENT_LENGTH.toLowerCase(Locale.ENGLISH);
	/** Header : Content Encoding */
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	/** Header : Content Encoding (lower case) */
	public static final String HEADER_CONTENT_ENCODING_LOWERCASE = 
			HEADER_CONTENT_ENCODING.toLowerCase(Locale.ENGLISH);
	/** Header : Accept Encoding */
	public static final String HEADER_ACCEPT_ENCODING = "Accept-Encoding";
	/** Header : Accept Encoding (lower case) */
	public static final String HEADER_ACCEPT_ENCODING_LOWERCASE = 
			HEADER_ACCEPT_ENCODING.toLowerCase(Locale.ENGLISH);
	/** Header : Content Language */
	public static final String HEADER_CONTENT_LANGUAGE = "Content-Language";
	/** Header value : deflate */
	public static final String HEADER_VALUE_DEFLATE = "deflate";
	/** Header value : gzip */
	public static final String HEADER_VALUE_GZIP = "gzip";
	/** Header value : gzip, deflate */
	public static final String HEADER_VALUE_GZIP_DEFLATE = HEADER_VALUE_GZIP + ", " + 
			HEADER_VALUE_DEFLATE;
	/** Header : Content Disposition */
	public static final String HEADER_CONTENT_DISPOSITION = "Content-Disposition";
	/** Header : Content Disposition split */
	public static final String HEADER_DISPOSITION_SPLIT = ";";
	/** Header value : filename */
	public static final String HEADER_VALUE_FILENAME = "filename";
	/** Response Header : X-Content-Type-Options: nosniff */
	public static final String HEADER_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
	/** Response Header value : X-Content-Type-Options: nosniff */
	public static final String HEADER_CONTENT_TYPE_OPTIONS_NOSNIFF = "nosniff";
	/** Request Header : X-Requested-With */
	public static final String X_REQUESTED_WITH = "X-Requested-With";
	/** Request Header value : XMLHttpRequest */
	public static final String X_REQUESTED_WITH_WHR = "XMLHttpRequest";
	/** Request Header : Host */
	public static final String HEADER_HOST = "Host";
	/** Response header : Location */
	public static final String HEADER_LOCATION = "Location";
	/** Request Header : Origin */
	public static final String HEADER_ORIGIN = "Origin";
	/** Header : Referer */
	public static final String HEADER_REFERER = "Referer";
	/** Response header : Set-Cookie */
	public static final String HEADER_SET_COOKIE = "Set-Cookie";
	/** Request header : Cookie */
	public static final String HEADER_COOKIE = "Cookie";
	/** RXIDヘッダのキー */
	public static final String HEADER_AUTHORIZATION = "Authorization";
	/** RXIDヘッダのキー */
	public static final String HEADER_AUTHORIZATION_RXID = "RXID ";
	/** JSESSIONID */
	public static final String COOKIE_JSESSIONID = "JSESSIONID";
	/** SID */
	public static final String COOKIE_SID = "SID";
	/** Platform */
	public static final String HEADER_PLATFORM = "X-PLATFORM";
	/** Platform Token */
	public static final String HEADER_PLATFORM_TOKEN = "X-PLATFORM-TOKEN";
	/** アクセストークンヘッダの値の先頭に使用 */
	public static final String HEADER_AUTHORIZATION_TOKEN = "Token ";
	/** Response header : ユーザ番号(UID) */
	public static final String HEADER_X_UID = "X-UID";
	/** Response header : RXID */
	public static final String HEADER_X_RXID = "X-RXID";
	/** WWW-Authenticate */
	public static final String HEADER_WWW_AUTHENTICATE = "WWW-Authenticate";
	/** Header value : None */
	public static final String HEADER_VALUE_NONE = "None";
	/** Header : ETag */
	public static final String HEADER_ETAG = "ETag";
	/** Header : If-None-Match */
	public static final String HEADER_IF_NONE_MATCH = "If-None-Match";
	/** Header :  Last-Modified */
	public static final String HEADER_LAST_MODIFIED = "Last-Modified";
	/** Response header : Access-Control-Allow-Origin */
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = 
			"Access-Control-Allow-Origin";
	/** Response header : Access-Control-Allow-Headers */
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = 
			"Access-Control-Allow-Headers";
	/** Response header : Access-Control-Allow-Methods */
	public static final String ACCESS_CONTROL_ALLOW_METHODS = 
			"Access-Control-Allow-Methods";
	/** Response header : Access-Control-Allow-Credentials */
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = 
			"Access-Control-Allow-Credentials";
	/** Request header : Access-Control-Request-Headers */
	public static final String ACCESS_CONTROL_REQUEST_HEADERS = 
			"Access-Control-Request-Headers";
	/** Request header : Access-Control-Request-Method */
	public static final String ACCESS_CONTROL_REQUEST_METHOD = 
			"Access-Control-Request-Method";
	/** Response header : Access-Control-Max-Age */
	public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	/** Response header value : Access-Control-Allow-Methods */
	public static final String ACCESS_CONTROL_ALLOW_METHODS_VALUE = 
			"POST, PUT, GET, DELETE, OPTIONS";
	/** Response header value : Access-Control-Max-Age */
	public static final String ACCESS_CONTROL_MAX_AGE_VALUE = "-1";
	/** Response header value : Access-Control-Allow-Credentials */
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS_VALUE = "true";
	/** Response header : Pragma */
	public static final String PRAGMA = "Pragma";
	/** Response header : Cache-Control */
	public static final String CACHE_CONTROL = "Cache-Control";
	/** Response header : Expires */
	public static final String EXPIRES = "Expires";
	/** Response header value : no-cache */
	public static final String NO_CACHE = "no-cache";
	/** Response header value : no-store */
	public static final String NO_STORE = "no-store";
	/** Response header value : must-revalidate */
	public static final String MUST_REVALIDATE = "must-revalidate";
	/** Response header value : past date */
	public static final String PAST_DATE = "Fri, 1 Jan 2010 00:00:00";
	/** Request header : Method Override (PUT、DELETEをPOSTで代行する場合に付加) */
	public static final String HEADER_METHOD_OVERRIDE = "X-HTTP-Method-Override";
	/** Request header : Method Override Cookie */
	public static final String COOKIE_METHOD_OVERRIDE_KEY_PATH = "OVERRIDE";
	/** Request header : X-Forwarded-For */
	public static final String HEADER_FORWARDED_FOR = "X-Forwarded-For";
	/** Request header : X-Frame-Options */
	public static final String HEADER_FRAME_OPTIONS = "X-Frame-Options";
	/** Request header value : SAMEORIGIN */
	public static final String SAMEORIGIN = "SAMEORIGIN";
	/** Request header : X-XSS-Protection */
	public static final String HEADER_XSS_PROTECTION = "X-XSS-Protection";
	/** Request header value : X-XSS-Protection */
	public static final String HEADER_XSS_PROTECTION_MODEBLOCK = "1; mode=block";
	/** Request header value : form-data */
	public static final String HEADER_FORM_DATA = "form-data";

	/** Schema : http */
	public static final String SCHEMA_HTTP = "http";
	/** Schema : https */
	public static final String SCHEMA_HTTPS = "https";
	
	/** Port : http default */
	public static final int PORT_DEFAULT_HTTP = 80;
	/** Port : https default */
	public static final int PORT_DEFAULT_HTTPS = 443;

	/** ヘッダの値の区切り文字 */
	public static final String HEADER_VALUE_DELIMITER = ",";
	/** アクセストークンのUID区切り文字 */
	public static final String ACCESSTOKEN_UID_DELIMITER = ",";
	/** アクセストークンのキー区切り文字 */
	public static final String ACCESSTOKEN_KEY_DELIMITER = ",";

	/** XMLヘッダ */
	public static final String XMLHEAD = "<?xml version=\"1.0\" encoding=\"" + ENCODING + 
			"\" ?>\n";
	/** json */
	public static final String JSON = "json";
	/** xml */
	public static final String XML = "xml";
	/** jpg */
	public static final String JPG = "jpg";
	/** jpeg */
	public static final String JPEG = "jpeg";
	/** gif */
	public static final String GIF = "gif";
	/** png */
	public static final String PNG = "png";
	/** txt */
	public static final String TXT = "txt";
	/** html */
	public static final String HTML = "html";
	/** pdf */
	public static final String PDF = "pdf";

	/** 改行コード */
	public static final String NEWLINE = "\n";
	/** HTMLの空白 */
	public static final String HTML_BLANK = "&nbsp;";
	/** Reflexロゴ */
	public static final String REFLEX_LOGOS = "http://reflex.sourceforge.jp/images/Reflex.gif";
	/** Reflex Signature */
	public static final String REFLEX_SIGNATURE = "　　ＶＩＲＴＵＡＬ ＴＥＣＨＮＯＬＯＧＹ ＩＮＣ.";
	/** デフォルトページ */
	public static final String DEFAULT_PAGE = "index.html";

	/** Method : GET */
	public static final String GET = "GET";
	/** Method : POST */
	public static final String POST = "POST";
	/** Method : PUT */
	public static final String PUT = "PUT";
	/** Method : DELETE */
	public static final String DELETE = "DELETE";
	/** Method : OPTIONS */
	public static final String OPTIONS = "OPTIONS";

	/** Format : Text */
	public static final int FORMAT_TEXT = 0;
	/** Format : XML */
	public static final int FORMAT_XML = 1;
	/** Format : JSON */
	public static final int FORMAT_JSON = 2;
	/** Format : MessagePack */
	public static final int FORMAT_MESSAGEPACK = 3;
	/** Format : multipart/form-data */
	public static final int FORMAT_MULTIPART_FORMDATA = 4;
	/** Format : Binary */
	public static final int FORMAT_BINARY = 5;

}
