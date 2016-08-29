<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title></title>
    <%--<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">--%>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.11.1.min.js"></script>
    <style type="text/css">
    body, div, iframe, ul, ol, dl, dt, dd, li, dl, h1, h2, h3, h4, table, th, td, input, button, select, textarea {
        margin: 0;
        padding: 0;
        font-family: "Microsoft YaHei", "微软雅黑", "sans-serif";
    }

    body {
        margin: 0px;
        background: #3f4752;
        left: 0;
        right: 0;
    }

    .STYLE1 {
        font-size: 14px;
        color: #FFFFFF;
    }

    .STYLE3 {
        font-size: 14px;
        color: #033d61;
    }

    .menu_title SPAN {
        FONT-WEIGHT: bold;
        LEFT: 3px;
        COLOR: #ffffff;
        POSITION: relative;
        TOP: 2px
    }

    .subMenu a:hover {
        COLOR: #00C3A1;
    }

    .menu_title2 SPAN {
        FONT-WEIGHT: bold;
        LEFT: 3px;
        COLOR: #FFCC00;
        POSITION: relative;
        TOP: 2px
    }

    .style1 {
        font-size: 14px;
    }

    a {
        text-decoration: none;
        color: #FFF;
    }

    .logo {
        background-color: #00C3A1;
        width: 100%;
        height: 100px;
    }
    .logo img {
        margin-left: 39px;
    }
    .topTitle {
        padding-left: 30px;
    }
    .childTitle {
        padding-left: 10px;
        padding-bottom: 10px;
    }
    .scrollView {
        overflow-x: hidden;
        overflow-y: auto;
        height: -webkit-calc(100% - 100px);
        height: -moz-calc(100% - 100px);
        height: calc(100% - 100px);
        width: 220px;
    }
    </style>
</head>

<body>
<div class="logo">
    <img src="<%=basePath%>/images/new/write-logo.png" alt=""/>
</div>
<div class="scrollView">
<table id="menu_tbl" width="200" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td valign="top">
            <table width="100%" height="40" border="0" align="center" cellpadding="0" cellspacing="0">
                <c:forEach var="item" items="${menuMap}" varStatus="status">
                    <tr>
                        <td>
                            <table width="100%" height="40" style="height: 40px;" border="0" cellspacing="0"
                                   cellpadding="0">
                                <tr>
                                    <td height="40" id="imgmenu${status.count}" class="menu_title"
                                        onclick="showSubMenu(${status.count})"
                                        style="cursor: pointer">
                                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <%--<td width="30%"><img src="<%=basePath%>/images/new/${item.key.icon}"--%>
                                                <td class="topTitle" width="30%"><img src="<%=basePath%>/images/new/${item.key.icon}" width="30px"
                                                                     style="float:right;margin-right: 10px" alt=""></td>
                                                <td width="70%" class="STYLE1">${item.key.menuName}</td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="subMenu">
                                        <div id="submenu${status.count}" class="sec_menu" style="background: #313842; DISPLAY: none">
                                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                                <tr>
                                                    <td>
                                                        <table width="100%" border="0" align="center" cellpadding="0"
                                                               cellspacing="0">
                                                            <c:forEach var="row" items="${item.value}" varStatus="cnt">
                                                                <tr>
                                                                    <td width="30%" height="23">
                                                                        <div align="center"></div>
                                                                    </td>
                                                                    <td width="70%" height="23">
                                                                        <table width="95%" heght="23" border="0"
                                                                               cellspacing="0" cellpadding="0">
                                                                            <tr>
                                                                                <td class="childTitle" height="23" style="cursor: pointer">
																					<span onclick="choseTitle('${row.menuName}','${item.key.menuName}', '<%=basePath%>${row.urls}')" class="STYLE3">
                                                                                        <a href="<%=basePath%>${row.urls}" target="mainFrame">${row.menuName}</a>
																					</span>
                                                                                </td>
                                                                            </tr>
                                                                        </table>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                        </table>
                                                    </td>
                                                </tr>
                                                    <%--<tr>--%>
                                                    <%--<td height="5">--%>
                                                    <%--<img src="<%=imagePath%>main_52.gif"--%>
                                                    <%--width="151" height="5" />--%>
                                                    <%--</td>--%>
                                                    <%--</tr>--%>
                                            </table>
                                        </div>
                                    </td>
                                </tr>

                            </table>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </td>

    </tr>
    <%--<tr>--%>
    <%--<td height="18"--%>
    <%--background="<%=imagePath%>main_58.gif">--%>
    <%--<table width="100%" border="0" cellspacing="0" cellpadding="0">--%>
    <%--<tr>--%>
    <%--<td height="18" valign="bottom">--%>
    <%--<div align="center" class="STYLE3">--%>
    <%--版本：20016V1.0--%>
    <%--</div>--%>
    <%--</td>--%>
    <%--</tr>--%>
    <%--</table>--%>
    <%--</td>--%>
    <%--</tr>--%>
</table>
</div>
</body>
<script type="text/javascript">
    document.getElementsByClassName('menu_title')[0].style.padding = '20px 0px 5px 0px';
    function choseTitle(title, parentTitle, jumpUrl) {
        window.top.document.getElementById('title').innerHTML = '— ' + parentTitle + ' — <a href="' + jumpUrl + '" target="mainFrame">' + title + '</a>';
    }
    var menuToggle = {
        menuObject: null,
        toggle: function ($parent, $child, speed) {
            if ($child.is(":hidden")) {
                this.show({"parent": $parent, "child": $child, "speed": speed});
                this.menuObject = {"parent": $parent, "child": $child};
            } else {
                this.hide({"parent": $parent, "child": $child, "speed": speed});
            }
        },
        show: function (obj) {
            if (this.menuObject != null) {
                this.hide(this.menuObject);
            }
            obj.parent.css({"backgroundColor": "#313842"});
            obj.child.slideDown(obj.speed);
        },
        hide: function (obj) {
            obj.child.slideUp(obj.speed, function () {
                obj.parent.css({"backgroundColor": ""});
            });
        }
    };
    function showSubMenu(sid) {
        menuToggle.toggle($("#imgmenu" + sid), $("#submenu" + sid), 222);
    }
    function layoutScrollView() {
        $('.scrollView').css({"height": (document.body.clientHeight - 100)});
    }
    window.onresize = function () {
        layoutScrollView();
    };
    layoutScrollView();
</script>
</html>
