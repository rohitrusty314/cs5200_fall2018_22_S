(function () {
    angular
        .module("MovieDBApp")
        .factory("WatchlistService", watchlistService);

    function watchlistService($http) {
        var api = {
            "findWatchlistsByUser": findWatchlistsByUserId
        }

        return api;

        function findWatchlistsByUserId(userId) {
            return $http.get("/api/user/" + userId + "/watchlists")
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();