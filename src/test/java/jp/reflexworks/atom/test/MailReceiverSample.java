package jp.reflexworks.atom.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.entry.FeedBase;
import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.reflexworks.atom.util.MailReceiver;

import org.junit.Test;

/**
 * メール受信テスト.
 * mavenビルド時にテスト実行されないよう、クラス名から「Test」を除去。
 */
public class MailReceiverSample {

	@Test
	public void test() {
		System.out.println("メール受信: 開始");
		try {
			MailReceiver mr = new MailReceiver();
			FeedTemplateMapper mapper = new FeedTemplateMapper(
					new String[] { "default" }, "");

			Map<String,String> propmap = new HashMap<String,String>();

			propmap.put("mail.pop3.host", "pop.gmail.com");
			propmap.put("mail.pop3.port", "995");
			propmap.put("mail.pop3.connectiontimeout", "60000");
			propmap.put("mail.pop3.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			propmap.put("mail.pop3.socketFactory.fallback", "false");
			propmap.put("mail.pop3.socketFactory.port", "995");
			propmap.put("username", "reflexworks.test@gmail.com");
			propmap.put("password", "reflex0613");

			FeedBase feed = mr.doReceive(mapper, propmap);

			System.out.println("メール受信: 終了");

			for(EntryBase entry:feed.getEntry()) {
				System.out.println("Number:"+entry.id);
				System.out.println("Subject:"+entry.title);
				System.out.println("From:"+entry.subtitle);
				System.out.println("Date:"+entry.published);
				System.out.println("Content:"+entry.summary);
				System.out.println("FileName:"+entry.content._$src);
				System.out.println("File:"+entry.content._$$text);
			}

		}catch(Exception e) {
			e.printStackTrace();
			// Do nothing
		}
		assertTrue(true);
	}

}
