package com.immobilier.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "location")
public class Location {
	
	@Id
	@GeneratedValue
	@Column(name = "id_location")
	private long id_location;
	@Column(name = "ville")
	private String ville;
	@Column(name = "region")
	private String region;
	@Column(name = "zip")
	private long ZIP;
	@Column(name = "adresse")
	private String address;
	@Column(name = "coordonneXGoogleMap")
	private double coordonneXGoogleMap;
	@Column(name = "coordonneYGoogleMap")
	private double coordonneYGoogleMap;
	
	@JsonBackReference(value="location-annonce")
	@OneToOne(mappedBy = "location")
	private Annonce annonce;

	public Location(long id_location, String ville, String region, long zIP, String adresse, double coordonneXGoogleMap,
			double coordonneYGoogleMap, Annonce annonce) {
		super();
		this.id_location = id_location;
		this.ville = ville;
		this.region = region;
		ZIP = zIP;
		this.address = adresse;
		this.coordonneXGoogleMap = coordonneXGoogleMap;
		this.coordonneYGoogleMap = coordonneYGoogleMap;
		this.annonce = annonce;
	}

	public Location() {
		super();
	}

	public long getId_location() {
		return id_location;
	}

	public void setId_location(long id_location) {
		this.id_location = id_location;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public long getZIP() {
		return ZIP;
	}

	public void setZIP(long zIP) {
		ZIP = zIP;
	}



	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public double getCoordonneXGoogleMap() {
		return coordonneXGoogleMap;
	}

	public void setCoordonneXGoogleMap(double coordonneXGoogleMap) {
		this.coordonneXGoogleMap = coordonneXGoogleMap;
	}

	public double getCoordonneYGoogleMap() {
		return coordonneYGoogleMap;
	}

	public void setCoordonneYGoogleMap(double coordonneYGoogleMap) {
		this.coordonneYGoogleMap = coordonneYGoogleMap;
	}

	public Annonce getAnnonce() {
		return annonce;
	}

	public void setAnnonce(Annonce annonce) {
		this.annonce = annonce;
	}
	
	

}
