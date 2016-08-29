<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
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
    
    <title>添加部门信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
  </head>
  
  <body>


<form action="<%=basePath%>users/departsave.html" method="post" name="form2" onsubmit="return checkForm('form2');">
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
  <tr class=editHeaderTr>
	<td class=editHeaderTd colSpan=7>  请输入新部门的详细信息</td>
  </tr>  
  <tr>
    <td width="19%" bgcolor="#FFFDF0"><div align="center">部门名称：</div></td>
    <td colspan="3" bgcolor="#FFFFFF">
    	<input type="text"  style=" width: 100%"  maxlength="20"  name="departmentName" check_str="部门名称">
    </td>
    <td bgcolor="#FFFDF0">
      <div align="center">
        所属系统：
      </div>
    </td>
    <td colspan="3" bgcolor="#FFFFFF">
      <select name="module" style=" width: 100%"   class="dictionary"  data-dict-code="module" data-current-value="${shareUser.module}"  data-dict-value="${shareUser.module}" >
        <c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option> </c:if>
      </select>
    </td>
  </tr>
  <tr>
    <td bgcolor="#FFFDF0"><div align="center">部门描述：</div></td>
    <td colspan="5" bgcolor="#FFFFFF">
      <textarea  rows="10" name="departmentDesc"   style="width:100%; resize:none; "></textarea>
      &nbsp;
    </td>
  </tr>
  
</table>
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
  <tr bgcolor="#ECF3FD">
  	<td width="25%"></td>
	<td width="17%"><input type="submit" name="submit"  value="添加"></td>
	<td width="17%"><input type="reset" name="reset"  value="重置"></td>
	<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
	<td width="43%"></td>
  </tr>
</table>

</form>
</body>
</html>
