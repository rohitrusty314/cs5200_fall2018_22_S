(function () {
    angular
        .module("MovieDBApp")
        .factory("ReviewService", reviewService);


    function reviewService($rootScope, $http) {
        var api = {
            "findResidentReviews": findResidentReviews,
            "findCriticReviews": findCriticReviews,
            "createResidentReview": createResidentReview,
            "createCriticReview" : createCriticReview,
            "createMovie" : createMovie
        };
        return api;

        function findResidentReviews(imdbId) {
            return $http.get("/api/movie/" + imdbId + "/reviews/resident")
                .then(function (response) {
                    return response.data;
                });
        }

        function findCriticReviews(imdbId) {
            return $http.get("/api/movie/" + imdbId + "/reviews/critic")
                .then(function (response) {
                    return response.data;
                });
        }

        function createResidentReview(review, imdbId) {
            var reviewJson = {"review" : review};
            return $http.post("/api/user/" + $rootScope.userId + "/movie/" + imdbId + "/review/resident",reviewJson)
                .then(function (response) {
                    return response.data;
                });
        }

        function createCriticReview(review, imdbId) {
            var reviewJson = {"review" : review};
            return $http.post("/api/user/" + $rootScope.userId + "/movie/" + imdbId + "/review/critic",reviewJson)
                .then(function (response) {
                    return response.data;
                });
        }

        function createMovie(movie) {

            return $http.post("/api/movie", movie)
                .then(function (response) {
                    return response.data;
                });
        }


    }
})();