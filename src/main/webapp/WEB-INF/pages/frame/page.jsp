<%@page language="java" contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
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
	function gopage(msg)
	{
		var currentPage = document.all.currentPage.value;
		if(msg=="refresh"){
			// 
		}else if(msg=="first"){
			currentPage = 1;
		}else if(msg=="last"){
			currentPage = '${page.totalPage }';
		}else if(msg=="previous"){
			currentPage = parseInt(currentPage) - 1;
			if(currentPage<=0){
				alert("不能在往前了:)");return;
			}
		}else if(msg=="go"){
			currentPage = document.all.textfield.value;
			currentPage = parseInt(currentPage)>parseInt('${page.totalPage }')?'${page.totalPage }':currentPage;
		}else {//默认下一页
			currentPage = parseInt(currentPage) + 1;
			if(currentPage>'${page.totalPage }'){
				alert("不能在往后了:)");return;
			}
		}
		document.all.currentPage.value = currentPage;
		document.all.pageQuery.value = "1";
		document.forms[0].submit();
	}
</script>
<input type="hidden" id="pageQuery" name="pageQuery" value="0">
<%-- <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }"> --%>
<input type="hidden" id="currentPage" name="currentPage" value="${page.currentPage }">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
    <%--    <td width="12" height="35">&lt;%&ndash;<img src="<%=imagePath%>tab_18.gif" width="12" height="35" />&ndash;%&gt;</td>--%>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="STYLE4">
            	&nbsp;&nbsp;共有 ${page.totalCount } 条记录，当前第 ${page.currentPage }/${page.totalPage } 页，分页
	        	<select id="pageSize" name = "pageSize" style="width: 50px" onchange="gopage('refresh')">
					<option <c:if test="${page.pageSize eq 10}">selected="selected"</c:if> value="10">10</option>
					<option <c:if test="${page.pageSize eq 30}">selected="selected"</c:if> value="30">30</option>
					<option <c:if test="${page.pageSize eq 50}">selected="selected"</c:if> value="50">50</option>
					<option <c:if test="${page.pageSize eq 100}">selected="selected"</c:if> value="100">100</option>
	        	</select>
            </td>
            <td>
              <table border="0" align="right" cellpadding="0" cellspacing="0">
                <tr>
                  <td width="40" class="STYLE2"><img src="<%=imagePath%>first.gif" width="37" height="15" onclick="gopage('first')"/></td>
                  <td width="45"><img src="<%=imagePath%>back.gif"  width="43" height="15" onclick="gopage('previous')"/></td>
                  <td width="45"><img src="<%=imagePath%>next.gif"  width="43" height="15" onclick="gopage('next')"/></td>
                  <td width="40"><img src="<%=imagePath%>last.gif"  width="37" height="15" onclick="gopage('last')"/></td>
                  <td width="100"><div align="center"><span class="STYLE1">转到第
                    <input name="textfield" type="text" size="8" style="height:17px; width:30px; border:1px solid #999999;" /> 
                    	页 </span></div>
                  </td>
                  <td width="40"><img src="<%=imagePath%>go.gif" width="37" height="15" onclick="gopage('go')"/></td>
                </tr>
              </table>
            </td>
          </tr>
        </table>
        </td>
        <%--<td width="16"><img src="<%=imagePath%>tab_20.gif" width="16" height="35" /></td>--%>
      </tr>
    </table>