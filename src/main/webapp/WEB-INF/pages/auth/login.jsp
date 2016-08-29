<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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
    
    <title>用户登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!-- 浏览器上显示title 图标 icon图标 -->
	<link type="image/x-icon" rel="icon" href="images/default/logo2.0_32.ico">
	<!-- 让图标出现在收藏夹中 -->
	<link type="image/x-icon" rel="shortcut icon" href="images/default/logo2.0_32.ico">
	<link type="image/x-icon" rel="bookmark" href="images/default/logo2.0_32.ico">
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/FormValid.js" language="JavaScript"></script>
	<script type="text/javascript">	
		var errori ="<%=request.getSession().getAttribute("errorMsg")%>";
		if(errori!=null && errori!="null" && errori!="") {
			alert(errori);
		}
	</script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #016aa9;
	overflow:hidden;
}
.STYLE1 {
	color: #000000;
	font-size: 12px;
}
#in1{
	background-image: url('<%=imagePath%>dl.gif');
	height:18px;
	width:49px;
	border: 0px;
}
-->
</style>

<script type="text/javascript">
	function isNumberOrLetter( s ){//判断是否是数字或字母 
 		return true;
			//var regu = "^[0-9a-zA-Z]+$"; 
			//var re = new RegExp(regu); 
			//if (re.test(s)) { 
			//return true; 
			//}else{ 
			//alert("账号只能是字母或数字!"); 
			//return false; 
		//}	 
	} 
	</script>
  </head>
  
  <body>
 <form name="from1" action="../j_spring_security_check" method ="post"  onsubmit="return validator(this)" >
   <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td><table width="962" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="235" background="<%=imagePath%>login_03.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="53"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="394" height="53" background="<%=imagePath%>login_05.gif">&nbsp;</td>
            <td width="206" background="<%=imagePath%>login_06.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="16%" height="25"><div align="right"><span class="STYLE1">用户</span></div></td>
                <td width="57%" height="25"><div align="center">
                  <input type="text" name="j_username" id="j_username" valid="required"  errmsg="用户名不能为空!"  value=""
                  	style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td width="27%" height="25">&nbsp;</td>
              </tr>
              <tr>
                <td height="25"><div align="right"><span class="STYLE1">密码</span></div></td>
                <td height="25"><div align="center">
                  <input type="password" name="j_password" id="j_password"  valid="required"  errmsg="密码不能为空!" value=""
                  	style="width:105px; height:17px; background-color:#292929; border:solid 1px #7dbad7; font-size:12px; color:#6cd0ff">
                </div></td>
                <td height="25"><div align="left"><input type="submit" id="in1" value=""/></div></td>
              </tr>
            </table></td>
            <td width="362" background="<%=imagePath%>login_07.gif">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="213" background="<%=imagePath%>login_08.gif">
		</td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
  </body>
</html>
