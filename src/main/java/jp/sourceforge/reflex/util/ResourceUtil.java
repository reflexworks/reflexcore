package jp.sourceforge.reflex.util;

import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * クラスローダーからリソースを取得します。
 */
public class ResourceUtil {
	
	private static String PREFIX;
	static {
		String[] parts = ResourceUtil.class.getName().split("\\.");
		StringBuilder buf = new StringBuilder();
		for (int i = 1; i < parts.length; i++) {
			buf.append("../");
		}
		PREFIX = buf.toString();
	}
	
	/**
	 * クラスが配置されているディレクトリ直下にあるリソースのURLを取得します。
	 * @param name リソース名
	 * @return リソースのURL
	 */
	public static URL getResourceURL(String name) {
		URL url = ResourceUtil.class.getResource(PREFIX + name);
		if (url == null) {
			url = ResourceUtil.class.getResource(name);	// prefixなし
		}
		return url;
	}
	
	/**
	 * プロパティオブジェクトを取得します.
	 * プロパティファイルは、クラスが配置されているディレクトリ直下に配置してください。
	 * @param filename プロパティファイル名
	 * @return プロパティオブジェクト
	 */
	public static Properties getProperties(String filename) 
	throws IOException {
		URL url = getResourceURL(filename);
		return getProperties(url);
	}
	
	/**
	 * プロパティオブジェクトを取得します.
	 * @param url プロパティオブジェクトのURL
	 * @return プロパティオブジェクト
	 */
	public static Properties getProperties(URL url) 
	throws IOException {
		Properties prop = null;
		if (url != null) {
			InputStream in = url.openStream();
			if (in != null) {
				prop = new Properties();
				prop.load(in);
			}
		}
		return prop;
	}

}
