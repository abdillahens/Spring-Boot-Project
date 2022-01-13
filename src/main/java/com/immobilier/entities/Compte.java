package com.immobilier.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.aspectj.weaver.tools.Trace;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "compte")
public class Compte implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id_compte;
	
	@JsonBackReference
	@OneToOne(mappedBy = "compte")
	private Utilisateur utilisateur;
	
	@Column(name = "email",unique = true,nullable = false)
	private String email;
	
	@Column(name = "mot_de_pass",nullable = false)
	private String mot_de_pass;

	public Long getId_compte() {
		return id_compte;
	}

	public void setId_compte(Long id_compte) {
		this.id_compte = id_compte;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMot_de_pass() {
		return mot_de_pass;
	}

	public void setMot_de_pass(String mot_de_pass) {
		this.mot_de_pass = mot_de_pass;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Compte() {
		super();
	}

	public Compte(Long id_compte, Utilisateur utilisateur, String email, String mot_de_pass) {
		super();
		this.id_compte = id_compte;
		this.utilisateur = utilisateur;
		this.email = email;
		this.mot_de_pass = mot_de_pass;
	}
	
}
