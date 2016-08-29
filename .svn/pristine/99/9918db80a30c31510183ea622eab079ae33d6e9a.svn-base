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

		<title>优惠券详细信息</title>

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
		<form action="">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						优惠券详情
						<input type="hidden" name="id" value="${coupon.couponId }" readonly="readonly">
					</td>
					<td colSpan=9><input type="button" name="button"
					onClick="history.back() " value="返回">
				</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">优惠券ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.couponId }</td>
					<td bgcolor="#FFFDF0"><div align="center">优惠券种类：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">
						<c:if test="${coupon.couponKind == '0' }">
							通用券
						</c:if>
						<c:if test="${coupon.couponKind == '1' }">
							停车券
						</c:if>
						<c:if test="${coupon.couponKind == '2' }">
							月租产权券
						</c:if>
						<c:if test="${coupon.couponKind == '3' }">
							代泊券
						</c:if>
						<c:if test="${coupon.couponKind == '4' }">
							车管家券
						</c:if>
						<c:if test="${coupon.couponKind == '5' }">
							洗车券
						</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">券（活动）名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="center">
						${coupon.vouchersName}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">发放理由：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">
						<c:if test="${coupon.channel eq 1 }">
							注册
						</c:if>
						<c:if test="${coupon.channel eq 2 }">
							交易
						</c:if>
						<c:if test="${coupon.channel eq 3 }">
							活动
						</c:if>
						<c:if test="${coupon.channel eq 4 }">
							分享
						</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">优惠券类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">
						<c:if test="${coupon.couponType eq 1}">
							定额
						</c:if>
						<c:if test="${coupon.couponType eq 2}">
							折扣
						</c:if>
					 </td>
					<td bgcolor="#FFFDF0"><div align="center">
						<c:if test="${coupon.couponType eq 1}">
							面值（元）：
						</c:if>
						<c:if test="${coupon.couponType eq 2}">
							面值（折）：
						</c:if>
					</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">
						<c:if test="${coupon.couponType eq 1}">
							${coupon.parAmount}
						</c:if>
						<c:if test="${coupon.couponType eq 2}">
							${coupon.parDiscount}
						</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">互斥规则：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.exclusionRule}</td>
					<td bgcolor="#FFFDF0"><div align="center">说明：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.info }</td>
				</tr>
				<c:if test="${!empty coupon.receiveBegin}">
					<tr>
						<td bgcolor="#FFFDF0"><div align="center">领取开始时间：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
							${coupon.receiveBegin}
						</td>
						<td bgcolor="#FFFDF0"><div align="center">领取结束时间：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
							${coupon.receiveEnd}
						</td>
					</tr>
				</c:if>
				<c:if test="${!empty coupon.effectiveBegin}">
					<tr>
						<td bgcolor="#FFFDF0"><div align="center">有效期开始时间：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
							${coupon.effectiveBegin}
						</td>
						<td bgcolor="#FFFDF0"><div align="center">有效期结束时间：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
						${coupon.effectiveEnd}
						</td>
					</tr>
				</c:if>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">优惠券发放人：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
							${coupon.creator}
						</td>
						<td bgcolor="#FFFDF0"><div align="center">优惠券发放时间：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">
							${coupon.createTime}
						</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">限用最低消费（元）：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.minconsumption}</td>
					<td bgcolor="#FFFDF0"><div align="center">优惠券状态：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">
						<c:if test="${coupon.couponStatus eq 200102}">
							已过期已领取
						</c:if>
						<c:if test="${coupon.couponStatus eq 200101}">
							已过期未领取
						</c:if>
						<c:if test="${coupon.couponStatus eq 100202}">
							未过期已领取已使用
						</c:if>
						<c:if test="${coupon.couponStatus eq 100201}">
							未过期已领取未使用
						</c:if>
						<c:if test="${coupon.couponStatus eq 100101}">
							未过期未领取
						</c:if>
					</td>
					<%-- <c:if test="${!empty coupon.receiveBegin}">
						<td bgcolor="#FFFDF0"><div align="center">有效期（天）：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.effectivetime }</td>
					</c:if> --%>
				<tr>
				<c:if test="${!empty coupon.receiveBegin}">
						<tr>
							<td bgcolor="#FFFDF0"><div align="center">有效期（天）：</div></td>
						<td colspan="3" bgcolor="#FFFFFF" align="center">${coupon.effectivetime }</td>
						</tr>
					</c:if>

					<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>

					<td colspan="3"  bgcolor="#FFFFFF"  align="center">
						<div style="overflow:hidden;text-overflow:ellipsis;-o-text-overflow:ellipsis;white-space:nowrap;width:200px;">
							${parkingName }
						</div>
					</td>

				</tr>
			</table>
		</form>
	</body>
</html>
