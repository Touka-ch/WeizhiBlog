package com.weizhiblog.utils;

/*
 *
 * @createTime 07-30 15:52:26
 * @author Touka_
 * @classname com.weizhiblog.utils.dateUtils
 * @lastModifiedTime 7月30日   15:52:26
 */

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class DateUtils {
    public static int differentDaysByMillisecond(Date date1, Date date2) {
        return (int) ((date2.getTime() - date1.getTime()) / (1000 * 3600 * 24));
    }
}
