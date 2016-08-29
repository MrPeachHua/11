<%@page import="com.boxiang.share.system.po.Dictionary"%>
<%@page import="com.boxiang.share.product.coupon.po.Coupon"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>优惠券</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>js/FormValid.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<%
	 	List<Dictionary> dict1 = (List<Dictionary>)request.getAttribute("dicList1");
		List<Dictionary> dict2 = (List<Dictionary>)request.getAttribute("dicList2");
		List<Dictionary> dict3 = (List<Dictionary>)request.getAttribute("dicList3");
		List<Dictionary> dict4 = (List<Dictionary>)request.getAttribute("dicList4");
	 %>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.STYLE1 {
	font-size: 12px
}

.STYLE3 {
	font-size: 12px;
	font-weight: bold;
}

.STYLE4 {
	color: #03515d;
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #033d61;
	font-size: 12px;
}

A:hover {
	COLOR: #f60;
	TEXT-DECORATION: underline
}

.content {
	display: none;
	position: absolute;
	top: 0%;
	left: 20%;
	width: 642px;
	/* width: 50%; */
	/* height: 100%; */
	/* border: 1px solid black; */
	background-color: #E3F0F8;
	z-index: 1002;
	overflow: auto;
}
.contentwa {
	display: none;
	position: absolute;
	top: 8%;
	left: 12%;
	width: 80%;
	height: 90%;
	/* border: 1px solid black; */
	background-color: white;
	z-index: 1500;
	overflow: auto;
}
#bg {
	background-color: #666;
	position: absolute;
	z-index: 99;
	left: 0;
	top: 0;
	display: none;
	width: 100%;
	height: 100%;
	opacity: 0.001;
	filter: alpha(opacity = 50);
}

.table_border tr td {
	border: solid 1px #B5D6E6;
}

.table_border {
	border: solid 1px #B5D6E6;
}
</style>
<script type="text/javascript">
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
$(document).ready(function(){
	if($("#select2").val()!=null){
		 $("#couponKind").val($("#select2").val());
	}
	$("#exchangeCode").click(function(){
		document.getElementById('exchangeCodeDiv').style.display='block';
		$("#tokenTicketDiv").hide();
		$("#tokenTicket").css("color","");
		$("#exchangeCode").css("color","#2C9DC8");
	});
	$("#tokenTicket").click(function(){
		document.getElementById('tokenTicketDiv').style.display='block'
		$("#tokenTicket").css("color","#2C9DC8");
		$("#exchangeCode").css("color","");
		$("#exchangeCodeDiv").hide();
	});
	$("#cancel").click(function(){
		$("#addCoupon").hide();
		$("#bg").css("display", "none");
		$("#exchangeCodeDiv").hide();
		$("#tokenTicketDiv").show();
		$("#exchangeCodeDiv :input[type='text']").val('');
		$("#tokenTicketDiv :input[type='text']").val('');
		$("#form_couponType").val("");
		$("#issueReason").val("");
		$("#exclusiveRule").val("");
		$("#infoText").val('');
		$("#exchangeCode").css("color","");
		$("#tokenTicket").css("color","#2C9DC8");
		$("#form_vailday1").click();
		$("#form_typeRadio1").click();
	});
	$("#form_typeRadio1").click(function(){
		$("#radio2").css("display", "none");
		$("#radio3").css("display", "none");
		$("#radio1").css("display", "");
		$("#radio4").css("display", "");
	});
	$("#form_typeRadio2").click(function(){
		$("#radio1").css("display", "none");
		$("#radio4").css("display", "none");
		$("#radio2").css("display", "");
		$("#radio3").css("display", "");
	});
	$("#form_vailday1").click(function(){
		$("#form_beginTime2").attr("disabled",true);
		$("#form_endTime2").attr("disabled",true);
		$("#form_beginTime1").attr("disabled",false);
		$("#form_endTime1").attr("disabled",false);
		$("#dayTimeNum").attr("disabled",false);
		$("#form_beginTime2").val("");
		$("#form_endTime2").val("");
	});
	$("#form_vailday2").click(function(){
		$("#form_beginTime2").attr("disabled",false);
		$("#form_endTime2").attr("disabled",false);
		$("#form_beginTime1").attr("disabled",true);
		$("#form_endTime1").attr("disabled",true);
		$("#dayTimeNum").attr("disabled",true);
		$("#form_beginTime1").val("");
		$("#form_endTime1").val("");
		$("#dayTimeNum").val("");
	});
	$("#saveCoupon").click(function(){

		if($("#form_vouchersName").val().length==0||$("#form_vouchersName").val().length==null){
			alert("优惠券名称不能为空！");return;
		}
		if($("#form_parAmount1").val().length==0||$("#form_vouchersName").val().length==null){
			alert("面值不能为空！");return;
		}
		if($("#form_couponType").val()==""){
			alert("请选择优惠券种类");return;
		}
		if($("#issueReason").val()==0){
			alert("请选择发放理由");return;
		}
		if($("#form_vailday1")[0].checked){
			if($("#form_beginTime1").val().length==0 || $("#form_endTime1").val().length==0 ||$("#dayTimeNum").val().length==0 ){
				alert("带领取的有效期不能为空！");return;
			}
		}else{
			if($("#form_beginTime2").val().length==0 || $("#form_endTime2").val().length==0){
				alert("一般使用有效期不能为空！");return;
			}
		}
		if (!confirm("定额券面值大于限用最低消费,但仍然可以添加！")) return;
		$("#tokenTicketForm").submit();

	});

	<%
		for(int i=0;i<dict1.size();i++){%>
		if($("#select1").val()==<%=dict1.get(i).getDictValue()%>){
			$("#vouchersStatus").append("<option selected='selected' value='<%=dict1.get(i).getDictValue()%>'><%=dict1.get(i).getDictName()%></option>");
		}else{
			$("#vouchersStatus").append("<option value='<%=dict1.get(i).getDictValue()%>'><%=dict1.get(i).getDictName()%></option>");
		}
	<%}
	for(int i=0;i<dict3.size();i++){%>
	<%-- if($("#select2").val()==<%=dict3.get(i).getDictValue()%>){
		$("#couponKind").append("<option selected='selected' value='<%=dict3.get(i).getDictValue()%>'><%=dict3.get(i).getDictName()%></option>");
	}else{
		$("#couponKind").append("<option value='<%=dict3.get(i).getDictValue()%>'><%=dict3.get(i).getDictName()%></option>");
	} --%>
<%}
	for(int i=0;i<dict3.size();i++){%>
		$("#form_couponType").append("<option value='<%=dict3.get(i).getDictValue()%>'><%=dict3.get(i).getDictName()%></option>");
	<%}
%>
<%
	for(int i = 0;i<dict4.size();i++){%>
	$("#issueReason").append("<option value='<%=dict4.get(i).getDictValue()%>'><%=dict4.get(i).getDictName()%></option>");
	<%}
%>
});

/* 导出excel表 */
function excelExport(){
	var couponStatus = $("#select1").val();
	var couponKind = $("#select2").val();
	var couponId =$("#couponId").val();
	var vouchersName = $("#vouchersName").val();
	var creator = $("#creator").val();
	var createBeginTime = $("#createBeginTime").val();
	var createEndTime = $("#createEndTime").val();
	window.location="<%=basePath%>products/coupon/excelExport.html?couponId="+couponId+"&vouchersName="+vouchersName+"&couponStatus="+couponStatus+"&couponKind="+couponKind+"&creator="+creator+"&createBeginTime="+createBeginTime+"&createEndTime="+createEndTime;
}
function marin(){
	var couponName=$("#form_vouchersName").val();
	$.ajax({
		cache: false,
		type: "POST",
		url:'products/coupon/getCouponName.html',
		data:{"couponName":couponName},
		dataType: "json",
		success: function (jsonStr) {
       if(jsonStr == '0'){
		  alert("存在相同的优惠券名称！但是也可以继续发放，望三思！")
	   }
		}
	});
}
function add(){
	document.getElementById('addCoupon').style.display='block';
	$("#bg").css({
        display: "block"
    });
}
function parkingPop(){
	var queryValue=$("#queryValue").val();
	var queryType = $("#queryType").val();
	document.getElementById('parkingForm').style.display='block';
	$.ajax({
		type: "POST",
		url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
		data:{"queryValue":queryValue,"queryType":queryType},
		dataType: "json",
		success: function (jsonStr) {
			$(".parkingListTr").remove();
			var parkings = $("#parkingId").val();
			for(var i=0;i<jsonStr.length;i++){
				if(parkings.indexOf(jsonStr[i].parkingId)>=0){
					var checkStatus = '<td><input type="checkbox" checked="checked" name="'+jsonStr[i].parkingName+'" value='+jsonStr[i].parkingId+'></td>';
				}else{
					var checkStatus = '<td><input type="checkbox"  name="'+jsonStr[i].parkingName+'" value='+jsonStr[i].parkingId+'></td>';
				}
				$("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center">'+checkStatus+'<td bgcolor="#FFFFFF">'+jsonStr[i].parkingId+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingName+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingArea+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingAddress+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCount+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCanUse+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingStatus+'</td>'+
				<%-- <td bgcolor="#FFFFFF">'+'<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\''+jsonStr[i].parkingName+'\',\''+jsonStr[i].parkingId+'\')">选择</span></td> --%>
				'</tr>');
			}
		}
	});
	$("#bg").css({
		display: "block"
	});
	$(".content").css({
		display: "block"
	});
}
function choiceParkingCode(text1,text2){
	var pkcode =
			$("#parkingCode").val(text1);
	$("#parkingId").val(text2);
	$("#parkingForm").hide();
	$(".parkingListTr").remove();
	$("#bg").css("display", "none");
	$(".content").css("display", "none");
}

function cancelButton(){
	$("#parkingForm").hide();
	$(".parkingListTr").remove();
	$("#bg").css("display", "none");
	$(".content").css("display", "block");
}
function chooseAll(){
	if($("#chooseAllBox").is(':checked')){
		$("#mytable input:checkbox").prop('checked', true);

	}else{
		$("#mytable input:checkbox").prop('checked', false);
	}
}
function confButton(){
	//获取parkingId
	var strName = "";
	var strId = "";
	$("#mytable input:checkbox:checked:not('#chooseAllBox')").each(function(index,element){
		if($(this).val()!=""){
			strId+=$(this).val();
			strId+=",";
		}
		if($(this).attr("name")!=""){
			strName += $(this).attr("name");
			strName += ",";
		}
	})
	if(strId!=""){
		strId = strId.substring(0,strId.length-1);
	}
	if(strName!=""){
		strName = strName.substring(0,strName.length-1);
	}
	$("#parkingCode").val(strName);
	$("#parkingId").val(strId);
	$("#parkingForm").hide();
	$(".parkingListTr").remove();
	$("#bg").css("display", "none");
	$(".content").css("display", "block");
}
</script>
</head>
<body>
	<div style="height: 100px;">
		<form action="<%=basePath%>products/coupon/list.html" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" background="<%=imagePath%>tab_0501.gif">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="12" height="30"><img
									src="<%=imagePath%>tab_03.gif" width="12" height="60" /></td>
								<td>
									<table width="100%" border="0" cellspacing="0" cellpadding="0"
										height="60">
										<tr>
											<td class="STYLE4" align="center">&nbsp;优惠券状态： <%-- <input type="text" name="vouchersStatus" value = "${page.params["dictCode"] }" style="width: 130px"/> --%>
												<select id="vouchersStatus" name="couponStatus"
												style="width: 130px">
													<option value=""></option>
											</select> <input type="hidden" id="select1"
												value="${page.params['couponStatus'] }" />
											</td>
											<td class="STYLE4">&nbsp;优惠券种类： <select id="couponKind"
												name="couponKind" style="width: 130px">
													<option value=""></option>
													<c:forEach items="${dicList3 }" var="dict">
														<option value="${dict.dictValue }">${dict.dictName}</option>
													</c:forEach>
											</select> <input type="hidden" id="select2"
												value="${page.params['couponKind'] }" />

											</td>
											<td class="STYLE4">&nbsp;&nbsp;券(活动)名称： <input
												type="text" name="vouchersName" id="vouchersName"
												value='${page.params["vouchersName"] }' style="width: 130px" />
											</td>
											<td class="STYLE4"><input type="submit" value="查询"
												style="width: 90px" /></td>
											<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_ADD_0014')"><input type="button" onclick="add()"
												value="发放优惠券" style="width: 90px" /></security:authorize></td>
											<td class="STYLE4"><security:authorize access="hasAnyRole('AUTH_EXP_0015')"><input type="button"
												onclick="excelExport()" value="导出Excel表" style="width: 90px" /></security:authorize></td>
										</tr>
										<tr>
											<td class="STYLE4" align="center">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发放人：
												<input type="text" name="creator" id="creator"
												value='${page.params["creator"] }' style="width: 130px" />
											</td>
											<td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;发放时间： <input
												type="text" placeholder="开始时间" id="createBeginTime"
												name="createBeginTime" value='${page.params["startTime"] }'
												style="width: 130px" readonly="readonly"
												onclick="WdatePicker({el:createBeginTime,dateFmt:'yyyy-MM-dd'})" />
												至 <input type="text" placeholder="截止时间" id="createEndTime"
												name="createEndTime" style="width: 130px"
												readonly="readonly" value='${page.params["stopTime"] }'
												onclick="WdatePicker({el:createEndTime,dateFmt:'yyyy-MM-dd'})" />
											</td>
											<td class="STYLE4">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;优惠券ID：
												<input type="text"name="couponId" id="couponId" style="width: 130px" value='${page.params["couponId"]}'/>
											</td>
										</tr>
									</table>
								</td>
								<td width="16"><img src="<%=imagePath%>tab_07.gif"
									width="16" height="60" /></td>
							</tr>
						</table>
					</td>
				</tr>

				<tr>
					<td>
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
								<td>
									<table width="100%" border="0" cellpadding="0" cellspacing="1"
										bgcolor="b5d6e6" onmouseover="changeto()"
										onmouseout="changeback()">
										<tr>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 8%"><div align="center">
													<span class="STYLE1">优惠券ID</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 8%"><div align="center">
													<span class="STYLE1">券(活动)名称</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 7%"><div align="center">
													<span class="STYLE1">优惠券种类</span>
												</div></td>
											<%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:7%"><div align="center"><span class="STYLE1">优惠券类型</span></div></td> --%>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 6%"><div align="center">
													<span class="STYLE1">发放理由</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 5%"><div align="center">
													<span class="STYLE1">面值(元)</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 5%"><div align="center">
													<span class="STYLE1">面值(折)</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 9%"><div align="center">
													<span class="STYLE1">激活有效时间</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 9%"><div align="center">
													<span class="STYLE1">使用有效时间</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 6%"><div align="center">
													<span class="STYLE1">使用时间</span>
												</div></td>
											<%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:6%"><div align="center"><span class="STYLE1">抵扣金额</span></div></td> --%>
											<%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:8%"><div align="center"><span class="STYLE1">用户手机号</span></div></td> --%>
											<%-- <td height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="width:10%"><div align="center"><span class="STYLE1">说明</span></div></td> --%>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 8%"><div align="center">
													<span class="STYLE1">发放人</span>
												</div></td>
											<td height="22" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" style="width: 8%"><div align="center">
													<span class="STYLE1">发放时间</span>
												</div></td>
											<%--  <td <c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if> height="22" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1" style="width: 16%"><div align="center">基本操作</div></td> --%>
										</tr>
										<c:choose>
											<c:when test="${fn:length(page.resultList)>0}">
												<c:forEach items="${page.resultList}" var="row"
													varStatus="status">
													<tr>
														<td height="20" bgcolor="#FFFFFF" style="width: 8%"><div
																align="center">
																<span class="STYLE1"><a
																	href="<%=basePath%>products/coupon/couponDetail.html?couponId=${row.couponId}">${row.couponId}</a></span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 8%"><div
																align="center">
																<span class="STYLE1">${row.vouchersName}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 7%"><div
																align="center">
																<span class="STYLE1"> <c:if
																		test="${row.couponKind == '1'}">
					停车券
				</c:if> <c:if test="${row.couponKind == '0'}">
					通用券
				</c:if> <c:if test="${row.couponKind == '2'}">
					月租产权券
				</c:if> <c:if test="${row.couponKind == '3'}">
					代泊券
				</c:if> <c:if test="${row.couponKind =='4'}">
						车管家券
				</c:if>
																	 <c:if test="${row.couponKind =='5'}">
																		 洗车券
																	 </c:if>
																</span>
															</div></td>
														<%-- <td height="20" bgcolor="#FFFFFF" style="width:  7%"><div align="center"><span class="STYLE1">
				<c:if test="${row.couponType == 1}">
					定额
				</c:if>
				<c:if test="${row.couponType == 2}">
					折扣
				</c:if>
	  	  </span></div></td> --%>
														<td height="20" bgcolor="#FFFFFF" style="width: 6%"><div
																align="center">
																<span class="STYLE1"> <c:if
																		test="${row.channel == 1}">
					注册
				</c:if> <c:if test="${row.channel == 2}">
					交易
				</c:if> <c:if test="${row.channel == 3}">
					活动
				</c:if> <c:if test="${row.channel == 4}">
					分享
				</c:if>
																</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 5%"><div
																align="center">
																<span class="STYLE1">${row.parAmount}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 5%"><div
																align="center">
																<span class="STYLE1">${row.parDiscount}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 9%"><div
																align="center">
																<span class="STYLE1">${row.receiveBegin}/${row.receiveEnd}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 9%"><div
																align="center">
																<span class="STYLE1">${row.effectiveBegin}/${row.effectiveEnd}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 8%"><div
																align="center">
																<span class="STYLE1">${row.useTime}</span>
															</div></td>
														<%-- <td height="20" bgcolor="#FFFFFF" style="width:  6%"><div align="center"><span class="STYLE1">${row.discount}</span></div></td> --%>
														<%-- <td height="20" bgcolor="#FFFFFF" style="width:  8%"><div align="center"><span class="STYLE1">${row.customerMobile}</span></div></td> --%>
														<%-- <td height="20" bgcolor="#FFFFFF" style="width:  10%"><div align="center"><span class="STYLE1">${row.info}</span></div></td> --%>
														<td height="20" bgcolor="#FFFFFF" style="width: 8%"><div
																align="center">
																<span class="STYLE1">${row.creator}</span>
															</div></td>
														<td height="20" bgcolor="#FFFFFF" style="width: 8%"><div
																align="center">
																<span class="STYLE1">${row.createTime}</span>
															</div></td>
														<%-- 	<td <c:if test="${shareUser.rolePower > 2}"> style=" display: none  "  </c:if> height="20" bgcolor="#FFFFFF" style="width: 16%"><div align="center"><span class="STYLE4">
            	<img src="<%=imagePath%>del.gif" width="16" height="16" />
            	<a href="<%=basePath %>products/coupon/${row.couponId}/del.html">删除</a></span></div>
            </td>  --%>
													</tr>
												</c:forEach>
											</c:when>
											<c:otherwise>
												<tr>
													<td height="20" bgcolor="#FFFFFF" colspan="21"
														align="center">
														<div align="center">
															<span class="STYLE1">没有优惠券相关信息</span>
														</div>
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
					<td height="35" background="<%=imagePath%>tab_19.gif"><jsp:include
							page="/frame/page.html" /></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 弹出层的开始 -->
	<div id="bg"></div>
	<div class="contentwa" id="parkingForm">
		<form action="<%-- <%=basePath%>products/parking/list.html --%>"
			  method="post">

			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td height="30" margin="0" padding="0"background="<%=imagePath%>tab_05.gif"><table
							width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30"><img
									src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
							<td>
								<table  width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4" align="center">&nbsp;&nbsp;请输入查询内容：<input
												type="text" name="queryValue" id="queryValue"style="width: 290px" /></td>
										<td class="STYLE4">&nbsp;&nbsp;请选择查询方式：<select
												name="queryType"  id="queryType" style="width: 100px">
											<option value="1">车场代码</option>
											<option value="2">车场名称</option>
											<option value="3">车场地址</option>
										</select>
										</td>
										<td class="STYLE4">&nbsp;&nbsp; <input type="button" onclick="parkingPop()"
																			   value="查询" style="width: 50px" />
										</td>
										<td class="STYLE4">&nbsp;&nbsp; <input type="button"
																			   value="取消" style="width: 50px" onclick="cancelButton()"/>
										</td>
										<td class="STYLE4">&nbsp;&nbsp; <input type="button"
																			   value="确定" style="width: 50px" onclick="confButton()"/>
										</td>
									</tr>
								</table>
							</td>
							<td width="16"><img src="<%=imagePath%>tab_07.gif"
												width="16" height="30" /></td>
						</tr>
					</table></td>
				</tr>

				<tr>
					<td><table width="100%" border="0" cellspacing="0"
							   cellpadding="0">
						<tr>
							<td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
							<td><table class="table_border" id="mytable" width="100%" cellpadding="0"
									   cellspacing="0"  onmouseover="changeto()"
									   onmouseout="changeback()">
								<tr>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 10%; height: 22px;">
										<div align="center">
											<span class="STYLE1"><input id="chooseAllBox" onclick="chooseAll()" type="checkbox">全选</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 10%; height: 22px;">
										<div align="center">
											<span class="STYLE1">车场代码</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 15%; height: 22px;">
										<div align="center">
											<span class="STYLE1">车场名称</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 15%; height: 22px;">
										<div align="center">
											<span class="STYLE1">小区</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 20%; height: 22px;">
										<div align="center">
											<span class="STYLE1">地址</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 10%; height: 22px;">
										<div align="center">
											<span class="STYLE1">车位数</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 10%; height: 22px;">
										<div align="center">
											<span class="STYLE1">空位数</span>
										</div>
									</td>
									<td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF"
										style="width: 10%; height: 22px;">
										<div align="center">
											<span class="STYLE1">车位状态</span>
										</div>
									</td>
									<!-- <td width="10%" background="<%=imagePath%>bg2.gif"
												bgcolor="#FFFFFF" class="STYLE1"
												style="width: 10%; height: 22px;">
												<div align="center">基本操作</div>
											</td> -->
								</tr>
							</table></td>
							<td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
						</tr>
					</table></td>
				</tr>
				<tr><td height="35" background="<%=imagePath%>tab_19.gif"><%-- <jsp:include page="/frame/page.html" /> --%></td></tr>
			</table>
		</form>
	</div>
	<div class="content" id="addCoupon">
		<div>
			<!-- 弹出层名称 -->
			<div style="margin-left: 10px; margin-top: 15px;">
				<span style="font-size: 20px; color: #2C9DC8; font-weight: bold;">优惠券添加</span>
			</div>
			<!-- div头   按钮 -->
			<div style="margin-top: 12px;">
				<div style="margin-left: 30px; margin-right: 40px; float: left;">
					<!-- <input type="button" style="width: 110px;height:25px; text-align: center;color:#2C9DC8" value="通用券" id="tokenTicket"/> -->
				</div>
				<div style="float: left;">
					<!-- <input type="button" style="height:25px;width: 110px;"value="CDKEY兑换码" id="exchangeCode"/> -->
				</div>
				<div style="float: right; margin-right: 30px;">
					<input type="button"
						style="width: 110px; height: 25px; text-align: center;" value="取消"
						id="cancel" />
				</div>
			</div>
			<!-- 根据不同的选项得到不同的form表单 -->
			<div style="margin-top: 55px; margin-left: 30px;">
				<!-- 通用券的form -->
				<div id="tokenTicketDiv" style="">
					<form method="post" onsubmit="return validator(this)"
						name="tokenTicketForm"
						action="<%=basePath%>products/coupon/insertCoupon.html"
						id="tokenTicketForm">
						<table class="table_border" cellpadding="2" cellspacing="0"
							style="width: 583px;">
							<tr>
							<td width="120px;">优惠券种类:</td>
							<td><select style="width: 145px;" name="form_couponType"
										id="form_couponType">
								<option value="">请选择优惠券种类</option>
							</select> <span style="color: red; float: right">按优惠券种类</span></td>
						</tr>
							<tr>
								<td>券名:</td>
								<td><input placeholder="券名"  style="width: 145px;"
									type="text" value="" id="form_vouchersName"
									name="form_vouchersName" onblur="marin()" /> <span
									style="color: red; float: right">可以是中文、英文、数字及其组合</span></td>

							</tr>
							<tr>
								<td>适用车场:</td>
								<td><input placeholder="适用车场(非必填)"  style="width: 145px;"
										   type="text"    readonly="readonly"
										   onclick="parkingPop()"  id="parkingCode"
										   name="parkingName"/> <span
										style="color: red; float: right">优惠券适用的车场，不填则全部车场通用</span></td>
								<input type="hidden" name = "parkingId" id="parkingId" />

							</tr>
							<tr>
								<td>发放张数:</td>
								<td><input placeholder="发放张数" valid="required"
									errmsg="优惠券发放张数不能为空!"
									onkeyup="value=value.replace(/[^\d]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))"
									style="width: 145px;" type="text" value=""
									id="form_vouchersNum" name="form_vouchersNum" />&nbsp;&nbsp;张
									<span style="color: red; float: right">输入发放优惠券的张数</span></td>
							</tr>
							<tr>
								<td>发放原因:</td>
								<td><select id="issueReason" name="issueReason"
									style="width: 145px;">
										<option value="0">请选择发放理由</option>
								</select> <span style="color: red; float: right">选择此优惠券发放理由</span></td>
							</tr>
							<tr>
								<td>类型:</td>
								<td><input type="radio" name="form_typeRadio" value="1"
									id="form_typeRadio1" checked /> 定额 <input type="radio"
									name="form_typeRadio" value="2" id="form_typeRadio2" /> 折扣 <span
									style="color: red; float: right">根据类型的选择，面值那一栏显示“元”或“折”</span>
								</td>
							</tr>

							<tr id="radio1">
								<td>面值:</td>
								<td><input placeholder="面值" type="text"
									onkeyup="value=value.replace(/[^\d\.]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))"
									name="form_parAmount1" style="width: 145px;"
									id="form_parAmount1" />&nbsp;&nbsp;元 <span
									style="color: red; float: right">输入定额券的面值</span></td>
							</tr>
							<tr id="radio2" style="display: none">
								<td>面值:</td>
								<td><input placeholder="面值" type="text"
									onkeyup="value=value.replace(/[^\d\.]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))"
									name="form_parAmount2" style="width: 145px;"
									id="form_parAmount2" />&nbsp;&nbsp;折 <span
									style="color: red; float: right">输入折扣券的面值</span></td>
							</tr>
							<tr id="radio3" style="display: none">
								<td>最高折扣:</td>
								<td><input placeholder="最高折扣" type="text"
									onkeyup="value=value.replace(/[^\d\.]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))"
									name="form_maxDiscount" style="width: 145px;"
									id="form_maxDiscount" />&nbsp;&nbsp;元 <span
									style="color: red; float: right">折扣券</span></td>
							</tr>
							<tr id="radio4">
								<td>限用最低消费:</td>
								<td>满<input placeholder="最低消费"
									onkeyup="value=value.replace(/[^\d\.]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))"
									type="text" name="form_limitFee" id="form_limitFee"
									style="width: 133px;" />&nbsp;&nbsp;元 <span
									style="color: red; float: right">定额券面值大于限用最低消费,仍然可以添加</span>
								</td>
							</tr>
							<tr>
								<td><input type="radio" name="form_vailday"
									id="form_vailday1" checked>带领取的有效期:</td>
								<td><input type="text" placeholder="开始时间"
									id="form_beginTime1" name="form_beginTime1"
									style="width: 145px" readonly="readonly"
									onclick="WdatePicker({el:form_beginTime1,dateFmt:'yyyy-MM-dd'})" />
									&nbsp;日至&nbsp; <input type="text" placeholder="截止时间"
									id="form_endTime1" name="form_endTime1" style="width: 145px"
									readonly="readonly"
									onclick="WdatePicker({el:form_endTime1,dateFmt:'yyyy-MM-dd'})" />
									&nbsp;&nbsp;日领取，<br />自激活时间起&nbsp;&nbsp;<input type="text"
									placeholder="天数" style="width: 30px;" name="dayTimeNum"
									id="dayTimeNum" onkeyup="value=value.replace(/[^\d\.]/g,'') "
									onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d\.]/g,''))" />&nbsp;&nbsp;日内有效
									<span style="color: red; float: right">两种有效期方式二选一</span></td>
							</tr>
							<tr>
								<td><input type="radio" name="form_vailday"
									id="form_vailday2">一般使用有效期:</td>
								<td><input type="text" placeholder="开始时间"
									id="form_beginTime2" name="form_beginTime2" disabled="disabled"
									style="width: 145px" readonly="readonly"
									onclick="WdatePicker({el:form_beginTime2,dateFmt:'yyyy-MM-dd'})" />
									&nbsp;日至&nbsp;&nbsp;<input type="text" placeholder="截止时间"
									id="form_endTime2" name="form_endTime2" disabled="disabled"
									style="width: 145px" readonly="readonly"
									onclick="WdatePicker({el:form_endTime2,dateFmt:'yyyy-MM-dd'})" />&nbsp;&nbsp;日
								</td>
							</tr>
							<tr>
								<td>互斥规则:</td>
								<td><select style="width: 145px;" id="exclusiveRule"
									name="exclusiveRule">
										<option value="">请选择互斥规则</option>
										<option value="不得与其他优惠同时使用">不得与其他优惠同时使用</option>
								</select></td>
							</tr>
							<tr>
								<td>说明:</td>
								<td><textarea rows="3" placeholder="限制120字" cols="60"
										name="infoText" id="infoText"></textarea></td>
							</tr>
						</table>
						<div style="margin-top: 15px;"></div>
						<input type="button" value="确定"
							style="width: 110px; height: 25px; text-align: center;"
							id="saveCoupon" />
					</form>
				</div>
				<!-- 兑换码的form表单 -->
				<div id="exchangeCodeDiv" style="display: none">
					<form method="post" action="" id="exchangeCodeForm"></form>
				</div>
			</div>
		</div>
	</div>
	<!-- 弹出层的结束 -->
</body>
</html>
