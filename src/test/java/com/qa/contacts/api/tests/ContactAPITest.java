package com.qa.contacts.api.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.manager.ConfigManager;
import com.qa.api.pojo.CredentialContact;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ContactAPITest extends BaseTest {
	private String tokenId;

	@BeforeMethod

	public void getToken() {
		CredentialContact cred = CredentialContact.builder().email("vrushalip049@gmail.com").password("Vrush@123")
				.build();
		Response response = restClient.post("/users/login", cred, null, null, AuthType.NO_AUTH, ContentType.JSON);
		// String token=response.jsonPath().get("token");

		tokenId = response.jsonPath().get("token");

		System.out.println("token->" + tokenId);
		response.prettyPrint();
		
		ConfigManager.set("CONTACTS_BEARER_TOKEN", tokenId);
	}

	
	@Test
	public void getContactTest()
	{
		Response response =restClient.get("/contacts", null, null, AuthType.CONTACTS_BEARER_TOKEN,  ContentType.JSON);
		response.prettyPrint();
		
	}
}
