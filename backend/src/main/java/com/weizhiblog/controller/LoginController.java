package com.weizhiblog.controller;

import com.weizhiblog.bean.ResponseBean;
import com.weizhiblog.bean.dto.PasswordDto;
import com.weizhiblog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;

/*
 *
 * @createTime 08-05 20:27:43
 * @author Touka_
 * @classname com.weizhiblog.controller.LoginController
 * @lastModifiedTime 8月5日   20:27:43
 */
@RestController
public class LoginController {
    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public ResponseBean login() {
        return ResponseBean.builder().status(400).message("尚未登录!").build();
    }

    @PostMapping("/logout")
    public ResponseBean logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return ResponseBean.builder().status(1).message("注销成功！").build();
    }


    @RequestMapping(value = "/password", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseBean updateUserPassword(@RequestBody @NotNull PasswordDto passwordDto) {
        return loginService.updateUserPassword(passwordDto);
    }


}
