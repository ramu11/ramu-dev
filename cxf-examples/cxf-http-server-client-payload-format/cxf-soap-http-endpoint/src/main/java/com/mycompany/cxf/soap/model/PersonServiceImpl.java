package com.mycompany.cxf.soap.model;

import javax.jws.WebService;

@WebService(endpointInterface = "com.mycompany.cxf.soap.model.PersonService")
public class PersonServiceImpl implements PersonService {

	
	public Person getPerson(String id) throws PersonException {
		
		
		Person p = new Person();
		if(id.equals("1")){
		p.setAge(10);
		p.setId("1");
		p.setName("Ramu");
		
		}else{
			p.setAge(20);
			p.setId("2");
			p.setName("Other");
		}
		return p;
	}

	
	public Person putPerson(Person person) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Person deletePerson(String id) throws PersonException {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
 