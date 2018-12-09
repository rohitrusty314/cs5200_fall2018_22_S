(function () {
    angular
        .module("MovieDBApp")
        .controller("SearchController", searchController);

    function searchController($rootScope, $scope, $location, $http) {
        var vm = this;
        function init() {
        }

        init();

        vm.watchlists = watchlists;
        vm.searchMovies = searchMovies;
        vm.searchIndividualMovie = searchIndividualMovie;

        function searchIndividualMovie(imdbId) {
            $rootScope.imdbId = imdbId;
            $location.url("/detail");

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
            // if (user.id === 0) {
            //     vm.alert = "User not found";
            // }
            $location.url("/watchlists");
        }

    }
})();
