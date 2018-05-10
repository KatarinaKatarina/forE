package jwd.zavrsni.web.dto;

import javax.persistence.Column;

public class SobaDTO {
	
	private Integer id;
	
	private String tip;
	
	private int brojKreveta;
	
	private double cenaNocenja;

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
	
	

}
