package jwd.zavrsni.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.zavrsni.model.Soba;
import jwd.zavrsni.service.RezervacijaService;
import jwd.zavrsni.service.SobaService;
import jwd.zavrsni.support.SobaToSobaDTO;
import jwd.zavrsni.web.dto.SobaDTO;

@RestController
@RequestMapping(value = "api/sobe")
public class SobaController {

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@Autowired
	private SobaService sobaService;

	@Autowired
	private RezervacijaService rezService;
	@Autowired
	private SobaToSobaDTO sobaToDto;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<SobaDTO>> getSoba(@RequestParam(required = false) String datumUlaska,
			@RequestParam(required = false) String datumIzlaska) {

		Date datumUlaskaD = null;
		try {
			datumUlaskaD = sdf.parse(datumUlaska);
		} catch (Exception e) {
			System.out.println("Greska u prevodjenju datuma.");
		}

		Date datumIzlaskaD = null;
		try {
			datumIzlaskaD = sdf.parse(datumIzlaska);
		} catch (Exception e) {
			System.out.println("Greska u prevodjenju datuma.");
		}
		

		List<Soba> sobe = null;

		if (datumUlaskaD != null && datumIzlaskaD != null && datumIzlaskaD.before(datumUlaskaD)) { //ako datumi postoje, a nisu usladjeni
			System.out.println("Datum ulaska je posle datuma izlaska.");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
		if(datumIzlaskaD == null || datumIzlaska == null) { //datumi ne postoje
			sobe = sobaService.findAll();
		}
		
		if (datumUlaskaD != null && datumIzlaskaD != null ) { //datumi postoje i uskladjeni su
			sobe = rezService.pronadji(datumUlaskaD, datumIzlaskaD);
			System.out.println("Po datumima: " + sdf.format(datumUlaskaD) + "  -  " + sdf.format(datumIzlaskaD));
		} 
		
		if (sobe.isEmpty() || sobe == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(sobaToDto.convert(sobe), HttpStatus.OK);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handleExceptions() { // nismo rekli koji tip
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
