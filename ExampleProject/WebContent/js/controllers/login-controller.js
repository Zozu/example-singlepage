angular.module('example').controller('LoginController', function ($scope, $location, AuthenticationService) {
    $scope.user = {};
    $scope.login = function () {
        AuthenticationService.ClearCredentials();
        AuthenticationService.Login($scope.user.username, $scope.user.password, function (response) {
            //TODO
            //TODO get id
            /*if (response.success) {
                AuthenticationService.SetCredentials($scope.username, $scope.password);
                $location.path('/dataTable');
            } else {
                alert("can't login");
            }*/
        });
    }
});
