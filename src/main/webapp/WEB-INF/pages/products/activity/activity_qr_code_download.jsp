<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
</head>
<body>
<div class="editView">
    <div class="titleView">二维码下载</div>
    <div style="text-align:center; margin:50px;">
        <h2>新增成功，请点击下方链接下载</h2>
        <a href="${requestScope.url}">${requestScope.url}</a>
    </div>
    <div class="footView">
        <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/activityQrCode/list.html'">
    </div>
</div>
</body>
</html>
