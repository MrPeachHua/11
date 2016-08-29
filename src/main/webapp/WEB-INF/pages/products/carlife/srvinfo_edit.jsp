<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>增加车场信息</title>

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
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
</head>
<body>

	<form action="<%=basePath%>products/carlife/srvinfo/update.html"
		name="form1" onsubmit="return validator(this)" method="post">
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr class=editHeaderTr>
				<td class=editHeaderTd colSpan=7>请输入服务信息<span
					style="color: red;"></span></td>
				<td><input type="hidden" name="id"
					value="${carlifeSrvInfo.id }"></td>
				<td><input type="hidden" name="createDate"
					value="${carlifeSrvInfo.createDate }"></td>
				<td><input type="hidden" name="createor"
					value="${carlifeSrvInfo.createor }"></td>
				<td><input type="hidden" name="isUsed"
					value="${carlifeSrvInfo.isUsed }"></td>

			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 165px" valid="required" errmsg="服务名称不能为空!"
					id="srvName" name="srvName" value="${carlifeSrvInfo.srvName }">
				</td>
				<td bgcolor="#FFFDF0" rowspan="6"><div align="center">服务logo：</div></td>
				<td colspan="3" bgcolor="#FFFFFF" rowspan="6">&nbsp; <share:imageUpload
						imageLabelName="服务logo" imagePathId="logoPath" savePath="product/"
						imagePath="${carlifeSrvInfo.logoPath}" /> <input type="hidden"
					value="${carlifeSrvInfo.logoPath }" id="logoPath" name="logoPath" />
				</td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">所属服务分类：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><select style="width: 165px"
					name="srvType">
						<c:forEach var="dictionary" items="${dictionary }">
							<c:choose>
								<c:when test="${dictionary.dictValue eq carlifeSrvInfo.srvType}">
									<OPTION value=${dictionary.dictValue } selected="selected">${dictionary.dictName}</OPTION>
								</c:when>
								<c:otherwise>
									<OPTION value=${dictionary.dictValue }>${dictionary.dictName}</OPTION>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务状态：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><select style="width: 165px"
					name="status">
						<c:if test="${carlifeSrvInfo.status eq 1 }">
							<OPTION value="1" selected="selected">已上线</OPTION>
							<OPTION value="0">预上线</OPTION>
						</c:if>
						<c:if test="${carlifeSrvInfo.status eq 0 }">
							<OPTION value="1">已上线</OPTION>
							<OPTION value="0" selected="selected">预上线</OPTION>
						</c:if>
				</select></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务简介：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><textarea rows="3" cols="6"
						maxlength="200" style="width: 165px" name="intro">${carlifeSrvInfo.intro}</textarea>
					<%-- <input type="text"   maxlength="40"  style=" width: 165px" 
				name="intro" value="${carlifeSrvInfo.intro }" > --%></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务标准介绍：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><textarea rows="3" cols="6"
						maxlength="200" style="width: 165px" name="description">${carlifeSrvInfo.description}</textarea>
					<%-- <input type="text"   maxlength="40"  style=" width: 165px" 
				name="description" value="${carlifeSrvInfo.description }"> --%></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务链接地址：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
					style="width: 165px" name="srvLink"
					value="${carlifeSrvInfo.srvLink }"></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">服务标签：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><select style="width: 165px"
					name="flag">
						<!-- dictList -->
						<c:forEach items="${dictList }" var="dict">
							<c:choose>
								<c:when test="${dict.dictValue eq  carlifeSrvInfo.flag}">
									<option value="${dict.dictValue }" selected="selected">${dict.dictName}</option>
								</c:when>
								<c:otherwise>
									<option value="${dict.dictValue }">${dict.dictName}</option>
								</c:otherwise>
							</c:choose>
						</c:forEach>
				</select></td>
				<td bgcolor="#FFFDF0"><div align="center">排序规则：</div></td>
				<td colspan="3" bgcolor="#FFFFFF"><input type="text"
														 style="width: 165px" name="sort"
														 value="${carlifeSrvInfo.sort }"></td>
			</tr>
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">最大服务次数：</div></td>
				<td colspan="6" bgcolor="#FFFFFF"><input type="text"
														 style="width: 165px" name="maxCount"
														 value="${carlifeSrvInfo.maxCount }"></td>
			</tr>
		</table>
		<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
			align=center border=0>
			<tr bgcolor="#ECF3FD">
				<td width="25%"></td>
				<td width="17%"><input type="submit" name="submit" value="提交"></td>
				<td width="4%"><input type="button" name="button"
					onClick="history.back() " value="返回"></td>
				<td width="43%"></td>
			</tr>
		</table>
	</form>
</body>
</html>

