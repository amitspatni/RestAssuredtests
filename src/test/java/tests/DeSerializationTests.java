package tests;

import io.restassured.RestAssured;
//import io.restassured.parsing.Parser;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static io.restassured.RestAssured.*;

import Pojo.deserialization.GetUsers;


public class DeSerializationTests {
	
	
	public static void main(String[] args) {
		
		
		RestAssured.baseURI = "https://reqres.in";
		
		GetUsers gu =given().queryParam("page", 2).header("Content-Type", "application/json")
				// .expect().defaultParser(Parser.JSON)
				// above default parser be required only response is in other than JSON)
				.when().get("/api/users/").as(GetUsers.class);
		
		
		System.out.println(gu.getPage());
		
		
		
		
		
	}

}
