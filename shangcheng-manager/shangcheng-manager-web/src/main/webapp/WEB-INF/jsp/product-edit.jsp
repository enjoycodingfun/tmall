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
    <script type="text/javascript" src="${pageContext.request.contextPath}/lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>

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
    <!--/meta 作为公共模版分离出去-->

    <link href="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="page-container">
    <form name="product-add"  action="${pageContext.request.contextPath}/updateProduct" method="get" class="form form-horizontal" id="product-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品编号：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="pid" name="pid">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="pname" name="pname">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品卖点：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="sellPoint" name="sellPoint">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>商品分类：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" hidden class="input-text" id="cid" name="cid" >
				<input type="text" onclick="chooseCategory()" readonly class="input-text" value="" placeholder="请点击选择按钮选择商品分类" id="catName" name="catName" style="width:50%">
                <input type="button" onclick="chooseCategory()" class="btn btn-secondary radius" value="选择类别">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>产品展示价格：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="price" id="price" placeholder="请输入正确金额" value="" class="input-text" style="width:50%">
                元</div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>库存数量：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" name="num" id="num" placeholder="0~99999" value="" class="input-text" style="width:50%">
                件</div>
        </div>
        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
                <button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 保存并发布</button>
                <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/lib/webuploader/0.1.5/webuploader.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/kindeditor/themes/default/default.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/lib/kindeditor/plugins/code/prettify.css" />
<script charset="utf-8" src="${pageContext.request.contextPath}/lib/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/lib/kindeditor/lang/zh-CN.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/lib/kindeditor/plugins/code/prettify.js"></script>
<script type="text/javascript">

  /*  pid = table.row(this).data().pid;
    price = table.row(this).data().price;
    shellPoint = table.row(this).data().shellPoint;
    cid = table.row(this).data().cid;
    status = table.row(this).data().status;
    pdesc = table.row(this).data().pdesc;*/

    $("#pid").val(parent.getId());
    $("#pname").val(parent.pname);
    $("#price").val(parent.price);
    $("#sellPoint").val(parent.sellPoint);
    $("#cid").val(parent.cid);
    $("#status").val(parent.status);
    $("#catName").val(parent.catName);
    $("#num").val(parent.num);

    var editor;
    var htmlData;


    function giveCid(cid){
        $("#cid").val(cid);
    }

    function giveCName(cname){
        $("#cname").val(cname);
    }

    function chooseCategory(){
        layer_show("选择商品分类","choose-category",300,510);
    }


    /*//保存发布
    $("#product-add").validate({
        rules:{
            title:{
                required:true,
            },
            sellPoint:{
                required:true,
            },
            cname:{
                required:true,
            },
            price:{
                decimalsValue:true,
                required:true,
                maxlength:10,
            },
            num:{
                digits:true,
                required:true,
                maxlength:5,
            },
            limitNum:{
                digits:true,
                required:true,
                maxlength:4,
            },
        },
      /!* onkeyup:false,
        focusCleanup:false,
        success:"valid",*!/
        submitHandler:function(form){
            var index = layer.load(3);
           /!* editor.sync();*!/
            $(form).ajaxSubmit({
                url: "${pageContext.request.contextPath}/productUpdate?pid="+parent.getId(),
                type: "GET",
                success: function(data) {
                    layer.close(index);
                    if(data.success==true){
                        parent.refresh();
                        parent.msgSuccess("编辑成功!");
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }else{
                        layer.alert('编辑失败! '+data.message, {title: '错误信息',icon: 2});
                    }
                },
                error:function(XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                }
            });
        }
    });
*/
    index = layer.load(3);
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="detail"]', {
            cssPath : 'lib/kindeditor/plugins/code/prettify.css',
            uploadJson : '/kindeditor/imageUpload',
            fileManagerJson : '/kindeditor/imageUpload',
            allowFileManager : false,
            height : '400px',
            afterCreate : function() {
                var self = this;
                K.ctrl(document, 13, function() {
                    self.sync();
                    document.forms['product-add'].submit();
                });
                K.ctrl(self.edit.doc, 13, function() {
                    self.sync();
                    document.forms['product-add'].submit();
                });
            }
        });
        prettyPrint();

        $.ajax({
            type: 'GET',
            url: '${pageContext.request.contextPath}/listProductByPid?pid='+parent.getId(),
            dataType: 'json',
            success: function(data){
                layer.close(index);
               /* $("#pid").val(parent.pid);
                $("#pname").val(parent.pname);
                $("#price").val(parent.price);
                $("#sellPoint").val(parent.sellPoint);
                $("#cid").val(parent.cid);
                $("#status").val(parent.status);
                $("#pdesc").val(parent.pdesc);
                $("#catName").val(parent.catName);
                $("#num").val(parent.num);*/
                $("#pname").val(data.result.pname);
                $("#price").val(data.price);
                $("#sellPoint").val(data.sellPoint);
                $("#cid").val(data.cid);
                $("#catName").val(data.catName);
                $("#status").val(data.status);
                $("#num").val(111);
                /*htmlData=data.detail;
                KindEditor.html('#detail', htmlData);*/
            },
            error:function(XMLHttpRequest) {
                layer.close(index);
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            },
        });
        alert(parent.getId());
    });

</script>
</body>
</html>