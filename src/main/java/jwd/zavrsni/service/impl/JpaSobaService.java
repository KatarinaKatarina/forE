package jwd.zavrsni.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Soba;
import jwd.zavrsni.repository.SobaRepository;
import jwd.zavrsni.service.SobaService;

@Service
public class JpaSobaService implements SobaService {

	@Autowired  //ako ovo zaboravis:  Exception encountered during context initialization - cancelling refresh attempt
				//baca NullPointerException i nece da radi
	private SobaRepository sobaRepo;

	@Override
	public Soba findOne(Integer id) {

		return sobaRepo.findOne(id);
	}

	@Override
	public List<Soba> findAll() {

		return sobaRepo.findAll();
	}

	@Override
	public Soba save(Soba Soba) {
		System.out.println("cuvanje sobe");
		return sobaRepo.save(Soba);
	}

	@Override
	public Soba delete(Integer id) {
		Soba s = sobaRepo.findOne(id);
		if (s != null) {
			sobaRepo.delete(id);
		}
		return s;
	}

}
