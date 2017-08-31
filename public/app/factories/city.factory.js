app.factory('cityFactory', ['$http', CityFactory]);

function CityFactory($http) {
    var getAllCities = function(success, failure) {
        $http({
            method: 'GET',
            url: '/cities'
        }).then(success, failure);
    };

    var getCity = function(id, success, failure) {
        $http({
            method: 'GET',
            url: '/cities/' + id,
            headers: {
                'Accept': 'application/json'
            }
        }).then(success, failure);
    };

    var updateCity = function(id, data, success, failure) {
        $http({
            method: 'PUT',
            url: '/cities/' + id,
            data: data
        }).then(success, failure);
    };

    var deleteCity = function(id, success, failure) {
        $http({
            method: 'DELETE',
            url: '/cities/' + id
        }).then(success, failure);
    };

    var createCity = function(data, success, failure) {
        $http({
            method: 'POST',
            url: '/cities/',
            data: data
        }).then(success, failure);
    }

    return {
        allCities: getAllCities,
        city: getCity,
        update: updateCity,
        deleteCity: deleteCity,
        createCity: createCity
    }
}