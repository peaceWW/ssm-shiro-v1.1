<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>贸易列表</title>
</head>

<body>
<div class="layui-container">
    <!--
描述：引入公共html
    -->
    <jsp:include page="inc.jsp"></jsp:include>
    <div class="layui-row">
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <div class="x-nav layui-elem-quote">
						<span class="layui-breadcrumb">
             <a><cite><i class="layui-icon" style="line-height:25px">&#xe68e  </i>首页</cite></a>
              <a><cite>贸易列表</cite></a>
            </span>
                <a class="layui-btn layui-btn-mini" style="line-height:1.0em;margin-top:1px;float:right"
                   href="${pageContext.request.contextPath}/trade/index" title="刷新">
                    <i class="layui-icon" style="line-height:25px">&#x1002</i></a>
            </div>
        </div>
        <a class="layui-btn layui-btn-normal" role="button"
           style="margin-right: 10px;float: right" href="${pageContext.request.contextPath}/trade/add">添加贸易</a>
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <table class="layui-table"
                   lay-data="{height: 'full-110',even: true,url:'${pageContext.request.contextPath}/trade/list',limits:[10,30,50,100], limit: 10,page:true,id:'id'}"
                   lay-filter="user">
                <thead>
                <tr>
                    <!--<th lay-data="{checkbox:true, fixed: true}"></th>-->
                    <th lay-data="{field:'id',align:'center', width:100, sort: true}">ID</th>
                    <th lay-data="{field:'tradeNo',align:'center', width:200, sort: true}">贸易编号</th>
                    <th lay-data="{field:'tradeAmount', align:'center',width:100, sort: true}">货物数量</th>
                    <th lay-data="{width:160, align:'center', toolbar: '#toolBar'}">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="update">修改</a>
    <a class="layui-btn layui-btn-warm layui-btn-mini" lay-event="delete">删除</a>
    <a class="layui-btn layui-btn-warm layui-btn-mini" lay-event="detail">详情</a>
</script>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script>
    layui.use(['table', 'element', 'jquery'], function () {
        var table = layui.table,
            element = layui.element,
            $ = layui.jquery;

        //监听导航点击
        element.on('nav(demo)', function (elem) {
            //console.log(elem)
            layer.msg(elem.text());
        });
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'update') {
                window.location.href = '${pageContext.request.contextPath}/trade/update/' + data.id;
            }
            if (obj.event === 'detail') {
                window.location.href = '${pageContext.request.contextPath}/trade/detail?tradeNo='+data.tradeNo;
            }




            //
            if (obj.event === 'delete') {
                var userId = obj.data.id;
                layer.confirm('您确定要删除该订单吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    layer.closeAll();
                    $.post("${pageContext.request.contextPath}/trade/delete/"+data.id,{dataType: "json"}, function (data) {
                        if ("SUCCESS"==data.code) {
                            //如果修改成功,则刷新页面
                            layer.msg('删除成功!', {icon: 1});
                            window.location.reload(); //刷新当前页面

                        }
                    }).error(function () {
                        layer.msg('删除失败!', {icon:5});
                    });
                });
            }



            //此处需要完善
            if (obj.event === 'split') {
                var userId = obj.data.userId;
                layer.confirm('您确定要删除该用户吗？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    layer.closeAll();
                    $.post("${pageContext.request.contextPath}/trade/deleteUserById", {userId: userId}, function (data) {
                        if ("success"==data) {
                            //如果修改成功,则刷新页面
                            window.location.reload(); //刷新当前页面
                            layer.msg('删除成功!', {icon: 1});
                        }
                    }).error(function () {
                        layer.msg('删除失败!', {icon:5});
                    });
                });
            }

        });
        //弹出层
        var msg = "${msg}";
        if (msg.length != "") {
            layer.msg(msg, {icon: 6});
            $("#msg").text("");
        }
    });
</script>
</body>

</html>