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
config(['$stateProvider', '$urlRouterProvider','$httpProvider',
    function($stateProvider, $urlRouterProvider, $httpProvider) {
        $stateProvider
            .state('state1', {
                url: '/view1',
                templateUrl: 'partials/partial1.html',
                controller: 'MainCtrl'
            });

        $urlRouterProvider.otherwise('/view1');

        //enables CORS
        $httpProvider.defaults.useXDomain = true;
        delete $httpProvider.defaults.headers.common['X-Requested-With'];
    }
]);

$(document).ready(function() {
            $('#list').height($(window).height()-200);
            $('#map').height($(window).height()-100);
            $(window).resize(function() {
                $('#list').height($(window).height()-200);
                $('#map').height($(window).height()-100);
            });

        });
