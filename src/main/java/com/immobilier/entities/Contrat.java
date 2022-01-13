package com.immobilier.entities;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.immobilier.metiers.Client;
import com.immobilier.metiers.Operation;

@Entity
@Table(name = "contrat")
public class Contrat {
	
	@Id
	@GeneratedValue
	@TableGenerator(name = "id_contrat")
	private Long id_contrat;
	@Column(name = "date_contrat")
	private Date date_contrat;
	@Column(name = "prix")
	private double prix;
	@Column(name = "date_fin_contrat")
	private Date dateFinContrat;
	@Column(name = "description")
	private String description;
	@Column(name = "operation")
	private Operation operation;
	
	@JsonBackReference(value="contrat-reservation")
	@OneToOne
	@JoinColumn(name="id_Reservation")
	private Reservation reservation;

	@JsonBackReference(value="contrat-client")
	@ManyToOne
	@JoinColumn(name="id_utilisateur")
	private Utilisateur client;

	public Long getId_contrat() {
		return id_contrat;
	}

	public void setId_contrat(Long id_contrat) {
		this.id_contrat = id_contrat;
	}

	public Date getDate_contrat() {
		return date_contrat;
	}

	public void setDate_contrat(Date date_contrat) {
		this.date_contrat = date_contrat;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Date getDateFinContrat() {
		return dateFinContrat;
	}

	public void setDateFinContrat(Date dateFinContrat) {
		this.dateFinContrat = dateFinContrat;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Operation getOperation() {
		return operation;
	}

	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}


	public Utilisateur getClient() {
		return client;
	}

	public void setClient(Utilisateur client) {
		this.client = client;
	}

	public Contrat() {
		super();
	}

	public Contrat(Long id_contrat, Date date_contrat, double prix, Date dateFinContrat, String description,
			Operation operation, Reservation reservation, Utilisateur client) {
		super();
		this.id_contrat = id_contrat;
		this.date_contrat = date_contrat;
		this.prix = prix;
		this.dateFinContrat = dateFinContrat;
		this.description = description;
		this.operation = operation;
		this.reservation = reservation;
		this.client = client;
	}

}
