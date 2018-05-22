package com.haoyue.auxiliary;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 作者：chen1 on 2018/1/26 15
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class DataUtils {
    /**
     * 获取一个月的最后一天
     * @param dat
     * @return
     */
    public String getEndDateOfMonth(String dat) {// yyyy-MM-dd
        String str = dat.substring(0, 8);
        String year = dat.substring(0, 4);
        String month = dat.substring(5, 7);
        int mon = Integer.parseInt(month);
        if (mon == 1 || mon == 3 || mon == 5 || mon == 7 || mon == 8 || mon == 10 || mon == 12) {
            str += "31";
        } else if (mon == 4 || mon == 6 || mon == 9 || mon == 11) {
            str += "30";
        } else {
            if ((Integer.parseInt(year) % 4) == 0 && (Integer.parseInt(year) % 100) == 0) {
                str += "28";
            } else if((Integer.parseInt(year) % 400) == 0|| (Integer.parseInt(year) % 4) == 0){
                str += "29";
            }else {
                str += "28";
            }
        }
        return str;
    }

    /**
     * 返回美国时间格式 26 Apr 2006
     * @param str
     * @return
     */
    public String getEDate(String str) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(str, pos);
        String j = strtodate.toString();
        String[] k = j.split(" ");
        return (k[2] + k[1].toUpperCase() + k[5]);
    }


    /**
     * 得到二个日期间的间隔天数
     * @param sj1
     * @param sj2
     */
    public long getTwoDay(String sj1, String sj2) {
        SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
        long day = 0;
        try {
            Date date = myFormatter.parse(sj1);
            Date mydate = myFormatter.parse(sj2);
            day = (date.getTime() - mydate.getTime()) / (1000 * 60 * 60 * 24);
        } catch (Exception e) {
        }
        return day;
    }


    /**
     * 将时间戳转化为标准时间
     * 输出：Tue Oct 07 12:04:36 CST 2014
     */
    public Date timestampToDate(long times) {
        Date date = new Date(times);
        return date;
    }

    /**
     * 获取时间戳
     * 输出结果:1438692801766
     */
    public long getTimeStamp() {
        Date date = new Date();
        long times = date.getTime();
        return times;
    }


    private String rollMethod(int year, int month, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        Date date = new Date();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.roll(Calendar.DATE, -4);
        date = cal.getTime();
        String sdf = df.format(date);
//        cal.roll(Calendar.DATE, 4);
//        date = cal.getTime();
        return sdf;
    }

    private String addMethod(int year, int month, int day) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.add(Calendar.DATE, -4);
        Date date = cal.getTime();
        String datef = df.format(date);
//        cal.add(Calendar.DATE, 4);
//        date = cal.getTime();
        return datef;
    }

    /**
     * 计算一年中的第几星期是几号
     * @param year
     * @param week
     * @param dayWeek:dayWeek取值：0-6，对应星期天-星期六
     * @param timeStyle:"yyyy-MM-dd ","yyyyMMdd","yyyy/MM/dd, "y年M月d日"
     * @return
     */
    private String getWhichDay(int year, int week, int dayWeek, String timeStyle) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.WEEK_OF_YEAR, week);
        cal.set(Calendar.DAY_OF_WEEK, dayWeek);//
        return df.format(cal.getTime());
    }

    /**
     * 计算某一天是一年中的第几星期
     * @param year
     * @param month
     * @param day
     */
    private int getWhichWeek(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        int weekno = cal.get(Calendar.WEEK_OF_YEAR);
        return weekno;
    }

    /**
     * 格式化输出日期时间:将时间格式转化为指定的格式
     * @param timeStyle:"yyyy-MM-dd hh:mm:ss","yyyyMMdd hh:mm:ss","yyyy/MM/dd hh:mm:ss",
     * "yyyy/MM/dd","yyyy-MM-dd","y年M月d日 h时m分s秒"
     */
    private String initOutputDate(String timeStyle) {
        Date date = new Date();
        SimpleDateFormat sdf0 = new SimpleDateFormat(timeStyle);
        String newStyle = "获取到的日期时间：" + sdf0.format(date);
        return newStyle;
    }

    private void calendarToDate() {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
    }

    private void dateToCalendar() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
    }

    /**
     * 计算某一月份的最大天数
     * @param year
     * @param month
     */
    private int getMaxDayInMonrh(int year, int month) {
        Calendar time = Calendar.getInstance();
        time.clear();
        time.set(Calendar.YEAR, year);
        time.set(Calendar.MONTH, month - 1);//注意,Calendar对象默认一月为0
        int day = time.getActualMaximum(Calendar.DAY_OF_MONTH);//本月份的天数
        return day;
    }
}
