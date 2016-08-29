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
    <title>缴费统计</title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type=text/css rel=stylesheet>
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/dictionary.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/pageListUtil.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script>
        function exportExcel(link){
            location.href=link;
        }
    </script>
</head>
<body>
<form method="post" action="<%=basePath%>reports/carInOutRecord/list.html">
    <div class="queryView">
        <ul>
            <li>
                <span>车场名称:</span>
                <input type="text"  name="parkingName" id="parkingName" value='${page.params["parkingName"] }' class="btn_tb"/>
            </li>
            <li>
                <span>车牌号:</span>
                <input type="text"  name="plateId" id="plateId" value='${page.params["plateId"] }' class="btn_tb"/>
            </li>
            <li>
                <span>类型:&nbsp;&nbsp;&nbsp;</span>
                <select name="type" class="btn_tb">
                    <option value="空">月租</option>
                    <option value="空">产权</option>
                    <option value="空">临停</option>
                </select>
            </li>
            <li>
                <span>收费方式:</span>
                <select name="type" class="btn_tb">
                    <option value="空">免费</option>
                    <option value="空">现金</option>
                    <option value="空">云收费</option>
                    <option value="空">固定车辆直接出场</option>
                </select>
            </li>
            <li>
                <span>出场时间:</span>
                <input type="text" placeholder="开始时间" id="form_beginTime_out" name="form_beginTime_out" value='${page.params["startOutTime"] }'    class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime_out,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
                <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
                <input type="text" placeholder="截止时间"  id="form_endTime_out" name="form_endTime_out"  class="btn_tb" readonly="readonly" value='${page.params["endOutTime"] }'  onclick="WdatePicker({el:form_endTime_out,dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
            </li>
            <li>
                <span style="margin-left: 60px">操作员:</span>
            <input type="text"  name="parkingName" id="parkingName" value='${page.params["parkingName"] }' class="btn_tb"/>
            </li>
            <li style="float: right">
                <input class="searchBtn" type="submit" value="查&nbsp;询">
                <!--<input class="searchBtn" type="button" value="重&nbsp;置" onclick="clearParams()">-->
                <input class="searchBtn" type="button" value="导&nbsp;出" onclick="exportExcel('<%=basePath%>products/order/excelCarInOutReport.html')">
            </li>
        </ul>
    </div>
    <div class="contentView">
        <table class="contentTable" cellspacing="0" cellpadding="0" border="0">
            <thead>
            <tr>
                <%-- <td>序号</td>--%>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">车场名称 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">类型 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">车牌号</span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">入场时间 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">出场时间 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">停车时间 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">收费方式 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">收费说明 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">应收金额 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">实收金额 </span></div></td>
                    <td style="width: 5%"><div align="center"><span class="STYLE1">操作员 </span></div></td>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${page.resultList}" var="row" varStatus="status">
                <tr>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.parkingName}</span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1"></span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.plateId}</span></div></td>
                    <td style="width:  8%"><div align="center" ><span class="STYLE1"><fmt:formatDate  value="${row.intime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </span></div></td>
                    <td style="width:  8%"><div align="center" ><span class="STYLE1"><fmt:formatDate  value="${row.outtime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.dayTime}</span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1"></span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1"></span></div></td>

                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.payCharge}</span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1">
                            ${row.realCharge}
                    </span></div></td>
                    <td style="width:  5%"><div align="center" ><span  class="STYLE1"></span></div></td>
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
    </tr>
    <ul style="list-style: none;margin-left:auto">
        <li style="padding: 20px;text-align:center;">
            &nbsp;&nbsp;&nbsp;<span style="font-weight: 700;"> 单页应收合计：</span><span class="STYLE1"> 现金：0元&nbsp;&nbsp; 固定车直接出场：0元&nbsp;&nbsp; 云收费：10元&nbsp;&nbsp; 免费：20元</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight: 700;">全部应收合计：</span><span class="STYLE1"> 现金：0元&nbsp;&nbsp; 固定车直接出场：0元&nbsp;&nbsp; 云收费：10元&nbsp;&nbsp; 免费：20元</span>
        </li>
        <li style="padding: 20px;text-align:center;">
            &nbsp;&nbsp;&nbsp;<span style="font-weight: 700;">单页实收合计：</span><span class="STYLE1"> 现金：0元&nbsp;&nbsp; 固定车直接出场：0元&nbsp;&nbsp; 云收费：10元&nbsp;&nbsp; 免费：20元</span>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style="font-weight: 700;">全部实收合计：</span><span class="STYLE1"> 现金：0元&nbsp;&nbsp; 固定车直接出场：0元&nbsp;&nbsp; 云收费：10元&nbsp;&nbsp; 免费：20元</span>
        </li>

    </ul>
    <jsp:include page="/frame/page.html"/>
</form>
</body>
</html>
