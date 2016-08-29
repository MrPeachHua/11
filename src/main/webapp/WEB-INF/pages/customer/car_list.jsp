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
    <title>客户车辆</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <%--<link href="<%=basePath%>/css/pages/commentTags.css" type="text/css" rel="stylesheet">--%>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/pages/commentTags.js" type="text/javascript"></script>--%>
</head>
<script>
   function queryCarSeries(){
       document.getElementById("carSeries").innerHTML = "";
       var carBrand=$("#carBrand").val();
       $.ajax({
           cache: false,
           type: "POST",
           url:'queryCarSeries.html',
           data:{"carBrand":carBrand},
           dataType: "json",
           success: function (jsonStr) {
               $("#carSeries").append('<option value=""></option>');
               for(var i=0;i<jsonStr.length;i++){
                   $("#carSeries").append('<option  value='+jsonStr[i].CAR_SERIES+'>' +jsonStr[i].CAR_SERIES + '</option>');
               }
           }
       });
   }
   function queryDisplacement(){
       document.getElementById("displacement").innerHTML = "";
       var carBrand=$("#carBrand").val();
       var carSeries=$("#carSeries").val();
       $.ajax({
           cache: false,
           type: "POST",
           url:'queryDisplacement.html',
           data:{"carBrand":carBrand,"carSeries":carSeries},
           dataType: "json",
           success: function (jsonStr) {
               $("#displacement").append('<option value=""></option>');
               for(var i=0;i<jsonStr.length;i++){
                 //  alert(jsonStr[i].DISPLACEMENT);
                   $("#displacement").append('<option <%--<c:if test="${jsonStr[i].DISPLACEMENT eq page.params['displacement'] }"> selected="selected" </c:if>--%> value='+jsonStr[i].DISPLACEMENT+'>' +jsonStr[i].DISPLACEMENT + '</option>');
               }
           }
       });
   }
   function queryStyleYear(){
       document.getElementById("styleYear").innerHTML = "";
       var carBrand=$("#carBrand").val();
       var carSeries=$("#carSeries").val();
       var displacement=$("#displacement").val();
       $.ajax({
           cache: false,
           type: "POST",
           url:'queryDisplacement.html',
           data:{"carBrand":carBrand,"carSeries":carSeries,"displacement":displacement},
           dataType: "json",
           success: function (jsonStr) {
               $("#styleYear").append('<option value=""></option>');
               for(var i=0;i<jsonStr.length;i++){
                   $("#styleYear").append('<option <%--<c:if test='${ parkingId  eq jsonStr[i].parkingId}'> selected="selected" </c:if>--%> value='+jsonStr[i].STYLE_YEAR+'>' +jsonStr[i].STYLE_YEAR + '</option>');
               }
           }
       });
   }
 //  window.onload=queryCarSeries;
</script>
<body>
<form method="post" action="<%=basePath%>customer/customer_car/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>手机号</span>
                <%--<select name="p_orderType" class="dictionary" data-current-value="${param.p_orderType}" data-dict-code="order_type">
                    <option value="">全部</option>
                </select>--%>
                <input type="text" name="p_customerMobile" value="${param.p_customerMobile}">
            </li>
            <li>
                <span>车牌号</span>
                <input type="text" name="p_carNumber" value="${param.p_carNumber}">
            </li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li></li>
            <li>
                <input class="searchBtn"  type="submit" value="查&nbsp;询">
                <%--<input  class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">--%>
                <input   class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>customer/customer_car/excelExport.html')">
            </li>
        </ul>
        <ul>
            <li style="align-content: center">
                <span>品牌</span>
                <select name="p_carBrand" id="carBrand"  onchange="queryCarSeries()">
                    <option value="">全部</option>
                    <c:forEach var="carBrand" items="${listCarBrand}">
                        <option <c:if test="${carBrand.brandName eq page.params['carBrand'] }"> selected="selected" </c:if>   value="${carBrand.brandName}">${carBrand.brandName}</option>
                    </c:forEach>
                </select>
            </li>
            <li>
                <span>车系</span>
                <select name="p_carSeries" id="carSeries" onchange="queryDisplacement()">
                  <%--  <option value="">全部</option>--%>
                </select>
            </li>
            <li>
                <span>排量</span>
                <select name="p_displacement" id="displacement" onchange="queryStyleYear()">
                   <%-- <option value="">全部</option>--%>
                </select>
            </li>
            <li>
                <span>年产</span>
                <select name="p_styleYear" id="styleYear">
                  <%--  <option value="">全部</option>--%>
                </select>
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <td>序号</td>
                <td>用户ID</td>
                <td>手机号码</td>
                <td>车牌号码</td>
                <td>行驶里程</td>
                <td>上路时间</td>
                <td>自动支付</td>
                <td>车架号</td>
                <td>发动机号</td>
                <td>车型</td>
                <td>状态</td>
                <td>添加时间</td>
                <%--<td width="8%">订单时间</td>--%>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td>${(page.currentPage - 1) * page.pageSize + status.index + 1}</td>
                    <td>${row.customer_id}</td>
                    <td>${row.customer_mobile}</td>
                    <td>${row.car_number}</td>
                    <td>${row.travlled_distance}</td>
                    <td>${row.car_use_date}</td>
                    <td><c:if test="${row.is_auto_pay == 0}">否</c:if><c:if test="${row.is_auto_pay == 1}">是</c:if></td>
                    <td>${row.frame_num}</td>
                    <td>${row.engine_num}</td>
                    <td>${row.car_brand}${row.CAR_SERIES} ${row.VEHICLE_TYPE} ${row.DISPLACEMENT}${row.intake_name}${row.STYLE_YEAR}</td>
                    <td>1</td>
                    <td>2016.01.01</td>
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
