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
            return $http.get("/api/uncensred/reviews")
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllEndorsedWatchlists() {
            return $http.get("/api/censoed/reviews")
                .then(function (response) {
                    return response.data;
                });
        }

        function endorseWatchlist(watchlistId, userId) {
            return $http.put("/api/censor/reiew/" + review.id + "/user/" + userId)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();