package com.qa.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchUpdateTest  extends BaseTest{
	@Test
	public void CreateUserTestUsingBuilderPatchTest()

	{
		// 1. POST
		User user = User.builder().name("Vrushali").email(StringUtility.getRandomEmailId()).status("active")
				.gender("female").build();
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);

		System.out.println("response_>" + response.asPrettyString());
		System.out.println("response_>" + response.getStatusLine());
		Assert.assertEquals(response.getStatusCode(), 201);
		// fetch userid
		String userid = response.jsonPath().getString("id");
		System.out.println("User id" + userid);

		// 2. GET
		Response responseGET = restClient.get(BASE_URL_GOREST,"/public/v2/users/" + userid, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);

		Assert.assertEquals(responseGET.getStatusCode(), 200);

		Assert.assertEquals(responseGET.jsonPath().getString("id"), userid);
		Assert.assertEquals(responseGET.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(responseGET.jsonPath().getString("email"), user.getEmail());

		// Assert.assertEquals(responseGET.jsonPath().get("id"), userId);
		
		//3.Patch call
		//update user details
		user.setStatus("inactive");
		user.setGender("male");
		Response responsePatch =restClient.patch(BASE_URL_GOREST,"/public/v2/users/"+userid, user, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		Assert.assertEquals(responsePatch.getStatusCode(), 200);

		Assert.assertEquals(responsePatch.jsonPath().getString("id"), userid);
		Assert.assertEquals(responsePatch.jsonPath().getString("status"), user.getStatus());
		Assert.assertEquals(responsePatch.jsonPath().getString("gender"), user.getGender());

		
		
	}

}
