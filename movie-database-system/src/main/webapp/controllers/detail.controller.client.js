(function () {
    angular
        .module("MovieDBApp")
        .controller("MovieDetailController", movieDetailController);

    function movieDetailController($rootScope, $scope, $location, $http, ReviewService) {
        var vm = this;
        function init() {
            var imdbId = ($rootScope.imdbId)

            $http.get("http://www.omdbapi.com/?apikey=e0432e3d&i="+imdbId)
                .then(function (response) {
                    vm.detail = response.data
                });

            ReviewService
                .findResidentReviews(imdbId)
                .then(function (response) {

                    console.log(response)
                });


            ReviewService
                .findCriticReviews(imdbId)
                .then(function (response) {

                    console.log(response)
                });
        }

        init();

        vm.writeReview = writeReview;

        function writeReview(review) {
            vm.alert = "";

            var movieJson = {

                "name" :vm.detail.Title,
                "releaseDate" : vm.detail.Released,
                "genre" : vm.detail.Genre,
                "language" : vm.detail.Language,
                "imdbId" : $rootScope.imdbId

            }

            console.log(vm.detail);
            ReviewService
                .createMovie(movieJson)
                .then(function (movie) {

                    if($rootScope.userRole === 'RESIDENT') {

                        ReviewService
                            .createResidentReview(review, movie.id)
                            .then(function (response) {  });
                    } else {

                        ReviewService
                            .createCriticReview(review, movie.id)
                            .then(function (response) {  });

                    }

                })

        }
    }
})();
