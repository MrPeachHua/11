<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>临停订单信息</title>

		<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<link href="<%=basePath%>css/file.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
			

	</head>

	<body>
		<form action=" <%=basePath%>servlet/UserUpdateServlet" method="get" name="form2"  >
			<table class=editTable cellSpacing=0 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						临停订单详细信息
						<input type="hidden" name="id" value="${orderTemporary.id }" readonly="readonly">
					</td>
					
				</tr>

				<tr>
					<td ><div >车场ID：</div></td>
					<td colspan="3"  >${orderTemporary.parkingId}</td>
					<td ><div >车场名称：</div></td>
					<td colspan="3"  >${parking.parkingName}</td>
				</tr>

				<tr>
					<td ><div >车牌号：</div></td>
					<td colspan="3"  >${orderTemporary.carNumber}</td>
					<td ><div >蓝卡云车场ID：</div></td>
					<td colspan="3"  >${orderTemporary.blueParkingId}</td>
				</tr>

				<tr>
					<td ><div >蓝卡云车场名称：</div></td>
					<td colspan="3"  > ${orderTemporary.blueParkingName}</td>
					<td ><div >蓝卡云订单号：</div></td>
					<td colspan="3"  >${orderTemporary.blueOrderId}</td>
				</tr>

				<tr>
					<td ><div >入场时间：</div></td>
					<td colspan="3"  ><fmt:formatDate  value="${orderTemporary.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td ><div >出场时间：</div></td>
					<td colspan="3"  ><fmt:formatDate  value="${orderTemporary.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	
				<tr>
					<td ><div >用户类型：</div></td>
					<td colspan="3"  >${orderTemporary.carType}</td>
					<td ><div > 订单主表ID：</div></td>
					<td colspan="3"  >${orderTemporary.orderId}</td>
				</tr>
				<tr class="bottom_btn">
			<td colspan="12">
				<div>
					<input class="btn_blue" type="button" name="back" onclick="history.back()" value="返回">
				</div>
			</td>
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
