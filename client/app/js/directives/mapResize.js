/* global directives:false */

(function() {
	'use strict';

	directives.directive('mapResize', ['$window', function(window) {
		return function($scope, $element, $attrs) {
			var $window = $(window);
			var marginBottom = $attrs.marginBottom ?  ~~$attrs.marginBottom : 100;
			var target = $attrs.targetId ? $element.find('#' + $attrs.targetId) : $element;
			$window.resize(function(){
				target.height($window.height()-marginBottom);

			});
		};
	}]);
})();
