(function () {
    angular
        .module("MovieDBApp")
        .controller("LoginController", loginController);

    function loginController($rootScope, $scope, $location, UserService) {
        var vm = this;

        // event handlers
        vm.login = login;

        function init() {
        }

        init();

        function login(user) {

            console.log("In login Controller");
            vm.alert = "";

            UserService
                .findUserByCredentials(user)
                .then(searchForMovies);
        }

        function searchForMovies(user) {

            console.log(user)
            if (user.id === 0) {
                vm.alert = "User not found";
            }
           else {
                $rootScope.userId = user.id;
                $rootScope.userRole = user.role;
                console.log("rootUserId", $rootScope.userId)
                $location.url("/search");
            }
        }
    }
})();
