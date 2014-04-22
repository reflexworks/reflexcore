package jp.reflexworks.servlet;

/**
 * ReflexServletで使用する定数
 */
public interface ReflexServletConst {

	/** エンコード*/
	public static final String ENCODING = "UTF-8";
	/** Content-Type : XML */
	public static final String CONTENT_TYPE_XML = "text/xml";
	/** Content-Type : XML, charset : UTF-8 */
	public static final String CONTENT_TYPE_REFLEX_XML = 
			CONTENT_TYPE_XML + ";charset=" + ENCODING;
	/** Content-Type : JSON */
	//public static final String CONTENT_TYPE_JSON = "text/javascript";
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
	public static final String CONTENT_TYPE_HTML = "text/html";
	/** Content-Type : HTML; charset */
	public static final String CONTENT_TYPE_HTML_CHARSET = CONTENT_TYPE_HTML + CHARSET;
	/** Content-Type : Plain Text */
	public static final String CONTENT_TYPE_PLAIN = "text/plain";
	/** Content-Type : Plain Text; charset */
	public static final String CONTENT_TYPE_PLAIN_CHARSET = CONTENT_TYPE_PLAIN + CHARSET;
	/** Content Type : png */
	public static final String CONTENT_TYPE_PNG = "image/png";
	/** Content Type : jpeg */
	public static final String CONTENT_TYPE_JPEG = "image/jpeg";
	/** Content Type : gif */
	public static final String CONTENT_TYPE_GIF = "image/gif";
	/** Content Type : pdf */
	public static final String CONTENT_TYPE_PDF = "application/pdf";
	/** Content Type : application/xml */
	public static final String CONTENT_TYPE_APPLICATION_XML = "application/xml";

	/** Header : Content Type */
	public static final String HEADER_CONTENT_TYPE = "Content-Type";
	/** Header : Content Length */
	public static final String HEADER_CONTENT_LENGTH = "Content-Length";
	/** Header : Content Encoding */
	public static final String HEADER_CONTENT_ENCODING = "Content-Encoding";
	/** Header value : Content-Encoding: deflate */
	public static final String HEADER_CONTENT_ENCODING_DEFLATE = "deflate";
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
	/** Response header : Set-Cookie */
	public static final String HEADER_SET_COOKIE = "Set-Cookie";
	/** Request header : Cookie */
	public static final String HEADER_COOKIE = "Cookie";
	/** RXIDヘッダのキー */
	public static final String HEADER_AUTHORIZATION = "Authorization";
	/** RXIDヘッダのキー */
	public static final String HEADER_AUTHORIZATION_TOKEN = "Token ";
	/** JSESSIONID */
	public static final String COOKIE_JSESSIONID = "JSESSIONID";
	/** Platform */
	public static final String HEADER_PLATFORM = "X-PLATFORM";
	/** Platform Token */
	public static final String HEADER_PLATFORM_TOKEN = "X-PLATFORM-TOKEN";
	/** アクセスキーヘッダの値の先頭に使用 */
	public static final String HEADER_AUTHORIZATION_ACCESSKEY = "AccessKey ";

	/** XMLヘッダ */
	public static final String XMLHEAD = "<?xml version=\"1.0\" encoding=\"" + ENCODING + "\" ?>\n";
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

}
