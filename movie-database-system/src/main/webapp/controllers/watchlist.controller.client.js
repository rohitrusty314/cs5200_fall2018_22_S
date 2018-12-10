(function () {
    angular
        .module("MovieDBApp")
        .controller("WatchlistController", watchlistController);

    function watchlistController($rootScope, $routeParams,$scope, $location, $http, ReviewService, WatchlistService) {
        var vm = this;

        vm.userId = $rootScope.userId;
        var imdbId = $routeParams['imdbId'];
        vm.imdbId = imdbId;
        console.log(imdbId);
        function init() {

            $http.get("http://www.omdbapi.com/?apikey=e0432e3d&i="+imdbId)
                .then(function (response) {
                    vm.detail = response.data
                });

            WatchlistService
                .findWatchlistsByUserId(vm.userId)
                .then(function (response) {
                    console.log(response);
                    vm.watchlists = response;
                });

        }

        init();
        vm.watchlists = watchlists;
        vm.addMovieToWatchlist = addMovieToWatchlist;
        vm.createWatchlist = createWatchlist;
        vm.removeMovieFromWatchlist = removeMovieFromWatchlist;
        vm.toggleWatched = toggleWatched;
        vm.deleteWatchlist = deleteWatchlist;

        function deleteWatchlist(watchlistId) {

            WatchlistService
                .deleteWatchlist(watchlistId)
                .then(function (wlst) {
                    WatchlistService
                        .findWatchlistsByUserId(vm.userId)
                        .then(function (response) {
                            console.log(response);
                            vm.watchlists = response;
                        });
                });
        }

        function createWatchlist(wName) {

            WatchlistService
                .createWatchlist(wName, vm.userId)
                .then(function (wlst) {
                    WatchlistService
                        .findWatchlistsByUserId(vm.userId)
                        .then(function (response) {
                            console.log(response);
                            vm.watchlists = response;
                        });
                });

        }


        function removeMovieFromWatchlist(watchlistId, movieId) {

            console.log(watchlistId)
            console.log(movieId);

            WatchlistService
                .removeMovieFromWatchlist(watchlistId, movieId)
                .then(function (wlst) {
                    WatchlistService
                        .findWatchlistsByUserId(vm.userId)
                        .then(function (response) {
                            console.log(response);
                            vm.watchlists = response;
                        });
                });
        }


        function toggleWatched(watchlistId, movieId, watched) {

            console.log(watchlistId);
            console.log(movieId);
            console.log(watched);

            WatchlistService
                .toggleWatched(watchlistId, movieId, watched)
                .then(function (wlst) {
                    WatchlistService
                        .findWatchlistsByUserId(vm.userId)
                        .then(function (response) {
                            console.log(response);
                            vm.watchlists = response;
                        });
                });

        }
        function addMovieToWatchlist(watchlistId) {

            var movieJson = {

                "name" :vm.detail.Title,
                "releaseDate" : vm.detail.Released,
                "genre" : vm.detail.Genre,
                "language" : vm.detail.Language,
                "imdbId" : imdbId

            };
            ReviewService
                .createMovie(movieJson)
                .then(function (movie) {
                    WatchlistService
                        .addMovieToWatchlist(movie.id, watchlistId)
                        .then(function (value) {
                            WatchlistService
                                .findWatchlistsByUserId(vm.userId)
                                .then(function (response) {
                                    console.log(response);
                                    vm.watchlists = response;
                                });
                        })
                })
        }

        function watchlists(user) {
            console.log("");

        }
    }
})();