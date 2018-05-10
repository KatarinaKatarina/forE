package jwd.zavrsni.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="soba")
public class Soba {
	
	@Id
	@GeneratedValue
	@Column
	private Integer id;
	@Column(nullable=false)
	private String tip;
	@Column(nullable=false)
	private int brojKreveta;
	@Column(nullable=false)
	private double cenaNocenja;
	
	@OneToMany(mappedBy="soba", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Rezervacija> rezervacije = new ArrayList<>();
	//Ako bi stavila ArrayList umesto List, javlja se sledeca greska:
	//Illegal attempt to map a non collection as a @OneToMany, @ManyToMany or @CollectionOfElements
	

	
	
	public void addRezervacija(Rezervacija rez) {
		rezervacije.add(rez); //this.rezervacije ako mi se i parametar zove isto, ali nece
		
		if(!this.equals(rez.getSoba())) {
			rez.setSoba(this);
		}
	}
	
	public void removeRezervacija(Rezervacija rez) {
		rez.setSoba(null);
		rezervacije.remove(rez); //moze i this.rezervacije
	}
	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public int getBrojKreveta() {
		return brojKreveta;
	}

	public void setBrojKreveta(int brojKreveta) {
		this.brojKreveta = brojKreveta;
	}

	public double getCenaNocenja() {
		return cenaNocenja;
	}

	public void setCenaNocenja(double cenaNocenja) {
		this.cenaNocenja = cenaNocenja;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(ArrayList<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}
	
	
	
	
	

}
