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

	<title>添加版本控制</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<%--<script type="text/javascript">--%>
		<%--function query(){--%>
			<%--window.location="<%=basePath%>users/rolelist.html";--%>
		<%--}--%>

	<%--</script>--%>

</head>

<body>
<form action="<%=basePath%>system/appversion/save.html" method="post"name="form2" onsubmit="return checkForm('form2');">
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
		<tr class=editHeaderTr>
			<td class=editHeaderTd colSpan=7>  请输入新版本控制的详细信息</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">平台：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="platformCode">
					<c:forEach items="${requestScope.platformCodeList}" var="item">
						<option value="${item.dictValue}">${item.dictName}</option>
					</c:forEach>
				</select>
				<%--<input id = "platformCode" type="text"  maxlength="20"  check_str="平台" style="width: 138px" required name="platformCode" >--%>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">版本号：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id ="versionCode" type="text"  maxlength="20"  check_str="版本号" style="width: 138px" required name="versionCode" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">更新地址：</div></td>
			<td colspan="6" bgcolor="#FFFFFF"><input id = "updateUrl" type="text"   check_str="更新地址"  style="width: 608px"  name="updateUrl" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">更新提示内容：</div></td>

			<td colspan="6" bgcolor="#FFFFFF">
				<textarea id = "notice" type="text"   check_str="更新提示内容" style="width: 608px;height: 100px" name="notice" ></textarea>
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否强制更新：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="isNeeded" >
					<option value="0">否</option>
					<option value="1">是</option>
				</select>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">渠道：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<%--<select name="versionChannel">--%>
				<%--<c:forEach items="${requestScope.versionChannelList}" var="item">--%>
				<%--<option value="${item.dictValue}">${item.dictName}</option>--%>
				<%--</c:forEach>--%>
				<%--</select>--%>
				<input id = "versionChannel" type="text" maxlength="20" check_str="渠道" style="width: 138px" required name="versionChannel" >
				&nbsp;</td>
		</tr>
	</table>
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
		<tr bgcolor="#ECF3FD">
			<td width="10%">&nbsp;</td>
			<td width="12%"><input type="submit" name="Submit" value="提交"></td>
			<td width="12%"><input type="reset" name="button"    value="重置"></td>
			<td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
		</tr>
	</table>

</form>
</body>
</html>
