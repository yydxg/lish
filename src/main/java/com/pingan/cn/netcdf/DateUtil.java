package com.pingan.cn.netcdf;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 两个时间差计算
 */
public class DateUtil {
    private static String distanceTime;
    public static void main(String[] args) throws Exception {
        String startday =  "2017-09-20";
        String endday ="2017-09-28";
        long DistanceDays = getDistanceDays(startday,endday);//两个时间之间相差距离多少天
        System.out.println(DistanceDays);

        /*String starttimes =  "2017-01-17 00:10:20";
        String endtimes ="2017-01-18 00:10:21";
        long[] DistanceTimes = getDistanceTimes(starttimes,endtimes);//两个时间相差距离多少天多少小时多少分多少秒 ，以long[]形式返回
        for (int i = 0; i < DistanceTimes.length; i++) {
            System.out.println(DistanceTimes[i]);
        }
        String DistanceTime = getDistanceTime(starttimes,endtimes);//两个时间相差距离多少天多少小时多少分多少秒 ，以String形式返回
        System.out.println(DistanceTime);*/
        String sourceDay = "1980-01-01-00-00";
        String endday2 = getEndtimeStr(sourceDay,9529,"yyyy-MM-dd-HH-mm");
        System.out.println(endday2);
    }


    public static String getEndtimeStr(String starttime,long addhours,String dformat) throws Exception{
        DateFormat df = new SimpleDateFormat(dformat);
        String endtime = "";
        try {
            long diff  = addhours *(1000 * 60 * 60);
            Date start = df.parse(starttime);
            long time1 = start.getTime();
            long time2 = time1 + diff;
            Date end = new Date(time2);
            endtime = df.format(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endtime;
    }

    /**
     * 两个时间之间相差距离多少天
     * one 时间参数 1：
     * two 时间参数 2：
     * @return 相差天数
     */
    public static long getDistanceDays(String starttime, String endtime) throws Exception{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date one;
        Date two;
        long days=0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            days = diff / (1000 * 60 * 60 * 24);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;//返回相差多少天
    }


    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     *  str1 时间参数 1 格式：1990-01-01 12:00:00
     *  str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return long[] 返回值为：{天, 时, 分, 秒}
     */
    public static long[] getDistanceTimes(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long[] times = {day, hour, min, sec};
        return times;
    }



    /**
     * 两个时间相差距离多少天多少小时多少分多少秒
     * @param str1 时间参数 1 格式：1990-01-01 12:00:00
     * @param str2 时间参数 2 格式：2009-01-01 12:00:00
     * @return String 返回值为：xx天xx小时xx分xx秒
     */
    public static String getDistanceTime(String starttime, String endtime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date one;
        Date two;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        try {
            one = df.parse(starttime);
            two = df.parse(endtime);
            long time1 = one.getTime();
            long time2 = two.getTime();
            long diff ;
            if(time1<time2) {
                diff = time2 - time1;
            } else {
                diff = time1 - time2;
            }
            day = diff / (24 * 60 * 60 * 1000);
            hour = (diff / (60 * 60 * 1000) - day * 24);
            min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            sec = (diff/1000-day*24*60*60-hour*60*60-min*60);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "天" + hour + "小时" + min + "分" + sec + "秒";
    }
}