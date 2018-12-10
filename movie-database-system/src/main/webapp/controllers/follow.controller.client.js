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
                .then(function (user) {
                    vm.allCritics = user.allCritics;
                    console.log(vm.allCritics);
                })
        }
        init();

        vm.followCritic = followCritic;

        function followCritic(user) {

            console.log(user);
            vm.alert = "";
            vm.message = "";
            //
            // UserService
            //     .updateUser(user)
            //     .then( function (value) {
            //         UserService
            //             .findUserById(vm.userId)
            //             .then(function (user) {
            //                 console.log(user);
            //                 vm.user = user;
            //                 vm.user.dob = new Date(user.dob);
            //             })
            //     });
        }

    }

})();
