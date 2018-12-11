(function () {
    angular
        .module("MovieDBApp")
        .controller("CensorController", censorController);

    function censorController($rootScope, $scope, $location,$routeParams,  CensorService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;

        function init() {
            CensorService
                .findAllCensoredReviews()
                .then(function (response) {
                    vm.censoredReviews = response;
                });

            CensorService
                .findAllUnCensoredReviews()
                .then(function (response) {
                    vm.uncensoredReviews = response;
                });

        }

        init();

        vm.flagReview = flagReview;

        function flagReview(review) {

            console.log(review)
            CensorService
                .flagReview(review, vm.userId)
                .then(function (response) {
                    CensorService
                        .findAllCensoredReviews()
                        .then(function (response) {
                            vm.censoredReviews = response;
                        });

                    CensorService
                        .findAllUnCensoredReviews()
                        .then(function (response) {
                            vm.uncensoredReviews = response;
                        });
                });
        }
    }

})();