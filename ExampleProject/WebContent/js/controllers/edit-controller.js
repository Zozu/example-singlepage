'use strict';
angular.module('example').controller('EditController', function ($scope, $location, UserService, $route, $routeParams) {

    $scope.getAll = function () {
        return UserService.GetById($routeParams.userId).then(function (response) {
            $scope.unit.username = response.split(":")[1];
            $scope.update = function () {
                UserService.Update($scope.unit).then(function (status) {
                    if (status == "OK") $location.path("table");
                    else alert(status);
                });
            }
            $scope.del = function () {
                UserService.Delete($routeParams.userId).then(function (status) {
                    if (status == "OK") $location.path("table");
                    else alert(status);
                });
            }
        });
    }
    $scope.getAll();
    $scope.deleteShow = true;
    $scope.params = $routeParams;
    $scope.unit = {
        id: $routeParams.userId
    }
});
