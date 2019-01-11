<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="Bookmark" href="${pageContext.request.contextPath}/resources/image/favicon.ico">
    <link rel="Shortcut Icon" href="${pageContext.request.contextPath}/resources/image/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/h-ui/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css"
          href="${pageContext.request.contextPath}/resources/h-ui/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/h-ui/skin/green/skin.css"
          id="skin"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/h-ui/css/style.css"/>
    <title>ssm+shiro后台管理系统(权限控制)</title>
</head>

<body>
<header class="navbar-wrapper">
    <div class="navbar navbar-fixed-top">
        <div class="container-fluid cl">
            <a class="logo navbar-logo f-l mr-10 hidden-xs" href="javascript:;">ssm+shiro后台管理系统(权限控制)</a>
            <a class="logo navbar-logo-m f-l mr-10 visible-xs" href="javascript:;">后台管理系统(权限控制)</a>
            <span class="logo navbar-slogan f-l mr-10 hidden-xs"></span>
            <a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;"
               onclick="showAllBtn()">&#xe667;</a>
            <nav class="nav navbar-nav">
                <ul class="cl">
                    <li class="dropDown dropDown_hover">
                        <%--遍历获取头部功能分类--%>
                        <c:forEach items="${list}" var="menus">
                    <li class="navbar-levelone current">
                        <c:if test="${menus.size()>0}">
                            <a href="javascript:;">${menus.get(0).menuname}</a>
                        </c:if>
                    </li>
                    </c:forEach>
                    </li>
                </ul>
            </nav>
            <nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
                <ul class="cl">
                    <li id="getTimeNow"></li>
                    <li></li>
                    <li></li>
                    <li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员工工号:&nbsp;&nbsp;&nbsp;&nbsp;<shiro:principal
                            property="username"/>
                        &nbsp;&nbsp;姓名:
                    </li>
                    <li class="dropDown dropDown_hover">
                        <a href="#" class="dropDown_A">【<shiro:principal property="staffname"/>】<i class="Hui-iconfont">&#xe6d5;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <%--<li>
                                <a href="javascript:;" onClick="member_info()">个人信息</a>
                            </li>
                            <li>
                                <a href="javascript:;" onClick="member_password_edit()">修改密码</a>
                            </li>--%>
                            <li>
                                <a href="${pageContext.request.contextPath}/logout">退出</a>
                            </li>
                        </ul>
                    </li>
                    <%--<li id="Hui-msg">
                        <a href="#" title="消息"><span class="badge badge-danger">1</span><i class="Hui-iconfont"
                                                                                           style="font-size:18px">&#xe68a;</i></a>
                    </li>
                    <li id="Hui-skin" class="dropDown right dropDown_hover">
                        <a href="javascript:;" class="dropDown_A" title="换肤"><i class="Hui-iconfont"
                                                                                style="font-size:18px">&#xe62a;</i></a>
                        <ul class="dropDown-menu menu radius box-shadow">
                            <li>
                                <a href="javascript:;" data-val="default" title="默认（蓝色）">默认（蓝色）</a>
                            </li>
                        </ul>
                    </li>--%>
                </ul>
            </nav>
        </div>
    </div>
</header>
<div class="dislpayArrow hidden-xs">
    <a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
</div>
<aside class="Hui-aside">
    <%--遍历获取左侧菜单栏显示:根据用户角色动态加载--%>
    <c:forEach items="${list}" var="menus">
        <%--${menus.get(0).childMenu}--%>
        <div class="menu_dropdown bk_2">
            <c:forEach items="${menus.get(0).childMenu}" var="childMenu">
                <dl id="menu-member">
                    <dt><i class="Hui-iconfont"></i>${childMenu.menuname}<i
                            class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i>
                    </dt>
                    <dd>
                        <ul>
                            <c:forEach items="${childMenu.childMenu}" var="menu">
                                <li>
                                    <a data-href="${pageContext.request.contextPath}${menu.url}"
                                       data-title="${menu.menuname}" href="javascript:;">${menu.menuname}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </dd>
                </dl>
            </c:forEach>
        </div>
    </c:forEach>
    <div class="menu_dropdown bk_2">
        <dl id="menu-member">
            <dt><%--<i class="Hui-iconfont"></i>--%><a data-href="${pageContext.request.contextPath}/logout"
                                               data-title="退出登录" href="javascript:;">退出登录</a>
            </dt>
        </dl>
    </div>
</aside>
<section class="Hui-article-box">
    <div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
        <div class="Hui-tabNav-wp">
            <ul id="min_title_list" class="acrossTab cl">
                <li class="active">
                    <span title="我的桌面" data-href="${pageContext.request.contextPath}/admin/pagejump/welcome">我的桌面</span>
                    <em></em></li>
            </ul>
        </div>
        <div class="Hui-tabNav-more btn-group">
            <a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
            <a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
        </div>
    </div>
    <div id="iframe_box" class="Hui-article">
        <div class="show_iframe">
            <div style="display:none" class="loading"></div>
            <c:if test="${sessionScope.user.username=='8888'}">
                <iframe name="ifr_body" scrolling="yes" frameborder="0"
                        src="${pageContext.request.contextPath}/admin/monitor/show"></iframe>
            </c:if>
            <c:if test="${sessionScope.user.username!='8888'}">
                <iframe name="ifr_body" scrolling="yes" frameborder="0"
                        src="${pageContext.request.contextPath}/admin/pagejump/welcome"></iframe>
            </c:if>
        </div>
    </div>
</section>

<div class="contextMenu" id="Huiadminmenu">
    <ul>
        <li id="closethis">关闭当前</li>
        <li id="closeall">关闭全部</li>
    </ul>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/h-ui/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/h-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/h-ui/js/H-ui.admin.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->

<!--时间显示-->
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/common/getTime.js"></script>
<script type="text/javascript"
        src="${pageContext.request.contextPath}/resources/h-ui/lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
<script type="text/javascript">
    /**
     *当页面宽度小于768时,将头部菜单列表添加到左侧菜单列表
     */
    function showAllBtn() {
        var width = $(window).width();
        if (width < 768) {
            $(".Hui-aside div").attr('style', 'display:block');
        }
    }

    /**
     *
     * 当页面宽度大于等于768时,将属于头部菜单栏的菜单从左侧移除,并添加到头部
     */
    $(window).resize(function () {
        var width = $(window).width();
        //console.log(width);
        /*if (width < 768) {
            $(".Hui-aside div").attr('style', 'display:block');
        }*/
        if (width >= 768) {
            $(".Hui-aside div").attr('style', 'display:none');
            $(".Hui-aside div:first-child").attr('style', 'display:block');
        }
    });
    $(function () {
        $("body").Huitab({
            tabBar: ".navbar-wrapper .navbar-levelone",
            tabCon: ".Hui-aside .menu_dropdown",
            className: "current",
            index: 0,
        });

    });
    //判断当前登录用户
    var username = "${sessionScope.user.username }";
    if (username == '8888') {
        setTimeout(displaynavbar($(".pngfix").get(0).tagName, 0));
    }
</script>
</body>
</html>