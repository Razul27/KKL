package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.baseClass.SampleBaseClass;
import com.endpoint.Endpoints;
import com.pojo.DummieeData;
import com.pojo.DummieeLogin_Output_pojo;

import io.restassured.response.Response;

public class OmrVelsClub extends SampleBaseClass{
	
	String logtoken;
	
	@Test(priority = 1)
	public void login_Page() {

		addHeader("accept", "application/json");

		basicAuth(getPropertyFileValue("username"), getPropertyFileValue("password"));

		Response requestType = requestType("POST", Endpoints.POSTMANBASICAUTHLOGIN);

		System.out.println(getStatusCode(requestType));
		System.out.println(requestType.as(DummieeLogin_Output_pojo.class).getMessage()().getFirst_name());

		logtoken = requestType.as(DummieeData.class).getData().logtoken();

		Assert.assertEquals(getStatusCode(requestType), 200, "verify the statuscode");

		Assert.assertEquals(requestType.as(DummieeLogin_Output_pojo.class).Dum().getFirst_name(), "Mohamed",
				"verify the first_name");

	}

}
