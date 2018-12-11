(function () {
    angular
        .module("MovieDBApp")
        .controller("CuratorController", curatorController);

    function curatorController($rootScope, $scope, $http, $location,$routeParams, CuratorService) {
        var vm = this;

        var userId = $routeParams['uid'];
        vm.userId = userId;

        function init() {
            CuratorService
                .findAllWatchlists()
                .then(function (response) {
                    vm.watchlists = response;
                });

            CuratorService
                .findAllEndorsedWatchlists($rootScope.userId)
                .then(function (response) {
                    vm.endorsedWatchlists = response;
                });

        }

        init();

        vm.endorseWatchlist = endorseWatchlist;
        vm.logout = logout;

        function endorseWatchlist(watchlist) {

            console.log(watchlist)
            CuratorService
                .endorseWatchlist(watchlist.id, vm.userId)
                .then(function (response) {
                    CuratorService
                        .findAllWatchlists()
                        .then(function (response) {
                            vm.watchlists = response;
                        });

                    CuratorService
                        .findAllEndorsedWatchlists($rootScope.userId)
                        .then(function (response) {
                            vm.endorsedWatchlists = response;
                        });
                });
        }

        function logout() {

            vm = undefined;
            $rootScope.userId = undefined;
            $rootScope.userRole = undefined;
            // redirect logged in user to login page
            $http.get('/api/user/loggedout');
            $location.url('/search')
        }
    }

})();