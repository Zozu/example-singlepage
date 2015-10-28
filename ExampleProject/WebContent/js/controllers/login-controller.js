angular.module('example').controller('LoginController', function($scope, $location, AuthenticationService, FlashService){
        $scope.login = login;

        function login() {
            AuthenticationService.Login($scope.username, $scope.password, function (response) {
                if (response.success) {
                    AuthenticationService.SetCredentials($scope.username, $scope.password);
                    $location.path('/');
                } else {
                    FlashService.Error(response.message);
                    $scope.dataLoading = false;
                }
            });
        };
});
