package jwd.zavrsni.support;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.repository.RezervacijaRepository;
import jwd.zavrsni.repository.SobaRepository;
import jwd.zavrsni.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija> {

	@Autowired
	private SobaRepository sobaRepo;
	@Autowired
	private RezervacijaRepository rezRepo;

	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		Rezervacija model = null;

		if (dto.getId() == null) {
			model = new Rezervacija();
			model.setId(dto.getId());
		} else {
			model = rezRepo.findOne(dto.getId());
		}
		model.setPlaceno(dto.isPlaceno());
		model.setImeGosta(dto.getImeGosta());
		model.setPrezimeGosta(dto.getPrezimeGosta());

		try {
			model.setDatumUlaska(dto.getDatumUlaska());
			System.out.println("DTO predao datumUlaska");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			model.setDatumIzlaska(dto.getDatumIzlaska());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		model.setSoba(sobaRepo.getOne(dto.getSobaId()));
		

		return model;
	}

	public List<Rezervacija> convert(List<RezervacijaDTO> dtos) {
		List<Rezervacija> models = new ArrayList<>();
		for (RezervacijaDTO m : dtos) {
			models.add(convert(m));
		}

		return models;
	}

}
