<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/layui/css/layui.css" media="all">
    <style>
        body {
            margin: 10px;
        }

        .demo-carousel {
            height: 200px;
            line-height: 200px;
            text-align: center;
        }
    </style>
</head>

<body>
<div class="layui-container">
    <!--
作者：yuton.yao@qq.com
时间：2017-09-01
描述：引入公共html
-->
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <p style="font-size: 26px;">ssm+shiro后台管理系统(权限控制)！</p>
        </div>
    </div>
    <div><p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
            style="font-family:等线; font-size:14pt">该系统是基于</span><span style="font-family:等线; font-size:14pt">spring,springMVC,mybatis框架开发的,权限控制采用shiro进行控制,实现用户,角色,权限,菜单的增删改查以及基本的权限控制.</span>
    </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">部分代码采用</span><span style="font-family:等线; font-size:14pt">mybatisCodeHelper生成,权限控制可以做到菜单和按钮级别,用户可选择记住我自动登录,缓存采用ehcache实现</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">&#xa0;</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">功能模块</span><span style="font-family:等线; font-size:14pt">:</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">个人中心</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">个人资料</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">密码修改</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">用户中心</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">用户管理</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">系统权限</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">角色管理</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">权限管理</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="width:21pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">菜单管理</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">&#xa0;</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">开发环境</span><span style="font-family:等线; font-size:14pt">:</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">jdk1.8</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">idea2017-2</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">maven</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="width:1pt; text-indent:0pt; display:inline-block"></span><span
                style="font-family:等线; font-size:14pt">mysql5.5</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">&#xa0;</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">编码格式</span><span
                style="font-family:等线; font-size:14pt">:utf-8</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">&#xa0;</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">前台地址</span><span
                style="font-family:等线; font-size:14pt">:ip+端口</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">后台地址</span><span style="font-family:等线; font-size:14pt">:ip+端口/admin</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">&#xa0;</span></p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">超级管理员</span><span style="font-family:等线; font-size:14pt">:admin,密码:000000</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">管理员</span><span style="font-family:等线; font-size:14pt">:yutons,密码:000000</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">用户</span><span style="font-family:等线; font-size:14pt">:user,密码:000000</span>
        </p>
        <p style="margin:0pt; orphans:0; text-align:justify; text-indent:20pt; widows:0"><span
                style="font-family:等线; font-size:14pt">不同的角色拥有不同的权限</span></p></div>
    <div class="cnzz" style="display: none;">
        <script src="http://s95.cnzz.com/stat.php?id=1253551100&web_id=1253551100" language="JavaScript"></script>
    </div>
</div>
<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['element', 'jquery'], function () {
        var element = layui.element,
            $ = layui.jquery;
    });
</script>
</body>

</html>