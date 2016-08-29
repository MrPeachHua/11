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

  	<form  action="<%=basePath%>system/smsLogs/list.html" method="post">
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

			        <td class="STYLE4">&nbsp;&nbsp;用户名：
						<input type="text"  name="userName"  class="btn_tb"/>
					</td>
					<td class="STYLE4">&nbsp;&nbsp;日志概要： &nbsp;&nbsp;
			        	<input type="text"  name="logSummary" id="logSummary"  class="btn_tb"/>
					</td>
						<td class="STYLE4" > &nbsp;&nbsp;日志日期： <input type="text" placeholder="开始时间" id="form_beginTime" name="form_beginTime" value='${page.params["startTime"] }'  class="btn_tb" readonly="readonly"  onclick="WdatePicker({el:form_beginTime,dateFmt:'yyyy-MM-dd'})"/>
							<font style="color: #39d5b8">—</font> &nbsp;&nbsp;&nbsp;
							<input type="text" placeholder="截止时间" id="form_endTime" name="form_endTime"  class="btn_tb" readonly="readonly" value='${page.params["stopTime"] }'  onclick="WdatePicker({el:form_endTime,dateFmt:'yyyy-MM-dd'})"/>
						</td>

						<td class="STYLE4"><input  type="submit" value="查询"  class="btn"/></td>
			        <td class="STYLE4"><input  type="button" name="reset" value="重置" onclick="reset1()" class="btn"/></td>
							 <!--<td class="STYLE4">
								 <security:authorize access="hasAnyRole('AUTH_EXP_0056')">
									 <input  type="button" onclick="excelExport()" value="导出" class="btn"/>
								 </security:authorize>
							 </td>-->
					</tr>
 					<tr>
						<td class="STYLE4">&nbsp;&nbsp;类名： &nbsp;&nbsp;
							<input type="text"  name="className" id="className"  class="btn_tb"/>
						</td>
						<td class="STYLE4">&nbsp;&nbsp;方法名： &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="text"  name="methodName" id="methodName"  class="btn_tb"/>
						</td>
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
				  <td style="width: 5%"><div align="center"><span class="STYLE1">日志日期 </span></div></td>
				  <td style="width: 5%"><div align="center"><span class="STYLE1">用户名</span></div></td>
				  <td style="width: 5%"><div align="center"><span class="STYLE1">系统用户ID</span></div></td>
				  <td style="width: 4%"><div align="center"><span class="STYLE1">日志类型</span></div></td>
				  <td style="width: 5%" ><div align="center"><span class="STYLE1">日志概要</span></div></td>
				  <td style="width: 5%"><div align="center"><span class="STYLE1">IP地址</span></div></td>
				  <td style="width: 5%"><div align="center"><span class="STYLE1">端口名称</span></div></td>
			  <td style="width: 5%"><div align="center"><span class="STYLE1">类名</span></div></td>
			  <td style="width: 5%"><div align="center"><span class="STYLE1">方法名</span></div></td>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
		  <tr>
			  <td style="width:  5%"><div align="center"><span class="STYLE1"> <fmt:formatDate value="${row.logDate}" pattern="yyyy-MM-dd  HH:mm:ss" /></span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.userName}</span></div></td>
			  <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.sysUserId}</span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.logType}</span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.logSummary}</span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.ipAddress}</span></div></td>
			  <td style="width:  5%"><div align="center" ><span  class="STYLE1">${row.hostName}</span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.className}</span></div></td>
			  <td style="width:  5%"><div align="center"><span class="STYLE1">${row.methodName}</span></div></td>
		  </tr>
		  </c:forEach>
		  </c:when>
          <c:otherwise>
			  <tr>
				<td colspan="21"  align="center">
				 	<div align="center"><span class="STYLE1">没有日志信息</span></div>
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
