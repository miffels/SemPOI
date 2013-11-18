/* global controllers:false */

(function() {
    'use strict';

    controllers.controller('MainCtrl', ['$scope', 'restFactory', function MainCtrl($scope, restFactory) {

        $scope.center = {
             latitude: 49, // initial map center latitude
             longitude: 9 // initial map center longitude
         };
         $scope.zoom = 8;
         $scope.markers = [];
         $scope.sights=[];

         $scope.searchCity = function(){
            restFactory.getSights($scope.city).then(function(result){
                //if multiple cities found....
                // $('#selectCityModal').modal('show');
                //if not load city into scope

                $scope.center.latitude = result.location.lat;
                $scope.center.longitude = result.location.lng;
                $scope.zoom = 13;
                $scope.cityName = result.name;
                $scope.sights = result.sights;
            });
        };

        $scope.onSightClicked = function(id){
            showDetailModal(id);
        };

        $scope.onCityClicked = function(city){
            console.log("asd");
            //load city into scope
        };


        $scope.$watch('sights', function() {
            $scope.markers=[];
            for ( var i = 0; i < $scope.sights.length; i++) {
                addMarker($scope.sights[i]);
            }
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
                onClick: function(){
                    showDetailModal(data.name);
                }
                // infoWindow: "<b>"+data.name+"</b><br/>"+data.description
            });
        }

        function showDetailModal(id){
            loadDetailIntoScope(id);
            $('#detailModal').modal('show');
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