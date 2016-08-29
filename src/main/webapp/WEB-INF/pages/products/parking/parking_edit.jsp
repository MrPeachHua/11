<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
    
    <title>显示产品信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.STYLE1 {font-size: 12px}
.STYLE3 {font-size: 12px; font-weight: bold; }
.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

a{
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60; TEXT-DECORATION: underline
}

-->
</style>
<script type="text/javascript">
window.onload=function()
{
	var obtn=document.getElementById('check');
	var oinput=document.getElementsByTagName('input');
	var i=0;
	var countclick=0;
	obtn.onclick=function()
	{   
	   
	   countclick=countclick+1;
	   
	   if((countclick % 2)==1)
	   {
			for(i=0;i<oinput.length;i++)
			{
				oinput[i].checked=true;
			}
		}
		else
		{
			for(i=0;i<oinput.length;i++)
			{
				oinput[i].checked=false;
			}

		}
	}

}

</script>
<script>
var  highlightcolor='#c1ebff';
var  clickcolor='#51b2f6';
function  changeto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=highlightcolor&&source.id!="nc"&&cs[1].style.backgroundColor!=clickcolor)
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=highlightcolor;
}
}

function  changeback(){
if  (event.fromElement.contains(event.toElement)||source.contains(event.toElement)||source.id=="nc")
return
if  (event.toElement!=source&&cs[1].style.backgroundColor!=clickcolor)
//source.style.backgroundColor=originalcolor
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}

function  clickto(){
source=event.srcElement;
if  (source.tagName=="TR"||source.tagName=="TABLE")
return;
while(source.tagName!="TD")
source=source.parentElement;
source=source.parentElement;
cs  =  source.children;
//alert(cs.length);
if  (cs[1].style.backgroundColor!=clickcolor&&source.id!="nc")
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor=clickcolor;
}
else
for(i=0;i<cs.length;i++){
	cs[i].style.backgroundColor="";
}
}
</script>
  </head>
  
  <body>
  <form action="<%=basePath%>products/parking/save.html" id="form2" method="post">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="30" background="<%=imagePath%>tab_05.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="STYLE4" align="center">
						&nbsp;&nbsp;
					</td>
					<td class="STYLE4">
						&nbsp;&nbsp;
					</td>
					<td align="right" class="STYLE4">
						<input type="submit" name="submit"  value="提交">&nbsp;&nbsp;
						<input type="button" name="button"  onClick="history.back()"  value="返回">
					</td>
				</tr>
			</table>
		</td>
        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" />
        </td>
      </tr>
    </table></td>
  </tr>


  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6"> 
			<input type="hidden" name="id" value="${parking.id}">
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">车场代码：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="20"  style=" width: 165px" valid="required"  errmsg="车场代码不能为空!" readonly="readonly" disabled="disabled"
						name="parkingCode" value="${parking.parkingCode }">
				</td>
			  	<td bgcolor="#FFFDF0" rowspan="6"><div align="center">车场图片：</div></td>
				<td colspan="3" bgcolor="#FFFFFF" rowspan="6">&nbsp;
					<share:imageUpload imageLabelName="车场图片" imagePathId="parkingPath" savePath="product/" imagePath="${parking.parkingPath }"/>
		            <input type="hidden" value="${parking.parkingPath }" id="parkingPath" name="parkingPath"  />
				</td>
		    </tr>    
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">车场名称：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" valid="required"  errmsg="车场名称不能为空!" 
						name="parkingName" value="${parking.parkingName }">
				</td>
		    </tr>    
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">国家：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" 
						name="parkingCountry" value="${parking.parkingCountry }">
				</td>
		    </tr>    
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">省：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" 
						name="parkingProvince" value="${parking.parkingProvince }">
				</td>
		    </tr>    
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">市：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" 
						name="parkingCity" value="${parking.parkingCity }">
				</td>
		    </tr>   
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">县 ：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" 
						name="parkingCounty" value="${parking.parkingCounty }">
				</td>
		    </tr>   
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">小区：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" 
						name="parkingArea" value="${parking.parkingArea }">
				</td>
			  	<td bgcolor="#FFFDF0"><div align="center">车位状态：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="20"  style=" width: 165px" 
						name="parkingStatus" value="${parking.parkingStatus }">
				</td>
		    </tr>
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">地址：</div></td>
				<td colspan="5" bgcolor="#FFFFFF">
					<input type="text"   maxlength="80"  style=" width: 70%" 
						name="parkingAddress" value="${parking.parkingAddress }">
				</td>
		    </tr>
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">经度：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="20"  style=" width: 165px" valid="isNumber"  errmsg="经度必须是数字!" 
						name="parkingLatitude" value="${parking.parkingLatitude }">
				</td>
			  	<td bgcolor="#FFFDF0"><div align="center">纬度：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" valid="isNumber"  errmsg="纬度必须是数字!" 
						name="parkingLongitude" value="${parking.parkingLongitude }">
				</td>
		    </tr> 
		    <tr>
			  	<td bgcolor="#FFFDF0"><div align="center">车位数：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="20"  style=" width: 165px" valid="isNumber"  errmsg="车位数必须是数字!" 
						name="parkingCount" value="${parking.parkingCount }">
				</td>
			  	<td bgcolor="#FFFDF0"><div align="center">空位数：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input type="text"   maxlength="40"  style=" width: 165px" valid="isNumber"  errmsg="空位数必须是数字!" 
						name="parkingCanUse" value="${parking.parkingCanUse }">
				</td>
		    </tr> 
		    
			<tr>
				<td bgcolor="#FFFDF0"><div align="center">车场简介：</div></td>
				<td colspan="5" bgcolor="#FFFFFF"><textarea rows="5" name="parkingInfo" style="width:100%; resize:none; "  >${parking.parkingInfo }</textarea></td>
			</tr>
			
         </table></td>
        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>


  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td><table id="attrivalues" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
			<tr>
				<td background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 5%; height: 22px;">
					<div align="center">
						<span class="STYLE1">属性ID</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif"
					bgcolor="#FFFFFF" style="width: 15%; height: 22px;">
					<div align="center">
						<span class="STYLE1">属性代码</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 15%; height: 22px;">
					<div align="center">
						<span class="STYLE1">属性名称</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" width="25%" class="STYLE1" style="width: 10%; height: 22px;">
					<div align="center">
						<span class="STYLE1">属性值</span>
					</div>
				</td>
				<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" width="40%" class="STYLE1" style="width: 10%; height: 22px;">
					<div align="center">
						<span class="STYLE1">属性简介</span>
					</div>
				</td>
			</tr>
		  <c:choose>		
		  <c:when test="${fn:length(attriList)>0}">
		  <c:forEach items="${attriList}" var="row" varStatus="status">
		    <input type="hidden" name="attriList[${status.index}].parkingCode" value="${parking.parkingCode }">
		    <input type="hidden" name="attriList[${status.index}].attributeCode" value="${row.attributeCode }">
 			<tr>
				<td height="20" bgcolor="#FFFFFF">
					<div align="center" class="STYLE1"><div align="center">${row.id}</div></div>
				</td>
            	<td height="20" bgcolor="#FFFFFF""><div align="left">&nbsp;<span class="STYLE1">
            		${row.attributeCode }</span></div>
            	</td>
            	<td height="20" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1">${row.attributeName }</span></div></td>
            	<td height="20" bgcolor="#FFFFFF"><div align="left"><span class="STYLE1">
					<input type="text" style=" width: 100%" name="attriList[${status.index}].attributeValue" value="${row.attributeValue }"></span></div>
				</td>
            	<td height="20" bgcolor="#FFFFFF"><div align="left"><span class="STYLE1">
					<input type="text" style=" width: 100%" name="attriList[${status.index}].memo" value="${row.memo }"></span></div>
				</td>
          	</tr>
	      </c:forEach>
		  </c:when>
          <c:otherwise>
     		<tr>
				<td height="20" bgcolor="#FFFFFF" colspan="22"  align="center">
					<div align="center">
						<span class="STYLE1">没有产品属性信息</span>
					</div>
				</td>
			</tr>
	      </c:otherwise>	
		  </c:choose>
         </table></td>
        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr><td height="35" background="<%=imagePath%>tab_19.gif">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="12" height="35"><img src="<%=imagePath%>tab_18.gif" width="12" height="35" /></td>
        <td></td>
        <td width="16"><img src="<%=imagePath%>tab_20.gif" width="16" height="35" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</form>
</body>
</html>
<script type="text/javascript">
<!--
if('${product.productAttributeGroup.attriGrpCode}' != null && '${product.productAttributeGroup.attriGrpCode}' !='' ){
	$('#attriGrpCode').val('${product.productAttributeGroup.attriGrpCode}');
}
//-->
</script>
