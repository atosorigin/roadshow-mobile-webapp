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

					// initial form state
					$log.debug("FeedbackController loaded");
					$scope.feedback = {};
					$scope.locations = [ {
						name : "Stockton"
					}, {
						name : "Durham"
					} ];
					$scope.statuses = [ {
						name : "Active"
					}, {
						name : "On Hold"
					}, {
						name : "Complete"
					} ];

					$scope.success = null;
					$scope.error = null;
					$scope.sending = false;

					$scope.scrollBackToTop = function() {
						$location.hash('pageTop');
						$anchorScroll();
					};

					$scope.reset = function() {
						$scope.feedback = {};
						$scope.form.$setPristine();
						$scope.success = null;
						$scope.error = null;
					};

					$scope.sendFeedback = function() {

						$scope.success = null;
						$scope.error = null;
						$scope.sending = true;

						$http({
							method : 'POST',
							url : 'ws/feedback',
							data : $scope.feedback
						}).then(function successCallback(response) {

							$log.debug('Sent successfully');
							$scope.success = true;
							$scope.feedback = {};
							$scope.form.$setPristine();
							$scope.sending = false;

							$scope.scrollBackToTop();

						}, function errorCallback(response) {

							$scope.errorMsg = {
								code : response.status,
								responseBody : response
							};

							$scope.error = true;

							$log.error('Error saving ' + $scope.errorMsg);
							$scope.sending = false;

							$scope.scrollBackToTop();
						});
					};

				} ]).controller('ResultsController',
		[ '$http', '$log', '$scope', function($http, $log, $scope) {
			$scope.feedback = [];

			$log.debug('ResultsController loaded.');
			
			$scope.error = null;

			$http({
				method : 'GET',
				url : 'ws/feedback'
			}).then(function(response) {
				$log.debug("feedback fetched " + response.data);				
				$scope.feedback = response.data;		
			}, function(response) {
				$log.error("Error occured! " + response);
				$scope.error = "Failed to load data ("+ response.code + "). Please try again";
			})

		} ]);