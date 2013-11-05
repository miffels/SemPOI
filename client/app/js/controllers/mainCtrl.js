/* global controllers:false */

(function() {
    'use strict';

    controllers.controller('MainCtrl', ['$scope', 'restFactory', function MainCtrl($scope, restFactory) {
        $scope.sights= [];
        $scope.searchCity = function(){
            var result = restFactory.getSights($scope.city);
            $scope.sights = result;
        };

    	angular.extend($scope,{
    			center: {
					latitude: 49, // initial map center latitude
					longitude: 7, // initial map center longitude
			},
			markers: [], // an array of markers,
			zoom: 8, // the zoom level
    	})

    }]);

})();


