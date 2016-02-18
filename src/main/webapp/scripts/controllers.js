'use strict';

angular.module('roadshowApp.controllers', []).controller(
		'FeedbackController',
		[
				'$http',
				'$log',
				'$timeout',
				'$location',
				'$scope',
				'$anchorScroll',
				function($http, $log, $timeout, $location, $scope,
						$anchorScroll) {

					$log.debug("FeedbackController loaded");
					$scope.feedback = {};
					$scope.locations = [ {name:"Stockton"}, {name:"Durham"} ];
					$scope.statuses = [ {name:"Active"}, {name:"On Hold"}, {name:"Complete"} ];
					
					$scope.success = null;
					$scope.error = null;

					/*$http({
						method : 'GET',
						url : 'ws/locations'
					}).success(function(result) {

						$log.debug("locations fetched " + result);
						if (result != undefined && result.length > 0) {
							// Bug here empty array still gets set to backing values
							$scope.locations = result;
						}
					});

					$http({
						method : 'GET',
						url : 'ws/statuses'
					}).success(function(result) {

						$log.debug("statuses fetched " + result);
						if (result != undefined && result.length > 0) {
							$scope.statuses = result;
						}

					});*/

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

							$location.hash('pageTop');

							$anchorScroll();
						}, function errorCallback(response) {

							$scope.errorMsg = {
								code : response.status,
								responseBody : response
							};

							$scope.error = true;

							$log.error($scope.errorMsg);

							$location.hash('pageTop');
							$anchorScroll();
						});
					};

				} ]);