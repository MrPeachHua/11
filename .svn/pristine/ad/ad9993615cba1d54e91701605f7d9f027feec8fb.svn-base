<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
     <meta name="renderer" content="webkit|ie-comp|ie-stand"> 
    <title>用户登录</title>
    <!-- 浏览器上显示title 图标 icon图标 -->
    <link type="image/x-icon" rel="icon" href="<%=basePath%>/images/default/logo2.0_32.ico">
    <!-- 让图标出现在收藏夹中 -->
    <link type="image/x-icon" rel="shortcut icon" href="<%=basePath%>/images/default/logo2.0_32.ico">
    <link type="image/x-icon" rel="bookmark" href="<%=basePath%>/images/default/logo2.0_32.ico">
    <link href="http://apps.bdimg.com/libs/bootstrap/3.3.0/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript">
        if (self != top) {
            window.top.location.href = '<%=basePath%>main/index.html';
        }
    </script>
    <style type="text/css">
        body {
            margin: 0px;
            background-image: url('<%=basePath%>/images/new/loginbg.png');
            overflow: hidden;
        }
        .tab {
            width: 400px;
            height: 370px;
            margin: 10% auto auto;
            border-radius: 10px;
            background: #F1F2F4 repeat left top;
        }
        .tab1 {
            background-color: #1B9BDC;
            width: 338px;
            height: 63px;
            align-content: center;
            border-radius: 5px;
            border-width: 0px;
            font-size: 22px;
            font-family: "微软雅黑";
            text-shadow: 0.5px 0.5px #000000;
            color: #F1FFFF;
            margin-top: 15px;
        }
        .tab p {
            text-align: center;
        }
        .username, .password {
            padding: 10px 10px 10px 60px;
            border: 0px;
            width: 80%;
            height: 70px;
            background-size: 30px 30px;
            background-color: #E7E7E7;
        }
        input:-webkit-autofill {
            -webkit-box-shadow: 0 0 0px 1000px #E7E7E7 inset;
        }
        .p_username, .p_password {
            position: relative;
        }
        .p_username img, .p_password img {
            position: absolute;
            width: 30px;
            height: 30px;
            left: 55px;
            top: 20px;
        }
    </style>
</head>
<body>
<div class="tab">
    <form name="from1" action="../j_spring_security_check" method ="post"  onsubmit="return validator(this)" >
        <p><img src="<%=basePath%>/images/new/loginlogo.png"/></p>
        <p class="p_username">
            <img src="<%=basePath%>/images/new/user.png" />
            <input type="text" name="j_username" class="username" placeholder="请输入账号"/>
        </p>
        <p class="p_password">
            <img src="<%=basePath%>/images/new/password.png" />
            <input type="password" name="j_password" class="password" placeholder="请输入密码"/>
        </p>
        <p style="text-align: center;"><input type="submit" class="tab1" value="登录"/></p>
    </form>
</div>

<script type="text/javascript">
    var error = '${error}';
    if (error.length > 0) {
        alert(error);
    }
</script>
</body>
</html>
