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
    <title></title>
    <link type="text/css" rel="stylesheet" href="<%=basePath%>/css/pages/style_v2.2.css">
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/pages/pageListUtil.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/ajaxfileupload.js"></script>
</head>
<body>
<form method="post" action="<%=basePath%>products/villageowner/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>小区名称</span>
                <input type="text" name="p_parkingName" value="${param.p_parkingName}">
            </li>
            <li>
                <span>客户ID</span>
                <input type="text" name="p_customerId" value="${param.p_customerId}">
            </li>
            <li>
                <span>手机号</span>
                <input type="text" name="p_mobile" value="${param.p_mobile}">
            </li>
            <li class="right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">
                <input class="searchBtn" type="button" value="新&nbsp;增" onclick="location='<%=basePath%>products/villageowner/add.html'">
            </li>
        </ul>
        <ul>
            <li>
                <span>业主姓名</span>
                <input type="text" name="p_name" value="${param.p_name}">
            </li>
            <li class="right">
                <input class="searchBtn" type="button" value="导&nbsp;入" onclick="importExcel('<%=basePath%>products/villageowner/uploadExcel.html')">
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>products/villageowner/excelList.html')">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>小区</td>
                <td>业主姓名</td>
                <td>手机号</td>
                <td>客户ID</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${row.parkingName}</td>
                    <td>${row.name}</td>
                    <td>${row.mobile}</td>
                    <td>${row.customerId}</td>
                    <td>
                        <input class="edit" type="button" onclick="location='<%=basePath%>products/villageowner/${row.id}/edit.html'"/>
                        <input class="delete" type="button" onclick="confirm('你确定？')?location='<%=basePath%>products/villageowner/${row.id}/delete.html':void(0)"/>
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
<%@ include file="../../template/importExcel.jsp" %>
</body>
</html>
