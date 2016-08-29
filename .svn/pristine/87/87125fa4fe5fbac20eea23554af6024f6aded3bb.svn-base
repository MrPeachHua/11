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
    
    <title>添加用户</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
 <script type="text/javascript">
	function query(){  
			window.location="<%=basePath%>users/rolelist.html";
		}
	
</script>

  </head>
  
  <body>
    <form action="<%=basePath%>system/users/save.html" method="post"name="form2" onsubmit="return checkForm('form2');">
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入新用户的详细信息
		</td>
	</tr>  
  	<tr>
    	<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">用户ID：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userId" type="text"  maxlength="20"  check_str="用户ID" style="width: 138px" required name="userId" >
      &nbsp;</td>--%>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">账号：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id ="userAccount" type="text"  maxlength="20"  check_str="账户" style="width: 138px" required name="userAccount" >
      &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">用户名称：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userName"type="text"  maxlength="20"  check_str="用户名称" style="width: 138px" required name="userName" >
      &nbsp;</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">密码：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userPassword" type="text"  maxlength="20"  check_str="密码" style="width: 138px" name="userPassword" >
      &nbsp;</td>

       <td width="19%" bgcolor="#FFFDF0"><div align="center">备注：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userDesc" type="text"  maxlength="20"  check_str="备注" style="width: 138px" name="userDesc" >
      &nbsp;</td>
    </tr>
    <tr>
      	 <td width="19%" bgcolor="#FFFDF0"><div align="center">是否启用：</div></td>
   		<td colspan="3" bgcolor="#FFFFFF">

        <select name="enabled"  style="width: 138px" >
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
        &nbsp;</td>

        <td width="19%" bgcolor="#FFFDF0"><div align="center">是否是超级用户：</div></td>
   		<td colspan="3" bgcolor="#FFFFFF">

        <select name="issys"  style="width: 138px" >
            <option value="1">是</option>
            <option value="0">否</option>
        </select>
        &nbsp;</td>

    </tr>
    <tr>
      	 <td width="19%" bgcolor="#FFFDF0"><div align="center">部门：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userDept"type="text"  maxlength="20"  check_str="部门" style="width: 138px" required name="userDept" >
      &nbsp;</td>
     

       <td width="19%" bgcolor="#FFFDF0"><div align="center">职位：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "userDuty" type="text"  maxlength="20"  check_str="职位" style="width: 138px" name="userDuty" >
      &nbsp;</td>
    </tr>
    <tr>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">所有子系统：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "subSystem"type="text"  maxlength="20"  check_str="所属子系统" style="width: 138px" required name="subSystem" >
      &nbsp;</td>
        <td width="19%" bgcolor="#FFFDF0"><div align="center">角色信息：</div></td>
        <td colspan="6" bgcolor="#FFFFFF">
        <select id="" name="sysRoles" style="width: 138px"  >
            <c:forEach items="${list}" var="list">
                <option value="${list.roleId}">${list.roleName}</option>
            </c:forEach>

        </select>
        <%--<input id = "sysRoles"type="text"  maxlength="20"  check_str="角色信息" style="width: 138px" required name="sysRoles" >--%>
            &nbsp;</td>
      </tr>
</table>
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
  <tr bgcolor="#ECF3FD">
    <td width="10%">&nbsp;</td>
    <td width="12%"><input type="submit" name="Submit" value="提交"></td>
    <td width="12%"><input type="reset" name="button"    value="重置"></td>
    <td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
  </tr>
</table>

</form>
  </body>
</html>
