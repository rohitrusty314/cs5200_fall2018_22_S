(function () {
    angular
        .module("MovieDBApp")
        .controller("AdminController", adminController);

    function adminController($rootScope, $scope, $location,$routeParams,  AdminService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;

        function init() {
            AdminService
                .findAllUsers(userId)
                .then(function (response) {
                    vm.users = response;
                });

        }

        init();

        vm.deleteUser = deleteUser;
        vm.addUser = addUser;
        vm.updateUser = updateUser;

        function addUser() {
            $location.url("/register");
        }

        function updateUser(user) {
            $location.url("/profile/" + user.id);
        }

        function deleteUser(user) {
            AdminService
                .deleteUser(user)
                .then(function (response) {
                    AdminService
                        .findAllUsers()
                        .then(function (response) {
                            vm.users = response;
                        });
                });
        }
    }

})();