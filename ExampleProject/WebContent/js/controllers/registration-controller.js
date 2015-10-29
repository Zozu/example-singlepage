angular.module('example').controller('RegistrationController', function ($scope, UserService, $location, AuthenticationService) {
    $scope.user = {};

    $scope.register = function () {
        AuthenticationService.ClearCredentials();
        if ($scope.user.password != $scope.user.confPass) {
            alert("different passwords");
            $scope.user = {}
        } else {
            UserService.Create($scope.user)
                .then(function (response) {
                    if (response.success) {
                        $location.path('/login');
                    } else {
                        alert("can't register");
                    }
                });
        }
    }
});
