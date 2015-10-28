'use strict';
angular.module('example').controller('IndexController', function ($scope, $rootScope, AuthenticationService) {
    $scope.isAuthorized = function () {
        return $rootScope.globals.currentUser;
    }
    $scope.logout = function () {
        AuthenticationService.ClearCredentials();
    }
});
