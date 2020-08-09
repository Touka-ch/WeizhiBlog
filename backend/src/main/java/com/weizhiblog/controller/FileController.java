package com.weizhiblog.controller;

/*
 *
 * @createTime 08-04 15:58:46
 * @author Touka_
 * @classname com.weizhiblog.controller.FileController
 * @lastModifiedTime 8月4日   15:58:46
 */

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@Controller
@Slf4j
public class FileController {
    @Autowired
    private HttpServletRequest request; //自动注入request


    public FileController() throws IOException {
    }

    @PostMapping("/file")
    @ResponseBody
    public ResponseBean file(MultipartFile file) throws IOException {
        String absoluteDir = "/usr/weizhiblog/public/";
        String os = System.getProperty("os.name");
        String extraPath = DateUtils.getLinuxExtraPath();
        log.info(os);
        if (os.startsWith("Win")) {
            absoluteDir = new File("").getCanonicalPath() + "\\backend\\src\\main\\resources\\static\\public\\";
            extraPath = DateUtils.getExtraPath();
        }
        String uri = "javascript:void(0)";
        StringBuffer url = request.getRequestURL();
        String tempContextUrl = url.delete(url.length() - request.getRequestURI().length(), url.length()).append("/").toString();
        log.debug("域名是：" + tempContextUrl);
        if (file.isEmpty()) {
            return ResponseBean.builder().status(-2).message("文件为空！").build();
        }
        String originalFilename = file.getOriginalFilename();
        String path = absoluteDir + extraPath + originalFilename;
        if (os.startsWith("Win")) {
            extraPath = extraPath.replaceAll("\\\\", "/");
        }
        log.info("文件绝对位置是：" + path);
        File des = new File(path);
        if (des.exists()) {
            return ResponseBean.builder().status(-3).message("文件已存在！").build();
        }
        //判断文件父目录是否存在
        if (!des.getParentFile().exists()) {
            des.getParentFile().mkdirs();
        }
        try {
            file.transferTo(des);
            log.info("文件绝对位置是：" + path);
            uri = tempContextUrl + "public/" + extraPath + originalFilename;
            log.info("访问地址是：" + uri);
            //写入数据库
        } catch (IOException e) {
            log.error(e.toString());
            return ResponseBean.builder().status(-4).message("文件出错").build();
        }
        return ResponseBean.builder().status(1).message("成功,但不能立即查看，需等待转换").object(uri).build();
    }
}
