(function () {
    angular
        .module("MovieDBApp")
        .controller("AdminController", adminController);

    function adminController($rootScope, $scope, $http, $location,$routeParams,  AdminService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;

        function init() {
            AdminService
                .findAllUsers(userId)
                .then(function (response) {
                    vm.users = response;
                });

            AdminService
                .findAllMovies()
                .then(function (response) {
                    vm.movies = response;
                });

        }

        init();

        vm.deleteUser = deleteUser;
        vm.addUser = addUser;
        vm.addMovie = addMovie;
        vm.updateUser = updateUser;
        vm.updateMovie = updateMovie;
        vm.deleteMovie = deleteMovie;
        vm.logout = logout;

        function addMovie() {
            $location.url("/movie");
        }

        function addUser() {
            $location.url("/register");
        }

        function updateUser(user) {
            $location.url("/profile/" + user.id);
        }

        function updateMovie(movie) {
            $rootScope.mId = movie.id;
            $location.url("/movie");
        }

        function deleteUser(user) {
            AdminService
                .deleteUser(user)
                .then(function (response) {
                    AdminService
                        .findAllUsers(vm.userId)
                        .then(function (response) {
                            console.log("ress");
                            console.log(response)
                            vm.users = response;
                        });
                });
        }

        function deleteMovie(movie) {
            AdminService
                .deleteMovie(movie)
                .then(function (response) {
                    AdminService
                        .findAllMovies()
                        .then(function (response) {
                            vm.movies = response;
                        });
                });
        }

        function logout() {

            vm = undefined;
            $rootScope.userId = undefined;
            $rootScope.userRole = undefined;

            $http.get('/api/user/loggedout');
            $location.url('/search')
        }
    }

})();