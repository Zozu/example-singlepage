'use strict';
angular
    .module('example')
    .factory('UserService', function ($http, $q) {
        var $scope = {};

        $scope.GetAll = GetAll;
        $scope.GetById = GetById;
        $scope.Create = Create;
        $scope.Update = Update;
        $scope.Delete = Delete;
        $scope.Registrate = Registrate;
        $scope.method = "POST";
        return $scope;

        function GetAll() {
            return $http({
                method: $scope.method,
                url: "/users/all"
            }).
            then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http({
                url: "/user",
                method: "POST",
                data: {
                    id: id
                }
            }).then(handleSuccess, handleError('Error getting user by id'));

        }

        function Create(user) {
            return $http({
                url: "/users/add",
                method: "POST",
                data: {
                    id: user.id,
                    username: user.username
                }
            }).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http({
                url: "/users/update",
                method: "POST",
                data: {
                    id: user.id,
                    username: user.username
                }
            }).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http({
                url: "/users/delete",
                method: "POST",
                data: {
                    id: id
                }
            }).then(handleSuccess, handleError('Error deleting user'));
        }

        function Registrate(user) {
            return $http({
                url: "/members/add",
                method: "POST",
                data: {
                    username: user.username,
                    password: user.password
                }
            }).then(handleSuccess, handleError('Error creating user'));
        }


        function handleSuccess(res) {
            return res.data;
        }

        function handleError(error) {
            return function () {
                return {
                    success: false,
                    message: error
                };
            };
        }
    });
