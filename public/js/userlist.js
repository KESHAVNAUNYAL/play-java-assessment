/*var myApp=angular.module('myApp.controllers', []);
myApp.controller('userlistController', ['$scope',  'UserFactory', '$location',
    function userlistController ($scope,  UserFactory, $location) {
	$scope.users = function () {
		UsersFactory.query();
	}
    }]);*/
'use strict';
var myApp=angular.module('myApp.controllers', []);

myApp.run(function ($rootScope, $templateCache) {
	$rootScope.$on('$viewContentLoaded', function () {
		$templateCache.removeAll();
	});
});


myApp.controller('userlistController', ['$rootScope','$scope', 'UsersFactory', 'UserFactory', '$location',
	function userlistController ($rootScope,$scope, UsersFactory, UserFactory, $location) {
	// callback for ng-click 'createNewUser':
//	angular.element(document).ready(function() {

//	});
	$scope.getUsers = function () {
		//$scope.users = UsersFactory.query();
		$scope.users = UsersFactory.query();
	}
	$scope.getUsers();
	$scope.edit = function (user_data) {
		console.log("user_data -->"+JSON.stringify(user_data));
		$rootScope.editeduser = user_data;
		$location.path('/edit');

	};
}



]);
myApp.controller('usereditController', ['$scope', '$routeParams', 'UserFactory', '$location','$rootScope',
	function ($scope, $routeParams, UserFactory, $location,$rootScope) {
	// callback for ng-click 'updateUser':
	$scope.editUser = function () 
	{
		console.log("into edit user");
		UserFactory.update($scope.play_user);
		// $location.path('/play_user');
	};
	//$scope.$on('EDITUSER', function(response) { });
	$scope.play_user = $rootScope.editeduser;

}]);

myApp.controller('usercreateController', ['$scope', 'UsersFactory', '$location',
	function usercreateController ($scope, UsersFactory, $location) {
	// callback for ng-click 'createNewUser':
	$scope.createNewUser = function () {
		console.log("play user -->"+ JSON.stringify($scope.play_user));
		UsersFactory.create($scope.play_user);
		console.log("after call play user -->"+ JSON.stringify($scope.users));
		//UsersFactory.create();
		//  $location.path('/play_user');
	}
}]);
myApp.controller('initiator',['$scope','$http',
	function initiator ($scope,$http) {
	console.log("Entering into initiator controller:");
	angular.element(document).ready(function() {

	});
}]);
