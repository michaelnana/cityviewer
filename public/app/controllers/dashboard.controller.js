app.controller('dashboardController', ['$scope', 'cityFactory', DashboardController]);

function DashboardController ($scope, cityFactory) {
    var dashboard = this;
    dashboard.allCities = [];

    dashboard.init = function () {
        cityFactory.allCities(
            function (response) {
                dashboard.allCities = response.data;
                console.log("Success: " + JSON.stringify(dashboard.allCities));
            },
            function (error) {
                console.log("Error: " + error);
            }
        )
    }

}