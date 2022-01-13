package com.immobilier.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.immobilier.dao.AnnonceDAO;
import com.immobilier.dao.UtilisateurDAO;
import com.immobilier.entities.Annonce;
import com.immobilier.entities.Utilisateur;


public class UtilisateurMetier{
	
	@Autowired
	private UtilisateurDAO dao;

	private AnnonceDAO daoAnnonce;
	
	public Utilisateur ajouter(Utilisateur u) {
		// TODO Auto-generated method stub
		return dao.save(u);
	}

	public boolean supprimer(Utilisateur u) {
		// TODO Auto-generated method stub
		 dao.delete(u);
		 return true;
	}

	public Utilisateur modifier(Utilisateur u) {
		// TODO Auto-generated method stub
		return dao.save(u);

	}

	@GetMapping("/utilisateurs")
	public List<Utilisateur> getAll() {
		// TODO Auto-generated method stub
		System.out.println(dao);
		return dao.findAll();
	}
	


}
