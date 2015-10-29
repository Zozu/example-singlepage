'use strict';
angular.module('example').controller('AddController', function ($scope, $location, UserService, $route, $routeParams) {
    $scope.deleteShow = false;
    $scope.new_id = $routeParams.newId;
    $scope.unit = {
        id: $scope.new_id
    };
    $scope.add = function () {
        UserService.Create($scope.unit);
        $location.path("table");
    }
});
