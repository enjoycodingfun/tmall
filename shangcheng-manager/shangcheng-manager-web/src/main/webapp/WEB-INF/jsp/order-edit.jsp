<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--_meta 作为公共模版分离出去-->
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/icon/favicon.ico" >
    <link rel="Shortcut Icon" href="/icon/favicon.ico" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <!--/meta 作为公共模版分离出去-->

    <title>添加用户 - H-ui.admin v3.1</title>
</head>
<body>
<article class="page-container">
    <form action="" method="get" class="form form-horizontal" id="form-member-add">
        <input type="password" name="password" hidden>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>订单号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <%--<input type="text" class="input-text" value="" placeholder="" id="oid" name="oid">--%>
                <span id="oid"></span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>支付金额(￥)：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <%--<input type="text" class="input-text" value="" placeholder="" id="allprice" name="allprice">--%>
                <span id="allprice"></span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收货人：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="gname" name="gname">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>收货地址：</label>
            <div class="formControls col-xs-8 col-sm-9" id="address">

            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>所属用户：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <%--<input type="text" class="input-text" value="" placeholder="" id="uname" name="uname">--%>
                <span id="uname"></span>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>联系方式：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="tell" name="tell">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>创建时间：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <%--<input type="text" class="input-text" value="" placeholder="" id="ordertime" name="ordertime">--%>
                <span id="ordertime"></span>
            </div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
                <input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
            </div>
        </div>
        <div class="row cl">
            <div class="formControls col-xs-8 col-sm-9">
                <input type="hidden" id="id" name="oid" value="">
                <input type="hidden" id="gid" name="gid" value="">
            </div>
        </div>
    </form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/province/distpicker.data.js"></script>
<script type="text/javascript" src="lib/province/distpicker.js"></script>
<script type="text/javascript" src="lib/common.js"></script>
<script type="text/javascript">
    var oid=parent.oid;
   /* var oidvo;
    var allpricevo;
    var gnamevo;
    var addressvo;
    var unamevo;
    var tellvo;
    var ordertimevo;

    oidvo =data.oid;
    allpricevo=data.allprice;
    gnamevo=data.gname;
    addressvo=data.address;
    unamevo=data.uname;
    tellvo=data.tell;
    ordertimevo=data.ordertime;

    oid =oidvo;
    allprice=allpricevo;
    gname=gnamevo;
    address=addressvo;
    uname=unamevo;
    tell=tellvo;
    ordertime=ordertimevo;*/
    $("#oid").html(oid);
    $("#allprice").html(parent.allprice);
    $("#gname").val(parent.gname);
    $("#uname").html(parent.uname);
    $("#tell").val(parent.tell);
    $("#ordertime").html(parent.ordertime)
    $("#gid").val(parent.gid);
    $("#id").val(oid);
    var status=parent.status;
    var address=parent.address;

    if(status>2){
        $("#address").html("<span>"+ address+"</span>");
    }else{
        $("#address").html("<input type=\"text\" class=\"input-text\" value="+address+" placeholder=\"\" name=\"address\">");
    }

    /*文本输入限制*/
    $(".textarea").Huitextarealength({
        minlength:0,
        maxlength:100
    });

    function radioCheck(){
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
    }

    $(function(){
        radioCheck();
        $("#form-member-add").validate({
            /*rules:{
                username:{
                    required:true,
                    minlength:2,
                    maxlength:16,
                    remote: "/member/edit/"+id+"/username"
                },
                password:{
                    required:true,
                    minlength:6,
                },
                password2:{
                    required:true,
                    minlength:6,
                    equalTo: "#password"
                },
                phone:{
                    required:true,
                    isMobile:true,
                    remote: "/member/edit/"+id+"/phone"
                },
                email:{
                    required:true,
                    email:true,
                    remote: "/member/edit/"+id+"/email"
                },
                sex:{
                    required:true,
                },
                file:{
                    required:false,
                },
                province:{
                    required:true,
                },
            },
            messages: {
                username: {
                    remote: "该用户名已被注册"
                },
                phone: {
                    remote: "该手机号已被注册"
                },
                email: {
                    remote: "该邮箱已被注册"
                }
            },*/
            onkeyup:false,
            focusCleanup:false,
            success:"valid",
            submitHandler:function(form){
                var index = layer.load(3);
                $(form).ajaxSubmit({
                    url: "${pageContext.request.contextPath}/order/updataOrder",
                    type: "get",
                    success: function(data) {
                        layer.close(index);
                        if(data.result==1){
                            parent.refresh();
                            parent.msgSuccess("编辑成功!");
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }else{
                            layer.alert('修改失败! '+data.message, {title: '错误信息',icon: 2});
                        }
                    },
                    error:function(XMLHttpRequest) {
                        layer.close(index);
                        layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                    }
                });
            }
        });
    });
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>