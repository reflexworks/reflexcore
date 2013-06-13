package jp.sourceforge.reflex;

import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import jp.sourceforge.reflex.util.DateUtil;

public class DateUtilTest {

	public static void main(String[] args) {

		int year = 2011;
		int month = 7;
		int day = 11;
		int hour = 15;
		int minute = 50;

		System.out.println("test date : " + year + "年 " + month + "月 " + day + "日 " + hour + "時 " + minute + "分");

		try {
			Date someday = DateUtil.createDate(year, month, day, hour, minute);
			//Date someday = new Date();
			
			// フォーマット
			System.out.println("createDate : " + someday);

			String format1Def = DateUtil.getDateTime(someday);
			System.out.println("format(1) : " + format1Def);

			String format1GMT = DateUtil.getDateTime(someday, "GMT");
			System.out.println("format(1) : " + format1GMT);

			String format1Millisec = DateUtil.getDateTimeMillisec(someday, "JST");
			System.out.println("format(millisec) : " + format1Millisec);

			Date date1Def = DateUtil.getDate(format1Def);
			System.out.println("date(1, Def) : " + date1Def);
			
			Date date1GMT = DateUtil.getDate(format1GMT);
			System.out.println("date(1, GMT) : " + date1GMT);
			
			//final String FORMAT_2 = "yyyy/MM/dd HH:mm:ss:SSS Z z";
			final String FORMAT_2 = "EEE, dd MMM yyyy HH:mm:ss zzz";

			String format2Def = DateUtil.getDateTimeFormat(someday, FORMAT_2);
			System.out.println("format(2) : " + format2Def);
			String format2DefEn = DateUtil.getDateTimeFormat(someday, FORMAT_2, Locale.ENGLISH);
			System.out.println("format(2)English : " + format2DefEn);

			String format2GMT = DateUtil.getDateTimeFormat(someday, FORMAT_2, "GMT");
			System.out.println("format(2) : " + format2GMT);
			String format2GMTEn = DateUtil.getDateTimeFormat(someday, FORMAT_2, "GMT", Locale.ENGLISH);
			System.out.println("format(2)English : " + format2GMTEn);
			
			Date date2Def = DateUtil.getDate(format2Def, FORMAT_2);
			System.out.println("date(2, Def) : " + date2Def);
			
			Date date2GMT = DateUtil.getDate(format2GMT, FORMAT_2);
			System.out.println("date(2, GMT) : " + date2GMT);
			
			// 日付加算
			System.out.println("2年加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 2, 0, 0, 0, 0, 0, 0), FORMAT_2));
			System.out.println("2月加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 2, 0, 0, 0, 0, 0), FORMAT_2));
			System.out.println("2日加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 0, 2, 0, 0, 0, 0), FORMAT_2));
			System.out.println("2時間加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 0, 0, 2, 0, 0, 0), FORMAT_2));
			System.out.println("2分加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 0, 0, 0, 2, 0, 0), FORMAT_2));
			System.out.println("2秒加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 0, 0, 0, 0, 2, 0), FORMAT_2));
			System.out.println("2ミリ秒加算 : " + DateUtil.getDateTimeFormat(DateUtil.addTime(someday, 0, 0, 0, 0, 0, 0, 2), FORMAT_2));
			
			// 日時大小比較
			System.out.println("----");
			Date now = new Date();
			System.out.println("now : " + now);

			Date nowAfter5min = DateUtil.addTime(now, 0, 0, 0, 0, 5, 0, 0);
			System.out.println("nowAfter5min : " + nowAfter5min);
			Date nowBefore5min = DateUtil.addTime(now, 0, 0, 0, 0, -5, 0, 0);
			System.out.println("nowBefore5min : " + nowBefore5min);
			System.out.println("test dateはnow+5分より前か : " + date1Def.before(nowAfter5min));
			System.out.println("test dateはnow-5分より後か : " + date1Def.after(nowBefore5min));
			
			// 範囲比較
			Date now2 = new Date();
			String start = "19:30";
			String end = "19:40";
			//TimeZone timeZone = TimeZone.getTimeZone("GMT+09:00");
			TimeZone timeZone = TimeZone.getTimeZone("GMT+00:00");
			
			System.out.println("isRange = " + DateUtil.isRange(now2, start, end, timeZone));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
