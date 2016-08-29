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
		<title>大地保险详细信息</title>
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
			<table class=editTable cellSpacing=1 cellPadding=0 width="100%" align=center border=0>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>
						大地保险详细信息
					</td>
				</tr>
				<tr>
			<td width="19%" bgcolor="#FFFDF0"><div align="center">商业险保费：</div></td>
			<td colspan="6" bgcolor="#FFFFFF">
						 ${ccicOrderInfo.commercialInsurePremium}
					</td>
				</tr>
				<tr>
					<%--<td bgcolor="#FFFDF0"><div align="center">保障条款：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${insuranceList}"
					</td>--%>

<tr>
	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">险种代码</span></div></td>
	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">险种名称 </span></div></td>
	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">险种保额 </span></div></td>
	<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">险种保费</span></div></td>
	<td colspan="2" height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 20%"><div align="center"><span class="STYLE1">不计免赔标志 </span></div></td>
</tr>
		<c:forEach items="${ccicInsure}" var="row" varStatus="status">
			<tr>
				<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.insureCode}</span></div></td>
				<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.insureName}</span></div></td>
				<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center" ><span class="STYLE1">${row.insureAmount}</span></div></td>
				<td height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center" ><span class="STYLE1">${row.insurePremium}</span></div></td>
				<td colspan="2" height="20" bgcolor="#FFFFFF" style="width:  20%"><div align="center"><span class="STYLE1">${row.insureFlag}</span></div></td>
			</tr>
		</c:forEach>
				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>交强险信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">交强险保费(不含车船税)：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.compulsoryInsurePremium}
					</td>
					<%--（1：待入场 2：已入场）--%>
					<td bgcolor="#FFFDF0"><div align="center">车船税：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						${ccicOrderInfo.compulsoryTravelTax}
					</td>
				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>车辆信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车辆VIN码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.vin}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">车牌号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.licenseNo}
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车辆行驶城市：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.carCity}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">车辆行驶省份：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.carProvince}
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">车辆型号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.carType}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">发动机号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.engineNo}
					</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">排量：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.displacement}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">初登日期：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.firstRegisterDate}
					</td>
				</tr>

				<tr>

					<td width="19%" bgcolor="#FFFDF0"><div align="center">座位数：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.seatNumber}
					</td>
					<td bgcolor="#FFFDF0"><div align="center">新车购置价：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.purchasePrice}
					</td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">是否为过户车标志：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.transferFlag}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">过户日期：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.transferDate}
						</td>

				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7> 投保人信息 </td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">投保人姓名：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.applicantName}
						</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">投保人证件类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.applicantIdentifyType}
						</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">投保人证件号码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.applicantIdentifyNumber}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">投保人手机号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.applicantMobile}
						&nbsp;</td>

				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">投保人邮箱地址：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						${ccicOrderInfo.applicantEmail}
					</td>

				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>被保人信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">被保人姓名：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.insuredName}
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">被保人证件类型：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.insuredIdentifyType}
					</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">被保人证件号码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.insuredIdentifyNumber}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">被保人手机号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.insuredMobile}
						&nbsp;</td>

				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">被保人邮箱地址：</div></td>
					<td colspan="6" bgcolor="#FFFFFF">
						${ccicOrderInfo.insuredEmail}
					</td>

				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>配送信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">配送人姓名：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.receiverName}
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">配送人号码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.receiverMobile}
					</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">配送人地址：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicOrderInfo.receiverAddress}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center"></div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						&nbsp;</td>
				</tr>
				<tr class=editHeaderTr>
					<td class=editHeaderTd colSpan=7>客户信息</td>
				</tr>
				<tr>
					<td bgcolor="#FFFDF0"><div align="center">用户名称：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.name}
					</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">手机号码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.mobile}
					</td>

				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">电子邮件地址：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.email}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">车牌号：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.carLicence}</td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">车辆行驶城市代码：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.cityCode}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">车辆登记日期：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.registerDate}</td>
				</tr>
				<tr>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">商业险生效日期：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.bizBeginDate}
						&nbsp;</td>
					<td width="19%" bgcolor="#FFFDF0"><div align="center">交强险生效日期：</div></td>
					<td colspan="3" bgcolor="#FFFFFF">
						${ccicCustomer.forBeginDate}</td>
				</tr>
				</table>
			</div>
	</body>
</html>
