'use strict';

angular.module('habilist.login', ['ngRoute', 'ngResource'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'scripts/app/login/login.html',
    controller: 'LoginCtrl'
  });
}])

.factory('myService', function($http) {
  return {
	  getData: function() {
		  return $http({method:'GET',url:'/habilist/api/myresource'});
    }
  }
})

.controller('LoginCtrl',['$rootScope','$http', '$location',	function($rootScope, $http, $location) {

		  var self = this;

		  var authenticate = function(credentials, callback) {
		    var headers = credentials ? {
		    	authorization : "Basic "+ btoa(credentials.username + ":" + credentials.password)
		    } : {};

		    $http.get('user', {headers : headers}).success(function(data) {
		      if (data.name) {
		        $rootScope.authenticated = true;
		      } else {
		        $rootScope.authenticated = false;
		      }
		      callback && callback();
		    }).error(function() {
		      $rootScope.authenticated = false;
		      callback && callback();
		    });

		  }

		  authenticate();
		  self.credentials = {};
		  self.login = function() {
		      authenticate(self.credentials, function() {
		        if ($rootScope.authenticated) {
		          $location.path("/");
		          self.error = false;
		        } else {
		          $location.path("/login");
		          self.error = true;
		        }
		      });
		  };
}]);
