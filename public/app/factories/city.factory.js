app.factory('cityFactory', ['$http', CityFactory]);

function CityFactory ($http) {
    var getAllCities = function (success, failure) {
        $http({
            method: 'GET',
            url: '/cities'
        }).then(success, failure);
    }
    return {
        allCities: getAllCities
    }
}