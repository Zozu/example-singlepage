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
            //return $http.get('/ExampleServlet/users').then(handleSuccess, handleError('Error getting all users'));
            var res = [{
                id: "1",
                username: "111"
            }, {
                id: "2",
                username: "222"
            }, {
                id: "3",
                username: "333"
            }];
            return res;
        }

        function GetById(id) {
            //return $http.get('/ExampleServlet/users/' + id).then(handleSuccess, handleError('Error getting user by id'));
            var res = [{
                id: "1",
                username: "111"
            }, {
                id: "2",
                username: "222"
            }, {
                id: "3",
                username: "333"
            }];
            for (var i = 0; i < res.length; i++) {
                if (res[i].id == id) return res[i];
            }

        }

        function GetByUsername(username) {
            return $http.get('/ExampleServlet/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
        }

        function Create(user) {
            console.log("create: ");
            console.log(user);
            //return $http.post('/ExampleServlet/users', user).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            console.log("update: ");
            console.log(user);
            //return $http.put('/ExampleServlet/users/' + user.id, user).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            console.log("delete: ");
            console.log(id);
            //return $http.delete('/ExampleServlet/users/' + id).then(handleSuccess, handleError('Error deleting user'));
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
