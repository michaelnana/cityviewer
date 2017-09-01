angular.module('app').controller('dashboardController', ['$scope', 'cityFactory', DashboardController]);

function DashboardController($scope, cityFactory) {
    var dashboard = this;
    dashboard.allCities = [];
    dashboard.alert = false;

    dashboard.init = function() {
        cityFactory.allCities(
            function(response) {
                dashboard.allCities = response.data;
            },
            function(error) {
                console.log("Error: " + error);
                toastr.error('An error occurred loading the cities');
            }
        )
    }

    dashboard.createCity = function() {
        cityFactory.createCity({
                name: dashboard.newcity.name,
                latitude: dashboard.newcity.latitude,
                longitude: dashboard.newcity.longitude
            },
            function(response) {
                toastr.success(dashboard.newcity.name + ' was created successfully.');
                window.location.reload();
            },
            function(error) {
                toastr.error('An error occurred creating ' + dashboard.newcity.name);
                console.log("Error: " + error);
            }
        )

    }

    dashboard.calculateDistance = function() {
        var distanceBetweenCities = distanceBetweenCoordinates(
            parseFloat(dashboard.locationCity1.latitude),
            parseFloat(dashboard.locationCity1.longitude),
            parseFloat(dashboard.locationCity2.latitude),
            parseFloat(dashboard.locationCity2.longitude)
        );
        dashboard.distance = "Distance between " + dashboard.locationCity1.name +
            " and " + dashboard.locationCity2.name + " is " + distanceBetweenCities.toFixed(2) + " KM";
    }

    function distanceBetweenCoordinates(city1Latitude, city1Longitude, city2Latitude, city2Longitude) {
        var radiusOfEarth = 6371;
        var latitudeDistance = toRadians(city2Latitude - city1Latitude);
        var longitudeDistance = toRadians(city2Longitude - city1Longitude);
        var a =
            Math.sin(latitudeDistance / 2) * Math.sin(latitudeDistance / 2) +
            Math.cos(toRadians(city1Latitude)) * Math.cos(toRadians(city2Latitude)) *
            Math.sin(longitudeDistance / 2) * Math.sin(longitudeDistance / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = radiusOfEarth * c;
        return d;
    }

    function toRadians(degrees) {
        return degrees * (Math.PI / 180);
    }

}