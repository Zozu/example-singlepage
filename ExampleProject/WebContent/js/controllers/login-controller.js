angular.module('example').controller('LoginController', function ($scope, $location, AuthenticationService) {
    $scope.user = {};
    $scope.login = function () {
        AuthenticationService.Login($scope.user.username, $scope.user.password, function (responseData) {
            if (responseData.data == "OK") {
                AuthenticationService.SetCredentials($scope.user.username, $scope.user.password);
                $location.path('/table');
            } else {
                alert(responseData.data);
            }
        });
    }
});
