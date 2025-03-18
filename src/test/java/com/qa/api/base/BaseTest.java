package com.qa.api.base;

import org.testng.annotations.BeforeMethod;

import com.qa.api.client.RestClient;

import io.restassured.RestAssured;

public class BaseTest {
	
	//not a default RestClient restClien
	protected RestClient restClient;

	@BeforeMethod

	public  void setUp()

	{
		restClient=new RestClient();
	}

}
