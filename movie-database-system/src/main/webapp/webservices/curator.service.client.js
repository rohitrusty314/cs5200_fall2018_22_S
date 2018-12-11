(function () {
    angular
        .module("MovieDBApp")
        .factory("CuratorService", curatorService);

    function curatorService($rootScope, $http) {
        var api = {
            "findAllWatchlists": findAllWatchlists,
            "findAllEndorsedWatchlists": findAllEndorsedWatchlists,
            "endorseWatchlist": endorseWatchlist
        };
        return api;

        function findAllWatchlists() {
            return $http.get("/api/watchlists/all")
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllEndorsedWatchlists(userId) {
            return $http.get("/api/curator/" + userId + "/endorsed/watchlists/all")
                .then(function (response) {
                    return response.data;
                });
        }

        function endorseWatchlist(watchlistId, userId) {
            return $http.put("/api/curator/" + userId + "/endorse/watchlist/" + watchlistId)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();