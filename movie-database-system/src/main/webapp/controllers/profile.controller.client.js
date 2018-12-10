(function () {
    angular
        .module("MovieDBApp")
        .controller("ProfileController", profileController);

    function profileController($rootScope, $scope, $location, $routeParams,  UserService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;
        function init() {


            UserService
                .findUserById(vm.userId)
                .then(function (user) {

                    if(user.role === 'RESIDENT') {
                        vm.resident = 'RESIDENT';
                    } else if(user.role === 'CRITIC') {
                        vm.critic = 'CRITIC';
                    }
                    vm.user = user;
                    vm.user.dob = new Date(user.dob);
                })
        }
        init();

        vm.updateUser = updateUser;
        vm.goToFollowCritic = goToFollowCritic;
        vm.goToFollowers = goToFollowers;

        function updateUser(user) {

            console.log(user);
            vm.alert = "";
            vm.message = "";

            UserService
                .updateUser(user)
                .then( function (value) {
                    UserService
                        .findUserById(vm.userId)
                        .then(function (user) {
                            console.log(user);
                            vm.user = user;
                            vm.user.dob = new Date(user.dob);
                        })
                   vm.message = "Profile Updated Successfully";
                });
        }


        function goToFollowCritic() {
            $location.url("/followCritic/" + vm.userId);
        }

        function goToFollowers() {
            $location.url("/followers/" + vm.userId);
        }
    }

})();
