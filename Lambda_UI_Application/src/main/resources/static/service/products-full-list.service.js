awsdemo.service('productsListService',function($http) {
					this.getfullProducts = function(callback) {
						var responsePromise = $http({
							url : 'https://wwzdkqw72c.execute-api.us-east-1.amazonaws.com/dev/getallproducts',
							method : 'Get',
							headers : {
								'Content-Type' : 'application/json',
							}
						});
						responsePromise.success(function(data, status, headers,config) {
							callback(data);
						});
						responsePromise.error(function(data, status, headers, config) {
									alert("AJAX failed! because no webservice is attached yet");
								});
					}

				});
