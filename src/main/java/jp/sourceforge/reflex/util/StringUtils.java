package jp.sourceforge.reflex.util;

public class StringUtils {
	
	/**
	 * nullの場合空文字を返却します.
	 * <p>
	 * nullでない場合は引数をそのまま返却します.
	 * </p>
	 * @param s 文字列
	 * @return nullの場合空文字、その他は引数そのまま
	 */
	public static String null2blank(String s) {
		if (s == null) {
			return "";
		}
		return s;
	}
	
	/**
	 * trim.
	 * <p>
	 * nullの場合空文字を返却します.
	 * </p>
	 * @param s 文字列
	 * @return 文字列をtrimした結果
	 */
	public static String trim(String s) {
		if (s == null) {
			return "";
		}
		return s.trim();
	}

	/**
	 * 全角・半角空白のtrim
	 * @param s 文字列
	 * @return 文字列をtrimした結果
	 */
    public static String trimUni(String s){
        int len = s.length();
        int st = 0;
        char[] val = s.toCharArray();

        while (st < len && (val[st] <= ' ' || val[st] == '　')) {
            st++;
        }
        while (st < len && (val[len - 1] <= ' ' || val[len - 1] == '　')) {
            len--;
        }
        
        if(st > 0 || len < s.length()) {
            return s.substring(st, len);
        }
        
        return s;
    }
    
    /**
     * 文字列をbooleanに変換します.
     * <p>
     * 文字列がnullの場合、falseを返却します.
     * </p>
     * @param str 文字列
     * @return true/false
     */
    public static boolean booleanValue(String str) {
    	return booleanValue(str, false);
    }
    
    /**
     * 文字列をbooleanに変換します.
     * <p>
     * 文字列がnullの場合、デフォルト値を返却します.
     * </p>
     * @param str 文字列
     * @param def デフォルト値
     * @return true/false
     */
    public static boolean booleanValue(String str, boolean def) {
		if (def) {
			// デフォルトはtrue
			if ("false".equalsIgnoreCase(str)) {
				return false;
			}
		} else {
			// デフォルトはfalse
			if ("true".equalsIgnoreCase(str)) {
				return true;
			}
		}
		return def;
	}
    
    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、0を返却します.
     * </p>
     * @param str 文字列
     * @return 数値
     */
    public static int intValue(String str) {
    	return intValue(str, 0);
    }

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、デフォルト値を返却します.
     * </p>
     * @param str 文字列
     * @param def デフォルト値
     * @return 数値
     */
    public static int intValue(String str, int def) {
		if (str != null) {
			try {
				return Integer.parseInt(str);
			} catch (NumberFormatException e) {}	// Do nothing.
		}
		return def;
	}

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、0を返却します.
     * </p>
     * @param str 文字列
     * @return 数値
     */
    public static long longValue(String str) {
    	return longValue(str, 0);
    }

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、デフォルト値を返却します.
     * </p>
     * @param str 文字列
     * @param def デフォルト値
     * @return 数値
     */
	public static long longValue(String str, long def) {
		if (str != null) {
			try {
				return Long.parseLong(str);
			} catch (NumberFormatException e) {}	// Do nothing.
		}
		return def;
	}

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、0を返却します.
     * </p>
     * @param str 文字列
     * @return 数値
     */
	public static float floatValue(String str) {
		return floatValue(str, 0);
	}

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、デフォルト値を返却します.
     * </p>
     * @param str 文字列
     * @param def デフォルト値
     * @return 数値
     */
	public static float floatValue(String str, float def) {
		if (str != null) {
			try {
				return Float.parseFloat(str);
			} catch (NumberFormatException e) {}	// Do nothing.
		}
		return def;
	}

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、0を返却します.
     * </p>
     * @param str 文字列
     * @return 数値
     */
	public static double doubleValue(String str) {
		return doubleValue(str, 0);
	}

    /**
     * 文字列を数値に変換します.
     * <p>
     * 文字列がnullの場合、デフォルト値を返却します.
     * </p>
     * @param str 文字列
     * @param def デフォルト値
     * @return 数値
     */
	public static double doubleValue(String str, double def) {
		if (str != null) {
			try {
				return Double.parseDouble(str);
			} catch (NumberFormatException e) {}
		}
		return def;
	}

	/**
	 * 文字列がnullまたは空文字かどうかチェックします.
	 * @param str 文字列
	 * @return 文字列がnullか空文字の場合true
	 */
	public static boolean isBlank(String str) {
		if (str == null || str.length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * 文字列がnullまたは空文字、スペースのみかどうかチェックします.
	 * @param str 文字列
	 * @return 文字列がnullか空文字かスペースのみの場合true
	 */
	public static boolean isBlankSpace(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 文字列がnullまたは空文字、スペースのみかどうかチェックします.
	 * <p>
	 * 全角スペースのみの場合もtrueを返却します.
	 * </p>
	 * @param str 文字列
	 * @return 文字列がnullか空文字かスペース(全角スペース含む)のみの場合true
	 */
	public static boolean isBlankSpaceUni(String str) {
		if (str == null || trimUni(str).length() == 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * ダブルクォーテーションが両端に付いている場合除去します.
	 * @param str 文字列
	 * @return 編集した文字列
	 */
	public static String trimDoubleQuotes(String str) {
		String ret = trim(str);
		if (ret.length() == 0) {
			return ret;
		}
		int start = 0;
		int end = ret.length();
		if (ret.startsWith("\"")) {
			start += 1;
		}
		if (ret.endsWith("\"")) {
			end -= 1;
		}
		return ret.substring(start, end);
	}

}
