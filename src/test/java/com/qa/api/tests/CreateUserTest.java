package com.qa.api.tests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.StringUtility;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateUserTest extends BaseTest {

	@Test
	public void CreateUserTest()

	{
		//1.POST Call
		
		//User user = new User("Vrushali", StringUtility.getRandomEmailId(), "female", "active");
		User user = new User(null,"Vrushali", StringUtility.getRandomEmailId(), "female", "active");
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", user, null, null,
				 AuthType.BEARER_TOKEN, ContentType.JSON);
		/*
		 * Response response = restClient.post("/public/v2/users", user, null, null,
		 * AuthType.BEARER_TOKEN, ContentType.JSON); when base url was configure in testng.xmlf file as parameter
		 */
		System.out.println("response_>" + response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 201);
		//fetch userid
		String userid=response.jsonPath().getString("id");
		System.out.println("User id"+userid);
		//2.GET call
		restClient.get(BASE_URL_GOREST,"/public/v2/users/"+userid, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);
		
		
		Assert.assertEquals(response.getStatusCode(), 201);

		Assert.assertEquals(response.jsonPath().getString("id"), userid);
		Assert.assertEquals(response.jsonPath().getString("name"), user.getName());
		Assert.assertEquals(response.jsonPath().getString("email"), user.getEmail());
		
		
		
	}

	@Test(enabled = false)
	public void CreateUserTestUsingJSONFile()

	{
		File file = new File(
				"C:\\Users\\Admin\\2025_eclipse-Automationworkspace\\RestAssuredGoRestFramework\\src\\test\\resources\\json\\user.json");
		//User user = new User("Vrushali", StringUtility.getRandomEmailId(), "female", "active");
		//User user = new User("Vrushali", "Vrushalipatisssssssssssssssaaaaal@gmail.com", "female", "active");
		Response response = restClient.post(BASE_URL_GOREST,"/public/v2/users", file, null, null, AuthType.BEARER_TOKEN,
				ContentType.JSON);

		System.out.println("response_>" + response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 201);
		

		
	}

	@Test
	public void CreateUserTestUsingBuilderTest()

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
	}

}
