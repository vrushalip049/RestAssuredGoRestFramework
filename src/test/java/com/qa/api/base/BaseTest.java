package com.qa.api.base;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.qa.api.client.RestClient;
import com.qa.api.manager.ConfigManager;

import io.restassured.RestAssured;

public class BaseTest {

	// not a default RestClient restClien
	protected static final String BASE_URL_GOREST="https://gorest.co.in";
	protected static final String BASE_URL_CIRCUIT="https://ergast.com";
	protected static final String BASE_URL_PRODUCT="https://fakestoreapi.com";
	protected static final String BASE_URL_CONTACTS="https://thinking-tester-contact-list.herokuapp.com";
	protected static final String BASE_URL_REQ_RES="https://reqres.in";
	protected static final String BASE_URL_HEROKUAPP="https://the-internet.herokuapp.com";

	protected RestClient restClient;

	// @org.testng.annotations.Parameters({"baseUrl"})->as it was added in
	// testng.xml file
	@BeforeTest
	// @@BeforeMethod

	// public void setUp(String baseUrl)->as it was added in testng.xml file
	public void setUp() {

		/*
		 * if (baseUrl != null) { ConfigManager.set("baseUrl", baseUrl); }->as it was
		 * added in testng.xml file
		 */
		restClient = new RestClient();
	}

}
