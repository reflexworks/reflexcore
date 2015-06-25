package jp.sourceforge.reflex;

import java.util.ArrayList;

import model.Content;
import model.Content2;
import model.Content3;
import model.Login;
import model.RequestHdr;
import jp.sourceforge.reflex.core.JSONSerializer;
import jp.sourceforge.reflex.core.ResourceMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class XMLJSONSerializeTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public XMLJSONSerializeTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(XMLJSONSerializeTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		// モデルビーンのパッケージ名を指定してmapperをnewする
		IResourceMapper mapper = new ResourceMapper("model",new JSONSerializer(false,true));
		
		// 子要素が多重度０，１の例。この場合は普通にクラス名を使う
		Login login = new Login();
		login.requestHdr = new RequestHdr();

		login.requestHdr.clientID = "clientid";
		login.requestHdr.ver = "version";
		login.requestHdr.ver_$title = "xmlns1"; // 属性の指定は必ずStringで

		// XMLにシリアライズ
		String toXML = mapper.toXML(login);
		System.out.println("\n【XML(0,1)　シリアライズテスト】:");
		System.out.println(toXML);

		// JSONにシリアライズ
		String toJSON = mapper.toJSON(login);
		System.out.println("\n【JSON(0,1)　シリアライズテスト】:");
		System.out.println(toJSON);

		// 子要素が多重度０，∞で存在する場合でかつtextnodeを含む場合の例
		login = new Login();

		Content id = new Content();
		id.content = "child's entity1";
		id.content2 = new ArrayList();
		Content2 content2 = new Content2();
		content2.content2 = "1-1";
		content2.content3 = new ArrayList();
		Content3 content3 = new Content3();
		content3.content3 = "1-1-1";
		content2.content3.add(content3);
		content3 = new Content3();
		content3.content3 = "1-1-2";
		content2.content3.add(content3);	
		
		id.content2.add(content2);
		content2 = new Content2();
		content2.content2 = "1-2";
		content2.content3 = new ArrayList();
		content3 = new Content3();
		content3.content3 = "1-2-1";
		content2.content3.add(content3);
		content3 = new Content3();
		content3.content3 = "1-2-2";
		content2.content3.add(content3); 
		id.content2.add(content2);
		
		Content id2 = new Content();
		id2.content = "child's entity2";
		id2.content2 = new ArrayList();
		content2 = new Content2();
		content2.content2 = "2-1";
		id2.content2.add(content2);
		content2 = new Content2();
		content2.content2 = "2-2";
		id2.content2.add(content2);

		login.content = new ArrayList();
		login.content.add(id);
		login.content.add(id2);
		login._$$text = "this is a textnode"; // text node は _$$textに書く

		// XMLにシリアライズ
		toXML = mapper.toXML(login);
		System.out.println("\n【XML(0,unbound)　シリアライズテスト】:");
		System.out.println(toXML);

		// JSONにシリアライズ
		toJSON = mapper.toJSON(login);
		System.out.println("\n【JSON(0,unbound1)　シリアライズテスト】:");
		System.out.println(toJSON);


	}
}
