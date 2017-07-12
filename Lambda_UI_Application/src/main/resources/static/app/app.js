var awsdemo = angular.module('aws', ['ui.router','angularSpinner']);

awsdemo.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.when("", "/home");

    $stateProvider
       
        .state("aws", {
            url: "/aws",
            templateUrl: "views/producsthome.html",
            controller : 'productsHomeController'
        })
        .state("aws.ProductList", {
            url: "/ProductList",
            templateUrl : 'views/ProductList.html',
		    controller :'productsListController'
        })
        .state("aws.ProductSearch", {
            url: "/ProductSearch",
            templateUrl : 'views/ProductSearch.html',
		    controller :'productsSearchController'
        })
        .state("aws.Productdelete", {
            url: "/Productdelete",
            templateUrl : 'views/Productdelete.html',
		    controller :'DeleteProductController'
        })
        .state("aws.Productupdate", {
            url: "/Productupdate",
            templateUrl : 'views/Productupdate.html',
	        controller :'productUpdateController'
        })
        
        .state("aws.SaveProduct", {
            url: "/SaveProduct",
            templateUrl : 'views/ProductInsert.html',
          //  templateUrl : 'views/SaveProduct.html',
	        controller :'saveproductController'
        })
        
         .state("home", {
            url: "/home",
            templateUrl: "views/home.html"
        })
    ;
});

