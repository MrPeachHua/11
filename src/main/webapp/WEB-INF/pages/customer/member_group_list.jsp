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
    <title>会员分组</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <%--<link href="<%=basePath%>/css/pages/commentTags.css" type="text/css" rel="stylesheet">--%>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/pages/commentTags.js" type="text/javascript"></script>--%>


    <script>
        function add() {
            window.location = "<%=basePath%>customer/member_group/add.html";
        }
    </script>
</head>
<body>
<form method="post" action="<%=basePath%>customer/member_group/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>分组名称</span>
                <input type="text" name="p_queryGroup"/>
                <span>状态</span>
                <select name="p_queryStatus" style="width: 100px">
                    <option  value="">全部</option>
                    <option  value="1">有效</option>
                    <option  value="0">失效</option>
                </select>
            </li>
            <li class="right">
                <security:authorize access="hasAnyRole('AUTH_VIEW_0123')">
                    <input class="searchBtn" type="submit" value="查&nbsp;询">
                </security:authorize>
                <security:authorize access="hasAnyRole('AUTH_ADD_0124')">
                    <input class="searchBtn" type="button" value="新&nbsp;增" onclick="add()">
                </security:authorize>
            </li>
        </ul>

    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>分组名称</td>
                <td>每日获得成长值上限</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${row.groupName}</td>
                    <td>${row.growthValue}</td>
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
                    <td><security:authorize access="hasAnyRole('AUTH_EDIT_0125')">
                        <a href="<%=basePath %>customer/member_group/${row.id}/edit.html">编辑</a>
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
