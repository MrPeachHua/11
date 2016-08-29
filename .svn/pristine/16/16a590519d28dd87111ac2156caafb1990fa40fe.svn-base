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
<form method="post" action="<%=basePath%>/products/activity/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>开始时间范围</span>
                <input type="text" name="p_startDateBegin" value="${param.p_startDateBegin}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_startDateEnd" value="${param.p_startDateEnd}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
            <li>
                <span>活动状态</span>
                <select name="p_state">
                    <option value="">全部</option>
                    <option <c:if test="${param.p_state eq '0'}">selected="selected"</c:if> value="0">已过期</option>
                    <option <c:if test="${param.p_state eq '1'}">selected="selected"</c:if> value="1">未过期</option>
                    <option <c:if test="${param.p_state eq '2'}">selected="selected"</c:if> value="2">未开始</option>
                </select>
            </li>
            <li class="right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">
                <input class="searchBtn" type="button" value="新&nbsp;增" onclick="location='<%=basePath%>products/activity/add.html'">
            </li>
        </ul>
        <ul>
            <li>
                <span>结束时间范围</span>
                <input type="text" name="p_endDateBegin" value="${param.p_endDateBegin}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_endDateEnd" value="${param.p_endDateEnd}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
            <li>
                <span>活动名称</span>
                <input type="text" name="p_name" value="${param.p_name}">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>活动名称</td>
                <td>活动时间</td>
                <td>URL地址</td>
                <td>状态</td>
                <td>操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${row.name}</td>
                    <td>
                        <fmt:formatDate value="${row.startDate}" type="both" pattern="yyyy-MM-dd"/>
                        <span class="mainColor"><strong>～</strong></span>
                        <fmt:formatDate value="${row.endDate}" type="both" pattern="yyyy-MM-dd"/>
                    </td>
                    <td>${row.url}</td>
                    <%-- 0:已过期 1:未过期 2:未开始 --%>
                    <td>${row.state eq '1' ? '未过期' : row.state eq '2' ? '未开始' : '<span style="color:red;">已过期</span>'}</td>
                    <td>
                        <%--<input class="edit" type="button" onclick="location='<%=basePath%>products/activity/${row.id}/edit.html'"/>--%>
                        <input class="delete" type="button" onclick="confirm('你确定？')?location='<%=basePath%>products/activity/${row.id}/delete.html':void(0)"/>
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
