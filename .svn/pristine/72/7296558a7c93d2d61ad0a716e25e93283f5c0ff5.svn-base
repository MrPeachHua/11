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

		<title>洗车订单详情</title>

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
						月租订单详细信息
						<input type="hidden" name="id" value="${orderMonthly.id }" readonly="readonly">
					</td>
					<td colSpan=7><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车场ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderMonthly.parkingId}</td>
					<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${parking.parkingName}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center"> 车牌号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="center">${orderMonthly.carNumber}</td>
					<td bgcolor="#FFFDF0"><div align="center">单价：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderMonthly.price/100.00}元</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">开始时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"><fmt:formatDate  value="${orderMonthly.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/> </td>
					<td bgcolor="#FFFDF0"><div align="center">结束时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"><fmt:formatDate  value="${orderMonthly.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">缴费月数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderMonthly.monthNum}</td>
					<td bgcolor="#FFFDF0"><div align="center">客户名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderMonthly.customer}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">订单主表ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderMonthly.orderId}</td>
					<td bgcolor="#FFFDF0"><div align="center"></div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"></td>
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
