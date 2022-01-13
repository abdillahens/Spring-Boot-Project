package com.immobilier.controlleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.immobilier.dao.CompteDAO;
import com.immobilier.entities.Compte;

    @RestController
    @CrossOrigin("*")
    public class CompteControlleur {
	
	@Autowired
	private CompteDAO dao;
	
	@PostMapping("/create-compte")
	public Compte createCompte(@RequestParam Compte c) {
		return dao.save(c);
	}
	
	@PostMapping("/modify-compte")
	public Compte modifierCompte(@RequestParam Compte compte) {
		return dao.save(compte);
	}
	
	@GetMapping("/getCompte/{id}")
	public Compte getCompte(@PathVariable Long id) {
		return dao.getById(id);
	}
	
	public void connecter() {
		
	}

}
