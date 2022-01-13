package com.immobilier.metiers;

import java.util.List;

import com.immobilier.entities.Utilisateur;

public interface CRUD {

	public Utilisateur ajouter(Utilisateur u) ;
	public boolean supprimer(Utilisateur u);
	public Utilisateur modifier(Utilisateur u);
	public List<Utilisateur> getAll();
	
	
}
