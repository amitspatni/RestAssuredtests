package TestDataBuilder;

import java.util.ArrayList;
import java.util.List;

import Pojo.serialization.AddPlace;
import Pojo.serialization.Location;

public class TestDataBuild {
	
	
	
	public AddPlace addPlacePayload() {
		
		  
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
		
		return p;
		
	}
	
	public AddPlace addPlacePayload(String name, String place, String address) {
		  
		AddPlace p = new AddPlace();
		p.setAccuracy(50);
		p.setAddress(place);
		p.setLanguage("French-IN");
		p.setPhone_number("+91 8806273328");
		p.setWebsite(address);
		p.setName(name);
			
		List<String> listString = new ArrayList<String>();
		
		listString.add("Shoe park");
		listString.add("Water park");
		
		p.setTypes(listString);
		
		Location l = new Location();
		
		l.setLat(-38.383494);
		l.setLng(33.427362);
			
		p.setLocation(l);
		
		return p;
		
	}
	
	public String deletePlacePayload(String place_id) {
		
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\" : \""+place_id+"\"\r\n"
				+ "}";
	
	}

}
