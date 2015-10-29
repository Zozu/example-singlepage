'use strict';
angular.module('example').controller('TableController', function ($scope, $location, UserService) {
    $scope.allUsers = UserService.GetAll();
    $scope.edit = function(unit){
        $location.path("/edit/" + unit.id);
    }
    $scope.addNew=function(){
        var implement = parseInt($scope.allUsers[$scope.allUsers.length - 1].id) + 1;
        $location.path("/edit/new/" + implement);
    }

});
