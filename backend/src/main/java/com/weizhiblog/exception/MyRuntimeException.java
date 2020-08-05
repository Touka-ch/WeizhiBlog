package com.weizhiblog.exception;

/*
 *
 * @createTime 07-19 8:33:19
 * @author Touka_
 * @classname com.weizhiblog.exception.MyRuntimeException
 * @lastModifiedTime 7月19日   8:32:45
 */

import com.weizhiblog.bean.ResponseBean;
import lombok.Getter;

@Getter
public class MyRuntimeException extends RuntimeException {
    private ResponseBean responseBean;

    public MyRuntimeException(ResponseBean responseBean) {
        super(responseBean.getMessage());
        this.responseBean = responseBean;
    }
}
