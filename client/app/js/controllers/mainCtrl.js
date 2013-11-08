/* global controllers:false */

(function() {
    'use strict';

    controllers.controller('MainCtrl', ['$scope', 'restFactory', function MainCtrl($scope, restFactory) {
    	angular.extend($scope,{
         center: {
					latitude: 10, // initial map center latitude
					longitude: 10, // initial map center longitude
             },
			markers: [], // an array of markers,
			zoom: 8, // the zoom level
       })

        $scope.sights=[];
        $scope.searchCity = function(){
            restFactory.getSights($scope.city).then(function(result){
                console.log(result);
                $scope.center.latitude = result.location.lat;
                $scope.center.longitude = result.location.lng;
                $scope.zoom = 13;
                $scope.cityName = result.name;
                $scope.sights = result.sights;
            });
        };

        $scope.$watch('sights', function() {
            $scope.markers=[];
            for ( var i = 0; i < $scope.sights.length; i++) {
                addMarker($scope.sights[i]);
            }
            addHoverListener();
            $('#list').height($(window).height()-200);
        }); 

        function addMarker(data) {
            $scope.markers.push({
                latitude: parseFloat(data.location.lat),
                longitude: parseFloat(data.location.lng),
                title: data.name
            });
        }

        function addHoverListener(){
            $('#list').children().unbind('mouseenter mouseleave');
            console.log("asdd");
            $("#list").children().hover(function() {
                console.log("asd");
            });
        }

    }]);

})();


