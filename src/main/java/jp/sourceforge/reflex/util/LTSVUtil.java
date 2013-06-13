package jp.sourceforge.reflex.util;

import java.util.Map;

public class LTSVUtil {
	
	public static final String TAB = "\t";
	
	/**
	 * Mapの内容をLTSV形式にシリアライズします.
	 * @param map Map
	 * @return LTSV形式文字列. mapがnullまたは件数が0件の場合はnull.
	 */
	public static String serialize(Map<String, String> map) {
		if (map == null || map.size() == 0) {
			return null;
		}
		
		StringBuilder buf = new StringBuilder();
		boolean isFirst = true;
		for (Map.Entry<String, String> mapEntry : map.entrySet()) {
			if (isFirst) {
				isFirst = false;
			} else {
				buf.append(TAB);
			}
			buf.append(mapEntry.getKey());
			buf.append(":");
			buf.append(String.valueOf(mapEntry.getValue()));
		}
		return buf.toString();
	}

}
