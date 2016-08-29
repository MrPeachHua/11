package com.boxiang.share.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author junior.pan
 * @date 2016-1-2
 */
public final class DateUtil {
	public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "HH:mm:ss";

    // Suppress default constructor for noninstantiability
    private DateUtil() {
        throw new AssertionError();
    }

	/**
	 * Format the specified date using the specified format String. The format
	 * String follows the rules specified in the
	 * <code>java.text.SimpleDateFormat</code> class.
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param date
	 *            The date
	 * @param format
	 *            The format String
	 * @return A formatted Date String
	 */
	public static String date2str(Date date, String format) {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.format(date);
	}

	/**
	 * Parse the specified date String using the specified format String. The
	 * format String follows the rules specified in the
	 * <code>java.text.SimpleDateFormat</code> class.
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param date	The date String
	 * @param format	The format String
	 * @return A Date object
	 * @throws ParseException
	 */
	public static Date str2date(String date, String format)
			throws ParseException {
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		return fmt.parse(date);
	}

	/**
	 * Format the current date using the specified format String. The
	 * format String follows the rules specified in the
	 * <code>java.text.SimpleDateFormat</code> class.
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param format	The format String
	 * @return A formatted Date String
	 */
	public static String getCurrDate(String format)	{
		return date2str(new Date(), format);
	}

	/**
     * Format the specified date to a day of week using the specified format String.
     *
	 * @author junior.pan
	 * @date 2016-1-2
     * @param 	date	The date
     * @param 	format 	0：number display；1：text display
     * @return	String	A day of week
     */
    public static String getDayOfWeek(Date date, int format) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(date);
    	int dayOfWeek = (c.get(Calendar.DAY_OF_WEEK) == 1) ? 7 : c.get(Calendar.DAY_OF_WEEK) - 1;
    	String dayOfWeekStr = null;
    	switch (dayOfWeek) {
    		case 1:
    			dayOfWeekStr = (0 == format) ? "1" : "一";
    			break;
    		case 2:
    			dayOfWeekStr = (0 == format) ? "2" : "二";
    			break;
    		case 3:
    			dayOfWeekStr = (0 == format) ? "3" : "三";
    			break;
    		case 4:
    			dayOfWeekStr = (0 == format) ? "4" : "四";
    			break;
    		case 5:
    			dayOfWeekStr = (0 == format) ? "5" : "五";
    			break;
    		case 6:
    			dayOfWeekStr = (0 == format) ? "6" : "六";
    			break;
    		case 7:
    			dayOfWeekStr = (0 == format) ? "7" : "日";
    			break;
		}
    	return dayOfWeekStr;
    }
	public static String getIntOfWeek(String week)
	{
		String intWeek = null;
		if ("周一".equals(week))
		{
			intWeek = "1";
		}else if ("周二".equals(week))
		{
			intWeek = "2";
		}else if ("周三".equals(week))
		{
			intWeek = "3";
		}
		else if ("周四".equals(week))
		{
			intWeek = "4";
		}
		else if ("周五".equals(week))
		{
			intWeek = "5";
		}
		else if ("周六".equals(week))
		{
			intWeek = "6";
		}
		else if ("周日".equals(week))
		{
			intWeek = "7";
		}
		return intWeek;
	}
	/**
	 * 得到给定日期的前或后n月
     *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param dDay
	 *            日期
	 * @param n
	 *            正为后，负为前
	 * @return	A date object
	 */
	public static Date getPreOrNextMonth(Date dDay, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dDay);
		calendar.add(Calendar.MONTH, n);
		return calendar.getTime();
	}

	/**
	 * 得到给定日期的前或后n天
     *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param dDay
	 *            日期
	 * @param n
	 *            正为后，负为前
	 * @return	A date object
	 */
	public static Date getPreOrNextDate(Date dDay, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dDay);
		calendar.add(Calendar.DATE, n);
		return calendar.getTime();
	}

	/**
	 * 得到给定日期的前或后n小时
     *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param dDay
	 *            日期
	 * @param n
	 *            正为后，负为前
	 * @return	A date object
	 */
	public static Date getPreOrNextHour(Date dDay, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dDay);
		calendar.add(Calendar.HOUR, n);
		return calendar.getTime();
	}

	/**
	 * 得到给定日期的前或后n分钟
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param dDay
	 *            日期
	 * @param n
	 *            正为后，负为前
	 * @return	A date object
	 */
	public static Date getPreOrNextMinute(Date dDay, int n) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dDay);
		calendar.add(Calendar.MINUTE, n);
		return calendar.getTime();
	}

    /**
     * 根据指定日期获取指定日期所在周的开始日期和结束日期(星期一、星期天)
	 * @author junior.pan
	 * @date 2016-1-2
     * @param 	paramDate	指定日期
     * @return	String[]	开始日期和结束日期数组
     */
    public static String[] getWeekStartAndEndDate(Date paramDate) {
    	String[] retAry = new String[2];

    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(paramDate);
    	//以周一为一周的开始
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		retAry[0] = date2str(calendar.getTime(), DATE_FORMAT);
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		retAry[1] = date2str(calendar.getTime(), DATE_FORMAT);

		return retAry;
    }

	/**
	 * 根据指定日期获取指定日期所在月的第一天和最后一天
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param	paramDate	指定日期
	 * @return	String[]	第一天和最后一天数组
	 */
	public static String[] getMonthStartAndEndDate(Date paramDate)
	{
		String[] retAry = new String[2];

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(paramDate);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		retAry[0] = date2str(calendar.getTime(), DATE_FORMAT);
		calendar.add(Calendar.MONTH, 1);
		calendar.set(Calendar.DAY_OF_MONTH, 0);
		retAry[1] = date2str(calendar.getTime(), DATE_FORMAT);

		return retAry;
	}

	/**
	 * 取得两个日期的天数之差
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param 	d1		指定日期1
	 * @param 	d2		指定日期2
	 * @return	int 	两个日期的天数之差
	 * @throws ParseException
	 */
	public static int getDaysInterval(String d1, String d2) throws ParseException {
		Date date1 = str2date(d1, DATE_FORMAT);
		Date date2 = str2date(d2, DATE_FORMAT);

		Long diffTimes = date1.getTime() - date2.getTime();
		Long diffDays = diffTimes / (3600 * 1000 * 24);

		return Math.abs(diffDays.intValue());
	}

	/**
	 * 取得两个日期的天数之差
	 *
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param d1
	 * @param d2
	 * @return long 两个日期的天数之差
	 * @throws ParseException
	 */
	public static int getDaysInterval(Date d1, Date d2) throws ParseException {
		String date1 = date2str(d1, DATE_FORMAT);
		String date2 = date2str(d2, DATE_FORMAT);

		return getDaysInterval(date1,date2);
	}

	/**
	 * 获取指定日期相差月份数
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param 	paramDate1	指定日期1
	 * @param 	paramDate2	指定日期2
	 * @return 	int			相差月份数
	 * 注：日期所在月都算一月
	 */
	public static int getDiffMonthsOfTwoDate(String paramDate1, String paramDate2)
	{
		//指定日期1的年份、月份
		int tempYear1 = Integer.parseInt(paramDate1.substring(0, 4));
		int tempMonth1 = Integer.parseInt(paramDate1.substring(5, 7));

		//指定日期2的年份、月份
		int tempYear2 = Integer.parseInt(paramDate2.substring(0, 4));
		int tempMonth2 = Integer.parseInt(paramDate2.substring(5, 7));

		return Math.abs((tempYear1 * 12 + tempMonth1) - (tempYear2 * 12 + tempMonth2)) + 1;
	}

	/**
	 * 获取指定日期所在月有多少天
	 * @author junior.pan
	 * @date 2016-1-2
	 * @param 	paramDate	指定日期(yyyy-MM格式)
	 * @return	int			指定日期所在月有多少天
	 */
	public static int getDaysOfMonths(String paramDate) {
		int days = 0;
		try {
			Date date = str2date(paramDate,"yyyy-MM");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		} catch(Exception e) {
			System.out.println("字符串转换到日期出错");
			throw new RuntimeException();
		}
		return days;
	}

	public static String[][] getWeekForMonth(Date paramDate) throws ParseException {
		//DateFormatSymbols dfs = new DateFormatSymbols();
		//String[] weeks = dfs.getWeekdays();
		Calendar c_begin = Calendar.getInstance();
		c_begin.setTime(paramDate);
		c_begin.set(Calendar.DAY_OF_MONTH, 1);
		Calendar c_end = Calendar.getInstance();
		c_end.setTime(paramDate);
		int count = 0;
		c_end.add(Calendar.DAY_OF_YEAR, 1); // 结束日期下滚一天是为了包含最后一天
		String[][] retAry = new String[5][2];
		while (c_begin.before(c_end)) {
			if (count ==0 && c_begin.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
				c_begin.add(Calendar.DAY_OF_YEAR, 1);
				continue;
			}
			count++;
			//System.out.print("第" + count + "周  日期：" + new java.sql.Date(c_begin.getTime().getTime()) + ","
			//		+ weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
			retAry[count-1][0]=date2str(c_begin.getTime(), DATE_FORMAT);
			c_begin.add(Calendar.DAY_OF_YEAR, 6);
			//System.out.println("  日期：" + new java.sql.Date(c_begin.getTime().getTime()) + ","
			//		+ weeks[c_begin.get(Calendar.DAY_OF_WEEK)]);
			retAry[count-1][1]=date2str(c_begin.getTime(), DATE_FORMAT);
			c_begin.add(Calendar.DAY_OF_YEAR, 1);
		}
		return retAry;
	}
	public static String[][] getNormalWeek(Date paramDate) throws ParseException {
		//paramDate  = DateUtil.str2date("2016-05-01", "yyyy-MM-dd");
		Calendar cl = Calendar.getInstance();
		cl.setTime(paramDate);
		int yearInt = cl.get(Calendar.YEAR);
		int monthInt = cl.get(Calendar.MONTH);
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(Calendar.YEAR, yearInt);
		c.set(Calendar.MONTH, monthInt);
		c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		//获取当前月最后一天
		Calendar ca = Calendar.getInstance();
		ca.clear();
		ca.set(Calendar.YEAR, yearInt);
		ca.set(Calendar.MONTH, monthInt);
		ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
		//获取下个月
		//ca.add(Calendar.MONTH, 1);
		ca.add(Calendar.DATE, 1);
		String ug = DateUtil.date2str(ca.getTime(), DateUtil.DATETIME_FORMAT);
		Date startDate = null;
		Date endDate = null;
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
			c.add(Calendar.DAY_OF_MONTH, 1);
		}
		startDate = c.getTime();
		//String uuStr = DateUtil.date2str(startDate,DateUtil.DATE_FORMAT);
		//ca.add(Calendar.MONTH, 1);
		//String a1 = DateUtil.date2str(ca.getTime(),DateUtil.DATE_FORMAT);
		//ca.add(Calendar.MONTH, 1);
		String a2 = DateUtil.date2str(ca.getTime(),DateUtil.DATE_FORMAT);

		while (ca.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
				ca.add(Calendar.DAY_OF_MONTH, 1);
		}
		endDate = ca.getTime();
		String iiStr = DateUtil.date2str(endDate,DateUtil.DATE_FORMAT);
		List<Date> nodes = new ArrayList<Date>();
		nodes.add(startDate);
		long weeks = (endDate.getTime()-startDate.getTime())/(1000*60*60*24)+1;
		for(int i=0;i<weeks/7;i++){
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(startDate);
			gc.add(5,7*(i+1)-1);
			nodes.add(gc.getTime());
		}
		String[][] retAry = new String[nodes.size()-1][2];
		for(int i=0;i<nodes.size()-1;i++){
			String dat1 = DateUtil.date2str(nodes.get(i),DateUtil.DATE_FORMAT);
			String dat2 = DateUtil.date2str(nodes.get(i+1),DateUtil.DATE_FORMAT);
			retAry[i][0]=dat1;
			retAry[i][1]=dat2;
		}
		return retAry;
	}

	/**
	 * 给传入的日期加上23:59:59
	 */
	public static Date getEndDate(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, 23);
		calendar.add(Calendar.MINUTE, 59);
		calendar.add(Calendar.SECOND, 59);
		return calendar.getTime();
	}

}

