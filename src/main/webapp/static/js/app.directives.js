var demoApp = angular.module('demoApp.directives', []);

demoApp.directive('itemsTable', function() {
    return {
        restrict: 'E',
        templateUrl: 'static/html/item/itemsTable.html',
        controller: 'ItemController'
    }
});