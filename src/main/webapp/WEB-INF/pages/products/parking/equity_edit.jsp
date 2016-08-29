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
	  <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
  </head>
  <body>
  
<form action="<%=basePath%>products/parking/equity/edit.html"  name="form1" onsubmit="return validator(this)" method="post" >
<table class=editTable cellSpacing=0 cellPadding=0 width="100%" align=center border=0>
	<tr class=editHeaderTr>
		<td class=editHeaderTd colSpan=7>  请输入车位信息<span style="color: red;"></span></td>
	</tr>  
    <tr>
     <td><div ><font color="#39d5b8">*</font>小区名称：</div></td>
		<td colspan="3" >
			 <input type="text"   maxlength="20" class="selectList"
					id="villageName" name="villageName" value="${villageName }" readonly="readonly" >
		</td>
	  	<td><div ><font color="#39d5b8">*</font>车牌号：</div></td>
		<td colspan="3" >
			<input  type="hidden"  maxlength="20" class="selectList"     valid="required"  errmsg="车牌号不能为空!"
				id="carNumber" name="carNumber" value="${propertyparkinginfo.carNumber }"  >
				<input   maxlength="20" class="selectList"     valid="required"  errmsg="车牌号不能为空!"
				id="newCarNumber" name="newCarNumber" value="${propertyparkinginfo.carNumber }"  >
				
		</td>
		 </tr>    
    <tr>
    <td><div >车主姓名：</div></td>
		<td colspan="3" >
					<input    class="selectList"
							  id="owner" name="owner" value="${propertyparkinginfo.owner }" />
					 
				</td>
		
	  	<td><div >身份信息：</div></td>
		<td colspan="3" >
			 <input  class="selectList"   id="certificate" name="certificate" value="${propertyparkinginfo.certificate }"  />
		</td>
   </tr>
		  <tr>
	  	<td><div >车主联系地址：</div></td>
		<td colspan="3" >
			<input type="text"   maxlength="40" class="selectList"
				   name="address" id="address" value="${propertyparkinginfo.address }" />
		</td>
		
	  	<td><div >车主联系电话：</div></td>
		<td colspan="3" >
			<input type="text" id="phone" class="selectList"     value="${propertyparkinginfo.phone }"
				name="phone" >
		</td>
   </tr>  
		    <tr>
	  	<td><div >车辆颜色：</div></td>
		<td colspan="3" >
		   <select name="carColor" id="carColor"   class="selectList"  >
			 <c:if test="${propertyparkinginfo.carColor eq 1 }">
			  <option value="1" selected="selected">黑色</option>
			 <option value="2">白色</option>
			 <option value="3">其他</option>
			 </select>
			 </c:if>
			 <c:if test="${propertyparkinginfo.carColor eq 2 }">
			  <option value="1">黑色</option>
			 <option value="2" selected="selected">白色</option>
			 <option value="3">其他</option>
			 </select>
			 </c:if>
			 <c:if test="${propertyparkinginfo.carColor eq 3 }">
			  <option value="1">黑色</option>
			 <option value="2">白色</option>
			 <option value="3" selected="selected">其他</option>
			 </select>
			 </c:if>
			
		</td>
		
	  	<td><div ><font color="#39d5b8">*</font>月租单价：</div></td>
		<td colspan="3" >
			<input type="text" id="managementFeeMonthlyUnit" class="selectList"     valid="required"  errmsg="月租单价不能为空!"
				name="managementFeeMonthlyUnit" value="${propertyparkinginfo.managementFeeMonthlyUnit }"  />
		</td>
	  </tr>
    <tr>
	  	<td><div >是否有违规情况：</div></td>
		<td colspan="3" >
			<select name = "iillegalFlg" id="iillegalFlg"  class="selectList"  >
			        		<c:if test="${propertyparkinginfo.iillegalFlg eq 0 }">
			        		<option value="0">放行 </option>
			        		<option value="1">不允许放行</option>
			        		</c:if>
			        		<c:if test="${propertyparkinginfo.iillegalFlg eq 1 }">
			        		<option value="1">不允许放行</option>
			        		<option value="0">放行 </option>
			        		</c:if>
			        	</select> 
		</td>
	 
	  	<td><div >当月有效标识：</div></td>
		<td colspan="3" >
			<select name = "validityFlg" id="validityFlg" class="selectList"  >
			               <c:if test="${propertyparkinginfo.validityFlg eq 0 }">
			        		<option value="0">有效 </option>
			        		<option value="1">无效</option>
			        		</c:if>
			        		 <c:if test="${propertyparkinginfo.validityFlg eq 1 }">
			        		<option value="1">无效</option>
			        		<option value="0">有效 </option>
			        		</c:if>
			        	</select> 
		</td>
   	 </tr>   
   	   	 <tr>
	  	<td><div >车位地址信息：</div></td>
		<td colspan="3" >
				<input type="text" id="parkingAddrInfo" class="selectList"   maxlength="20"
				name="parkingAddrInfo" value="${propertyparkinginfo.parkingAddrInfo }"  />
		</td>
		<td><div >车位产权信息：</div></td>
		<td colspan="3" >
				<input type="text" id="parkingPropertyInfo"  class="selectList"  maxlength="20"
				name="parkingPropertyInfo" value="${propertyparkinginfo.parkingPropertyInfo }" />
		</td>
    </tr>
    <tr>
	  	<td><div >购买日期：</div></td>
		<td colspan="3" >
				<input type="text" id="purchaseDate"  maxlength="20" class="selectList"
					   name="purchaseDate1" value="${purchaseDate }"  onclick="WdatePicker({el:purchaseDate,dateFmt:'yyyy-MM-dd'})"/>
		</td>
		<td><div >车位物业信息：</div></td>
		<td colspan="3" >
				<input type="text" id="parkingInfo"  maxlength="20" class="selectList"
					   name="parkingInfo" value="${propertyparkinginfo.parkingInfo }" />
		</td>
    </tr>
    <tr>
	  	<td><div >开始时间：</div></td>
		<td colspan="3" >
				<input type="text" id="effect_begin_time"  maxlength="20" class="selectList"    onclick="WdatePicker({el:effect_begin_time,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'effect_end_time\')}'})"
				name="effect_begin_time1" value="${begin }" />
		</td>
			<td><div >结束时间：</div></td>
		<td colspan="3" >
			<input type="text" id="effect_end_time"  maxlength="20" class="selectList"     onclick="WdatePicker({el:effect_end_time,dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'effect_begin_time\')}',maxDate:'#F{$dp.$D(\'max_date\')}'})"
				name="effect_end_time1" value="${end }" />
		</td>
    </tr>
      <tr>
	  	<td><div >车位号：</div></td>
		<td colspan="3" >
				<input type="text" id="parkingNumber" class="selectList"
					   name="parkingNumber"  value="${propertyparkinginfo.parkingNumber }" />
		</td>
		
  <td><div >最大付款时间：</div></td>
		<td colspan="3" >
			<input type="text" id="max_date"  readonly="readonly" class="selectList"  onclick="WdatePicker({el:max_date,dateFmt:'yyyy-MM',minDate:'#F{$dp.$D(\'effect_end_time\')}'})"
				name="max_date1" value="${max_date }" />
		</td>
		<input type="hidden" name="createUser" value="${propertyparkinginfo.createUser }"  />
		<input type="hidden" name="villageId" value="${propertyparkinginfo.villageId }"  />
		  <input type="hidden" name="isUsed" value="${propertyparkinginfo.isUsed }"  />
    </tr>
    <tr>
    	<td><div >是否开通线上支付：</div></td>
		<td colspan="3" >
			<select name ="onlinePayment" id="onlinePayment"    class="selectList">
			        		<option value="0">不开通 </option>
			        		<option value="1" <c:if test="${propertyparkinginfo.onlinePayment eq 1 }">selected="selected"</c:if>>开通</option>
			        	</select> 
		</td>
		<td><div >开票抬头：</div></td>
		<td colspan="3" >
			<input type="text" id="makeUp"   maxlength="40"   value="${propertyparkinginfo.makeUp}"  class="selectList"
				name="makeUp">
		</td>
    </tr>
	<tr>
		<td align="right">
			<div>
				所属系统：
			</div>
		</td>
		<td colspan="7" bgcolor="#FFFFFF">
			<select name="module" class="dictionary selectList" data-dict-code="module" data-current-value="${shareUser.module}"  data-dict-value="${shareUser.module}" >
				<c:if test="${shareUser.module =='' || shareUser.module ==null }" ><option value=""></option></c:if>
			</select>
			&nbsp;
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

