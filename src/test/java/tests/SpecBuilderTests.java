package tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.serialization.AddPlace;
import Pojo.serialization.Location;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilderTests {
	
	
	public static void main(String[] args) {
		//serialization using RequestSpecBuilder / RequestSpecification/ ResponseSpecification
		
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress("Bhoomi Ksgitij");
		p.setLanguage("French-IN");
		p.setPhone_number("+91 8806273328");
		p.setWebsite("https://google.com");
		p.setName("amit Jain house");
			
		List<String> listString = new ArrayList<String>();
		
		listString.add("Shoe park");
		listString.add("Water park");
		
		p.setTypes(listString);
		
		Location l = new Location();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
			
		p.setLocation(l);
		
		RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		
		RequestSpecification req = given()
		.spec(reqspec).body(p);
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectStatusCode(200)
										.expectContentType(ContentType.JSON).build();
		
		Response res = req.when().post("maps/api/place/add/json")
				.then().spec(resSpec).extract().response();
			
		String response =  res.asString();
		System.out.println(response);
		

//		
//		when().log().all().
//		post("maps/api/place/add/json")
//					.then().assertThat().statusCode(200).log().all();
	
		
		
		
	 }

}
