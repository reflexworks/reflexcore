package jp.sourceforge.reflex;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.Content;
import model.Element2;
import model.Type2;

import jp.sourceforge.reflex.util.FieldMapper;

/**
 * Unit test for simple App.
 */
public class FieldMapperCloneTest {

	/**
	 * Rigourous Test :-)
	 */
	public void testfieldmapperclone() {

		Type2 source = new Type2();

		// 値の設定
		source.type_str__ing = "文字";
		source.type_int = 1111;
		source.type_long = 2222222l;
		source.type_float = 3333.33f;
		source.type_double = 3333.33;
		source.type_date = new Date();
		source.type_Integer = 6666;
		source.type_Long = 7777777l;
		source.type_Float = 8888.88f;
		source.type_Double = 8888.88;

		source.type_str__ing_$attr1 = "属性１";
		source.type_str__ing_$attr2 = "属性２";
		source._$xml$lang = "ja";
		source.type_str__ing_$xml$lang = "jp";
		source.xml$atom = "間にコロン";
		source._$$text = "テキスト要素";
		source.setRevision(3);
		source.setDeleted(2);
		
		source.list = new ArrayList();
		source.list.add("チャプター１");
		source.list.add("チャプター２");
		source.list2 = new ArrayList();
		source.list2.add(new Element2("エレメント１"));
		source.list2.add(new Element2("エレメント２"));
		source.map = new HashMap();
		source.map.put("キー１", "値１");
		source.map.put("キー２", "値２");
		source.map2 = new HashMap();
		source.map2.put("キー１", new Element2("エレメント値１"));
		source.map2.put("キー２", new Element2("エレメント値２"));
		source.array_int = new int[2];
		source.array_int[0] = 9000;
		source.array_int[1] = 9001;
		source.content = new Content();
		source.content.content = "コンテント";
		
		try {
			System.out.println("初期ソース: " + source);
			
			FieldMapper fieldMapper = new FieldMapper(false);
			//fieldMapper.setValueExcludingMultiObj(source, target);
			//fieldMapper.setValue(source, target, false);
			Type2 cloneObj = (Type2)fieldMapper.clone(source);
			
			System.out.println("クローン　: " + cloneObj);
			
			//source.type_int = 11115;
			source._$$text = "変更テキスト要素";
			source.type_Integer = 66661;
			source.content.content = "変更コンテント";
			source.list.add("チャプター３");
			Element2 ele = (Element2)source.list2.get(1);
			ele._$$text = "変更エレメント２";
			source.list2.add(new Element2("エレメント３"));
			source.map.put("キー１", "値変更");
			source.array_int[1] = 90011;

			Element2 ele2 = (Element2)source.map2.get("キー１");
			ele2.set_$$text("エレメント値１変更");
			
			System.out.println("変更ソース: " + source);
			System.out.println("クローン　: " + cloneObj);

		} catch (Exception e) {
			e.printStackTrace();
		}


	}
}
