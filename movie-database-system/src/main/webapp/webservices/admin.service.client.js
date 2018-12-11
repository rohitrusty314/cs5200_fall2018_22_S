(function () {
    angular
        .module("MovieDBApp")
        .factory("AdminService", adminService);

    function adminService($rootScope, $http) {
        var api = {
            "findAllUsers": findAllUsers,
            "deleteUser": deleteUser,
            "findAllMovies": findAllMovies,
            "createMovie": createMovie,
            "findMovieById": findMovieById,
            "updateMovie": updateMovie,
            "deleteMovie" : deleteMovie
        };
        return api;

        function updateMovie(mid, movie) {
            return $http.put("/api/movie/" + mid, movie)
                .then(function (response) {
                    return response.data;
                });
        }

        function findMovieById(mid) {
            return $http.get("/api/movie/" + mid)
                .then(function (response) {
                    return response.data;
                });
        }

        function createMovie(movie) {
            return $http.post("/api/movie", movie)
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllMovies() {
            return $http.get("/api/movies")
                .then(function (response) {
                    return response.data;
                });
        }

        function findAllUsers(userId) {
            return $http.get("/api/users/except/admin/" + userId)
                .then(function (response) {
                    return response.data;
                });
        }

        function deleteUser(user) {
            return $http.delete("/api/" + user.role.toLowerCase() + "/" + user.id + "/delete")
                .then(function (response) {
                    return response.data;
                });
        }

        function deleteMovie(movie) {
            return $http.delete("/api/movie/" + movie.id)
                .then(function (response) {
                    return response.data;
                });
        }
    }
})();