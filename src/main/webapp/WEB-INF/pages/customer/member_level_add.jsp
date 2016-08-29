<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/parkingViewV2.js" type="text/javascript"></script>
    <%--<script src="<%=basePath%>/js/CheckForm.js" type="text/javascript"></script>--%>
</head>
<script type="text/javascript">
    var numTest = /^[0-9]*$/;
function check(){
             var levelCode = $("#levelCode").val();
             if(!numTest.test(levelCode)){
                alert("请输入整数类型！");
                $("#levelCode").val("");
             }else{
                 $.ajax({
                     type: "POST",
                     url:'check.html',
                     data:{levelCode:levelCode},
                     success: function(data) {
                         if(data=="0"){
                             alert("该等级代码已存在，请重新输入!");
                             $("#levelCode").val("");
                         }
                     }
                 });
             }
}

function check1(){
    var growthValue = $("#growthValue").val();
    if(!numTest.test(growthValue)){
        alert("请输入整数类型！");
        $("#growthValue").val("");
    }
}
    function check2(){
        var ext1Value = $("#ext1Value").val();
        if(!numTest.test(ext1Value)){
            alert("请输入整数类型！");
            $("#ext1Value").val("");
        }
    }
    function check3(){
        var weight = $("#weight").val();
        if(!numTest.test(weight)){
            alert("请输入整数类型！");
            $("#weight").val("");
        }
    }
    function check4(){
        var ext1 = $("#ext1").val();
        if(!numTest.test(ext1)){
            alert("请输入整数类型！");
            $("#ext1").val("");
        }
    }

    function check5(){
        var levelName = $("#levelName").val();
            $.ajax({
                type: "POST",
                url:'check1.html',
                data:{levelName:levelName},
                success: function(data) {
                    if(data=="0"){
                        alert("该等级名称已存在，请重新输入!");
                        $("#levelName").val("");
                    }
                }
            });
    }

</script>
<body>
<form method="post" action="<%=basePath%>customer/member_level/save.html">
    <div class="editView">
        <div class="titleView">请填会员等级信息</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>等级代码：</td>
                <td>
                    <input required type="text"  id="levelCode" name="levelCode" required onchange="check()"/>
                </td>
                </td>
                <td><span class="star">＊</span>等级名称：</td>
                <td><input type="text" name="levelName"  id="levelName" required  onchange="check5()"/></td>
            </tr>
            <tr>
                <td><span class="star">＊</span>成长值目标：</td>
                <td>
                    <input type="text" name="growthValue" required  id="growthValue" onblur="check1()"/>
                <td><span class="star">＊</span>成长值减少：</td>
                <td>
                    <input  type="text" name="ext1Value" required id="ext1Value" onblur="check2()"/>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>状态：</td>
                <td>
                    <select name="isValid"  required style="width: 191px">
                        <option value="0">
                           失效
                        </option>
                        <option value="1"  selected="selected">
                            有效
                        </option>
                    </select>
                </td>
                <td>权重：</td>
                <td><input type="text" id="weight"  name="weight" onblur="check3()"/></td>
            </tr>
            <tr>
                <td><span class="star">＊</span>无有效订单时长(天)：</td>
                <td><input type="text"  required  name="ext1" id="ext1" onblur="check4()"/></td>
            </tr>

        </table>
        <div class="footView">
            <input type="submit"  class="greenBtn"  value="添&nbsp;加">
            <input class="greenBtn" type="button" value="返&nbsp;回" onClick="history.back()">
        </div>
    </div>
</form>
<script type="text/javascript">
    var info = '${requestScope.info}';
    if (info.length > 0) alert(info);
</script>
</body>
</html>
