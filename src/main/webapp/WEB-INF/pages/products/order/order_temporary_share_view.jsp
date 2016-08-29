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

		<title>共享临停订单信息</title>

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
						优惠停车订单详细信息
						<input type="hidden" name="id" value="${orderTemporaryShare.id }" readonly="readonly">
					</td>
					<%--<td colSpan=9><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>--%>
				</tr>

				<tr>
					<td ><div >车场ID：</div></td>
					<td colspan="3"  >${orderTemporaryShare.parkingId}</td>
					<td ><div >车场名称：</div></td>
					<td colspan="3"  >${parking.parkingName}</td>
				</tr>

				<tr>
					<td  style="height: 21px"><div >车牌号：</div></td>
					<td colspan="3"  style="height: 21px" >${orderTemporaryShare.carNumber}</td>
					<td ><div >停车码：</div></td>
					<td colspan="3"  >${orderTemporaryShare.parkingCode}</td>
				</tr>

				<tr>
					<td ><div > 订单主表ID：</div></td>
					<td colspan="3"  >${orderTemporaryShare.orderId}</td>
					<td ><div >预约停车次数：</div></td>
					<td colspan="3"  >${orderTemporaryShare.parkingNum}</td>
				</tr>
				<tr>
					<!-- <td ><div > 预约日期：</div></td>
					<td colspan="3"  >${orderTemporaryShare.appointmentDate}</td> -->
					<td ><div > 创建日期：</div></td>
					<td colspan="3"  ><fmt:formatDate  value="${orderTemporaryShare.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td ><div >凭证状态：</div></td>
					<td colspan="3"  >
						<c:if test="${orderTemporaryShare.voucherStatus eq '0'}">
							未使用
						</c:if>
						<c:if test="${orderTemporaryShare.voucherStatus eq '1'}">
							已使用
						</c:if>
						<c:if test="${orderTemporaryShare.voucherStatus eq '2'}">
							失效
						</c:if>
					</td>
				</tr>
				<tr>
					<td  style="height: 21px"><div >停车时间：</div></td>
					<td colspan="3"  style="height: 21px" >${orderTemporaryShare.packageDay}</td>
					<td  style="height: 21px"><div ></div></td>
					<td colspan="3"  style="height: 21px" ></td>
				</tr>
				<tr class="bottom_btn">
					<td colspan="8">
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
