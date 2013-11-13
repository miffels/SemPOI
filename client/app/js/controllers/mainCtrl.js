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
			zoom: 8 // the zoom level

     })

        $scope.sights=[];
        $scope.searchCity = function(){
            restFactory.getSights($scope.city).then(function(result){
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
            resizeUI();
        }); 

        function resizeUI(){
            $('#map').height($(window).height()-100);
            $('#list').height($(window).height()-200);
        }

        function addMarker(data) {
            $scope.markers.push({
                latitude: parseFloat(data.location.lat),
                longitude: parseFloat(data.location.lng),
                label: data.name,
                infoWindow: "<b>"+data.name+"</b><br/>"+data.description
            });
        }

        function addHoverListener(){
            $('#list').on('mouseover', 'div', function() {
                $(this).css("background-color", "#999999");
            });

            $('#list').on('mouseout', 'div', function() {
                $(this).css("background-color", "#eeeeee");
            });


            $('#list').on('click', 'a', function() {
                loadDetailIntoScope($(this).text().replace(/^\s+|\s+$/g, ''));
                $('#detailModal').modal('show');
            });
            
        }

        function loadDetailIntoScope(id){
            for ( var i = 0; i < $scope.sights.length; i++) {
                if ($scope.sights[i].name == id) {
                    $scope.detail = $scope.sights[i];
                    $scope.center.latitude = $scope.detail.location.lat;
                    $scope.center.longitude = $scope.detail.location.lng;
                    $scope.zoom = 16;
                }
            }

        }

    }]);

})();


