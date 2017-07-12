package com.amazonaws.lambda.demo;

import java.util.LinkedHashMap;

import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidentity.model.GetOpenIdTokenForDeveloperIdentityRequest;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class AuthenticateUser implements RequestHandler<Object, Object> {
   
    public AuthenticateUserResponse handleRequest(Object input, Context context) {
          
        AuthenticateUserResponse authenticateUserResponse = new AuthenticateUserResponse();
        @SuppressWarnings("unchecked")
        LinkedHashMap inputHashMap = (LinkedHashMap)input;
        User user = authenticateUser(inputHashMap);
        if(user!=null){
            authenticateUserResponse.setUserId(user.getUserId());
            authenticateUserResponse.setStatus("true");
            authenticateUserResponse.setOpenIdToken(user.getOpenIdToken());
        }else{
            authenticateUserResponse.setUserId(null);
            authenticateUserResponse.setStatus("false");
            authenticateUserResponse.setOpenIdToken(null);
        }
            
        return authenticateUserResponse;
    }

	

public User authenticateUser(LinkedHashMap input){
    User user=null;
    	
    String userName = input.get("userName");
    String passwordHash = input.get("passwordHash");
    	
    try{
        AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(Regions.US_EAST_1));
        DynamoDBMapper mapper = new DynamoDBMapper(client);
	    	
        user = mapper.load(User.class, userName);
	    	
        if(user!=null){
            if(user.getPasswordHash().equalsIgnoreCase(passwordHash)){
                String openIdToken = getOpenIdToken(user.getUserId());
                user.setOpenIdToken(openIdToken);
                return user;
            }
        }
    }catch(Exception e){
        System.out.println(e.toString());
    }
    return user;
}


private String getOpenIdToken(Integer userId){
    	
    AmazonCognitoIdentityClient client = new AmazonCognitoIdentityClient();
    GetOpenIdTokenForDeveloperIdentityRequest tokenRequest = new GetOpenIdTokenForDeveloperIdentityRequest();
    tokenRequest.setIdentityPoolId("us-west-1:6dbccdfd-9444-4d4c-9e1b-5d1139cbe863");
    	
    HashMap map = new HashMap();
    map.put("login.dhruv.services", userId.toString());
    	
    tokenRequest.setLogins(map);
    tokenRequest.setTokenDuration(new Long(10001));
    	
    GetOpenIdTokenForDeveloperIdentityResult result = client.getOpenIdTokenForDeveloperIdentity(tokenRequest);
    	
    String token = result.getToken();
    	
    return token;
}
   
}
