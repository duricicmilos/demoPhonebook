var demoApp = angular.module('demoApp.services', []);

demoApp.service('itemRestService', function($http) {
	
	this.getItem = function(id) {
		return $http.get('api/items/' + id);
	};
	
	this.getItems = function(request_params) {
		return $http.get('api/items', { params : request_params });
	};
	
	this.deleteItem = function(id) {
		return $http.delete('api/items/' + id);
	};
	
	this.saveItem = function(item) {
		if (item.id) {
			return $http.put('api/items/' + item.id, item);
		} else {
			return $http.post('api/items', item);
		}
	};
});