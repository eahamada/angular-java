'use strict';

// Declare app level module which depends on views, and components
angular.module('habilist', [
  'ngRoute',
  'habilist.home',
  'habilist.login'
]).
config(['$routeProvider','$httpProvider', function($routeProvider,$httpProvider) {
  $routeProvider.otherwise({redirectTo: '/home'});
  $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';
}]);