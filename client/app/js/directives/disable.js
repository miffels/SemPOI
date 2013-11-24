/* global directives:false */

(function() {
	'use strict';

	directives.directive('disable', [function() {
		return function($scope, $element, $attrs) {
			$scope.$watch('filteredSights', function(){
				if (typeof $element === 'undefined'){
					return;
				}
				var length = $scope.filteredSights.length;
				if(length > 10 || length < 2){
					$element.addClass('disabled');
				} else{
					$element.removeClass('disabled');
				}
			} , true)
		};
	}]);
})();
