<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="/common/taglib/share.tld" prefix="share" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="<%=basePath%>/css/pages/style_v2.2.css" type="text/css" rel="stylesheet">
    <script src="<%=basePath%>js/jquery-1.11.1.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
    <script src="<%=basePath%>/js/pages/parkingViewV2.js" type="text/javascript"></script>
    <script type="text/javascript" src="<%=basePath%>js/ajaxfileupload.js"></script>
    <script src="<%=basePath%>/js/CheckForm.js" type="text/javascript"></script>
    <link href="<%=basePath%>/js/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>/js/umeditor/umeditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=basePath%>/js/umeditor/umeditor.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
<form name="form" method="post" action="<%=basePath%>/products/carLifeActivity/save.html">
    <div class="editView">
        <div class="titleView">活动新增</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td><span class="star">＊</span>标题：</td>
                <td>
                    <input required type="text" name="title" value="${param.title}"/>
                </td>
                <td><span class="star">＊</span>活动时间：</td>
                <td>
                    <input required type="text" name="startDate" value="${param.startDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                    <strong>~</strong>
                    <input required type="text" name="endDate" value="${param.endDate}" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})">
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>适用车场：</td>
                <td>
                    <input required type="text" name="parkingName" value="${param.parkingName}" onclick="pv.show($('#parkingId'), $(this), false)" onkeydown="return false"/>
                    <input type="hidden" name="parkingId" id="parkingId" value="${param.parkingId}"/>
                </td>
                <td rowspan="5"><span class="star">＊</span>首图：</td>
                <td rowspan="5">
                    <share:imageUpload imageLabelName="图片" imagePathId="imagePath" savePath="product/"   imagePath="${param.imagePath}"/>
                    <input type="hidden" check_str="图片" value="${param.imagePath}" id="imagePath" name="imagePath"  />
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>类型：</td>
                <td>
                    <select name="type">
                        <option <c:if test="${param.type eq '1'}">selected="selected"</c:if> value="1">优惠活动</option>
                        <option <c:if test="${param.type eq '2'}">selected="selected"</c:if> value="2">用车心得</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>排序：</td>
                <td>
                    <input required pattern="\d+" title="必须为数字" type="text" name="sort" value="${param.sort}"/>
                    <span style="color: #ff0000;">（数字越小，排序越靠前）</span>
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>内容：</td>
                <td>
                    <input type="radio" name="contentType" <c:if test="${param.contentType eq '1'}">checked="checked"</c:if> value="1"/>文字内容
                    <input type="radio" name="contentType" <c:if test="${param.contentType eq '2' || param.contentType eq null}">checked="checked"</c:if> value="2"/>URL地址
                </td>
            </tr>
            <tr>
                <td><span class="star">＊</span>URL：</td>
                <td>
                    <input style="width: 300px;" type="text" name="url" value="${param.url}" pattern="^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)?((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.[a-zA-Z]{2,4})(\:[0-9]+)?(/[^/][a-zA-Z0-9\.\,\?\'\\/\+&amp;%\$#\=~_\-@]*)*$"/>
                    <br/>
                    <span style="color: #ff0000;">例如：http://www.p-share.com</span>
                </td>
            </tr>
            <tr>
                <td colspan="4" style="text-align:left;">
                    <script type="text/plain" id="myEditor" style="width:800px;height:240px;">${param.html}</script>
                    <input type="hidden" name="html"/>
                </td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="submit" value="确&nbsp;定">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="location='<%=basePath%>products/carLifeActivity/list.html'">
        </div>
    </div>
</form>
<script type="text/javascript">
    var info = '${requestScope.info}';
    if (info.length > 0) alert(info);
</script>
<script src="<%=basePath%>/js/pages/car_life_activity.js" type="text/javascript"></script>
</body>
</html>
