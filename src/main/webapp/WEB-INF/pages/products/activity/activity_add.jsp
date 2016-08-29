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
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/CheckForm.js" type="text/javascript"></script>--%>
</head>
<body>
<form method="post" action="<%=basePath%>/products/activity/save.html">
    <div class="editView">
        <div class="titleView">活动新增</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>活动名称：</td>
                <td>
                    <input required type="text" name="name" value="${param.name}"/>
                </td>
                <td><span class="star">＊</span>活动时间：</td>
                <td>
                    <input required type="text" name="startDate" value="${param.startDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                    <span class="mainColor"><strong>—</strong></span>
                    <input required type="text" name="endDate" value="${param.endDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>URL地址：</td>
                <td colspan="3"><input required style="width: 633px" type="text" name="url" value="${param.url}" pattern="^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.[a-zA-Z]{2,4})(\:[0-9]+)?(/[^/][a-zA-Z0-9\.\,\?\'\\/\+&amp;%\$#\=~_\-@]*)*$"/></td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="submit" value="确&nbsp;定">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/activity/list.html'">
        </div>
    </div>
</form>
<script type="text/javascript">
    var info = '${requestScope.info}';
    if (info.length > 0) alert(info);
</script>
</body>
</html>
