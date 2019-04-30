package com.fusesource.samples;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

public class JacksonExample {
	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		//For testing
		User user = createDummyUser();
		
		try {
			//Convert object to JSON string and save into file directly 
			mapper.writeValue(new File("D:\\user.json"), user);
			
			//Convert object to JSON string
			String jsonInString = mapper.writeValueAsString(user);
			System.out.println(jsonInString);
			
			//Convert object to JSON string and pretty print
			jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(user);
			System.out.println(jsonInString);
			
			
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static User createDummyUser(){
		
		User user = new User();
		
		user.setName("mkyong");
		user.setAge(33);

		Map<String,String> msg = new HashMap<String,String>();
		msg.put("1","hello jackson 1");
		msg.put("2","hello jackson 2");
		
		user.setMsgs(msg);
		
		return user;
		
	}
}
