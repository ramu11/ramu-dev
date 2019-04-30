package com.mycompany.test.interfaces;

import java.net.URI;
import java.util.List;


import com.mycompany.test.jpa.model.BasicSystem;

public interface HibenrateBasicSystemInterface {
	public boolean add(BasicSystem basicSystem);
	public BasicSystem get(String code);
	public BasicSystem get(long id);
	public boolean update(BasicSystem basicSystem);
	public boolean delete(String code);
	public List<BasicSystem> getAll();
	public List<BasicSystem> getAllByUri(URI uri);
}
