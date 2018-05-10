package jwd.zavrsni.web.dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RezervacijaDTO {
	
	public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	private Integer id;
	@NotBlank@NotNull
	private String imeGosta;
	@NotBlank@NotNull
	private String prezimeGosta;
	private boolean placeno;
	
	@NotNull
	private String datumUlaskaStr;
	@NotNull
	private String datumIzlaskaStr;
	private Date datumUlaska; //s obzirom da se uzimaju iz DateStr, u get-u to podesi.
	private Date datumIzlaska;
	
	private Integer sobaId;
	private String sobaTip;
	private Integer brojKreveta;
	private Double cenaPoNoci;
	private int brojDana;
	
	
	
	
	public RezervacijaDTO() {
		super();
	}
	public String getDatumIzlaskaStr() {
		return datumIzlaskaStr;
	}
	public void setDatumIzlaskaStr(String datumIzlaskaStr) {
		this.datumIzlaskaStr = datumIzlaskaStr;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setDatumUlaska(Date datumUlaska) {
		this.datumUlaska = datumUlaska;
	}
	
	public Date getDatumUlaska() throws ParseException {
		return sdf.parse(datumUlaskaStr);
	}
	public Date getDatumIzlaska() throws ParseException {
		return sdf.parse(datumIzlaskaStr);
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
	public Integer getSobaId() {
		return sobaId;
	}
	public void setSobaId(Integer sobaId) {
		this.sobaId = sobaId;
	}
	public String getSobaTip() {
		return sobaTip;
	}
	public void setSobaTip(String sobaTip) {
		this.sobaTip = sobaTip;
	}
	public String getDatumUlaskaStr() {
		return datumUlaskaStr;
	}
	public void setDatumUlaskaStr(String datumUlaskaStr) {
		this.datumUlaskaStr = datumUlaskaStr;
	}
	public boolean isPlaceno() {
		return placeno;
	}
	public void setPlaceno(boolean placeno) {
		this.placeno = placeno;
	}
	public Integer getBrojKreveta() {
		return brojKreveta;
	}
	public void setBrojKreveta(Integer brojKreveta) {
		this.brojKreveta = brojKreveta;
	}
	public Double getCenaPoNoci() {
		return cenaPoNoci;
	}
	public void setCenaPoNoci(Double cenaPoNoci) {
		this.cenaPoNoci = cenaPoNoci;
	}
	public int getBrojDana() {
		return (int)Math.round((datumIzlaska.getTime() - datumUlaska.getTime()) / (double) 86400000);
	}
	public void setBrojDana(int brojDana) {
		this.brojDana = brojDana;
	}

}
