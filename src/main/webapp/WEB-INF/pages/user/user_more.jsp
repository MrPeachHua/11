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
						用户的详细信息
						<input type="hidden" name="id" value="${userInfo.userId }" readonly="readonly">
					</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>姓名：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userName}</td>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>登录账号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userNum}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>部门：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.departmentName}</td>
					<td bgcolor="#FFFDF0"><div align="center"><font color="red">*</font>角色：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.rolesName}</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${parkingName}</td>
					<td bgcolor="#FFFDF0"><div align="center">E_mail：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userEmail}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">身份证：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userIdnum}</td>
					<td bgcolor="#FFFDF0"><div align="center">年龄：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userAge}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0" style="height: 21px"><div align="center">性别：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" style="height: 21px" align="center">${userInfo.userSex}</td>
					<td bgcolor="#FFFDF0"><div align="center">民族：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userNation}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">学历：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center"> ${userInfo.userDiploma}</td>
					<td bgcolor="#FFFDF0"><div align="center">婚姻：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.isMarried}</td>
				</tr>
	
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">座机：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userTel}</td>
					<td bgcolor="#FFFDF0"><div align="center">手机：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userMobile}</td>
				</tr>
			
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">工资卡号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userBankcard}</td>
					<td bgcolor="#FFFDF0"><div align="center">爱好：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userIntest}</td>
				</tr>
				
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">用户地址：</div></td>
					<td colspan="7" bgcolor="#FFFFFF" align="center">${userInfo.userAddress}</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">添加人：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userAddman}</td>
					<td bgcolor="#FFFDF0"><div align="center">添加时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userAddtime}</td>
				</tr>

				<tr>
					<td bgcolor="#FFFDF0"><div align="center">修改人：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userChangeman}</td>
					<td bgcolor="#FFFDF0"><div align="center">修改时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF" align="center">${userInfo.userChangetime}</td>
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
