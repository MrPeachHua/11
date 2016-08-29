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
    function check1(){
        var groupCode = $("#groupCode").val();
        if(!numTest.test(groupCode)){
            alert("请输入整数类型！");
            $("#groupCode").val("");
        }else{
            $.ajax({
                type: "POST",
                url:'check.html',
                data:{groupCode:groupCode},
                success: function(data) {
                    if(data=="0"){
                        alert("该等级代码已存在，请重新输入!");
                        $("#groupCode").val("");
                    }
                }
            });
        }
    }

    function check2(){
        var growthValue = $("#growthValue").val();
        if(!numTest.test(growthValue)){
            alert("请输入整数类型！");
            $("#growthValue").val("");
        }
    }

</script>
<body>
<form method="post" action="<%=basePath%>customer/member_group/save.html">
    <div class="editView">
        <div class="titleView">请填会员分组信息</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>请选择分组：</td>
                <td>
                    <input required type="text" name="groupName" required/>
                </td>
                <td><span class="star">＊</span>分组代码：</td>
                <td>
                    <input required type="text" name="groupCode" id="groupCode" required onblur="check1()"/>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>每日获得成长值上限：</td>
                <td>
                    <input required type="text" name="growthValue" id="growthValue" required onblur="check2()"/>
                </td>
                <td><span class="star">＊</span>状态：</td>
                <td>
                    <select name="isValid" style="width: 191px">
                        <option value="0">
                            失效
                        </option>
                        <option value="1"  selected="selected">
                            有效
                        </option>
                    </select>
                </td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="submit" value="确&nbsp;定">
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
