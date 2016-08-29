<%@page import="com.boxiang.share.system.po.Dictionary"%>
<%@page import="com.boxiang.share.product.coupon.po.Coupon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
function reset1(){
	$("#registerBegin").val(null);
	$("#registerEnd").val(null);
	$("#loginBegin").val(null);
	$("#loginEnd").val(null);
}
</script>
</head>
<body>
<body>
<div style="height: 100px;">
  	<form  action="<%=basePath%>reports/channel/view.html" method="post">
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
					<td><input type="hidden" name="channel" id="channel" value="${page.params["channel"] }"> </td>
			        <td class="STYLE4" align="center"><input  type="submit" value="查询" style="width:90px"/> &nbsp;&nbsp;&nbsp;
			        <td class="STYLE4" align="center"><input type="button" name="reset" onclick="reset1()"   value="重置" style="width:90px" ></td>
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
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 10%"><div align="center"><span class="STYLE1">渠道名称</span></div></td><td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">注册用户名</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">用户昵称</span></div></td><td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">注册时间 </span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">登录时间</span></div></td>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr>
            <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  10%"><div align="center">  <span class="STYLE1">${row.channel}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"> <span class="STYLE1">${row.customerMobile}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.customerNickname}</span></div></td><td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.createdAt}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1"> <fmt:formatDate  value="${row.lastLoginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></span></div></td>
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
