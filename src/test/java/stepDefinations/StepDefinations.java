package stepDefinations;
import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.junit.Assert;

import TestDataBuilder.APIResource;
import TestDataBuilder.TestDataBuild;
import TestDataBuilder.Utils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;	

public class StepDefinations extends Utils {

	
	 //RequestSpecification reqspec;
	 RequestSpecification req;
	 //ResponseSpecification resSpec;
	 Response res;
	 TestDataBuild td = new TestDataBuild();
	 String place_id;
	
@Given("Add place payload")
public void add_place_payload() throws IOException {
  
	req = given()
	.spec(RequestBuilder()).body(td.addPlacePayload());
	
}


@Given("Add place payload with {string} {string} {string}")
public void add_place_payload_with(String name, String place, String address) throws IOException {
    // Write code here that turns the phrase above into concrete actions
    
	req = given()
			.spec(RequestBuilder()).body(td.addPlacePayload(name, place, address));
}

@When("user calls {string} with {string} http request")
public void user_calls_with_post_http_request(String resource, String method) {
	//using enum
    APIResource resourceAPI = APIResource.valueOf(resource);
    
    if (method.equalsIgnoreCase("post")) {
    
    	 res = req.when().post(resourceAPI.getResource())
    				.then().spec(ResponseBuilder()).extract().response();
     } else if (method.equalsIgnoreCase("get")) {
    	 
    	 res = req.when().get(resourceAPI.getResource())
 				.then().spec(ResponseBuilder()).extract().response();
    	 
     }
    	
}

@Then("the API call got success with status code {int}")
public void the_api_call_got_success_with_status_code(int int1) {
    // Write code here that turns the phrase above into concrete actions
    
	int statusCode = res.getStatusCode();
	
		Assert.assertEquals(int1, statusCode);
	
}

@Then("{string} in response body is {string}")
public void in_response_body_is(String key, String expectedValue) {
 
	String actualMsg = convertResponseToString(res, key);
	
	Assert.assertEquals(expectedValue, actualMsg);

}


@Then("verify {string} created maps to {string} using {string}")
public void verify_place_id_created_maps_to_using(String field1 , String field2, String resource) throws IOException {
    // Write code here that turns the phrase above into concrete actions

	String place_id_value = convertResponseToString(res, field1);
	req = given()
			.spec(RequestBuilder()).queryParam(field1, place_id_value);
	user_calls_with_post_http_request(resource, "GET");
	
	 Assert.assertEquals(field2, convertResponseToString(res, "name"));
	 	
}


@Given("Delete place payload")
public void delete_place_payload() throws IOException {
    // create the new place using post call 
	req = given()
			.spec(RequestBuilder()).body(td.addPlacePayload());
	
	user_calls_with_post_http_request("AddPlaceAPI", "Post");

	//create the delete payload
	
	req = given()
			.spec(RequestBuilder()).body(td.deletePlacePayload(convertResponseToString(res, "place_id")));
	
 APIResource resourceAPI = APIResource.valueOf("DeletePlaceAPI");
	
	
	
	
	
	
	
	
	
	
}


}
