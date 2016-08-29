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
    
    <title>字典管理</title>
    
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
function add(){  
	window.location="<%=basePath%>system/dict/add.html";
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

  	<form  action="<%=basePath%>system/dict/list.html" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	  <tr>
    	<td height="30" background="<%=imagePath%>tab_05.gif">
    	<table width="100%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
	        <td>
	        	<table width="100%" border="0" cellspacing="0" cellpadding="0">
			      <tr>
			        <td class="STYLE4" align="center">&nbsp;&nbsp;请输入字典代码：
			        	<input type="text" name="p_queryCode" value = "${page.params["dictCode"] }" style="width: 170px"/>
			        </td>
			        <td class="STYLE4">&nbsp;&nbsp;请输入字典名称：
			        	<input type="text" name="p_queryValue" value = "${page.params["dictName"] }" style="width: 170px"/>
					</td>
					<td class="STYLE4">&nbsp;&nbsp;请输入字典类型：
			        	<input type="text" name="p_queryRex" value = "${page.params["memo"] }" style="width: 170px"/>
					</td>
			        <td class="STYLE4">&nbsp;&nbsp;<input  type="submit" value="查询" style="width:50px"/></td>
			        <td class="STYLE4">&nbsp;&nbsp;<security:authorize access="hasAnyRole('AUTH_ADD_0088')">
			        <input  type="button" onclick="add()" value="添加" style="width:50px"/></security:authorize></td>            
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
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">字典代码</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">字典名称</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:3%"><div align="center"><span class="STYLE1">字典值</span></div></td>
	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:12%"><div align="center"><span class="STYLE1">类型</span></div></td>
            <%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">是否可用</span></div></td>  --%>
<%-- 	    	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">创建者</span></div></td> --%>
	    	<%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">修改者ID</span></div></td> --%>
<%--             <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 12%"><div align="center"><span class="STYLE1">创建时间</span></div></td>
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 12%"><div align="center"><span class="STYLE1">修改时间</span></div></td>    --%>   
            <security:authorize access="hasAnyRole('AUTH_DEL_0089','AUTH_EDIT_0090')">
            <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 4%"><div align="center">基本操作</div></td>
            </security:authorize>
          </tr>
		  <c:choose>		
		  <c:when test="${fn:length(page.resultList)>0}">
		  <c:forEach items="${page.resultList}" var="row" varStatus="status">
          <tr>
           	<td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${status.count}</span></div></td>
            <%-- <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">
            	<a href="<%=basePath %>users/${row.id}/userview.html">${row.id}</a>
            		</span></div>
            </td> --%>
           <%--  <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.id}</span></div></td> --%>
            <td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.dictCode}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.dictName}</span></div></td>
	  	  	<td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1">${row.dictValue}</span></div></td>
	    	<td height="20" bgcolor="#FFFFFF" style="width:  12%"><div align="center"><span class="STYLE1">${row.memo}</span></div></td>
	    	
           <%--  <td height="20" bgcolor="#FFFFFF" style="width:  5%">
            	<c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if> height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 14%">
            	<div align="center">基本操作</div>
            	<c:if test="${row.isUsed ==1}">
            		<div align="center"><span class="STYLE1">是</span></div>
            	</c:if>
            	<c:if test="${row.isUsed <1}">
            		<div align="center"><span class="STYLE1">否</span></div>
            	</c:if>
            
            </td> --%>
            
<%--             <td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.createor}</span></div></td> --%>
	    	<%-- <td height="20" bgcolor="#FFFFFF" style="width: 15%"><div align="center"><span class="STYLE1">${row.modified}</span></div></td> --%>
<%-- 	    	<td height="20" bgcolor="#FFFFFF" style="width: 12%"><div align="center"><span class="STYLE1">${row.createDate}</span></div></td>
   	    	<td height="20" bgcolor="#FFFFFF" style="width: 12%"><div align="center"><span class="STYLE1">${row.modifyDate}</span></div></td> --%>
           	<security:authorize access="hasAnyRole('AUTH_DEL_0089','AUTH_EDIT_0090')">
           	<td height="20" bgcolor="#FFFFFF" style="width: 4%"><div align="center"><span class="STYLE4">
           		<security:authorize access="hasAnyRole('AUTH_EDIT_0090')">
            	<img src="<%=imagePath%>edt.gif" width="16" height="16" />
            	<a href="<%=basePath %>system/dict/${row.id}/edit.html">编辑</a>
            	</security:authorize>
            	<security:authorize access="hasAnyRole('AUTH_DEL_0089')">
            	<img src="<%=imagePath%>del.gif" width="16" height="16" />
            	<a href="<%=basePath %>system/dict/${row.id}/del.html">删除</a>
            	</security:authorize></span></div>
            </td> 
            </security:authorize>
          </tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise>
			  <tr>
				<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
				 	<div align="center"><span class="STYLE1">没有字典相关信息</span></div>
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
