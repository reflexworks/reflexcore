package jp.reflexworks.atom.api;

/**
 * メール送信定数クラス.
 */
public interface MailConst {

	/** プロパティ : 送信元アドレス */
	public static final String PROP_FROM = "mail.from";
	/** プロパティ : 送信元名 */
	public static final String PROP_FROM_PERSONAL = "mail.from.personal";
	/** プロパティ : 認証ユーザ */
	public static final String PROP_USER = "mail.user";
	/** プロパティ : 認証パスワード */
	public static final String PROP_PASSWORD = "mail.password";
	/** プロパティ : ホスト名 */
	public static final String PROP_HOST = "mail.host";
	/** プロパティ : SMTPサーバ */
	public static final String PROP_SMTP_HOST = "mail.smtp.host";
	/** プロパティ : SMTPポート番号 */
	public static final String PROP_SMTP_PORT = "mail.smtp.port";
	/** プロパティ : STARTTLSを使用するかどうか */
	public static final String PROP_SMTP_STARTTLS = "mail.smtp.starttls.enable";
	/** プロパティ : プロトコル */
	public static final String PROP_TRANSPORT_PROTOCOL = "mail.transport.protocol";
	/** プロパティ : 認証を行うかどうか */
	public static final String PROP_SMTP_AUTH = "mail.smtp.auth";
	/** プロパティ : メール送信デバッグログを出力するかどうか */
	public static final String PROP_DEBUG = "mail.debug";

	/** SMTPプロトコル */
	public static final String SMTP = "smtp";
	/** SMTPSプロトコル */
	public static final String SMTPS = "smtps";

	/** Charset : ISO-2022-JP */
	public static final String CHARSET = "ISO-2022-JP";
	/** Content-Type : text/plain */
	public static final String CONTENT_TYPE = "text/plain; charset=" + CHARSET;
	/** Encoding : B or Q */
	public static final String ENCODING = "B";	// B or Q
	/** SubType : plain */
	public static final String PLAIN = "plain";
	/** SubType : html */
	public static final String HTML = "html";
	/** Header : Content-Transfer-Encoding */
	public static final String CONTENT_TRANSFER_ENCODING = "Content-Transfer-Encoding";
	/** Header value : Base64 */
	public static final String BASE64 = "base64";
	/** MimeMultipart : alternative */
	public static final String ALTERNATIVE = "alternative";
	/** MimeMultipart : mixed */
	public static final String MIXED = "mixed";
	/** MimeMultipart : related */
	public static final String RELATED = "related";

}
