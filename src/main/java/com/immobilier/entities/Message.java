package com.immobilier.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.immobilier.metiers.Administrateur;


@Entity
@Table(name = "Message")
public class Message {

	@Id
	@GeneratedValue
	@Column(name = "id_Message")
	private int id_Message;
	
	@Column(name = "message_Contenu")
	private String message;
	
	@Column(name = "date_envoie")
	private Date dateEnvoie;
	
	@ManyToOne
	@JoinColumn(name = "id_utilisateur")
	Utilisateur utilisateurReceiver;

	public int getId_Message() {
		return id_Message;
	}

	public void setId_Message(int id_Message) {
		this.id_Message = id_Message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDateEnvoie() {
		return dateEnvoie;
	}

	public void setDateEnvoie(Date dateEnvoie) {
		this.dateEnvoie = dateEnvoie;
	}


	public Utilisateur getUtilisateurReceiver() {
		return utilisateurReceiver;
	}

	public void setUtilisateurReceiver(Utilisateur utilisateurReceiver) {
		this.utilisateurReceiver = utilisateurReceiver;
	}

	public Message(int id_Message, String message, Date dateEnvoie, Utilisateur utilisateurReceiver) {
		super();
		this.id_Message = id_Message;
		this.message = message;
		this.dateEnvoie = dateEnvoie;
		this.utilisateurReceiver = utilisateurReceiver;
	}
	
	
	
}
