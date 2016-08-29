<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>泊享科技</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- 浏览器上显示title 图标 icon图标 -->
	<link type="image/x-icon" rel="icon" href="images/default/logo2.0_32.ico">
	<!-- 让图标出现在收藏夹中 -->
	<link type="image/x-icon" rel="shortcut icon" href="images/default/logo2.0_32.ico">
	<link type="image/x-icon" rel="bookmark" href="images/default/logo2.0_32.ico">
	<script type="text/javascript">
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
</script>
  </head>
 <frameset id="full" name="full" rows="98,*,8"  border="7" bordercolor="#1873aa" scrolling="No" noresize="noresize"  framespacing="0"  ondblclick="maxmain()">
	<frame src="<%=basePath%>frame/top.html" name="topFrame" scrolling="No"  noresize="noresize" id="topFrame" />
	<frameset id="cen" name="cen"  cols="166,*" >
		<frame src="<%=basePath%>frame/menu.html"   noresize="noresize" />
		<frame src="<%=basePath%>frame/desktop.html"  name="mainFrame"/>
	</frameset>
	<frame src="<%=basePath%>frame/down.html" name="bottomFrame" scrolling="No" noresize="noresize" id="bottomFrame" onclick="shbottom()" />
</frameset>

</html>
