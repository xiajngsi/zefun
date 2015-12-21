package com.zefun.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期工具类
 * 
 */
public class DateUtil {

    /**
     * 根据时间字符串获取日期    yyyy-MM-dd HH:mm:ss
     * @param dataStr	时间字符串
     * @return			日期
     */
    public static Date tranStrToDate(String dataStr) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return format.parse(dataStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 根据时间字符串获取日期     yyyy-MM-dd
     * @param dataStr   时间字符串
     * @return          日期
     */
    public static Date tranStrToDateDD(String dataStr) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.parse(dataStr);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据时间字符串获取日期
     * @param dataStr   时间字符串 1313243564
     * @return          日期
     */
    public static String tranStrToDateStr(String dataStr) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date(Long.valueOf(dataStr)));
    }
    
    /**
     * 根据时间字符串获取日期
     * @return          当前日期，格式：yyyy-MM-dd HH:mm
     */
    public static String getCurDateTrimMinutes() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(new Date());
    }
    
    /**
     * 根据时间字符串获取日期
     * @return          当前日期，格式：yyyy年MM月dd日
     */
    public static String getCurDateChine() {
        DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(new Date());
    }
    
    /**
     * 根据传入时间转换为毫秒值，附加纳秒后六位
     * @param dateTime 日期
     * @return String
     * @throws ParseException 异常
     */
    public static String getTimeToNanos(String dateTime) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateTime));
        Long curNanos = Math.abs(System.nanoTime());
        String nanos = curNanos.toString();
        return c.getTimeInMillis() + nanos.substring(nanos.length() - 6);
    }

    /**
     * 根据传入的毫秒值转换为yyyy-MM-dd HH:mm:ss的日期字符串
     * @param dateTime 日期
     * @return String
     */
    public static String getDateStrByTimeMillis(long dateTime) {
        Date date = new Date(dateTime);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

    /**
     * 获取当前日期
     * @return		当前日期，格式：yyyy-MM-dd
     */
    public static String getCurDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(new Date());
    }
    
    
    /**
     * 获取当前月份
     * @return      当前日期，格式：yyyy-MM
     */
    public static String getCurMonth() {
        DateFormat format = new SimpleDateFormat("yyyy-MM");
        return format.format(new Date());
    }
    
    /**
     * 
    * @author 王大爷
    * @date 2015年9月18日 下午8:17:52
    * @return 当前日期，格式：yyyyMMdd
     */
    public static String getCurDateString() {
        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        return format.format(new Date());
    }

    /**
     * 获取当前时间
     * @return		当前时间，格式：yyyy-MM-dd HH:mm:ss
     */
    public static String getCurTime() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(new Date());
    }

    /**
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)
     * @param timeZoneId 时区Id
     * @param time 时间
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)
     */
    public static long getDiffTimeZoneRawOffset(String timeZoneId, long time) {
        long diffTime = TimeZone.getDefault().getRawOffset() - TimeZone.getTimeZone(timeZoneId).getRawOffset();
        long curTime = System.currentTimeMillis();
        return time - curTime + diffTime;
    }

    /**
     * 获取当天结束时间
     * @return	毫秒数
     */
    public static Long getEndTime() {
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.AM_PM, 0);
        todayEnd.set(Calendar.HOUR, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 999);
        return todayEnd.getTime().getTime();
    }

    /**
     * 添加日期
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:07:45
    * @param dateTime 时间
    * @param days 天
    * @return String
     */
    public static String addDays(String dateTime, int days) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(sdf.parse(dateTime));
            date.set(Calendar.DATE, date.get(Calendar.DATE) + days);
            return sdf.format(date.getTime());
        }
        catch (Exception e) {
            return dateTime;
        }
    }

    /**
     * 添加秒
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:07
    * @param dateTime 时间
    * @param secondes 秒
    * @return String
     */
    public static String addSecondes(String dateTime, int secondes) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar date = Calendar.getInstance();
            date.setTime(sdf.parse(dateTime));
            date.set(Calendar.SECOND, date.get(Calendar.SECOND) + secondes);
            return sdf.format(date.getTime());
        }
        catch (Exception e) {
            return dateTime;
        }
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:21
    * @param startTime 开始时间
    * @return long
     */
    public static long getDiffTimeByNow(String startTime) {
        return getDiffTime(startTime, null);
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:38
    * @param startTime 开始时间
    * @param endTime 结束时间
    * @return long
     */
    public static long getDiffTime(String startTime, String endTime) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startDate = sdf.parse(startTime);
            Date endDate = null;
            if (StringUtil.isNotEmpty(endTime)) {
                endDate = sdf.parse(endTime);
            }
            else {
                endDate = new Date();
            }
            return startDate.getTime() - endDate.getTime();
        }
        catch (Exception e) {
            return 0;
        }
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:08:54
    * @param time 时间
    * @return String
     */
    // 支付宝or微信回传的yyyyMMddhhmmss时间格式需要转化为yyyy-MM-dd hh:mm:ss
    public static String timestampFormat(String time) {
        String yyyy = time.substring(0, 4);
        String mm = time.substring(4, 6);
        String dd = time.substring(6, 8);
        String hh = time.substring(8, 10);
        String min = time.substring(10, 12);
        String ss = time.substring(12, 14);
        return yyyy + "-" + mm + "-" + dd + " " + hh + ":" + min + ":" + ss;
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:09
    * @param d 日期
    * @param day 天
    * @return Date
     */
    public static Date getDateDaysBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        // now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        now.add(Calendar.DATE, -day);
        return now.getTime();
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:20
    * @param d 日期
    * @param day 天
    * @return Date
     */
    public static Date getDateDaysAfter(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        // now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        now.add(Calendar.DATE, day);
        return now.getTime();
    }
    
    /**
     * 
     * @param dateStr 日期
     * @param day 天数
     * @return Date
     * @throws ParseException 
     */
    public static String getDateAfterDaysStr(String dateStr, int day) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = sdf.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, day);
        return sdf.format(cal.getTime());
    }
    
    
    /**
     * 
     * @param dateStr 日期
     * @param months 月份数
     * @return Date
     * @throws ParseException 
     */
    public static String getDateAfterMonthsStr(String dateStr, int months) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date date = sdf.parse(dateStr);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return sdf.format(cal.getTime());
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:32
    * @param d 日期
    * @param month 月份
    * @return Date
     */
    public static Date getDateMonthsBefore(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        // now.set(Calendar.DATE, now.get(Calendar.MONTH) - month);
        now.add(Calendar.MONTH, -month);
        return now.getTime();
    }

    /**
     * 
    * @author 洪秋霞
    * @date 2015年8月11日 下午2:09:45
    * @param d 日期
    * @param month 月份
    * @return Date
     */
    public static Date getDateMonthsAfter(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        // now.set(Calendar.DATE, now.get(Calendar.MONTH) + month);
        now.add(Calendar.MONTH, month);
        return now.getTime();
    }

    /**
     * 获得当前周- 周一的日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:13:50
    * @return 周一的日期
     */
    public static  Integer getCurrentMonday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Integer preMonday = Integer.parseInt(df.format(monday));
        return preMonday;
    }
    
    /**
     * 获得当前周- 周一的日期   yyyy-MM-dd
    * @author 王大爷
    * @date 2015年8月19日 下午7:13:50
    * @return 周一的日期
     */
    public static  String getCurrentMondayStr() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    
    /**
     * 获得当前周- 周日  的日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:14:04
    * @return  周日  的日期
     */
    public static Integer getPreviousSunday() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        Integer preMonday = Integer.parseInt(df.format(monday));
        return preMonday;
    }
    
    /**
     * 获得当前周- 周日  的日期 yyyy-MM-dd
    * @author 王大爷
    * @date 2015年8月19日 下午7:14:04
    * @return  周日  的日期
     */
    public static String getPreviousSundayStr() {
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus +6);
        Date monday = currentDate.getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String preMonday = df.format(monday);
        return preMonday;
    }
    
    /**
     * 获得当前日期与本周一相差的天数
    * @author 王大爷
    * @date 2015年8月19日 下午7:12:13
    * @return 天数
     */
    private static int getMondayPlus() {
        Calendar cd = Calendar.getInstance();
        // 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == 1) {
            return -6;
        } 
        else {
            return 2 - dayOfWeek;
        }
    } 
    
    /**
     * 获得当前月--开始日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:28:53
    * @return String
     */
    public static Integer getMinMonthDate() {   
        Calendar calendar = Calendar.getInstance();   
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
        return Integer.parseInt(dateFormat.format(calendar.getTime()));
    }
    
    /**
     * 获得当前月--开始日期 yyyy-MM-dd
    * @author 王大爷
    * @date 2015年8月19日 下午7:28:53
    * @return String
     */
    public static String getMinMonthDateStr() {   
        Calendar calendar = Calendar.getInstance();   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 获得当前月--结束日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:33:09
    * @return Integer
     */
    public static Integer getMaxMonthDate(){   
        Calendar calendar = Calendar.getInstance();   
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return Integer.parseInt(dateFormat.format(calendar.getTime()));
    }
    
    /**
     * 获得当前月--结束日期 yyyy-MM-dd
    * @author 王大爷
    * @date 2015年8月19日 下午7:33:09
    * @return Date
     */
    public static Date getMaxMonthDateDate(){   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }
    
    /**
     * 获得当前月--结束日期
    * @author 王大爷
    * @date 2015年8月19日 下午7:33:09
    * @return String
     */
    public static String getMaxMonthDateStr(){   
        Calendar calendar = Calendar.getInstance();   
        calendar.setTime(new Date());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dateFormat.format(calendar.getTime());
    }
    
    /**
     * 获取当天是星期几
    * @author 王大爷
    * @date 2015年8月20日 下午2:44:38
    * @return 当天是星期几
     */
    public static String getgetWeekOfDate(){
        Date date=new Date();
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }
     
    /**
     * 计算两个日期之间相差的天数 
    * @author 王大爷
    * @date 2015年8月21日 下午5:15:37
    * @param smdate 较小的时间 
    * @param bdate 较大的时间 
    * @return 相差天数 
    * @throws ParseException 异常
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long betweendays=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(betweendays));           
    }    
      
    /**
     * 计算两个日期之间相差的天数 
    * @author 王大爷
    * @date 2015年8月21日 下午5:16:15
    * @param smdate 较小的时间 
    * @param bdate 较大的时间 
    * @return 相差天数 
    * @throws ParseException 异常
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long betweendays = (time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(betweendays));     
    }
    
    /**
     * 计算两个日期之间相差的分钟数
    * @param smdate 较小的时间 
    * @param bdate 较大的时间 
    * @return 相差的分钟数
    * @throws ParseException 异常
     */
    public static int daysBetweenMinutes(String smdate, String bdate) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long betweendays = (time2-time1)/(1000 * 60);  
        return Integer.parseInt(String.valueOf(betweendays));     
    }
    
    /**
     * 传入数字时间,获得固定样式时间
    * @author 高国藩
    * @date 2015年9月7日 下午5:52:02
    * @param time 数字时间
    * @return 时间
     */
    public static String getDate(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Date date = new Date(Long.valueOf(time));
        return sdf.format(date);
    }
  
    /**** 
     * 传入具体日期 ，返回具体日期加一个月。 
     *  
     * @param date 
     *            日期(2014-04-20) 
     * @return 2014-03-20 
     * @throws ParseException 
     */  
    public static String subMonth(String date) throws ParseException {  
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  
        Date dt = sdf.parse(date);  
        Calendar rightNow = Calendar.getInstance();  
        rightNow.setTime(dt);  
  
        rightNow.add(Calendar.MONTH, 1);  
        Date dt1 = rightNow.getTime();  
        String reStr = sdf.format(dt1);  
  
        return reStr;  
    }
    
    
    /**
     * 根据周几数字获得汉字
    * @author 张进军
    * @date Oct 19, 2015 6:40:23 PM
    * @param weekNumber 周几数字
    * @return   周几汉字
     */
    public static String getWeekName(int weekNumber) {
        String weekName = "";
        switch (weekNumber) {
            case 1:
                weekName = "日";
                break;
            case 2:
                weekName = "一";
                break;
            case 3:
                weekName = "二";
                break;
            case 4:
                weekName = "三";
                break;
            case 5:
                weekName = "四";
                break;
            case 6:
                weekName = "五";
                break;
            case 7:
                weekName = "六";
                break;
            default:
                break;
        }
        return weekName;
    }
    
    /**
     * 计算两个(分钟)时间差
     * @param beginTime  开始时间
     * @param endTime  结束时间
     * @return (分钟)时间差
     */
    public static int getTwoTimeBetween(String beginTime, String endTime) {
    	SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	long minute = 0;
    	try {
			Date beginDate = myFormatter.parse(beginTime);
			Date endDate = myFormatter.parse(endTime);
			minute = (endDate.getTime() - beginDate.getTime()) / (60*1000);
		}
    	catch (ParseException e) {
			e.printStackTrace();
		}
    	return Integer.parseInt(String.valueOf(minute));
    }
    
    /**
     * 当前日期加一天
     * @return 加一天后的日期字符(yyyy-MM-dd)
     */
    public static String addOneDay() {
    	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sf.format(c.getTime());
    }
}
