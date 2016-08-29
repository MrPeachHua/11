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
    
    <title>增加车场信息</title>
    
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
  function check(){
	  $.ajax({
          cache: false,
          type: "POST",
          url:'products/parking/check.html',
          data:$('#parkingCode').serialize(),// 你的formid
          async: false,
          error: function(request) {
              alert("Connection error");
          },
          success: function(data) {
        	  if(data=="0"){
        		  alert("车场代码已存在，请重新输入!");
        	  }
              
          }
      });
  }
  </script>
  <body>
  
<form action="<%=basePath%>products/parking/save.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入车场信息<span style="color: red;"></span></td>
	</tr>  
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">车场代码：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="20"  style=" width: 165px" valid="required"  errmsg="车场代码不能为空!" onchange="check()"
				id="parkingCode" name="parkingCode" value="${parkingCode }">
		</td>
	  	<td bgcolor="#FFFDF0" rowspan="6"><div align="center">车场图片：</div></td>
		<td colspan="3" bgcolor="#FFFFFF" rowspan="6">&nbsp;
			<share:imageUpload imageLabelName="车场图片" imagePathId="parkingPath" savePath="product/" imagePath="${parkingPath }"/>
            <input type="hidden" value="" id="parkingPath" name="parkingPath"  />
		</td>
    </tr>  
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" valid="required"  errmsg="车场名称不能为空!" 
				name="parkingName" value="${parkingName }">
		</td>
    </tr>    
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">国家：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="parkingCountry" value="${parkingCountry }">
		</td>
    </tr>    
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">省：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="parkingProvince" value="${parkingProvince }">
		</td>
    </tr>    
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">市：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="parkingCity" value="${parkingCity }">
		</td>
    </tr>   
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">县 ：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="parkingCounty" value="${parkingCounty }">
		</td>
    </tr>   
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">小区：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" 
				name="parkingArea" value="${parkingArea }">
		</td>
	  	<td bgcolor="#FFFDF0"><div align="center">车位状态：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="20"  style=" width: 165px" 
				name="parkingStatus" value="${parkingStatus }">
		</td>
    </tr>
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">地址：</div></td>
		<td colspan="5" bgcolor="#FFFFFF">
			<input type="text"   maxlength="80"  style=" width: 70%" 
				name="parkingAddress" value="${parkingAddress }">
		</td>
    </tr>
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">经度：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="20"  style=" width: 165px" valid="isNumber"  errmsg="经度必须是数字!" 
				name="parkingLatitude" value="${parkingLatitude }">
		</td>
	  	<td bgcolor="#FFFDF0"><div align="center">纬度：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" valid="isNumber"  errmsg="纬度必须是数字!" 
				name="parkingLongitude" value="${parkingLongitude }">
		</td>
    </tr> 
    <tr>
	  	<td bgcolor="#FFFDF0"><div align="center">车位数：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="20"  style=" width: 165px" valid="isNumber"  errmsg="车位数必须是数字!" 
				name="parkingCount" value="${parkingCount }">
		</td>
	  	<td bgcolor="#FFFDF0"><div align="center">空位数：</div></td>
		<td colspan="3" bgcolor="#FFFFFF">
			<input type="text"   maxlength="40"  style=" width: 165px" valid="isNumber"  errmsg="空位数必须是数字!" 
				name="parkingCanUse" value="${parkingCanUse }">
		</td>
    </tr> 
    
	<tr>
		<td bgcolor="#FFFDF0"><div align="center">车场简介：</div></td>
		<td colspan="5" bgcolor="#FFFFFF"><textarea rows="5" name="parkingInfo" style="width:100%; resize:none; "  >${parkingInfo }</textarea></td>
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

