package com.weizhiblog.handler;

/*
 *
 * @createTime 07-17 16:27:2
 * @author Touka_
 * @classname com.weizhiblog.handler.GlobalExceptionHandler
 * @lastModifiedTime 7月17日   16:27:2
 */

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.exception.MyRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseBean handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error(e.getMessage(), e);
        return ResponseBean.builder().status(-999).message(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()).build();
    }

    @ExceptionHandler(MyRuntimeException.class)
    public ResponseBean handleMyRuntimeException(MyRuntimeException e) {
        log.error(e.getMessage(), e);
        return e.getResponseBean();
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean handleOtherException(Exception e) {
        log.error(e.getMessage(), e);
        return ResponseBean.builder()
                .status(-1)
                .message(e.getMessage())
                .object(e.toString())
                .build();
    }


}
