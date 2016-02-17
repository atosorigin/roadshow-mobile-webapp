'use strict';

angular.module('roadshowApp.controllers', []).controller('FeedbackController', [ '$http', '$log', '$timeout', '$location', '$scope', function($http, $log, $timeout, $location, $scope) {

	$log.debug("FeedbackController loaded");
	$scope.feedback = {};
	$scope.locations = [];
	$scope.statuses = [];
	
	$http({
        method: 'GET',
        url: 'ws/locations'     
    }).success(function (result) {
    	
    	$scope.locations = result;
    });
	
	$http({
        method: 'GET',
        url: 'ws/statuses'     
    }).success(function (result) {
    	$scope.statuses = result;
    });
	
	$scope.reset = function() {
      $scope.feedback = {};
      $scope.form.$setPristine();
	};
	
	$scope.sendFeedback = function() {
		$http({
			method : 'POST',
			url : 'ws/feedback',
			data : $scope.feedback
		}).then(function successCallback(response) {
			
			$log.debug('Sent successfully');
		}, function errorCallback(response) {

			$scope.errorMsg = {
				code : response.status,
				responseBody : response
			};

			$log.error($scope.errorMsg);
		});
	};

} ]);