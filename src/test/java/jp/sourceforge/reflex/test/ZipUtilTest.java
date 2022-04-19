package jp.sourceforge.reflex.test;

import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;

import jp.sourceforge.reflex.util.ZipUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ZipUtilTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public ZipUtilTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(ZipUtilTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		try {
			String homeDir = System.getProperty("user.dir") + "/src/test/resources/zip/";

			String file1name = "helloworld97.xml";
			String file2name = "helloworld98.html";
			String file3name = "vteclogo.jpg";
			String file4name = "folder/vteclogo.jpg";	// zip内にディレクトリ作成
			String file5name = "folder1/folder2/folder3/vteclogo.jpg";	// zip内に複数階層ディレクトリ作成
			
			String zipFilename = "zipUtilTest.zip";

			System.out.println("zip start.");
			
			ZipUtil zipUtil = new ZipUtil();

			File file1 = new File(homeDir + file1name);
			File file2 = new File(homeDir + file2name);
			File file3 = new File(homeDir + file3name);
			File file4 = new File(homeDir + file3name);	// ファイルは3番目を使用
			File file5 = new File(homeDir + file3name);	// ファイルは3番目を使用
			
			Map fileMap = new HashMap();
			fileMap.put(file1name, file1);
			fileMap.put(file2name, file2);
			fileMap.put(file3name, file3);
			fileMap.put(file4name, file4);
			fileMap.put(file5name, file5);
			
			File zipFile = new File(homeDir + zipFilename);
			zipFile.createNewFile();
			FileOutputStream out = new FileOutputStream(zipFile);
			zipUtil.bringZip(out, fileMap);

			System.out.println("zip succeeded.");

			System.out.println("unzip start.");

			List entryList = zipUtil.getZipEntry(zipFile);
			File unzipDir = new File(homeDir + "unzip\\");
			unzipDir.mkdirs();

			if (entryList != null && entryList.size() > 0) {
				Map outMap = new HashMap();
				for (int i = 0; i < entryList.size(); i++) {
					ZipEntry entry = (ZipEntry)entryList.get(i);
					String name = entry.getName();
					File outFile = new File(homeDir + "unzip\\" + name);
					int idx = name.lastIndexOf("/");
					if (idx > -1) {
						File dir = new File(homeDir + "unzip\\" + name.substring(0, idx));
						dir.mkdirs();
					}
					outMap.put(name, outFile);
				}
				zipUtil.unzip(zipFile, outMap);
			}

			System.out.println("unzip succeeded.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
