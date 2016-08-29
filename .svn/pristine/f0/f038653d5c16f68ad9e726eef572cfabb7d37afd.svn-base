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
    <style type="text/css">
        .editTable {
            width: 90%;
            border-top: 1px solid #D0CDC7;
            border-right: 1px solid #D0CDC7;
        }
        .editTable td {
            padding: 15px;
            border-left: 1px solid #D0CDC7;
            border-bottom: 1px solid #D0CDC7;
        }
        .editTable ul {
            margin: 0;
            padding: 0;
            border: 0;
        }
        .editTable ul li {
            list-style-type: none;
            float: left;
            width: 207px;
            display: block;
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
    </style>
</head>
<body>
<form method="post" action="<%=basePath%>/products/activityQrCode/batchSave.html">
    <div class="editView">
        <div class="titleView">二维码新增</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td width="12%">生成类型</td>
                <td>
                    <ul>
                        <li><input type="radio" name="type" value="1"/>微信公众号关注</li>
                        <li><input type="radio" name="type" value="2"/>活动</li>
                    </ul>
                </td>
            </tr>
            <tr id="activityTr">
                <td>活动</td>
                <td>
                    <ul>
                        <c:forEach items="${requestScope.activityList}" var="item">
                            <li><input type="radio" name="activityId" value="${item.id}"/>${item.name}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>小区</td>
                <td>
                    <ul>
                        <c:forEach items="${requestScope.parkingList}" var="item">
                            <li><input type="checkbox" name="parkingId" value="${item.parkingId}"/>${item.parkingName}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
            <tr>
                <td>员工</td>
                <td>
                    <ul>
                        <c:forEach items="${requestScope.parkerList}" var="item">
                            <li><input type="checkbox" name="userId" value="${item.parkerId}"/>${item.parkerName}</li>
                        </c:forEach>
                    </ul>
                </td>
            </tr>
        </table>
        <div class="footView">
            <input type="hidden" name="isDownload" id="isDownload" value="0"/>
            <input class="greenBtn" type="button" value="确定新增" onclick="sb(0)">
            <input class="greenBtn" type="button" value="新增并下载" onclick="sb(1)">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/activityQrCode/list.html'">
        </div>
    </div>
</form>
<div class="bgTransparent"><center><h1 style="color:#ffffff;margin-top:20%;">速度比较慢，别着急......</h1></center></div>
<script type="text/javascript">
    function sb(isDownload) {
        $('#isDownload').val(isDownload);
        $('form').submit();
    }
    var $activityId = $('input[name=activityId]');
    var type2 = $('input[name=type][value="2"]')[0];
    $('input[name=type]').click(function () {
        if (type2.checked) {
            $activityId.removeAttr("disabled");
            $('#activityTr').css("backgroundColor", "#FFFFFF");
        } else {
            $activityId.attr("disabled", "disabled");
            $activityId.attr("checked", false);
            $('#activityTr').css("backgroundColor", "#C8C8C8");
        }
    });
    $('form').submit(function () {
        if ($('input[name=type]:checked').length == 0) {
            alert('请选择生成类型');
            return false;
        }
        if (type2.checked && $('input[name=activityId]:checked').length == 0) {
            alert('请选择活动');
            return false;
        }
        if ($('input[name=parkingId]:checked').length == 0) {
            alert('请选择小区');
            return false;
        }
        if ($('input[name=userId]:checked').length == 0) {
            alert('请选择员工');
            return false;
        }
        $('.bgTransparent').show();
    });
    if ($activityId.length == 0) {
        type2.disabled = true;
    }
</script>
</body>
</html>
