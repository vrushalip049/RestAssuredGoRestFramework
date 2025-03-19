package com.qa.products.api.test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITest extends BaseTest {
	
	@Test
	public void getProductAPITest()
	{
		
		Response response =restClient.get("/products", null, null,  AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
	}

	
	@Test
	public void getProductAPILimitTest()
	{
		Map<String, String> map=Map.of("limit","5");
		Response response =restClient.get("/products", map, null,  AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();
	}

}
