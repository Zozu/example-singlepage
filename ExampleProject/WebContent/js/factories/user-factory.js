'use strict';
angular
    .module('example')
    .factory('UserService', function ($http) {
        var $scope = {};

        $scope.GetAll = GetAll;
        $scope.GetById = GetById;
        $scope.GetByUsername = GetByUsername;
        $scope.Create = Create;
        $scope.Update = Update;
        $scope.Delete = Delete;

        return $scope;
    //TODO

        function GetAll() {
            return $http.get('/ExampleServlet/users').then(handleSuccess, handleError('Error getting all users'));
        }

        function GetById(id) {
            return $http.get('/ExampleServlet/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
        }

        function GetByUsername(username) {
            return $http.get('/ExampleServlet/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            return $http.post('/ExampleServlet/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            return $http.put('/ExampleServlet/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            return $http.delete('/ExampleServlet/users/' + id).then(handleSuccess, handleError('Error deleting user'));
        }

        // private functions

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
