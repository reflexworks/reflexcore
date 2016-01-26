package jp.sourceforge.reflex.util;

import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日付・時刻の編集クラス
 */
public class DateUtil {

	public static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	// GMT
	public static final String GMT = "GMT";
	// タイムゾーン(+00:00)の正規表現
	public static final String REGEX_TIMEZONE = "[\\+|\\-]([0-1][0-9]|[2][0-3]):[0-5][0-9]";
	// 時間(HH:mm)の正規表現
	public static final String REGEX_HHMM = "([0-1][0-9]|[2][0-3]):[0-5][0-9]";
	
	// 時間(HH:mm)のPattern
	public static final Pattern PATTERN_HHMM = Pattern.compile(REGEX_HHMM);

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
		StringBuilder dateString = new StringBuilder();
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
	 * いろんな形式の日時文字列をDateオブジェクトに変換します.
	 * <p>
	 * 対応するフォーマット
	 * <ol>
	 *   <li>yyyy-MM-dd</li>
	 *   <li>yyyy-MM-dd HH</li>
	 *   <li>yyyy-MM-dd HH:mm</li>
	 *   <li>yyyy-MM-dd HH:mm:ss</li>
	 *   <li>yyyy-MM-dd HH:mm:ss.SSS</li>
	 *   <li>上記の"-"を"/"にしたもの</li>
	 *   <li>上記の" "を"T"にしたもの</li>
	 *   <li>yyyyMMdd</li>
	 *   <li>yyyyMMddHH</li>
	 *   <li>yyyyMMddHHmm</li>
	 *   <li>yyyyMMddHHmmss</li>
	 *   <li>yyyyMMddHHmmssSSS</li>
	 *   <li>上記の各フォーマットについて、末尾にタイムゾーン([ISO 8601] +99:99、+9999、+99)を加えたもの</li>
	 * </ol>
	 * </p>
	 * @param dateStr 日付文字列
	 * @return Dateオブジェクト
	 * @throws ParseException
	 */
	public static Date getDate(String dateStr)
	throws ParseException {
		if (StringUtils.isBlank(dateStr)) {
			return null;
		}
		dateStr = dateStr.trim();
		int len = dateStr.length();
		if (len < 8) {
			throw new ParseException("Length is not enough. " + dateStr, 0);
		}
		boolean hasPartition = false;
		StringBuilder format = new StringBuilder();
		// 年月日
		format.append("yyyy");
		String delimiter = dateStr.substring(4, 5);
		if ("-".equals(delimiter)) {
			format.append("-MM-dd");
			hasPartition = true;
		} else {
			if ("/".equals(delimiter)) {
				format.append("/MM/dd");
				hasPartition = true;
			} else {
				format.append("MMdd");
			}
		}
		
		boolean isFinish = false;
		int formatLen = format.length();
		if (len <= formatLen) {
			isFinish = true;
		} else {
			// タイムゾーンチェック
			if (checkAndAppendTimeZone(dateStr, formatLen, format)) {
				isFinish = true;
			}
		}
		
		int formatLenMinus = 0;
		if (!isFinish) {
			// 区切り文字付きの場合、スペースかTか判定する。
			if (hasPartition) {
				delimiter = dateStr.substring(formatLen, formatLen + 1);
				if (" ".equals(delimiter)) {
					format.append(" ");
				} else {
					format.append("'T'");
					formatLenMinus = 2;
				}
			}
			
			// 時
			format.append("HH");
			formatLen = format.length() - formatLenMinus;
			if (len <= formatLen) {
				isFinish = true;
			} else {
				if (checkAndAppendTimeZone(dateStr, formatLen, format)) {
					isFinish = true;
				}
			}
		}
		
		if (!isFinish) {
			if (hasPartition) {
				format.append(":");
			}
			// 分
			format.append("mm");
			formatLen = format.length() - formatLenMinus;
			if (len <= formatLen) {
				isFinish = true;
			} else {
				if (checkAndAppendTimeZone(dateStr, formatLen, format)) {
					isFinish = true;
				}
			}
		}
		
		if (!isFinish) {
			if (hasPartition) {
				format.append(":");
			}
			// 秒
			format.append("ss");
			formatLen = format.length() - formatLenMinus;
			if (len <= formatLen) {
				isFinish = true;
			} else {
				if (checkAndAppendTimeZone(dateStr, formatLen, format)) {
					isFinish = true;
				}
			}
		}
		
		if (!isFinish) {
			if (hasPartition) {
				format.append(".");
			}
			// ミリ秒
			format.append("SSS");
			formatLen = format.length() - formatLenMinus;
			if (len > formatLen) {
				checkAndAppendTimeZone(dateStr, formatLen, format);
			}
		}
		
		return getDate(dateStr, format.toString());
	}
	
	/**
	 * 指定されたIndexにタイムゾーンが設定されている場合、StringBuilderにタイムゾーンを追加し
	 * trueを返却します。
	 */
	private static boolean checkAndAppendTimeZone(String dateStr, int idx,
			StringBuilder format) {
		if (dateStr == null || dateStr.length() <= idx) {
			return false;
		}
		String sign = dateStr.substring(idx, idx + 1);
		if ("+".equals(sign) || "-".equals(sign)) {
			format.append("X");	// ISO 8601タイムゾーン
			return true;
		}
		return false;
	}
	
	/**
	 * 指定されたパターンの日付文字列をDateに変換します
	 * @param dateStr 日付文字列
	 * @param pattern 文字列のパターン
	 * @return date
	 */
	public static Date getDate(String dateStr, String pattern) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
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
			calendar.add(Calendar.HOUR_OF_DAY, hour);
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
	
	/**
	 * 時間の範囲チェック.
	 * <p>
	 * 指定されたdateがstart-endの間かどうかチェックします。
	 * </p>
	 * @param date チェックしたい時刻
	 * @param start 開始時刻。HH:mm形式。
	 * @param end 終了時刻。HH:mm形式。
	 * @param timeZone タイムゾーン。nullの場合はデフォルトのタイムゾーン。
	 * @return 指定された範囲内であればtrueを返却。
	 */
	public static boolean isRange(Date date, String start, String end, TimeZone timeZone) {
		// 入力チェック
		if (date == null || start == null || end == null) {
			return false;
		}
		Matcher matcher = PATTERN_HHMM.matcher(start);
		if (!matcher.matches()) {
			return false;
		}
		matcher = PATTERN_HHMM.matcher(end);
		if (!matcher.matches()) {
			return false;
		}
		if (start.equals(end)) {
			return false;
		}
		if (timeZone == null) {
			timeZone = TimeZone.getDefault();
		}
		
		Calendar cal = Calendar.getInstance(timeZone);
		cal.setTime(date);

		boolean isAsc = true;
		if (start.compareTo(end) > 0) {
			isAsc = false;
		}
		
		String[] startParts = start.split(":");
		int startHour = intValue(startParts[0]);
		int startMinute = intValue(startParts[1]);
		String[] endParts = end.split(":");
		int endHour = intValue(endParts[0]);
		int endMinute = intValue(endParts[1]);
		
		// nowと当日のstartを比較。
		cal.set(Calendar.HOUR_OF_DAY, startHour);
		cal.set(Calendar.MINUTE, startMinute);
		Date startToday = cal.getTime();

		if (!startToday.after(date)) {
			// now >= start の場合
			cal.setTime(date);
			if (isAsc) {
				// (start < end)であれば、nowと当日のendを比較。
				cal.set(Calendar.HOUR_OF_DAY, endHour);
				cal.set(Calendar.MINUTE, endMinute);
			} else {
				// (start > end)であれば、nowと翌日のendを比較。
				cal.set(Calendar.DATE, 1);
				cal.set(Calendar.HOUR_OF_DAY, endHour);
				cal.set(Calendar.MINUTE, endMinute);
			}
			Date endDate = cal.getTime();
			if (!endDate.before(date)) {
				// now <= endであれば範囲内。
				return true;
			}

		} else {
			// now < start の場合
			if (!isAsc) {
				// (start > end)であれば、nowと前日のstartは now < start なので、
				// nowと当日のendを比較。
				cal.setTime(date);
				cal.set(Calendar.HOUR_OF_DAY, endHour);
				cal.set(Calendar.MINUTE, endMinute);
				Date endDate = cal.getTime();
				if (!endDate.before(date)) {
					// now <= endであれば範囲内。
					return true;
				}
			}
		}

		return false;
	}

	/*
	 * 先頭の0を除去してint変換
	 */
	private static int intValue(String str) {
		if (str == null) {
			return 0;
		}
		if (str.length() > 1 && str.startsWith("0")) {
			str = str.substring(1);
		}
		try {
			return Integer.parseInt(str);
		} catch (NumberFormatException e) {}	// Do nothing.
		return 0;
	}
	
}
