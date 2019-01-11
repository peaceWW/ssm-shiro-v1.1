<%--
  Created by IntelliJ IDEA.
  User: yutons
  Date: 2017/12/13/013
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ssm+shiro后台管理系统(权限控制)</title>
    <link rel="Bookmark" href="${pageContext.request.contextPath}/resources/image/favicon.ico">
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/resources/image/favicon.ico"/>
    <link href="${pageContext.request.contextPath}/resources/login/css/style.css" type="text/css" rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/resources/jquery-3.1.0.min.js" type="text/javascript"></script>
</head>
<body>
<div class="wrap">
    <div class="logo">
        <span class="logo-sub">ssm+shiro后台管理系统(权限控制)</span>
    </div>
    <div class="banner-show" id="js_ban_content" style="margin-top: 5px;">

        <!-- 不存在 显示默认 -->
        <div class="cell bns">
            <div class="con" style="background-image:url('${pageContext.request.contextPath}/resources/login/images/mid_banner/background.jpg');"></div>
        </div>
    </div>

    <div class="container">
        <div class="register-box">
            <div class="reg-slogan">欢迎登录</div>
            <form id="js-form-mobile" class="reg-form" method="post"
                  action="${pageContext.request.contextPath}/login">
                <span style="color: red">${msg}</span>
                <div class="cell">
                    <span class="icon user"></span>
                    <input type="text" name="username" id="username" tabindex="1" class="text" style="height: 40px;width: 291px" maxlength="20" value="" placeholder="工号"/>
                </div>
                <div class="cell">
                    <span class="icon password"></span>
                    <input type="password" name="password" id="password" tabindex="2" class="text" style="height: 40px;width: 291px" maxlength="20"  placeholder="密码"/>
                </div>
                <div class="other">
                    <input type="checkbox" name="rememberMe" id="checkbox_id"/> 记住用户名
                </div>
                <font id='loginMsg' style="color: red;"></font>
                <div class="bottom" style="margin-top: 10px;">
                    <input type="submit" class="button btn-green" style="height: 45px;width: 292px" value="登录">
                </div>
            </form>
        </div>
    </div>
</div>
<div id="js-footer" class="footer">
    <div class="w1000">
        <p> Copyrights &copy; yutons All Rights Reserved.</p>
    </div>
</div>
<!--Apache Shiro会话超时，登录页面跳转到父窗体的解决方法-->
<script>
    window.onload = function () {
        if (window.parent.window != window) {
            window.top.location = "${pageContext.request.contextPath}/login";
        }
    }
</script>
</body>
</html>
