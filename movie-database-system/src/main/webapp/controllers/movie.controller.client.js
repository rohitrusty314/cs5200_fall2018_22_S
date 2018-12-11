(function () {
    angular
        .module("MovieDBApp")
        .controller("MovieController", movieController);

    function movieController($rootScope, $scope, $location, $routeParams,  AdminService) {
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

    }

})();
