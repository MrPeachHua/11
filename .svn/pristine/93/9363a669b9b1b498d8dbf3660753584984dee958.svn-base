<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>会员等级</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <%--<link href="<%=basePath%>/css/pages/commentTags.css" type="text/css" rel="stylesheet">--%>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/pages/commentTags.js" type="text/javascript"></script>--%>


    <script>
        function add() {
            window.location = "<%=basePath%>customer/member_level/add.html";
        }
    </script>
</head>
<body>
<form method="post" action="<%=basePath%>customer/member_level/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>会员等级</span>
                <input type="text" name="p_queryValue"/>
            </li>
            <li class="right">
                <security:authorize access="hasAnyRole('AUTH_VIEW_0120')">
                    <input class="searchBtn" type="submit" value="查&nbsp;询">
                </security:authorize>
                <security:authorize access="hasAnyRole('AUTH_ADD_0121')">
                    <input class="searchBtn" type="button" value="新&nbsp;增" onclick="add()">
                </security:authorize>
            </li>
        </ul>

    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>等级名称</td>
                <td>成长值目标</td>
                <td>无有效订单时长(天)</td>
                <td>成长值减少</td>
                <td>状态</td>
                <td>权重</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${row.levelName}</td>
                    <td>${row.growthValue}</td>
                    <td>${row.ext1}</td>
                    <td>${row.ext1Value}</td>
                    <td><div align="center"><span class="STYLE1">
                                                    <c:if
                                                            test="${row.isValid == '1'}">
                                                        有效
                                                    </c:if>
                                                    <c:if
                                                            test="${row.isValid == '0'}">
                                                        失效
                                                    </c:if>
                                                    </span></div></td>
                    <td>${row.weight}</td>
                    <td><security:authorize access="hasAnyRole('AUTH_EDIT_0122')">
                        <a href="<%=basePath %>customer/member_level/${row.id}/edit.html">编辑</a>
                        </security:authorize>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${fn:length(page.resultList) == 0}">
            <div class="contentLine">
                没有任何数据
            </div>
        </c:if>
    </div>
    <jsp:include page="/frame/page.html"/>
</form>
</body>
</html>
