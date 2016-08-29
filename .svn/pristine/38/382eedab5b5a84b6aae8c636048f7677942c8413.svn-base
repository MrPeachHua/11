<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Insert title here</title>
	<script type="text/javascript" src="http://www.p-share.com/share/js/jquery-1.11.1.min.js"></script>
</head>
<script type="text/javascript">
function yanzheng(){
var mobile=$("#mobile").val();
  $.ajax({
          cache: false,
          type: "POST",
          url:'http://www.p-share.com/share/other/sendSmsCode.html',
          data:{"mobile":mobile},
         dataType: "json",   
        success: function (jsonStr) {
         if(jsonStr==0){
          alert("验证码发送成功 !");
          }else{
        alert("验证码发送失败 !");
         }
          }
      });
}
function getUrlParam(name)
{
var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
var r = window.location.search.substr(1).match(reg);  //匹配目标参数
if (r!=null) return unescape(r[2]); return null; //返回参数值
}

$(document).ready(function(){
//alert(getUrlParam("platform")+"-------"+getUrlParam("name"));
/*  var url = window.location.search;
   var loc = url.substring(url.lastIndexOf('=')+1, url.length);
   alert(loc); */
 $("#platform").val(getUrlParam("platform"));
 $("#channel").val(getUrlParam("channel"));
});
function add(){
var mobile=$("#mobile").val();
var password=$("#password").val();
var smsCode=$("#smsCode").val();
var channel=$("#channel").val();
var platform=$("#platform").val();
  $.ajax({
         cache: false,
         type: "POST",
         url:'http://www.p-share.com/share/other/registerbyqrcode.html',
         data:{"mobile":mobile,"password":password,"smsCode":smsCode,"channel":channel,"platform":platform},
         dataType: "json",   
         success: function (jsonStr) {alert(alert);
         if(jsonStr.msg!=null){
         alert(jsonStr.msg);
         }else{
	         if(jsonStr.type=="ios"){
				location.href="http://beta.qq.com/m/v1xw";
	         }else{
	         	location.href="https://www.pgyer.com/bkP9";
	         }
	         }
          }
      });
}
</script >
<body style="background-color : #AEEEEE">
<div align="center" style=" margin-top: 200px " >
<form action="http://www.p-share.com/share/other/registerbyqrcode.html" method="post" id="form1" name="form1">
手机号:<input type="text" name="mobile" id="mobile"><br/><br/>
密&nbsp;码:<input type="password" name="password" id="password"><br/><br/>
验证码:<input type="text" name="smsCode" id="smsCode" style="width: 68px"> 
<input type="button" onclick="javascript:yanzheng()" value="获取验证码 " /> <br/><br/>
<input type="hidden" id="channel"  name="channel" />
<input type="hidden" id="platform"  name="platform" />
<input type="submit">
<!-- <input type="button" value="注册" onclick="add()"  /> -->&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="重置"   /> 
</form>
</div>
</body>
</html>