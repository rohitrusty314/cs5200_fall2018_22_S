(function () {
    angular
        .module("MovieDBApp")
        .controller("FollowController", followController);

    function followController($rootScope, $scope, $location, $routeParams,  UserService, ReviewService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;
        function init() {

            UserService
                .findUserById(vm.userId)
                .then(function (user) {
                    vm.followsCritics = user.followsCritics;
                })

            UserService
                .findAllCritics()
                .then(function (critics) {
                    vm.allCritics = critics;
                })

            ReviewService
                .findAllReviewsByUserId(vm.userId)
                .then(function (reviews) {
                    vm.reviews = reviews;
                })
        }
        init();

        vm.followCritic = followCritic;

        function followCritic(criticId) {

            vm.alert = "";
            vm.message = "";

            UserService
                .followCritic(criticId, vm.userId)
                .then( function (response) {
                    UserService
                        .findUserById(vm.userId)
                        .then(function (user) {
                            vm.followsCritics = user.followsCritics;
                        })
                });
        }

    }

})();
