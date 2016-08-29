<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
</head>
<body>
<form method="post" action="<%=basePath%>/products/activityQrCode/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>名称</span>
                <input type="text" name="p_name" value="${param.p_name}">
            </li>
            <li>
                <span>生成时间</span>
                <input type="text" name="p_createDateBegin" value="${param.p_createDateBegin}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" placeholder="开始时间">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_createDateEnd" value="${param.p_createDateEnd}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" placeholder="结束时间">
            </li>
            <li class="right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">
                <input class="searchBtn" type="button" value="新&nbsp;增" onclick="location='<%=basePath%>products/activityQrCode/add.html'">
                <input class="searchBtn" type="button" value="上&nbsp;传" onclick="location='<%=basePath%>products/activityQrCode/upload.html'">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>名称</td>
                <td>生成时间</td>
                <td>扫描数</td>
                <td>下载</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>
                        <c:choose>
                            <c:when test="${row.type eq '1'}">
                                ${row.title}&nbsp;&nbsp;${row.parkingName}&nbsp;&nbsp;${row.parkerName}
                            </c:when>
                            <c:when test="${row.type eq '2'}">
                                ${row.name}&nbsp;&nbsp;${row.parkingName}&nbsp;&nbsp;${row.parkerName}
                            </c:when>
                            <c:when test="${row.type eq '3'}">
                                ${row.title}
                            </c:when>
                            <c:otherwise>
                                &nbsp;
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>${row.createDate}</td>
                    <td>${row.scanCount}</td>
                    <td>
                        <a href="<%=basePath%>/products/activityQrCode/download.html?id=${row.id}">下载</a>
                    </td>
                    <td>
                        <input class="delete" type="button" onclick="confirm('你确定？')?location='<%=basePath%>products/activityQrCode/${row.id}/delete.html':void(0)"/>
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
