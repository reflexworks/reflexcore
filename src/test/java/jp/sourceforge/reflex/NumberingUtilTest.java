package jp.sourceforge.reflex;

import jp.sourceforge.reflex.util.NumberingUtil;

/**
 * Unit test for simple App.
 */
public class NumberingUtilTest {

	/**
	 * Rigourous Test :-)
	 */
	public void testnumbering() {

		int start = 1;
		int end = 10;
		
		try {
			int ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			
			ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);

			int len = 1;
			String ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 2;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 3;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 4;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 5;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 8;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 12;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 15;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 50;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 64;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 99;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 100;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

			len = 1000;
			ranStr = NumberingUtil.randomString(len);
			System.out.println("random string (len = " + len + ", String len = " + ranStr.length() + ") : " + ranStr);

		} catch (Throwable e) {
			e.printStackTrace();
		}


	}
}
