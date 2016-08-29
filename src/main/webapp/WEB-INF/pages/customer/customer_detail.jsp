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
		<title>客户详情</title>
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
					<td class=editHeaderTd colSpan=6>
						客户的详细信息
						<input type="hidden" name="id" value="${customer.customerId }" readonly="readonly">
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center"width="15%">姓名：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center" width="35%">${customer.customerName}</td>
					<td bgcolor="#FFFDF0"><div align="center" width="15%">年龄：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center" width="35%">${customer.customerAge}</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">性别：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" style="height: 21px" align="center">
						<c:if test="${customer.customerSex eq '1' }">
							男
						</c:if>
						<c:if test="${customer.customerSex eq '2' }">
							女
						</c:if>
						<c:if test="${customer.customerSex eq '3' }">
							未知
						</c:if>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">手机：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.customerMobile}</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">身份证：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.customerCardId}</td>
					<td bgcolor="#FFFDF0"><div align="center">邮箱：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.customerEmail}</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">职业：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						<c:choose>
							<c:when test="${customer.customerJob eq '1' }">私营业主</c:when>
							<c:when test="${customer.customerJob eq '2' }">事业单位</c:when>
							<c:when test="${customer.customerJob eq '3'}">企业单位</c:when>
							<c:when test="${customer.customerJob eq '4' }">其他</c:when>
							<c:otherwise>${customer.customerJob}</c:otherwise>
						</c:choose>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">注册时间：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						<c:if
						test="${customer.createdAt != '0000-00-00 00:00:00'}">
							${customer.createdAt}
				</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">地址：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.customerRegion}</td>
					<td bgcolor="#FFFDF0"><div align="center">等级：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						<c:if test="${customer.customerLevel eq '1'}">普通</c:if>
						<c:if test="${customer.customerLevel eq '2'}">白银</c:if>
						<c:if test="${customer.customerLevel eq '3'}">黄金</c:if>
						<c:if test="${customer.customerLevel eq '4'}">白金</c:if>
						<c:if test="${customer.customerLevel eq '5'}">钻石</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">客户ID：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.customerId}</td>
					<td bgcolor="#FFFDF0"><div align="center">最后登录时间：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
					<fmt:formatDate  value="${customer.lastLoginTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">渠道：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.channel}</td>
					<td bgcolor="#FFFDF0"><div align="center">用户身份：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						<c:if test="${customer.identity eq '0'}">
							普通会员
						</c:if>
						<c:if test="${customer.identity eq '1'}">
							VIP会员
						</c:if>
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">微信号：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.wxId}</td>
					<td bgcolor="#FFFDF0"><div align="center">QQ号：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">${customer.qqId}</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">微博：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						${customer.sinaId}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">昵称：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						${customer.customerNickname}
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">头像：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						<img style="width:50px;height:50px;" alt="" src = "<%=basePath%>${customer.customerHead}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">余额：</div></td>
					<td colspan="2" bgcolor="#FFFFFF" align="center">
						${money}
					</td>
				</tr>
			</table>
		</form>

	</body>
</html>
