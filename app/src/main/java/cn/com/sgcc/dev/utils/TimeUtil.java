package cn.com.sgcc.dev.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import cn.com.sgcc.dev.view.activity.DeviceDetailsActivity;

/**
 * <p>@description:时间工具类</p>
 *
 * @author lizilei
 * @version 1.0.0
 * @Email lizilei_warms@163.com
 * @since 2017/9/6
 */
public class TimeUtil {

    private static DateFormat df;

    /**
     * 获取时间差
     *
     * @return
     */
    public static String getTimeDiff(String time) {
        if (time == null || time.isEmpty()) {
            return "";
        }
        String diff = "";
        df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            Date d = df.parse(time);
            long diff1 = System.currentTimeMillis() - d.getTime();//这样得到的差值是微秒级别

            long days = diff1 / (1000 * 60 * 60 * 24);
            long hours = (diff1 - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            long minutes = (diff1 - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);

            if (days > 0) {
                return days + "天";
            } else if (hours > 0) {
                return hours + "小时";
            } else if (minutes > 0) {
                return minutes + "分钟";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return diff;
    }

    /**
     * 时间比较大小
     *
     * @return
     */
    public static int timeCompare(String t1, String t2) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d1 = null, d2 = null;
        try {
            d1 = formatter.parse(formatString(t1));
            d2 = formatter.parse(formatString(t2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int result = d1.compareTo(d2);
        return result;
    }

    /*获得当前时间*/
    public static String getCurrentTime() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /*获得当前时间*/
    public static String getCurrentTime2() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    public static String formatString(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (strToDate2(str) != null) {
            return formatter.format(strToDate2(str));
        }
        return "";
    }

    public static String formatString2(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        if (str != null && !str.equals("")) {
            return formatter.format(strToDate2(str));
        }
        return "";
    }

    /*获得当前时间*/
    public static Date getCurrentDate() {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sf.parse(sf.format(new Date()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将String类型的时间转换成Date类型,传入的时间格式必须要满足下面的格式
     */
    public static Date strToDate(String dateStr) {
        SimpleDateFormat format;
        if (dateStr.contains(".")) {
            format = new SimpleDateFormat("dd-MM月-yy HH.mm.ss");
        } else {
            if (dateStr.contains(":")) {
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            } else {
                format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            }
        }
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将String类型的时间转换成Date类型,传入的时间格式必须要满足下面的格式
     */
    public static Date strToDate2(String dateStr) {
        SimpleDateFormat format;
        if (dateStr.contains(".")) {
            format = new SimpleDateFormat("dd-MM月-yy HH.mm.ss");
        } else {
            format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        }
        try {
            return format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将Date型转换成指定格式的时间字符串
     */
    public static String dateToStr(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return format.format(date);
    }

    /**
     * 将Date型转换成指定格式的时间字符串
     */
    public static String dateToStr2(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        return format.format(date);
    }

    public static Timestamp dateToTimestamp(String date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        try {
            String time = sf.format(sf.parse(date));
            return Timestamp.valueOf(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    //判断返回的时间是否为空
    public static boolean dateIsEmpty(String s) {
        return s == null || s.equals("") || s.equals("0") || s.equals("null");
    }
}
