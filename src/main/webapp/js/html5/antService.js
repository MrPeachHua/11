/**
 * Created by Administrator on 2016/4/13.
 */
'use strict';
var myService=angular.module('antServices',[]);
myService.factory('mainService',['$http',function($http){
    return {
        getStationDetail: function (stationId,typeId,page) {
            if(page==1){
                return $http.get("../../js/police/stationDetailData.json")
            }else{
                return $http.get("../../js/police/stationDetailDataOther.json")
            }

        }
    }
}]);
