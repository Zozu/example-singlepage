angular.module('example').controller('RegistrationController', function ($scope, UserService, $location, AuthenticationService) {
    $scope.user = {};

    $scope.register = function () {
        AuthenticationService.ClearCredentials();
        if ($scope.user.password != $scope.user.confPass) {
            alert("different passwords");
            $scope.user = {}
        } else {
            $scope.regUser = {
                username: $scope.user.username,
                password: $scope.user.password
            }
            UserService.Registrate($scope.regUser)
                .then(function (response) {
                    if (response == "OK") $location.path('login');
                    else alert(response);
                });
        }
    }
});
