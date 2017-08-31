var jsdom = require('jsdom').jsdom;

global.document = jsdom('<html><head><script></script></head><body></body></html>');
global.window = global.document.parentWindow;
global.navigator = window.navigator = {userAgent: 'node.js'};
global.Node = window.Node;

global.window.mocha = {};
global.window.beforeEach = beforeEach;
global.window.afterEach = afterEach;


/*
 * Only for NPM users
 */
require('angular/angular');
require('angular-mocks');
require('jquery');
require('toastr');
//require('underscore');
//require('moment');

global.angular = window.angular;
global.inject = global.angular.mock.inject;
global.ngModule = global.angular.mock.module;
global.toastr = require('toastr');
global.jquery = require('jquery');
//global._ = require('underscore');
//global.moment = require('moment');
