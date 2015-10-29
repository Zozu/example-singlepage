angular.module('example').controller('LoginController', function ($scope, $location, AuthenticationService) {
    $scope.user = {};
    $scope.login = function () {
        AuthenticationService.Login($scope.user.username, $scope.user.password, function (response) {
            //TODO
            if (response.success) {
                AuthenticationService.SetCredentials($scope.username, $scope.password);
                $location.path('/table');
            } else {
                alert("can't login");
            }
        });
        //$location.path('/table');
    }
});
