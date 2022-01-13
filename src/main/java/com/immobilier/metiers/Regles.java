package com.immobilier.metiers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.immobilier.config.JwtTokenUtil;
import com.immobilier.dao.CompteDAO;
import com.immobilier.entities.Compte;
import com.immobilier.entities.Utilisateur;

import io.jsonwebtoken.ExpiredJwtException;


public class Regles {
	
	@Autowired
    JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	CompteDAO daoCompte;
	
	public Utilisateur VerfiyTokensRoles(String jwtToken,Role role) {
		
	    String username=null;
		if ( jwtToken != null && jwtToken.startsWith("Bearer ")) {
			
			jwtToken = jwtToken.substring(7);
	    try {
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
		}
	    List<Compte> c =  daoCompte.findUserByStatusAndName(username);
		if(c.isEmpty() || c.get(0).getUtilisateur().getRole()!=role) return  null;
		return c.get(0).getUtilisateur();
	}
		return null;

   }
public Utilisateur VerfiyTokensRoles(String jwtToken) {
		
	    String username=null;
		if ( jwtToken != null && jwtToken.startsWith("Bearer ")) {
			
			jwtToken = jwtToken.substring(7);
	    try {
			username = jwtTokenUtil.getUsernameFromToken(jwtToken);
		} catch (IllegalArgumentException e) {
			System.out.println("Unable to get JWT Token");
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token has expired");
		}
	    List<Compte> c =  daoCompte.findUserByStatusAndName(username);
		if(c.isEmpty() ) return  null;
		return c.get(0).getUtilisateur();
	}
		return null;

   }
}
