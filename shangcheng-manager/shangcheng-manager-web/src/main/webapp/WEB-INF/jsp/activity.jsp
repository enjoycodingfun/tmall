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
    <title>活动列表</title>
</head>
<style>
    .table>tbody>tr>td{
        text-align:center;
    }
</style>
<body>

<div>
    <nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 商品管理 <span class="c-gray en">&gt;</span> 活动列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
    <form class="page-container">
        <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a></span> <a class="btn btn-primary radius" onclick="activity_add('添加活动','activity-add')" href="javascript:;"><i class="Hui-iconfont">&#xe600;</i> 添加活动</a><span class="r">共有数据：<strong id="num">0</strong> 条</span> </div>
        <div class="mt-20">
            <div class="mt-20" style="margin-bottom: 70px">
                <table class="table table-border table-bordered table-bg table-hover table-sort" width="100%">
                    <thead>
                    <tr class="text-c">
                        <th width="25"><input type="checkbox" name="" value=""></th>
                        <th width="80">活动id</th>
                        <th width="80">活动名称</th>
                        <th width="50">满</th>
                        <th width="80">减</th>
                        <th width="100">打折</th>
                        <th width="100">操作</th>
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

    //    datatables配置
    $(document).ready(function () {
        $('.table').DataTable({
           "processing": true,//加载显示提示
            "ajax": {
                url:"${pageContext.request.contextPath}/activitys",
                type: 'GET',
            },
            "columns": [
//                { "data": null,
//                    render : function(data,type, row, meta) {
//                        return "<input name=\"checkbox\" value=\""+row.orderId+"\" type=\"checkbox\" value=\"\">";
//                    }
//                },

                { "data": "aid",
                    render : function(data){
                        return "<input type=\"checkbox\" name=\"checkbox\" value="+data+">";
                    }
                },
                { "data": "aid"},
                { "data": "aname"},
                { "data": "full"},
                { "data": "minus"},
                { "data": "discount"},
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
//                { "orders": "status",
//                    render : function(data) {
//                        if(data==0){
//                            return "<span class=\"label label-defant radius td-status\">待支付</span>";
//                        }else if(data==1){
//                            return "<span class=\"label label-warning radius td-status\">已支付</span>";
//                        } else if(data==2){
//                            return "<span class=\"label label-primary radius td-status\">待发货</span>";
//                        }else if(data==3){
//                            return "<span class=\"label label-secondary radius td-status\">已发货</span>";
//                        }else if(data==4){
//                            return "<span class=\"label label-success radius td-status\">交易成功</span>";
//                        }else if(data==5) {
//                            return "<span class=\"label label-danger radius td-status\">交易关闭</span>";
//                        }else if(data==6){
//                            return "<span class=\"label label-defant radius td-status\">支付失败</span>";
//                        }else{
//                            return "<span class=\"label label-warning radius td-status\">其它态</span>";
//                        }
//                    }
//                },
                /* { "data": "ordertime",
                     render : function(data) {
                         return "<a href=\"javascript:;\" onclick=\"datadel()\" class=\"btn btn-danger radius\" ><i class=\"Hui-iconfont\">&#xe6e2;</i> 删除</a>";
                     }
                 }*/
                {
                    "data": "aid",
                    render : function(data) {
                        return "<a title=\"编辑\" href=\"javascript:;\" onclick=\"activityedit('编辑','activityedit',"+data+",'','510')\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6df;</i></a> <a title=\"删除\" href=\"javascript:;\" onclick=\"acdel(this,"+data+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\">&#xe6e2;</i></a>";
                    }
                }
            ],
            "aaSorting": [[ 6, "desc" ]],//默认第几个排序
            /*
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

    orderCount();

    function orderCount() {
        $.ajax({
            url:"${pageContext.request.contextPath}/activityCount",
            type: 'GET',
            success:function (data) {
                /*if(data.success!=true){
                    layer.alert(data.message,{title: '错误信息',icon: 2});
                    return;
                }*/
                $("#num").html(data.count);
            }
           /* error:function(XMLHttpRequest){
                if(XMLHttpRequest.status!=200){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }
            }*/
        });
    }

    /*订单-删除*/
    function acdel(obj,aid){
        layer.confirm('确认要删除ID为\''+aid+'\'的订单吗？',function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/removeActivity?aid='+aid,
                dataType: 'json',
                success: function(data){
                    layer.close(index);
                    layer.msg('已删除!',{icon:1,time:1000});
                    layer.msg(data.i);
                    if(data.i!=0){
                        layer.alert('删除失败!',{icon: 2});
                        return;
                    }
                    orderCount();
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

        var count=0,ids=[];
        for(var i=0;i<cks.length;i++){
            if(cks[i].checked){
                count++;
                ids.push(cks[i].value);
            }
        }
        if(count==0){
            layer.msg('您还未勾选任何数据!',{icon:5,time:3000});
            return;
        }
        /*去除末尾逗号*/
        /*if(ids.length>0){
            ids=ids.substring(0,ids.length-1);
        }*/
        layer.confirm('确认要删除所选的'+count+'条数据吗？',{icon:0},function(index){
            var index = layer.load(3);
            $.ajax({
                type: 'get',
                url: '${pageContext.request.contextPath}/removeListActivity?ids[]='+ids,
                dataType: 'json',
                success:function(data){
                    layer.close(index);
                    if(data.i==0){
                        layer.alert('未删除!',{icon: 2});
                        return;
                    }
                    layer.msg('已删除!',{icon:1,time:1000});
                    orderCount();
                    refresh();
                },
                /*error:function(XMLHttpRequest){
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
                }*/
            });
        });
    }

    /*活动-编辑*/
    function activity_edit(title,url,id){
        setId(id);
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }
    var ID=0;
    function setId(id){
        ID=id;
    }

    function getId(){
        return ID;
    }

    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }

    /*活动-添加*/
    function activity_add(title,url){
        var index = layer.open({
            area: ['600px', '400px'],
            type: 2,
            title: title,
            content: url,
            end:function(){
                location.reload();
            }
        });
        //layer.full(index);
    }

    function activityedit(title,url,id,w,h){
        Id=id;
        var table = $('.table').DataTable();
        $('.table tbody').on( 'click', 'tr', function () {
            aid = table.row(this).data().aid;
            aname = table.row(this).data().aname;
            full = table.row(this).data().full;
            minus = table.row(this).data().minus;
            discount = table.row(this).data().discount;

        });
        layer_show(title,url,w,h);
    }
</script>
</body>
</html>

