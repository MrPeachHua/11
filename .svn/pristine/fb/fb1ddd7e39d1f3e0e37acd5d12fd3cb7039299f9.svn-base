<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="com.boxiang.share.utils.ApplicationContextUtil" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    String imagePath = ApplicationContextUtil.IMAGE_PATH;
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title></title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link href="<%=basePath%>css/admin.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="<%=basePath%>/js/CheckForm.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/My97DatePicker/WdatePicker.js"></script>
    <script language="JavaScript" type="text/javascript" src="<%=basePath%>/js/FormValid.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/common.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.1.min.js"></script>
    <style type="text/css">
        body {
            margin: 0;
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
    </style>
</head>
<body>

<form id="mainForm" onsubmit="return on_submit(this);" action="<%=basePath%>products/scenePush/save.html" method="post">
    <table id="tab" width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" style="background-image:url('<%=imagePath%>tab_05.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30" alt=""/></td>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td class="STYLE4"></td>
                                    <td class="STYLE4"></td>
                                    <td align="right" class="STYLE4">
                                        <input type="submit" value="提交">
                                        <input type="button" value="返回" onclick="history.back()">
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30" alt=""/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table id="mainTable" width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="8" style="background-image:url('<%=imagePath%>tab_12.gif');"></td>
                        <td>
                            <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="b5d6e6">
                                <tr>
                                    <td width="10%" bgcolor="#FFFDF0">
                                        <div align="center">蓝卡云车场ID：</div>
                                    </td>
                                    <td width="20%" bgcolor="#FFFFFF">
                                        <input type="text" readonly="readonly" name="blueParkingId"/>
                                    </td>
                                    <td width="10%" bgcolor="#FFFDF0">
                                        <div align="center">车场ID：</div>
                                    </td>
                                    <td width="60%" bgcolor="#FFFFFF">
                                        <input type="text" readonly="readonly" name="parkingId"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">车场名称：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" readonly="readonly" name="parkingName"/>
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">场景模式：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input id="inPark" type="checkbox" name="sceneMode" value="1">入场
                                        <%--<input type="checkbox" name="sceneMode" value="2">出场--%>
                                        <input id="register" type="checkbox" name="sceneMode" value="3">注册
                                        <input id="parkStatus" type="checkbox" name="sceneMode" value="4">车位状态
                                        <input id="orderPush" type="checkbox" name="sceneMode" value="5">下单推送
                                        <input type="checkbox" name="sceneMode" value="6">月租、产权缴费提醒
                                        <input type="checkbox" name="sceneMode" value="7">月租、产权过期后提醒
                                        <input type="checkbox" name="sceneMode" value="8">优惠券到期前提醒
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">赠送的用户类型：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="checkbox" name="userType" value="1">月租
                                        <input type="checkbox" name="userType" value="2">产权
                                        <input id="notMP" type="checkbox" name="userType" value="3">非月租产权用户
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">推送方式：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="checkbox" name="pushMode" value="1">短信
                                        <input id="jPush" type="checkbox" name="pushMode" value="2">APP极光推送
                                    </td>
                                </tr>
                                <tr id="orderLine">
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">下单次数：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" name="orderCount">
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">订单类型：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <c:forEach items="${orderTypeDict}" var="item">
                                            <input type="radio" name="orderType" value="${item.dictValue}">${item.dictName}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr id="monthlyPropertyLine">
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">月租、产权到期日前(天)：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" name="monthlyPropertyExpireBefore">
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">月租、产权过期日后(天)：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" name="monthlyPropertyExpireAfter">
                                    </td>
                                </tr>
                                <tr id="couponLine">
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">优惠券到期前(天)：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" name="couponExpireBefore">
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">优惠券类型：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="checkbox" name="couponExpireType" value="">全部类型
                                        <c:forEach items="${dict}" var="item">
                                            <input type="checkbox" name="couponExpireType" value="${item.dictValue}">${item.dictName}
                                        </c:forEach>
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">活动开始时间：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" required="required" pattern="[\d]{4}-[\d]{2}-[\d]{2}" name="beginDate" check_str="活动开始时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">活动结束时间：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input type="text" required="required" pattern="[\d]{4}-[\d]{2}-[\d]{2}" name="endDate" check_str="活动结束时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"/>
                                    </td>
                                </tr>
                                <tr>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">推送内容：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <textarea name="pushContent" rows="3" cols="20"></textarea>
                                    </td>
                                    <td bgcolor="#FFFDF0">
                                        <div align="center">车辆入场推送次数：</div>
                                    </td>
                                    <td bgcolor="#FFFFFF">
                                        <input id="inParkPushCount" type="text" name="inParkPushCount"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="8" style="background-image:url('<%=imagePath%>tab_15.gif');"></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="35" style="background-image:url('<%=imagePath%>tab_19.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="35"><img src="<%=imagePath%>tab_18.gif" width="12" height="35" alt=""/></td>
                        <td align="right"><input onclick="add_line()" type="button" value="新增"></td>
                        <td width="16"><img src="<%=imagePath%>tab_20.gif" width="16" height="35" alt=""/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</form>
<%@ include file="../../template/blue_parking_view.jsp" %>
<%@ include file="../../template/coupon_template_add.jsp" %>
<script type="text/javascript" src="<%=basePath%>/js/pages/scene_push.js"></script>
<script type="text/javascript">
    var info = '${requestScope.info}';
    if (info != null && info.length != 0) {
        alert(info);
    }
</script>
</body>
</html>