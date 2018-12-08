(function () {
    angular
        .module("MovieDBApp")
        .controller("LoginController", loginController);

    function loginController($scope, $location, UserService) {
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
            if (user.id === 0) {
                vm.alert = "User not found";
            }
           else {
               $location.url("/search");
            }
        }
    }
})();
