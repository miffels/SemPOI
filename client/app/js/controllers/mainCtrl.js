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
        $scope.loading = false;

         $scope.searchCity = function(){
            $scope.sights=[];
            $scope.loading = true;            
            restFactory.getSights($scope.city)
            .success(function(result){
                $scope.loading = false;
                if(!result.length){
                    console.log('nothing found');
                }
                else if(result.length===1){
                    $scope.center.latitude = result[0].location.lat;
                    $scope.center.longitude = result[0].location.lng;
                    $scope.zoom = 13;
                    $scope.cityName = result[0].name;
                    $scope.sights = result[0].sights;
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
                   $scope.center.latitude = selectedItem.location.lat;
                   $scope.center.longitude = selectedItem.location.lng;
                   $scope.zoom = 13;
                   $scope.cityName = selectedItem.name;
                   $scope.sights = selectedItem.sights;
                   $scope.refresh = true;
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


$scope.$watch('sights', function() {
    $scope.markers=[];
    for ( var i = 0; i < $scope.sights.length; i++) {
        addMarker($scope.sights[i]);
    }
    resizeUI();
}); 


function resizeUI(){
    $('#map').height($(window).height()-100);
    $('#list').height($(window).height()-145);
    $('#categories').height($(window).height()-145);
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
}]);

})();