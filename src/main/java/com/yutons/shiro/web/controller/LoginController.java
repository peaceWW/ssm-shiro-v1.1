package com.yutons.shiro.web.controller;

import com.yutons.shiro.bean.admin.User;
import com.yutons.shiro.service.admin.UserService;
import com.yutons.shiro.util.TokenUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by yutons on 2016/9/19.
 */
@RequestMapping(value = "/")
@Controller
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(User user, boolean rememberMe, Model model) {
        String msg = null;
        try {
            //用户登录
            user = TokenUtil.login(user, rememberMe);

        } catch (Exception e) {
            /*User login = userService.login(user.getUsername(), user.getPassword());
            if (login.getStatus() == 0) {
                msg = "用户已经被禁用，请联系管理员启用该账号";
            } else {
                msg = "用户名或密码不正确";
            }*/
            msg = "用户名或密码不正确";
        } finally {
            if (msg == null) {
                return "redirect:/admin/pagejump/index";
            }

            model.addAttribute("msg", msg);
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        Subject subject = SecurityUtils.getSubject();
        /*System.out.println("session信息已经成功清除!" + session.getAttribute("user"));
        session.removeAttribute("user");*/
        subject.logout();
        model.addAttribute("msg", "您已经退出登录,请重新登录");
        return "login";
    }

    @RequestMapping(value = "/unAuthorization")
    public String unAuthorization() {
        return "unAuthorization";
    }
}
