<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>down</title>
    
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
-->
</style>

  </head>
  
  <body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="6" background="<%=imagePath%>main_59.gif" style="line-height:6px;"><img src="<%=imagePath%>main_59.gif" width="6" height="6" ></td>
    <td background="<%=imagePath%>main_61.gif" style="line-height:6px;">&nbsp;</td>
    <td width="6" background="<%=imagePath%>main_61.gif" style="line-height:6px;"><img src="<%=imagePath%>main_62.gif" width="6" height="6" ></td>
  </tr>
</table>
  </body>
</html>
