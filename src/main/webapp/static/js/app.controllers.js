var demoApp = angular.module('demoApp.controllers', []);

demoApp.controller('ItemController', function($scope, 
		$location, $routeParams, itemRestService) {
	
	$scope.getItems = function() {
		var request_params = {};
		if ($scope.search) {
			request_params['lastname'] = $scope.search;
		}
		
		itemRestService.getItems(request_params)
				.success(function(data, status, headers) {
					$scope.items = data;
					$scope.successMessage = 'Everything OK';
				})
				.error(function() {
					$scope.errorMessage = 'Oops, something went wrong.';
				});
	};
	
	$scope.deleteItem = function(id, index) {
		itemRestService.deleteItem(id)
				.success(function() {
					$scope.items.splice(index, 1);
				})
				.error(function() {
					
				});
	};
	
	$scope.initItem = function() {
		$scope.item = {};
		if ($routeParams && $routeParams.id) {
			itemRestService.getItem($routeParams.id)
					.success(function(data) {
						$scope.item = data;
					})
					.error(function() {
						
					});
		}
	};
	
	$scope.saveItem = function() {
		itemRestService.saveItem($scope.item)
				.success(function() {
					$location.path('/items');
				})
				.error(function() {
					
				});
	};
	
	$scope.changePage = function(page) {
		$scope.page = page;
		$scope.getItems();
	};
});