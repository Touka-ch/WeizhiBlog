package com.weizhiblog.exception;

/*
 *
 * @createTime 07-19 8:33:19
 * @author Touka_
 * @classname com.weizhiblog.exception.MyRuntimeException
 * @lastModifiedTime 7月19日   8:32:45
 */

import lombok.Getter;

@Getter
public class MyRuntimeException extends RuntimeException {
    private final Integer status;
    private final String message;

    public MyRuntimeException(Integer status, String message) {
        super(message);
        this.message = message;
        this.status = status;
    }
}
