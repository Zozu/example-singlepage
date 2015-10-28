angular.module('example').config(['$routeProvider',
  function ($routeProvider) {
        $routeProvider.
        when('/', {
            templateUrl: 'tpl/login.html',
            controllerAs: 'LoginController'
        }).
        when('/login', {
            templateUrl: 'tpl/login.html',
            controllerAs: 'LoginController'
        }).
        when('/table', {
            templateUrl: 'tpl/dataTable.html',
            controllerAs: 'TableController'
        }).
        when('/edit', {
            templateUrl: 'tpl/edit.html',
            controllerAs: 'EditController'
        }).
        when('/registration', {
            templateUrl: 'tpl/registration.html',
            controllerAs: 'RegistrationController'
        }).
        otherwise({
            redirectTo: '/'
        });
}]);
