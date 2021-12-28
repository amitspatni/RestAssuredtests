package tests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import Pojo.serialization.AddPlace;
import Pojo.serialization.Location;
import io.restassured.RestAssured;

public class SerializationTestsUsingPOJO {
	
	
	public static void main(String[] args) {
		
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
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
		.body(p).when().log().all().
		post("maps/api/place/add/json")
					.then().assertThat().statusCode(200).log().all();
	}

}
