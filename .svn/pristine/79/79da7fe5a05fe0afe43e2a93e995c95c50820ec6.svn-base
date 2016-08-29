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

		<title>意见反馈信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
			

	</head>

	<body>
		<form action=" <%=basePath%>servlet/UserUpdateServlet" method="get" name="form2"  >
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						意见反馈详细信息
						<input type="hidden" name="id" value="${SystemReviews.id }" readonly="readonly">
					</td>
					<td colSpan=7><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center" style="width: 100px">创建人名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center" style="height: 20px" width="300px">${customer.customerNickname}</td>
					<td bgcolor="#FFFDF0"><div align="center" style="width: 100px">创建人手机号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"  style="height: 20px"  width="300px">${customer.customerMobile}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" ><div align="center" style="width: 100px">内容：</div></td>
					<td colspan="7" bgcolor="#FFFFFF" style="height: 120px"  align="center">${systemReviews.reviewsInfo}</td>
					</tr>
					<tr>
					<td bgcolor="#FFFDF0"><div align="center" style="width: 100px">创建时间：</div></td>
					<td colspan="7" bgcolor="#FFFFFF" align="center">${systemReviews.createTime}</td>
				</tr>
			</table>
			<!-- <table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table> -->

		</form>

	</body>
</html>
