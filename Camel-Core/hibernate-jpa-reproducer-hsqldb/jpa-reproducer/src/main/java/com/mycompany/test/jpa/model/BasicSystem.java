package com.mycompany.test.jpa.model;

import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * System domain entity that contains the information to refer to a system.
 * 
 * @author ivor
 */
@Entity
public class BasicSystem implements ISystem {

	@Id
	@GeneratedValue
	private Long id;
	@Column(unique = true)
	private String code; // systemId
	@Column
	private String name;
	@Column
	private String uri;

	public BasicSystem() {
		/* Default constructor, to be used by Hibernate, etc */
	}

	public BasicSystem(Long id, String code, String name, URI uri) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.uri = (uri != null) ? uri.toString() : null;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public URI getUri() {
		if (uri == null) {
			return null;
		}

		try {
			return new URI(uri);
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setUri(URI uri) {
		this.uri = (uri != null) ? uri.toString() : null;
	}
}