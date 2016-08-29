<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/common/taglib/share.tld" prefix="share"%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>显示广告信息</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>js/FormValid.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60;
	TEXT-DECORATION: underline
}
-->
</style>
</head>
<body>
	<form action="<%=basePath%>products/advertising/adver_edit.html"
		id="form2" method="post">
		<input type="hidden" name = "createDate" value="${advertising.createDate}">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="<%=imagePath%>tab_05.gif"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30"><img
								src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4" align="center">&nbsp;&nbsp;</td>
										<td class="STYLE4">&nbsp;&nbsp;</td>
										<td align="right" class="STYLE4"><input type="submit"
											name="submit" value="提交">&nbsp;&nbsp; <input
											type="button" name="button" onClick="location.href='<%=basePath%>/products/advertising/list.html'"
											value="返回"></td>
									</tr>
								</table>
							</td>
							<td width="16"><img src="<%=imagePath%>tab_07.gif"
								width="16" height="30" /></td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td><table width="100%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
							<td><table width="100%" border="0" cellpadding="0"
									cellspacing="1" bgcolor="b5d6e6">
									<input type="hidden" name="id" value="${advertising.id}">
									<tr>
										<td bgcolor="#FFFDF0"><div align="center">广告标题：</div></td>
										<td colspan="3" bgcolor="#FFFFFF"><input type="text"
											maxlength="20" style="width: 165px" name="name"
											value="${advertising.name }"></td>
										<td bgcolor="#FFFDF0" rowspan="6"><div align="center">广告图片：</div></td>
										<td colspan="3" bgcolor="#FFFFFF" rowspan="6">&nbsp; <share:imageUpload
												imageLabelName="广告图片" imagePathId="imagePath"
												savePath="product/"
												imagePath="${advertising.imagePath }" /> <input
											type="hidden" value="${advertising.imagePath }"
											id="imagePath" name="imagePath" />
										</td>
									</tr>
									<tr>
										<td bgcolor="#FFFDF0"><div align="center">广告图片链接：</div></td>
										<td colspan="3" bgcolor="#FFFFFF"><input type="text"
											maxlength="120" style="width: 300px" name="imageLink"
											value="${advertising.imageLink }">
											</td>
									</tr>
									<tr>
										<td bgcolor="#FFFDF0"><div align="center">是否可用：</div></td>
										<td colspan="3" bgcolor="#FFFFFF">
											<input type="radio" name="isUsed" <c:if test="${advertising.isUsed eq '0'}">checked="checked"</c:if> value="0">否
											<input type="radio" name="isUsed" <c:if test="${advertising.isUsed eq '1'}">checked="checked"</c:if> value="1">是
										</td>
									</tr>
								</table></td>
							<td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
						</tr>
					</table></td>
			</tr>
			<tr>
				<td height="35" background="<%=imagePath%>tab_19.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="35"><img
								src="<%=imagePath%>tab_18.gif" width="12" height="35" /></td>
							<td></td>
							<td width="16"><img src="<%=imagePath%>tab_20.gif"
								width="16" height="35" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
<script type="text/javascript">
	var info = '${info}';
	if (info.length > 0) {
		alert(info);
	}
</script>
