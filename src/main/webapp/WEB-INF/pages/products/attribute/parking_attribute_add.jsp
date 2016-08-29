<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    
    <title>增加产品属性信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>


  </head>
  
  <body>
<form action="<%=basePath%>product/parking/attri/save.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入产品属性信息</td>
	</tr>  
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">属性代码：</div></td>
		<td colspan="3" bgcolor="#FFFFFF"><input type="text"   maxlength="20"  style=" width: 165px" valid="required"  errmsg="产品属性代码不能为空!" name="attributeCode"></td>
	  	<td bgcolor="#FFFDF0"><div align="center">属性名称：</div></td>
		<td colspan="3" bgcolor="#FFFFFF"><input type="text"   maxlength="40"  style=" width: 165px" valid="required"  errmsg="产品属性名称不能为空!" name="attributeName"></td>
    </tr>
    
	<tr>
		<td bgcolor="#FFFDF0"><div align="center">备注：</div></td>
		<td colspan="5" bgcolor="#FFFFFF"><textarea rows="10" name="memo" style="width:100%; resize:none; "  ></textarea></td>
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

