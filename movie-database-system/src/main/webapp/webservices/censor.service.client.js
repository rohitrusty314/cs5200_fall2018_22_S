(function () {
    angular
        .module("MovieDBApp")
        .factory("CensorService", censorService);

    function censorService($rootScope, $http) {
        var api = {
            "findAllCensoredReviews": findAllCensoredReviews,
            "findAllUnCensoredReviews": findAllUnCensoredReviews,
            "flagReview": flagReview
        };
        return api;

        function findAllUnCensoredReviews() {
            return $http.get("/api/uncensored/reviews")
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllCensoredReviews() {
            return $http.get("/api/censored/reviews")
                .then(function (response) {
                    return response.data;
                });
        }

        function flagReview(review, userId) {
            return $http.put("/api/censor/review/" + review.id + "/user/" + userId)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();