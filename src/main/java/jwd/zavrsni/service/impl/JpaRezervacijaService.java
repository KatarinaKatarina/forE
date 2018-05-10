package jwd.zavrsni.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.model.Soba;
import jwd.zavrsni.repository.RezervacijaRepository;
import jwd.zavrsni.repository.SobaRepository;
import jwd.zavrsni.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService {
	@Autowired 
	//ako ovo zaboravis:  Exception encountered during context initialization - cancelling refresh attempt
	//baca NullPointerException i nece da radi, takodje INJECTION OF AUTOWIRED DEPENDENCIES FAILED
	private RezervacijaRepository rezervacijaRepo;
	@Autowired
	private SobaRepository sobaRepo;
	
	
	
	
	@Override
	public Rezervacija findOne(Integer id) {

		return rezervacijaRepo.findOne(id);
	}

	@Override
	public List<Rezervacija> findAll() {

		return rezervacijaRepo.findAll();
	}

	@Override
	public Rezervacija save(Rezervacija Rezervacija) {

		return rezervacijaRepo.save(Rezervacija);
	}

	@Override
	public Rezervacija delete(Integer id) {
		Rezervacija s = rezervacijaRepo.findOne(id);
		if (s != null) {
			rezervacijaRepo.delete(id);
		}
		return s;
	}

	@Override
	public List<Soba> pronadji(Date datumUlaska, Date datumIzlaska) {
		List<Soba> slobSobe = sobaRepo.findAll();//pretpostavimo da su sve, pa uklanjamo
		List<Rezervacija> rezUTomPeriodu = rezervacijaRepo.pronadji(datumUlaska, datumIzlaska);
		
		for (Rezervacija rezervacija : rezUTomPeriodu) {
			slobSobe.remove(rezervacija.getSoba());
			System.out.println("Brise rezervaciju.");
		}
		
		return slobSobe;
	}

	@Override
	public List<Rezervacija> pronadji(String imeP, String prezimeP) {
		return rezervacijaRepo.pronadji(imeP, prezimeP);
	}

	@Override
	public List<Rezervacija> pronadjiStatistika(String imeP, String prezimeP) {
		return rezervacijaRepo.pronadjiStatistika(imeP, prezimeP);
	}

}
