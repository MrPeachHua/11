<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
<form method="post" action="<%=basePath%>/products/activity/update.html">
    <div class="editView">
        <div class="titleView">活动修改</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>活动名称：</td>
                <td>
                    <input required type="text" name="name" value="${entity.name}"/>
                    <input type="hidden" name="id" value="${entity.id}"/>
                </td>
                <td><span class="star">＊</span>活动时间：</td>
                <td>
                    <input required type="text" name="startDate" value="<fmt:formatDate value="${entity.startDate}" type="both" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                    <span class="mainColor"><strong>—</strong></span>
                    <input required type="text" name="endDate" value="<fmt:formatDate value="${entity.endDate}" type="both" pattern="yyyy-MM-dd"/>" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>URL地址：</td>
                <td colspan="3"><input required style="width: 633px" type="text" name="url" value="${entity.url}"/></td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="submit" value="确&nbsp;定">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="history.back()">
        </div>
    </div>
</form>
</body>
</html>
