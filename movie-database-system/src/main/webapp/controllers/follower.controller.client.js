(function () {
    angular
        .module("MovieDBApp")
        .controller("FollowerController", followerController);

    function followerController($rootScope, $scope, $http, $location, $routeParams,  ReviewService, UserService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;
        function init() {

            UserService
                .findFollowersOfCritic(vm.userId)
                .then(function (followers) {
                    vm.followers = followers;
                })

            ReviewService
                .findAllReviewsByUserId(vm.userId)
                .then(function (reviews) {
                    vm.reviews = reviews;
                })
        }
        init();

        vm.logout = logout;

        function logout() {

            $rootScope.userId = undefined;
            $rootScope.userRole = undefined;
            // redirect logged in user to login page
            $http.get('/api/user/loggedout');
            $location.url('/search')
        }

    }

})();
