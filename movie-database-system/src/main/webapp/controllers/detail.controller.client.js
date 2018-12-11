(function () {
    angular
        .module("MovieDBApp")
        .controller("MovieDetailController", movieDetailController);

    function movieDetailController($rootScope, $routeParams, $scope, $location, $http, ReviewService) {
        var vm = this;
        var imdbId = $routeParams['imdbId'];

        if($rootScope.userId) {
            vm.userId = $rootScope.userId;
        }

        if($rootScope.userRole === 'RESIDENT') {
            vm.resident = 'RESIDENT';
        } else if($rootScope.userRole === 'CRITIC') {
            vm.critic = 'CRITIC';
        }

         function init() {

            $http.get("http://www.omdbapi.com/?apikey=e0432e3d&i="+imdbId)
                .then(function (response) {
                    vm.detail = response.data
                });

            ReviewService
                .findResidentReviews(imdbId)
                .then(function (response) {
                    console.log(response);
                    vm.residentReviews = response;
                });


            ReviewService
                .findCriticReviews(imdbId)
                .then(function (response) {
                    vm.criticReviews = response;
                });

             ReviewService
                 .findRatingForMovie(imdbId)
                 .then(function (rating) {
                     console.log(rating)
                     vm.rating = rating;
                 });
        }

        init();

        vm.writeReview = writeReview;
        vm.upvote = upvote;
        vm.downvote = downvote;
        vm.endorse = endorse;
        vm.rate = rate;
        vm.addToWatchList = addToWatchList;
        vm.logout = logout;
        vm.login = login;

        function login() {

            $location.url("/login");

        }

        function addToWatchList(movie) {

            vm.alert1 = "";
            if($rootScope.userId === undefined) {
                vm.alert1 = "Please login to add movie to watchlist"
            } else {
                $location.url("/watchlists/" + movie.imdbID);
            }


        }
        function rate(rating, movieId) {
            var movieJson = {

                "name" :vm.detail.Title,
                "releaseDate" : vm.detail.Released,
                "genre" : vm.detail.Genre,
                "language" : vm.detail.Language,
                "imdbId" : imdbId

            };

            console.log(vm.detail);
            ReviewService
                .createMovie(movieJson)
                .then(function (movie) {
                    ReviewService
                        .rateMovie(rating,movie.id)
                        .then(function (movieId) {
                            ReviewService
                                .findRatingForMovie(imdbId)
                                .then(function (rating) {
                                    vm.rating = rating;
                                });
                        })
                })

        }

        function upvote(reviewId) {

            ReviewService
                .upVote(reviewId)
                .then(function (review) {
                    ReviewService
                        .findCriticReviews(imdbId)
                        .then(function (reviews) {
                            vm.criticReviews = reviews;
                        });
                })
        }


        function downvote(reviewId) {

            ReviewService
                .downVote(reviewId)
                .then(function (review) {

                    ReviewService
                        .findCriticReviews(imdbId)
                        .then(function (reviews) {
                            vm.criticReviews = reviews;
                        });
                })
        }

        function endorse(reviewId) {

            ReviewService
                .endorseCritic(reviewId)
                .then(function (review) {
                    ReviewService
                        .findResidentReviews(imdbId)
                        .then(function (reviews) {
                            vm.residentReviews = reviews;
                        });
                })
        }

        function writeReview(review) {
            vm.alert = "";

            console.log("user",  $rootScope.userId);

            if($rootScope.userId === undefined) {
                vm.alert = "Please login to write the review"
            } else {

                var movieJson = {

                    "name" :vm.detail.Title,
                    "releaseDate" : vm.detail.Released,
                    "genre" : vm.detail.Genre,
                    "language" : vm.detail.Language,
                    "imdbId" : imdbId

                };

                console.log(vm.detail);
                ReviewService
                    .createMovie(movieJson)
                    .then(function (movie) {

                        if($rootScope.userRole === 'RESIDENT') {

                            ReviewService
                                .createResidentReview(review, movie.id)
                                .then(function (response) {
                                    ReviewService
                                        .findResidentReviews(imdbId)
                                        .then(function (reviews) {
                                            vm.residentReviews = reviews;
                                        });
                                });
                        } else if($rootScope.userRole === 'CRITIC'){

                            ReviewService
                                .createCriticReview(review, movie.id)
                                .then(function (response) {
                                    ReviewService
                                        .findCriticReviews(imdbId)
                                        .then(function (reviews) {
                                            vm.criticReviews = reviews;
                                        });
                                });
                        }
                    })
            }
        }

        function logout() {
            vm = undefined;
            $rootScope.userId = undefined;
            $rootScope.userRole = undefined;
            // redirect logged in user to login page
            $http.get('/api/user/loggedout');
            $location.url('/search')
        }
    }
})();
