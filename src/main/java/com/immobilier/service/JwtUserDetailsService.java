package com.immobilier.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.immobilier.dao.CompteDAO;
import com.immobilier.entities.Compte;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	CompteDAO daoCompte;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Compte> c =  daoCompte.findUserByStatusAndName(username);
		
		if (!c.isEmpty() && c.get(0).getEmail().equals(username)) {
			return new User(username, c.get(0).getMot_de_pass(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
}
