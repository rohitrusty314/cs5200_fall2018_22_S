(function () {
    angular
        .module("MovieDBApp")
        .controller("AdminController", adminController);

    function adminController($rootScope, $scope, $location, AdminService) {
        var vm = this;

        function init() {
            AdminService
                .findAllUsers()
                .then(function (response) {
                    vm.users = response;
                });

        }

        init();

        vm.deleteUser = deleteUser;

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