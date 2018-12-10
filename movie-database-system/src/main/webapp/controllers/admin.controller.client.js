(function () {
    angular
        .module("MovieDBApp")
        .controller("AdminController", adminController);

    function loginController($rootScope, $scope, $location, AdminService) {
        var vm = this;

        function init() {
            AdminService
                .findAllUsers
        }
        init();
    }

})();