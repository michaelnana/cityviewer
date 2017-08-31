var assert = require('assert');
var jsdom = require('./test-helper.js');
var app;
var ctrl;
var $httpBackend;
var scope;
require('./../public/app/app.js');
require('./../public/app/controllers/city/city.controller.js');

describe('CityController', function() {
    beforeEach(ngModule('app'));
    beforeEach(ngModule(function($provide) {
        $provide.factory('cityFactory', function($http) {
            return {
                city: function(id, success, failure) {
                    $http({
                        method: 'GET',
                        url: '/cities/' + id
                    }).then(success, failure);
                },
                update: function(id, data, success, failure) {
                    $http({
                        method: 'PUT',
                        url: '/cities/' + id,
                        data: data
                    }).then(success, failure);
                },
                deleteCity: function(id, success, failure) {
                    $http({
                        method: 'DELETE',
                        url: '/cities/' + id
                    }).then(success, failure);
                }
            }
        });
    }));
    beforeEach(inject(function($injector) {
        var $rootScope = $injector.get('$rootScope');
        var $controller = $injector.get('$controller');
        var $cityFactory = $injector.get('cityFactory');
        $httpBackend = $injector.get('$httpBackend');
        scope = $rootScope.$new();
        ctrl = $controller('cityController', {
            $scope: scope,
            cityFactory: $cityFactory
        });
    }));
    describe('init', function() {
        var citiesMockData = [{
                "name": "City A",
                "latitude": 10.0,
                "longitude": 10.0
            },
            {
                "name": "City B",
                "latitude": 20.0,
                "longitude": 10.0
            },
            {
                "name": "City C",
                "latitude": 30.0,
                "longitude": 10.0
            }
        ];

        it('on init city is loaded based on the id of the route', function() {
            var expectedCity = {
                "name": "City A",
                "latitude": 10.0,
                "longitude": 10.0
            };
            ctrl.id = "1";
            $httpBackend.when('GET', '/cities/1').respond(200, expectedCity);
            ctrl.init();
            $httpBackend.flush();
            scope.$digest();
            assert.deepEqual(ctrl.details, expectedCity);
        });
    });

    describe('update', function() {
            it('on update city is updated with data passed in', function() {
                var expectedCity = {
                    "name": "City A",
                    "latitude": 10.0,
                    "longitude": 10.0
                };
                ctrl.details = expectedCity;
                ctrl.id = "1";
                $httpBackend.when('PUT', '/cities/1', expectedCity).respond(200, expectedCity);
                ctrl.update();
                $httpBackend.flush();
                scope.$digest();
                assert.deepEqual(ctrl.details, expectedCity);
            });
        });

});