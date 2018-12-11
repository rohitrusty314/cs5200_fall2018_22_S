(function () {
    angular
        .module("MovieDBApp")
        .controller("MovieController", movieController);

    function movieController($rootScope, $scope, $http, $location, $routeParams,  AdminService) {
        var vm = this;

        vm.mId = $rootScope.mId;
        function init() {

            if(vm.mId) {
                AdminService.findMovieById(vm.mId)
                    .then(function (response) {
                        vm.movie = response;
                    })
            }
        }

        init();

        vm.createMovie = createMovie;
        vm.updateMovie = updateMovie;
        vm.cancelCreate = cancelCreate;
        vm.logout = logout;

        function cancelCreate() {
            $location.url("/admin/" + $rootScope.userId);
        }

        function updateMovie(movie) {

            vm.alert = "";
            vm.message = "";

            AdminService
                .updateMovie(vm.mId, movie)
                .then( function (response) {
                    $location.url("/admin/" + $rootScope.userId);
                });
        }

        function createMovie(movie) {

            vm.alert = "";
            vm.message = "";

            AdminService
                .createMovie(movie)
                .then( function (response) {
                    $location.url("/admin/" + $rootScope.userId);
                });
        }

        function logout() {

            vm = undefined;
            $rootScope.mId = undefined;
            $rootScope.userId = undefined;
            $rootScope.userRole = undefined;
            // redirect logged in user to login page
            $http.get('/api/user/loggedout');
            $location.url('/search')
        }

    }

})();
