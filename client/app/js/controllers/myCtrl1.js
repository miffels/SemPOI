/* global controllers:false */

(function() {
    'use strict';

    controllers.controller('MyCtrl1', ['$scope', function MyCtrl1($scope) {
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


