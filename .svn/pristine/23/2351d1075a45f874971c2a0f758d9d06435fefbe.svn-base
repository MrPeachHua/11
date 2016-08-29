'use strict';
var app =angular.module('MyApp',[
    'ui.router',
    'antControllers',
    'antServices',
]);
app.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.when('','/home');
    $stateProvider
        .state('home',{
            url:'/home',
            templateUrl:'index.html',
            controller:'Maintrl'
        })
        .state('home.playlists', {
            url: "/playlists",
            views: {
                'homeCont' :{
                    templateUrl: "../templates/list.html",
                    controller: 'PlaylistsCtrl'
                }
            }
        })
})
