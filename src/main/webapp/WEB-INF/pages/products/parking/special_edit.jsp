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
    
    <title>修改产权车位信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	  <LINK href="<%=basePath%>css/file.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
  </head>
  <body>
  
<form action="<%=basePath%>products/parking/specialCar/edit.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入车位信息<span style="color: red;"></span></td>
	</tr>  
    <tr>
     <td ><div align="center"><font color="#39d5b8">*</font>小区名称：</div></td>
		<td colspan="3" >
			 <input type="text"   maxlength="20"
				id="villageName" name="villageName" value="${villageName }" class="selectList"  readonly="readonly" >
				<input type="text"  style="display:none" 
				 name="villageId" value="${specialparkinginfo.villageId}" >
		</td>
	  	<td ><div align="center"><font color="#39d5b8">*</font>车牌号：</div></td>
		<td colspan="3" >
			<input type="text"   maxlength="20" valid="required" class="selectList"   errmsg="车牌号不能为空!"
				id="newCarNumber" name="newCarNumber" value="${specialparkinginfo.carNumber }">
			<input type="hidden" name="carNumber" value="${specialparkinginfo.carNumber }">
		</td>
		 </tr>    
    <tr>
    <td ><div align="center">车主姓名：</div></td>
		<td colspan="3" >
					<input   class="selectList"
							 id="owner" name="owner" value="${specialparkinginfo.owner }" />
					 
				</td>
		
	  	<td ><div align="center">身份信息：</div></td>
		<td colspan="3" >
			 <input  class="selectList"
					 id="certificate" name="certificate" value="${specialparkinginfo.certificate }"  />
		</td>
   </tr>
		  <tr>
	  	<td ><div align="center">车主联系地址：</div></td>
		<td colspan="3" >
			<input type="text"   maxlength="40" class="selectList"
				   name="address" id="address" value="${specialparkinginfo.address }" >
		</td>
		
	  	<td ><div align="center">车主联系电话：</div></td>
		<td colspan="3" >
			<input type="text" id="phone" class="selectList"   value="${specialparkinginfo.phone }"
				name="phone" >
		</td>
   </tr>  
		    <tr>
	  	<td ><div align="center">车辆颜色：</div></td>
		<td colspan="3" >
		   <select name="carColor" id="carColor"   class="selectList"  >
			 <c:if test="${specialparkinginfo.carColor eq 1 }">
			  <option value="1" selected="selected">黑色</option>
			 <option value="2">白色</option>
			 <option value="3">其他</option>
			 </select>
			 </c:if>
			 <c:if test="${specialparkinginfo.carColor eq 2 }">
			  <option value="1">黑色</option>
			 <option value="2" selected="selected">白色</option>
			 <option value="3">其他</option>
			 </select>
			 </c:if>
			 <c:if test="${specialparkinginfo.carColor eq 3 }">
			  <option value="1">黑色</option>
			 <option value="2">白色</option>
			 <option value="3" selected="selected">其他</option>
			 </select>
			 </c:if>
			
		</td>
		<td ><div align="center"></div></td>
		<td colspan="3" >
			
		</td>
	</tr>
	<tr class="bottom_btn">
		<td colspan="12">
			<div>
				<input class="btn_blue" type="submit" name="submit"  value="提交">
				<input class="btn_blue" type="reset" name="reset"  value="重置">
				<input class="btn_blue" type="button" name="back" onclick="history.back()" value="返回">
			</div>
		</td>
	</tr>
</table>
</form>
  </body>
</html>

