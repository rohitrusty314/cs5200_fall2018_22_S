(function () {
    angular
        .module("MovieDBApp")
        .controller("WatchlistController", watchlistController);

    function watchlistController($scope, $location, WatchListService) {
        var vm = this;

        function init() {
            console.log("definitely getting the user watchlists now!");
            // WatchListService
            //     .findWatchlistsByUserId(user);

        }

        init();
        vm.watchlists = watchlists;

        // function watchlists(user) {
        //     console.log("");
        //
        // }
    }
})();