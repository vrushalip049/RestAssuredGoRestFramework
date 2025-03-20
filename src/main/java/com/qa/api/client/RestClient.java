package com.qa.api.client;

import com.qa.api.constants.AuthType;
import com.qa.api.exception.FrameworkException;
import com.qa.api.manager.ConfigManager;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.RestAssured.expect;

import java.io.File;
import java.util.Base64;
import java.util.Map;

public class RestClient {

	// define Response spec:
	// import static io.restassured.RestAssured.expect;
	private ResponseSpecification responseSpec200 = expect().statusCode(200);
//	/import static org.hamcrest.Matchers.*;
	private ResponseSpecification responseSpec200or400 = expect().statusCode(anyOf(equalTo(200),equalTo(404)));
	private ResponseSpecification responseSpec200or201 = expect().statusCode(anyOf(equalTo(200),equalTo(201)));
	private ResponseSpecification responseSpec201 = expect().statusCode(201);
	private ResponseSpecification responseSpec204 = expect().statusCode(204);
	private ResponseSpecification responseSpec400 = expect().statusCode(400);
	private ResponseSpecification responseSpec401 = expect().statusCode(401);
	private ResponseSpecification responseSpec404 = expect().statusCode(404);
	private ResponseSpecification responseSpec500 = expect().statusCode(500);

	private String baseUrl = ConfigManager.get("baseUrl");

	private RequestSpecification setupRequest(String baseUrl,AuthType authType, ContentType contenType) {
		RequestSpecification request = RestAssured.given().log().all().

				baseUri(baseUrl).contentType(contenType).accept(contenType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("bearerToken"));
			break;
		case CONTACTS_BEARER_TOKEN:
			request.header("Authorization", "Bearer " + ConfigManager.get("CONTACTS_BEARER_TOKEN"));
			break;

		case OAUTH2:
			request.header("Authorization", "Bearer " + genertaOauth2Toke());
			break;

		case BASIC_AUTH:
			request.header("Authorization", "Basic "+basicAuthGenterat());
			break;

		case API_KEY:
			request.header("api-key", ConfigManager.get("apiKey"));
			break;

		case NO_AUTH:
			System.out.println("This auth is not suppoted .please enter the right authtype..");
			break;

		default:
			throw new FrameworkException("No Auth Supported");
		// break;

		}
		return request;
	}

	
	private String basicAuthGenterat()
	
	{
		String cred= ConfigManager.get("basicAuthUsername")+":"+ConfigManager.get("basicAuthPassword");
		
		 return Base64.getEncoder().encodeToString(cred.getBytes());
		
	}
	private String genertaOauth2Toke() {
		return RestAssured.given().formParam("client_id", ConfigManager.get("clientId"))
				.formParam("client_secrete", ConfigManager.get("clientSecrte"))
				.formParam("grant_type", ConfigManager.get("grantType")).post(ConfigManager.get("tokenUrl")).then()
				.extract().path("access_token");

	}

	// ****************************CRUD Methods****************************//

	/**
	 * 
	 * @param endPoint
	 * @param queryParms
	 * @param pathParm
	 * @param authType
	 * @param contenType
	 * @return
	 */

	public Response get(String baseUrl,String endPoint, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupRequest( baseUrl,authType, contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.get(endPoint).then().spec(responseSpec200or400).extract().response();
		System.out.println(response);
		return response;

	}

	/**
	 * 
	 * @param <T>        T means any type of body
	 * @param endPoint
	 * @param body
	 * @param queryParms
	 * @param pathParm
	 * @param authType
	 * @param contenType
	 * @return
	 */

	public <T> Response post(String baseUrl,String endPoint, T body, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupAuthAndContentType(baseUrl,authType, contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.body(body).post(endPoint).then().spec(responseSpec200or201).extract().response();
		System.out.println(response);
		return response;

	}

	public Response post(String baseUrl,String endPoint, File file, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupAuthAndContentType(baseUrl,authType, contenType);
		// RequestSpecification request=setupRequest(authType,contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.body(file).post(endPoint).then().spec(responseSpec200or201).extract().response();
		System.out.println(response);
		return response;

	}
	
	/**put
	 * 
	 * @param <T>
	 * @param endPoint
	 * @param body
	 * @param queryParms
	 * @param pathParm
	 * @param authType
	 * @param contenType
	 * @return
	 */
	public <T> Response put(String baseUrl,String endPoint, T body, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupAuthAndContentType(baseUrl,authType, contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.body(body).put(endPoint).then().spec(responseSpec200).extract().response();
		System.out.println(response);
		response.prettyPrint();
		return response;

	}
	
	public <T> Response patch(String baseUrl,String endPoint, T body, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupAuthAndContentType(baseUrl, authType, contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.body(body).patch(endPoint).then().spec(responseSpec200).extract().response();
		System.out.println(response);
		return response;

	}
	
	public Response delete(String baseUrl,String endPoint, Map<String, String> queryParms, Map<String, String> pathParm,
			AuthType authType, ContentType contenType) {
		RequestSpecification request = setupAuthAndContentType(baseUrl,authType, contenType);

		applyParams(request, queryParms, pathParm);
		/*
		 * if (queryParms != null) { request.queryParams(queryParms); }
		 * 
		 * if (pathParm != null) { request.params(pathParm); }
		 */

		Response response = request.delete(endPoint).then().spec(responseSpec204).extract().response();
		System.out.println(response);
		return response;

	}


	private RequestSpecification setupAuthAndContentType(String baseUrl,AuthType authType, ContentType contenType) {
		return setupRequest(baseUrl,authType, contenType);
	}

	private void applyParams(RequestSpecification request, Map<String, String> queryParms,
			Map<String, String> pathParm) {

		if (queryParms != null) {
			request.queryParams(queryParms);
		}

		if (pathParm != null) {
			request.params(pathParm);
		}
	}

}
