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

		<title>修改员工信息</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<%--<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>--%>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="<%=basePath%>/css/style_v2.css">
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<style type="text/css">
			body {
				padding: 15px 20px;
			}
			td {
				background-color: #ffffff;
				height: 17px;
				padding: 8px;
			}
		</style>
	</head>

	<body>
	<div class="mainView">
		<form action=" <%=basePath%>servlet/UserUpdateServlet" method="get" name="form2"  >
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" border='0'>
				<tr class=editHeaderTr>
					<td class='headerTitle' colSpan=7>
						用户的详细信息
						<input type="hidden" name="id" value="${userInfo.userId }" readonly="readonly">
					</td>
				</tr>
				<tr>
					<td style="height: 15px;"></td>
					<td colspan="3"></td>
					<td></td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="right"><span class="star">＊</span>姓名：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userName}</td>
					<td bgcolor="#FFFDF0"><div align="right"><span class="star">＊</span>登录账号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userNum}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="right"><span class="star">＊</span>部门：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.departmentName}</td>
					<td bgcolor="#FFFDF0"><div align="right"><span class="star">＊</span>角色：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.rolesName}</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFDF0"><div align="right">车场名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${parkingName}</td>
					<td bgcolor="#FFFDF0"><div align="right">E_mail：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userEmail}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="right">身份证：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userIdnum}</td>
					<td bgcolor="#FFFDF0"><div align="right">年龄：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userAge}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="right">性别：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="left">${userInfo.userSex}</td>
					<td bgcolor="#FFFDF0"><div align="right">民族：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userNation}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="right">学历：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left"> ${userInfo.userDiploma}</td>
					<td bgcolor="#FFFDF0"><div align="right">婚姻：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.isMarried}</td>
				</tr>
	
				<tr>
					<td bgcolor="#FFFDF0"><div align="right">座机：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userTel}</td>
					<td bgcolor="#FFFDF0"><div align="right">手机：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userMobile}</td>
				</tr>
			
				<tr>
					<td bgcolor="#FFFDF0"><div align="right">工资卡号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userBankcard}</td>
					<td bgcolor="#FFFDF0"><div align="right">爱好：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userIntest}</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFDF0"><div align="right">用户地址：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userAddress}</td>
					<td bgcolor="#FFFDF0"></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left"></td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="right">添加人：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userAddman}</td>
					<td bgcolor="#FFFDF0"><div align="right">添加时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userAddtime}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="right">修改人：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userChangeman}</td>
					<td bgcolor="#FFFDF0"><div align="right">修改时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="left">${userInfo.userChangetime}</td>
				</tr>

				<tr>
					<td style="height: 20px;"></td>
					<td colspan="3"></td>
					<td></td>
					<td colspan="3"></td>
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
	</div>
	</body>
</html>
