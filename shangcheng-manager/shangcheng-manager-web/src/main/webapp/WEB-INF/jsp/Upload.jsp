<%--
  Created by IntelliJ IDEA.
  User: running
  Date: 2018/8/1
  Time: 22:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>图片上传功能测试</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/uploadImage" method="post" enctype="multipart/form-data">
<input type="file" name="file" >
    <button type="submit">上传</button>
</form>
</body>
</html>
