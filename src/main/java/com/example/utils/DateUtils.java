package com.example.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by djb
 * on 2014/11/17.
 */
public class DateUtils {

    /**
     * 一天的时间
     */
    public final static int DAYTIME = 86400;

    /**
     * 一个小时的时间
     */
    public final static int ONE_HOUR_TIME = 3600;

    /**
     * 年-月-日 小时 yyyy-MM-dd HH
     */
    public static final String SIMPLE_FORMAT = "yyyy-MM-dd HH";
    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String SIMPLE = "yyyy-MM-dd HH:mm:ss";

    /**
     * 完整时间 yyyy-MM-dd HH:mm:ss
     */
    public static final String SIMPLE_FORMAT_WITHOUT_BLANK = "yyyy-MM-ddHH:mm:ss";

    /**
     * 年-月-日 yyyy-MM-dd
     */
    public static final String SIMPLE_FORMAT_DAY = "yyyy-MM-dd";


    /**
     * 获取当前时间戳 精确到秒
     *
     * @return
     */
    public static Integer getCurrentSecondIntValue() {
        return new Long(System.currentTimeMillis() / 1000).intValue();
    }


    public static String getCurrentFormatStr(String format) {
        return getFormatStr(new Date(), format);
    }

    public static String getFormatStr(Date date, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        String timeStr = df.format(date);
        return timeStr;
    }

    /**
     * 获取格式化后的时间字符串
     *
     * @param second
     * @param format
     * @return
     */
    public static String getFormatDateFromSecond(Long second, String format) {
        if (second == null) {
            return null;
        }
        SimpleDateFormat sFormat = new SimpleDateFormat(format,
                Locale.SIMPLIFIED_CHINESE);
        long milliS = second * 1000L;
        Date date = new Date(milliS);
        return sFormat.format(date);

    }

    /**
     * 获取格式化后的时间字符串
     *
     * @param second
     * @param format
     * @return
     */
    public static String getFormatDateFromSecond(Integer second, String format) {
        if (second == null) {
            return null;
        }

        return getFormatDateFromSecond(second.longValue(), format);
    }


    public static String getChineseDateFromSecond(Integer second) {
        if (second == null) {
            return null;
        }
        if (second == 0) {
            return "";
        }
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.SIMPLIFIED_CHINESE);
        long milliS = second * 1000L;
        Date date = new Date(milliS);
        return sFormat.format(date);

    }


    /**
     * 根据时间戳获取沙特时间字符串
     *
     * @param second        时间戳
     * @param formatPattern 字符串格式
     * @return
     */
    public static String getSADateStrFromSeconds(Integer second, String formatPattern) {
        if (second == null) {
            return null;
        }
        if (second == 0) {
            return "";
        }
        TimeZone saTimeZone = TimeZone.getTimeZone("GMT+03:00");

        SimpleDateFormat sFormat = new SimpleDateFormat(formatPattern);
        long milliS = second * 1000L;
        Date date = new Date(milliS);
        sFormat.setTimeZone(saTimeZone);
        return sFormat.format(date);
    }

    /**
     * 根据沙特的时间字符串，获取对应的秒
     *
     * @param timeStr       时间字符串
     * @param formatPattern 字符串格式
     * @return
     */
    public static Integer getSecondsFromSADateStr(String timeStr, String formatPattern) {
        if (StringUtils.isBlank(timeStr)) {
            return null;
        }
        TimeZone saTimeZone = TimeZone.getTimeZone("GMT+03:00");
        SimpleDateFormat sFormat = new SimpleDateFormat(formatPattern);
        sFormat.setTimeZone(saTimeZone);
        try {
            Date dateSA = sFormat.parse(timeStr);
            long milliS = dateSA.getTime();
            return Long.valueOf(milliS / 1000L).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getDateToWeek(String datetime) {
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
        String[] weekDaysName  = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        // 获得一个日历
        Calendar calendar= Calendar.getInstance();
        try {
            Date date=sFormat.parse(datetime);
            calendar.setTime(date);
            // 指示一个星期中的某天。
            int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
            if (w < 0) {
                w = 0;
                return weekDaysName[w];
            }
            return weekDaysName[w];
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return  null;
    }

    public static Date stampToString(Integer second, String formatPattern){
        if (second == null) {
            return null;
        }
        if (second == 0) {
            return null;
        }
        try{
            SimpleDateFormat sFormat = new SimpleDateFormat(formatPattern);
            long milliS = second * 1000L;
            Date date = new Date(milliS);
            String strDate=sFormat.format(date);
            return sFormat.parse(strDate);
        }catch(Exception e){
            return null;
        }
    }

    public static Date stampToDate(Date date, String formatPattern){
        if (date == null) {
            return null;
        }
        try{
            SimpleDateFormat sFormat = new SimpleDateFormat(formatPattern);
            String strDate= sFormat.format(date);
            return sFormat.parse(strDate);
        }catch(Exception e){
            return null;
        }
    }

    public static Integer dateToStamp(Date date){
        long  milliS=date.getTime();
        return Long.valueOf(milliS / 1000L).intValue();
    }

    public static Integer getEndTime(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        todayEnd.set(Calendar.MILLISECOND, 0);
        todayEnd.add(Calendar.DATE, -1);
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        return dateToStamp(todayEnd.getTime());
    }
}
