'use strict';


// Declare app level module which depends on filters, and services
angular.module('semPoi', [
        'ui.router',
        'semPoi.filters',
        'semPoi.services',
        'semPoi.directives',
        'semPoi.controllers'
]).
config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('state1', {
                url: '/view1',
                templateUrl: 'partials/partial1.html',
                controller: 'MyCtrl1'
            })
            .state('state2', {
                url: '/view2',
                templateUrl: 'partials/partial2.html',
                controller: 'MyCtrl2'
            });

        $urlRouterProvider.otherwise('state1');
}]);
