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
    <title>评价</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <%--<link href="<%=basePath%>/css/pages/commentTags.css" type="text/css" rel="stylesheet">--%>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/pages/commentTags.js" type="text/javascript"></script>--%>
</head>
<body>
<form method="post" action="<%=basePath%>products/comment/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>订单类型</span>
                <select name="p_orderType" class="dictionary" data-current-value="${param.p_orderType}" data-dict-code="order_type">
                    <option value="">全部</option>
                </select>
            </li>
            <li>
                <span>订单ID</span>
                <input type="text" name="p_orderId" value="${param.p_orderId}">
            </li>
            <li>
                <span>评论星级</span>
                <select name="p_star">
                    <option value="">全部</option>
                    <c:forEach varStatus="status" begin="0" end="5" step="1">
                        <option <c:if test="${param.p_star != '' && param.p_star == status.index}">selected="selected"</c:if> value="${status.index}">${status.index}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>products/comment/excelList.html')">
            </li>
        </ul>
        <ul>
            <li>
                <span>客户手机</span>
                <input type="text" name="p_customerMobile" value="${param.p_customerMobile}">
            </li>
            <li>
                <span>订单时间</span>
                <input type="text" name="p_startTime" value="${param.p_startTime}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_endTime" value="${param.p_endTime}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>订单ID</td>
                <td>订单类型</td>
                <td>客户手机</td>
                <td>客户姓名</td>
                <td>总体评价</td>
                <td>标签</td>
                <td>车管家评价</td>
                <td>标签</td>
                <td>商家评价</td>
                <td>标签</td>
                <td width="10%">评论内容</td>
                <%--<td width="8%">订单时间</td>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${(page.currentPage - 1) * page.pageSize + status.index + 1}</td>
                    <td>${row.orderId}</td>
                    <td>${row.orderTypeName}</td>
                    <td>${row.customerMobile}</td>
                    <td>${row.customerNickname}</td>
                    <td>${row.totalityStar}</td>
                    <td class="commentTags">${fn:replace(row.totalityTag, '=', '<br/>')}</td>
                    <td>${row.carManagerStar}</td>
                    <td class="commentTags">${fn:replace(row.carManagerTag, '=', '<br/>')}</td>
                    <td>${row.businessStar}</td>
                    <td class="commentTags">${fn:replace(row.businessTag, '=', '<br/>')}</td>
                    <td>${row.commentContent}</td>
                    <%--<td>${row.createDate}</td>--%>
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
