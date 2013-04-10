package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.converters.reflection.PureJavaReflectionProvider;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;

import jp.sourceforge.reflex.core.ResourceMapper;
import model.Content;
import model.Login;
import model.Nsbean;
import model.RequestHdr;
import model2.Nsbean2;

public class ReflexCoreTest {

	public static void main(String[] args) {
/*
		// モデルビーンのパッケージ名を指定してmapperをnewする
		IResourceMapper mapper = new ResourceMapper("model");

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
		Content id2 = new Content();
		id2.content = "child's entity2";

		login.content = new ArrayList();
		login.content.add(id);
		login.content.add(id2);
		login._$$text = "this is a textnode"; // text node は _$$textに書く�ɏ���

		// XMLにシリアライズ
		toXML = mapper.toXML(login);
		System.out.println("\n【XML(0,unbound)　シリアライズテスト】:");
		System.out.println(toXML);

		// JSONにシリアライズ
		toJSON = mapper.toJSON(login);
		System.out.println("\n【JSON(0,unbound1)　シリアライズテスト】:");
		System.out.println(toJSON);
*/
		// 名前空間が付く場合はMapを指定する
		// 例） put("xml namespace","java packagename");

		Map nsmap = new HashMap();

		nsmap.put("model", "http://nstest"); // ""はデフォルトの名前空間
		nsmap.put("model2", "n2=http://nstest2"); // 左は名前空間を指定する（prefixではない）右はパッケージ名�

		IResourceMapper mapper = new ResourceMapper(nsmap); // mapをパラメータにしてMapperをnewする

//		ReflectionProvider rp = new PureJavaReflectionProvider();
//		mapper = new ResourceMapper(nsmap,rp); // mapをパラメータにしてMapperをnewする

		// 名前空間 nstestのビーン
		Nsbean nsbean = new Nsbean();
 
		// field名変換
		// 　　QNAMEの:は$に、-は__に変換される
		nsbean.minus__fld = " - ⇔ __ ";
		nsbean.colon$fld = " : ⇔ $ ";

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
		nsbean2.fld = "test fields";

		// 名前空間 nstestのビーンの子としてセット
		nsbean.nsbean3 = nsbean2;
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
