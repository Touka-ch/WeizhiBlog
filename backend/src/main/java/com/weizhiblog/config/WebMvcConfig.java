package com.weizhiblog.config;

/*
 *
 * @createTime 08-05 12:41:56
 * @author Touka_
 * @classname com.weizhiblog.config.WebMvcConfig
 * @lastModifiedTime 8月5日   12:41:56
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;
import java.io.IOException;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    private final String filePathWindow = new File("").getCanonicalPath() + "\\backend\\src\\main\\resources\\static\\public\\";
    private final String filePathLinux = "/usr/weizhiblog/public/";

    public WebMvcConfig() throws IOException {
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 可限制哪个请求可以通过跨域
                .allowedHeaders("*")  // 可限制固定请求头可以通过跨域
                .allowedMethods("*") // 可限制固定methods可以通过跨域
                .allowedOrigins("*")  // 可限制访问ip可以通过跨域
                .allowCredentials(true) // 是否允许发送cookie
                .exposedHeaders(HttpHeaders.SET_COOKIE);
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String os = System.getProperty("os.name");
        log.info("操作系统是：" + os);
        //如果是Windows系统
        if (os.toLowerCase().startsWith("win")) {
            registry.addResourceHandler("/public/**")
                    .addResourceLocations("file:" + filePathWindow);
        } else {  //linux 和mac
            registry.addResourceHandler("/public/**")
                    .addResourceLocations("file:" + filePathLinux);
        }

        super.addResourceHandlers(registry);
    }
}
