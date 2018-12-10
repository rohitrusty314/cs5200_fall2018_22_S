(function () {
    angular
        .module("MovieDBApp")
        .controller("FollowController", followController);

    function followController($rootScope, $scope, $location, $routeParams,  UserService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;
        function init() {

            UserService
                .findUserById(vm.userId)
                .then(function (user) {
                    vm.followsCritics = user.followsCritics;
                    console.log(vm.followsCritics);
                })

            UserService
                .findAllCritics()
                .then(function (critics) {
                    vm.allCritics = critics;
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
