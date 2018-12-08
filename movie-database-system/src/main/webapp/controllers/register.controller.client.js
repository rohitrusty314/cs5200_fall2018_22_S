(function () {
    angular
        .module("MovieDBApp")
        .controller("RegisterController", registerController);

    function registerController($scope, $location) {
        var vm = this;

        function init() {
        }

        init();
        vm.register = register;

        function register(user) {

            // console.log("reached in register controller")
            // vm.alert = "";
            // if (!user.email || user.email === undefined) {
            //     vm.alert = "Invalid Email format";
            // } else {
            //     if (user.password === user.verifypassword) {
            //         UserService
            //             .findUserByUsername(user.username)
            //             .then(function (response) {
            //                 if (response === '') {
            //                     UserService
            //                         .createUser(user)
            //                         .then(function (user) {
            //                             $location.url("/login");
            //                         })
            //                 } else {
            //                     vm.alert = "User Name already taken, Please try another one";
            //                 }
            //             })
            //     } else {
            //         vm.alert = "Password and verify password do not match";
            //     }
            // }
        }
    }
})();