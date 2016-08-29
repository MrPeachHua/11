<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="/common/taglib/share.tld" prefix="share"%>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<script type="text/javascript"
	src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
<script language="JavaScript" type="text/javascript"
	src="<%=basePath%>js/FormValid.js"></script>
<script type="text/javascript" src="<%=basePath%>js/common.js"></script>
<script type="text/javascript"
	src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
<script type="text/javascript">
	function confirm(){
		var ruleId = $("#rechargeRuleId").val();
		var payAmount = $("#payAmount").val();
		var giftAmount = $("#giftAmount").val();
		if(parseInt(giftAmount)>parseInt(payAmount)){
			alert("添加失败,赠送金额不能多于充值金额");
		}else{
			$.ajax({
				type:'post',
				url:'<%=basePath%>products/rule/rule_add1.html',
				data:{'ruleId':ruleId,'giftAmount':giftAmount,'amount':payAmount},
				success:function(msg){
					alert(msg);
					location.reload();
				}
			});
		}
		/* document.getElementById('addCoupon').style.display='none';
		$("#bg").css({
	        display: "none"	
	    });
		$("#payAmount").val("");
		$("#giftAmount").val(""); */
	}
	function cancel(){
		document.getElementById('addCoupon').style.display='none';
		$("#bg").css({
	        display: "none"	
	    });
		$("#payAmount").val("");
		$("#giftAmount").val("");
	}
	function addRule1(){
		document.getElementById('addCoupon').style.display='block';
		$("#bg").css({
	        display: "block"	
	    });
	}
	function deleteData(id){
		$.ajax({
			type:'post',
			url:'<%=basePath%>products/rule/del1.html',
			data:{'id':id},
			success:function(msg){
				alert("删除成功");
				location.reload();
			}
		}); 
	<%-- 	window.location="<%=basePath%>products/rule/del1.html?id="+id; --%>
	}
</script>
<style type="text/css">
.content {
	display: none;
	position: absolute;
	top: 30%;
	left: 30%;
	width: 300px;
	/* width: 50%; */
	/* height: 100%; */
	/* border: 1px solid black; */
	background-color: #E3F0F8;
	z-index: 1002;
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
<!--

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
-->
</style>
</head>
<body>
	<form action="<%=basePath%>products/rule/rule_edit.html"
		id="form2" method="post">
		<input type="hidden" name = "createor" value="${rechargeRule.createor}">
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="30" background="<%=imagePath%>tab_05.gif"><table
						width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="30"><img
								src="<%=imagePath%>tab_03.gif" width="12" height="30" /></td>
							<td>
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td class="STYLE4" align="center">&nbsp;&nbsp;</td>
										<td class="STYLE4">&nbsp;&nbsp;</td>
										<td align="right" class="STYLE4"><input type="submit"
											name="submit" value="提交">&nbsp;&nbsp; <input
											type="button" name="button" onClick="history.back()"
											value="返回"></td>
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
							<td><table width="100%" border="0" cellpadding="0"
									cellspacing="1" bgcolor="b5d6e6">
									<input type="hidden" id ="rechargeRuleId" name="id" value="${rechargeRule.id}">
									<tr>
										<td bgcolor="#FFFDF0"><div align="center">开始时间：</div></td>
										<td colspan="3" bgcolor="#FFFFFF">
										
										<input id="beginDate"
                             name="beginDate"
                             style=" width: 145px" readonly="readonly"  value='${begin}'
                             onclick="WdatePicker({el:beginDate,dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'endDate\')}'})">
									</td>
										<td bgcolor="#FFFDF0" rowspan="2"><div align="center">规则图片：</div></td>
										<td colspan="3" bgcolor="#FFFFFF" rowspan="2">&nbsp; <share:imageUpload
												imageLabelName="活动图片" imagePathId="imagePath"
												savePath="product/"
												imagePath="${rechargeRule.imagePath }" /> <input
											type="hidden" value="${rechargeRule.imagePath }"
											id="imagePath" name="imagePath" />
										</td>
									</tr>
									<tr>
										<td bgcolor="#FFFDF0"><div align="center">结束时间：</div></td>
										<td colspan="3" bgcolor="#FFFFFF">
										<input id="endDate"
                             name="endDate"
                             style=" width: 145px" readonly="readonly"  value='${end }'
                             onclick="WdatePicker({el:endDate,dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'})">
                             </td>
                             <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
									</tr>
									<tr height="30">
										<td colspan="8" align="right" class="STYLE4"><input
											type="button" name="addRule" onclick ="addRule1()" 
											value="新增">&nbsp;&nbsp;&nbsp;</td>
									</tr>
					<c:forEach items="${rechargeRuleGiftAmountList}" var="rule" varStatus="status">
											<tr>
												<td bgcolor="#FFFDF0"><div align="center">充值金额：</div></td>
										<td colspan="3" bgcolor="#FFFFFF">
										<input type="hidden" value="${rule.ruleId }" class="ruleId" name="rechargeRuleGiftAmount[${status.index}].ruleId"/>
										<input type="hidden" value="${rule.id }" class="giftId" name="rechargeRuleGiftAmount[${status.index}].id"/>
										<input type="text" value="${rule.amount}" name="rechargeRuleGiftAmount[${status.index}].amount"/>
										<td bgcolor="#FFFDF0"><div align="center">赠送金额：</div></td>
										<td colspan="3" bgcolor="#FFFFFF">
										<input type="text" value="${rule.giftAmount}" name="rechargeRuleGiftAmount[${status.index}].giftAmount"/>&nbsp;&nbsp;&nbsp;
										<input type="button" value="删除" onclick="deleteData('${rule.id}')" >
										</td>
											</tr>
										</c:forEach>
										
									
								</table>
								</td>
							
						</tr>
					</table>
					
					</td>
			</tr>
			
			<tr>
				<td height="35" background="<%=imagePath%>tab_19.gif">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="12" height="35"><img
								src="<%=imagePath%>tab_18.gif" width="12" height="35" /></td>
							<td></td>
							<td width="16"><img src="<%=imagePath%>tab_20.gif"
								width="16" height="35" /></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
	<!-- 弹出层的开始 -->
	<div id="bg"></div>
	<div class="content" id="addCoupon">
		<div>
			<!-- 弹出层名称 -->
			<div style="margin-left: 10px; margin-top: 15px;">
				<span style="font-size: 20px; color: #2C9DC8; font-weight: bold;">充值规则添加</span>
			</div>
			<div style="height:20px;"></div>
			<!-- 弹出层主体 -->
			<div>
				<form action="">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td width="20px;"></td>
							<td>充值金额：</td>
							<td><input onkeyup="value=value.replace(/[^\d]/g,'') "
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" type="text" value="" id="payAmount"/></td>
						</tr>
						<tr>
						<td></td>
							<td>赠送金额：</td>
							<td><input onkeyup="value=value.replace(/[^\d]/g,'') "
					onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" type="text" value ="" id="giftAmount"/></td>
						</tr>
						<tr height="10px;"></tr>
						<tr>
						<td></td>
							<td><input type="button" value ="确认" onclick="confirm();"/></td>
							<td><input type="button" value ="取消" onclick="cancel();"/></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	</div>
	<!-- 弹出层的结束 -->
</body>
</html>

