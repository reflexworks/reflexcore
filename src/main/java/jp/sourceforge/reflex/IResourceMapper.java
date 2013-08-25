package jp.sourceforge.reflex;

import java.io.Reader;
import java.io.Writer;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

import org.json.JSONException;

/**
 * XML、JSONからのオブジェクトデシリアライズ、
 * またはオブジェクトからXML、JSONへのシリアライズ処理を行います
 */
public interface IResourceMapper {

	/**
	 * オブジェクトをJSON文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @return JSON文字列
	 */
	public String toJSON(Object entity);

	/**
	 * オブジェクトをJSON文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @param writer JSON文字列の出力先
	 */
	public void toJSON(Object entity, Writer writer);

	/**
	 * JSON文字列からオブジェクトを作成します。
	 * @param json JSON文字列
	 * @return オブジェクト
	 */
	public Object fromJSON(String json) throws JSONException;

	/**
	 * JSON文字列からオブジェクトを作成します。
	 * @param json JSON文字列の読み込み元
	 * @return オブジェクト
	 */
	public Object fromJSON(Reader json) throws JSONException;

	/**
	 * オブジェクトをXML文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @return XML文字列
	 */
	public String toXML(Object entity);

	/**
	 * オブジェクトをXML文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @param printns 名前空間を出力する場合trueを設定します。
	 * @return XML文字列
	 */
	public String toXML(Object entity, boolean printns);

	/**
	 * オブジェクトをXML文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @param writer XML文字列の出力先
	 */
	public void toXML(Object entity, Writer writer);

	/**
	 * オブジェクトをXML文字列にシリアライズします。
	 * @param entity オブジェクト
	 * @param writer XML文字列の出力先
	 * @param printns 名前空間を出力する場合trueを設定します。
	 */
	public void toXML(Object entity, Writer writer, boolean printns);

	/**
	 * XML文字列からオブジェクトを作成します。
	 * @param xml XML文字列
	 * @return オブジェクト
	 */
	public Object fromXML(String xml);

	/**
	 * XML文字列からオブジェクトを作成します。
	 * @param xml XML文字列の読み込み元
	 * @return オブジェクト
	 */
	public Object fromXML(Reader xml);

	/**
	 * オブジェクトをMessagePack形式にシリアライズします。
	 * @param entity オブジェクト
	 * @return MessagePack形式バイト配列
	 */
	public byte[] toMessagePack(Object entity) throws IOException;

	/**
	 * オブジェクトをMessagePack形式にシリアライズします。
	 * @param entity オブジェクト
	 * @param out MessagePack形式データの出力先
	 */
	public void toMessagePack(Object entity, OutputStream out) throws IOException;

	/**
	 * MessagePack形式からオブジェクトを作成します。
	 * @param msg MessagePack形式バイト配列
	 * @return オブジェクト
	 * @throws ClassNotFoundException 
	 */
	public Object fromMessagePack(byte[] msg) throws IOException, ClassNotFoundException;

	/**
	 * MessagePack形式からオブジェクトを作成します。
	 * @param msg MessagePack形式データの読み込み元
	 * @return オブジェクト
	 * @throws ClassNotFoundException 
	 */
	public Object fromMessagePack(InputStream msg) throws IOException, ClassNotFoundException;

	/**
	 * Array形式からオブジェクトを作成します。
	 * @param array
	 * @param isFeed
	 * @return
	 * @throws JSONException
	 */
	public Object fromArray(String array,boolean isFeed) throws JSONException;

	/**
	 * オブジェクトをArray形式にシリアライズします。
	 * @param msg
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public Object toArray(byte[] msg) throws IOException,ClassNotFoundException;

}
