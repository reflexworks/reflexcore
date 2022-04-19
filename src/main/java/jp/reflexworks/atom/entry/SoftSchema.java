package jp.reflexworks.atom.entry;

import java.util.List;

import jp.reflexworks.atom.mapper.ConditionContext;
import jp.reflexworks.atom.mapper.CipherContext;
import jp.reflexworks.atom.mapper.MaskpropContext;
import jp.reflexworks.atom.mapper.SizeContext;

public interface SoftSchema {
	
	/**
	 * オブジェクトから項目の値を取得
	 * 
	 * @param fldname
	 * @return 項目の値
	 */
	public Object getValue(String fldname);
	
	/**
	 * 暗号化対象の項目を暗号化
	 * 
	 * @param context
	 */
	public void encrypt(CipherContext context);
	
	/**
	 * 暗号化対象の項目を複合
	 * 
	 * @param context
	 */
	public void decrypt(CipherContext context);
	
	/**
	 * 検索条件に合致するか調べる
	 * 
	 * @param context
	 */
	public void isMatch(ConditionContext context);
	
	/**
	 * 項目のバリデーションを行う
	 * 
	 * @param uid
	 * @param groups
	 * @param myself
	 * @return validであればtrue
	 * @throws java.text.ParseException
	 */
	public boolean validate(String uid, List<String> groups, String myself) 
			throws java.text.ParseException;
	
	/**
	 * 項目の値をマスクする
	 * 
	 * @param context
	 */
	public void maskprop(MaskpropContext context);

	/**
	 * Entryのサイズを計算する
	 * 
	 * @param context
	 */
	public void getsize(SizeContext context);

}
