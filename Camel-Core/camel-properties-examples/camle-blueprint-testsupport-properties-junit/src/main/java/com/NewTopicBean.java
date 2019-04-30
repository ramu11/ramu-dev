package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class NewTopicBean {
	
	 private Random ran = new Random();

	    /**
	     * Generates a new topic structured as a {@link Map}
	     */
	    public Map<String, Object> generateNewTopic() {
	        Map<String, Object> answer = new HashMap<String, Object>();
	        answer.put("number", String.valueOf(ran.nextInt()));
	        answer.put("departure", "888");
	        answer.put("arrival",  "555" );
	        return answer;
	    }
	    
	    public String processNewTopic(Map<String, Object> data) {
	        return "Processed NewTopic id " + data.get("number") + " number " 
			+ data.get("departure")
			+ " of " + data.get("arrival");
	    }

	   

}
