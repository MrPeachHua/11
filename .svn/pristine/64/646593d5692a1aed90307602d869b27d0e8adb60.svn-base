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
    <style type="text/css">
        a {
            color: #6D7D83;
            text-decoration: none;
        }
    </style>
</head>
<body>
<form method="post" action="<%=basePath%>/products/carLifeActivity/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>开始时间范围</span>
                <input type="text" name="p_startDateBegin" value="${param.p_startDateBegin}" placeholder="开始时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_startDateEnd" value="${param.p_startDateEnd}" placeholder="结束时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
            <li>
                <span>类型</span>
                <select name="p_type">
                    <option value="">全部</option>
                    <option <c:if test="${param.p_type eq '1'}">selected="selected"</c:if> value="1">优惠活动</option>
                    <option <c:if test="${param.p_type eq '2'}">selected="selected"</c:if> value="2">用车心得</option>
                </select>
            </li>
            <li class="right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">
                <input class="searchBtn" type="button" value="新&nbsp;增" onclick="location='<%=basePath%>products/carLifeActivity/add.html'">
            </li>
        </ul>
        <ul>
            <li>
                <span>结束时间范围</span>
                <input type="text" name="p_endDateBegin" value="${param.p_endDateBegin}" placeholder="开始时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_endDateEnd" value="${param.p_endDateEnd}" placeholder="结束时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
            <li>
                <span>标题</span>
                <input type="text" name="p_title" value="${param.p_title}">
            </li>
            <li>
                <span>适用车场</span>
                <input type="text" name="p_parkingName" value="${param.p_parkingName}">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>开始时间</td>
                <td>结束时间</td>
                <td>类型</td>
                <td>标题</td>
                <td>适用车场</td>
                <td width="20%">URL地址</td>
                <td>排序</td>
                <td width="10%">基本操作</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <c:choose>
                        <c:when test="${row.type eq '1'}">
                            <td><fmt:formatDate value="${row.startDate}" type="both" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${row.endDate}" type="both" pattern="yyyy-MM-dd"/></td>
                        </c:when>
                        <c:otherwise>
                            <td>——</td>
                            <td>——</td>
                        </c:otherwise>
                    </c:choose>
                    <%--类型: 1.优惠活动 2.用车心得--%>
                    <td>${row.type eq '1' ? '优惠活动' : row.type eq '2' ? '用车心得' : ''}</td>
                    <td>${row.title}</td>
                    <td>${row.parkingName}</td>
                    <td style="word-break:break-all;"><a href="${row.url}" target="_blank">${row.url}</a></td>
                    <td>${row.sort}</td>
                    <td>
                        <input class="edit" type="button" onclick="location='<%=basePath%>products/carLifeActivity/${row.id}/edit.html'"/>
                        <input class="delete" type="button" onclick="confirm('你确定？')?location='<%=basePath%>products/carLifeActivity/${row.id}/delete.html':void(0)"/>
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
