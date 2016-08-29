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
    
    <title>添加角色</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<link href="<%=basePath%>css/file.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
  </head>
  
  <body>
    <form action="<%=basePath%>users/updatepwd.html" method="post"name="form2" onsubmit="return checkForm('form2');">
<table class=editTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
<tr class=editHeaderTr>
<td class=editHeaderTd colSpan=7>  请输入需要重置的密码
<input type="hidden" name="userId" value="${userInfo.userId }" readonly="readonly">
</td>
</tr>  
  <tr>
    <td  ><div>用户密码：</div></td>
    <td colspan="3" >
    	<input type="password" name="userPw"  maxlength="20" class="selectList"  valid="required"  errmsg="密码不能为空!">
      &nbsp;</td>
    </tr>
  <tr>
 <tr class="bottom_btn">
			<td colspan="12">
				<div>
					<input class="btn_blue" type="submit" name="Submit" value="提交"  >
				</div>
			</td>
		</tr>
</table>
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>

</table>

</form>
  </body>
</html>
