/* global services:false */

(function() {
	'use strict';

	services.factory('restFactory', function($http) {
		var restFactory = {};

		restFactory.getSights = function(city) {
			return $http.get('berlin.json').then(function(result) {
				return result.data.result[0].tourist_attractions;
			});
		};

		return restFactory;
	});
})();