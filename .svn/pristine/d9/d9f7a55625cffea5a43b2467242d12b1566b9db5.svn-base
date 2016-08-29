<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	</head>
	<body>
		<form action="<%=basePath%>products/carlife/carRent/update.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改信息
						<input type="hidden" name="id" value="${carRent.id }" readonly="readonly">
						<input type="hidden" name="isUsed" value="${carRent.isUsed }" readonly="readonly">
						<input type="hidden" name="createDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${carRent.createDate}" />" readonly="readonly">
						<input type="hidden" name="createor" value="${carRent.createor }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px" name="name" value="${carRent.name}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">价格：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="price" value="${carRent.price/100.00}">元
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">租聘类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="type">
							<option <c:if test="${carRent.type eq 1}">selected="selected"</c:if> value="1">常规租车</option>
							<option <c:if test="${carRent.type eq 2}">selected="selected"</c:if> value="2">按小时租车</option>
						</select>
						<%--<input type="text" style="width: 190px" name="type" value="${carRent.type}">--%>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">跳转链接：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="jumpUrl" value="${carRent.jumpUrl}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">图片：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<share:imageUpload imageLabelName="图片" imagePathId="imagePath" savePath="product/"   imagePath="${carRent.imagePath}"/>
						<input type="hidden" valid="required"  errmsg="图片不能为空!" value="${carRent.imagePath}" id="imagePath" name="imagePath"  />
					</td>
					<td bgcolor="#FFFDF0"><div align="center"></div></td>
					<td colspan="3" bgcolor="#FFFFFF"></td>
				</tr>
			</table>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					<td width="17%"><input type="submit" name="submit"  value="提交"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
