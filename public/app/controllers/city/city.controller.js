angular.module('app').controller('cityController', ['$scope', 'cityFactory', CityController]);

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
                switch (error.status) {
                    case 404:
                        toastr.error("City wasn't found");
                        break;
                    case 500:
                        toastr.error("Something went wrong with your request");
                        break;
                }
            }
        )
    }

    city.update = function() {
        cityFactory.update(
            city.id, {
                name: city.details.name,
                latitude: city.details.latitude,
                longitude: city.details.longitude
            },
            function(response) {
                city.details = response.data;
                toastr.success(city.details.name + ' was successfully updated');
            },
            function(error) {
                switch (error.status) {
                    case 404:
                        toastr.error("City wasn't found");
                        break;
                    case 500:
                        toastr.error("Something went wrong with your request");
                        break;
                }
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
                switch (error.status) {
                    case 404:
                        toastr.error("City wasn't found");
                        break;
                    case 500:
                        toastr.error("Something went wrong with your request");
                        break;
                }
            }
        )
    }
}