<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>查询大地保险信息</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
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
		#select{padding-left:130px;
			box-sizing:border-box;}

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

	<script>
		var  highlightcolor='#c1ebff';
		//此处clickcolor只能用win系统颜色代码才能成功,如果用#xxxxxx的代码就不行,还没搞清楚为什么:(
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
<form  action="<%=basePath%>products/ccic/list.html" method="post">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td height="30" background="<%=imagePath%>tab_0501.gif">
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="60" /></td>
						<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0" height="60">
								<tr>
									<td class="STYLE4" align="center">&nbsp;&nbsp;&nbsp;投保单号：
								<input type="text"  name="p_insuranceApplicantNo" id="p_insuranceApplicantNo" value='${page.params["insuranceApplicantNo"] }' style="width: 130px"/>
							</td>
								<td class="STYLE4">&nbsp;&nbsp;被投保单号：
									<input type="text"  name="p_policyNo" id="p_policyNo" value='${page.params["policyNo"] }' style="width: 130px"/>
								</td>
								<td class="STYLE4">&nbsp;&nbsp;
									<%--<input type="text"  name="customerName" id="customerName" value='${page.params["customer_nickname"] }' style="width: 130px"/>--%>
								</td>
								<td class="STYLE4"><input  type="submit" value="查询" style="width:90px"/></td>
							<%--	<td class="STYLE4"><input  type="button" name="reset" value="重置" onclick="reset1()" style="width:90px"/></td>--%>
								</tr>
								<tr>
									<td class="STYLE4" align="center">&nbsp;&nbsp;&nbsp; 车牌号：
										<input type="text" name="p_licenseNo" id="p_licenseNo"  value='${page.params["licenseNo"] }' style="width: 130px"   /></td>

									<td class="STYLE4" > &nbsp;&nbsp;&nbsp;&nbsp;订单号：
										<input type="text" name="p_utmsn" id="p_utmsn"  value='${page.params["utmsn"] }' style="width: 130px"   />
									</td>

								</tr>
							</table>
						</td>
						<td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="60" /></td>
					</tr>
				</table>

				<table width="100%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
						<td>
							<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"  onmouseout="changeback()">
								<tr>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">投保单号</span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">起保日期 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">商机进入时间 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">车牌号</span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">总保费 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">保单号</span></div></td>
									<%--<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">保单生效时间</span></div></td>--%>
									<%--<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">投保单类型</span></div></td>--%>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">合作方订单号 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">商业险保费 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">交强险保费 </span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">车船税</span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">支付时间</span></div></td>
									<td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width: 5%"><div align="center"><span class="STYLE1">支付申请号</span></div></td>
								</tr>
								<c:choose>
									<c:when test="${fn:length(page.resultList)>0}">
										<c:forEach items="${page.resultList}" var="row" varStatus="status">
											<tr>
												<td height="20" bgcolor="#FFFFFF" style="width:  3%"><div align="center"><span class="STYLE1"><a href="<%=basePath%>products/ccic/view.html?id=${row.id}">${row.insuranceApplicantNo}</a></span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.startDate}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center" ><span class="STYLE1">${row.chanceCreateTime}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center" ><span class="STYLE1">${row.licenseNo}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.premium}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.policyNo}</span></div></td>
												<%--<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.effectDate}</span></div></td>--%>
												<%--<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center" ><span  class="STYLE1">${row.insuranceType}</span></div></td>--%>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center" ><span  class="STYLE1">${row.utmsn}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.commercialInsurePremium}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.compulsoryInsurePremium}</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  5%"><div align="center"><span class="STYLE1">${row.compulsoryTravelTax }</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center" ><span class="STYLE1">${row.payTime }</span></div></td>
												<td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center" ><span class="STYLE1">${row.paymentNo}</span></div></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td height="20" bgcolor="#FFFFFF" colspan="21"  align="center">
												<div align="center"><span class="STYLE1">没有订单信息</span></div>
											</td>
										</tr>
									</c:otherwise>
								</c:choose>

							</table>
						</td>
						<td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="35" background="<%=imagePath%>tab_19.gif">
				<jsp:include page="/frame/page.html" />
			</td>
		</tr>

	</table>
</form>
</body>
</html>
