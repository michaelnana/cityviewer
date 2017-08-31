app.controller('cityController', ['$scope', 'cityFactory', CityController]);

function CityController($scope, cityFactory) {
    var city = this;
    city.id = window.location.pathname.substring(1).split("/")[1];
    city.details = {};

    city.init = function() {
        cityFactory.city(
            city.id,
            function(response) {
                city.details = response.data;
            },
            function(error) {
                console.log("Error: " + error);
            }
        )
    }

    city.update = function() {
        cityFactory.update(
            city.id,
            {name: city.details.name, latitude: city.details.latitude, longitude: city.details.longitude},
            function(response) {
                city.details = response.data;
                toastr.success(city.details.name + ' was successfully updated');
            },
            function(error) {
                console.log("Error: " + error);
            }
        )
    }

    city.deleteCity = function() {
        cityFactory.deleteCity(
            city.id,
            function(response) {
                            window.location.href = "/";
                        },
                        function(error) {
                            console.log("Error: " + error);
                        }
        )
    }
}