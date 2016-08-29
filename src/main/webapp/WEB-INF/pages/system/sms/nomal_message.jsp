<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 
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
    
    <title>停车券推送</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<LINK href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
	<LINK href="<%=basePath%>js/My97DatePicker/skin/WdatePicker.css" type=text/css rel=stylesheet>
	<script type="text/javascript" src="<%=basePath%>js/CheckForm.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
	<script language="JavaScript" type="text/javascript" src="<%=basePath%>js/FormValid.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
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

<script>
function add(){  
	window.location="<%=basePath%>system/dict/add.html";
}
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
</script>


<script type="text/javascript">

function parkingPop(){
	var queryValue=$("#queryValue").val();
	var queryType = $("#queryType").val();
	//document.getElementById('parkingForm').style.display='block';
	$("#parkingForm").show();
	$.ajax({  
      type: "POST",  
      url: "<%=basePath%>products/carlife/srvbilling/parkingData.html",
      data:{"queryValue":queryValue,"queryType":queryType},
      dataType: "json",   
      success: function (jsonStr) {
      	$(".parkingListTr").remove();
          	for(var i=0;i<jsonStr.length;i++){ 
          		$("table#myTable tr:last").after('<tr class = "parkingListTr" style="text-align:center"><td bgcolor="#FFFFFF">'+jsonStr[i].parkingId+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingName+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingArea+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingAddress+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCount+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingCanUse+'</td><td bgcolor="#FFFFFF">'+jsonStr[i].parkingStatus+'</td><td bgcolor="#FFFFFF">'+
          				'<img src="<%=imagePath%>edt.gif" width="16" height="16" /><span style="cursor:pointer" onclick="choiceParkingCode(\''+jsonStr[i].parkingName+'\',\''+jsonStr[i].parkingId+'\')">选择</span></td></tr>');
          }
      },
  });
	$("#bg").css({
      display: "block"
  });
}

function choiceParkingCode(text1,text2){
	  $("#parkingCode").val(text1);
	  $("#parkingId").val(text2);
	  $("#parkingForm").hide();
	  $(".parkingListTr").remove();
	  $("#bg").css("display", "none");
}
 
function cancelButton(){
	  $("#parkingForm").hide();
	  $(".parkingListTr").remove();
	  $("#bg").css("display", "none");
}
function pushMessage(){
	//方案一：按条件发送 方案二给固定用户发送
	//方案二 传手机号和内容发送 1:方案一 2：方案二
	var mobiles = $("#mobiles").val();
	var parkingScope = null;
	var userScope = null;
	var msgContent = $("#msgContent").val();
	var parkingId = $("#parkingId").val();
	var scheme = "1";
	
	/* if($("#scheme2").is(':checked')){		
		scheme = "2";
		mobiles = $("#mobiles").val();
	}else{
		parkingScope = $("#parkingScope").val();
		userScope = $("#userScope").val();		
		parkingId = $("#parkingId").val();
	}
	if(parkingScope==1){
		if(null==parkingId||""==parkingId){
			alert("请选择车场");
			return false;
		}
	} */
	/** 验证消息格式*/
	var reg0 = /^【口袋停】/gi;

	if(!reg0.test(msgContent)){
		alert("请在开头使用关键字【口袋停】");
		return false;
	}
	if(scheme==2){
		if(null==mobiles||""==mobiles){
			alert("请输入手机号,以‘,’隔开。");
			return false;
		}
	}	

	//return false;
	var param = {"numbers":mobiles,"parkingScope":parkingScope,"userScope":userScope,"msgContent":msgContent,"parkingId":parkingId,"scheme":scheme};
	 $.ajax({
		type : "POST",
		url : "<%=basePath%>system/sms/nomalMessagePush.html",
		dataType : "json",
		data:param,
		success : function(data) {
			if(data=="0"){
				alert("短信发送成功");
				location.href="<%=basePath%>system/sms/list.html";
			}else{
				alert("有异常短信");
			}
		}
	})
	
}
$(function(){
	$("#scheme1").click(function(){
		//alert($(this).is(':checked'));
		$("#scheme2").attr('checked',false);
		//清空手机号
		$("#mobiles").val("");
		$("#mobiles").attr("disabled",true);
		$("#parkingScope").attr("disabled",false);
		$("#userScope").attr("disabled",false);
		$("#parkingCode").attr("disabled",false);
	});
	$("#scheme2").click(function(){
		//alert($(this).is(':checked'));
		$("#scheme1").attr('checked',false);
		$("#mobiles").val("");
		$("#mobiles").attr("disabled",false);
		
		$("#parkingScope").attr("disabled",true);
		$("#userScope").attr("disabled",true);
		$("#parkingCode").attr("disabled",true);
	});
})


</script>
  </head>
  
  <body>
<!-- 短信发送 -->
<div id="bg"></div>
<div class="content" id="parkingForm" style="display:none">
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
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
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
							<td><table id="mytable" width="100%" border="0" cellpadding="0"
									cellspacing="1" bgcolor="b5d6e6" onmouseover="changeto()"
									onmouseout="changeback()">
									<tr>
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
										<td width="10%" background="<%=imagePath%>bg2.gif"
											bgcolor="#FFFFFF" class="STYLE1"
											style="width: 10%; height: 22px;">
											<div align="center">基本操作</div>
										</td>
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
					
					<td align="right"  class="STYLE4">
						<input type="button" onclick="pushMessage()" style="margin-left:80%"  value="发送">&nbsp;&nbsp;
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
    <td>
    	<table width="100%" height="100px" border="0" cellspacing="0" cellpadding="0">
	      <!--  <tr>
	        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
	        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
	        <tr>
	        	<td bgcolor="#FFFDF0"><div align="center"><input id="scheme1" checked="checked" type="checkbox">范围发送：</div>
	        	</td>
	        	<td background="images/bg2.gif">
	        		短信范围：<select id="parkingScope" style="width: 145px">
							
							<option value="0">所有车场</option>
							
							<option value="1">固定车场</option>														
						</select>
	        			
	        			
	        	</td>
	        	<td background="images/bg2.gif">停车场：<input type="text" onclick="parkingPop()" id="parkingCode">
	        	<input type="hidden" name = "parkingId" id="parkingId"/>        	
	        	</td>
	        	
	        	<td background="images/bg2.gif">用户群组：
	        		<select id="userScope" style="width: 145px">
							
						<option value="0">全部</option>
						
						<option value="1">月租用户</option>	
						
						<option value="2">产权用户</option>													
					</select>	
				</td>	        	
	       </tr>-->
	         <tr>
	        	<td bgcolor="#FFFDF0" width="20%"><div align="center"><!-- <input  type="checkbox" id="scheme2"> -->收&nbsp;件&nbsp;人：</div>
	        	</td>
	        	<td  background="images/bg2.gif">
					<textarea  id="mobiles" maxlength="500" rows="" style="height:50px;width:100%" cols=""></textarea>
	        	</td>
	        </tr> 
		    <tr height="100%" width="20%">
			  	<td bgcolor="#FFFDF0"><div align="center" style="height:200px">短信内容：</div></td>
				<td  style="height:100px"  bgcolor="#FFFFFF">
					<textarea id="msgContent" maxlength="70" rows="" style="height:100%;width:100%" cols="">【口袋停】亲爱的小伙伴，欢迎您使用口袋停。</textarea>
				</td>	  	
		 </tr> 	 
		</table>
	  </td>
	</tr>
</table>




</body>
</html>
