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
		<title>修改版本控制信息</title>
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
		<form action="<%=basePath%>system/appversion/update.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改版本控制信息
						<input type="hidden" name="id" value="${appVersion.id }" readonly="readonly">
						<input type="hidden" name="isUsed" value="${appVersion.isUsed }" readonly="readonly">
						<input type="hidden" name="createDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${appVersion.createDate}" />" readonly="readonly">
						<input type="hidden" name="createor" value="${appVersion.createor }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">平台：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="platformCode">
							<c:forEach items="${requestScope.platformCodeList}" var="item">
								<option <c:if test="${appVersion.platformCode eq item.dictValue}">selected="selected"</c:if> value="${item.dictValue}">${item.dictName}</option>
							</c:forEach>
						</select>
						<%--<input type="text" style="width: 190px" name="platformCode" value="${appVersion.platformCode}">--%>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">版本号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="versionCode"value="${appVersion.versionCode}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">更新地址：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input type="text"  style="width: 577px" name="updateUrl"value="${appVersion.updateUrl}">
					</td>

				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">更新提示内容：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						<textarea type="text"  style="width: 577px;height: 100px" name="notice"  maxlength="256" >${appVersion.notice}</textarea>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">是否强制更新：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="isNeeded">
							<option <c:if test="${appVersion.isNeeded eq 1}">selected="selected"</c:if> value="1">是</option>
							<option <c:if test="${appVersion.isNeeded eq 0}">selected="selected"</c:if> value="0">否</option>
						</select>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">渠道：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<%--<select name="versionChannel">--%>
						<%--<c:forEach items="${requestScope.versionChannelList}" var="item">--%>
						<%--<option <c:if test="${appVersion.versionChannel eq item.dictValue}">selected="selected"</c:if> value="${item.dictValue}">${item.dictName}</option>--%>
						<%--</c:forEach>--%>
						<%--</select>--%>
						<input type="text" style="width: 190px" name="versionChannel" value="${appVersion.versionChannel}">
					</td>
				</tr>
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
