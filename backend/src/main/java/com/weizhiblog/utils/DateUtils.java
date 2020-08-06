package com.weizhiblog.utils;

/*
 *
 * @createTime 07-30 15:52:26
 * @author Touka_
 * @classname com.weizhiblog.utils.dateUtils
 * @lastModifiedTime 7月30日   15:52:26
 */

import lombok.extern.slf4j.Slf4j;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateUtils {
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }

    public static String getExtraPath() {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        String year = "";
        String month = "";
        String day = "";
        String hour = "";
        String min = "";
        String second = "";
        String mills = "";
        StringBuilder sb = new StringBuilder();
        year += calendar.get(Calendar.YEAR);
        int mon = calendar.get(Calendar.MONTH) + 1;
        if (mon < 10) {
            month = "0" + mon;
        } else {
            month = month + mon;
        }
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        if (dayOfMonth < 10) {
            day = "0" + dayOfMonth;
        } else {
            day = day + dayOfMonth;
        }
        int hou = calendar.get(Calendar.HOUR_OF_DAY);
        if (hou < 10) {
            hour = "0" + hou;
        } else {
            hour = hour + hou;
        }

        int mi = calendar.get(Calendar.MINUTE);
        if (mi < 10) {
            min = "0" + mi;
        } else {
            min = min + mi;
        }

        int se = calendar.get(Calendar.SECOND);
        if (se < 10) {
            second = "0" + se;
        } else {
            second = second + se;
        }

        int ms = calendar.get(Calendar.MILLISECOND);
        if (ms < 10) {
            mills = "0" + ms;
        } else {
            mills = mills + ms;
        }

        sb
                .append(year).append("\\")
                .append(month).append("\\")
                .append(day).append("\\")
                .append(hour).append("\\")
                .append(min).append("\\")
                .append(second).append("\\")
                .append(mills).append("\\");
        return sb.toString();
    }

    public static String getLinuxExtraPath() {
        String extraPath = getExtraPath();
        log.info("extraPath：" + extraPath);
        log.info("extraLinuxPath：" + extraPath.replaceAll("\\\\", "/"));
        return extraPath.replaceAll("\\\\", "/");
    }
}
