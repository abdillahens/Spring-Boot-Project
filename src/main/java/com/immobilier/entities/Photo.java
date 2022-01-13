package com.immobilier.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Photo {

	@Id
	private String url;
	

	@ManyToOne(cascade=CascadeType.MERGE)
	@JoinColumn(name = "id_annonce")
	private Annonce annonce;


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public Annonce getAnnonce() {
		return annonce;
	}


	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}


	public Photo(String url, Annonce annonce) {
		super();
		this.url = url;
		this.annonce = annonce;
	}
	
	
}
