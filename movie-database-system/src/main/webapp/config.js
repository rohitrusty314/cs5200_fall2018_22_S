(function () {
    angular
        .module("MovieDBApp")
        .config(configuration);

    function configuration($routeProvider, $httpProvider) {
        $httpProvider.defaults.headers.post['Content-Type'] = 'application/json;charset=utf-8';
        $httpProvider.defaults.headers.put['Content-Type'] = 'application/json;charset=utf-8';

        $routeProvider
            .when("/", {
                templateUrl: "templates/login.view.client.html",
                controller: "LoginController",
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
                controllerAs: "model"
            })
    }
})();
