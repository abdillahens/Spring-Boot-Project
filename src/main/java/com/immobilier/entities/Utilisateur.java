package com.immobilier.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.immobilier.metiers.Role;

@Entity
@Table(name = "Utilisateur")
public class Utilisateur implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "id_utilisateur")
	private Long id;
	
	@Column(name = "nom_utilisateur")
	private String nom;
	
	@Column(name = "prenom_utilisateur")
	private String prenom;
	

	@Column(name = "date_naissance")
	private Date date_naissance;
	
	@Column(name = "numero_tele")
	private String numero_tele;
	
	@Column(name = "CIN")
	private String CIN;
	
	@Column(name = "role")
	private Role role;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "id_compte")
	private Compte compte;
	
	@OneToMany(mappedBy = "proprietaire")
	private List<Annonce> annonces;
	
	@OneToMany(mappedBy = "utilisateurReceiver")
	private List<Message> messagesRecus;

	@OneToMany(mappedBy = "client")
	private List<Contrat> contrats;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Reservation> reservations;
	
	public Utilisateur() {
		super();
	}

	public Utilisateur(Long id, String nom, String prenom, Date date_naissance, String numero_tele, String cIN,
			Role role, List<Message> messagesRecus, List<Contrat> contrats, List<Reservation> reservations) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.numero_tele = numero_tele;
		CIN = cIN;
		this.role = role;
		this.messagesRecus = messagesRecus;
		this.contrats = contrats;
		this.reservations = reservations;
	}
	
	public Utilisateur(Long id, String nom, String prenom, Date date_naissance, String numero_tele, String cIN,
			Role role, List<Annonce> annonces, List<Message> messagesRecus, List<Contrat> contrats,
			List<Reservation> reservations) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.date_naissance = date_naissance;
		this.numero_tele = numero_tele;
		CIN = cIN;
		this.role = role;
		this.annonces = annonces;
		this.messagesRecus = messagesRecus;
		this.contrats = contrats;
		this.reservations = reservations;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public Date getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(Date date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getNumero_tele() {
		return numero_tele;
	}
	public void setNumero_tele(String numero_tele) {
		this.numero_tele = numero_tele;
	}
	public String getCIN() {
		return CIN;
	}
	public void setCIN(String cIN) {
		CIN = cIN;
	}
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<Message> getMessagesRecus() {
		return messagesRecus;
	}
	
	public void setMessagesRecus(List<Message> messagesRecus) {
		this.messagesRecus = messagesRecus;
	}

	@JsonManagedReference(value="contrat-client")
	public List<Contrat> getContrats() {
		return contrats;
	}
	
	public void setContrats(List<Contrat> contrats) {
		this.contrats = contrats;
	}

	@JsonManagedReference(value="user-movement")
	public List<Reservation> getReservations() {
		return reservations;
	}
	
	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	@JsonManagedReference(value="annonce-user-movement")
	public List<Annonce> getAnnonces() {
		return annonces;
	}

	public void setAnnonces(List<Annonce> annonces) {
		this.annonces = annonces;
	}

	@JsonManagedReference
	public Compte getCompte() {
		return compte;
	}

	public void setCompte(Compte compte) {
		this.compte = compte;
	}

}
