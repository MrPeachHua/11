// 获取终端的相关信息
var terminal = {
    // 辨别移动终端类型
    platform : function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            // android终端或者uc浏览器
            android: u.indexOf('Android') > -1 || u.indexOf('Linux') > -1,
            // 是否为iPhone或者QQHD浏览器
            iPhone: u.indexOf('iPhone') > -1 ,
            // 是否iPad
            iPad: u.indexOf('iPad') > -1
        };
    }(),
    // 辨别移动终端的语言：zh-cn、en-us、ko-kr、ja-jp...
    language : (navigator.browserLanguage || navigator.language).toLowerCase()
}

// 如果要分渠道，也是可以的，渠道区分：?from=xx
var fromChannel = (function(){
    var searchInfo = location.search.substr(1).split('&'),item,from;
    for(var i= 0,len=searchInfo.length;len > 1 && i<len;i++){
        item = searchInfo[i].split('=');
        if(item[0] == 'channel') {
            from = item[1];
            break;
        }
    }
    return from;
})();
var count = 60;
function remainTime(){
    if(count==0){
        document.getElementById("register_yz").innerHTML="获取验证码";
        count = 60;
        return;
    }
    document.getElementById('register_yz').innerHTML=count--;
    setTimeout("remainTime()",1000);
}
function yanzheng(){
    //alert(terminal.platform.android);
    //alert(terminal.platform.iPhone);
    //alert(fromChannel);
    if(count < 60){
        return;
    }
    var mobile=$("#mobile").val();

    $.ajax({
        cache: false,
        type: "POST",
        url:'http://localhost:9090/share/other/sendSmsCode.html',
        data:{"mobile":mobile},
        dataType: "json",
        success: function (jsonStr) {
            if(jsonStr==0){
                alert("验证码发送成功 !");
                remainTime();
            }else{
                alert(((jsonStr=="1")?"验证码发送失败 !":jsonStr));
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
    $("#channel").val(getUrlParam("channel"));
});

function add(){
    var mobile=$("#mobile").val();
    var password=$("#password").val();
    var smsCode=$("#smsCode").val();
    var channel=$("#channel").val();
    $.ajax({
        cache: false,
        type: "POST",
        url:'http://localhost:9090/share/other/registerbyqrcode.html',
        data:{"mobile":mobile,"password":password,"smsCode":smsCode,"channel":channel},
        dataType: "json",
        success: function (jsonStr) {
            if(jsonStr.msg!=null){
                alert(jsonStr.msg);
            }else{
                if(terminal.platform.iPhone){
                    location.href="https://itunes.apple.com/us/app/kou-dai-ting/id1049233050?mt=8&uo=4";
                }else{
                    location.href="https://www.pgyer.com/bkP9";
                }
            }
        }
    });
}