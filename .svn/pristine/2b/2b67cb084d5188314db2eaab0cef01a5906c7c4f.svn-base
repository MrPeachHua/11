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

	<title>产权订单详情</title>

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
				订单详细信息
				<input type="hidden" name="id" value="${orderMonthly.id }" readonly="readonly">
			</td>
		</tr>
		<tr>
			<td><div >车场ID：</div></td>
			<td colspan="3" >${orderMonthly.parkingId}</td>
			<td><div >车场名称：</div></td>
			<td colspan="3" >${parking.parkingName}</td>
		</tr>
		<tr>
			<td><div > 车牌号：</div></td>
			<td colspan="3" >${orderMonthly.carNumber}</td>
			<td><div >单价：</div></td>
			<td colspan="3" >${orderMonthly.price}元</td>
		</tr>

		<tr>
			<td><div >开始时间：</div></td>
			<td colspan="3" ><fmt:formatDate  value="${orderMonthly.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
			<td><div >结束时间：</div></td>
			<td colspan="3" ><fmt:formatDate  value="${orderMonthly.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		</tr>

		<tr>
			<td><div >缴费月数：</div></td>
			<td colspan="3" >${orderMonthly.monthNum}</td>
			<td><div >客户名称：</div></td>
			<td colspan="3" >${orderMonthly.customer}</td>

		<tr>
		<td><div >寄送地址：</div></td>
		<td colspan="3" >${invoice.sendAddress}</td>
			<td><div >订单主表ID：</div></td>
			<td colspan="3" >${orderMonthly.orderId}</td>
		</tr>
		<tr>
			<td><div >发票抬头：</div></td>
			<td colspan="3" >${invoice.invoiceName}</td>
			<td><div >寄送方式：</div></td>
			<td colspan="3" ><c:if test="${invoice.sendType eq '0'}">自取</c:if><c:if test="${invoice.sendType eq '1'}">寄送上门</c:if></td>
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
