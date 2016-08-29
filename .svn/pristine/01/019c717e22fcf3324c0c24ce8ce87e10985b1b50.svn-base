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
</head>
<body>
<form method="post" action="<%=basePath%>/products/activityQrCode/save.html">
    <input type="hidden" name="type" value="3"/>
    <div class="editView">
        <div class="titleView">二维码上传</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>名称：</td>
                <td>
                    <input required type="text" name="title" value="${param.title}"/>
                </td>
                <td>
                    <%--<span class="star">＊</span>类型：--%>
                </td>
                <td>
                    <%--<select>--%>
                        <%--<option>口袋停android</option>--%>
                        <%--<option>口袋停iOS</option>--%>
                        <%--<option>代泊端android</option>--%>
                    <%--</select>--%>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>URL地址：</td>
                <td colspan="3">
                    <input required style="width: 633px" type="text" name="url" value="${param.url}" pattern="^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.[a-zA-Z]{2,4})(\:[0-9]+)?(/[^/][a-zA-Z0-9\.\,\?\'\\/\+&amp;%\$#\=~_\-@]*)*$"/>
                    <br/>
                    <span style="color: #ff0000;">例如：http://www.p-share.com</span>
                </td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="submit" value="确&nbsp;定">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/activityQrCode/list.html'">
        </div>
    </div>
</form>
</body>
</html>
