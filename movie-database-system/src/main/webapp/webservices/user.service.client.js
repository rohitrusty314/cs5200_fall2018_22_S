(function () {
    angular
        .module("MovieDBApp")
        .factory("UserService", userService);


    function userService($http) {
        var api = {
            "findUserByCredentials": findUserByCredentials,
            "createUser": createUser,
            "findUserByUsername": findUserByUsername
        };
        return api;

        function findUserByCredentials(user) {
            return $http.post("/api/login", user)
                .then(function (response) {
                    return response.data;
                });
        }

        function createUser(user) {
            console.log("reached in service create user");
            return $http.post("/api/user", user)
                .then(function (response) {
                    return response.data;
                });
        }

        function findUserByUsername(username) {
            return $http.get("/api/user?username=" + username)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();