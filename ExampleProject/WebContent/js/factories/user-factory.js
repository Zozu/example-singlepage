'use strict';
angular
    .module('example')
    .factory('UserService', function ($http) {
        var $scope = {};

        $scope.GetAll = GetAll;
        $scope.GetById = GetById;
        $scope.Create = Create;
        $scope.Update = Update;
        $scope.Delete = Delete;
        $scope.Registrate = Registrate;

        return $scope;
        //TODO


        function GetAll() {
            return $http.post('/ExampleServlet/users/all').then(handleSuccess, handleError('Error getting all users'));

            /* var res = [{
                 id: "1",
                 username: "111"
             }, {
                 id: "2",
                 username: "222"
             }, {
                 id: "3",
                 username: "333"
             }];
             return res;*/
        }

        function GetById(id) {
            return $http.post('/ExampleServlet/user', {
                id: id
            }).then(handleSuccess, handleError('Error getting user by id'));
            /*var res = [{
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
            }*/

        }

        /*
                useless
                function GetByUsername(username) {
                    return $http.get('/ExampleServlet/users/' + username).then(handleSuccess, handleError('Error getting user by username'));
                }*/

        function Create(user) {
            console.log("create: ");
            console.log(user);
            return $http.post('/ExampleServlet/users/add', {
                user: user
            }).then(handleSuccess, handleError('Error creating user'));
        }

        function Update(user) {
            console.log("update: ");
            console.log(user);
            return $http.post('/ExampleServlet/users/update', {
                id: user.id,
                user: user
            }).then(handleSuccess, handleError('Error updating user'));
        }

        function Delete(id) {
            console.log("delete: ");
            console.log(id);
            return $http.post('/ExampleServlet/users/delete', {
                id: id
            }).then(handleSuccess, handleError('Error deleting user'));
        }

        function Registrate(user) {
            console.log("registrate: ");
            console.log(user);
            return $http.post('/ExampleServlet/members/add', {
                user: user
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
