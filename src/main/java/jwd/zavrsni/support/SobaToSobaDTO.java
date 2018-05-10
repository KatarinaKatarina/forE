package jwd.zavrsni.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Soba;
import jwd.zavrsni.web.dto.SobaDTO;

@Component
public class SobaToSobaDTO implements Converter<Soba, SobaDTO> {

	@Override
	public SobaDTO convert(Soba model) {
		SobaDTO dto = new SobaDTO();
		dto.setId(model.getId());
		dto.setTip(model.getTip());
		dto.setBrojKreveta(model.getBrojKreveta());
		dto.setCenaNocenja(model.getCenaNocenja());
		
		return dto;
	}

	
	public List<SobaDTO> convert(List<Soba> models) {
		List<SobaDTO> dtos = new ArrayList<>();
		for(Soba m: models) {
			dtos.add(convert(m));
		}
		
		return dtos;
	}
}
