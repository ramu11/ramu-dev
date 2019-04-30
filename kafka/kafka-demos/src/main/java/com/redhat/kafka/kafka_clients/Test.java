/**
 rest("/api/user/*") 

To match
- /api/user
- /api/user/foo
-/api/user/foo/bar

/path/** that matches all nested paths within /path, but you also have regex support and you can implement custom HandlerMapping that maps in whatever way you feel is needed.

*/
package com.redhat.kafka.kafka_clients;

public class Test {

	public static void main(String[] args) {
		
		 String pattern = "\'foo*";
	        String line = "\foo";
	        if (line.matches(pattern)) {
	            System.out.println(line + " matches \"" + pattern + "\"");
	        } else {
	            System.out.println("NO MATCH");
	        }
	    
	}

}
