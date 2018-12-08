(function () {
    angular
        .module("MovieDBApp")
        .factory("UserService", userService);

    function userService($http) {
        // var api = {
        //     "findUserByCredentials": findUserByCredentials,
        //     "createUser": createUser
        // };
        // return api;
        //
        // function findUserByCredentials(user) {
        //     return $http.post("/api/login", user)
        //         .then(function (response) {
        //             return response.data;
        //         });
        // }
        //
        // function createUser(user) {
        //     return $http.post("/api/user", user)
        //         .then(function (response) {
        //             return response.data;
        //         });
        // }
    }
})();