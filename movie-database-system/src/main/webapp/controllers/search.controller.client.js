(function () {
    angular
        .module("MovieDBApp")
        .controller("SearchController", searchController);

    function searchController($rootScope, $scope, $location, $http) {
        var vm = this;

        if($rootScope.userRole === 'ADMIN') {
            vm.admin = 'ADMIN';
        }

        if($rootScope.userRole === 'CENSOR') {
            vm.censor = 'CENSOR';
        }


        function init() {
        }

        init();

        vm.watchlists = watchlists;
        vm.searchMovies = searchMovies;
        vm.searchIndividualMovie = searchIndividualMovie;
        vm.profile = profile;
        vm.goToAdmin = goToAdmin;
        vm.goToCensor = goToCensor;

        function searchIndividualMovie(imdbId) {

            $location.url("/detail/" + imdbId);

        }

        function searchMovies(movie) {
            return $http.get("http://www.omdbapi.com/?apikey=e0432e3d&s="+movie)
                .then(function (response) {
                    console.log(response)
                    vm.movies = response.data.Search;
                });
            
        }

        function watchlists(user) {
            console.log("Getting watchlists for user");
            showUserWatchlists(user);
        }

        function showUserWatchlists(user) {
            console.log("Navigating to user watchlists page");
            $location.url("/watchlists");
        }

        function profile() {
            $location.url("/profile/" + $rootScope.userId);
        }

        function goToAdmin() {
            $location.url("/admin/" + $rootScope.userId);
        }

        function goToCensor() {
            $location.url("/censor/" + $rootScope.userId);
        }

    }
})();
