/* global services:false */

(function() {
	'use strict';

	services.factory('restFactory', function($http) {
		var restFactory = {};

		restFactory.getSights = function(city) {
			return $http.get('http://localhost:8080/server/search?city='+city);
		};

		return restFactory;
	});
})();