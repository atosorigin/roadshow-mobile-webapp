'use strict';

angular.module('roadshowApp.controllers', []).controller('FeedbackController', [
	'$http',
	'$log',
	'$timeout',
	'$location',
	'$scope',		
	function($http, $log, $timeout, $location, $scope) {

		$log.debug("FeedbackController loaded");
    $scope.feedback= {};
    
    $scope.sendFeedback = function() {
      alert('Sending feedback ' + $scope.feedback.name);
    };
  

	} ]);