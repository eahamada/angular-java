'use strict';

angular.module('habilist.home', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'scripts/app/home/home.html',
    controller: 'HomeCtrl'
  });
}])

.factory('myService', function($http) {
  return {
	  getData: function() {
		  return $http({method:'GET',url:'/habilist/api/myresource'});
    }
  }
})

.controller('HomeCtrl',['$scope','myService', function($scope, myService) {
	myService.getData().success(function(data,status,config, headers){
		$scope.data=data;
		console.log("ok"+data);
	}).error(function(data,status,config,headers){
	});
	
}]);
