package com.weizhiblog.config;

/*
 *
 * @createTime 08-03 13:42:27
 * @author Touka_
 * @classname com.weizhiblog.config.MySecurityConfig
 * @lastModifiedTime 8月3日   13:42:27
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.User;
import com.weizhiblog.filter.MyUsernamePasswordAuthenticationFilter;
import com.weizhiblog.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

@Configuration
@Slf4j
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    LoginService loginService;
    private final String[] WHITE_LIST = new String[]{
            "/doLogin",
            "/login",
            "/logout",
            "/articles/",
            "/articles/**",
            "/users",
            "/public/**",
            "/comments/**",
            "/comments"
    };

    private final String[] ADMIN_LIST = new String[]{
            "/role/setAdmin/**",
            "/role/setOrdinary/**",
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(WHITE_LIST).permitAll()
                .antMatchers(ADMIN_LIST).hasRole("1")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and().csrf().disable()
                .exceptionHandling()
                .accessDeniedHandler((req, resp, e) -> {
                    resp.setStatus(403);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(
                            ResponseBean.builder().status(403).message("权限不足！").build()
                    ));
                    out.flush();
                    out.close();
                });
        http.exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                    resp.setStatus(403);
                    resp.setContentType("application/json;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.write(new ObjectMapper().writeValueAsString(
                            ResponseBean.builder().status(403).message("尚未登录!").build()
                    ));
                    out.flush();
                    out.close();
                });

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(loginService);
        super.configure(auth);
    }

    @Bean
    MyUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
        MyUsernamePasswordAuthenticationFilter filter = new MyUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationSuccessHandler((req, resp, authentication) -> {
            User principal = (User) authentication.getPrincipal();
            principal.setPassword(null);
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(
                    ResponseBean.builder().status(1).message("登录成功").object(principal).build()
            ));
            out.flush();
            out.close();
        });
        filter.setAuthenticationFailureHandler((req, resp, e) -> {
            resp.setContentType("application/json;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.write(new ObjectMapper().writeValueAsString(
                    ResponseBean.builder().status(400).message("登录失败").build()
            ));
            out.flush();
            out.close();
        });
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
