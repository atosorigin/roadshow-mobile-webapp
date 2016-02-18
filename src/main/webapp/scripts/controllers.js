'use strict';

angular.module('roadshowApp.controllers', []).controller('FeedbackController', [ '$http', '$log', '$timeout', '$location', '$scope', function($http, $log, $timeout, $location, $scope) {

	$log.debug("FeedbackController loaded");
	$scope.feedback = {};
	$scope.locations = ["Stockton", "Durham"];
	$scope.statuses = ["Active", "On Hold", "Complete"];
	
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
      $scope.success = null;
      $scope.error = null;
	};
	
	$scope.sendFeedback = function() {
		
		$scope.success = null;
	    $scope.error = null;
		
		$http({
			method : 'POST',
			url : 'ws/feedback',
			data : $scope.feedback
		}).then(function successCallback(response) {
			
			$log.debug('Sent successfully');
			$scope.success = true;
			$scope.feedback = {};
		    $scope.form.$setPristine();
		}, function errorCallback(response) {

			$scope.errorMsg = {
				code : response.status,
				responseBody : response
			};
			
			$scope.error = true;

			$log.error($scope.errorMsg);
		});
	};

} ]);