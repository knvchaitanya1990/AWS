awsdemo.service('saveProductService', function($http) {

this.saveProduct = function(productInput,callback){
    var responsePromise = 	 $http({	
        url: 'https://pr7w5v87p0.execute-api.us-east-1.amazonaws.com/dev/savenewproduct',
        method: "POST", 
        data: productInput,
        headers:
        { 
        	'Content-Type': 'application/json' 
        		
        }
     });
        responsePromise.success(function(data, status, headers, config) {
            callback(data);
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed! because no webservice is attached yet");
        });
}
});