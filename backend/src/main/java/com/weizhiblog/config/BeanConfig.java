package com.weizhiblog.config;

/*
 *
 * @createTime 08-03 17:14:16
 * @author Touka_
 * @classname com.weizhiblog.config.BeanConfig
 * @lastModifiedTime 8月3日   17:14:16
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanConfig {
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
