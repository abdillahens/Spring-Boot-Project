package com.immobilier.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.immobilier.entities.Utilisateur;

public interface UtilisateurDAO extends JpaRepository<Utilisateur, Long>{
	
	

}
