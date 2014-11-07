package jp.sourceforge.reflex;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

import jp.reflexworks.servlet.model.webxml.Web__app;
import jp.sourceforge.reflex.core.ResourceMapper;
import jp.sourceforge.reflex.util.FileUtil;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class WebXmlReadTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public WebXmlReadTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(WebXmlReadTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		Reader reader = null;
		try {
			// モデルビーンのパッケージ名を指定してmapperをnewする
			Map nsmap = new HashMap();

			nsmap.put("jp.reflexworks.servlet.model.webxml", ""); // ""はデフォルトの名前空間
			IResourceMapper mapper = new ResourceMapper(nsmap);
			
			// Data XML
			String dataXml = "data/web.xml";
			String dataXmlFile = FileUtil.getResourceFilename(dataXml);
			reader = new InputStreamReader(new FileInputStream(dataXmlFile), "UTF-8");

			// デシリアライズ （_の対応が必要）
			Web__app webxml = (Web__app)mapper.fromXML(reader);

			System.out.println("fromXML:" + webxml);
			
			// シリアライズ
			String xmlStr = mapper.toXML(webxml);

			System.out.println("toXML:" + xmlStr);

			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
