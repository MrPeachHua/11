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
function pushMessage(){
	var start_date = $("#start_date").val();
	var end_date = $("#end_date").val();
	var msg_content = $("#msg_content").val();
	var mobiles = $("#mobiles").val();
	if(null==start_date||undefined==start_date){
		start_date = "";
	}
	if(null==end_date||undefined==end_date){
		end_date = "";
	}
	/** 验证消息格式*/
	var reg0 = /^【口袋停】/gi;
	var reg1 = /@start_date@/gi;
	var reg2 = /@end_date@/gi;
	var reg3= /@parking_code@/gi;
	var regdate=/\d{4}-\d{2}-\d{2}/;
	if(!reg0.test(msg_content)||!reg1.test(msg_content)||!reg2.test(msg_content)||!reg3.test(msg_content)){
		alert("请在开头使用关键字【口袋停】，内容中使用关键字@start_date@，@end_date@，@parking_code@");
		return false;
	}
	if(!regdate.test(start_date)||!regdate.test(end_date)){
		alert("日期请使用格式xxxx-xx-xx");
		return false;
	}	
	var numbers = new Array;
	if(null!=mobiles||""!=mobiles){
		mobiles = mobiles.replace(/\s+/g, ""); 
		numbers = mobiles.split(",");
	}else{
		alert("请输入手机号,以‘,’隔开。");
		return false;
	}
	
	//return false;
	//return false;
	var param = {"numbers":numbers,"start_date":start_date,"end_date":end_date,"msg_content":msg_content};
	console.log(param)
	 $.ajax({
		type : "POST",
		url : "<%=basePath%>system/sms/parkingTicketMessagePush.html",
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



</script>
  </head>
  
  <body>
<!-- 短信发送 -->
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
    <td><table width="100%" height="100px" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
        <td><table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
        <tr>
        	<td bgcolor="#FFFDF0"><div align="center">开始时间：</div>
        	</td>
        	<td background="images/bg2.gif">
        		<input type="text" id="start_date" name="start_date"  style=" width: 145px"  valid="required"  
        			errmsg="出生日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:start_date,dateFmt:'yyyy-MM-dd'})"/>
        	</td>
       </tr>
       <tr>
        	<td bgcolor="#FFFDF0"><div align="center">结束时间：</div>
        	</td>
        	<td background="images/bg2.gif">
        		<input type="text" id="end_date" name="end_date"  style=" width: 145px"  valid="required"  
        			errmsg="出生日期不能为空!" readonly="readonly"  onclick="WdatePicker({el:end_date,dateFmt:'yyyy-MM-dd'})"/>
        	</td>
        </tr>
         <tr>
        	<td bgcolor="#FFFDF0"><div align="center">收件人：</div>
        	</td>
        	<td background="images/bg2.gif">
				<textarea id="mobiles" maxlength="500" rows="" style="height:50px;width:100%" cols=""></textarea>
        	</td>
        </tr> 
	    <tr height="100%">
		  	<td bgcolor="#FFFDF0"><div align="center" style="height:200px">短信内容：</div></td>
			<td colspan="3" style="height:100px"  bgcolor="#FFFFFF">
				<textarea id="msg_content" maxlength="100" rows="" style="height:100%;width:100%" cols="">【口袋停】亲爱的小伙伴，恭喜你获得了口袋停提供的价值20元停车代金券一份。兑换码为@parking_code@,有效期@start_date@~@end_date@</textarea>
			</td>	  	
	 </tr>     
</table>



</body>
</html>
