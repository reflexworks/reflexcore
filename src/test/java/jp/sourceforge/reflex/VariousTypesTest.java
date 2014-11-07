package jp.sourceforge.reflex;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.Type;

import jp.sourceforge.reflex.core.ResourceMapper;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class VariousTypesTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public VariousTypesTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(VariousTypesTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		// モデルビーンのパッケージ名を指定してmapperをnewする
		Map nsmap = new HashMap();

		nsmap.put("model", ""); // ""はデフォルトの名前空間
		IResourceMapper mapper = new ResourceMapper(nsmap);

		// 値の設定
		Type type = new Type();
		type._type_str__ing = "文字";
		type._type_int = 1111;
		type._type_long = 2222222l;
		type._type_float = 3333.33f;
		type._type_double = 3333.33;
		type._type_date = new Date();
		type._type_Integer = 6666;
		type._type_Long = 7777777l;
		type._type_Float = 8888.88f;
		type._type_Double = 8888.88;

		type._type_str__ing_$attr1 = "属性１";
		type._type_str__ing_$attr2 = "属性２";
		type._$xml$lang = "ja";
		type._xml$atom = "間にコロン";
		type._$$text = "テキスト要素\n改行\n改行２";
		type.setRevision(3);
		type.setDeleted(2);

		try {
			// XMLにシリアライズ
			String toXML = mapper.toXML(type);
			System.out.println("\n【XML(Type)　シリアライズテスト】:");
			System.out.println(toXML);

			// デシリアライズ
			Type type_fromXML = (Type)mapper.fromXML(toXML);

			toXML = mapper.toXML(type_fromXML);
			System.out.println("\n上をXMLにデシリアライズ:");
			System.out.println(toXML);

			// JSONにシリアライズ
			String toJSON = mapper.toJSON(type);
			System.out.println("\n【JSON(Type)　シリアライズテスト】:");
			System.out.println(toJSON);

			// デシリアライズ JSON deserializer is not working correctly. Please use the FeedTemplateMapper in ReflexMapper packages. 
//			Type type_fromJSON = (Type)mapper.fromJSON(toJSON);
//			toJSON = mapper.toJSON(type_fromJSON);
//			System.out.println("\n上をJSONにデシリアライズ:");
//			System.out.println(toJSON);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
