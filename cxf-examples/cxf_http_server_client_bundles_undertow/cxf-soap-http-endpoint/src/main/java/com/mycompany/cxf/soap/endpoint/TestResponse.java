package com.mycompany.cxf.soap.endpoint;



import com.mycompany.cxf.soap.model.Person;

public class TestResponse {

	
	
	public Person getPerson(String id) {
		
		Person p = new Person();
		if(id.equals("1")){
		p.setAge(29);
		p.setId("1");
		p.setName("Chandra Shekhar");
		
		}else{
			p.setAge(30);
			p.setId("2");
			p.setName("Other");
		}
		return p;
	}

}
