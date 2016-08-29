<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查询订单信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	  <LINK href="<%=basePath%>css/base.css" type=text/css rel=stylesheet>
	  <LINK href="<%=basePath%>css/fileList.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>

<script>
var  highlightcolor='#c1ebff';
//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
$(document).ready(function(){
 if($("#select1").val()!=null){
 $("#orderType").val($("#select1").val());
 }
//  if($("#select2").val()!=null){
// $("#orderStatus").val($("#select2").val());
// }
 if($("#select3").val()!=null){
 $("#payType").val($("#select3").val());
 }
 
});
</script>
<script type="text/javascript">

/* 导出excel表 */
function reset1(){
$("#customerName").val(null);
$("#customerPhone").val(null);
$("#form_beginTime").val(null);
$("#form_endTime").val(null);
$("#orderType").val("");
$("#orderStatus").val("");
$("#payType").val("");
}
function excelExport(){
	var orderType = $("#select1").val();
	var orderStatus = $("#orderStatus").val();
	var orderId = $("#orderId").val();
	var customerPhone = $("#customerPhone").val();
	var form_beginTime = $("#form_beginTime").val();
	var form_endTime = $("#form_endTime").val();
	var payType = $("#payType").val();
	window.location="<%=basePath%>products/order/excelExport.html?orderType="+orderType+"&orderStatus="+orderStatus+"&orderId="+orderId+"&orderStatus="+orderStatus+"&customerPhone="+customerPhone+"&form_beginTime="+form_beginTime+"&form_endTime="+form_endTime+"&payType="+payType;
}
</script>
  </head>
  
  <body>

  	<form  action="<%=basePath%>products/order/list.html" method="post">
  	<div style="height: 60px;">
<%--	<table width="100%" border="0" cellspacing="0" cellpadding="0">--%>
	  <tr>
    	<td height="30" background="<%=imagePath%>tab_0501.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	       <%-- <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="60" /></td>--%>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="60">
 						 <tr>
			        <td class="STYLE4" align="center">&nbsp;&nbsp;订单类型：
			        	<select id="orderType" class="btn_tb" name = "orderType"   >
			        		<option value=""> </option>
			        		<c:forEach items="${orderType }" var="orderType">
			        		<option value="${orderType.dictValue }">${orderType.dictName}</option>
			        		</c:forEach>
			        	</select>
			        	<input type="hidden" id="select1" value="${page.params['orderType'] }"/>
			        </td>
			        <td class="STYLE4">&nbsp;&nbsp;订单状态：
			        	<select name = "orderStatus" id="orderStatus" class="btn_tb">
			        		<option value=""> </option>
			        		<c:forEach items="${orderStatus}" var="orderStatus">
			        		<option <c:if test="${(page.params['orderStatus'] == null && orderStatus.dictValue eq '11') || page.params['orderStatus'] eq orderStatus.dictValue}">selected="selected"</c:if> value="${orderStatus.dictValue }">${orderStatus.dictName}</option>
			        		</c:forEach>
			        	</select> 
			        	<%--<input type="hidden" id="select2" value="${page.params['orderStatus'] }"/>--%>
					</td>
					<td class="STYLE4">&nbsp;&nbsp;订单ID： &nbsp;&nbsp;
			        	<input type="text"  name="orderId" id="orderId" value='${page.params["orderId"] }' class="btn_tb"/>
					</td>
			        <td class="STYLE4"><input  type="submit" value="查询"  class="btn"/></td>
			        <td class="STYLE4"><input  type="button" name="reset" value="重置" onclick="reset1()" class="btn"/></td>
							 <td class="STYLE4">
								 <security:authorize access="hasAnyRole('AUTH_EXP_0056')">
									 <input  type="button" onclick="excelExport()" value="导出" class="btn"/>
								 </security:authorize>
							 </td>
						 </tr>
 					<tr>
 						<td class="STYLE4" align="center">&nbsp;&nbsp;客户手机：
 						 <input type="text" name="customerPhone" id="customerPhone"  value='${page.params["customer_mobile"] }'  class="btn_tb"/></td>
 						 
 						<td class="STYLE4" > &nbsp;&nbsp;支付时间： <input type="text" placeholder="开始时间" id="form_beginTime" name="form_beginTime" value='${page.params["startTime"] }'  class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime,dateFmt:'yyyy-MM-dd'})"/>
									 <font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
									<input type="text" placeholder="截止时间" id="form_endTime" name="form_endTime"  class="btn_tb" readonly="readonly" value='${page.params["stopTime"] }'  onclick="WdatePicker({el:form_endTime,dateFmt:'yyyy-MM-dd'})"/>
									</td>
 						<td class="STYLE4" >&nbsp;&nbsp;支付类型：&nbsp;<select id="payType" name = "payType" class="btn_tb">
			        		<option value=""></option>
			        		<option value="00">支付宝</option>
			        		<option value="01">微信</option>
			        		<option value="02">银联</option>
			        		<option value="03">线下</option>
			        		<option value="04">其他</option>
							<option value="05">钱包支付</option>
			        	</select>
			        	<input type="hidden" id="select3" value='${page.params["payType"] }'/></td>
			         </tr>
			    </table>
			</td>
	        <%--<td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="60" /></td>--%>
	      </tr>
    	</table>
    	</td>
  	  </tr>
</div>
  <tr>
    <td>
    <table class="table_content" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <%--<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>--%>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="0"  onmouseover="changeto()"  onmouseout="changeback()">
          <tr  height="50" style="background-color:#ecf6ff">
            <td style="width: 5%"><div align="center"><span class="STYLE1">订单ID</span></div></td>
            <td style="width: 4%"><div align="center"><span class="STYLE1">订单类型</span></div></td>
            <td style="width: 5%" ><div align="center"><span class="STYLE1">订单状态 </span></div></td>
	    	<td style="width: 5%"><div align="center"><span class="STYLE1">发票状态 </span></div></td>
	    	<td style="width: 5%"><div align="center"><span class="STYLE1">是否需开具发票 </span></div></td>
	    	<td style="width: 5%"><div align="center"><span class="STYLE1">客户名称</span></div></td>
            <td style="width: 5%"><div align="center"><span class="STYLE1">客户手机</span></div></td>
            <td style="width: 5%"><div align="center"><span class="STYLE1">应付金额</span></div></td>
            <td style="width: 5%"><div align="center"><span class="STYLE1">优惠金额 </span></div></td>
	    	<td style="width: 5%"><div align="center"><span class="STYLE1">实付金额 </span></div></td>
	    	<td style="width: 5%"><div align="center"><span class="STYLE1">支付类型</span></div></td>
            <td style="width: 5%"><div align="center"><span class="STYLE1">支付时间</span></div></td>
            <td style="width: 5%"><div align="center"><span class="STYLE1">支付方交易号 </span></div></td>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr  class="underline" height="50" style="background-color: #FFF;" >
            <td style="width:  3%"><div align="center"><span class="STYLE1"><c:if test="${!(row.orderType eq '17' || row.orderType eq '16') }"><a href="<%=basePath%>products/order/view.html?id=${row.orderId}&orderType=${row.orderType}"></c:if>${row.orderId}</a> </span></div></td>
            <td style="width:  3%"><div align="center"><span class="STYLE1">
            <c:if test="${row.orderType eq 10}">优惠停车</c:if>
            <c:if test="${row.orderType eq 11}">临停缴费</c:if>
            <c:if test="${row.orderType eq 12}">代泊</c:if>
            <c:if test="${row.orderType eq 13}">月租</c:if>
            <c:if test="${row.orderType eq 14}">产权</c:if>
            <c:if test="${row.orderType eq 15}">加油卡</c:if>
		    <c:if test="${row.orderType eq 16}">钱包充值</c:if>
			<c:if test="${row.orderType eq 17}">洗车</c:if>
			</span></div></td>
           <%-- <td style="width:  5%"><div align="center"><span class="STYLE1"  <c:if test="${row.orderStatus eq 10}"> style="color:#ff0000" </c:if>>
             <c:if test="${row.orderStatus eq 1}">已预约</c:if>
             <c:if test="${row.orderStatus eq 2}">已接车</c:if>
             <c:if test="${row.orderStatus eq 3}">已交车</c:if>
             <c:if test="${row.orderStatus eq 4}">已停车</c:if>
             <c:if test="${row.orderStatus eq 5}">已完成</c:if>
             <c:if test="${row.orderStatus eq 6}">已取车</c:if>
             <c:if test="${row.orderStatus eq 7}">已拒绝</c:if>
             <c:if test="${row.orderStatus eq 8}">待取车</c:if>
             <c:if test="${row.orderStatus eq 9}">提车中</c:if>
             <c:if test="${row.orderStatus eq 10}">未付款</c:if>
             <c:if test="${row.orderStatus eq 11}">已付款</c:if>
             <c:if test="${row.orderStatus eq 12}">已取消</c:if>
				<c:if test="${row.orderStatus eq 13}">数据异常</c:if>
            </span></div></td>--%>
			  <c:forEach items="${orderStatus}" var="item">
				  <c:if test="${item.dictValue eq row.orderStatus}">
					  <td style="width:  5%"><div align="center"><span class="STYLE1" <c:if test="${row.orderStatus eq 10}"> style="color:#ff0000" </c:if>>${item.dictName}</span></div></td>
				  </c:if>
			  </c:forEach>
	  	  	<td style="width:  5%"><div align="center"><span class="STYLE1"><c:if test="${row.invoiceStatus eq 0}">未开具</c:if><c:if test="${row.invoiceStatus eq 1}">已开具</c:if></span></div></td>
	  	  	<td style="width:  5%"><div align="center"><span class="STYLE1"><c:if test="${row.isNeedInvoice eq 0}">不需要</c:if><c:if test="${row.isNeedInvoice eq 1}">需要</c:if></span></div></td>	  	  	
	  	  	<td style="width:  5%"><div align="center"><span class="STYLE1">${row.customer_nickname}</span></div></td>
            <td style="width:  5%"><div align="center"><span class="STYLE1">${row.customer_mobile}</span></div></td>
            <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.amountPayable/100}</span></div></td>
            <td style="width:  5%"><div align="center" ><span  class="STYLE1"  <c:if test="${row.orderStatus eq 10}"> style="color:#ff0000" </c:if>> ${row.amountDiscount/100}</span></div></td>
	  	  	<td style="width:  5%"><div align="center"><span class="STYLE1">${row.amountPaid/100}</span></div></td>
	  	  	<td style="width:  5%"><div align="center"><span class="STYLE1">
	  	  	<c:if test="${row.payType eq '00'}">支付宝</c:if> 
	  	  	<c:if test="${row.payType eq '01'}">微信</c:if>
	  	  	<c:if test="${row.payType eq '02'}">银联</c:if>
	  	  	<c:if test="${row.payType eq '03'}">线下</c:if>
	  	  	<c:if test="${row.payType eq '04'}">其他</c:if>
	  	  	<c:if test="${row.payType eq '05'}">钱包支付</c:if>
	  	  	</span></div></td>
            <td style="width:  8%"><div align="center" ><span class="STYLE1"><fmt:formatDate  value="${row.payTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </span></div></td>
            <td style="width:  5%"><div align="center"><span class="STYLE1">${row.tradeNo}</span></div></td>
          </tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise>
			  <tr>
				<td colspan="21"  align="center">
				 	<div align="center"><span class="STYLE1">没有订单信息</span></div>
				</td>
			  </tr>
	      </c:otherwise>	
		  </c:choose>
          
          </table>
        </td>
     <%--   <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>--%>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
   <%-- <td height="35" background="<%=imagePath%>tab_19.gif">--%>
	<jsp:include page="/frame/page.html" />
    </td>
  </tr>

    </table>  
    </form>
</body>
</html>
