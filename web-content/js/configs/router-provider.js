angular.module('example').config(['$routeProvider',
  function ($routeProvider) {
        $routeProvider.
        when('/', {
            templateUrl: 'tpl/login.html',
            controller: 'LoginController'
        }).
        when('/login', {
            templateUrl: 'tpl/login.html',
            controller: 'LoginController'
        }).
        when('/table', {
            templateUrl: 'tpl/dataTable.html',
            controller: 'TableController'
        }).
        when('/edit/:userId', {
            templateUrl: 'tpl/edit.html',
            controller: 'EditController'
        }).
        when('/edit/new/:newId', {
            templateUrl: 'tpl/edit.html',
            controller: 'AddController'
        }).
        when('/registration', {
            templateUrl: 'tpl/registration.html',
            controller: 'RegistrationController'
        }).
        otherwise({
            redirectTo: '/'
        });
}]);
