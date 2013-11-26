/* global controllers:false */

(function() {
    'use strict';

    controllers.controller('MainCtrl', ['$scope', 'restFactory', '$modal', function($scope, restFactory, $modal) {

        $scope.center = {
             latitude: 49, // initial map center latitude
             longitude: 9 // initial map center longitude
         };
         $scope.zoom = 8;
         $scope.markers = [];
         $scope.sights=[];
         $scope.filteredSights = [];
         $scope.loading = false;
         $scope.filters = [];
         var directionsService = new google.maps.DirectionsService();

         $scope.searchCity = function(){
            $scope.sights=[];
            $scope.filteredSights = [];
            $scope.filters = [];
            $scope.loading = true;            
            restFactory.getSights($scope.city).success(function(result){
                $scope.loading = false;
                if(!result.length){
                    console.log('nothing found');
                }
                else if(result.length===1){
                    setScopeValues(result[0]);
                }else{
                   var modalInstance = $modal.open({
                    templateUrl: 'partials/selectCityModal.html',
                    controller: 'SelectCityCtrl',
                    resolve: {
                        items: function () {
                          return result;
                      }
                  }
              });
                   modalInstance.result.then(function (selectedItem) {
                    setScopeValues(selectedItem);
                }, function () {
                    console.log('Modal dismissed at: ' + new Date());
                });
               }

           })
            .error(function(){
                console.log('request failed');
                $scope.loading = false;
                $scope.sights=[];
            });

        };

        $scope.onSightClicked = function(id){
            showDetailModal(id);
        };

        $scope.clearSelection = function(){
            $scope.filters.map(function(filter){
              filter.selected = false;
          });
            updateSelection();
        };

        $scope.calculateRoute = function(){
            if($scope.filteredSights.length<=1){
                return;
            } else if($scope.filteredSights.length ===2){
              var origin =  new google.maps.LatLng($scope.filteredSights[0].location.lat, $scope.filteredSights[0].location.lng);
              var dest =  new google.maps.LatLng($scope.filteredSights[1].location.lat, $scope.filteredSights[1].location.lng);
              var request = {
                origin: origin,
                destination: dest,
                optimizeWaypoints: true,
                provideRouteAlternatives: false,
                travelMode: google.maps.TravelMode.WALKING
            };
            
        } else{
            var origin =  new google.maps.LatLng($scope.filteredSights[0].location.lat, $scope.filteredSights[0].location.lng);
            var dest =  new google.maps.LatLng($scope.filteredSights[1].location.lat, $scope.filteredSights[1].location.lng);
            var waypts = [];
            for (var i = 2; i < $scope.filteredSights.length; i++) {
              waypts.push({
                  location:new google.maps.LatLng($scope.filteredSights[i].location.lat, $scope.filteredSights[i].location.lng),
                  stopover:true
              });
          }
          var request = {
            origin: origin,
            destination: dest,
            waypoints: waypts,
            optimizeWaypoints: true,
            provideRouteAlternatives: false,
            travelMode: google.maps.TravelMode.WALKING
        };
    }
    directionsService.route(request, function (response, status) {
        if (status == google.maps.DirectionsStatus.OK) {
            console.log(response);
            $scope.directions = response;
            $scope.$apply();
        }
    });
};

$scope.$watch('filteredSights', function() {
    $scope.markers=[];
    for ( var i = 0; i < $scope.filteredSights.length; i++) {
        addMarker($scope.filteredSights[i]);
    }
    resizeUI();
}); 

$scope.$watch('filters', function() {
    updateSelection();
},true); 

function setScopeValues(sight){
    $scope.center.latitude = sight.location.lat;
    $scope.center.longitude = sight.location.lng;
    $scope.zoom = 13;
    $scope.cityName = sight.name;
    $scope.sights = sight.sights;
    $scope.filteredSights = $scope.sights;
    $scope.types = sight.types;
    setFilters($scope.types);
}

function setFilters(types){
    $.each( types,
       function(index,value){ 
        $scope.filters.push({
            id : index,
            count : value,
            selected : false
        });
    });
}

function resizeUI(){
    $('#map').height($(window).height()-100);
    $('#list').height($(window).height()-185);
    $('#categories').height($(window).height()-185);
}

function addMarker(data) {
    $scope.markers.push({
        latitude: parseFloat(data.location.lat),
        longitude: parseFloat(data.location.lng),
        onClick: function(){
            showDetailModal(data.name);
        }
    });
}

function showDetailModal(id){
    loadDetailIntoScope(id);
    var modalInstance = $modal.open({
        templateUrl: 'partials/sightDetailModal.html',
        scope: $scope
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

function updateSelection(){
    var numOfSelectedItems=0;
    $scope.filteredSights=[];
    $scope.filters.map(function(filter){
      if(filter.selected){
        numOfSelectedItems++;
        $scope.sights.map(function(sight){
          if(sight.types.indexOf(filter.id)!=-1 && $scope.filteredSights.indexOf(sight) ==-1 ){
            $scope.filteredSights.push(sight);
        }
    });
    }
});
    if(numOfSelectedItems===0){
      $scope.filteredSights=$scope.sights;
  }
}
}]);

})();