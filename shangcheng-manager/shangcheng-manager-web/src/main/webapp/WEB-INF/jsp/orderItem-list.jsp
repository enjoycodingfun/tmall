<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/html5shiv.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>订单项列表</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单项列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <span class="r">共有数据：<strong id="num">0</strong> 条</span> </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" name="" value=""></th>
                        <th width="80">订单项编号</th>
                        <th width="80">所属订单</th>
                        <th width="80">商品编号</th>
                        <th width="80">商品名称</th>
                        <th width="50">购买数量</th>
                        <th width="80">商品单价(￥)</th>
                        <th width="50">操作</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/datatables/dataTables.colReorder.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/common.js"></script>
<script type="text/javascript">
     var bbb = ${oid}
    //    datatables配置
    $(document).ready(function () {
        $('.table').DataTable({
            "processing": true,//加载显示提示
            "ajax": {
                url:"${pageContext.request.contextPath}/order/listOrderItem?oid="+bbb,
                type: 'GET',
            },
            "columns": [
                /*{ "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.orderId+"\" type=\"checkbox\" value=\"\">";
                    }
                },*/

                { "data": "otid",
                    render : function(data){
                        return "<input type=\"checkbox\" name=\"checkbox\" value="+data+">";
                    }
                },
                { "data": "otid"},
                { "data": "oid"},
                { "data": "pid"},
                { "data": "pname"},
                { "data": "num"},
                { "data": "price"},
                /*{ "orders": "createTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "updateTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "paymentTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "closeTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                { "data": "endTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },*/
               /* { "data": "ordertime",
                    render : function(data) {
                        return "<a href=\"javascript:;\" onclick=\"datadel()\" class=\"btn btn-danger radius\" ><i class=\"Hui-iconfont\">&#xe6e2;</i> 删除</a>";
                    }
                }*/
                {
                    "data": null,
                    render : function(data) {
                        return " <a title=\"删除\" href=\"javascript:;\" onclick=\"orderItem_del("+data.otid+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a> ";
                    }
                }
            ],
            /*"aaSorting": [[ 6, "desc" ]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                {"orderable":false,"aTargets":[0,12]}// 制定列不参与排序
            ],*/
            language: {
                url: '${pageContext.request.contextPath}/lib/datatables/Chinese.json'
            },
            colReorder: true
        });

    });

    orderItemCount();

    function orderItemCount() {
        $.ajax({
            url:"${pageContext.request.contextPath}/order/getOrderItemConut",
            type: 'GET',
            success:function (data) {
                /*if(data.success!=true){
                    layer.alert(data.message,{title: '错误信息',icon: 2});
                    return;
                }*/
                $("#num").html(data.count);
            },
            /*error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }*/
        });
    }

    /*订单-删除*/
    function orderItem_del(otid){
        layer.confirm('确认要删除ID为\''+otid+'\'的订单吗？',function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/order/removeOrderItem?otid='+otid,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    if(data.result!=1){
                        layer.alert('删除失败!',{icon:2,time:1000});
                        return;
                    }
                    orderItemCount();
                    refresh();
                    layer.msg('已删除!',{icon:1,time:1000});
                }
                /*error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }*/
            });
        });
    }

    /*批量删除*/
    function datadel() {
        var cks=document.getElementsByName("checkbox");
        var count=0,ids="";
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids+=cks[i].value+",";
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        /*去除末尾逗号*/
        if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/order/removeListOrderItem?ids='+ids,
                dataType: 'json',
                success:function(data){
                    layer.close(index);
                    if(data.result!=1){
                        layer.alert('删除失败!',{icon:2,time:1000});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    orderItemCount();
                    refresh();
                },
                /*error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }*/
            });
        });
    }

    var oidvo;
    var allpricevo;
    var gnamevo;
    var addressvo;
    var unamevo;
    var tellvo;
    var ordertimevo;

    function gainOrderCustom(callback) {
        $.ajax({
            url:"${pageContext.request.contextPath}/order/getOrderCustom?oid="+oidvo,
            type:"GET",
            dataType:"json",
            success:function (data) {
                /*oidvo =data.orderCustom.oid;
                allpricevo=data.orderCustom.allprice;
                gnamevo=data.orderCustom.gname;
                addressvo=data.orderCustom.address;
                unamevo=data.orderCustom.uname;
                tellvo=data.orderCustom.tell;
                ordertimevo=data.orderCustom.ordertime;*/
                callback(data)
            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        });
    }


    function order_edit(title,url,id,w,h){
        oidvo = id;
        /*订单-编辑*/

        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            gainOrderCustom(function (data) {
                oid =oidvo;
                allprice=data.orderCustom.allprice;
                gname=data.orderCustom.gname;
                address=data.orderCustom.address;
                uname=data.orderCustom.uname;
                tell=data.orderCustom.tell;
                ordertime=data.orderCustom.ordertime;
                status = data.orderCustom.status;
                gid = data.orderCustom.gid;
            });
        });
        layer_show(title,url,w,h);
    }
    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }
</script>
</body>
</html>