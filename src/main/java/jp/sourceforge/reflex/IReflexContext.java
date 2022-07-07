package jp.sourceforge.reflex;

import java.io.IOException;

/**
 * ReflexContextインターフェース
 */
public interface IReflexContext {

	/**
	 *  /_html 配下のコンテンツ取得.
	 * @param requestUri URI
	 * @return コンテンツ
	 */
	public byte[] getHtmlContent(String requestUri) throws IOException, IReflexException;

}
