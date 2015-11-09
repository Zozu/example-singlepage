'use strict';
angular.module('example').controller('TableController', function ($scope, $location, UserService) {

    $scope.init = function () {
       return UserService.GetAll().then(function (response) {
            $scope.allUsersQuery = response;
            $scope.allUsers = decode($scope.allUsersQuery);
            $scope.edit = function (unit) {
                $location.path("/edit/" + unit.id);
            }
            $scope.addNew = function () {
                var implement = parseInt($scope.allUsers[$scope.allUsers.length - 1].id) + 1;
                $location.path("/edit/new/" + implement);
            }
            return response;
        });
    }
    $scope.init();

    function decode(query) {
        var usersArrayEncoded = query.split(";");
        var users = []
        for (var i = 0; i < usersArrayEncoded.length - 1; i++) {
            var tempUserQuery = usersArrayEncoded[i].split(":");
            users.push({
                id: tempUserQuery[0],
                username: tempUserQuery[1]
            });
        }
        return users;
    }

});
