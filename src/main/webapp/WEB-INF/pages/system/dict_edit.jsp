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
		<title>修改字典信息</title>
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
		<form action="<%=basePath%>system/dictupdate.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改字典信息
						<input type="hidden" name="id" value="${dictionary.id }" readonly="readonly">
						<input type="hidden" name="isUsed" value="${dictionary.isUsed }" readonly="readonly">
						<input type="hidden" name="createDate" value="${dictionary.createDate }" readonly="readonly">
						<input type="hidden" name="createor" value="${dictionary.createor }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">字典代码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px" name="dictCode" disabled="disabled" readonly="readonly"
							value="${dictionary.dictCode}">
						&nbsp;
						<input type="hidden" style="width: 190px" name="dictCode1" 
							value="${dictionary.dictCode}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">字典名称：</div>
					</td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="dictName"value="${dictionary.dictName}"> <!-- maxlength="2"  valid="required|isNumber" -->  
							<!-- errmsg="员工年龄不能为空!|请输入正确的年龄!" --> 
						&nbsp;
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">字典值：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px">
						<input type="text" style="width: 190px" name="dictValue"
							value="${dictionary.dictValue}">
						&nbsp;
					</td>
					<td bgcolor="#FFFDF0"><div align="center">类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="memo"
							value="${dictionary.memo}">
						&nbsp;
					</td>
				</tr>
				<%-- <tr>
					<td bgcolor="#FFFDF0"><div align="center">是否有效：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="isUsed"
							value="${dictionary.isUsed}">
						&nbsp;
					</td>
				</tr> --%>
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					<td width="17%"><input type="submit" name="submit"  value="提交"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
