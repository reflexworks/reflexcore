package jp.reflexworks.atom.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import jakarta.mail.internet.MimeUtility;
import jp.reflexworks.atom.api.MailConst;
import jp.sourceforge.reflex.util.StringUtils;

/**
 * メール送信ユーティリティ
 */
public class MailUtil implements MailConst {

	/** ロガー */
	private static Logger logger = Logger.getLogger(MailUtil.class.getName());

	/**
	 * テキストメール送信
	 * @param title 題名
	 * @param textMessage テキストメッセージ
	 * @param to 送信先アドレス
	 * @param from 送信元アドレス(認証にも使用)
	 * @param password 認証パスワード
	 * @param host SMTPサーバ
	 * @param port ポート番号
	 * @param protocol プロトコル(smtpまたはsmtps)
	 * @param debug デバッグログを出力する場合true
	 */
	public static void send(String title, String textMessage, String to, String from, 
			String password, String host, String port, String protocol,
			boolean debug) 
	throws IOException {
		send(title, textMessage, null, null, null, 
				to, null, from, null, null, password, host, port, protocol, 
				null, null, debug);
	}
	
	/**
	 * メール送信
	 * @param title 題名
	 * @param textMessage テキストメッセージ
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @param attachments 添付ファイル
	 * @param to 送信先アドレス
	 * @param toPersonal 送信先名
	 * @param from 送信元アドレス
	 * @param fromPersonal 送信元名
	 * @param user 認証ユーザ(指定のない場合は送信元アドレスが認証ユーザとなる。)
	 * @param password 認証パスワード
	 * @param host SMTPホスト
	 * @param port SMTPポート番号
	 * @param protocol プロトコル(smtpまたはsmtps)
	 * @param isStarttls STARTTLSを行う場合true(デフォルトはtrue)
	 * @param isAuth 認証を行う場合true(デフォルトはtrue)
	 * @param debug デバッグログを出力する場合true
	 */
	public static void send(String title, String textMessage, String htmlMessage,
			Map<String, DataSource> inlineImages, List<DataSource> attachments,
			String to, String toPersonal, String from, String fromPersonal, 
			String user, String password,  String host, String port, String protocol, 
			Boolean isStarttls, Boolean isAuth, boolean debug) 
	throws IOException {
		String[] tos = new String[]{to};
		String[] toPersonals = null;
		if (!StringUtils.isBlank(toPersonal)) {
			toPersonals = new String[]{toPersonal};
		}
		send(title, textMessage, htmlMessage, inlineImages, attachments,
				tos, toPersonals, null, null, null, null, from, fromPersonal,
				user, password, host, port, protocol, isStarttls, isAuth, debug);
	}

	/**
	 * メール送信
	 * @param title 題名
	 * @param textMessage テキストメッセージ
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @param attachments 添付ファイル
	 * @param to 送信先アドレス配列
	 * @param toPersonal 送信先名配列
	 * @param cc CC送信先アドレス配列
	 * @param ccPersonal CC送信先名配列
	 * @param bcc BCC送信先アドレス配列
	 * @param bccPersonal BCC送信先名配列
	 * @param from 送信元アドレス
	 * @param fromPersonal 送信元名
	 * @param user 認証ユーザ(指定のない場合は送信元アドレスが認証ユーザとなる。)
	 * @param password 認証パスワード
	 * @param host SMTPホスト
	 * @param port SMTPポート番号
	 * @param protocol プロトコル(smtpまたはsmtps)
	 * @param isStarttls STARTTLSを行う場合true(デフォルトはtrue)
	 * @param isAuth 認証を行う場合true(デフォルトはtrue)
	 * @param debug デバッグログを出力する場合true
	 */
	public static void send(String title, String textMessage, String htmlMessage,
			Map<String, DataSource> inlineImages, List<DataSource> attachments,
			String[] to, String[] toPersonal, String[] cc, String[] ccPersonal, 
			String[] bcc, String[] bccPersonal, String from, String fromPersonal, 
			String user, String password,  String host, String port, String protocol, 
			Boolean isStarttls, Boolean isAuth, boolean debug) 
	throws IOException {
		Properties props = new Properties();
		props.put(PROP_SMTP_HOST, host);
		props.put(PROP_HOST, host);
		props.put(PROP_FROM, from);
		props.put(PROP_SMTP_PORT, port);  // サブミッションポート
		if (isAuth == null || isAuth) {
			props.put(PROP_SMTP_AUTH, "true");   // SMTP 認証を行う
		}
		if (isStarttls == null || isStarttls) {
			props.put(PROP_SMTP_STARTTLS, "true");   // STARTTLS
		}
		if (debug) {
			props.put(PROP_DEBUG, "true");
		}
		
		send(title, textMessage, htmlMessage, inlineImages, attachments, 
				to, toPersonal, cc, ccPersonal, bcc, bccPersonal,
				from, fromPersonal, user, password, props, protocol, debug);
	}

	/**
	 * メール送信
	 * @param title 題名
	 * @param textMessage テキストメッセージ
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @param attachments 添付ファイル
	 * @param to 送信先アドレス
	 * @param toPersonal 送信先名
	 * @param from 送信元アドレス
	 * @param fromPersonal 送信元名
	 * @param user 認証ユーザ(指定のない場合は送信元アドレスが認証ユーザとなる。)
	 * @param password 認証パスワード
	 * @param props プロパティ
	 * @param protocol プロトコル(smtpまたはsmtps)
	 * @param debug デバッグログを出力する場合true
	 */
	public static void send(String title, String textMessage, String htmlMessage,
			Map<String, DataSource> inlineImages, List<DataSource> attachments,
			String to, String toPersonal,
			String from, String fromPersonal, String user, String password, 
			Properties props, String protocol, boolean debug) 
	throws IOException {
		String[] tos = new String[]{to};
		String[] toPersonals = null;
		if (!StringUtils.isBlank(toPersonal)) {
			toPersonals = new String[]{toPersonal};
		}
		send(title, textMessage, htmlMessage, inlineImages, attachments, 
				tos, toPersonals, null, null, null, null, from, fromPersonal,
				user, password, props, protocol, debug);
	}

	/**
	 * メール送信
	 * @param title 題名
	 * @param textMessage テキストメッセージ
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @param attachments 添付ファイル
	 * @param to 送信先アドレス
	 * @param toPersonal 送信先名
	 * @param from 送信元アドレス
	 * @param fromPersonal 送信元名
	 * @param user 認証ユーザ(指定のない場合は送信元アドレスが認証ユーザとなる。)
	 * @param password 認証パスワード
	 * @param props プロパティ
	 * @param protocol プロトコル(smtpまたはsmtps)
	 * @param debug デバッグログを出力する場合true
	 */
	public static void send(String title, String textMessage, String htmlMessage,
			Map<String, DataSource> inlineImages, List<DataSource> attachments,
			String[] to, String[] toPersonal, String[] cc, String[] ccPersonal,
			String[] bcc, String[] bccPersonal,
			String from, String fromPersonal, String user, String password, 
			Properties props, String protocol, boolean debug) 
	throws IOException {
		Session session = Session.getInstance(props);
		session.setDebug(debug);
		
		Transport transport = null;
		try {
			InternetAddress iaFrom = new InternetAddress(from, fromPersonal, CHARSET);
			InternetAddress[] iaTos = createInternetAddresses(to, toPersonal);
			InternetAddress[] iaCcs = createInternetAddresses(cc, ccPersonal);
			InternetAddress[] iaBccs = createInternetAddresses(bcc, bccPersonal);
			
			MimeMessage msg = new MimeMessage(session);
			
			// From
			msg.setFrom(iaFrom);
			// To
			msg.setRecipients(Message.RecipientType.TO, iaTos);
			// Cc
			if (iaCcs != null) {
				msg.setRecipients(Message.RecipientType.CC, iaCcs);
			}
			// Bcc
			if (iaBccs != null) {
				msg.setRecipients(Message.RecipientType.BCC, iaBccs);
			}
			// Subject
			msg.setSubject(title, CHARSET);
			// Date
			msg.setSentDate(new Date());
			
			// メインパート
			// HTML形式 + インライン画像 + 添付ファイルの場合、mixed
			// その他はalternative
			Multipart alternativePart = new MimeMultipart(ALTERNATIVE);
			Multipart mainPart = null;
			if (isMixedPart(htmlMessage, inlineImages, attachments)) {
				// mixed
				mainPart = new MimeMultipart(MIXED);
				
				// alternative
				MimeBodyPart alternativeBodyPart = new MimeBodyPart();
				alternativeBodyPart.setContent(alternativePart);
				mainPart.addBodyPart(alternativeBodyPart);
				
			} else {
				// alternative
				mainPart = alternativePart;
			}
			
			// text mail
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(StringUtils.null2blank(textMessage), CHARSET, PLAIN);
			textBodyPart.setHeader(CONTENT_TRANSFER_ENCODING, BASE64);
			alternativePart.addBodyPart(textBodyPart);
			
			// html mail
			if (isSendHtmlMessage(htmlMessage)) {
				boolean isSendInlineImages = isSendInlineImages(htmlMessage, 
						inlineImages);
				Multipart relatedPart = null;
				if (isSendInlineImages) {
					// related
					MimeBodyPart relatedBodyPart = new MimeBodyPart();
					relatedPart = new MimeMultipart(RELATED);
					relatedBodyPart.setContent(relatedPart);
					alternativePart.addBodyPart(relatedBodyPart);
				} else {
					// alternative
					relatedPart = alternativePart;
				}
				
				// html message
				MimeBodyPart htmlBodyPart = new MimeBodyPart();
				htmlBodyPart.setText(htmlMessage, CHARSET, HTML);
				htmlBodyPart.setHeader(CONTENT_TRANSFER_ENCODING, BASE64);
				relatedPart.addBodyPart(htmlBodyPart);
				
				// inline image
				if (isSendInlineImages) {
					for (Map.Entry<String, DataSource> mapEntry : inlineImages.entrySet()) {
						String cid = mapEntry.getKey();
						DataSource dataSource = mapEntry.getValue();
						MimeBodyPart imageBodyPart = new MimeBodyPart();
						DataHandler dataHandler= new DataHandler(dataSource);
						imageBodyPart.setDataHandler(dataHandler);
						String fileName = dataSource.getName();
						if (!StringUtils.isBlank(fileName)) {
							imageBodyPart.setFileName(MimeUtility.encodeWord(dataSource.getName()));
						}
						imageBodyPart.setDisposition(MimeBodyPart.INLINE);	    // inline指定しておく
						imageBodyPart.setContentID("<" + cid + ">");  // インライン画像を指定
						relatedPart.addBodyPart(imageBodyPart);
					}
				}
			}
			
			// attach image
			if (isSendAttachments(attachments)) {
				for (DataSource dataSource : attachments) {
					MimeBodyPart attachBodyPart = new MimeBodyPart();
					DataHandler dataHandler2 = new DataHandler(dataSource);
					attachBodyPart.setDataHandler(dataHandler2);
					String fileName = dataHandler2.getName();
					attachBodyPart.setFileName(MimeUtility.encodeWord(fileName));
					attachBodyPart.setDisposition(MimeBodyPart.ATTACHMENT);  // attachment 指定しておく
					mainPart.addBodyPart(attachBodyPart);
				}
			}
			
			// set mainPart
			msg.setContent(mainPart);
			
			if (!StringUtils.isBlank(protocol)) {
				// protocolが設定されている場合、認証コネクト
				transport = session.getTransport(protocol);
				if (StringUtils.isBlank(user)) {
					user = from;
				}
				transport.connect(user, password);
				transport.sendMessage(msg, msg.getAllRecipients());

			} else {
				// protocolが設定されていない場合、そのままsendする。(GAE)
				Transport.send(msg);
			}
			
		} catch (MessagingException e) {
			IOException ie = new IOException(e.getMessage());
			ie.initCause(e);
			throw ie;

		} finally {
			if (transport != null) {
				try {
					transport.close();
				} catch (MessagingException mex) {
					logger.warning("MessagingException: " + mex.getMessage());
				}
			}
		}
	}
	
	/**
	 * HTMLメッセージを送るかどうか判定
	 * @param htmlMessage HTMLメッセージ
	 * @return HTMLメッセージを送る場合true
	 */
	private static boolean isSendHtmlMessage(String htmlMessage) {
		return !StringUtils.isBlank(htmlMessage);
	}
	
	/**
	 * インライン画像を送るかどうか判定
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @return インライン画像を送る場合true
	 */
	private static boolean isSendInlineImages(String htmlMessage, 
			Map<String, DataSource> inlineImages) {
		return !StringUtils.isBlank(htmlMessage) &&
				inlineImages != null && !inlineImages.isEmpty();
	}
	
	/**
	 * 添付ファイルを送るかどうか判定
	 * @param attachments 添付ファイル
	 * @return 添付ファイルを送る場合true
	 */
	private static boolean isSendAttachments(List<DataSource> attachments) {
		return attachments != null && !attachments.isEmpty();
	}
	
	/**
	 * mixed partかどうか判定.
	 * 以下の引数が全て設定されている場合true
	 * @param htmlMessage HTMLメッセージ
	 * @param inlineImages インライン画像
	 * @param attachments 添付ファイル
	 * @return mixed partの場合true
	 */
	private static boolean isMixedPart(String htmlMessage, 
			Map<String, DataSource> inlineImages, List<DataSource> attachments) {
		return isSendInlineImages(htmlMessage, inlineImages) && 
				isSendAttachments(attachments);
	}
	
	/**
	 * InternetAddress配列を生成
	 * @param to 送信先配列
	 * @param toPersonal 送信先名配列
	 * @return InternetAddress配列
	 */
	private static InternetAddress[] createInternetAddresses(String[] to, String[] toPersonal) 
	throws IOException {
		if (to == null || to.length == 0) {
			return null;
		}
		int toLen = to.length;
		int toPersonalLen = 0;
		if (toPersonal != null) {
			toPersonalLen = toPersonal.length;
		}
		InternetAddress[] iaTos = new InternetAddress[toLen];
		for (int i = 0; i < toLen; i++) {
			String tmpTo = to[i];
			String tmpToPersonal = null;
			if (i < toPersonalLen) {
				tmpToPersonal = toPersonal[i];
			}
			iaTos[i] = new InternetAddress(StringUtils.trim(tmpTo), 
					tmpToPersonal, CHARSET);
		}
		return iaTos;
	}
	
	/**
	 * メッセージをJISに変換
	 * @param msg メッセージ
	 * @return メッセージを変換した文字列
	 */
	public static String convertJIS(String msg) {
		try {
			byte[] convBytes = msg.getBytes(CHARSET);
			return new String(convBytes);
		} catch (UnsupportedEncodingException e) {
			logger.warning("UnsupportedEncodingException: " + e.getMessage());
		}
		return null;
	}

}
