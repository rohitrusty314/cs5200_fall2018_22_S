(function () {
    angular
        .module("MovieDBApp")
        .controller("WatchlistController", watchlistController);

    function watchlistController($scope, $location) {
        var vm = this;

        vm.watchlists = watchlists;

        function init() {
            console.log("definitely getting the user watchlists now!");
            
        }

        init();

        function watchlists(user) {
            console.log("");

        }
    }
})();