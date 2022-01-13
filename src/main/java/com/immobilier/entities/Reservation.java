package com.immobilier.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "reservation")
public class Reservation {
	
	@Id
	@GeneratedValue
	@Column(name = "id_reservation")
	private Long id_Reservation;

	@ManyToOne
	@JoinColumn(name = "id_annonce")
	private Annonce reserveAnnonce;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	private Utilisateur utilisateur;
	@JsonManagedReference(value="contrat-reservation")
	@OneToOne(mappedBy = "reservation")
	private Contrat contrat;
	
	public Reservation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reservation(Long id_Reservation, Annonce reserveAnnonce, Utilisateur utilisateur) {
		super();
		this.id_Reservation = id_Reservation;
		this.reserveAnnonce = reserveAnnonce;
		this.utilisateur = utilisateur;
	}
	
	public Long getId_Reservation() {
		return id_Reservation;
	}

	public void setId_Reservation(Long id_Reservation) {
		this.id_Reservation = id_Reservation;
	}

	@JsonBackReference(value="annonce-movement")
	public Annonce getReserveAnnonce() {
		return reserveAnnonce;
	}

	public void setReserveAnnonce(Annonce reserveAnnonce) {
		this.reserveAnnonce = reserveAnnonce;
	}

	@JsonBackReference(value="user-movement")
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

}
