'use strict';

angular.module('myApp', [ 'myApp.services', 'myApp.controllers', 'ngRoute' ])
		.config(function($routeProvider, $httpProvider) {
			$routeProvider.when('/users', {
				templateUrl : 'assets/js/userlist.html',
				controller : 'userlistController'
			});
			$routeProvider.when('/userlist', {
				templateUrl : 'assets/js/userlist.html',
				controller : 'userlistController'
			});
			$routeProvider.when('/edit', {
				templateUrl : 'assets/js/useredit.html',
				controller : 'usereditController'
			});
			$routeProvider.when('/usercreate', {
				templateUrl : 'assets/js/usercreate.html',
				controller : 'usercreateController'
			});
			
			myApp.run(function($rootScope,$location) {
				$rootScope.$on("$rootChangeStart",function(event,next,current){
					
				});
			});
			
						// $routeProvider.otherwise({redirectTo: ''});

			/* CORS... */
			/* http://stackoverflow.com/questions/17289195/angularjs-post-data-to-external-rest-api */
			$httpProvider.defaults.useXDomain = true;
			delete $httpProvider.defaults.headers.common['X-Requested-With'];
		});