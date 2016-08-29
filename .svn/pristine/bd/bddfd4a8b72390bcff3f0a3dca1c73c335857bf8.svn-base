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
    <title>缴费统计</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type=text/css rel=stylesheet>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script>
        //function exportExcel(link){
           // location.href=link;
       // }
       /* $(function(){
            $("#parkingName").innerHTML = "";
            var region=$("#p_region").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
                data:{"region":region},
                dataType: "json",
                success: function (jsonStr) {
                    $("#parkingName").append('<option value=""></option>');
                    for(var i=0;i<jsonStr.length;i++){
                        $("#parkingName").append('<option value='+jsonStr[i].parkingId+'>' +jsonStr[i].parkingName + '</option>');
                    }

                }
            });
        })*/
        function queryParking(){
            document.getElementById("parkingName").innerHTML = "";
            var region=$("#p_region").val();
            $.ajax({
                type: "POST",
                url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
                data:{"region":region},
                dataType: "json",
                success: function (jsonStr) {
                    $("#parkingName").append('<option value=""></option>');
                    for(var i=0;i<jsonStr.length;i++){
                        /* <c:if test='${parkingId  eq jsonStr[i].parkingId}'>selected="selected"</c:if>*/
                        $("#parkingName").append('<option <%--<c:if test='${ parkingId  eq jsonStr[i].parkingId}'> selected="selected" </c:if>--%> value='+jsonStr[i].parkingId+'>' +jsonStr[i].parkingName + '</option>');
                    }

                }
            });
        }
        window.onload=queryParking;
    </script>
</head>
<body>
<form method="post" action="<%=basePath%>products/order/queryStatistics.html">
    <div class="queryView">
        <ul>
            <li>
                <span>日期:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                <input type="text" name="p_beginDate" value="${param.p_beginDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                <span class="mainColor"><strong>—</strong></span>
                <input type="text" name="p_endDate" value="${param.p_endDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
            </li>
            <li>
                <span>所属区域:</span>
                <select name="p_region" id="p_region" class="dictionary" <%-- data-current-value="${param.p_region}"--%> data-dict-code="region" onchange="queryParking()" >
                    <option value=""></option>
                </select>
                <input type="hidden" name="p_region1" value="${param.p_region}">
            </li>
            <li>
                <span>车场名称:</span>
                <select name="p_parkingName" id="parkingName">
                   <%-- <option value="">全部</option>
                    <c:forEach var="parking" items="${parking}">
                        <option <c:if test="${param.p_parkingId eq parking.parkingId}">selected="selected"</c:if>  value="${parking.parkingId}">${parking.parkingName}</option>
                    </c:forEach>--%>
                </select>
                <input type="hidden" name="p_parkingName1" value="${param.p_parkingName}">
            </li>
            <li >
                <span>区域负责人:</span>
                <input type="text" name="p_regionManager" value="${param.p_regionManager}">
            </li>
            <li style="margin-left: 225px">
                <span>项目负责人:</span>
                <input type="text" name="p_regionLeader" value="${param.p_regionLeader}">
            </li>
            <li style="float: right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <!--<input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">-->
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>products/order/excelQueryStatistics.html')">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
               <%-- <td>序号</td>--%>
                <td>日期</td>
                <td>所属区域</td>
                <td>车场名称</td>
                <td>线上收入(元)</td>
                <td>线下收入(元)</td>
                <td>合计收入(元)</td>
                <td>项目负责人</td>
                <td>区域负责人</td>
                <td>详情</td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <%--<td>${(page.currentPage - 1) * page.pageSize + status.index + 1}</td>--%>
                    <td>${row.statistics_date}</td>
                    <td>${row.regionName}</td>
                    <td>${row.parking_name}</td>
                    <td>${row.amount_online}</td>
                    <td>${row.amount_offline}</td>
                    <td>${row.amount_total}</td>
                    <td>${row.REGION_LEADER}</td>
                    <td>${row.REGION_MANAGER}</td>
                    <td><a <c:if test="${row.region !=null && row.region !=''}"> href="<%=basePath%>products/order/paymentDetail.html/${row.statistics_date}/${row.region}/${row.parking_id}"</c:if> <c:if test="${row.region ==null || row.region ==''}"> href="#" onclick="javascript:alert('请先给车场配置区域！')" </c:if>>查看</a></td>
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
<%--
<script>
    document.getElementById("parkingName").innerHTML = "";
    var region=$("#p_region").val();
    $.ajax({
        type: "POST",
        url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
        data:{"region":region},
        dataType: "json",
        success: function (jsonStr) {
            $("#parkingName").append('<option value=""></option>');
            for(var i=0;i<jsonStr.length;i++){
                $("#parkingName").append('<option &lt;%&ndash;<c:if test='${param.p_parkingId eq jsonStr[i].parkingId}'>selected="selected"</c:if>&ndash;%&gt; value='+jsonStr[i].parkingId+'>' +jsonStr[i].parkingName + '</option>');
            }

        }
    });
</script>--%>
