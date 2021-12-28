package TestDataBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.response.Response;

public class Utils {
	
	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	static FileInputStream fis;
	static Properties prop = new Properties();
	
	public RequestSpecification RequestBuilder () throws IOException {
		
		if (reqSpec==null) {
		
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseURI"))
					.addQueryParam(getGlobalValue("key"), getGlobalValue("pwd"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON)
					.build();
			
			return reqSpec;
			
		}
		
		return reqSpec;
	
		
	}
	
	public ResponseSpecification ResponseBuilder() {
		
		
		resSpec = new ResponseSpecBuilder().expectStatusCode(200)
				.expectContentType(ContentType.JSON).build();

		return resSpec;
	}
	
	public static String getGlobalValue(String key) throws IOException {
		
		
		
		String userDir = System.getProperty("user.dir");
		
		File file = new File(userDir + "\\src\\test\\java\\Resources\\global.properties");
		
		fis = new FileInputStream(file);
		prop.load(fis);
		
		return prop.getProperty(key);
		
		
		
	}
	
	public static String convertResponseToString(Response res, String key) {
		
		String responseBody = res.asString();
		
		JsonPath js1 = new JsonPath(responseBody);
		
		String returnKeyValue = js1.get(key).toString();
		
		return returnKeyValue;
		
	}
	
	

}
