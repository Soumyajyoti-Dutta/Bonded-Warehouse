///<reference path = "angular.min.js" />

var myApp = angular
					.module("myModule",[])
					.controller("myController",function($scope, $http, $interval, $log, $window){
						$scope.initMap = function(deviceid){
							 $window.scrollTo(0, 0);
							 document.getElementById('light').style.display='block';
							 document.getElementById('fade').style.display='block';
							 document.getElementById('loadingIcon').style.display='block';
							 document.getElementById('truckStatus').style.display='none';
							/* $http({
                                 method:'get',
                                 url:'https://cbdocbatchservice.eu-gb.mybluemix.net/service/getTruckStatus/191998866550'
							 	})
								 .then(function(response){
								                 console.log("1st call "+response.data);
								                 $scope.callMap(deviceid);
								 });
								 }
								 $scope.callMap = function(deviceid){*/
							$http({
								method:'get',
								url:'https://iotlocationtracker.mybluemix.net/truckTrack.php?truckID='+deviceid
							})
						.then(function(response){
							document.getElementById('loadingIcon').style.display='none';
							document.getElementById('truckStatus').style.display='block';
							
								var myLatLng = {lat: parseFloat(response.data.lat), lng: parseFloat(response.data.lon)};
								console.log(myLatLng);
                                if(response.data.lat != null){
                                	var map = new google.maps.Map(document.getElementById('mappop'), {
      								  zoom: 16,
      								  center: myLatLng
      								});

      								var marker = new google.maps.Marker({
      								  position: myLatLng,
      								  map: map,
      								  title: deviceid
      								});
                                }else{
                                	 document.getElementById('noData').style.display='block';
                                	 document.getElementById('truckStatus').style.display='none';
                                }
								
						});
						}
						
						$scope.StopTimer = function () {
							var iEl = angular.element( document.querySelector( '#mappop' ) );
						     iEl.html('');
							document.getElementById('light').style.display='none';
							document.getElementById('fade').style.display='none';
							document.getElementById('noData').style.display='none';
						};
						
					});

