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
        $scope.BASE_URL = "project";
        $scope.method = "POST";
        return $scope;

        function GetAll() {
            return $http({
                method: $scope.method,
                url: $scope.BASE_URL + "/users/all"
            }).
            then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http({
                url: $scope.BASE_URL + "/user",
                method: "POST",
                params: {
                    id: id
                }
            }).then(handleSuccess, handleError('Error getting user by id'));

        }

        function Create(user) {
            return $http({
                url: $scope.BASE_URL + "/users/add",
                method: "POST",
                params: {
                    id: user.id,
                    username: user.username
                }
            }).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http({
                url: $scope.BASE_URL + "/users/update",
                method: "POST",
                params: {
                    id: user.id,
                    username: user.username
                }
            }).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http({
                url: $scope.BASE_URL + "/users/delete",
                method: "POST",
                params: {
                    id: id
                }
            }).then(handleSuccess, handleError('Error deleting user'));
        }

        function Registrate(user) {
            return $http({
                url: $scope.BASE_URL + "/members/add",
                method: "POST",
                params: {
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
