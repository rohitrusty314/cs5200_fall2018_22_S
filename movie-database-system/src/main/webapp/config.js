(function () {
    angular
        .module("MovieDBApp")
        .config(configuration);

    function configuration($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';

        $routeProvider
            .when("/", {
                templateUrl: "templates/search.view.client.html",
                controller: "SearchController",
                controllerAs: "model"
            })
            .when("/login", {
                templateUrl: "templates/login.view.client.html",
                controller: "LoginController",
                controllerAs: "model"
            })
            .when("/register", {
                templateUrl: "templates/register.view.client.html",
                controller: "RegisterController",
                controllerAs: "model"
            })
            .when("/search", {
                templateUrl: "templates/search.view.client.html",
                controller: "SearchController",
                controllerAs: "model"
            })
            .when("/watchlists", {
                templateUrl: "templates/watchlists.view.client.html",
                controller: "WatchlistController",
                controllerAs: "model",
                resolve: {loggedin: checkLoggedin}
            })
            .when("/detail/:imdbId", {
                templateUrl: "templates/detail.view.client.html",
                controller: "MovieDetailController",
                controllerAs: "model"
            })
            .when("/watchlists/:imdbId", {
                templateUrl: "templates/watchlists.view.client.html",
                controller: "WatchlistController",
                controllerAs: "model"
            })
            .when("/profile/:uid", {
                templateUrl: "templates/profile.view.client.html",
                controller: "ProfileController",
                controllerAs: "model",
                resolve: {loggedin: checkLoggedin}
            })
            .when("/admin/:uid", {
                templateUrl: "templates/admin.view.client.html",
                controller: "AdminController",
                controllerAs: "model"
            })
            .when("/censor/:uid", {
                templateUrl: "templates/censor.view.client.html",
                controller: "CensorController",
                controllerAs: "model"
            })
            .when("/curator/:uid", {
                templateUrl: "templates/curator.view.client.html",
                controller: "CuratorController",
                controllerAs: "model"
            })
            .when("/followCritic/:uid", {
                templateUrl: "templates/followcritic.view.client.html",
                controller: "FollowController",
                controllerAs: "model",
                resolve: {loggedin: checkLoggedin}
            })
            .when("/followers/:uid", {
                templateUrl: "templates/followers.view.client.html",
                controller: "FollowerController",
                controllerAs: "model",
                resolve: {loggedin: checkLoggedin}
            })
            .when("/movie", {
                templateUrl: "templates/movie.view.client.html",
                controller: "MovieController",
                controllerAs: "model"
            })

        function checkLoggedin($q, $timeout, $http, $location, $rootScope) {
            var deferred = $q.defer();
            $http.get('/api/user/loggedin')
                .success(function(user) {
                    $rootScope.errorMessage = null;
                    if (user !== '') {
                        deferred.resolve();
                    } else {
                        deferred.reject();
                        $location.url('/login');
                    }
                });
            return deferred.promise;
        };
    }
})();
