package com.mycompany.test.jpa.model;

import java.net.URI;

public interface ISystem {

	String getCode();

	Long getId();

	String getName();

	URI getUri();

	void setCode(String code);

	void setName(String name);

	void setUri(URI uri);

}
