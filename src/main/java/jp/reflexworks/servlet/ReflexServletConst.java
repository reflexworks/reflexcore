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
	/** Reqest Header : X-Requested-With */
	public static final String X_REQUESTED_WITH = "X-Requested-With";
	/** Reqest Header value : XMLHttpRequest */
	public static final String X_REQUESTED_WITH_WHR = "XMLHttpRequest";
	/** Reqest Header : Host */
	public static final String HEADER_HOST = "Host";
	/** Response header : Location */
	public static final String HEADER_LOCATION = "Location";
	/** Reqest Header : Origin */
	public static final String HEADER_ORIGIN = "Origin";
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
	/** Response header : Access-Control-Allow-Origin */
	public static final String ACCESS_CONTROL_ALLOW_ORIGIN = 
			"Access-Control-Allow-Origin";
	/** Response header : Access-Control-Allow-Headers */
	public static final String ACCESS_CONTROL_ALLOW_HEADERS = 
			"Access-Control-Allow-Headers";
	/** Request header : Access-Control-Request-Headers */
	public static final String ACCESS_CONTROL_REQUEST_HEADERS = 
			"Access-Control-Request-Headers";
	/** Response header : Access-Control-Allow-Methods */
	public static final String ACCESS_CONTROL_ALLOW_METHODS = 
			"Access-Control-Allow-Methods";
	/** Response header : Access-Control-Max-Age */
	public static final String ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";
	/** Response header : Access-Control-Allow-Credentials */
	public static final String ACCESS_CONTROL_ALLOW_CREDENTIALS = 
			"Access-Control-Allow-Credentials";

	/** ヘッダの値の区切り文字 */
	public static final String HEADER_VALUE_DELIMITER = ",";
	/** アクセストークンのUID区切り文字 */
	public static final String ACCESSTOKEN_UID_DELIMITER = ",";

	/** XMLヘッダ */
	public static final String XMLHEAD = "<?xml version=\"1.0\" encoding=\"" + ENCODING + 
			"\" ?>\n";
	/** json */
	public static final String JSON = "json";
	/** xml */
	public static final String XML = "xml";

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
