<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <title></title>
    <link href="<%=basePath%>/css/bootstrap-3.3.5-dist/css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="<%=basePath%>/js/angular.min.js"></script>
</head>
<style type="text/css">
    body {
        background-color: #EFEEEC;
        font-family: "Microsoft YaHei", "微软雅黑", "sans-serif";
        color: #6D7D83;
        font-size: 16px;
        padding: 15px 20px;
    }
</style>
<body>
<div ng-app="app" ng-controller="ctrl">
    <ul ng-include="'recursion'" class="list-group"></ul>
    <script id="recursion" type="text/ng-template">
        <li ng-repeat="n in treeArray" class="list-group-item">
            <p ng-style="{'cursor':'pointer'}" ng-click="toggle(n,$event)">
                <span ng-click="spanToggle(n)" ng-hide="n.isEdit">{{n.nodeName}}</span>
                <span ng-show="n.isEdit">
                    <input ng-keydown="editKeyDown($event,n)" ng-model="n.nodeName" class='form-control' type='text' ng-style="{'width':'30%','display':'inline-block'}" title="">
                    <span ng-click='editOk(n)' class='glyphicon glyphicon-ok' ng-style='{"margin-left":"15px"}'></span>
                    <span ng-click='editCancel(n)' class='glyphicon glyphicon-remove' ng-style='{"margin-left":"15px"}'></span>
                </span>
                <span ng-if="n.levels>=2" ng-click="del($index,treeArray)" class="glyphicon glyphicon-trash" ng-style="{'float':'right'}"></span>
                <span ng-if="n.levels>=2" ng-click="edit(n)" class="glyphicon glyphicon-pencil" ng-style="{'float':'right','margin-right':'15px'}"></span>
                <span ng-if="n.levels<4" ng-click="add(n)" class="glyphicon glyphicon-plus" ng-style="{'float':'right','margin-right':'15px'}"></span>
            </p>
            <ul ng-show="n.isAdd">
                <li class="list-group-item">
                    <input ng-keydown="addKeyDown($event,n)" ng-model="n.addedNodeName" class='form-control' type='text' ng-style="{'width':'30%','display':'inline-block'}" title="">
                    <span ng-click='addOk(n)' class='glyphicon glyphicon-ok' ng-style='{"margin-left":"15px","cursor":"pointer"}'></span>
                    <span ng-click='addCancel(n)' class='glyphicon glyphicon-remove' ng-style='{"margin-left":"15px","cursor":"pointer"}'></span>
                </li>
            </ul>
            <ul ng-if="n.childrenList.length" ng-include="'recursion'" ng-init="treeArray=n.childrenList" ng-show="n.isOpen"></ul>
        </li>
    </script>
</div>
<script type="text/javascript">
    angular.module("app", []).controller("ctrl", function ($scope, $http) {
        $scope.treeArray = ${requestScope.carList};
        $scope.toggle = function (n, $event) {
            if ($event.target.tagName.toLowerCase() != 'p') return;
            $scope.spanToggle(n);
        };
        $scope.spanToggle = function (n) {
            n.isOpen = !n.isOpen;
            if (!n.isOpen || n.levels > 3 || n.childrenList.length != 0) return;
            $http.get("<%=basePath%>/products/carModel/listWithParentCode.html?parentCode=" + n.nodeCode).success(function (response) {
                n.childrenList = response;
            });
        };
        $scope.del = function ($index, treeArray) {
            if (!confirm('确定要删除吗？')) return;
            var id = treeArray[$index].id;
            $http.get("<%=basePath%>/products/carModel/" + id + "/del.html").success(function (response) {
                if (response == "0") {
                    treeArray.splice($index, 1);
                }
            });
        };
        $scope.edit = function (n) {
            n.originalNodeName = n.nodeName;
            n.isEdit = true;
        };
        $scope.editOk = function (n) {
            if (n.isEditing) return;
            if (n.nodeName == null || n.nodeName.trim().length == 0) {
                alert('名称不能为空！');
                return;
            }
            n.isEditing = true;
            $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'},
                method: 'POST',
                url: "<%=basePath%>/products/carModel/" + n.id + "/edit.html",
                params: {"nodeName": n.nodeName}
            }).success(function (response) {
                if (response == "0") {
                    n.isEdit = false;
                }
                n.isEditing = false;
            });
        };
        $scope.editKeyDown = function ($event, n) {
            if ($event.code != 'Enter') return;
            $scope.editOk(n);
        };
        $scope.editCancel = function (n) {
            n.nodeName = n.originalNodeName;
            n.isEdit = false;
        };
        $scope.add = function (n) {
            n.addedNodeName = "";
            n.isAdd = true;
            if (!n.isOpen) {
                $scope.spanToggle(n);
            }
        };
        $scope.addKeyDown = function ($event, n) {
            if ($event.code != 'Enter') return;
            $scope.addOk(n);
        };
        $scope.addCancel = function (n) {
            n.isAdd = false;
        };
        $scope.addOk = function (n) {
            if (n.isAdding) return;
            if (n.addedNodeName == null || n.addedNodeName.trim().length == 0) {
                alert('名称不能为空！');
                return;
            }
            n.isAdding = true;
            $http({
                headers: {'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8'},
                method: 'POST',
                url: "<%=basePath%>/products/carModel/" + n.id + "/add.html",
                params: {"addedNodeName": n.addedNodeName}
            }).success(function (response) {
                if (response.errorNum == '0') {
                    n.childrenList.push.apply(n.childrenList, response.data);
                    n.isAdd = false;
                }
                n.isAdding = false;
            });
        };
    });
</script>
</body>
</html>
