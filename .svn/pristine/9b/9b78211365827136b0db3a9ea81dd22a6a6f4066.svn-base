<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<script type="text/javascript" src="<%=basePath%>/js/angular.min.js"></script>
<div id="blueParkingView" ng-app="app" ng-controller="ctrl"
     style="position:absolute; width:80%; height:450px; overflow: auto; left:10%; top:10%; display:none;">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td height="30" margin="0" padding="0" background="<%=imagePath%>tab_05.gif">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="30"><img src="<%=imagePath%>tab_03.gif" width="12" height="30"/></td>
                        <td align="right"><input type="button" value="关闭" onclick="close_park_view()"/></td>
                        <td width="16"><img src="<%=imagePath%>tab_07.gif" width="16" height="30"/></td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="8" background="<%=imagePath%>tab_12.gif">&nbsp;</td>
                        <td>
                            <table id="mytable" width="100%" border="0" cellpadding="0" cellspacing="1"
                                   bgcolor="b5d6e6">
                                <tr>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">蓝卡云车场ID</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">车场ID</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">车场名称</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">区</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">地址</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">车位数</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">空位数</span>
                                        </div>
                                    </td>
                                    <td background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" style="height: 22px;">
                                        <div align="center">
                                            <span class="STYLE1">车位状态</span>
                                        </div>
                                    </td>
                                    <td width="10%" background="<%=imagePath%>bg2.gif" bgcolor="#FFFFFF" class="STYLE1"
                                        style="height: 22px;">
                                        <div align="center">基本操作</div>
                                    </td>
                                </tr>
                                <tr ng-repeat="p in parkingList">
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.item01}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingId}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingName}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingArea}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingAddress}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingCount}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingCanUse}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center">
                                            <span class="STYLE1">{{p.parkingStatus}}</span>
                                        </div>
                                    </td>
                                    <td height="20" bgcolor="#FFFFFF">
                                        <div align="center"><img style="vertical-align: middle;" src="<%=imagePath%>edt.gif" width="16" height="16"/>
                                            <span class="STYLE1"><a ng-click="parking_select(p)" href="javascript:void(0);">选择</a></span>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td width="8" background="<%=imagePath%>tab_15.gif">&nbsp;</td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td height="35" style="background-image:url('<%=imagePath%>tab_19.gif');">
                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                        <td width="12" height="35"><img src="<%=imagePath%>tab_18.gif" width="12" height="35" alt=""/></td>
                        <td></td>
                        <td width="16"><img src="<%=imagePath%>tab_20.gif" width="16" height="35" alt=""/></td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
<script type="text/javascript">
    $("input[name=blueParkingId],input[name=parkingId],input[name=parkingName]").click(function () {
        $("#blueParkingView").show();
    });
    function close_park_view() {
        $("#blueParkingView").hide();
    }
    var app = angular.module('app', []);
    app.controller('ctrl', function ($scope, $http) {
        var local = (window.location + '').split('/');
        var basePath = local[0] + '//' + local[2] + '/' + local[3];
        var url = basePath + '/products/scenePush/blueParkingList.html';
        $http.get(url).success(function (response) {
            $scope.parkingList = response.data;
        });
        $scope.parking_select = function (parking) {
            $("input[name=blueParkingId]").val(parking.item01);
            $("input[name=parkingId]").val(parking.parkingId);
            $("input[name=parkingName]").val(parking.parkingName);
            $("#blueParkingView").hide();
        }
    });
</script>