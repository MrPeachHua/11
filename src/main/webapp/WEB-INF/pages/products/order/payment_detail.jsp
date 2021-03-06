<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type=text/css rel=stylesheet>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script>
        function exportExcel(){
            var cType = $("#cType").val();
            var onlineType = $("#onlineType").val();
            location.href="<%=basePath%>products/order/excelPaymentDetailReport.html/${cDate}/${region}/${parkingId}?cType="+cType+"&onlineType="+onlineType;
        }
    </script>
</head>
<body>
<form method="post" action="<%=basePath%>products/order/paymentDetail.html/${cDate}/${region}/${parkingId}">
    <div class="queryView" style="height: 50px">
        <ul>
            <li>
                <span>类型:</span>
                <select name="cType" id="cType">
                    <option value="">全部</option>
                    <option <c:if test="${cType eq '11'}">selected="selected"</c:if> value="11">临停</option>
                    <option <c:if test="${cType eq '13'}">selected="selected"</c:if> value="13">月租</option>
                    <option <c:if test="${cType eq '14'}">selected="selected"</c:if> value="14">产权</option>

                </select>
            </li>
            <li>
                <span>支付类型:</span>
                <select name="onlineType" id="onlineType">
                    <option value="">全部</option>
                    <option <c:if test="${onlineType eq '1'}">selected="selected"</c:if> value="1">线上</option>
                    <option <c:if test="${onlineType eq '2'}">selected="selected"</c:if> value="2">线下</option>
                </select>
            </li>
            <li style="margin-left: 35%">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
            </li>
            <li>
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel()">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>日期</td>
                <td>车场名称</td>
                <td>所属区域</td>
                <td>类型</td>
                <td>支付类型</td>
                <td>收入金额</td>
                <td>详情</td>
                <%--<td width="8%">订单时间</td>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${odList}" var="row" varStatus="status">
                <tr>
                    <td><fmt:formatDate value="${row.payTime}" pattern="yyyy-MM-dd"/></td>
                    <td>${parkingName}</td>
                    <td>
                        <c:if test="${region eq '1'}">第一区域</c:if>
                        <c:if test="${region eq '2'}">第二区域</c:if>
                        <c:if test="${region eq '3'}">第三区域</c:if>
                    </td>
                    <td>
                        <c:if test="${row.orderType eq '11'}">临停</c:if>
                        <c:if test="${row.orderType eq '13'}">月租</c:if>
                        <c:if test="${row.orderType eq '14'}">产权</c:if>
                    </td>
                    <td>
                        <c:if test="${row.onlineType eq '1'}">线上</c:if>
                        <c:if test="${row.onlineType eq '2'}">线下</c:if>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${row.sumPaid != null}">
                                <fmt:formatNumber type="number" maxFractionDigits="0" value="${row.sumPaid}" /><br>
                            </c:when>
                            <c:otherwise>0</c:otherwise>
                        </c:choose>

                    </td>
                    <td><a href="<%=basePath%>products/order/list2.html?inputDate=<fmt:formatDate value="${row.payTime}" pattern="yyyy-MM-dd"/>&region=${row.region}&orderType=${row.orderType}&parkingId=${parkingId}&onlineType=${row.onlineType}&sumPaid=${row.sumPaid}
">详情</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <c:if test="${fn:length(odList) == 0}">
            <div class="contentLine">
                没有任何数据
            </div>
        </c:if>
    </div>
</form>
</body>
</html>
