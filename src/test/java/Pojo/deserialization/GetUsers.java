package Pojo.deserialization;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetUsers {
		
	
	private int page;
	private int per_page;
	private int total;
	private int total_pages;
	private List<Data> data;
	private Support support;
	
	
	
	
	

}
