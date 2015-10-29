'use strict';
angular.module('example')
    .factory('AuthenticationService', function ($http, $cookieStore, $rootScope, UserService) {
        var $scope = {};

        $scope.Login = Login;
        $scope.SetCredentials = SetCredentials;
        $scope.ClearCredentials = ClearCredentials;

        return $scope;

        function Login(username, password, callback) {
            //TODO
            $http.post('/ExampleServlet/member', {
                    username: username,
                    password: password
                })
                .success(function (response) {
                    callback(response);
                });
            //SetCredentials(username, password);
        }

        function SetCredentials(username, password) {
            var authdata = username + ':' + password;

            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };

            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata;
            $cookieStore.put('globals', $rootScope.globals);
        }

        function ClearCredentials() {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
        }
    });
