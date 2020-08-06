package com.weizhiblog.config;

/*
 *
 * @createTime 08-05 20:18:1
 * @author Touka_
 * @classname com.weizhiblog.config.CustomAuthenticationEntryPoint
 * @lastModifiedTime 8月5日   20:18:0
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weizhiblog.bean.ResponseBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(200);
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.write(new ObjectMapper().writeValueAsString(
                ResponseBean.builder().status(403).message("尚未登录！").build()
        ));
        out.flush();
        out.close();
    }
}
