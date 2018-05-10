package jwd.zavrsni;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwd.zavrsni.model.Rezervacija;
import jwd.zavrsni.model.Soba;
import jwd.zavrsni.service.RezervacijaService;
import jwd.zavrsni.service.SobaService;

@Component
public class TestData {

	@Autowired
	private SobaService sobaService;

	@Autowired
	private RezervacijaService rezervacijaService;

	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	@PostConstruct
	public void init() {

		Soba soba = new Soba();
		soba.setTip("Studio");
		soba.setBrojKreveta(2);
		soba.setCenaNocenja(2500.00);
		System.out.println(soba.getTip());
		sobaService.save(soba); // komuniciras sa servisom, ne sa bazom!

		Soba soba1 = new Soba();
		soba1.setTip("Studio");
		soba1.setBrojKreveta(3);
		soba1.setCenaNocenja(3500.00);
		sobaService.save(soba1);

		Soba soba2 = new Soba();
		soba2.setTip("Suite");
		soba2.setBrojKreveta(2);
		soba2.setCenaNocenja(2500.00);
		sobaService.save(soba2);

		Soba soba3 = new Soba();
		soba3.setTip("Familly");
		soba3.setBrojKreveta(5);
		soba3.setCenaNocenja(10500.00);
		sobaService.save(soba3);

		Soba soba4 = new Soba();
		soba4.setTip("Interconected");
		soba4.setBrojKreveta(7);
		soba4.setCenaNocenja(13500.00);
		sobaService.save(soba4);

		Rezervacija rez = new Rezervacija();
		rez.setImeGosta("A");
		rez.setPrezimeGosta("A");
		try {
			rez.setDatumUlaska(sdf.parse("2017-08-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			rez.setDatumIzlaska(sdf.parse("2017-08-10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rez.setSoba(soba1);
		rezervacijaService.save(rez);

		Rezervacija rez1 = new Rezervacija();
		rez1.setImeGosta("A");
		rez1.setPrezimeGosta("A");
		try {
			rez1.setDatumUlaska(sdf.parse("2017-08-08"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			rez1.setDatumIzlaska(sdf.parse("2017-08-10"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rez1.setSoba(soba2);
		rezervacijaService.save(rez1);

		Rezervacija rez3 = new Rezervacija();
		rez3.setImeGosta("K");
		rez3.setPrezimeGosta("K");
		try {
			rez3.setDatumUlaska(sdf.parse("2017-09-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			rez3.setDatumIzlaska(sdf.parse("2017-09-03"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		rez3.setSoba(soba2);
		System.out.println();
		rezervacijaService.save(rez3);

		Rezervacija rez5 = new Rezervacija();
		rez5.setImeGosta("A");
		rez5.setPrezimeGosta("A");
		try {
			rez5.setDatumUlaska(sdf.parse("2017-08-08"));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		try {
			rez5.setDatumIzlaska(sdf.parse("2017-08-10"));
		} catch (ParseException e) {

			e.printStackTrace();
		}
		rez5.setSoba(soba4);
		rezervacijaService.save(rez5);

	}
}
