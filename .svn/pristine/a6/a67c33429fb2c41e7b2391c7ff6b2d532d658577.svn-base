<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>添加</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	<%--<script type="text/javascript">--%>
		<%--function query(){--%>
			<%--window.location="<%=basePath%>users/rolelist.html";--%>
		<%--}--%>

	<%--</script>--%>

</head>

<body>
<form action="<%=basePath%>products/carlife/carRent/save.html" method="post"name="form2" onsubmit="return checkForm('form2');">
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
		<tr class=editHeaderTr>
			<td class=editHeaderTd colSpan=7>  请输入信息</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">名称：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id="name" type="text"  maxlength="20" style="width: 138px" name="name" >
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">价格：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id ="price" type="text"  maxlength="20"  style="width: 138px" name="price" value="${param.price}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">租聘类型：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="type">
					<option <c:if test="${param.type eq 1}">selected="selected"</c:if> value="1">常规租车</option>
					<option <c:if test="${param.type eq 2}">selected="selected"</c:if> value="2">按小时租车</option>
				</select>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">跳转链接：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id="jumpUrl" type="text"  maxlength="20" style="width: 138px" name="jumpUrl" value="${param.jumpUrl}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">图片：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<share:imageUpload imageLabelName="图片" imagePathId="imagePath" savePath="product/"   imagePath="${param.imagePath}"/>
				<input type="hidden" valid="required"  errmsg="图片不能为空!" value="${param.imagePath}" id="imagePath" name="imagePath"  />
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center"></div></td>
			<td colspan="3" bgcolor="#FFFFFF">
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
