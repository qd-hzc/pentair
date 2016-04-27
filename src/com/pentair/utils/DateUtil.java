package com.pentair.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期时间相关的辅助处理工具类: JDK的SimpleDateFormat不是thread-safe的,
 * 可以使用Jakarta中DateFormatUtils、FastDateFormat。
 * 这两个类的区别是如果你自定义显示的格式就使用FastDateFormat，否则可以使用DateFormatUtils。
 */
public final class DateUtil {
	// public static final String ISO_DATE_FORMAT = "yyyyMMdd";
	public static final Date ZERO_DATE = new Date(0);
	public static final Date HITHERTO = new Date(-1000L);// 必须为1000的整数倍，数据库只能存放到秒

	private static final SynchronizedSimpleDateFormat MILLIS_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyyMMddHHmmss");

	private static final SynchronizedSimpleDateFormat YEAR_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyy");
	private static final SynchronizedSimpleDateFormat MONTH_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyy-MM");
	private static final SynchronizedSimpleDateFormat DATE_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyy-MM-dd");
	private static final SynchronizedSimpleDateFormat DATE_TIME_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");// ISO_DATE_FORMAT
	private static final SynchronizedSimpleDateFormat TIME_FORMATER = new SynchronizedSimpleDateFormat(
			"HH:mm:ss");
	private static final SynchronizedSimpleDateFormat DATE_TIME_MINUTE_FORMATER = new SynchronizedSimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	// formatDateToString

	private static final SynchronizedSimpleDateFormat[] DEFAULT_PATTERNS = new SynchronizedSimpleDateFormat[] {
			DATE_FORMATER, DATE_TIME_FORMATER, MONTH_FORMATER,
			DATE_TIME_MINUTE_FORMATER, TIME_FORMATER,
			new SynchronizedSimpleDateFormat("yyyy/MM/dd"),
			new SynchronizedSimpleDateFormat("yyyy.MM.dd"),
			new SynchronizedSimpleDateFormat("yyyy/MM"),
			new SynchronizedSimpleDateFormat("yyyy.MM") };

	/**
	 * 计算指定日期相隔N天的日期
	 * 
	 * @param srcdate
	 * @param days
	 * @return
	 */
	public static Date addDate(Date srcdate, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(srcdate);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}

	/**
	 * 将Date格式化为毫秒数字符串 datetime to long sample: 19740713130208
	 * 
	 * @return a Date encoded as a String.
	 */
	public static final String dateToMillis(Date date) {
		return MILLIS_FORMATER.format(date);
	}

	/**
	 * 将Date转换为Calendar
	 * 
	 * @param date
	 * @return
	 */
	public static final Calendar dateToCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	/**
	 * 将表示时间的毫秒数字符串，转化为Date
	 */
	public static final Date millisToDate(String millis) {
		return parseDate(MILLIS_FORMATER, millis);
	}

	/**
	 * 将表示时间的毫秒数字符串，转化为Calendar
	 */
	public static final Calendar millisToCalendar(String millis) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(millisToDate(millis));
		return cal;
	}

	/**
	 * Parses the specified date with the specified pattern, if it fails it uses
	 * the predefined patterns of this object.
	 * 
	 * @param text
	 *            java.lang.String A string representing the date to be parsed.
	 */
	/**
	 * This method generates a string representation of a date/time in the
	 * format you specify on input
	 * 
	 * @param aMask
	 *            the date pattern the string is in
	 * @param strDate
	 *            a string representation of a date
	 * @return a converted Date object
	 * @see java.text.SimpleDateFormat
	 * @throws ParseException
	 */
	public static final Date parseDate(SynchronizedSimpleDateFormat df,
			String strDate) {
		// refactor to regexp strDate.split(" ./-:\\");
		if (strDate == null || strDate.trim().length() == 0) {
			return null;
		}
		try {
			return df.parse(strDate);
		} catch (ParseException pe) {
			pe.printStackTrace();
			// throw new IllegalArgumentException("Unable to parse the date: " +
			// strDate);
			// "Can't parse " + strDate + " using " + df
		}
		return null;// new Date(0)
	}

	/**
	 * 自动判断字符串格式，将其转换为Date
	 * 
	 * @param strDate
	 * @return
	 */
	public static final Date parseDate(String strDate) {
		if (strDate == null || "".equals(strDate)) {
			return new Date();
		}
		Date date = null;
		for (int i = 0; i < DEFAULT_PATTERNS.length; i++) {
			date = parseDate(DEFAULT_PATTERNS[i], strDate);
			if (date != null)
				break;
		}
		return date;
		// throw new IllegalArgumentException("The field " + strDate +
		// " can not parse");
	}

	/**
	 * 日期格式化为一字符串
	 * 
	 * @param date
	 *            被格式化的日期字符串
	 * @return 格式化后的字符串
	 */
	public static String format(Date date, String pattern) {
		return new SimpleDateFormat(pattern).format(date);
	}

	public static String formatDate(Date date) {
		return DATE_FORMATER.format(date);
	}

	public static String formatDateTime(Date date) {
		return DATE_TIME_FORMATER.format(date);
	}

	public static String formatTime(Date date) {
		return TIME_FORMATER.format(date);
	}

	public static String formatYear(Date date) {
		return YEAR_FORMATER.format(date);
	}

	public static String formatMonth(Date date) {
		return MONTH_FORMATER.format(date);
	}

	public static String formatMinute(Date date) {
		return DATE_TIME_MINUTE_FORMATER.format(date);
	}

	public static String getNowDate() {// getNowDate currentDate//getCurrentTime
										// getToday
		return DATE_FORMATER.format(new Date());
	}

	public static String getNowDataTime() {
		return DATE_TIME_FORMATER.format(new Date());
	}

	public static String getNowTime() {
		return TIME_FORMATER.format(new Date());
	}

	public static String getNowMillis() {
		return MILLIS_FORMATER.format(new Date());
	}

	/**
	 * 获取当前星期（中国, 如：星期日,星期一,星期二）
	 */
	public static String getWeek() {
		Calendar c = GregorianCalendar.getInstance();
		c.setFirstDayOfWeek(Calendar.SUNDAY);
		String[] s = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		return s[c.get(Calendar.DAY_OF_WEEK) - 1];
	}

	public static class SynchronizedSimpleDateFormat {
		private final DateFormat internalDateFormat;

		public SynchronizedSimpleDateFormat(String pattern, Locale locale) {
			super();
			internalDateFormat = new SimpleDateFormat(pattern, locale);
		}

		public SynchronizedSimpleDateFormat(String pattern) {
			super();
			internalDateFormat = new SimpleDateFormat(pattern);
		}

		protected SynchronizedSimpleDateFormat(DateFormat theDateFormat) {
			super();
			internalDateFormat = theDateFormat;
		}

		public String format(Date d) {
			synchronized (internalDateFormat) {
				return internalDateFormat.format(d);
			}
		}

		public Date parse(String source) throws ParseException {
			synchronized (internalDateFormat) {
				return internalDateFormat.parse(source);
			}
		}

		public void setTimeZone(TimeZone zone) {
			synchronized (internalDateFormat) {
				internalDateFormat.setTimeZone(zone);
			}
		}

		public TimeZone getTimeZone() {
			synchronized (internalDateFormat) {
				return internalDateFormat.getTimeZone();
			}
		}

		public void setLenient(boolean lenient) {// 告知日期/时间分析是否为不严格的。
			synchronized (internalDateFormat) {
				internalDateFormat.setLenient(lenient);
			}
		}

		public boolean isLenient() {
			synchronized (internalDateFormat) {
				return internalDateFormat.isLenient();
			}
		}

		public int hashCode() {
			synchronized (internalDateFormat) {
				return internalDateFormat.hashCode();
			}
		}

		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			synchronized (internalDateFormat) {
				return internalDateFormat.equals(obj);
			}
		}
	}

	/**
	 * 比较两个日期之间相差的天数，不考虑时分秒
	 * 
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	public static int daysBetweenTwoDate(Date beginDate, Date endDate) {

		GregorianCalendar gc1 = new GregorianCalendar();
		GregorianCalendar gc2 = new GregorianCalendar();

		gc1.setTime(beginDate);
		gc2.setTime(endDate);
		int elapsed = 0;

		gc1.clear(Calendar.MILLISECOND);
		gc1.clear(Calendar.SECOND);
		gc1.clear(Calendar.MINUTE);
		gc1.clear(Calendar.HOUR_OF_DAY);

		gc2.clear(Calendar.MILLISECOND);
		gc2.clear(Calendar.SECOND);
		gc2.clear(Calendar.MINUTE);
		gc2.clear(Calendar.HOUR_OF_DAY);

		while (gc1.before(gc2)) {
			gc1.add(Calendar.DATE, 1);
			elapsed++;
		}
		return elapsed;
	}

}
