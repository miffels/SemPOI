/* global directives:false */

(function() {
	'use strict';

	directives.directive('spinner', [function() {
		return function($scope, $element, $attrs) {
			$.fn.spin.presets.sempoi = {
  lines: 7, // The number of lines to draw
  length: 4, // The length of each line
  width: 2, // The line thickness
  radius: 4, // The radius of the inner circle
  corners: 0.1, // Corner roundness (0..1)
  rotate: 21, // The rotation offset
  direction: 1, // 1: clockwise, -1: counterclockwise
  color: '#000', // #rgb or #rrggbb or array of colors
  speed: 1.5, // Rounds per second
  trail: 83, // Afterglow percentage
  shadow: false, // Whether to render a shadow
  hwaccel: false, // Whether to use hardware acceleration
  className: 'spinner', // The CSS class to assign to the spinner
  zIndex: 2e9, // The z-index (defaults to 2000000000)
  top: '-30px', // Top position relative to parent in px
  left: 'auto' // Left position relative to parent in px
};

$element.spin('sempoi');
};
}]);
})();
