(function () {
    angular
        .module("MovieDBApp")
        .factory("AdminService", adminService);

    function adminService($rootScope, $http) {
        var api = {
            "findAllUsers": findAllUsers()
        };

        return api;

        function findAllUsers() {
            return $http.get("/api/movie/" + imdbId + "/ratings/critic")
                .then(function (response) {
                    console.log(response.data);
                    return response.data;
                });

        }
    }
})();