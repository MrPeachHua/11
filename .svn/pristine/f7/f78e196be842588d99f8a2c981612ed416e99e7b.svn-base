<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
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
		<title>修改信息</title>
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
				border: 1px solid dodgerblue;
			}

		</style>
	</head>
	<body>
		<form action="<%=basePath%>products/parking/update.html" method="post" name="form2" onsubmit="return validator(this)">
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						请修改车场信息
						<input type="hidden" name="parkingId" value="${parking.parkingId }" readonly="readonly">
						<%--<input type="hidden" name="isUsed" value="${parking.isUsed }" readonly="readonly">--%>
						<%--<input type="hidden" name="createDate" value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${parking.createDate}" />" readonly="readonly">--%>
						<%--<input type="hidden" name="createor" value="${parking.createor }" readonly="readonly">--%>
					</td>
				</tr>
				<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">车场编号：</div></td>
			<td colspan="6" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px"  name="parkingIdentifier" pattern="^[a-z]{1,6}$" maxlength="6" value="${parking.parkingIdentifier}"   title="长度不能超过6位，只允许小写a-z!">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text" style="width: 190px" name="parkingName" value="${parking.parkingName}">
					</td>
					<td rowspan="10" bgcolor="#FFFDF0"><div align="center">车场照片：</div></td>
					<td rowspan="10" colspan="3" bgcolor="#FFFFFF">
						<share:imageUpload imageLabelName="车场照片" imagePathId="imagePath" savePath="product/min/"   imagePath="${parking.parkingPath}"/>
						<input type="hidden" value="${parking.parkingPath}" id="imagePath" name="parkingPath"  />
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">国家：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="parkingCountry" value="${parking.parkingCountry}" readonly="readonly">--%>
					<%--</td>--%>
				<%--</tr>--%>
				<input type="hidden" name="parkingCountry" value="${parking.parkingCountry}" readonly="readonly">
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">省：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="parkingProvince" id="province"></select>
						<input id="province_hidden" type="hidden" value="${parking.parkingProvince}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">市：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="parkingCity" id="city"></select>
						<input id="city_hidden" type="hidden" value="${parking.parkingCity}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">县：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="parkingCounty" id="county"></select>
						<input id="county_hidden" type="hidden" value="${parking.parkingCounty}">
					</td>
				</tr>
				<%--<tr>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">区：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="parkingArea"value="${parking.parkingArea}">--%>
					<%--</td>--%>
				<%--</tr>--%>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">详细地址：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingAddress" value="${parking.parkingAddress}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">经度：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingLongitude" value="${parking.parkingLongitude}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">纬度：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingLatitude"value="${parking.parkingLatitude}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车场简介：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingInfo" value="${parking.parkingInfo}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车位数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingCount"value="${parking.parkingCount}">
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">空位数初始值：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingInitialUse"value="${parking.parkingInitialUse}">
					</td>
				</tr>
				<tr>

					<td bgcolor="#FFFDF0"><div align="center">蓝卡云车场ID：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="villageinfo.item01" value="${parking.villageinfo.item01}">
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">发票说明</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="invoiceDescribe" value="${parking.invoiceDescribe}">
						&nbsp;</td>

				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">空位数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="parkingCanUse" value="${parking.parkingCanUse}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">是否能够自动支付：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" name="isAutoPay" <c:if test="${parking.isAutoPay eq '0'}">checked="checked"</c:if> value="0">否
						<input type="radio" name="isAutoPay" <c:if test="${parking.isAutoPay eq '1'}">checked="checked"</c:if> value="1">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车位状态：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<select name="parkingStatus">
							<option <c:if test="${parking.parkingStatus eq '空'}">selected="selected"</c:if> value="空">空</option>
							<option <c:if test="${parking.parkingStatus eq '正常'}">selected="selected"</c:if> value="正常">正常</option>
							<option <c:if test="${parking.parkingStatus eq '中'}">selected="selected"</c:if> value="中">中</option>
							<option <c:if test="${parking.parkingStatus eq '满'}">selected="selected"</c:if> value="满">满</option>
						</select>
						<%--<input type="text"  style="width: 190px" name="parkingStatus"value="${parking.parkingStatus}">--%>
					</td>
					<td bgcolor="#FFFDF0"><div align="center">是否允许充电：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" name="isCharge" <c:if test="${parking.isCharge eq 0}">checked="checked"</c:if> value="0">否
						<input type="radio" name="isCharge" <c:if test="${parking.isCharge eq 1}">checked="checked"</c:if> value="1">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">是否允许开通蓝卡缴费：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" name="isOpenPayment" <c:if test="${parking.isOpenPayment eq '0'}">checked="checked"</c:if> value="0">否
						<input type="radio" name="isOpenPayment" <c:if test="${parking.isOpenPayment eq '1'}">checked="checked"</c:if> value="1">是
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">车场所属区域：</div></td>
					<td colspan="6" bgcolor="#FFFFFF" >
						<select name="region" class="dictionary" style="width: 138px" data-current-value="${parking.region}" data-dict-code="region">
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
						<%--<input id = "parkingStatus" type="text"  maxlength="20"  check_str="车位状态" style="width: 138px" name="parkingStatus"  value="${param.parkingStatus}" >--%>
						&nbsp;</td>

				</tr>-->
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>优惠停车</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">vip共享价格：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 70px" placeholder="如 08:00" name="vipStartTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${parking.vipStartTime}" title="请输入正确的格式,如: 08:00">
						<input type="text"  style="width: 70px" placeholder="如 18:00" name="vipStopTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${parking.vipStopTime}" title="请输入正确的格式,如: 08:00">
						<input type="text"  style="width: 70px" placeholder='金额' name="vipSharePrice" value="${parking.vipSharePrice}">（元）
					</td>
					<%--（1：待入场 2：已入场）--%>
					<td bgcolor="#FFFDF0"><div align="center">是否入场：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input type="radio" name="isIn" <c:if test="${parking.isIn eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio" name="isIn" <c:if test="${parking.isIn eq 2}">checked="checked"</c:if> value="2">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">vip共享优惠描述：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="vipSharePriceComment" value="${parking.vipSharePriceComment}">
					</td>
					<%--（1：不可代泊 2：可代泊）--%>
					<td bgcolor="#FFFDF0"><div align="center">是否代泊：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" name="canUse" <c:if test="${parking.canUse eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio"  id="canUse" name="canUse" <c:if test="${parking.canUse eq 2}">checked="checked"</c:if> value="2">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">临时停车描述：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="peacetimePrice"value="${parking.peacetimePrice}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">是否直连：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<%--<input type="text"  style="width: 190px" name="isDirect"value="${parking.isDirect}">--%>
						<input type="radio" name="isDirect" <c:if test="${parking.isDirect eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio" name="isDirect" <c:if test="${parking.isDirect eq 2}">checked="checked"</c:if> value="2">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 70px" placeholder="如 08:00" name="startTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${parking.startTime}" title="请输入正确的格式,如: 08:00">
						<input type="text"  style="width: 70px" placeholder="如 18:00" name="stopTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${parking.stopTime}" title="请输入正确的格式,如: 08:00">
						<input type="text"  style="width: 70px" placeholder='金额' name="sharePrice" value="${parking.sharePrice}">（元）
					</td>
					<td bgcolor="#FFFDF0"><div align="center">是否合作：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<%--<input type="text"  style="width: 190px" name="isCooperate" value="${parking.isCooperate}">--%>
						<input type="radio" id="isCooperate" name="isCooperate" <c:if test="${parking.isCooperate eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio" name="isCooperate" <c:if test="${parking.isCooperate eq 2}">checked="checked"</c:if> value="2" onclick="boomshakalaka()">是
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">优惠停车描述：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" name="sharePriceComment"value="${parking.sharePriceComment}">
					</td>
					<td bgcolor="#FFFDF0"><div align="center">是否开放：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="radio" name="isOpen" <c:if test="${parking.isOpen eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio" name="isOpen" <c:if test="${parking.isOpen eq 2}">checked="checked"</c:if> value="2">是
						<%--<input type="text"  style="width: 190px" name="isOpen"value="${parking.isOpen}">--%>
					</td>
				</tr>

				<%--<tr>--%>
					<%--&lt;%&ndash;(1、时间 2、按次)&ndash;%&gt;--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">收费类型：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="radio" name="chargeType" <c:if test="${parking.chargeType eq 1}">checked="checked"</c:if> value="1">时间--%>
						<%--<input type="radio" name="chargeType" <c:if test="${parking.chargeType eq 2}">checked="checked"</c:if> value="2">按次--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">designatedPrice：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="designatedPrice" value="${parking.designatedPrice}">--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">折扣：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="discount"value="${parking.discount}">--%>
					<%--</td>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">按次数收费：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="priceTimes" value="${parking.priceTimes}">--%>
					<%--</td>--%>
				<%--</tr>--%>
				<%--<tr>--%>
					<%--<td bgcolor="#FFFDF0"><div align="center">封顶费用：</div></td>--%>
					<%--<td colspan="3" bgcolor="#FFFFFF">--%>
						<%--<input type="text"  style="width: 190px" name="priceMax"value="${parking.priceMax}">--%>
					<%--</td>--%>
				<%--</tr>--%>



				<tr>

					<td width="19%" bgcolor="#FFFDF0"><div align="center">周一优惠停车：</div></td>
					<%--<td colspan="3" bgcolor="#FFFFFF">
                        <input id="mondayPrice" type="text" style="width: 138px" name="mondayPrice">
                        &nbsp;</td>--%>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "mondayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="mondayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$"  value="${discountParkingPrice.mondayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "mondayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="mondayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.mondayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "mondayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="mondayPrice" value="${discountParkingPrice.mondayPrice}">（元）
						&nbsp;</td>
					<td bgcolor="#FFFDF0"><div align="center">是否首屏：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<%--<input type="text"  style="width: 190px" name="isIndex" value="${parking.isIndex}">--%>
						<input type="radio" name="isIndex" <c:if test="${parking.isIndex eq 1}">checked="checked"</c:if> value="1">否
						<input type="radio" name="isIndex" <c:if test="${parking.isIndex eq 2}">checked="checked"</c:if> value="2">是
					</td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周二优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "tuesdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="tuesdayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.tuesdayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "tuesdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="tuesdayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.tuesdayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "tuesdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="tuesdayPrice" value="${discountParkingPrice.tuesdayPrice}">（元）
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周三优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "wednesdayBeginTime" type="text"placeholder="如 08:00" maxlength="20" style="width: 70px" name="wednesdayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.wednesdayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "wednesdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="wednesdayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.wednesdayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "wednesdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="wednesdayPrice" value="${discountParkingPrice.wednesdayPrice}" >（元）
						&nbsp;</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周四优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "thursdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="thursdayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.thursdayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "thursdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="thursdayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.thursdayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "thursdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="thursdayPrice" value="${discountParkingPrice.thursdayPrice}" >（元）
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周五优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "fridayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="fridayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.fridayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "fridayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="fridayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.fridayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "fridayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="fridayPrice" value="${discountParkingPrice.fridayPrice}" >（元）
						&nbsp;</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周六优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "saturdayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="saturdayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.saturdayBeginTime}"  title="请输入正确的格式,如: 08:00">
						<input id = "saturdayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="saturdayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.saturdayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "saturdayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="saturdayPrice" value="${discountParkingPrice.saturdayPrice}" >（元）
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">周日优惠停车：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id = "sundayBeginTime" type="text" placeholder="如 08:00" maxlength="20" style="width: 70px" name="sundayBeginTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.sundayBeginTime}" title="请输入正确的格式,如: 08:00">
						<input id = "sundayEndTime" type="text" placeholder="如 18:00" maxlength="20" style="width: 70px" name="sundayEndTime" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" value="${discountParkingPrice.sundayEndTime}" title="请输入正确的格式,如: 08:00">
						<input id = "sundayPrice" placeholder='金额' type="text"  maxlength="20" style="width: 70px" name="sundayPrice" value="${discountParkingPrice.sundayPrice}">（元）
						&nbsp;</td>

				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>代泊信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">代泊价格规则：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						<input type="radio" name="rule" <c:if test="${parking.rule eq 1}">checked="checked"</c:if> value="1">规则1
						<input type="radio" name="rule" <c:if test="${parking.rule eq 2}">checked="checked"</c:if> value="2">规则2
						<%--<input type="text"  style="width: 190px" name="rule"value="${parking.rule}">--%>
					</td>

				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">规则一：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						起步价：</br><input id = "scheme_init_price" type="text"  maxlength="20" placeholder='起步价'  style="width: 190px" name="scheme_init_price"  value="${parking.scheme_init_price}" >（元）</br>
						起步价时间：</br><input id = "scheme_init_hour" type="text"  maxlength="20" placeholder='起步价时间'  style="width: 190px" name="scheme_init_hour"  value="${parking.scheme_init_hour}" >（小时）</br>
						超过每小时：</br><input id = "scheme_exceed_price" type="text"  maxlength="20" placeholder='超过每小时'  style="width: 190px" name="scheme_exceed_price"  value="${parking.scheme_exceed_price}" >（元）</br>
						封顶价格：</br><input id = "scheme_top_price" type="text"  maxlength="20" placeholder='封顶价格'  style="width: 190px" name="scheme_top_price"  value="${parking.scheme_top_price}" >（元）</br>
						折扣：</br><input id = "scheme_discount" type="text"  maxlength="20" placeholder='折扣'  style="width: 190px" name="scheme_discount"  value="${parking.scheme_discount}" >（折）
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">规则二：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						代泊费：</br><input id = "scheme_proxy_price" type="text"  maxlength="20" placeholder='代泊费'  style="width: 190px" name="scheme_proxy_price" value="${parking.scheme_proxy_price}" >（元/次） </br>
						停车场价格：</br><input id = "scheme_park_price" type="text"  maxlength="20" placeholder='停车场价格'  style="width: 190px" name="scheme_park_price" value="${parking.scheme_park_price}" >（元） </br>
						超过夜间停车时间：</br><input id = "scheme_exceed_night_price" type="text"  maxlength="20" placeholder='超过夜间停车时间'  style="width: 190px" name="scheme_exceed_night_price" value="${parking.scheme_exceed_night_price}" >（元/小时）
						&nbsp;</td>
				</tr>
				<tr>

					<td width="19%" bgcolor="#FFFDF0"><div align="center">代客泊车描述：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" id="parkPriceComment" name="parkPriceComment" value="${parking.parkPriceComment}">
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">单小时最大接单数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input type="text"  style="width: 190px" id="maxinumHour" name="maxinumHour"  value="${parking.maxinumHour}">
						&nbsp;
						<input type="button" value="查看当前小时的已接单数" onclick="queryCount(this, 'querySingleHourCount')">
					</td>
				</tr>

				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">选择对应车场：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input class="btn btn-default" type="button" id="choosePaking" value="选择对应车场" style="width: 138px" />
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">单日最大代泊数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id=maximumDay type="text"  style="width: 138px" name="maximumDay" value="${parking.maximumDay}" >
						&nbsp;
						<input type="button" value="查看当天的已接单数" onclick="queryCount(this, 'queryTodayCount')">
					</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">代泊服务开始时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input  type="text" id="parkBeginTime" name="parkBeginTime"  style="width: 138px" value="${parking.parkBeginTime}" placeholder="如 08:00" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" title="请输入正确的格式,如: 8:00"/>
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">代泊服务结束时间：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<input id=parkEndTime type="text"  style="width: 138px" name="parkEndTime" value="${parking.parkEndTime}" placeholder="如 18:00" pattern="^([01]\d|2[0-3]):?([0-5]\d)$" title="请输入正确的格式,如: 18:00"/>
						&nbsp;
					</td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">查看代泊员列表：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						<button onclick="queryParker('${parking.parkingId}')" style="width: 100px;" class="btn btn-default" data-toggle="modal" data-target="#parkerView">
							查看
						</button>
						<!-- 模态框（Modal） -->
						<div class="modal fade" id="parkerView" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								<table style="background-color: #ffffff;" width="100%" border="0" cellspacing="0" cellpadding="0">
									<thead>
									<tr>
										<td height="30" background="images/tab_05.gif">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="12" height="30"><img src="images/tab_03.gif" width="12" height="30" /></td>
													<td>
														<table width="100%" cellspacing="0" cellpadding="0" height="30">
															<tr>
																<td class="STYLE4"></td>
																<td class="STYLE4"></td>
																<td width="10%" align="right" class="STYLE4">
																	<button type="button" data-dismiss="modal">关闭</button>
																</td>
															</tr>
														</table>
													</td>
													<td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
												</tr>
											</table>
										</td>
									</tr>
									</thead>
									<tbody>
									<tr>
										<td>
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td width="8" background="images/tab_12.gif">&nbsp;</td>
													<td>
														<table id="parkerContent" width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
															<thead>
															<tr>
																<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">代泊员Id</span></div></td>
																<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">姓名</span></div></td>
																<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">手机号</span></div></td>
																<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">当班状态</span></div></td>
																<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">最后接单时间</span></div></td>
															</tr>
															</thead>
															<tbody>
															</tbody>
														</table>
													</td>
													<td width="8" background="images/tab_15.gif">&nbsp;</td>
												</tr>
											</table>
										</td>
									</tr>
									</tbody>
									<tfoot>
									<tr>
										<td height="32" background="images/tab_19.gif" style="text-align: right;"></td>
									</tr>
									</tfoot>
								</table>
							</div><!-- /.modal -->
						</div>
						<style>
							#parkerView {
								height: 90%;
								overflow-y: auto;
							}
							#parkerView tr, #parkerView td {
								border: 0;
							}
							#parkerContent td {
								border: 1px solid lightblue;
								text-align: center;
							}
						</style>
						<script type="text/javascript">
							function queryParker(parkingId) {
								var $tbody = $('#parkerContent tbody');
								$tbody.html("");
								var url = '<%=basePath%>/products/parking/queryParker?parkingId=' + parkingId;
								$.get(url, function (result) {
									result = $.parseJSON(result);
									if (result.errorNum == '0') {
										if (result.data.length == 0) {
											$tbody.append('<tr><td colspan="5">没有代泊员</td></tr>');
											return;
										}
										for (var i = 0; i < result.data.length; i++) {
											var item = result.data[i];
											var tr = '<tr>';
											tr += '<td>' + item.parkerId + '</td>';
											tr += '<td>' + item.parkerName + '</td>';
											tr += '<td>' + item.parkerMobile + '</td>';
											tr += item.state == 1 ? '<td style="color:green;">当班</td>' : '<td style="color:#ff0000;">休班</td>';
											tr += '<td>' + new Date(item.lastOperTime).toLocaleString() + '</td>';
											tr += '</tr>';
											$tbody.append(tr);
										}
									}
								});
							}
						</script>
					</td>
				</tr>
			</table>
		<%--	<div class="modal" id="mymodal"></div>--%>
		</div>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-transition.js"></script>
			<script src="http://cdn.bootcss.com/bootstrap/2.3.1/js/bootstrap-modal.js"></script>
			<script>
				$(function(){
				<%--/*	$("#choosePaking").click(function(){--%>
						 <%--$("#mymodal").load("products/parking/toChooseParkingPage.html?id=${parking.parkingId}").css({"height":"100%","min-height":"100px","max-height":"450","overflowY":"scroll"}).modal("show");--%>
					<%--});*/--%>
					<%--$(document).on('click',"#savePark",function(){--%>
						<%--var parkingId=$("#parkingId").val();--%>
						<%--$("#choosePaking").html("");--%>
						<%--$.each($("#mysTable input:checked"),function(i,obj){--%>
							<%--$("#choosePaking").append("<input type='hidden' name='parkingList' value='"+obj.value+"'/>");--%>
							<%--var parkingList=obj.values()--%>
						<%--});--%>
						<%--$("#mymodal").modal("hide");--%>
				<%--/*		$.ajax({--%>
							<%--type: "POST",--%>
							<%--url: "<%=basePath%>products/parking/updateParking.html",--%>
							<%--data:{"parkingId":parkingId,"parkingList":parkingList},--%>
							<%--dataType: "json",--%>
							<%--success: function (jsonStr) {--%>

							<%--}--%>
						<%--})*/--%>

					<%--});--%>
				/*	$(document).on('click',"#selectPark",function(){
						$.post("products/parking/get.html",{parkName:$("#p_queryValue").val()},function(data){
							$("#mysTable tr:gt(0)").remove();
							$.each(eval('('+data+')'),function(i,obj){
								var uu="<tr> <td height='22' style='width: 3%'><div align='center'><span class='STYLE1'>"+(i+1)+"</span></div></td> <td height='22' style='width: 8%'><div align='center'><span class='STYLE1'>"+obj.parkingName+"</span></div></td> <td height='22' style='width: 5%'><div align='center'><input type='checkbox'";
							//	var ui= " value='"+obj.parking_id+"' /></div></td> </tr>"
                                *//*  if(obj.parkingId!=null&&obj.parkingId!=''){
									uu += "checked = 'checked'";
								  }*//*
								uu +=  "value='"+obj.parkingId+"' /></div></td> </tr>"
								$("#mysTable").append(uu);
							});
						});
					});*/
				});
				$(function(){
					$("#choosePaking").click(function(){
						$("#mymodal").modal("toggle");
						$("#mymodal").css({"height":"80%","overflow":"auto"});
					});
				});
				function selectPark(){

				}
			</script>
			<div class="modal" id="mymodal">
			<div class="modal-dialog" style="width:700px;">

				<table width="100%" border="0" cellspacing="0" cellpadding="0" style="background: white;">
					<tr>
						<td height="30" background="images/tab_05.gif">
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="12" height="30"><img src="images/tab_03.gif" width="12" height="30" /></td>
									<td>
										<table width="100%" cellspacing="0" cellpadding="0" height="30">
											<tr>
												<td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input type="text" id="p_queryValue" name="p_queryValue" style="width: 290px"/></td>
												<td class="STYLE4">&nbsp;&nbsp;请选择查询方式：
													<select id="p_queryType" name="p_queryType" style="width: 100px">
														<option  value="1">车场名称</option>
													</select>
												</td>
												<td class="STYLE4">&nbsp;&nbsp;<input  type="button" onclick="selectDesc()" value="查询"  id="selectPark" style="width:50px"/></td>
											</tr>
										</table>
									</td>
									<td width="16"><img src="images/tab_07.gif" width="16" height="30" /></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr>
						<td>
							<table width="100%" height="100" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td width="8" background="images/tab_12.gif">&nbsp;</td>
									<td>
										<table width="100%" border="0" id="mysTable" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
											<tr>
												<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 3%"><div align="center"><span class="STYLE1">序号</span></div></td>
												<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width: 8%"><div align="center"><span class="STYLE1">车场名称</span></div></td>
												<td height="22" background="images/bg2.gif" bgcolor="#FFFFFF" style="width:5%"><div align="center"><span class="STYLE1">选择</span></div></td>
											</tr>

											<c:forEach items="${ listParking}" var="parking" varStatus="i">
												<tr>
													<td height="22" style="width: 3%"><div align="center"><span class="STYLE1">${i.index + 1 }</span></div></td>
													<td height="22" style="width: 8%"><div align="center"><span class="STYLE1 desc">${parking.parking_name }</span></div></td>
													<td height="22" style="width: 5%"><div align="center"><input type="checkbox" <c:if test="${parking.parkingId!=null&&parking.parkingId!='' }">checked="checked" </c:if> name="parkingList" value="${parking.parking_id }" /></div></td>
												</tr>
											</c:forEach>

										</table>
									</td>
									<td width="8" background="images/tab_15.gif">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td height="32" background="images/tab_19.gif" style="text-align: right;">
							<button type="button" id="savePark" style="width: 50px;margin-top: -8px;"  data-dismiss="modal">确定</button>
							<button type="button" style="width: 50px;margin-top: -8px;margin-right: 10px;" data-dismiss="modal">关闭</button>
						</td>
					</tr>
				</table>
			</div>
				</div>
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%"
				align=center border=0>
				<tr bgcolor="#ECF3FD">
					<td width="36%"></td>
					<td width="17%"><input type="submit" value="提交"></td>
					<td width="4%"><input type="button" name="button"  onClick="history.back() "  value="返回"></td>
					<td width="43%"></td>
				</tr>
			</table>
		</form>
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

			function queryCount(element, operation) {
				var url = '<%=basePath%>products/parking/' + operation + '?parkingId=${parking.parkingId}';
				$.get(url, function (result) {
					result = $.parseJSON(result);
					if (result.errorNum == '0') {
						if (operation == 'queryTodayCount') {
							alert('当日已经' + result.data + '单');
						} else if (operation == 'querySingleHourCount') {
							alert('单小时已接' + result.data + '单');
						}
					}
				});
			}
		</script>
	</body>
</html>
