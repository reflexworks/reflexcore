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

/**
 * Unit test for simple App.
 */
public class WebXmlReadTest{

	/**
	 * Rigourous Test :-)
	 */
	public void testwebxmlread() {

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
