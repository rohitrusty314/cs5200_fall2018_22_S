(function () {
    angular
        .module("MovieDBApp")
        .factory("UserService", userService);


    function userService($http) {
        var api = {
            "findUserByCredentials": findUserByCredentials,
            "createUser": createUser,
            "findUserByUsername": findUserByUsername,
            "findUserById": findUserById,
            "updateUser": updateUser,
            "findAllCritics": findAllCritics,
            "followCritic": followCritic,
            "findFollowersOfCritic": findFollowersOfCritic
        };
        return api;

        function findFollowersOfCritic(criticId) {

            return $http.get("/api/critic/" + criticId + "/follower/residents/")
                .then(function (response) {
                    console.log(response);
                    return response.data;
                });
        }

        function followCritic(criticId, userId) {

            return $http.put("/api/resident/" + userId + "/follow/critic/" + criticId , {})
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllCritics() {
            return $http.get("/api/critics")
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });
        }


        function updateUser(user) {

            console.log(user)
            return $http.put("/api/user/profile/update" , user)
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });
        }

        function findUserById(userId) {
            return $http.get("/api/user/" + userId)
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });
        }


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
            } else if(user.role === "CENSOR") {
                return $http.post("/api/censor", user)
                    .then(function (response) {
                        return response.data;
                    });
            }
            else if(user.role === "CURATOR") {
                return $http.post("/api/curator", user)
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