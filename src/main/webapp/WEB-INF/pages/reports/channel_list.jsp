<%@page import="com.boxiang.share.system.po.Dictionary"%>
<%@page import="com.boxiang.share.product.coupon.po.Coupon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>渠道统计</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	<%
	 	List<Dictionary> dictList = (List<Dictionary>)request.getAttribute("dictList");
	 %>
	<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

	a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
}

</style>
<script type="text/javascript">

$(document).ready(function(){ 
	<%
		for(int i=0;i<dictList.size();i++){%>
		$("#channel").append("<option value='<%=dictList.get(i).getDictValue()%>'><%=dictList.get(i).getDictName()%></option>");
		<%}
	%>
	if($("#select1").val()!=null){
		$("#channel").val($("#select1").val());
	}
});
function pp(data){
	//var channel = $("#channel").val();
	//alert(data);onclick="pp('${row.channel}')"
	var loginBegin = $("#loginBegin").val();
	var loginEnd = $("#loginEnd").val();
	var registerBegin = $("#registerBegin").val();
	var registerEnd = $("#registerEnd").val();
	 $.ajax({
         cache: false,
         type: "POST",
         url:'reports/channel/view.html',
         data:{"channel":data,"loginBegin":loginBegin,"loginEnd":loginEnd,"registerBegin":registerBegin,"registerEnd":registerEnd},
        dataType: "json",   
       success: function (jsonStr) {
      
         }
     });
	
}
/* 导出excel表 */
function excelExport1(){
	var channel = $("#select1").val();
	var loginBegin = $("#loginBegin").val();
	var loginEnd = $("#loginEnd").val();
	var registerBegin = $("#registerBegin").val();
	var registerEnd = $("#registerEnd").val();
	window.location="<%=basePath%>reports/channel/excel_export.html?channel="+channel+"&loginBegin="+loginBegin+"&loginEnd="+loginEnd+"&registerBegin="+registerBegin+"&registerEnd="+registerEnd; 
}
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

</script>
</head>
<body>
<body>
<div style="height: 100px;">
  	<form  action="<%=basePath%>reports/channel/list.html" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
    	<td height="30" background="<%=imagePath%>tab_0501.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="60" /></td>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0" height="60">
 					<tr>
			        
			        <td class="STYLE4">&nbsp;&nbsp;注册开始时间：
			        <input id="registerBegin" 
			        name="registerBegin"  
			        style=" width: 145px" readonly="readonly"  value='${page.params["registerBegin"] }'
			        onclick="WdatePicker({el:registerBegin,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'registerEnd\')}'})">
					</td>
					<td class="STYLE4">&nbsp;&nbsp;注册结束时间：
			        	<input id="registerEnd" 
			        name="registerEnd"  
			        style=" width: 145px" readonly="readonly"  value='${page.params["registerEnd"] }'
			        onclick="WdatePicker({el:registerEnd,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'registerBegin\')}'})">
					</td>
					 <td class="STYLE4" align="center">&nbsp;&nbsp;渠道：
			        	<select id="channel" name = "channel" style="width: 130px">
			        		<option value=""></option>
			        	</select>
			        	<input type="hidden" id="select1" value="${page.params['channel'] }"/>
			        </td> 
			        <td></td>        
			      </tr>
			      <tr>
			      	
			      	<td class="STYLE4">&nbsp;&nbsp;登录开始时间：
			        	<input id="loginBegin" 
			        name="loginBegin"  
			        style=" width: 145px" readonly="readonly"  value='${page.params["loginBegin"] }' 
			        onclick="WdatePicker({el:loginBegin,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'loginEnd\')}'})">
					</td>
					<td class="STYLE4">&nbsp;&nbsp;登录结束时间：
			        	<input id="loginEnd" 
			        name="loginEnd"  
			        style=" width: 145px" readonly="readonly"  value='${page.params["loginEnd"] }'
			        onclick="WdatePicker({el:loginEnd,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'loginBegin\')}'})">
					</td>
					<td></td>
			        <td class="STYLE4" align="center"><input  type="submit" value="查询" style="width:90px"/> &nbsp;&nbsp;&nbsp;
			        <security:authorize access="hasAnyRole('AUTH_EXP_0074')">
			        <input  type="button" onclick="excelExport1()" value="导出Excel表" style="width:90px"/></security:authorize></td>  
			      </tr>
			    </table>
			</td>
	        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="60" /></td>
	      </tr>
    	</table>
    	</td>
  	  </tr>
<div/>
  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 30%"><div align="center"><span class="STYLE1">渠道</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 30%"><div align="center"><span class="STYLE1">登录数</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 30%"><div align="center"><span class="STYLE1">注册数 </span></div></td>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr>
            <td height="20" bgcolor="#FFFFFF" style="width:  30%"><div align="center"><a href="<%=basePath%>reports/channel/view.html?channel=${row.channel}"><span class="STYLE1" >${row.channel}</span></a></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  30%"><div align="center"><span class="STYLE1">${row.loginNum}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width:  30%"><div align="center"><span class="STYLE1">${row.registerNum}</span></div></td>
          </tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise>
			  <tr>
				<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
				 	<div align="center"><span class="STYLE1">没有渠道信息</span></div>
				</td>
			  </tr>
	      </c:otherwise>	
		  </c:choose>
          
          </table>
        </td>
        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
      </tr>
    </table>
    </td>
  </tr>
  <tr>
    <td height="35" background="<%=imagePath%>tab_19.gif">
	<jsp:include page="/frame/page.html" />
    </td>
  </tr>

    </table>  
    </form>
</body>
</html>
