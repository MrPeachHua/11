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

		<title>临停订单信息</title>

		<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
			body {
				padding: 15px 20px;
			}
			td {
				padding: 10px;
			}
		</style>
	</head>

	<body>
		<form action=" <%=basePath%>servlet/UserUpdateServlet" method="get" name="form2"  >
			<table class='mainView' cellSpacing=0 cellPadding=0 width="100%"
				align=center border='0'>
				<tr class=editHeaderTr>
					<td class=headerTitle colSpan=7>
						临停订单详细信息
						<input type="hidden" name="id" value="${orderTemporary.id }" readonly="readonly">
					</td>
					
				</tr>
				<tr>
					<td align="right"></td>
					<td colspan="3" ></td>
					<td align="right"></td>
					<td colspan="3" ></td>
				</tr>
				<tr>
					<td align="right"><div >车场ID：</div></td>
					<td colspan="3"  >${orderTemporary.parkingId}</td>
					<td align="right"><div >车场名称：</div></td>
					<td colspan="3"  >${parking.parkingName}</td>
				</tr>

				<tr>
					<td align="right"><div >车牌号：</div></td>
					<td colspan="3"  >${orderTemporary.carNumber}</td>
					<td align="right"><div >蓝卡云车场ID：</div></td>
					<td colspan="3"  >${orderTemporary.blueParkingId}</td>
				</tr>

				<tr>
					<td align="right"><div >蓝卡云车场名称：</div></td>
					<td colspan="3"  > ${orderTemporary.blueParkingName}</td>
					<td align="right"><div >蓝卡云订单号：</div></td>
					<td colspan="3"  >${orderTemporary.blueOrderId}</td>
				</tr>

				<tr>
					<td align="right"><div >入场时间：</div></td>
					<td colspan="3"  ><fmt:formatDate  value="${orderTemporary.beginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td align="right"><div >出场时间：</div></td>
					<td colspan="3"  ><fmt:formatDate  value="${orderTemporary.endDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	
				<tr>
					<td align="right"><div >用户类型：</div></td>
					<td colspan="3"  >${orderTemporary.carType}</td>
					<td align="right"><div > 订单主表ID：</div></td>
					<td colspan="3"  >${orderTemporary.orderId}</td>
				</tr>
				<tr>
					<td align="right"></td>
					<td colspan="3" ></td>
					<td align="right"></td>
					<td colspan="3" >
						<div style="padding: 20px">
							<input class="greenBtn" type="button" name="back" onclick="history.back()" value="返&nbsp;&nbsp;回">
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
