<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
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
    <script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
    <%--<script src="<%=basePath%>/js/CheckForm.js" type="text/javascript"></script>--%>
    <style type="text/css">
        .editTable{
             margin: 15px auto 0 auto;
             width: 27%;
        }
    </style>
    <script>
        function vali(){
            var co = $("#qrcodeUrl").val();
            if(co==""||co==undefined||co==null){
                alert("请上传二维码图片");
                return false;
            }
        }

    </script>
</head>
<body>
<form method="post" action="<%=basePath%>products/qrcode/save.html" onsubmit="return validator(this)">
    <div class="editView">
        <div class="titleView">请填写信息</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>社区名称：</td>
                <td>
                    <input required type="text" name="parkingName" value="${entity.parkingName}" onclick="pv.show($('#parkingId'), $(this), false);"/>
                    <input type="hidden" id="parkingId" name="parkingId" value="${entity.parkingId}"/>
                    <input type="hidden" valid="required" errmsg="小区名称不能为空!" name="id" value="${entity.id}"/>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>社群二维码：</td>
                <td>
                    <share:imageUpload imageLabelName="二维码图片" imagePathId="qrcodeUrl" savePath="product/" imagePath="${qrcodeUrl }"/>
                    <input type="hidden" id="qrcodeUrl" name="qrcodeUrl" valid="required"  errmsg="二维码图片不能为空!"  />
                </td>
            </tr>

        </table>
        <div class="footView">
            <span style="color: #ff0000;text-align: left">代泊端每5日进行推送提醒</span>
        </div>
        <div class="footView">
            <input class="greenBtn" type="submit" onclick="return vali()" value="确&nbsp;定">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/qrcode/list.html'">
        </div>
    </div>
</form>
<script type="text/javascript">
    var info = '${requestScope.info}';
    if (info.length > 0) alert(info);
</script>
</body>
</html>
