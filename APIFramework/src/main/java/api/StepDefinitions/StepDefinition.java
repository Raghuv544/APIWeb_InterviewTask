package api.StepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import api.Resources.APIResources;
import api.Resources.Utils;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition extends Utils {
	RequestSpecification request;
	ResponseSpecification resspec;
	Response response;

	@Given("Add Healthz API Request")
	public void add_Healthz_API_Request() {
		try {
			request = given().spec(requestSpecification());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Then("verify the API response code")
	public void verify_the_API_response_code() {
		assertEquals(response.getStatusCode(), 200);
	}

	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) {
		// constructor will be called with value of resource which you pass
		APIResources resourceAPI = APIResources.valueOf(resource);
		resspec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).build();
		if (method.equalsIgnoreCase("POST"))
			response = request.when().post(resourceAPI.getResource());
		else if (method.equalsIgnoreCase("GET"))
			response = request.when().get(resourceAPI.getResource());
		//
		//
		//
		//
		
	}

	@Then("getEULA using {string} and Query Paramenter {string} based on HealthStatusCode {string}")
	public void geteula_using_and_Query_Paramenter_based_on_HealthStatusCode(String resource, String QuerytenantVal,
			String expHealthCode) {
		APIResources resourceAPI = APIResources.valueOf(resource);
		String actHealthCode = getJsonPath(response, "code");
		if (actHealthCode.equals(expHealthCode)) {
			try {
				request = given().spec(requestSpecification()).queryParam("tenant", QuerytenantVal);
				user_calls_with_http_request(resource, "GET");
				String actualName = getJsonPath(response, "eula_b64");
				System.out.println("eula_b64: "+actualName);
				assertNotNull(actualName);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
