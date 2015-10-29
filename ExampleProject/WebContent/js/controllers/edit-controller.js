'use strict';
angular.module('example').controller('EditController', function ($scope, $location, UserService, $route, $routeParams) {

    $scope.deleteShow = true;
    $scope.params = $routeParams;
    $scope.id_parameter = $routeParams.userId;
    $scope.unit = UserService.GetById($scope.id_parameter);
    $scope.update= function(){
        UserService.Update($scope.unit);
        $location.path("/dataTable");
    }
    $scope.delete = function(){
        UserService.Delete($scope.id_parameter);
        $location.path("/dataTable");
    }
});
