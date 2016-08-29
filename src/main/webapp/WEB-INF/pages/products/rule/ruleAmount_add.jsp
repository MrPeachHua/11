<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
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
    <title>增加车管家轮番活动信息</title>
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
  </head>
  <script type="text/javascript">
  </script>
  <body>
  
<form action="<%=basePath%>products/rule/rule_add1.html"  name="form1" onsubmit="return validator(this)" method="post" >
<input type="hidden" name = "ruleId" value="${ruleId }"/>
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>请输入规则信息<span style="color: red;"></span></td>
	</tr>
	   
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">充值金额：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="amount" value="">
		</td>
		<td bgcolor="#FFFDF0"><div align="center">赠送金额：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="giftAmount" value="">
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

