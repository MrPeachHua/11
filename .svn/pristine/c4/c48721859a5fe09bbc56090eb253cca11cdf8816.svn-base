<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
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
    <title>增加车管家轮番活动信息</title>
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
  <script type="text/javascript">
  </script>
  <body>
  
<form action="<%=basePath%>products/carlife/eventpage/event_add.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>请输入车管家轮番活动信息<span style="color: red;"></span></td>
	</tr>  
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">活动标题：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="20"  style=" width: 165px"
				id="title" name="title" value="${title }">
		</td>
	  	<td bgcolor="#FFFDF0" rowspan="6"><div align="center">活动图片：</div></td>
		<td colspan="3" bgcolor="#FFFFFF" rowspan="6">&nbsp;
			<share:imageUpload imageLabelName="活动图片" imagePathId="imagePath" savePath="product/"   imagePath="${imagePath }"/>
            <input type="hidden" valid="required"  errmsg="图片不能为空!" value="" id="imagePath" name="imagePath"  />
		</td>
    </tr>  
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">活动图片链接：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="120"  style=" width: 300px"
				name="imageLink" value="${imageLink }">
		</td>
    </tr>    
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">排序：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="sort" value="${sort }">
		</td>
    </tr>   
     <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">图片类型：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<%-- <input type="text"   maxlength="40"  style=" width: 165px" 
				name="imageType" value="${imageType}"> --%>
			<select name="imageType" value="${imageType}" maxlength="40"  style=" width: 165px">
				<option value="1">轮播图</option>
				<option value="2">车管家</option>
			</select>
		</td>
    </tr> 
</table>
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr bgcolor="#ECF3FD">
		<td width="25%"></td>
		<td width="17%"><input type="submit" name="submit"  value="添加"></td>
		<td width="17%"><input type="reset" name="reset"  value="重置"></td>
		<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
		<td width="43%"></td>
	</tr>
</table>
</form>
  </body>
</html>

