package jp.reflexworks.atom.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jp.reflexworks.atom.api.Condition;
import jp.reflexworks.atom.api.EntryUtil;
import jp.reflexworks.atom.entry.EntryBase;
import jp.reflexworks.atom.entry.FeedBase;
import jp.reflexworks.atom.mapper.CipherUtil;
import jp.reflexworks.atom.mapper.FeedTemplateMapper;
import jp.sourceforge.reflex.util.FileUtil;
import jp.sourceforge.reflex.util.StringUtils;

public class TemplateChecker {

	private static final String ENCODING = "UTF-8";
	
	private static final String SERVICE_NAME = "example1";
	//private static final String SERVICE_NAME = "bqschema";
	private static final String SERVICE_NAME_X = "exampleX";
	private static final String UID = "2";
	private static final List<String> GROUPS = new ArrayList<String>();
	static {
		GROUPS.add("/@" + SERVICE_NAME + "/_group/$admin");
		GROUPS.add("/@" + SERVICE_NAME + "/_group/$useradmin");
		GROUPS.add("/@" + SERVICE_NAME + "/_group/$content");
	}

	@Test
	public void check() 
	throws IOException, ParseException, URISyntaxException, GeneralSecurityException {
		// 指定されたテンプレートにエラーがないかチェックする。
		String templateFileStr = getFilePathTemplate(SERVICE_NAME);
		String indexEncItemACLFileStr = getFilePathIndexEncItemACL(SERVICE_NAME);
		FeedTemplateMapper mapper = createMapper(templateFileStr, indexEncItemACLFileStr);
		System.out.println("create mapper OK : template_" + SERVICE_NAME + ".txt");
		
		// データチェック
		String dataPath = getFilePathData(SERVICE_NAME);
		FeedBase feed = createFeedFromJsonFile(mapper, dataPath);
		if (feed == null) {
			throw new IllegalArgumentException("Feed is null. file path = " + dataPath);
		}
		if (feed.entry == null) {
			throw new IllegalArgumentException("Feed's entries are null. file path = " + dataPath);
		}
		if (feed.entry.size() == 0) {
			throw new IllegalArgumentException("Feed's entries are empty. file path =  " + dataPath);
		}
		// バリデーション
		feed.validate(UID, GROUPS);

		// 暗号化
		EntryBase entry = feed.entry.get(0);

		// test log
		System.out.println("[start]testinfo.str.encrypt" + " = " + entry.getValue("testinfo.str.encrypt"));
		System.out.println("[start]testinfo.str_idx.encrypt" + " = " + entry.getValue("testinfo.str_idx.encrypt"));
		System.out.println("[start]testinfo.str.map.encrypt" + " = " + entry.getValue("testinfo.str.map.encrypt"));
		System.out.println("[start]testinfo.str.map_required.encrypt" + " = " + entry.getValue("testinfo.str.map_required.encrypt"));
		System.out.println("[start]testinfo.str_idx.map.encrypt" + " = " + entry.getValue("testinfo.str_idx.map.encrypt"));
		System.out.println("[start]testinfo.str_idx.map_required.encrypt" + " = " + entry.getValue("testinfo.str_idx.map_required.encrypt"));
		
		String tmpTemplateFileStr = getFilePathTemplate("encrypt");
		String tmpIndexEncItemACLFileStr = getFilePathIndexEncItemACL("encrypt");
		FeedTemplateMapper encMapper = createMapper(tmpTemplateFileStr, tmpIndexEncItemACLFileStr);

		CipherUtil cipherUtil = new CipherUtil();
		cipherUtil.encrypt(feed);

		entry = feed.entry.get(0);

		// test log
		System.out.println("[encrypt]testinfo.str.encrypt" + " = " + entry.getValue("testinfo.str.encrypt"));
		System.out.println("[encrypt]testinfo.str_idx.encrypt" + " = " + entry.getValue("testinfo.str_idx.encrypt"));
		System.out.println("[encrypt]testinfo.str.map.encrypt" + " = " + entry.getValue("testinfo.str.map.encrypt"));
		System.out.println("[encrypt]testinfo.str.map_required.encrypt" + " = " + entry.getValue("testinfo.str.map_required.encrypt"));
		System.out.println("[encrypt]testinfo.str_idx.map.encrypt" + " = " + entry.getValue("testinfo.str_idx.map.encrypt"));
		System.out.println("[encrypt]testinfo.str_idx.map_required.encrypt" + " = " + entry.getValue("testinfo.str_idx.map_required.encrypt"));

		// 暗号化されているか
		assertTrue(!"encstrencenc".equals(entry.getValue("testinfo.str.encrypt")));
		String tmp = encrypt(encMapper, "encstrencenc", cipherUtil);
		assertTrue(tmp.equals(entry.getValue("testinfo.str.encrypt")));
		assertTrue(!"encstrIdxencenc".equals(entry.getValue("testinfo.str_idx.encrypt")));
		tmp = encrypt(encMapper, "encstrIdxencenc", cipherUtil);
		assertTrue(tmp.equals(entry.getValue("testinfo.str_idx.encrypt")));
		
		tmp = encrypt(encMapper, "encstrmapencenc1", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map.encrypt")).get(0)));
		tmp = encrypt(encMapper, "encstrmapencenc2", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map.encrypt")).get(1)));
		tmp = encrypt(encMapper, "encstrmapencenc3", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map.encrypt")).get(2)));

		tmp = encrypt(encMapper, "encencenc-1mrq", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map_required.encrypt")).get(0)));
		tmp = encrypt(encMapper, "encencenc-2mrq", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map_required.encrypt")).get(1)));
		tmp = encrypt(encMapper, "encencenc-3mrq", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str.map_required.encrypt")).get(2)));

		tmp = encrypt(encMapper, "encstrIdxMapencenc1", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map.encrypt")).get(0)));
		tmp = encrypt(encMapper, "encstrIdxMapencenc2", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map.encrypt")).get(1)));
		tmp = encrypt(encMapper, "encstrIdxMapencenc3", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map.encrypt")).get(2)));

		tmp = encrypt(encMapper, "encstrIdxMapRequired1enc", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map_required.encrypt")).get(0)));
		tmp = encrypt(encMapper, "encstrIdxMapRequired2enc", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map_required.encrypt")).get(1)));
		tmp = encrypt(encMapper, "encstrIdxMapRequired3enc", cipherUtil);
		assertTrue(tmp.equals(((List<String>)entry.getValue("testinfo.str_idx.map_required.encrypt")).get(2)));

		cipherUtil.decrypt(feed);
		
		// 復号チェック
		checkCipher(entry, "testinfo.str.encrypt", "encstrencenc");
		checkCipher(entry, "testinfo.str_idx.encrypt", "encstrIdxencenc");
		checkCipherList(entry, "testinfo.str.map.encrypt", new String[]{"encstrmapencenc1", "encstrmapencenc2", "encstrmapencenc3"});
		checkCipherList(entry, "testinfo.str.map_required.encrypt", new String[]{"encencenc-1mrq", "encencenc-2mrq", "encencenc-3mrq"});
		checkCipherList(entry, "testinfo.str_idx.map.encrypt", new String[]{"encstrIdxMapencenc1", "encstrIdxMapencenc2", "encstrIdxMapencenc3"});
		checkCipherList(entry, "testinfo.str_idx.map_required.encrypt", new String[]{"encstrIdxMapRequired1enc", "encstrIdxMapRequired2enc", "encstrIdxMapRequired3enc"});
		
		// サービス名付与・除去
		feed.addSvcname(SERVICE_NAME);
		feed.cutSvcname(SERVICE_NAME);
		
		// 値の取得
		entry = feed.entry.get(0);
		//String name = "testinfo.int_idx.intmap_limit.range";
		//String name = "testinfo";
		//String name = "testinfo.int_idx.group_useradmin_r";
		//String name = "testinfo.int_idx";
		String name = "testinfo.int_idx.intmap_required.required";
		Object obj = entry.getValue(name);

		System.out.println("  [getValue]" + name + " : " + obj);
		int dataInt_idx$intmap_required$required = 203880201;	// data_example1.xml より抜粋
		System.out.println("  [xml data]" + name + " : " + dataInt_idx$intmap_required$required);
		
		assertTrue(((List<Integer>)obj).get(0).equals(dataInt_idx$intmap_required$required));

		System.out.println("data_" + SERVICE_NAME + ".xml : OK");
		
		// compareTo : 引数文字列がこの文字列に等しい場合は、値 0。
		//             この文字列が文字列引数より辞書式に小さい場合は、0 より小さい値。
		//             この文字列が文字列引数より辞書式に大きい場合は、0 より大きい値。
		String anotherString = "gggrrr298kk-3mrq";
		System.out.println("gggrrr219kk-mrq".compareTo(anotherString));
		System.out.println("gggrrr219kk-mrq2".compareTo(anotherString));
		System.out.println("gggrrr219kk-3mrq".compareTo(anotherString));
		
		// Feed
		String dataStr = readData(SERVICE_NAME_X);
		if (StringUtils.isBlank(dataStr)) {
			throw new IllegalArgumentException("file " + SERVICE_NAME_X + " is null.");
		}
		
		dataStr = dataStr.replaceAll("XXX", "219");
		feed = createFeedFromJson(mapper, dataStr);
		entry = feed.entry.get(0);
		
		// isMatch
		String item = "testinfo.str.group_users_r";
		System.out.println(item + " = " + entry.getValue(item));
		String value = "gggrr219kkk";
		isMatch(entry, item + "-eq-" + value, true);
		isMatch(entry, item + "-lt-" + value, false);
		isMatch(entry, item + "-le-" + value, true);
		isMatch(entry, item + "-ge-" + value, true);
		isMatch(entry, item + "-gt-" + value, false);

		// 比較対象が小さい場合
		value = "gggrr218kkk";
		isMatch(entry, item + "-eq-" + value, false);
		isMatch(entry, item + "-lt-" + value, false);
		isMatch(entry, item + "-le-" + value, false);
		isMatch(entry, item + "-ge-" + value, true);
		isMatch(entry, item + "-gt-" + value, true);

		// 比較対象が大きい場合
		value = "gggrr298kkk";
		isMatch(entry, item + "-eq-" + value, false);
		isMatch(entry, item + "-lt-" + value, true);
		isMatch(entry, item + "-le-" + value, true);
		isMatch(entry, item + "-ge-" + value, false);
		isMatch(entry, item + "-gt-" + value, false);
		
		// List項目
		item = "testinfo.str.map_required.group_users_r";
		System.out.println(item + " = " + entry.getValue(item));
		value = "gggrrr219kk-3mrq";
		isMatch(entry, item + "-eq-" + value, true);

		// 比較対象が小さい場合
		value = "gggrrr218kk-mrq2";
		isMatch(entry, item + "-eq-" + value, false);
		isMatch(entry, item + "-lt-" + value, false);
		isMatch(entry, item + "-le-" + value, false);
		isMatch(entry, item + "-ge-" + value, true);
		isMatch(entry, item + "-gt-" + value, true);
		
		// 比較対象が大きい場合
		value = "gggrrr298kk-3mrq";
		isMatch(entry, item + "-eq-" + value, false);
		isMatch(entry, item + "-lt-" + value, true);
		isMatch(entry, item + "-le-" + value, true);
		isMatch(entry, item + "-ge-" + value, false);
		isMatch(entry, item + "-gt-" + value, false);

	}
	
	private void isMatch(EntryBase entry, String conditionStr, boolean isMatch) {
		System.out.println("[isMatch]" + conditionStr + " : " + isMatch);
		assertTrue(entry.isMatch(new Condition[]{new Condition(conditionStr)}) == isMatch);
	}
	
	private void checkCipher(EntryBase entry, String name, String val) {
		String source = (String)entry.getValue(name);
		System.out.println("[checkCipher]" + name + " : " + val + ", " + source);
		assertTrue(val.equals(source));
	}
	
	private void checkCipherList(EntryBase entry, String name, String[] val) {
		List<String> list = (List<String>)entry.getValue(name);
		for (int i = 0; i < val.length; i++) {
			String source = list.get(i);
			System.out.println("[checkCipherList]" + name + " : " + val[i] + ", " + source);
			assertTrue(val[i].equals(source));
		}
	}
	
	private String encrypt(FeedTemplateMapper encMapper, String str,
			CipherUtil cipherUtil) 
	throws GeneralSecurityException {
		EntryBase entry = EntryUtil.createEntry(encMapper);
		entry.id = "/1/new,1";
		entry.subtitle = str;
		cipherUtil.encrypt(entry);
		return entry.subtitle;
	}

	private FeedTemplateMapper createMapper(String templateFileStr, 
			String indexEncItemACLFileStr) 
	throws IOException, URISyntaxException, ParseException {
		String[] template = null;
		String[] rights = null;
		if (templateFileStr != null) {
			String[] tmpTemplate = readTemplate(templateFileStr);
			if (tmpTemplate != null && tmpTemplate.length > 0) {
				template = new String[tmpTemplate.length + 1];
				template[0] = "default{99999}";
				System.arraycopy(tmpTemplate, 0, template, 1, tmpTemplate.length);
				if (indexEncItemACLFileStr != null) {
					rights = readTemplate(indexEncItemACLFileStr);
				}
			}
		} else {
			template = new String[]{"_"};
		}
		FeedTemplateMapper mapper = new FeedTemplateMapper(template, rights, 
				999999999, "testsecret123");
		return mapper;
	}
	
	/*
	 * ファイルを読み、改行区切りのString配列にして返却する。
	 */
	private String[] readTemplate(String fileStr) 
	throws IOException, URISyntaxException {
		BufferedReader reader = getReader(fileStr);
		if (reader != null) {
			try {
				List<String> lines = new ArrayList<String>();
				String line;
				while ((line = reader.readLine()) != null) {
					if (!"".equals(line.trim())) {
						lines.add(line);
					}
				}
				if (lines.size() > 0) {
					return lines.toArray(new String[0]);
				}
				
			} finally {
				try {
					if (reader != null) {
						reader.close();
					}
				} catch (Exception e) {}	// Do nothing.
			}
		}
		return null;
	}
	
	private BufferedReader getReader(String filePath) 
	throws IOException, URISyntaxException {
		File file = new File(filePath);
		if (file.exists()) {
			return new BufferedReader(new InputStreamReader(
					new FileInputStream(file), ENCODING));
		}
		return null;
	}

	/**
	 * テンプレートのファイルパスを返却.
	 */
	private String getFilePathTemplate(String serviceName) 
	throws FileNotFoundException {
		String fileName = editFileNameTemplate(serviceName);
		return editInputFilePath(fileName);
	}
	
	/**
	 * テンプレートのIndex、暗号化、項目ACL設定のファイルパスを返却.
	 */
	private String getFilePathIndexEncItemACL(String serviceName) 
	throws FileNotFoundException {
		String fileName = editFileNameIndexEncItemACL(serviceName);
		return editInputFilePath(fileName);
	}

	/**
	 * データのファイルパスを返却.
	 */
	private String getFilePathData(String serviceName) 
	throws FileNotFoundException {
		String fileName = editFileNameData(serviceName);
		return editInputFilePath(fileName);
	}

	// template_{サービス名}.txt
	private String editFileNameTemplate(String serviceName) {
		StringBuilder sb = new StringBuilder();
		sb.append("template_");
		sb.append(serviceName);
		sb.append(".txt");
		return sb.toString();
	}

	// idx_enc_itemacl_{サービス名}.txt
	private String editFileNameIndexEncItemACL(String serviceName) {
		StringBuilder sb = new StringBuilder();
		sb.append("idx_enc_itemacl_");
		sb.append(serviceName);
		sb.append(".txt");
		return sb.toString();
	}

	// data_{サービス名}.json
	private String editFileNameData(String serviceName) {
		StringBuilder sb = new StringBuilder();
		sb.append("data_");
		sb.append(serviceName);
		sb.append(".json");
		return sb.toString();
	}

	private String editInputFilePath(String filename) {
		StringBuilder sb = new StringBuilder();
		sb.append(editInputFileDir());
		sb.append(File.separator);
		sb.append(filename);
		return sb.toString();
	}
	
	private String editInputFileDir() {
		StringBuilder sb = new StringBuilder();
		sb.append(FileUtil.getUserDir());
		sb.append(File.separator);
		sb.append("src");
		sb.append(File.separator);
		sb.append("test");
		sb.append(File.separator);
		sb.append("resources");
		return sb.toString();
	}
	
	/**
	 * XML文字列からFeedオブジェクトを作成
	 */
	private FeedBase createFeedFromJsonFile(FeedTemplateMapper mapper, String filePath) 
	throws IOException, URISyntaxException {
		BufferedReader reader = getReader(filePath);
		if (reader != null) {
			try {
				return (FeedBase)mapper.fromJSON(reader);
				
			} finally {
				try {
					reader.close();
				} catch (Exception e) {}	// Do nothing.
			}
		}
		return null;
	}

	private String readData(String serviceName) 
	throws IOException {
		String filePath = getFilePathData(serviceName);
		Reader reader = FileUtil.getReaderFromFile(filePath);
		return FileUtil.readString(reader);
	}

	private FeedBase createFeedFromJson(FeedTemplateMapper mapper, String str) {
		if (!StringUtils.isBlank(str)) {
			return (FeedBase)mapper.fromJSON(str);
		}
		return null;
	}

}
