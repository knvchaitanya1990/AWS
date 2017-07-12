awsdemo.service('searchProductsService', function($http) {

this.searchProduct = function(productID,callback){
	
	
    var responsePromise = 	 $http({	
        url: 'https://3yznlsrs97.execute-api.us-east-1.amazonaws.com/dev/searchproduct',
        method: "POST", // In this case it is POST
        data: productID,
        headers: { 'Content-Type': 'application/json' }
     });
        responsePromise.success(function(data, status, headers, config) {
            callback(data);
        });
        responsePromise.error(function(data, status, headers, config) {
            alert("AJAX failed! because no webservice is attached yet");
        });
}

});