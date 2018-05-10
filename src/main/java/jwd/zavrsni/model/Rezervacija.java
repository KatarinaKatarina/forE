package jwd.zavrsni.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {

	@Column
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable = false)
	private Date datumUlaska;
	@Column(nullable = false)
	private Date datumIzlaska;
	@Column(nullable = false)
	private String imeGosta;
	@Column(nullable = false)
	private String prezimeGosta;
	private boolean placeno;

	@ManyToOne(fetch = FetchType.EAGER)
	private Soba soba;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDatumUlaska() {
		return datumUlaska;
	}

	public void setDatumUlaska(Date datumUlaska) {
		this.datumUlaska = datumUlaska;
	}

	public Date getDatumIzlaska() {
		return datumIzlaska;
	}

	public void setDatumIzlaska(Date datumIzlaska) {
		this.datumIzlaska = datumIzlaska;
	}

	public String getImeGosta() {
		return imeGosta;
	}

	public void setImeGosta(String imeGosta) {
		this.imeGosta = imeGosta;
	}

	public String getPrezimeGosta() {
		return prezimeGosta;
	}

	public void setPrezimeGosta(String prezimeGosta) {
		this.prezimeGosta = prezimeGosta;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;

		if (soba != null && !soba.getRezervacije().contains(this)) {
			// .contains metoda koristi .equals metodu
			// mi je nismo definisali, tako da se podrazumeva default .equals
			// metoda-definisana u Object klasi
			// u tom slucaju, objekti su isti SAMO ako su im reference jednake -najstrozi
			// nacin provere
			// this method returns true if and only if x and y refer to the same object (x
			// == y has the value true).

			soba.getRezervacije().add(this);

		}
	}

	public boolean isPlaceno() {
		return placeno;
	}

	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}

}
