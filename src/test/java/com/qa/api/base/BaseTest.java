package com.qa.api.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;

public class BaseTest {
	
	//not a default RestClient restClien
	protected RestClient restClient;
	@org.testng.annotations.Parameters({"baseUrl"})
	@BeforeTest
	//@@BeforeMethod

	public  void setUp(String baseUrl)

	{
		
		if (baseUrl != null)
		{
			ConfigManager.set("baseUrl", baseUrl);
		}
		restClient=new RestClient();
	}

}
