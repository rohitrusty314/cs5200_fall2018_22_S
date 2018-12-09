(function () {
    angular
        .module("MovieDBApp")
        .controller("WatchlistController", watchlistController);

    function watchlistController($scope, $location, WatchListService) {
        var vm = this;

        vm.watchlists = watchlists;

        function init() {
            console.log("definitely getting the user watchlists now!");
            WatchListService
                .findWatchlistsByUserId(user);

        }

        init();

        function watchlists(user) {
            console.log("");

        }
    }
})();