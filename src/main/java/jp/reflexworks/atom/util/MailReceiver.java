package jp.reflexworks.atom.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.internet.MimeUtility;

import org.apache.commons.codec.binary.Base64;

import jp.reflexworks.atom.api.EntryUtil;
import jp.reflexworks.atom.entry.Content;
import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.entry.FeedBase;
import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.sourceforge.reflex.util.DateUtil;

import org.apache.commons.io.IOUtils;

import com.sun.mail.pop3.POP3Store;

public class MailReceiver {

	/** エンコード*/
	public static final String ENCODING = "UTF-8";
	
	public FeedBase doReceive(FeedTemplateMapper mapper, Map<String,String> propmap) throws MessagingException, IOException {
		
		Properties props = new Properties();
		String username = "";
		String password = "";
		
		for(Map.Entry<String, String> entry:propmap.entrySet()) {
			
			if (entry.getKey().equals("username")) {
				username = entry.getValue();
			}else if(entry.getKey().equals("password")){
				password = entry.getValue();
			}else {
				props.setProperty(entry.getKey(), entry.getValue());
			}
		}
		return doReceive(mapper,props,username,password);
		
	}
	
	public FeedBase doReceive(FeedTemplateMapper mapper, Properties props,
			String username, String password) throws MessagingException,
			IOException {
		
		Session session = Session.getInstance(props);
		// session.setDebug(debug);

		POP3Store store = (POP3Store) session.getStore("pop3");
		store.connect(username, password);

		Folder folder = store.getFolder("INBOX");
		FeedBase feed = EntryUtil.createFeed(mapper);

		try {
		folder.open(Folder.READ_ONLY);

		Message[] msgs = folder.getMessages();
		feed.entry = new ArrayList<EntryBase>();
		
		for (Message msg : msgs) {
			
			EntryBase entry = EntryUtil.createEntry(mapper);
			// Subject
			entry.title = msg.getSubject();
			// Number
			entry.id = ""+msg.getMessageNumber();
			// From
			entry.subtitle = "" + msg.getFrom()[0];
			// 日時
			entry.published = DateUtil.getDateTime(msg.getSentDate());
			entry.content = new Content();

			Object content = msg.getContent();
			
			if (content instanceof Multipart) {
				final Multipart multiPart = (Multipart) content;
				for (int indexPart = 0; indexPart < multiPart.getCount(); indexPart++) {
					final Part part = multiPart.getBodyPart(indexPart);
					final String disposition = part.getDisposition();
					if (Part.ATTACHMENT.equals(disposition) || Part.INLINE.equals(disposition)) {
						// 添付ファイル名
						entry.content._$src = MimeUtility.decodeText(part.getFileName());
						entry.content._$type = MimeUtility.decodeText(part.getContentType());						
						byte[] body = IOUtils.toByteArray(part.getInputStream());
						entry.content._$$text =
								new String(Base64.encodeBase64(body), ENCODING);
					} else {
						// 添付ファイル
						entry.summary = part.getContent().toString();
					}
				}
			} else {
				// 本文
				entry.summary = content.toString();
			}
			// メールの削除フラグ
			msg.setFlag(Flags.Flag.DELETED, true);
			feed.entry.add(entry);
		}

		} finally {
            if (folder != null) {
                // 削除マークされたメールを実際に削除
                folder.close(true);
            }
        }

		return feed;
	}
}