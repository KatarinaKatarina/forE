package jwd.zavrsni.service;

import java.util.Date;
import java.util.List;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.model.Soba;

public interface RezervacijaService {
	Rezervacija findOne(Integer id);

	List<Rezervacija> findAll();

	Rezervacija save(Rezervacija Rezervacija);

	Rezervacija delete(Integer id);

	List<Soba> pronadji(Date datumUlaska, Date datumIzlaska);

	List<Rezervacija> pronadji(String imeP, String prezimeP);

	List<Rezervacija> pronadjiStatistika(String imeP, String prezimeP);

}
