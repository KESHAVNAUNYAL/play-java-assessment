'use strict';

var services = angular.module('myApp.services', ['ngResource']);

var baseUrl = 'http://localhost\\:9000';

/*services.factory('UserFactory', function ($resource) {
    return $resource(baseUrl + '/ngdemo/web/dummy', {}, {
        query: { method: 'GET', params: {} }
    })
});*/

services.factory('UsersFactory', function ($resource) {
    return $resource(baseUrl + '/users', {}, {
        //query: { method: 'GET', isArray: true },
    	query: { method: 'GET' },
        create: { method: 'POST' }
    })
})
;

services.factory('UserFactory', function ($resource) {
    return $resource(baseUrl + '/play_user', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT' },
        'delete' : { method: 'DELETE', params: {id: '@id'} }
    })
});