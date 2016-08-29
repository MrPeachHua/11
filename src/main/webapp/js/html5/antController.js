/**
 * Created by Administrator on 2016/4/13.
 */
'use strict';
var myControllers=angular.module('antControllers',[]);
myControllers.controller('Maintrl',function($scope, $stateParams, mainService){
    console.log("Maintrl");
    //请求机构数据
    mainService.getStationDetail(1,2,1).success(function(req){
        $scope.items=req.Results;
        $scope.onPageChange = function() {
            console.log($scope.currentPage);
        };
        $scope.pageCount = 12;
    }).error(function(data, status){
        console.log("error")
    })
});
myControllers.controller('PlaylistsCtrl', function () {
    console.log("PlaylistsCtrl")
})
