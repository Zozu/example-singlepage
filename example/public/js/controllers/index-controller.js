'use strict';
angular.module('example').controller('IndexController', function ($scope, $rootScope, $location, AuthenticationService) {
    $scope = $rootScope;
    $scope.isLogged = function() {
        return $scope.globals.currentUser != undefined;
    }
    $scope.logout = function () {
        AuthenticationService.ClearCredentials();
        $location.path('login');
    }
});
