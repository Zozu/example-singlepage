angular.module('example').controller('RegistrationController', function($scope, UserService, $location, $rootScope, FlashService){
        $scope.register = register;

        function register() {
            UserService.Create($scope.user)
                .then(function (response) {
                    if (response.success) {
                        FlashService.Success('Registration successful', true);
                        $location.path('/login');
                    } else {
                        FlashService.Error(response.message);
                        $scope.dataLoading = false;
                    }
                });
        }
});
