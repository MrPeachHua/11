<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
    <link href="<%=basePath%>/css/style_v2.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <style type="text/css">
        body {
            padding: 15px 20px;
        }
        td {
            padding: 10px;
        }
    </style>
</head>
<body>
<form action="<%=basePath%>users/updatepwd.html" method="post" name="form2" onsubmit="return checkForm('form2');">
    <table class='mainView' cellSpacing=0 cellPadding=0 width="100%" align=center border='0'>
        <tr class=editHeaderTr>
            <td class='headerTitle' colSpan=7> 请输入需要重置的密码
                <input type="hidden" name="userId" value="${userInfo.userId }" readonly="readonly">
            </td>
        </tr>
        <tr>
            <td align="right">
                <div>用户密码：</div>
            </td>
            <td>
                <input type="password" name="userPw" maxlength="20" class="selectList" valid="required"
                       errmsg="密码不能为空!">
            </td>
            <td><input class="greenBtn" type="submit" name="Submit" value="提交"></td>
        </tr>
        <tr>
    </table>
</form>
</body>
</html>
