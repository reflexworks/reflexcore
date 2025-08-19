package jp.sourceforge.reflex.test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import org.junit.Test;

import jp.sourceforge.reflex.util.NumberingUtil;

/**
 * NumberingUtil テスト
 */
public class NumberingUtilTest {

	@Test
	public void test() {
		try {
			int start = 1;
			int end = 10;
			int cnt = 10;

			testRandom(start, end, cnt);

			start = 1;
			end = Integer.MAX_VALUE;
			cnt = 2;

			testRandom(start, end, cnt);

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

			List<String> values = new ArrayList<>();
			values.add("aa");
			values.add("bb");
			values.add("cc");
			values.add("dd");
			values.add("ee");

			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));
			System.out.println("chooseOne : " + NumberingUtil.chooseOne(values));

		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	/**
	 * randomメソッドを指定回数繰り返し
	 * @param start start
	 * @param end end
	 * @param cnt 回数
	 */
	private void testRandom(int start, int end, int cnt) {
		System.out.println("numbered start=" + start + " end=" + end);

		for (int i = 0; i < cnt; i++) {
			int ran = NumberingUtil.random(start, end);
			System.out.println("numbered : " + ran);
			assertTrue(ran >= start && ran <= end);
		}
	}

}
