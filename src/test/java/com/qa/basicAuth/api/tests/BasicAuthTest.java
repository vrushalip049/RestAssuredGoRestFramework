package com.qa.basicAuth.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BasicAuthTest  extends BaseTest{
	
	@Test
	
	public void BasicAuthTest()
	{
		Response response=restClient.get(BASE_URL_HEROKUAPP, "basic_auth", null, null, AuthType.BASIC_AUTH, ContentType.ANY);
		
		Assert.assertEquals(response.asString().contains("Congratulations! You must have the proper credentials."), true);
		response.prettyPrint();
	}
}
