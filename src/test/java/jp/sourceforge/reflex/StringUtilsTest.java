package jp.sourceforge.reflex;

import jp.sourceforge.reflex.util.StringUtils;

public class StringUtilsTest {

	public static void main(String[] args) {
		
		String str = "   あいう  えお   ";
		String str2 = "　　　あいう　　えお　　　";
		String str3 = "  \"あいうえお\"  ";

		try {
			System.out.println("trimUni(str)  : '" + StringUtils.trimUni(str) + "'");
			System.out.println("trimUni(str2) : '" + StringUtils.trimUni(str2) + "'");
			System.out.println("trimUni(str3) : '" + StringUtils.trimUni(str3) + "'");

			System.out.println("trimDoubleQuotes(str3) : '" + StringUtils.trimDoubleQuotes(str3) + "'");

			long lnum = 8988888888888888889L;
			System.out.println("String.valueOf : " + String.valueOf(lnum));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
