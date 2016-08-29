<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    
    <title>center</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 <LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<style type="text/css">
	#div1{
	
		width:100%;
		height:80%;
	
	}
	#div2{
	
		width:100%;
		height:80%;
	
	}

</style>
<!-- <script type="text/javascript">
function shbottom(){
	if (parent.full.rows.indexOf(",*,0")!=-1){
		parent.full.rows=parent.full.rows.split(",")[0]+",*,8"
		document.getElementById("shbottom").value="隐藏下部";
	} 
	else{
		parent.full.rows=parent.full.rows.split(",")[0]+",*,0"
		document.getElementById("shbottom").value="显示下部";
	}
}
function shleft(){
	if (parent.cen.cols=="0,*"){
		parent.cen.cols="166,*,"
		document.getElementById("shleft").value="隐藏左部";
	}
	else{
		parent.cen.cols="0,*"
		document.getElementById("shleft").value="显示左部";
	}
}
function shtop(){
	if (parent.full.rows.indexOf("0,*,")!=-1){
		parent.full.rows="98,*,"+parent.full.rows.split(",")[2]
		document.getElementById("shtop").value="隐藏上部";
	}
	else{
		parent.full.rows="0,*,"+parent.full.rows.split(",")[2]
		document.getElementById("shtop").value="显示上部";
	}
}
function maxmain(){
	if (parent.full.rows=="0,*,0"){
		parent.full.rows="98,*,8"
		parent.cen.cols="166,*"
		document.getElementById("maxmain").value="隐藏左上下";
		document.getElementById("shtop").value="隐藏上部";
		document.getElementById("shleft").value="隐藏左部";
		document.getElementById("shbottom").value="隐藏下部";
	}
	else{
		parent.full.rows="0,*,0";
		parent.cen.cols="0,*";
		document.getElementById("maxmain").value="显示左上下";
		document.getElementById("shtop").value="显示上部";
		document.getElementById("shleft").value="显示左部";
		document.getElementById("shbottom").value="显示下部";
	}
}
</script> -->
  </head>
  
  <body>
<!-- <input type="button" name="shtop" id="shtop" onclick="shtop();" value="隐藏上部">
<input type="button" name="shleft" id="shleft" onclick="shleft();" value="隐藏左部">
<input type="button" name="shbottom" id="shbottom" onclick="shbottom();" value="隐藏下部">
<input type="button" name="maxmain" id="maxmain" onclick="maxmain();" value="隐藏左上下"> -->
<img width="100%" height="100%" src="<%=imagePath%>background.jpg"/>
  </body>
</html>
