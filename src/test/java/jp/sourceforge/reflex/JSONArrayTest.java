package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.sourceforge.reflex.core.ResourceMapper;
import jp.sourceforge.reflex.exception.JSONException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import model.Array;
import model.Element;

/**
 * Unit test for simple App.
 */
public class JSONArrayTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public JSONArrayTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JSONArrayTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void test() {

		// モデルビーンのパッケージ名を指定してmapperをnewする
		Map nsmap = new HashMap();

		nsmap.put("model", ""); // ""はデフォルトの名前空間
		IResourceMapper mapper = new ResourceMapper(nsmap);

		// 配列
		List elementList = new ArrayList();
		Element element1 = new Element();
		element1._$$text = "XYZ\\<>'\"";
		elementList.add(element1);
		Element element2 = new Element();
		element2._$$text = "102";
		elementList.add(element2);
		Element element3 = new Element();
		element3._$$text = "103";
		elementList.add(element3);
		Element element4 = new Element();
		element4._$$text = "104";
		elementList.add(element4);
		Element element5 = new Element();
		element5._$$text = "105";
		elementList.add(element5);
		Element element6 = new Element();
		element6._$$text = "ABC";
		elementList.add(element6);

		Array array = new Array();
		array._element = elementList;

		// XMLにシリアライズ
		String toXML = mapper.toXML(array);
		System.out.println("\n【XML(array)　シリアライズテスト】:");
		System.out.println(toXML);

		// JSONにシリアライズ
		String toJSON = mapper.toJSON(array);
		System.out.println("\n【JSON(array)　シリアライズテスト】:");
		System.out.println(toJSON);

		try {
			// 試しにデシリアライズしてみる
			Array arrayFromXML = (Array) mapper.fromXML(toXML);
			toXML = mapper.toXML(arrayFromXML);
			System.out.println("\n上をデシリアライズ(XML):");
			System.out.println(toXML);

		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			// 試しにデシリアライズしてみる(エラーが出る）
			Array arrayFromJSON = (Array) mapper.fromJSON(toJSON);
			toJSON = mapper.toJSON(arrayFromJSON);
			System.out.println("\n上をデシリアライズ(JSON):");
			System.out.println(toJSON);
			/*
			 * // 順序を逆にする ReflexJSONTest me = new ReflexJSONTest(); List
			 * oppsiteElement = me.opposite(arrayFromJSON.element);
			 * arrayFromJSON.element = oppsiteElement; toJSON =
			 * mapper.toJSON(arrayFromJSON);
			 * System.out.println("\n上の順序を逆にしてデシリアライズ:");
			 * System.out.println(toJSON);
			 */

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}


}
