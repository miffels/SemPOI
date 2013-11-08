'use strict';


// Declare app level module which depends on filters, and services
angular.module('semPoi', [
    'ui.router',
    'semPoi.filters',
    'semPoi.services',
    'semPoi.directives',
    'semPoi.controllers',
    'google-maps'
]).
config(['$stateProvider', '$urlRouterProvider',
    function($stateProvider, $urlRouterProvider) {
        $stateProvider
            .state('state1', {
                url: '/view1',
                templateUrl: 'partials/partial1.html',
                controller: 'MainCtrl'
            });

        $urlRouterProvider.otherwise('/view1');
    }
]);


$(document).ready(function() {
            $('#list').height($(window).height()-200);
            $(window).resize(function() {
                $('#list').height($(window).height()-200);
            });

        });