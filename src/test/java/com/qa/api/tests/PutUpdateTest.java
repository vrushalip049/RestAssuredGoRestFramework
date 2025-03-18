package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutUpdateTest  extends BaseTest{
	
	@Test
	public void CreateUserTestUsingBuilderPutTest()

	{
		// 1. POST
		User user = User.builder().name("Vrushali").email(StringUtility.getRandomEmailId()).status("active")
				.gender("female").build();
		Response response = restClient.post("/public/v2/users", user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);

		System.out.println("response_>" + response.asPrettyString());
		System.out.println("response_>" + response.getStatusLine());
		Assert.assertEquals(response.getStatusCode(), 201);
		// fetch userid
		String userid = response.jsonPath().getString("id");
		System.out.println("User id" + userid);

		// 2. GET
		Response responseGET = restClient.get("/public/v2/users/" + userid, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);

		Assert.assertEquals(responseGET.getStatusCode(), 200);

		Assert.assertEquals(responseGET.jsonPath().getString("id"), userid);
		Assert.assertEquals(responseGET.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGET.jsonPath().getString("email"), user.getEmail());

		// Assert.assertEquals(responseGET.jsonPath().get("id"), userId);
		
		//3.PUT call
		//update user details
		user.setStatus("inactive");
		user.setGender("male");
		Response responsePUT =restClient.put("/public/v2/users/"+userid, user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(responsePUT.getStatusCode(), 200);

		Assert.assertEquals(responsePUT.jsonPath().getString("id"), userid);
		Assert.assertEquals(responsePUT.jsonPath().getString("status"), user.getStatus());
		Assert.assertEquals(responsePUT.jsonPath().getString("gender"), user.getGender());

		
		
	}


}
