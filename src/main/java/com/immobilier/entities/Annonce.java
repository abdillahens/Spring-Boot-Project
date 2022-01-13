package com.immobilier.entities;

import java.sql.Date;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.immobilier.metiers.Etat;
import com.immobilier.metiers.Operation;
import com.immobilier.metiers.TypeAnnonce;

@Entity
@Table(name = "annonce")
public class Annonce {
	
	@Id
	@GeneratedValue
	@Column(name = "id_annonce")
	private Long id_annonce;
	
	@Column(name = "titre_annonce")
	private String titre;
	
	@Column(name="date_annonce")
	private Date date_annonce;
	
	@Column(name = "desription")
	private String description;
	
	@OneToMany(mappedBy = "annonce")
	private List<Photo> photos;
	
	@Column(name = "prix")
	private float prix;
	@Column(name = "etat_annonce")
	private Etat etat;
	
	@Column
	private TypeAnnonce typeAnnonce;
	
	@Column
	private Operation typeOperation;
	
	private boolean estReserver;
	
	
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur proprietaire;
	@JsonManagedReference(value="location-annonce")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_location")
	private Location location;
	
	
	
	@OneToMany(mappedBy = "reserveAnnonce",cascade = CascadeType.ALL)
	private List<Reservation> reservation;
	



	public Annonce(Long id_annonce, String titre, Date date_annonce, String description, List<Photo> photos, float prix,
			Etat etat, TypeAnnonce typeAnnonce, Operation operation, Utilisateur proprietaire, Location location,
			List<Reservation> reservation) {
		super();
		this.id_annonce = id_annonce;
		this.titre = titre;
		this.date_annonce = date_annonce;
		this.description = description;
		this.photos = photos;
		this.prix = prix;
		this.etat = etat;
		this.typeAnnonce = typeAnnonce;
		this.typeOperation = operation;
		this.proprietaire = proprietaire;
		this.location = location;
		this.reservation = reservation;
	}
	


	public boolean isEstReserver() {
		return estReserver;
	}



	public void setEstReserver(boolean estReserver) {
		this.estReserver = estReserver;
	}


	



	public Operation getTypeOperation() {
		return typeOperation;
	}



	public void setTypeOperation(Operation typeOperation) {
		this.typeOperation = typeOperation;
	}



	public List<Photo> getPhotos() {
		return photos;
	}


	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}



	@JsonManagedReference(value="annonce-movement")
	public List<Reservation> getReservation() {
		return reservation;
	}


	public void setReservation(List<Reservation> reservation) {
		this.reservation = reservation;
	}


	public Annonce() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getId_annonce() {
		return id_annonce;
	}

	public void setId_annonce(Long id_annonce) {
		this.id_annonce = id_annonce;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public Date getDate_annonce() {
		return date_annonce;
	}

	public void setDate_annonce(Date date_annonce) {
		this.date_annonce = date_annonce;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	

	public float getPrix() {
		return prix;
	}

	public void setPrix(float prix) {
		this.prix = prix;
	}

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}


	@JsonBackReference(value="annonce-user-movement")
	public Utilisateur getProprietaire() {
		return proprietaire;
	}

	public void setProprietaire(Utilisateur proprietaire) {
		this.proprietaire = proprietaire;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public TypeAnnonce getTypeAnnonce() {
		return typeAnnonce;
	}

	public void setTypeAnnonce(TypeAnnonce typeAnnonce) {
		this.typeAnnonce = typeAnnonce;
	}
	
	
	

}
