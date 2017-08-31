var assert = require('assert');
var jsdom = require('./test-helper.js');
var app;
var ctrl;
var $httpBackend;
var scope;
require('./../public/app/app.js');
require('./../public/app/controllers/dashboard/dashboard.controller.js');

describe('DashboardController', function() {
    beforeEach(ngModule('app'));
    beforeEach(ngModule(function($provide) {
        $provide.factory('cityFactory', function($http) {
            return {
                allCities: function(success, failure) {
                    $http({
                        method: 'GET',
                        url: '/cities'
                    }).then(success, failure);
                },
                createCity: function(data, success, failure) {
                    $http({
                        method: 'POST',
                        url: '/cities',
                        data: data
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
        ctrl = $controller('dashboardController', {
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

        it('on init all cities should be loaded', function() {
            $httpBackend.when('GET', '/cities').respond(200, citiesMockData);
            ctrl.init();
            $httpBackend.flush();
            scope.$digest();
            assert.deepEqual(ctrl.allCities, citiesMockData);
        });
    });
    describe('createCity', function() {

        it('on city created, the controller reloads the screen', function() {
            var expectedCreatedCity = {
                "name": "New City",
                "latitude": 15.0,
                "longitude": 15.0
            }
            ctrl.newcity = {};
            ctrl.newcity.name = expectedCreatedCity.name;
            ctrl.newcity.latitude = expectedCreatedCity.latitude;
            ctrl.newcity.longitude = expectedCreatedCity.longitude;
            $httpBackend.when('POST', '/cities', expectedCreatedCity).respond(200, expectedCreatedCity);
            ctrl.createCity();
            $httpBackend.flush();
            scope.$digest();
        });

        it('on error when creating city, the controller loads an error toastr', function() {
            var expectedCreatedCity = {
                "name": "New City",
                "latitude": 15.0,
                "longitude": 15.0
            }
            ctrl.newcity = {};
            ctrl.newcity.name = expectedCreatedCity.name;
            ctrl.newcity.latitude = expectedCreatedCity.latitude;
            ctrl.newcity.longitude = expectedCreatedCity.longitude;
            $httpBackend.when('POST', '/cities', expectedCreatedCity).respond(200, expectedCreatedCity);
            ctrl.createCity();
            $httpBackend.flush();
            scope.$digest();
        });
    });

    describe('calculateDistance', function() {
        it('for two cities given, the distance between them is calculated', function() {
            ctrl.locationCity1 = {
                "name": "City A",
                "latitude": 10.0,
                "longitude": 10.0
            };
            ctrl.locationCity2 = {
                "name": "City B",
                "latitude": 20.0,
                "longitude": 10.0
            };
            ctrl.calculateDistance();
            assert.equal(ctrl.distance, "Distance between City A and City B is 1111.95 KM");
        });
    });
});