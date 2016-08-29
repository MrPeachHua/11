<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>添加版本控制</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
		<link rel="stylesheet" href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css">
		<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
		<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/pccs.js"></script>
	    <script type="text/javascript" src="<%=basePath%>/js/pages/dictionary.js"></script>
		<style type="text/css">

			.editTable td,#mysTable td{
				border: 1px solid #B1CEEE;
			}

		</style>
	
	<c:if test="${requestScope.info != null && requestScope.info != ''}">
		<script type="text/javascript">
			alert('${requestScope.info}');
		</script>
	</c:if>
</head>

<body>
<form action="<%=basePath%>products/parking/save.html" method="post" name="form2" onsubmit="return checkForm('form2');">
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
		<tr class=editHeaderTr>
			<td class=editHeaderTd colSpan=7>  车场信息</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车场编号：</div></td>
			<td colspan="6" bgcolor="#FFFFFF">
				<input id = "parkingIdentifier" type="text"   check_str="车场编号" style="width: 138px" required name="parkingIdentifier" value="${param.parkingIdentifier}" pattern="^[a-z]{1,6}$" maxlength=6   title="长度不能超过6位，只允许小写a-z!">
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">名称：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "parkingName" type="text"  maxlength="20"  check_str="名称" style="width: 138px" required name="parkingName" value="${param.parkingName}" >
				&nbsp;</td>
			<td rowspan="10" width="19%" bgcolor="#FFFDF0"><div align="center">车场照片：</div></td>
			<td rowspan="10" colspan="3" bgcolor="#FFFFFF">
				<share:imageUpload imageLabelName="车场照片" imagePathId="imagePath" savePath="product/min/"   imagePath="${param.parkingPath}"/>
				<input type="hidden" valid="required"  errmsg="图片不能为空!" value="${param.parkingPath}" id="imagePath" name="parkingPath"  />
				&nbsp;</td>
		</tr>
		<%--<tr>--%>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">国家：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF">--%>
				<%--<input id ="parkingCountry" type="text"  maxlength="20" style="width: 138px" required name="parkingCountry" value="中国" readonly="readonly" >--%>
				<%--&nbsp;</td>--%>
		<%--</tr>--%>
		<input id ="parkingCountry" type="hidden" name="parkingCountry" value="中国" readonly="readonly" >
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">省：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="parkingProvince" id="province"></select>
				<%--<input id = "parkingProvince" type="text" maxlength="20" check_str="省" style="width: 138px" required name="parkingProvince" value="${param.parkingProvince}" >--%>
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">市：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="parkingCity" id="city"></select>
				<%--<input id = "parkingCity" type="text"  maxlength="20"  check_str="市" style="width: 138px" name="parkingCity" value="${param.parkingCity}" >--%>
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">县/区：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="parkingCounty" id="county"></select>
				<%--<input id = "parkingCounty" type="text"  maxlength="20"  check_str="县" style="width: 138px" name="parkingCounty" value="${param.parkingCounty}" >--%>
				&nbsp;</td>
		</tr>
		<%--<tr>--%>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">区：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingArea" type="text"  maxlength="20"  check_str="区" style="width: 138px" name="parkingArea"  value="${param.parkingArea}" >--%>
				<%--&nbsp;</td>--%>
		<%--</tr>--%>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">详细地址：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingAddress" type="text"  maxlength="20"  check_str="地址" style="width: 138px" name="parkingAddress" value="${param.parkingAddress}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">经度：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingLongitude" type="text"  maxlength="20"  check_str="纬度" style="width: 138px" name="parkingLongitude" value="${param.parkingLongitude}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">纬度：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingLatitude" type="text"  maxlength="20"  check_str="经度" style="width: 138px" name="parkingLatitude"  value="${param.parkingLatitude}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车场简介：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingInfo" type="text"  maxlength="20"  check_str="车场简介" style="width: 138px" name="parkingInfo" value="${param.parkingInfo}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车位数：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingCount" type="text"  maxlength="20"  check_str="车位数" style="width: 138px" name="parkingCount"  value="${param.parkingCount}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">空位数初始值：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="text"   maxlength="20"  style=" width: 138px" check_str="空位数初始值"
					   name="parkingInitialUse" value="${param.parkingInitialUse }">
			</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">蓝卡云车场ID：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id="item01" type="text" style="width: 138px" name="villageinfo.item01" value="${param.villageinfo.item01}" >
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">发票说明：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id="invoiceDescribe" type="text" style="width: 138px" name="invoiceDescribe" value="${param.invoiceDescribe}" >
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">空位数：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "parkingCanUse" type="text"  maxlength="20"  check_str="空位数" style="width: 138px" name="parkingCanUse" value="${param.parkingCanUse}" >
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否能够自动支付：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isAutoPay" <c:if test="${param.isAutoPay eq '0' || param.isAutoPay == null}">checked="checked"</c:if> value="0">否
				<input type="radio" name="isAutoPay" <c:if test="${param.isAutoPay eq '1'}">checked="checked"</c:if> value="1">是
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车位状态：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<select name="parkingStatus">
					<option <c:if test="${param.parkingStatus eq '空'}">selected="selected"</c:if> value="空">空</option>
					<option <c:if test="${param.parkingStatus eq '正常'}">selected="selected"</c:if> value="正常">正常</option>
					<option <c:if test="${param.parkingStatus eq '中'}">selected="selected"</c:if> value="中">中</option>
					<option <c:if test="${param.parkingStatus eq '满'}">selected="selected"</c:if> value="满">满</option>
				</select>
				<%--<input id = "parkingStatus" type="text"  maxlength="20"  check_str="车位状态" style="width: 138px" name="parkingStatus"  value="${param.parkingStatus}" >--%>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否允许充电：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isCharge" <c:if test="${param.isCharge eq 0 || param.isCharge == null}">checked="checked"</c:if> value="0">否
				<input type="radio" name="isCharge" <c:if test="${param.isCharge eq 1}">checked="checked"</c:if> value="1">是
				<%--<input id = "isIndex" type="text"  maxlength="20"  check_str="isIndex" style="width: 138px" name="isIndex" value="${param.isIndex}" >--%>
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否允许开通蓝卡缴费：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isOpenPayment" <c:if test="${param.isOpenPayMent eq '0' || param.isOpenPayMent == null}">checked="checked"</c:if> value="0">否
				<input type="radio" name="isOpenPayment" <c:if test="${param.isOpenPayMent eq '1'}">checked="checked"</c:if> value="1">是
				<%--<input id = "isIndex" type="text"  maxlength="20"  check_str="isIndex" style="width: 138px" name="isIndex" value="${param.isIndex}" >--%>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车场所属区域：</div></td>
			<td colspan="6" bgcolor="#FFFFFF" >
				<select name="region" class="dictionary" style="width: 138px" <%--data-current-value="${param.p_orderType}"--%> data-dict-code="region">
				</select></td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">七鱼客服ID(","分隔)：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id="qiyuId" type="text" style="width: 138px" name="qiyuId" value="${parking.qiyuId}" >
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center"></div></td>
			<td colspan="6" bgcolor="#FFFFFF" ></td>
		</tr>
		<!--<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否社区车场：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isCommunity" <c:if test="${parking.isCommunity eq 0 || parking.isCommunity == null}">checked="checked"</c:if> value="0">否
				<input type="radio" name="isCommunity" <c:if test="${parking.isCommunity eq 1}">checked="checked"</c:if> value="1">是
				<%--<input id = "isIndex" type="text"  maxlength="20"  check_str="isIndex" style="width: 138px" name="isIndex" value="${param.isIndex}" >--%>
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center"></div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				&nbsp;</td>

		</tr>-->
		<tr class=editHeaderTr>
			<td class=editHeaderTd colSpan=7>优惠停车</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">临时停车价格：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "peacetimePrice" type="text"  maxlength="20" style="width: 138px" name="peacetimePrice"  value="${param.peacetimePrice}" >
				&nbsp;</td>
			<%--（1：待入场 2：已入场）--%>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否入场：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<%--<input id = "isIn" type="text"  maxlength="20"  check_str="是否入场" style="width: 138px" name="isIn" value="${param.isIn}" >--%>
				<input type="radio" name="isIn" <c:if test="${param.isIn eq 1 || param.isIn == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="isIn" <c:if test="${param.isIn eq 2}">checked="checked"</c:if> value="2">是
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">vip共享价格：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "vipStartTime" type="text"  maxlength="20" placeholder="如 08:00" style="width: 70px" name="vipStartTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${param.vipStartTime}" title="请输入正确的格式,如: 08:00">
				<input id = "vipStopTime" type="text"  maxlength="20" placeholder="如 18:00" style="width: 70px" name="vipStopTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${param.vipStopTime}" title="请输入正确的格式,如: 08:00">
				<input id = "vipSharePrice" type="text"  maxlength="20" placeholder='金额' style="width: 70px" name="vipSharePrice" value="${param.vipSharePrice}" >（元）
				&nbsp;</td>
			<%--（1：不可代泊 2：可代泊）--%>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否代泊：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<%--<input id = "canUse" type="text"  maxlength="20"  check_str="是否可代泊" style="width: 138px" name="canUse"  value="${param.canUse}" >--%>
				<input type="radio" name="canUse" <c:if test="${param.canUse eq 1 || param.canUse == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="canUse" <c:if test="${param.canUse eq 2}">checked="checked"</c:if> value="2">是
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">vip共享优惠描述：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "vipSharePriceComment" type="text"  maxlength="20" style="width: 138px" name="vipSharePriceComment" value="${param.vipSharePriceComment}" >
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否直连：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isDirect" <c:if test="${param.isDirect eq 1 || param.isDirect == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="isDirect" <c:if test="${param.isDirect eq 2}">checked="checked"</c:if> value="2">是
				<%--<input id = "isDirect" type="text"  maxlength="20"  check_str="isDirect" style="width: 138px" name="isDirect"  value="${param.isDirect}" >--%>
				&nbsp;</td>
		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "startTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="startTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$"  value="${param.startTime}" title="请输入正确的格式,如: 08:00">
				<input id = "stopTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="stopTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${param.stopTime}" title="请输入正确的格式,如: 08:00">
				<input id = "sharePrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="sharePrice" value="${param.sharePrice}" >（元）
				&nbsp;</td>
			<%--(1、时间 2、按次)--%>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">收费类型：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF">--%>
				<%--&lt;%&ndash;<input id = "chargeType" type="text"  maxlength="20"  check_str="收费类型" style="width: 138px" name="chargeType" value="${param.chargeType}" >&ndash;%&gt;--%>
				<%--<input type="radio" name="chargeType" <c:if test="${param.chargeType eq 1 || param.chargeType == null}">checked="checked"</c:if> value="1">时间--%>
				<%--<input type="radio" name="chargeType" <c:if test="${param.chargeType eq 2}">checked="checked"</c:if> value="2">按次--%>
				<%--&nbsp;</td>--%>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否合作：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isCooperate" <c:if test="${param.isCooperate eq 1 || param.isCooperate == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="isCooperate" <c:if test="${param.isCooperate eq 2}">checked="checked"</c:if> value="2">是
				<%--<input id = "isCooperate" type="text"  maxlength="20"  check_str="isCooperate" style="width: 138px" name="isCooperate" value="${param.isCooperate}" >--%>
				&nbsp;</td>
		</tr>
		<tr>

			<td width="19%" bgcolor="#FFFDF0"><div align="center">共享优惠描述：</div></td>
			<td colspan="3" bgcolor="#FFFFFF"><input id = "sharePriceComment" type="text"  maxlength="20" style="width: 138px" name="sharePriceComment"  value="${param.sharePriceComment}" >
				&nbsp;</td>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">designatedPrice：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "designatedPrice" type="text"  maxlength="20"  check_str="designatedPrice" style="width: 138px" name="designatedPrice" value="${param.designatedPrice}" >--%>
				<%--&nbsp;</td>--%>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否开放：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isOpen" <c:if test="${param.isOpen eq 1 || param.isOpen == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="isOpen" <c:if test="${param.isOpen eq 2}">checked="checked"</c:if> value="2">是
				<%--<input id = "isOpen" type="text"  maxlength="20"  check_str="isOpen" style="width: 138px" name="isOpen"  value="${param.isOpen}" >--%>
				&nbsp;</td>
		</tr>
		<%--<tr>--%>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">折扣：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "discount" type="text"  maxlength="20"  check_str="折扣" style="width: 138px" name="discount"  value="${param.discount}" >--%>
				<%--&nbsp;</td>--%>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">按次数收费：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "priceTimes" type="text"  maxlength="20"  check_str="按次数收费" style="width: 138px" name="priceTimes" value="${param.priceTimes}" >--%>
				<%--&nbsp;</td>--%>
		<%--</tr>--%>

		<tr>

			<td width="19%" bgcolor="#FFFDF0"><div align="center">周一优惠停车：</div></td>
			<%--<td colspan="3" bgcolor="#FFFFFF">
                <input id="mondayPrice" type="text" style="width: 138px" name="mondayPrice">
                &nbsp;</td>--%>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "mondayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="mondayBeginTime"  title="请输入正确的格式,如: 08:00">
				<input id = "mondayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="mondayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "mondayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="mondayPrice" >（元）
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">是否首屏：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input type="radio" name="isIndex" <c:if test="${param.isIndex eq 1 || param.isIndex == null}">checked="checked"</c:if> value="1">否
				<input type="radio" name="isIndex" <c:if test="${param.isIndex eq 2}">checked="checked"</c:if> value="2">是
				<%--<input id = "isIndex" type="text"  maxlength="20"  check_str="isIndex" style="width: 138px" name="isIndex" value="${param.isIndex}" >--%>
				&nbsp;</td>

		</tr>




		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">周二优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "tuesdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="tuesdayBeginTime" title="请输入正确的格式,如: 08:00" >
				<input id = "tuesdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="tuesdayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "tuesdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="tuesdayPrice" >（元）
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">周三优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "wednesdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="wednesdayBeginTime"  title="请输入正确的格式,如: 08:00">
				<input id = "wednesdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="wednesdayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "wednesdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="wednesdayPrice" >（元）
				&nbsp;</td>

		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">周四优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "thursdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="thursdayBeginTime" title="请输入正确的格式,如: 08:00" >
				<input id = "thursdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="thursdayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "thursdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="thursdayPrice" >（元）
				&nbsp;</td>
		<td width="19%" bgcolor="#FFFDF0"><div align="center">周五优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "fridayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="fridayBeginTime" title="请输入正确的格式,如: 08:00" >
				<input id = "fridayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="fridayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "fridayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="fridayPrice" >（元）
				&nbsp;</td>

		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">周六优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "saturdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="saturdayBeginTime" title="请输入正确的格式,如: 08:00" >
				<input id = "saturdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="saturdayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "saturdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="saturdayPrice" >（元）
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">周日优惠停车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id = "sundayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="sundayBeginTime" title="请输入正确的格式,如: 08:00" >
				<input id = "sundayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" name="sundayEndTime" title="请输入正确的格式,如: 08:00">
				<input id = "sundayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="sundayPrice" >（元）
				&nbsp;</td>

		</tr>
		<tr class=editHeaderTr>
			<td class=editHeaderTd colSpan=7>代泊信息</td>
		</tr>
		<tr>
			<%--<td width="19%" bgcolor="#FFFDF0"><div align="center">封顶费用：</div></td>--%>
			<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "priceMax" type="text"  maxlength="20"  check_str="封顶费用" style="width: 138px" name="priceMax"  value="${param.priceMax}" >--%>
			<%--&nbsp;</td>--%>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">代泊价格规则：</div></td>
			<td colspan="6" bgcolor="#FFFFFF">
				<input type="radio" name="rule" <c:if test="${param.rule eq 1 || param.isIndex == null}">checked="checked"</c:if> value="1">规则1
				<input type="radio" name="rule" <c:if test="${param.rule eq 2}">checked="checked"</c:if> value="2">规则2
				<%--<td colspan="3" bgcolor="#FFFFFF"><input id = "rule" type="text"  maxlength="20"  check_str="rule" style="width: 138px" name="rule"  value="${param.rule}" >--%>
				&nbsp;</td>


		</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">规则一：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				起步价：</br><input id = "scheme_init_price" type="text"  maxlength="20" placeholder='起步价' style="width: 138px" name="scheme_init_price"  value="${param.scheme_init_price}" >（元）</br>
				起步价时间：</br><input id = "scheme_init_hour" type="text"  maxlength="20" placeholder='起步价时间'  style="width: 138px" name="scheme_init_hour"  value="${param.scheme_init_hour}" >（小时）</br>
				超过每小时：</br><input id = "scheme_exceed_price" type="text"  maxlength="20" placeholder='超过每小时'  style="width: 138px" name="scheme_exceed_price"  value="${param.scheme_exceed_price}" >（元）</br>
				封顶价格：</br><input id = "scheme_top_price" type="text"  maxlength="20" placeholder='封顶价格'  style="width: 138px" name="scheme_top_price"  value="${param.scheme_top_price}" >（元）</br>
				折扣：</br><input id = "scheme_discount" type="text"  maxlength="20" placeholder='折扣'  style="width: 138px" name="scheme_discount"  value="${param.scheme_discount}" >（折）
				&nbsp;</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">规则二：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				代泊费：</br><input id = "scheme_proxy_price" type="text"  maxlength="20" placeholder='代泊费'  style="width: 138px" name="scheme_proxy_price" value="${param.scheme_proxy_price}" >（元/次） </br>
				停车场价格：</br><input id = "scheme_park_price" type="text"  maxlength="20" placeholder='停车场价格'  style="width: 138px" name="scheme_park_price" value="${param.scheme_park_price}" >（元） </br>
				超过夜间停车时间：</br><input id = "scheme_exceed_night_price" type="text"  maxlength="20" placeholder='超过夜间停车时间'  style="width: 138px" name="scheme_exceed_night_price" value="${param.scheme_exceed_night_price}" >（元/小时）
				&nbsp;</td>
		</tr>


		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">代客泊车：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id="parkPriceComment" type="text" style="width: 138px" name="parkPriceComment" value="${param.parkPriceComment}" >
				&nbsp;</td>
				<td width="19%" bgcolor="#FFFDF0"><div align="center">单小时最大接单数：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id=maxinumHour type="text" style="width: 138px" name="maxinumHour"  >
				&nbsp;</td>
		</tr>
		<!-- <tr>
			 <td width="19%" bgcolor="#FFFDF0" style=" border: 1px solid dodgerblue;"><div align="center">车场名称：</div></td>
             <td colspan="6" bgcolor="#FFFFFF">
        <input class="btn btn-default" type="button"  name="ai" value="选择对应车场" style="width: 138px" />
      </td>
      </td>

		</tr> -->
			<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">选择对应车场：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input class="btn btn-default" type="button" id="choosePaking" value="选择对应车场" style="width: 138px" />
					</td>
				<td width="19%" bgcolor="#FFFDF0"><div align="center">单日最大代泊数：</div></td>
				<td colspan="3" bgcolor="#FFFFFF">
					<input id=maximumDay type="text"  style="width: 138px" name="maximumDay"  >
					&nbsp;</td>

				</tr>
		<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">代泊服务开始时间：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input  type="text" id="parkBeginTime" name="parkBeginTime"  style="width: 138px"  placeholder="如 08:00" pattern="/^([01]\d|2[0-3]):?([0-5]\d)$/" title="请输入正确的格式,如: 8:00" />
			</td>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">代泊服务结束时间：</div></td>
			<td colspan="3" bgcolor="#FFFFFF">
				<input id=parkEndTime type="text"  style="width: 138px" name="parkEndTime"   placeholder="如 18:00" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" title="请输入正确的格式,如: 8:00" />
				&nbsp;</td>
		</tr>
	</table>
	<div class="modal" id="mymodal"></div>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
  <script>
	$(function(){
		$("#choosePaking").click(function(){
			 $("#mymodal").load("products/parking/toChooseParkingPage.html").css({"height":"100%","min-height":"100px","max-height":"450","overflowY":"scroll"}).modal("show"); 
		});
	/*	$(document).on('click',"#savePark",function(){
			$("#choosePaking").html("");
			$.each($("#mysTable input:checked"),function(i,obj){
				$("#choosePaking").append("<input type='hidden' name='parkingList' value='"+obj.value+"'/>");
			});
			$("#mymodal").modal("hide");
		});
		$(document).on('click',"#selectPark",function(){
			$.post("products/parking/get.html",{parkName:$("#p_queryValue").val()},function(data){
				$("#mysTable tr:gt(0)").remove();
				$.each(eval('('+data+')'),function(i,obj){
					$("#mysTable").append("<tr> <td height='22' style='width: 3%'><div align='center'><span class='STYLE1'>"+(i+1)+"</span></div></td> <td height='22' style='width: 8%'><div align='center'><span class='STYLE1'>"+obj.parkingName+"</span></div></td> <td height='22' style='width: 5%'><div align='center'><input type='checkbox' value='"+obj.parkingId+"' /></div></td> </tr>");
				});
			});
		});*/
	});
  </script>
	<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
		<tr bgcolor="#ECF3FD">
			<td width="10%">&nbsp;</td>
			<td width="12%"><input type="submit" name="Submit" value="提交"></td>
			<td width="12%"><input type="reset" name="button"    value="重置"></td>
			<td width="12%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
		</tr>
	</table>

</form>
<script language="javascript" type="text/javascript">setup();</script>
<script language="javascript" type="text/javascript">
			setup();
			$("#province").val($("#province_hidden").val());
			change(1);
			$("#city").val($("#city_hidden").val());
			change(2);
			$("#county").val($("#county_hidden").val());

			function selectDesc() {
				var desc = $("#p_queryValue").val();
				$(".desc").each(function () {
					var $tr = $(this).parent().parent().parent();
					if (desc == null || desc.length == 0 ) {
						$tr.show();
					} else if ($(this).text().indexOf(desc) == -1) {
						$tr.hide();
					} else {
						$tr.show();
					}
				});
			}
			$("form[name='form2']").submit(function(){
				var a= $("input[name='canUse'][value='2']");
				var b= $("input[name='isCooperate'][value='1']");
				if(a[0].checked&& b[0].checked){
					alert("非合作的时候不可代泊！");
					return false;
				}else if(a[0].checked){
					var maxinumHour = $('input[name=maxinumHour]').val();
					var maximumDay = $('input[name=maximumDay]').val();
					if (!(/^\d+$/.test(maxinumHour) && maxinumHour > 0) || !(/^\d+$/.test(maximumDay) && maximumDay > 0)) {
						alert("代泊时单小时最大接单数,单日最大代泊数不能为空 ！");
						return false;
					}
				}
				return true;
			});
		</script>
</body>
</html>
