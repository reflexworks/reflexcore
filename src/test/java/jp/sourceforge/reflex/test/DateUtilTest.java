package jp.sourceforge.reflex.test;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import jp.sourceforge.reflex.util.DateUtil;

import org.junit.Test;

public class DateUtilTest {

	@Test
	public void test() 
	throws ParseException {

		int year = 2011;
		int month = 7;
		int day = 11;
		int hour = 15;
		int minute = 50;

		System.out.println("test date : " + year + "年 " + month + "月 " + day + "日 " + hour + "時 " + minute + "分");

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
		
		final String FORMAT_3 = "dd";
		String format3JST = DateUtil.getDateTimeFormat(someday, FORMAT_3);
		System.out.println("format(3) : " + format3JST);
		String format3Now = DateUtil.getDateTimeFormat(new Date(), FORMAT_3);
		System.out.println("format(3)Now : " + format3Now);
		
		final String FORMAT_4 = "SSS";
		String format4JST = DateUtil.getDateTimeFormat(someday, FORMAT_4);
		System.out.println("format(4) : " + format4JST);
		String format4Now = DateUtil.getDateTimeFormat(new Date(), FORMAT_4);
		System.out.println("format(4)Now : " + format4Now);
		
		// fromXML RXISO8601DateConverter
		final String FORMAT_5 = "yyyy-MM-dd'T'HH:mm:ssZZ";
		String format5JST = DateUtil.getDateTimeFormat(someday, FORMAT_5);
		System.out.println("format(5) : " + format5JST);
		String format5Now = DateUtil.getDateTimeFormat(new Date(), FORMAT_5);
		System.out.println("format(5)Now : " + format5Now);

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
		
		// タイムゾーンチェック
		String dateTimezoneStr = "2014-03-13T01:45:06-04:00";
		//String dateTimezoneStr = "2014-03-13T01:45:06+04:00";
		Date dateTimezone = DateUtil.getDate(dateTimezoneStr);
		System.out.println("TimezoneStr = " + dateTimezoneStr + ", TimezoneDate = " + dateTimezone);

		// getDate テスト
		System.out.println("----");
		
		String printFormat = "yyyy-MM-dd HH:mm:ss.SSSZ";
		
		System.out.println("ハイフン区切りフォーマット");
		String dateStr = "2016-01-12"; Date date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09-08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09-0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09-08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33.455"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33.455+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33.455+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12 09:21:33.455+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);

		System.out.println("スラッシュ区切りフォーマット");
		dateStr = "2016/01/12"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09-08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09-0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09-08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33.455"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33.455+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33.455+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12 09:21:33.455+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);

		System.out.println("ハイフン区切りフォーマット 日と時の間が'T'");
		dateStr = "2016-01-12T09"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09-08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09-0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09-08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33.455"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33.455+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33.455+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016-01-12T09:21:33.455+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);

		System.out.println("スラッシュ区切りフォーマット 日と時の間が'T'");
		dateStr = "2016/01/12T09"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09-08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09-0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09-08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33.455"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33.455+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33.455+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016/01/12T09:21:33.455+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);

		System.out.println("区切り文字なしフォーマット");
		dateStr = "20160112"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209-08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209-0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "2016011209-08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "201601120921"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "201601120921+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "201601120921+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "201601120921+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133455"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133455+08:00"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133455+0800"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		dateStr = "20160112092133455+08"; date = DateUtil.getDate(dateStr);
		System.out.println(DateUtil.getDateTimeFormat(date, printFormat) + " | " + dateStr);
		
		// マイクロ秒テスト
		System.out.println("[microsecond] " + DateUtil.getMicrosecondStr());
		System.out.println("[microsecond] " + DateUtil.getMicrosecondStr());
		System.out.println("[microsecond] " + DateUtil.getMicrosecondStr());
		
	}
}
