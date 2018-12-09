(function () {
    angular
        .module("MovieDBApp")
        .controller("SearchController", searchController);

    function searchController($scope, $location) {
        var vm = this;

        vm.watchlists = watchlists;

        function watchlists(user) {
            console.log("Getting watchlists for user");
            showUserWatchlists(user);
        }

        function showUserWatchlists(user) {
            console.log("Navigating to user watchlists page");
            // if (user.id === 0) {
            //     vm.alert = "User not found";
            // }
            $location.url("/watchlists");
        }

    }
})();
