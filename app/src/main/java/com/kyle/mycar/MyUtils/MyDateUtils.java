package com.kyle.mycar.MyUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 默认"yyyy年MM月dd日  HH:mm"格式日期
 * Created by Kyle on 2017/2/9.
 */

public class MyDateUtils {

    /**string转long
     * @param dateStr yyyy年MM月dd日  HH:mm
     * @return 返回long型时间，失败返回-1
     */
    public static long strToLong(String dateStr) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm", Locale.getDefault());
        try {
            return sdf.parse(dateStr).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**long转string
     * @param time
     * @return yyyy年MM月dd日  HH:mm 失败返回null
     */
    public static String longToStr(long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH:mm", Locale.getDefault());
        return sdf.format(new Date(time));
    }

}
