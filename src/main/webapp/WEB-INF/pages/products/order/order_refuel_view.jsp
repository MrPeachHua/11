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

		<title>加油卡订单信息</title>

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
						加油卡订单详细信息
						<input type="hidden" name="id" value="${orderRefuel.id }" readonly="readonly">
					</td>
					<td colSpan=9><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">订单ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderRefuel.orderId}</td>
					<td bgcolor="#FFFDF0"><div align="center">加油卡ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderRefuel.cardId}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">充值类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="center">
					<c:if test="${orderRefuel.topupType eq 10000 }">中石化50元加油卡</c:if>
					<c:if test="${orderRefuel.topupType eq 10001 }">中石化100元加油卡</c:if>
					<c:if test="${orderRefuel.topupType eq 10003 }">中石化500元加油卡</c:if>
					<c:if test="${orderRefuel.topupType eq 10004 }">中石化100元加油卡</c:if>
					<c:if test="${orderRefuel.topupType eq 10007 }">中石化任意金额充值</c:if>
					<c:if test="${orderRefuel.topupType eq 10008 }">中石油任意金额充值</c:if>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">充值数量：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderRefuel.topupNum}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">充值单价：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"> ${orderRefuel.topupPrice/100.00}元</td>
					<td bgcolor="#FFFDF0"><div align="center">进货价格：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${orderRefuel.orderCash}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">充值状态：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"> 
					<c:if test="${orderRefuel.topupStatus eq 0 }">充值中</c:if>
					<c:if test="${orderRefuel.topupStatus eq 1 }">成功</c:if>
					<c:if test="${orderRefuel.topupStatus eq 9 }">撤销</c:if>
					  </td>
					<td bgcolor="#FFFDF0"><div align="center">充值名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"> ${orderRefuel.topupName} </td>
	
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">商家订单号：</div></td>
					<td colspan="7" bgcolor="#FFFFFF" align="center">${orderRefuel.sporderId}</td>
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
