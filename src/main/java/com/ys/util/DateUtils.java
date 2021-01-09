package com.ys.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    /*
     * 将时间转换为时间戳
     */
    public static long dateToStamp(String s,String format) throws ParseException {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        return ts;
    }

    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / ( 60 * 60 * 24);
        long hours = (mss % ( 60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % ( 60 * 60)) /60;
        if(days>0){
            DateTimes= days + "天" + hours + "小时" + minutes + "分钟";
        }else if(hours>0){
            DateTimes=hours + "小时" + minutes + "分钟";
        }else {
            DateTimes=minutes + "分钟";
        }

        return DateTimes;
    }



}
