package com.baseClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SampleBaseClass {
	RequestSpecification reqSpec;
	Response response;

	public static String getPropertyFileValue(String key) {
		// 1.0 get the value from properties file using properties class
		String res = null;
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream(System.getProperty("user.dir") + "\\config.properties"));
			Object object = properties.get(key);
			res = (String) object;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	public void addHeaders(List<Header> h) {
		Headers headers = new Headers(h);
		reqSpec = RestAssured.given().headers(headers);
	}

	public void addHeader(String key, String value) {
		reqSpec = RestAssured.given().header(key, value);

	}

	public void basicAuth(String username, String password) {
		reqSpec = reqSpec.auth().preemptive().basic(username, password);
	}

	public void pathParam(String key, String value) {
		reqSpec = reqSpec.pathParam(key, value);

	}

	public void queryParam(String key, String value) {
		reqSpec = reqSpec.queryParam(key, value);

	}

	public void addBody(String payloadUrl) {
		reqSpec = reqSpec.body(payloadUrl);

	}

	public void addBody(Object payloadUrl) {
		reqSpec = reqSpec.body(payloadUrl);

	}


	public Response requestType(String reqType, String endpoint) {
		switch (reqType) {
		case "GET":

			response = reqSpec.get(endpoint);
			break;

		case "POST":
			response = reqSpec.log().all().post(endpoint);
			break;

		case "PUT":
			response = reqSpec.put(endpoint);
			break;

		case "DELETE":

			response = reqSpec.delete(endpoint);
			break;

		default:
			break;

		}
		return response;

	}
	
	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public ResponseBody getResBody(Response response) {
		ResponseBody body = response.getBody();
		return body;

	}

	public String getResBodyAsString(Response response) {
		String asString = getResBody(response).asString();
		return asString;

	}

	public String getResBodyAsPrettyString(Response response) {
		String asPrettyString = getResBody(response).asPrettyString();
		return asPrettyString;
	}

}
