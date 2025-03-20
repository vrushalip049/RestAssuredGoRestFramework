package com.qa.Oauth2.Amadeus.api.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.collections.Maps;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class AmadeusAPITest extends BaseTest {
	
	//https://developers.amadeus.com/self-service/apis-docs/guides/developer-guides/API-Keys/authorization/#managing-tokens-from-your-source-code
	@Test(enabled = false)
	public void getFlightDetailsTest() {
		
				
		Map<String, String> queryParams = Map.of("origin", "PAR", "maxPrice", "200");
		Response response = restClient.get(BASE_URL_AMADEUS, "/v1/shopping/flight-destinations", queryParams, null, AuthType.OAUTH2, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
}
