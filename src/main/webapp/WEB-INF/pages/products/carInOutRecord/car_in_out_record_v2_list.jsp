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
<form method="post" action="<%=basePath%>products/carInOutRecord/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>出入状态</span>
                <select name="p_inOrOut">
                    <option value="">全部</option>
                    <option <c:if test="${param.p_inOrOut eq '1'}">selected="selected"</c:if> value="1">入场</option>
                    <option <c:if test="${param.p_inOrOut eq '2'}">selected="selected"</c:if> value="2">出场</option>
                </select>
            </li>
            <li>
                <span>车场名称</span>
                <input type="text" name="p_parkingName" value="${param.p_parkingName}"/>
            </li>
            <li>
                <span>车牌号</span>
                <input type="text" name="p_plateId" value="${param.p_plateId}"/>
            </li>
            <li class="right">
                <input class="searchBtn" type="submit" value="查&nbsp;询"/>
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()"/>
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>products/carInOutRecord/excelList.html')"/>
            </li>
        </ul>
        <ul>
            <li>
                <span>入场时间</span>
                <input type="text" name="p_inTimeBegin" value="${param.p_inTimeBegin}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_inTimeEnd" value="${param.p_inTimeEnd}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
            </li>
            <li>
                <span>出场时间</span>
                <input type="text" name="p_outTimeBegin" value="${param.p_outTimeBegin}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_outTimeEnd" value="${param.p_outTimeEnd}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>车场名称</td>
                <td>车牌号</td>
                <td>进场/出场</td>
                <td>入场时间</td>
                <td>出场时间</td>
                <td>应收金额</td>
                <td>实收金额</td>
                <td>停车时间</td>
                <td>收费方式</td>
                <td>收费说明</td>
                <td>操作员</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td><a href="<%=basePath%>products/carInOutRecord/view.html?id=${row.id}">${row.parkingName}</a></td>
                    <td>${row.plateId}</td>
                    <td>${row.outparkId == null ? '入场' : '出场'}</td>
                    <td><fmt:formatDate value="${row.inTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td><fmt:formatDate value="${row.outTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                    <td>${row.payCharge/100.00}</td>
                    <td>${row.realCharge/100.00}</td>
                    <td>${row.stayTime}</td>
                    <td>${row.payType}</td>
                    <td>${row.remark}</td>
                    <td>${row.operator}</td>
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
