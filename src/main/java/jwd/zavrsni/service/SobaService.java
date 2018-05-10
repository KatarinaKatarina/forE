package jwd.zavrsni.service;

import java.util.List;

import jwd.zavrsni.model.Soba;

public interface SobaService {
	
	Soba findOne(Integer id);

	List<Soba> findAll();

	Soba save(Soba Soba);

	Soba delete(Integer id);

}
