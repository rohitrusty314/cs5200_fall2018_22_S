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
            console.log("user by cred");
            return $http.post("/api/login", user)
                .then(function (response) {
                    console.log("user found");
                    console.log(response.data);
                    return response.data;
                });
        }

        function createUser(user) {
            console.log("reached in service create user");
            if(user.role === "CRITIC") {
                return $http.post("/api/critic", user)
                    .then(function (response) {
                        return response.data;
                    });
            } else if(user.role === "ADMIN") {
                return $http.post("/api/admin", user)
                    .then(function (response) {
                        return response.data;
                    });
            }
            else {
                return $http.post("/api/resident", user)
                    .then(function (response) {
                        return response.data;
                    });
            }
        }

        function findUserByUsername(username) {
            return $http.get("/api/user?username=" + username)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();