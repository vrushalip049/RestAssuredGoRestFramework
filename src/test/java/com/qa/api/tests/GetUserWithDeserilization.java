package com.qa.api.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.api.base.BaseTest;
import com.qa.api.constants.AuthType;
import com.qa.api.pojo.User;
import com.qa.api.utils.JsonUtils;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetUserWithDeserilization extends BaseTest {
	
	@Test
	public void getDeserilizeAllUserTest() {
		
		
		Response response =restClient.get(BASE_URL_GOREST,"/public/v2/users", null, null, AuthType.NO_AUTH, ContentType.JSON);
		
		System.out.println("response_>"+response.asPrettyString());
		Assert.assertEquals(response.getStatusCode(), 200);
		
		User [] users=JsonUtils.deserialize(response, User[].class);
		for (User u:users)
		{System.out.println("--------------");
			System.out.println("Id->"+u.getId());
			System.out.println("getName->"+u.getName());
			System.out.println("getEmail->"+u.getEmail());
			System.out.println("getGender->"+u.getGender());
			System.out.println("getStatus->"+u.getStatus());
			
		}
	}
	

}
