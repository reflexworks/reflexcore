package jp.sourceforge.reflex.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;

/**
 * 日付・時刻の編集クラス
 */
public class DateUtil {

	public static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 指定されたパラメータより日付を作成します
	 * @param year
	 * @param month
	 * @param date
	 * @return Date
	 */
	public static Date createDate(int year, int month, int date) {
		return createDate(year, month, date, 0, 0, 0);
	}

	/**
	 * 指定されたパラメータより日時を作成します
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @return Date
	 */
	public static Date createDate(int year, int month, int date, int hour, int minute) {
		return createDate(year, month, date, hour, minute, 0);
	}

	/**
	 * 指定されたパラメータより日時を作成します
	 * @param year
	 * @param month
	 * @param date
	 * @param hour
	 * @param minute
	 * @param second
	 * @return Date
	 */
	public static Date createDate(int year, int month, int date, int hour, int minute, int second) {
		int month2 = month - 1;	// Calendarクラスのバグ？

		Calendar calendar = Calendar.getInstance();
		calendar.set(year,month2, date, hour, minute, second);
		return calendar.getTime();
	}

	/**
	 * dateを"yyyy-MM-dd'T'HH:mm:ss+99:99"形式の文字列に変換します
	 * @param date
	 * @return dateの文字列
	 */
	public static String getDateTime(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		String datestring = format.format(date);
		format = new SimpleDateFormat("Z");
		String zone = format.format(date);
		datestring += zone.substring(0, 3) + ":" + zone.substring(3, 5);
		return datestring;
	}

	/**
	 * dateを"yyyy-MM-dd'T'HH:mm:ss.SSS+99:99"形式の文字列に変換します
	 * @param date
	 * @param id TimeZoneのID
	 * @return dateの文字列
	 */
	public static String getDateTimeMillisec(Date date, String id) {
		return getDateTime(date, id, "yyyy-MM-dd'T'HH:mm:ss.SSS");
	}
	
	/**
	 * dateを"yyyy-MM-dd'T'HH:mm:ss+99:99"形式の文字列に変換します
	 * @param date
	 * @param id TimeZoneのID
	 * @return dateの文字列
	 */
	public static String getDateTime(Date date, String id) {
		return getDateTime(date, id, "yyyy-MM-dd'T'HH:mm:ss");
	}
		
	/**
	 * dateを、指定されたFormat + "+99:99"形式の文字列に変換します
	 * @param date
	 * @param id TimeZoneのID
	 * @return dateの文字列
	 */
	public static String getDateTime(Date date, String id, String dateTimeFormat) {
		StringBuffer dateString = new StringBuffer();
		TimeZone timeZone = null;
		if (id != null) {
			timeZone = TimeZone.getTimeZone(id);
		}
		SimpleDateFormat format = new SimpleDateFormat(dateTimeFormat);
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		dateString.append(format.format(date));

		format = new SimpleDateFormat("Z");
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		String zone = format.format(date);

		dateString.append(zone.substring(0, 3));
		dateString.append(":");
		dateString.append(zone.substring(3, 5));

		return dateString.toString();
	}

	/**
	 * dateを文字列に変換します
	 * @param date
	 * @return dateの文字列
	 */
	public static String getDateTimeFormat(Date date) {
		return getDateTimeFormat(date, null);
	}

	/**
	 * dateを文字列に変換します
	 * @param date
	 * @param pattern フォーマットパターン
	 * @return dateの文字列
	 */
	public static String getDateTimeFormat(Date date, String pattern) {
		return getDateTimeFormat(date, pattern, null, null);
	}

	/**
	 * dateを文字列に変換します
	 * @param date
	 * @param pattern フォーマットパターン
	 * @param locale ロケール
	 * @return dateの文字列
	 */
	public static String getDateTimeFormat(Date date, String pattern, Locale locale) {
		return getDateTimeFormat(date, pattern, null, locale);
	}

	/**
	 * dateを文字列に変換します
	 * @param date
	 * @param pattern フォーマットパターン
	 * @param id TimeZoneのID
	 * @return dateの文字列
	 */
	public static String getDateTimeFormat(Date date, String pattern, String id) {
		return getDateTimeFormat(date, pattern, id, null);
	}
		
	/**
	 * dateを文字列に変換します
	 * @param date
	 * @param pattern フォーマットパターン
	 * @param id TimeZoneのID
	 * @param locale ロケール
	 * @return dateの文字列
	 */
	public static String getDateTimeFormat(Date date, String pattern, String id, Locale locale) {
		String patternStr = pattern;
		if (patternStr == null) {
			patternStr = FORMAT_PATTERN;
		}
		SimpleDateFormat format = null;
		if (locale != null) {
			format = new SimpleDateFormat(patternStr, locale);
		} else {
			format = new SimpleDateFormat(patternStr);
		}
		if (id != null) {
			format.setTimeZone(TimeZone.getTimeZone(id));
		}
		return format.format(date);
	}

	/**
	 * "yyyy-MM-dd'T'HH:mm:ss+99:99"形式の日付文字列をDateに変換します
	 * @param dateStr 日付文字列
	 * @return date
	 */
	public static Date getDate(String dateStr) throws ParseException {
		return getDate(dateStr, (TimeZone)null);
	}

	/**
	 * "yyyy-MM-dd'T'HH:mm:ss+99:99"形式の日付文字列をDateに変換します
	 * @param dateStr 日付文字列
	 * @param timeZone タイムゾーン
	 * @return date
	 */
	public static Date getDate(String dateStr, TimeZone timeZone) throws ParseException {
		String targetStr = null;
		int idx = dateStr.lastIndexOf(":");
		if (idx == -1 || idx + 1 >= dateStr.length()) {
			throw new ParseException("The form at the date is not \"yyyy-MM-dd'T'HH:mm:ss+99:99\".", 0);
		} else {
			targetStr = dateStr.substring(0, idx) + dateStr.substring(idx + 1);
		}

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		return format.parse(targetStr);
	}
	
	/**
	 * 指定されたパターンの日付文字列をDateに変換します
	 * @param dateStr 日付文字列
	 * @param pattern 文字列のパターン
	 * @return date
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		return getDate(dateStr, pattern, (TimeZone)null);
	}

	/**
	 * 指定されたパターンの日付文字列をDateに変換します
	 * @param dateStr 日付文字列
	 * @param pattern 文字列のパターン
	 * @param timeZone タイムゾーン
	 * @return date
	 */
	public static Date getDate(String dateStr, String pattern, TimeZone timeZone) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		if (timeZone != null) {
			format.setTimeZone(timeZone);
		}
		return format.parse(dateStr);
	}
	
	/**
	 * 日付計算.
	 * <p>
	 * パラメータのdateに、指定された日時を加算します。<br>
	 * 減算する場合はマイナス値を設定してください。
	 * </p>
	 * @param date 計算対象日時
	 * @param year 加算する年
	 * @param month 加算する月
	 * @param day 加算する日
	 * @param hour 加算する時間
	 * @param minute 加算する分
	 * @param second 加算する秒
	 * @param millisecond 加算するミリ秒
	 * @return 計算後の日時
	 */
	public static Date addTime(Date date, int year, int month, int day, 
			int hour, int minute, int second, int millisecond) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		if (year != 0) {
			calendar.add(Calendar.YEAR, year);
		}
		if (month != 0) {
			calendar.add(Calendar.MONTH, month);
		}
		if (day != 0) {
			calendar.add(Calendar.DATE, day);
		}
		if (hour != 0) {
			calendar.add(Calendar.HOUR, hour);
		}
		if (minute != 0) {
			calendar.add(Calendar.MINUTE, minute);
		}
		if (second != 0) {
			calendar.add(Calendar.SECOND, second);
		}
		if (millisecond != 0) {
			calendar.add(Calendar.MILLISECOND, millisecond);
		}
		
		return calendar.getTime();
	}
}
