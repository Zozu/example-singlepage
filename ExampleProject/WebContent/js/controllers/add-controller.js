'use strict';
angular.module('example').controller('AddController', function ($scope, $location, UserService, $route, $routeParams) {
    $scope.deleteShow = false;
    $scope.new_id = $routeParams.newId;
    $scope.unit = {
        id: $scope.new_id
    };
    $scope.update = function () {
        UserService.Create($scope.unit).then(function (status) {
            if (status == "OK") $location.path("table");
            else alert(status);
        });
    }
});
