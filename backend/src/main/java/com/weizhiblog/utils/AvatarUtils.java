package com.weizhiblog.utils;

/*
 *
 * @createTime 08-07 11:38:15
 * @author Touka_
 * @classname com.weizhiblog.utils.AvatarUtils
 * @lastModifiedTime 8月7日   11:38:14
 */

public class AvatarUtils {

    private final static String[] AVATARS = new String[]{
            "http://47.115.41.198:8090/public/2020/08/07/03/41/27/298/touxiang1.png",
            "http://47.115.41.198:8090/public/2020/08/07/03/41/52/375/touxiang2.png",
            "http://47.115.41.198:8090/public/2020/08/07/03/42/19/360/touxiang3.png",
            "http://47.115.41.198:8090/public/2020/08/07/03/42/34/953/touxinag4.png",
            "http://47.115.41.198:8090/public/2020/08/07/03/43/27/270/touxiang5.png",
            "http://47.115.41.198:8090/public/2020/08/07/03/43/50/34/touxiang6.png"
    };

    public static String getRandAvatar() {
        return AVATARS[(int) (Math.random() * AVATARS.length)];
    }
}
