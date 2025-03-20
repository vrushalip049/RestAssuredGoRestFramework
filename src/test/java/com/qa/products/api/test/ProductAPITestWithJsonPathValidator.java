package com.qa.products.api.test;

import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.utils.JsonPathValidator;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class ProductAPITestWithJsonPathValidator extends BaseTest {

	@Test(enabled = false)
	public void getProductTestJsonPathTest() {
		Response response = restClient.get(BASE_URL_PRODUCT,"/products", null, null, AuthType.NO_AUTH, ContentType.JSON);
		Assert.assertEquals(response.getStatusCode(), 200);
		response.prettyPrint();

		List<Number> prices = JsonPathValidator.readList(response, "$[?(@.price>50)].price");

		System.out.println("prices->" + prices);
		List<Number> ids = JsonPathValidator.readList(response, "$[?(@.price>50)].id");

		System.out.println("ids->" + ids);
		List<Number> rates = JsonPathValidator.readList(response, "$[?(@.price>50)].rating.rate");

		System.out.println("rates->" + rates);
		List<Number> counts = JsonPathValidator.readList(response, "$[?(@.price>50)].rating.count");

		System.out.println("counts->" + counts);
		//map
		
		List<Map<String, Object>> jewleryList = JsonPathValidator.readMap(response, "min($[*].price)");

	
		System.out.println(jewleryList);
		System.out.println(jewleryList.size());
		
		for(Map<String, Object> product : jewleryList) {
			String title = (String) product.get("title");
			Number price = (Number) product.get("price");
			System.out.println("title:" + title);
			System.out.println("price:" + price);
			System.out.println("-----------");
		}
		
		//to get min price
		int minPrice = JsonPathValidator.read(response, "$.MRData.CircuitTable.Circuits.length()");
		System.out.println(minPrice);
		
	}
}
