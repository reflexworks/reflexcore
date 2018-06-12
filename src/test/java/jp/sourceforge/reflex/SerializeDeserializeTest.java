package jp.sourceforge.reflex;

import java.util.HashMap;
import java.util.Map;

import model.Nsbean;
import model2.Nsbean2;

import jp.sourceforge.reflex.core.ResourceMapper;

/**
 * Unit test for simple App.
 */
public class SerializeDeserializeTest {

	/**
	 * Rigourous Test :-)
	 */
	public void testserializedeserialize() {

		// 名前空間が付く場合はMapを指定する
		// 例） put("xml namespace","java packagename");

		Map nsmap = new HashMap();

		nsmap.put("model", "http://nstest"); // ""はデフォルトの名前空間
		nsmap.put("model2", "n2=http://nstest2"); // 左は名前空間を指定する（prefixではない）右はパッケージ名�

		IResourceMapper mapper = new ResourceMapper(nsmap); // mapをパラメータにしてMapperをnewする

		// 名前空間 nstestのビーン
		Nsbean nsbean = new Nsbean();
 
		// field名変換
		// 　　QNAMEの:は$に、-は__に変換される
		nsbean._minus__fld = " - ⇔ __ ";
		nsbean._colon$fld = " : ⇔ $ ";

		// XMLにシリアライズ
		String toXML = mapper.toXML(nsbean);
		System.out.println("\n【XML名前空間＆field変換　シリアライズテスト】:");
		System.out.println("\nNamespace:");
		System.out.println(toXML);

		// 試しにデシリアリズしてみる
		Nsbean nsbeannew = (Nsbean) mapper.fromXML(toXML);

		toXML = mapper.toXML(nsbeannew);
		System.out.println("\n上をデシリアライズ:");
		System.out.println(toXML);

		// JSONにシリアライズ
		String toJSON = mapper.toJSON(nsbeannew);
		System.out.println("\n【JSON(nsbeannew)　シリアライズテスト】:");
		System.out.println(toJSON);

		// 名前空間 nstest2のビーンを
		Nsbean2 nsbean2 = new Nsbean2();
		nsbean2._fld = "test fields";

		// 名前空間 nstestのビーンの子としてセット
		nsbean._nsbean3 = nsbean2;
		nsbean._$xmlns$atr1 = "nstest2"; // 親に子の名前空間をつけてprefixにできるかどうかのテスト

		// XMLにシリアライズ
		toXML = mapper.toXML(nsbean);
		System.out.println("\n【名前空間の異なるnstest2を子要素として持つ。かつprefixは親で宣言】:");
		System.out.println(toXML);

		// 試しにデシリアライズしてみる
		Nsbean nsbeannew2 = (Nsbean) mapper.fromXML(toXML);

		toXML = mapper.toXML(nsbeannew2);
		System.out.println("\n上をデシリアライズ:");
		System.out.println(toXML);

		// JSONにシリアライズ
		toJSON = mapper.toJSON(nsbean);
		System.out.println("\n【JSON(nsbean)　シリアライズテスト】:");
		System.out.println(toJSON);


	}
}
