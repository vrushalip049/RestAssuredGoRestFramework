package com.qa.api.utils;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

import io.restassured.response.Response;

public class JsonPathValidator {
	
	private static String getJsonseAsString(Response response)
	{
		return response.getBody().asString();
	}
	
	public static <T> T read(Response response,String jsonpath)
	{
		String jsonResonse=getJsonseAsString(response);
		ReadContext ctx=JsonPath.parse(jsonResonse);
		return ctx.read(jsonpath);
	}
	public static <T> List<T> readList(Response response,String jsonpath)
	{
		String jsonResonse=getJsonseAsString(response);
		ReadContext ctx=JsonPath.parse(jsonResonse);
		return ctx.read(jsonpath);
	}
	
	public static <T> List<Map<String,T>>readMap(Response response,String jsonpath)
	{
		String jsonResonse=getJsonseAsString(response);
		ReadContext ctx=JsonPath.parse(jsonResonse);
		return ctx.read(jsonpath);
	}

}
