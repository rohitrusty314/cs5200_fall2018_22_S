(function () {
    angular
        .module("MovieDBApp")
        .factory("WatchlistService", watchlistService);

    function watchlistService($http) {
        var api = {
            "findWatchlistsByUserId": findWatchlistsByUserId,
            "addMovieToWatchlist" : addMovieToWatchlist,
            "createWatchlist" :createWatchlist,
            "removeMovieFromWatchlist" : removeMovieFromWatchlist,
            "toggleWatched" : toggleWatched,
            "deleteWatchlist" : deleteWatchlist

        }

        return api;
        
        function deleteWatchlist(watchlistId) {
            return $http.delete("/api/watchlist/" + watchlistId + "/delete")
                .then(function (response) {
                    return response.data;
                });
        }


        function createWatchlist(wName, userId) {
            return $http.post("/api/user/" + userId + "/watchlist", {"name" : wName})
                .then(function (response) {
                    return response.data;
                });
        }

        function removeMovieFromWatchlist(watchlist, movieId) {
            return $http.put("/api/watchlist/" + watchlist + "/movie/" + movieId + "/delete", {})
                .then(function (response) {
                    return response.data;
                });
        }


        function toggleWatched(watchlistId, movieId, watched) {
            return $http.get("/api/watchlist/" + watchlistId + "/movie/" + movieId + "/watch?watched=" + !watched)
                .then(function (response) {
                    return response.data;
                });
        }
        function findWatchlistsByUserId(userId) {
            return $http.get("/api/user/" + userId + "/watchlists")
                .then(function (response) {
                    return response.data;
                });
        }


        function addMovieToWatchlist(movieId, watchlistId) {
            return $http.put("/api/watchlist/" + watchlistId + "/movie/" + movieId + "/add", {})
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();