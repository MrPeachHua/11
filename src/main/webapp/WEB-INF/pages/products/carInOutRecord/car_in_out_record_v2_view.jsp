<%@ page language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        .editTable td {
            padding: 5px;
        }
    </style>
</head>
<body>
    <div class="editView">
        <div class="titleView">详情</div>
        <table class="editTable" cellpadding="0" cellspacing="0" border="0">
            <tr>
                <td>车类型：</td>
                <td>${entity.carType}</td>
                <td>置信度：</td>
                <td>${entity.confidence}</td>
            </tr>
            <tr>
                <td>优惠金额：</td>
                <td>${entity.discountCharge/100.0}</td>
                <td>优惠编码：</td>
                <td>${entity.discountCode}</td>
            </tr>
            <tr>
                <td>优惠时间：</td>
                <td>${entity.discountTime}</td>
                <td>是否修改标识：</td>
                <td>${entity.editFlag}</td>
            </tr>
            <tr>
                <td>序列号：</td>
                <td>${entity.inparkId}</td>
                <td>入口道闸ID：</td>
                <td>${entity.inGateId}</td>
            </tr>
            <tr>
                <td>入场抓拍照片名称：</td>
                <td>${entity.inImageName}</td>
                <td>入口LED灯Id：</td>
                <td>${entity.inLedId}</td>
            </tr>
            <tr>
                <td>入场标志：</td>
                <td>${entity.inOrOut}</td>
                <td>入口读卡器ID：</td>
                <td>${entity.inReaderId}</td>
            </tr>
            <tr>
                <td>入场时间：</td>
                <td><fmt:formatDate value="${entity.inTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>入口摄像机ID：</td>
                <td>${entity.inVideoId}</td>
            </tr>
            <tr>
                <td>发票代码：</td>
                <td>${entity.invoiceCode}</td>
                <td>交易订单ID：</td>
                <td>${entity.orderId}</td>
            </tr>
            <tr>
                <td>停车场编号：</td>
                <td>${entity.parkId}</td>
                <td>实时剩余车位数量：</td>
                <td>${entity.parkSpaceNum}</td>
            </tr>
            <tr>
                <td>应收停车费用：</td>
                <td>${entity.payCharge/100.0}</td>
                <td>车辆颜色：</td>
                <td>${entity.plateColor}</td>
            </tr>
            <tr>
                <td>车牌号：</td>
                <td>${entity.plateId}</td>
                <td>车牌号关键字：</td>
                <td>${entity.platek}</td>
            </tr>
            <tr>
                <td>实付金额：</td>
                <td>${entity.realCharge/100.0}</td>
                <td>记录的唯一标识ID：</td>
                <td>${entity.recordId}</td>
            </tr>
            <tr>
                <td>记录上传时间：</td>
                <td><fmt:formatDate value="${entity.sysWriteDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                <td>车辆用户类型：</td>
                <td>${entity.userType}</td>
            </tr>
            <tr>
                <td>支付历史表中的id：</td>
                <td>${entity.outparkId}</td>
                <td>入场时的通道名称：</td>
                <td>${entity.inPassageway}</td>
            </tr>
            <tr>
                <td>入场识别的车牌号：</td>
                <td>${entity.inPlateId}</td>
                <td>车主姓名：</td>
                <td>${entity.name}</td>
            </tr>
            <tr>
                <td>岗亭操作员姓名：</td>
                <td>${entity.operator}</td>
                <td>出场时的通道名称：</td>
                <td>${entity.outPassageway}</td>
            </tr>
            <tr>
                <td>出场识别的车牌号：</td>
                <td>${entity.outPlateId}</td>
                <td>出场时间：</td>
                <td><fmt:formatDate value="${entity.outTime}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/></td>
            </tr>
            <tr>
                <td>支付类型：</td>
                <td>${entity.payType}</td>
                <td>优惠编码：</td>
                <td>${entity.profitNo}</td>
            </tr>
            <tr>
                <td>优惠金额：</td>
                <td>${entity.profitNum/100.0}</td>
                <td>备注：</td>
                <td>${entity.remark}</td>
            </tr>
            <tr>
                <td>停留时间：</td>
                <td>${entity.stayTime}</td>
                <td>车场名称：</td>
                <td>${entity.parkingName}</td>
            </tr>
        </table>
        <div class="footView">
            <input class="greenBtn" type="button" value="返&nbsp;回" onclick="history.back()"/>
        </div>
    </div>
</body>
</html>
