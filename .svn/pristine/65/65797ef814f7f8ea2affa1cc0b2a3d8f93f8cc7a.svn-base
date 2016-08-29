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
<form method="post" action="<%=basePath%>customer/member_recommend/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>类型:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <select name="p_type" class="dictionary" >
                    <option value="">全部</option>
                    <option <c:if test='${page.params["type"] eq 1 }'>selected="selected"</c:if> value="1">新注册用户使用兑换码获取的优惠券</option>
                    <option <c:if test='${page.params["type"] eq 2 }'>selected="selected"</c:if> value="2">老用户兑换码被兑换一定次数后可领取的优惠券</option>
                </select>
            </li>
            <li>
                <span>被领取者用户ID:</span>
                <input type="text" name="p_oldCustomerId"  value='${page.params["oldCustomerId"] }'>
            </li>
            <li>
                <span>领取者用户ID:</span>
                <input type="text" name="p_newCustomerId"  value='${page.params["newCustomerId"] }'>
            </li>

            <li>
                <input class="searchBtn" type="submit" value="查&nbsp;询">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>customer/member_recommend/excelList.html')">
            </li>
        </ul>
            <ul>
            <li>
                <span>创建时间:</span>
                <input type="text" placeholder="开始时间" id="form_beginTime_out" name="p_form_beginTime_out" value='${page.params["form_beginTime_out"] }'    class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime_out,dateFmt:'yyyy-MM-dd'})"/>
                <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
                <input type="text" placeholder="截止时间"  id="form_endTime_out" name="p_form_endTime_out"  class="btn_tb" readonly="readonly" value='${page.params["form_endTime_out"] }'  onclick="WdatePicker({el:form_endTime_out,dateFmt:'yyyy-MM-dd'})"/>
            </li>

        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>类型</td>
                <td>被领取者的用户ID</td>
                <td>领取者的用户ID</td>
                <td>是否可用</td>
                <td>创建人</td>
                <td>创建时间</td>
                <%--<td width="8%">订单时间</td>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${(page.currentPage - 1) * page.pageSize + status.index + 1}</td>
                    <td>
                        <c:if test="${row.type eq 1}">新注册用户使用兑换码获取的优惠券</c:if>
                        <c:if test="${row.type eq 2}">老用户兑换码被兑换一定次数后可领取的优惠券</c:if>

                    </td>
                    <td>${row.oldCustomerId}</td>
                    <td>${row.newCustomerId}</td>
                    <td><c:if test="${row.isUsed eq 0}">不可用</c:if>
                        <c:if test="${row.isUsed eq 1}">可用</c:if>
                    </td>
                    <td>${row.createor}</td>
                    <td><fmt:formatDate  value="${row.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
