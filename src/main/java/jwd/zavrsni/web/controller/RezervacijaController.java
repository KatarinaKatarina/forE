package jwd.zavrsni.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.service.RezervacijaService;
import jwd.zavrsni.support.RezervacijaDtoToRezervacija;
import jwd.zavrsni.support.RezervacijaToRezervacijaDTO;
import jwd.zavrsni.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "api/rezervacije")
public class RezervacijaController {
	@Autowired
	private RezervacijaService rezervacijaService;

	@Autowired
	private RezervacijaDtoToRezervacija dtoToRezervacija;

	@Autowired
	private RezervacijaToRezervacijaDTO rezervacijaTodto;

	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<RezervacijaDTO>> getRezervacija(@RequestParam(required = false) String imeP,
			@RequestParam(required = false) String prezimeP, @RequestParam(required = false) Boolean isStatistika) {
		System.out.println(imeP);

		List<Rezervacija> rezSve;
		if (prezimeP != null && imeP != null && isStatistika == null) {
			rezSve = rezervacijaService.pronadji(imeP, prezimeP);
			System.out.println("Pretraga sa parametrima bez statistike.Ukupno " + rezSve.size());
			return new ResponseEntity<>(rezervacijaTodto.convert(rezSve), HttpStatus.OK);
		}
		if (prezimeP != null && imeP != null && isStatistika != null) {
			rezSve = rezervacijaService.pronadjiStatistika(imeP, prezimeP);
			System.out.println("Pretraga sa parametrima - statistika.Ukupno " + rezSve.size());
			return new ResponseEntity<>(rezervacijaTodto.convert(rezSve), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // ako stavis ovo pod ELSE,
		// svaki put kad ne bude STATISTIKA, bacace BadRequest.

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<RezervacijaDTO> getRezervacija(@PathVariable Integer id) {
		Rezervacija fes = rezervacijaService.findOne(id);
		if (fes == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(rezervacijaTodto.convert(fes), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<Rezervacija> delete(@PathVariable Integer id) {
		rezervacijaService.delete(id);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<RezervacijaDTO> add(@RequestBody @Validated RezervacijaDTO newRezervacija) {

		Rezervacija rezervacija = dtoToRezervacija.convert(newRezervacija);

		Rezervacija savedRezervacija = rezervacijaService.save(rezervacija);

		return new ResponseEntity<>(rezervacijaTodto.convert(savedRezervacija), HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<RezervacijaDTO> edit(@RequestBody @Validated RezervacijaDTO novDto,
			@PathVariable Integer id) {

		if (id != novDto.getId()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Rezervacija persisted = rezervacijaService.save(dtoToRezervacija.convert(novDto));

		return new ResponseEntity<>(rezervacijaTodto.convert(persisted), HttpStatus.OK);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handleExceptions() { // nismo rekli koji tip
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	// za glasanje
	@RequestMapping(method = RequestMethod.POST, value = "/plati/{id}")
	public ResponseEntity<Void> buy(@PathVariable Integer id) {

		Rezervacija g = rezervacijaService.findOne(id);

		if (g == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			g.setPlaceno(true);
			rezervacijaService.save(g);
			return new ResponseEntity<>(HttpStatus.OK);
		}

	}

}
