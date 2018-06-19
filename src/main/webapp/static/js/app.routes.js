var demoApp = angular.module('demoApp.routes', ['ngRoute']);

demoApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl : 'static/html/home.html'
        })
        .when('/items', {
            templateUrl : 'static/html/item/items.html'
        })
        .when('/items/add', {
            templateUrl : 'static/html/item/addEditItem.html',
            controller : 'ItemController'
        })
        .when('/items/edit/:id', {
            templateUrl : 'static/html/item/addEditItem.html',
            controller : 'ItemController'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);