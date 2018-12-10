(function () {
    angular
        .module("MovieDBApp")
        .controller("FollowerController", followerController);

    function followerController($rootScope, $scope, $location, $routeParams,  UserService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;
        function init() {

            UserService
                .findFollowersOfCritic(vm.userId)
                .then(function (followers) {
                    vm.followers = followers;
                })
        }
        init();

    }

})();
