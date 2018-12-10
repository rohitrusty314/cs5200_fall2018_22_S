(function () {
    angular
        .module("MovieDBApp")
        .controller("RegisterController", registerController);

    function registerController($rootScope, $scope, $location, UserService) {
        var vm = this;

        if($rootScope.userRole === 'ADMIN') {
            vm.admin = 'ADMIN';
        }

        function init() {
        }

        init();
        vm.register = register;
        vm.registerByAdmin = registerByAdmin;
        vm.cancelRegister = cancelRegister;

        function cancelRegister() {
            $location.url("/admin/" + $rootScope.userId);

        }

        function registerByAdmin(user) {

            console.log("reached in register controller")
            vm.alert = "";
            if (!user.email || user.email === undefined) {
                vm.alert = "Invalid Email format";
            } else {
                if (user.password === user.verifypassword) {
                    UserService
                        .findUserByUsername(user.username)
                        .then(function (response) {
                            console.log(response)
                            if (response === '') {
                                UserService
                                    .createUser(user)
                                    .then(function (user) {
                                        $location.url("/admin/" + $rootScope.userId);
                                    })
                            } else {
                                vm.alert = "User Name already taken, Please try another one";
                            }
                        })
                } else {
                    vm.alert = "Password and verify password do not match";
                }
            }
        }

        function register(user) {

            console.log("reached in register controller")
            vm.alert = "";
            if (!user.email || user.email === undefined) {
                vm.alert = "Invalid Email format";
            } else {
                if (user.password === user.verifypassword) {
                    UserService
                        .findUserByUsername(user.username)
                        .then(function (response) {
                            console.log(response)
                            if (response === '') {
                                UserService
                                    .createUser(user)
                                    .then(function (user) {
                                        $location.url("/login");
                                    })
                            } else {
                                vm.alert = "User Name already taken, Please try another one";
                            }
                        })
                } else {
                    vm.alert = "Password and verify password do not match";
                }
            }
        }
    }
})();