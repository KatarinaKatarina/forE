package jwd.zavrsni.support;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.web.dto.RezervacijaDTO;

@Component
public class RezervacijaToRezervacijaDTO implements Converter<Rezervacija, RezervacijaDTO>{
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
	
	@Override
	public RezervacijaDTO convert(Rezervacija model) {
		
		RezervacijaDTO dto = new RezervacijaDTO();
		
		dto.setId(model.getId());
		dto.setDatumUlaska(model.getDatumUlaska());
		dto.setDatumIzlaska(model.getDatumIzlaska());
		dto.setImeGosta(model.getImeGosta());
		dto.setPrezimeGosta(model.getPrezimeGosta());
		dto.setPlaceno(model.isPlaceno());
		
		dto.setDatumIzlaskaStr(sdf.format(model.getDatumIzlaska()));
		dto.setDatumUlaskaStr(sdf.format(model.getDatumUlaska()));
		
		dto.setSobaId(model.getSoba().getId());
		dto.setSobaTip(model.getSoba().getTip());
		dto.setBrojKreveta(model.getSoba().getBrojKreveta());
		dto.setCenaPoNoci(model.getSoba().getCenaNocenja());
		
		
		return dto;
	}

	
	public List<RezervacijaDTO> convert(List<Rezervacija> models) {
		List<RezervacijaDTO> dtos = new ArrayList<>();
		for(Rezervacija m: models) {
			dtos.add(convert(m));
		}
		
		return dtos;
	}

}
