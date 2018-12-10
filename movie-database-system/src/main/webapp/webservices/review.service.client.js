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
            "createMovie" : createMovie,
            "upVote" : upVote,
            "downVote" : downVote,
            "endorseCritic" : endorseCritic,
            "findRatingForMovie" : findRatingForMovie,
            "rateMovie" : rateMovie

        };
        return api;


        function rateMovie(rating, imdbId) {
            return $http.post("/api/user/" + $rootScope.userId + "/movie/"+ imdbId + "/rating", {rating : rating})
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });
        }

        function findRatingForMovie(imdbId) {
            return $http.get("/api/movie/" + imdbId + "/ratings/critic")
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });
        }
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

        function upVote(reviewId) {
            return $http.put("/api/critic/review/" + reviewId +"/upvote", {})
                .then(function (response) {
                    return response.data;
                });
        }

        function downVote(reviewId) {
            return $http.put("/api/critic/review/" + reviewId +"/downvote", {})
                .then(function (response) {
                    return response.data;
                });
        }

        function endorseCritic(reviewId) {

            return $http.put("/api/resident/review/" + reviewId +"/endorse", {})
                .then(function (response) {
                    return response.data;
                });
        }



    }
})();