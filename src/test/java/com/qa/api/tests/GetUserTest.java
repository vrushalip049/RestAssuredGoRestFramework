package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.client.RestClient;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserTest  extends BaseTest{
	
	
	@Test
	public void getAllUserTest() {
		
		Map<String ,String> queryParams=new HashMap<String,String>();
		queryParams.put("name", "naveen");
		queryParams.put("status", "inactive");
		Response response =restClient.get("/public/v2/users", queryParams, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		System.out.println("response_>"+response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	@Test
	public void getSingleUserTest() {
		
		Map<String ,String> queryParams=new HashMap<String,String>();
		
		Response response =restClient.get("/public/v2/users/7725337", null, null, AuthType.BEARER_TOKEN, ContentType.JSON);
		
		System.out.println("response_>"+response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	

}
