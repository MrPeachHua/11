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

		<title>代泊订单信息</title>

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
						代泊订单详细信息
						<input type="hidden" name="id" value="${orderPark.id }" readonly="readonly">
					</td>
					<td colSpan=7><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center" style="width:100px">订单主表ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.orderId}</td>
					<td bgcolor="#FFFDF0"><div align="center" style="width: 100px">车场名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${parking.parkingName}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">接车代泊员ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="center">${orderPark.parkerId}</td>
					<td bgcolor="#FFFDF0"><div align="center">还车代泊员ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.parkerBackId}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">验车照片路径：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"> ${orderPark.validateImagePath}</td>
					<td bgcolor="#FFFDF0"><div align="center">停车照片路径：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.parkingImagePath}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车牌号 ：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.carNumber}</td>
					<td bgcolor="#FFFDF0"><div align="center">订单创建时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"><fmt:formatDate  value="${orderPark.orderBeginDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
	
				<tr>
					<td bgcolor="#FFFDF0"><div align="center"> 预约取车时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"><fmt:formatDate  value="${orderPark.orderEndDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
					<td bgcolor="#FFFDF0"><div align="center">代泊员接车拍完验车照上传完照片开始：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.actualBeginDate}</td>
				</tr>
			
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">用户付完款结束或代泊员订单结束：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.actualEndDate}</td>
					<td bgcolor="#FFFDF0"><div align="center">车场ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderPark.parkingId}</td>
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
