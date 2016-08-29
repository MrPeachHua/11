<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String baseIp = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>泊享科技</title>
    <!-- 浏览器上显示title 图标 icon图标 -->
    <link type="image/x-icon" rel="icon" href="images/default/logo2.0_32.ico">
    <!-- 让图标出现在收藏夹中 -->
    <link type="image/x-icon" rel="shortcut icon" href="images/default/logo2.0_32.ico">
    <link type="image/x-icon" rel="bookmark" href="images/default/logo2.0_32.ico">
    <link href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/css/bootstrap-3.3.5-dist/js/bootstrap.min.js"></script>
    <style type="text/css">
        html, body, table, tr, td {
            margin: 0;
            padding: 0;
            border: 0;
            font-size: 16px;
        }
        body {
            overflow-y: hidden;
        }
        #sidebar {
            position: absolute;
            left: 180px;
            top: 80px;
            width: 40px;
        }
        #sidebar:hover {
            cursor: pointer;
        }
        .line {
            color: #929FA3;
            font-family: "Microsoft YaHei", "微软雅黑", "sans-serif";
            margin-left: 50px;
            font-size: 16px;
        }
        .line a {
            text-decoration: none;
            color: #929FA3;
            text-align: center;
        }
        .line a:hover {
            color: #929FA3;
        }
        .name {
            float: right;
            margin-right: 30px;
        }
        .home {
            float: left;
            margin-top: 10px;
        }
        @media (max-width: 1320px) {
            body {
                width: 1320px;
            }
        }
    </style>
</head>
<body>
<table border="0" width="100%" height="100%" cellpadding="0" cellspacing="0">
    <tr>
        <td id="left" rowspan="2" width="200px">
            <iframe id="menu" name="menu" src="<%=basePath%>frame/menu.html" height="100%" width="100%" scrolling="no" frameborder="0"></iframe>
        </td>
        <td height="100px">
            <div class="line">
                <div class="home">
                    <span class="glyphicon glyphicon-home"></span>
                    <span onclick="document.getElementById('title').innerHTML=''">
                        <%--<a href="<%=basePath%>frame/desktop.html" target="mainFrame">首页</a>--%>
                        <a href="<%=baseIp%>wx_share/html5/views/index.html#/home/report?userId=${shareUser.sysUserId}" target="mainFrame">首页</a>
                    </span>
                    <span id="title"></span>
                </div>
                <div class="name">
                    <div class="dropdown">
                        <img width="40px" src="<%=basePath%>/images/new/head_sculpture_.png" alt="">
                        <span style="font-size: 16px;" class="btn dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">
                            ${sessionScope.shareUser.userName}
                            <span class="caret"></span>
                        </span>
                        <ul style="font-size: 16px;" class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                            <li role="presentation" onclick="choseTitle('个人资料', '<%=basePath%>users/${shareUser.userId}/userself.html')">
                                <a role="menuitem" tabindex="-1" href="<%=basePath%>users/${shareUser.userId}/userself.html" target="mainFrame">个人资料</a>
                            </li>
                            <li role="presentation" onclick="choseTitle('密码重置', '<%=basePath%>users/${shareUser.userId}/resetpwd.html')">
                                <a role="menuitem" tabindex="-1" href="<%=basePath%>users/${shareUser.userId}/resetpwd.html" target="mainFrame">密码重置</a>
                            </li>
                            <li role="presentation" class="divider"></li>
                            <li role="presentation">
                                <a role="menuitem" tabindex="-1" href="<%=basePath%>auth/logout">退出</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </td>
    </tr>
    <tr>
        <td>
            <iframe id="mainFrame" name="mainFrame" src="<%=baseIp%>wx_share/html5/views/index.html#/home/report?userId=${shareUser.sysUserId}" height="100%" width="100%" scrolling="yes" frameborder="0"></iframe>
        </td>
    </tr>
</table>
<img id="sidebar" src="<%=basePath%>/images/new/sidebar.png" alt=""/>
<script type="text/javascript">
    var sidebar = {
        flag: false,
        click: function ($obj, $left, offset, speed) {
            $obj.click(function () {
                this.flag = !this.flag;
                var width = parseInt($left.css('width'));
                var left = parseInt($obj.css('left'));
                if (this.flag) {
                    $left.animate({"width": width + offset}, speed);
                    $obj.animate({"left": left + offset}, speed);
                } else {
                    $left.animate({"width": width - offset}, speed);
                    $obj.animate({"left": left - offset}, speed);
                }
            });
        }
    };
    sidebar.click($('#sidebar'), $('#left'), -130, 222);

    function choseTitle(title, jumpUrl) {
        window.document.getElementById('title').innerHTML = ' — <a href="' + jumpUrl + '" target="mainFrame">' + title + '</a>';
    }
</script>
</body>
</html>
