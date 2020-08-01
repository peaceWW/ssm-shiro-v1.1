<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, trade-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <title>贸易列表</title>
</head>

<body>
<div class="layui-container">
    <!--
描述：引入公共html
    -->
    <jsp:include page="inc.jsp"></jsp:include>
    <div class="layui-row" >
        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <div class="x-nav layui-elem-quote">
						<span class="layui-breadcrumb">
             <a><cite><i class="layui-icon" style="line-height:25px">&#xe68e  </i>首页</cite></a>
              <a href="${pageContext.request.contextPath}/trade/index">订单列表</a>
              <a><cite>贸易批次列表</cite></a>
            </span>
                <a class="layui-btn layui-btn-mini" style="line-height:1.0em;margin-top:1px;float:right"
                   href="${pageContext.request.contextPath}/trade/detail?tradeNo=${tmTradeInfo.tradeNo}" title="刷新">
                    <i class="layui-icon" style="line-height:25px">&#x1002</i></a>
            </div>
        </div>

        <a class="layui-btn layui-btn-danger" role="button"
           style="margin-right: 10px;float: right"
           href="${pageContext.request.contextPath}/trade/index">返回上一级</a>
        <div class="layui-btn-group demoTable">
            <button class="layui-btn" data-type="getCheckData">合并</button>
        </div>

        <div class="layui-col-xs12 layui-col-sm12 layui-col-md12">
            <table class="layui-table"
                   lay-data="{height: 'full-110',even: true,url:'${pageContext.request.contextPath}/trade/batch/list?tradeNo=${tmTradeInfo.tradeNo}',limits:[10,30,50,100], limit: 10,page:true,id:'idTest'}"
                   lay-filter="trade">

                <thead>
                <tr>
                    <th lay-data="{type:'checkbox', fixed: 'left',checkbox:true}"></th>
                    <th lay-data="{field:'id',align:'center', width:100, sort: true}">ID</th>
                    <th lay-data="{field:'tradeNo',align:'center', width:300, sort: true}">贸易编号</th>
                    <th lay-data="{field:'lot', align:'center',width:100, sort: true}">货物数量</th>
                    <th lay-data="{width:150, align:'center', toolbar: '#toolBar'}">操作</th>
                </tr>
                </thead>
            </table>
        </div>
    </div>
</div>
<script type="text/html" id="toolBar">
    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="edit">拆分</a>

</script>

<script src="${pageContext.request.contextPath}/resources/layui/layui.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery-3.1.0.min.js"></script>
<script>


    layui.use(['table', 'element', 'jquery'], function () {
        var table = layui.table,
            element = layui.element,
            $ = layui.jquery;


        //监听表格复选框选择
        table.on('checkbox(trade)', function(obj){
            console.log(obj)
        });
        //监听工具条
        table.on('tool(trade)', function(obj){
            var data = obj.data;
            if(obj.event === 'detail'){
                layer.msg('ID：'+ data.id + ' 的查看操作');
            } else if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    obj.del();
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                //示范一个公告层
                layer.open({
                    type: 1
                    ,title: '拆分' //不显示标题栏
                    ,closeBtn: true
                    ,area: '600px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<table class="layui-table" id="table" lay-filter="table">\n' +
                        '                <thead>\n' +
                        '                    <tr>\n' +
                        '                        <td>数量</td>\n' +
                        '                        <td>操作</td>\n' +
                        '                    </tr>\n' +
                        '                </thead>\n' +
                        '                <tbody>\n' +
                        '                            <input type="hidden" id="splitId"  value='+data.id+'></td>\n' +
                        '                    <tr>\n' +
                        '                        <td><input type="text" id="layui-input" class="layui-input" name="price" value='+data.lot+'>' +
                        '                        <td>\n' +
                        '                            <a class="layui-btn layui-btn-xs add">添加</a>\n' +
                        '                            <a class="layui-btn layui-btn-danger layui-btn-xs del">删除</a>\n' +
                        '                        </td>\n' +
                        '                    </tr>\n' +
                        '                </tbody>\n' +
                        '            </table>' +
                        '<button class="layui-btn" type="button" onclick="split();">确定</button>'

                });
            }
        });
        //触发事件
        var active = {
            getCheckData: function(){ //获取选中数据
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                $.ajax({
                    type: 'POST',
                    contentType : "application/json",
                    url: '${pageContext.request.contextPath}/trade/mergeTrade',
                    data: JSON.stringify(data),
                    success: function (d) {
                        layer.msg('合并成功', {
                            time: 20000, //20s后自动关闭
                            area: '300px;',
                            btn: '确定',
                            yes: function(){
                                location.reload();
                            }
                        });
                    },
                    dataType: "json"
                });
            }
            ,getCheckLength: function(){ //获取选中数目
                var checkStatus = table.checkStatus('idTest')
                    ,data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
            }
            ,isAll: function(){ //验证是否全选
                var checkStatus = table.checkStatus('idTest');
                layer.msg(checkStatus.isAll ? '全选': '未全选')
            }, splitTrade: function(){ //拆分对象
                layer.msg('123123');
                var o = $("*[name='price']").map(function(){return $(this).val()}).get();
                // 将对象o转换为数组
                var j=$.toJSON(o);
                layer.msg(j)
            }

        };

        $('#splitTrade').on('click', function(){
            layer.msg('123123');
            // var othis = $(this), method = othis.data('method');
            // active[method] ? active[method].call(this, othis) : '';
        });
        $('#addTrade').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';
        });

        $('.demoTable .layui-btn').on('click', function(){
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

        $('body').on('click', '.add', function() {
            //你要添加的html
            var html = '<tr>'+
                '<td><input type="text" id="layui-input" class="layui-input" name="price"></td>'+
                '<td>'+
                '<a class="layui-btn layui-btn-xs add">添加</a>'+
                '<a class="layui-btn layui-btn-danger layui-btn-xs del">删除</a>'+
                '</td>'+
                '</tr>';
            //添加到表格最后
            $(html).appendTo($('#table tbody:last'));
        });

        $('body').on('click', '.del', function() {
            if ($('#table tbody tr').length === 1) {
                layer.msg('只有一条不允许删除。', {
                    time : 2000
                });
            } else {
                //删除当前按钮所在的tr
                $(this).closest('tr').remove();
            }
        });

        //弹出层
        var msg = "${msg}";
        if (msg.length != "") {
            layer.msg(msg, {icon: 6});
            $("#msg").text("");
        }

    });

    function split() {

        // 将对象o转换为数组
        var opt={"tradeBatchId":"","tradeNo":"","tmTradeBatchInfoList":""};
        var arr=[];
        opt.tradeBatchId=$('#splitId').val();
        var check=0;
        $("*[name='price']").each(function(i,item){

            var ret = /^\+?[1-9]\d*$/;;
            if(!ret.test(item.value)){
                check=1;
            }
            var l = {"lot":item.value,"tradeNo":"${tmTradeInfo.tradeNo}"};
            arr.push(l);
        });
        if(check!=0){
            layer.msg('只能填写正整数', {
                time: 20000, //20s后自动关闭
                area: '300px;',
                btn: '确定',
                yes: function(){
                    layer.closeAll();
                }
            });
        }else{
            opt.tmTradeBatchInfoList = arr;
            opt.tradeNo = "${tmTradeInfo.tradeNo}";
            $.ajax({
                type: 'POST',
                contentType : "application/json",
                url: '${pageContext.request.contextPath}/trade/splitTrade',
                data: JSON.stringify(opt),
                success: function (result) {
                    if("SUCCESS"==result.code){
                        layer.msg('拆分成功', {
                            time: 20000, //20s后自动关闭
                            area: '300px;',
                            btn: '确定',
                            yes: function(){
                                location.reload();
                                layer.closeAll();
                                location.reload();
                            }
                        });
                    }else{
                        layer.msg('拆分失败:'+result.message, {
                            time: 20000, //20s后自动关闭
                            area: '300px;',
                            btn: '确定',
                            yes: function () {
                                location.reload();
                                layer.closeAll();
                                location.reload();
                            }
                        })
                    }
                },
                dataType: "json"
            });
        }



    }
</script>
</body>

</html>