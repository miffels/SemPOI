(function() {
  'use strict';

  controllers.controller('SelectCityCtrl', ['$scope', '$modalInstance', 'items', function ($scope, $modalInstance, items) {

    $scope.items = items;


    $scope.itemSelected = function (item) {
      $modalInstance.close(item);
    };

    $scope.cancel = function () {
      $modalInstance.dismiss('cancel');
    };

  }]);

})();