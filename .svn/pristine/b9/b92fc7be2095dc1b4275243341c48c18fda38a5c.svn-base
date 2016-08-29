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
    
    <title>查询广播信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<style type="text/css">
<!--
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

-->
</style>

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
</script>
<script type="text/javascript">
	function add(){  
			window.location="<%=basePath%>system/msgpub/msgadd.html";
		}

</script>
  </head>
  
  <body>

  	<form  action="<%=basePath%>system/msgpub/list.html" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
    	<td height="30" background="<%=imagePath%>tab_05.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input type="text" name="p_queryValue" value="${page.params["queryValue"] }" style="width: 290px"/></td>
			        <td class="STYLE4">&nbsp;&nbsp;请选择查询方式：
			        	<select name="p_queryType" style="width: 100px">
			        	<c:if test='${page.params["queryType"] eq null }'>
			  				<option  value="1">标题</option>
			 				<option  value="2">消息内容</option>
			 			</c:if>
			 			<c:if test='${page.params["queryType"] eq 1 }'>
			  				<option  value="1" selected="selected">标题</option>
			 				<option  value="2">消息内容</option>
			 			</c:if>
			 			<c:if test='${page.params["queryType"] eq 2 }'>
			  				<option  value="1">标题</option>
			 				<option  value="2" selected="selected">消息内容</option>
			 			</c:if>
			 			
						</select>            
					</td>
			        <td class="STYLE4">&nbsp;&nbsp;<input  type="submit" value="查询" style="width:50px"/></td>    
			        <td class="STYLE4">
											&nbsp;&nbsp;<security:authorize access="hasAnyRole('AUTH_ADD_0092')">
											<input type="button" value="添加" onclick="add()" style="width: 50px" /></security:authorize>
										</td>        
			      </tr>
			    </table>
			</td>
	        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" /></td>
	      </tr>
    	</table>
    	</td>
  	  </tr>

  <tr>
    <td>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td>
          <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
          <tr>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">序号</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">标题</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">消息</span></div></td>
          <!--   <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">消息</span></div></td> -->
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">是否可用</span></div></td>
          <!--  <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">创建人</span></div></td> 
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">修改人</span></div></td>
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">创建日期</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">修改日期</span></div></td> -->
            <security:authorize access="hasAnyRole('AUTH_DEL_0093')">
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 5%"><div align="center">基本操作</div></td>
            </security:authorize>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr>
           <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
           <!--  <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">
            	 ${row.messageId}
            		</span></div>
            </td> -->
            <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center" style="width:350px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;"><span title="${row.title}" class="STYLE1">${row.title}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center" style="width:350px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis; -o-text-overflow:ellipsis;"><span title="${row.message}" class="STYLE1">${row.message}</span></div></td>
	    	<!--  <td height="20" bgcolor="#FFFFFF" style="width:  7%"><div align="center"><span class="STYLE1">${row.messageTwo}</span></div></td>-->
	  	  	<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1"><c:if test="${row.isUsed eq 1}">是</c:if> <c:if test="${row.isUsed eq 0}">否</c:if></span></div></td>
	        <!-- <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.createor}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.modified}</span></div></td>
            <td height="20" bgcolor="#FFFFFF" style="width:  10%"><div align="center"><span class="STYLE1">${row.createDate}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width: 10%"><div align="center"><span class="STYLE1">${row.modifyDate}</span></div></td>-->
	    	<security:authorize access="hasAnyRole('AUTH_DEL_0093')">
            <td height="20" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE4">
            	<img src="<%=imagePath%>del.gif" width="16" height="16" />
            	<a href="<%=basePath %>system/msgpub/${row.messageId}/msgdel.html">删除</a></span></div>
            </td>
            </security:authorize>
          </tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise>
			  <tr>
				<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
				 	<div align="center"><span class="STYLE1">没有广播信息</span></div>
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
