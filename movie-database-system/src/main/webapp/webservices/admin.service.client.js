(function () {
    angular
        .module("MovieDBApp")
        .factory("AdminService", adminService);

    function adminService($rootScope, $http) {
        var api = {
            "findAllUsers": findAllUsers,
            "deleteUser": deleteUser
        };
        return api;

        function findAllUsers() {
            return $http.get("/api/users")
                .then(function (response) {
                    return response.data;
                });
        }

        function deleteUser(user) {
            return $http.delete("/api/critic/" + user.id + "/delete")
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();