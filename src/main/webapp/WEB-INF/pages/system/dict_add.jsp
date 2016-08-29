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
    
    <title>添加字典</title>
    
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
    <form action="<%=basePath%>system/dict_add.html" method="post"name="form2" onsubmit="return checkForm('form2');">
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入新字典的详细信息
		</td>
	</tr>  
  	<tr>
    	<td width="19%" bgcolor="#FFFDF0"><div align="center">字典代码：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "dictCode" type="text"  maxlength="20"  check_str="字典代码" style="width: 138px" required name="dictCode" >
      &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">字典名称：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id ="dictName" type="text"  maxlength="20"  check_str="字典名称" style="width: 138px" required name="dictName" >
      &nbsp;</td>
      </tr>
      <tr>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">字典值：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "dictValue"type="text"  maxlength="20"  check_str="字典值" style="width: 138px" required name="dictValue" >
      &nbsp;</td>
      <td width="19%" bgcolor="#FFFDF0"><div align="center">类型：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "memo" type="text"  maxlength="20"  check_str="类型" style="width: 138px" name="memo" >
      &nbsp;</td>
     <!--  </tr>
      	 <td width="19%" bgcolor="#FFFDF0"><div align="center">是否可用：</div></td>
   		 <td colspan="3" bgcolor="#FFFFFF"><input id = "dictValue"type="text"  maxlength="20"  check_str="字典值" style="width: 138px" required name="dictValue" >
      &nbsp;</td>
      </tr> -->
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
