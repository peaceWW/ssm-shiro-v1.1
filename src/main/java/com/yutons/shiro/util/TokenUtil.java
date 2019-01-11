package com.yutons.shiro.util;

import com.yutons.shiro.bean.admin.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * @author yutons
 * @desc Shiro管理下的Token工具类
 * @date 2017/10/25 11:31
 */
public class TokenUtil {
    /**
     * 登录
     *
     * @param token
     * @param user
     * @param rememberMe @return
     */
    public static User login(User user, boolean rememberMe) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user.getPassword());
        token.setRememberMe(rememberMe);
        subject.login(token);
        return getUser();
    }

    /**
     * 获取当前登录的用户User对象
     *
     * @return
     */
    public static User getUser() {
        return (User) SecurityUtils.getSubject().getPrincipal();
    }
}
